/**
 *
 */
package com.xhm.longxin.qth.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.xhm.longxin.qth.dal.dao.AdminUserDao;
import com.xhm.longxin.qth.dal.dataobject.AdminUser;

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
	private static final String QUERY_BY_MAP = "QUERY_ADMIN_USER_BY_MAP";

	public boolean addAdminUser(AdminUser user) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_USER + "." + INSERT_ID, user);
		return res > 0 ? true : false;
	}

	public boolean deleteAdminUserById(Long id) {
		AdminUser user = new AdminUser();
		user.setId(id);
		user.setIsDeleted("y");
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
		AdminUser user = (AdminUser) getSqlMapClientTemplate().queryForObject(
				NAMESPACE_USER + "." + QUERY_BY_LOGIN_ID, loginId);
		return user;
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
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loginId", loginId);
		param.put("password", password);
		List<AdminUser> userList = (List<AdminUser>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_USER + "." + QUERY_BY_MAP, param);
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

}
