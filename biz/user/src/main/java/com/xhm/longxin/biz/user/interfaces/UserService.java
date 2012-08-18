package com.xhm.longxin.biz.user.interfaces;

import java.util.List;

import com.xhm.longxin.biz.user.vo.LoginVO;
import com.xhm.longxin.biz.user.vo.UserAuditVO;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.query.UserQuery;

/**
 *
 * @author xhm.xuhm
 *
 */
public interface UserService {
	/**
	 * login
	 * @param vo
	 * @return User
	 */
	public User login(LoginVO vo);


	/**
	 * 根据id查询用户
	 * @param loginId
	 * @return
	 */
	public User getUserByLoginId(String loginId);

	/**
	 * 根据id查询用户
	 * @param loginId
	 * @return
	 */
	public User getUserById(Long id);
	/**
	 * 根据email获取用户
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email);

	/**
	 * 新增用户
	 * */
	public boolean addUser(User user);

	/**
	 * 查询用户
	 * */
	public List<User> queryUser(UserQuery userQuery,int pageStart,int pageSize);

	/**
	 * 查询用户
	 * */
	public int queryCount(UserQuery userQuery);

	/**
	 *审核用户
	 * */
	public boolean auditUser(UserAuditVO userAuditVO);


	/**
	 * @param id
	 * @return
	 */
	public String resetUserPass(User user);

	/**
	 * 更新用户
	 * */
	public boolean updateUser(User user);

	/**
	 * 冻结用户
	 * */
	public boolean freeseUser(Long id);

	/**
	 * 解结用户
	 * */
	public boolean unFreeseUser(Long id);
}
