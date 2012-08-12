/**
 *
 */
package com.xhm.longxin.qth.dal.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.xhm.longxin.qth.dal.dao.UserDao;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.dataobject.UserInterest;

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
	private static final String QUERY_BY_MAP = "QUERY_USER_BY_MAP";

	private static final String INSERT_INTEREST = "INSERT_INTEREST";
	private static final String GET_EXIST_INTEREST = "GET_EXIST_INTEREST";
	private static final String DELETE_INTEREST_BY_IDS = "DELETE_INTEREST_BY_IDS";

	/**
	 * 保存
	 * */
	public boolean addUser(User user) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_USER + "." + INSERT_ID, user);
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
		user.setIsDeleted("y");
		Integer res = (Integer) getSqlMapClientTemplate().delete(
				NAMESPACE_USER + "." + UPDATE_ID, user);
		return res > 0 ? true : false;
	}

	/**
	 * 通过id查询
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
	public User getUserByLoginIdAndPass(String loginId, String password) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loginId", loginId);
		param.put("password", password);
		List<User> userList = (List<User>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_USER + "." + QUERY_BY_MAP, param);
		if (userList.size() != 1) {
			return null;
		}
		return userList.get(0);
	}

	/**
	 * 按条件查询，当查询公司的求购/售卖产品时，比较复杂，是否分表？
	 * */
	public List<User> queryUser(Map<String, Object> param) {
		List<User> userList = (List<User>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_USER + "." + QUERY_BY_MAP, param);
		return userList;
	}

	/**
	 * 更新用户，对类目的更新时，需要考虑插入的删除两种情况！！！先把数据库中id not in的查询出来，传入id的list，再插入或不动。
	 * */
	public boolean updateUser(User user) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_USER + "." + UPDATE_ID, user);
		List<String> buyInterests =null;
		if(user.getBuyInterests()!=null&&user.getBuyInterests().size()>0){
			buyInterests=new ArrayList<String>();
			for (UserInterest inst:user.getBuyInterests()){
				buyInterests.add(inst.getValue());
			}
		}
		List<String> saleInterests =null;
		if(user.getSaleInterests()!=null&&user.getSaleInterests().size()>0){
			saleInterests=new ArrayList<String>();
			for (UserInterest inst:user.getSaleInterests()){
				saleInterests.add(inst.getValue());
			}
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("buyInterests", buyInterests);
		param.put("saleInterests", saleInterests);
		param.put("loginId", user.getLoginId());
		// delete不存在的
		getSqlMapClientTemplate().update(
				NAMESPACE_USER + "." + DELETE_INTEREST_BY_IDS, param);
		// 插入或不动
		for (UserInterest interest : user.getBuyInterests()) {
			this.addOrUpdateUserInterest(interest);
		}
		for (UserInterest interest : user.getSaleInterests()) {
			this.addOrUpdateUserInterest(interest);
		}
		return res > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.UserDao#addUserInterest(com.xhm.longxin.qth
	 * .dal.dataobject.UserInterest)
	 */
	public boolean addOrUpdateUserInterest(UserInterest userInterest) {
		// 如果已经存在了，不插入
		if (getSqlMapClientTemplate().queryForList(
				NAMESPACE_USER + "." + GET_EXIST_INTEREST, userInterest).size() > 0) {
			return true;
		}
		// 否则插入
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_USER + "." + INSERT_INTEREST, userInterest);
		return res > 0 ? true : false;
	}
}
