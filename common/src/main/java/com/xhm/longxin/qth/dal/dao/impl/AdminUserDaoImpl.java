/**
 *
 */
package com.xhm.longxin.qth.dal.dao.impl;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dao.AdminUserDao;
import com.xhm.longxin.qth.dal.dataobject.AdminUser;
import com.xhm.longxin.qth.dal.query.AdminUserQuery;

/**
 * @author ren.zhangr
 *
 */
public class AdminUserDaoImpl extends SqlMapClientDaoSupport implements
		AdminUserDao {
	private static final String NAMESPACE_USER = "QTH_ADMIN_USER";
	private static final String INSERT_ID = "INSERT_ADMIN_USER";
	private static final String UPDATE_ID = "UPDATE_ADMIN_USER";
	private static final String QUERY_BY_ID = "QUERY_ADMIN_USER_BYID";
	private static final String QUERY_BY_LOGIN_ID = "QUERY_ADMIN_USER_BY_LOGINID";
	private static final String QUERY_BY_QUERYVO = "QUERY_ADMIN_USER_BY_QUERYVO";
	private static final String QUERY_COUNT = "QUERY_COUNT";

	public boolean addAdminUser(AdminUser user) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_USER + "." + INSERT_ID, user);
		return res > 0 ? true : false;
	}

	public boolean deleteAdminUserById(Long id) {
		AdminUser user = new AdminUser();
		user.setId(id);
		user.setIsDeleted(IS.Y);
		Integer res = (Integer) getSqlMapClientTemplate().delete(
				NAMESPACE_USER + "." + UPDATE_ID, user);
		return res > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.AdminUserDao#getAdminUserById(java.lang.Long)
	 */
	public AdminUser getAdminUserById(Long id) {
		AdminUser user = (AdminUser) getSqlMapClientTemplate().queryForObject(
				NAMESPACE_USER + "." + QUERY_BY_ID, id);
		return user;
	}

	public AdminUser getAdminUserByLoginId(String loginId) {
		List<AdminUser> users = (List<AdminUser>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_USER + "." + QUERY_BY_LOGIN_ID, loginId);
		return users.size() > 0 ? users.get(0) : null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.AdminUserDao#getAdminUserByLoginIdAndPass
	 * (java.lang.String, java.lang.String)
	 */
	public AdminUser getAdminUserByLoginIdAndPass(String loginId,
			String password) {
		AdminUserQuery adminUserQuery = new AdminUserQuery();
		adminUserQuery.setLoginId(loginId);
		adminUserQuery.setPassword(password);
		List<AdminUser> userList = (List<AdminUser>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_USER + "." + QUERY_BY_QUERYVO,
						adminUserQuery);
		if (userList.size() != 1) {
			return null;
		}
		return userList.get(0);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.AdminUserDao#updateAdminUser(com.xhm.longxin
	 * .qth.dal.dataobject.AdminUser)
	 */

	public boolean updateAdminUser(AdminUser user) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_USER + "." + UPDATE_ID, user);
		return res > 0 ? true : false;
	}

	public List<AdminUser> query(AdminUserQuery adminUserQuery) {
		List<AdminUser> userList = (List<AdminUser>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_USER + "." + QUERY_BY_QUERYVO,
						adminUserQuery);
		return userList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.query.BaseQuery#query(com.xhm.longxin.qth.dal
	 * .query.QueryObject, int, int)
	 */
	public List<AdminUser> query(AdminUserQuery adminUserQuery, int pageStart,
			int pageSize) {
		adminUserQuery.setPageStart(pageStart);
		adminUserQuery.setPageSize(pageSize);
		List<AdminUser> userList = (List<AdminUser>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_USER + "." + QUERY_BY_QUERYVO,
						adminUserQuery);
		return userList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.query.BaseQuery#queryCount(com.xhm.longxin.qth
	 * .dal.query.QueryObject)
	 */
	public int queryCount(AdminUserQuery adminUserQuery) {
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				NAMESPACE_USER + "." + QUERY_COUNT, adminUserQuery);
		return count;
	}

}
