/**
 *
 */
package com.xhm.longxin.qth.dal.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.xhm.longxin.qth.dal.dao.UserDao;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.dataobject.UserInterest;
import com.xhm.longxin.qth.dal.query.UserInterestVo;
import com.xhm.longxin.qth.dal.query.UserQuery;

/**
 * @author ren.zhangr
 *
 */
public class UserDaoImpl extends SqlMapClientDaoSupport implements UserDao {

	private static final String NAMESPACE_USER = "QTH_USER";
	private static final String INSERT_ID = "INSERT_USER";
	private static final String UPDATE_ID = "UPDATE_USER";
	private static final String QUERY_BY_ID = "QUERY_USER_BYID";
	private static final String QUERY_BY_LOGIN_ID = "QUERY_USER_BY_LOGINID";
	private static final String QUERY_BY_QUERYVO = "QUERY_BY_QUERYVO";
	private static final String QUERY_BY_QUERYVO_WITHINTEREST = "QUERY_BY_QUERYVO_WITHINTEREST";
	private static final String QUERY_COUNT_QUERYVO = "QUERY_COUNT_QUERYVO";
	private static final String QUERY_COUNT_QUERYVO_WITHINTEREST = "QUERY_COUNT_QUERYVO_WITHINTEREST";

	//对user_interest表的关联操作
	private static final String QTH_USER_INTEREST = "QTH_USER_INTEREST";
	private static final String INSERT_INTEREST = "INSERT_INTEREST";
	private static final String GET_EXIST_INTEREST = "GET_EXIST_INTEREST";
	private static final String DELETE_INTEREST_NOT_IN_IDS = "DELETE_INTEREST_NOT_IN_IDS";
	private static final String DELETE_INTEREST_BY_ID = "DELETE_INTEREST_BY_ID";

	/**
	 * 保存
	 * */
	public boolean addUser(User user) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_USER + "." + INSERT_ID, user);
		//插入关联内容
		if (user.getBuyInterests() != null) {
			for (UserInterest interest : user.getBuyInterests()) {
				this.addOrUpdateUserInterest(interest);
			}
		}
		if (user.getSaleInterests() != null) {
			for (UserInterest interest : user.getSaleInterests()) {
				this.addOrUpdateUserInterest(interest);
			}
		}
		return res > 0 ? true : false;
	}

	/**
	 * 删除
	 * */
	public boolean deleteUserById(Long id) {
		User user = new User();
		user.setId(id);
		user.setIsDeleted("Y");
		Integer res = (Integer) getSqlMapClientTemplate().delete(
				NAMESPACE_USER + "." + UPDATE_ID, user);
		//删除关联内容
		if (user.getBuyInterests() != null) {
			for (UserInterest interest : user.getBuyInterests()) {
				getSqlMapClientTemplate().update(
						QTH_USER_INTEREST + "." + DELETE_INTEREST_BY_ID,
						interest.getId());
			}
		}
		if (user.getSaleInterests() != null) {
			for (UserInterest interest : user.getSaleInterests()) {
				getSqlMapClientTemplate().update(
						QTH_USER_INTEREST + "." + DELETE_INTEREST_BY_ID,
						interest.getId());
			}
		}
		return res > 0 ? true : false;
	}

	/**
	 * 通过id查询，结果中会包括感兴趣的类目信息
	 * */
	public User getUserById(Long id) {
		User user = (User) getSqlMapClientTemplate().queryForObject(
				NAMESPACE_USER + "." + QUERY_BY_ID, id);
		return user;
	}

	/**
	 * 通过登录id查询
	 * */

	public User getUserByLoginId(String loginId) {
		User user = (User) getSqlMapClientTemplate().queryForObject(
				NAMESPACE_USER + "." + QUERY_BY_LOGIN_ID, loginId);
		return user;
	}

	/**
	 * 用户登录
	 * */
	@SuppressWarnings("unchecked")
	public User getUserByLoginIdAndPass(String loginId, String password) {
		UserQuery userQuery=new UserQuery();
		userQuery.setLoginId(loginId);
		userQuery.setPassword(password);
		List<User> userList = (List<User>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_USER + "." + QUERY_BY_QUERYVO, userQuery);
		if (userList.size() != 1) {
			return null;
		}
		return userList.get(0);
	}

	/**
	 * 按条件查询
	 * */
	@SuppressWarnings("unchecked")
	public List<User> query(UserQuery userQuery) {
		//分成两个sql，如果有兴趣点，则关联user_interest表
		if ((userQuery.getBuyInterestIds() != null && userQuery
				.getBuyInterestIds().size() > 0)
				|| (userQuery.getSaleInterestIds() != null && userQuery
						.getSaleInterestIds().size() > 0)) {
			List<User> userList = (List<User>) getSqlMapClientTemplate()
					.queryForList(NAMESPACE_USER + "." + QUERY_BY_QUERYVO_WITHINTEREST,
							userQuery);
			return userList;
		} else {
			//没有兴趣点，只需要查user表即可
			List<User> userList = (List<User>) getSqlMapClientTemplate()
					.queryForList(NAMESPACE_USER + "." + QUERY_BY_QUERYVO,
							userQuery);
			return userList;
		}

	}

	/**
	 * 更新用户，对类目的更新时，需要考虑插入的删除两种情况！！！先把数据库中id not in的查询出来，传入id的list，再插入或不动。
	 * 这个方法比较复杂一些
	 * */
	public boolean updateUser(User user) {
		//更新user表
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_USER + "." + UPDATE_ID, user);
		//感兴趣的求购类目id列表
		List<Long> buyInterestIds = null;
		if (user.getBuyInterests() != null && user.getBuyInterests().size() > 0) {
			buyInterestIds = new ArrayList<Long>();
			for (UserInterest inst : user.getBuyInterests()) {
				buyInterestIds.add(inst.getValue());
			}
		}
		//感兴趣的销售类目id列表
		List<Long> saleInterestIds = null;
		if (user.getSaleInterests() != null
				&& user.getSaleInterests().size() > 0) {
			saleInterestIds = new ArrayList<Long>();
			for (UserInterest inst : user.getSaleInterests()) {
				saleInterestIds.add(inst.getValue());
			}
		}
		UserInterestVo userInterests = new UserInterestVo();
		userInterests.setBuyInterestIds(buyInterestIds);
		userInterests.setSaleInterestIds(saleInterestIds);
		userInterests.setLoginId(user.getLoginId());
		// delete不存在的
		getSqlMapClientTemplate().update(
				QTH_USER_INTEREST + "." + DELETE_INTEREST_NOT_IN_IDS, userInterests);
		// 插入或不动
		for (UserInterest interest : user.getBuyInterests()) {
			this.addOrUpdateUserInterest(interest);
		}
		for (UserInterest interest : user.getSaleInterests()) {
			this.addOrUpdateUserInterest(interest);
		}
		return res > 0 ? true : false;
	}


	@SuppressWarnings("unchecked")
	public List<User> query(UserQuery userQuery, int pageStart, int pageSize) {
		userQuery.setPageSize(pageSize);
		userQuery.setPageStart(pageStart);
		if ((userQuery.getBuyInterestIds() != null && userQuery
				.getBuyInterestIds().size() > 0)
				|| (userQuery.getSaleInterestIds() != null && userQuery
						.getSaleInterestIds().size() > 0)) {
			List<User> userList = (List<User>) getSqlMapClientTemplate()
					.queryForList(NAMESPACE_USER + "." + QUERY_BY_QUERYVO_WITHINTEREST,
							userQuery);
			return userList;
		} else {
			List<User> userList = (List<User>) getSqlMapClientTemplate()
					.queryForList(NAMESPACE_USER + "." + QUERY_BY_QUERYVO,
							userQuery);
			return userList;
		}
	}

	/* (non-Javadoc)
	 * @see com.xhm.longxin.qth.dal.dao.UserDao#queryCount(com.xhm.longxin.qth.dal.query.UserQuery)
	 */
	public int queryCount(UserQuery userQuery) {
		if ((userQuery.getBuyInterestIds() != null && userQuery
				.getBuyInterestIds().size() > 0)
				|| (userQuery.getSaleInterestIds() != null && userQuery
						.getSaleInterestIds().size() > 0)) {
			int count = (Integer) getSqlMapClientTemplate().queryForObject(
					NAMESPACE_USER + "." + QUERY_COUNT_QUERYVO_WITHINTEREST, userQuery);
			return count;
		} else {
			int count = (Integer) getSqlMapClientTemplate().queryForObject(
					NAMESPACE_USER + "." + QUERY_COUNT_QUERYVO, userQuery);
			return count;
		}
	}

	private boolean addOrUpdateUserInterest(UserInterest userInterest) {
		// 如果已经存在了，不插入
		if (getSqlMapClientTemplate().queryForList(
				QTH_USER_INTEREST + "." + GET_EXIST_INTEREST, userInterest)
				.size() > 0) {
			return true;
		}
		// 否则插入
		Integer res = (Integer) getSqlMapClientTemplate().update(
				QTH_USER_INTEREST + "." + INSERT_INTEREST, userInterest);
		return res > 0 ? true : false;
	}

	public User getUserByEmail(String email) {
		//TODO 填写具体的sql
		return null;
	}
}
