/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import java.util.List;
import java.util.Map;

import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.dataobject.UserInterest;
import com.xhm.longxin.qth.dal.query.UserQuery;

/**
 * @author ren.zhangr
 *
 */
public interface UserDao{

	/**
	 * 通过id查询用户
	 * */
	public User getUserById(Long id);

	/**
	 * 通过id查询用户
	 * */
	public User getUserByLoginId(String loginId);

	/**
	 * 通过用户名/密码查询用户，登录使用
	 * */
	public User getUserByLoginIdAndPass(String loginId, String password);

	/**
	 * 新增用户，注册使用
	 * */
	public boolean addUser(User user);

	/**
	 * 查询用户，可通过各类条件查询，如用户名，邮箱，用户等级等等，管理员使用
	 * */
	public List<User> query(UserQuery userQuery);
	/**
	 * 查询用户，可通过各类条件查询，如用户名，邮箱，用户等级等等，管理员使用
	 * */
	public List<User> query(UserQuery userQuery,int pageStart,int pageSize);
	/**
	 * 查询用户数
	 * */
	public int queryCount(UserQuery userQuery);

	/**
	 * 更新用户，修改信息或管理员修改用户信息时用
	 * */
	public boolean updateUser(User user);

	/**
	 * 删除用户，管理员用
	 * */
	public boolean deleteUserById(Long id);

}
