/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import java.util.List;

import com.xhm.longxin.qth.dal.dataobject.AdminUser;
import com.xhm.longxin.qth.dal.query.AdminUserQuery;

/**
 * @author ren.zhangr
 *
 */
public interface AdminUserDao {
	/**
	 * 通过id查询用户
	 * */
	public AdminUser getAdminUserById(Long id);

	/**
	 * 通过id查询用户
	 * */
	public AdminUser getAdminUserByLoginId(String loginId);

	/**
	 * 通过用户名/密码查询用户，登录使用
	 * */
	public AdminUser getAdminUserByLoginIdAndPass(String loginId,
			String password);

	/**
	 * 新增用户，注册使用
	 * */
	public boolean addAdminUser(AdminUser user);

	public boolean updateAdminUser(AdminUser user);

	/**
	 * 删除用户，管理员用
	 * */
	public boolean deleteAdminUserById(Long id);

	/**
	 * 查询用户，分页
	 * */
	public List<AdminUser> query(AdminUserQuery adminUserQuery, int pageStart,
			int pageSize);

	/**
	 *查询用户，不分页
	 * */
	public List<AdminUser> query(AdminUserQuery adminUserQuery);

	/**
	 *查询用户count
	 * */
	public int queryCount(AdminUserQuery adminUserQuery);

}
