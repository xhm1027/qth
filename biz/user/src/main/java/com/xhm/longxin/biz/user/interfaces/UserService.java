package com.xhm.longxin.biz.user.interfaces;

import com.xhm.longxin.biz.user.vo.LoginVO;
import com.xhm.longxin.qth.dal.dataobject.User;

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
	 * 根据email获取用户
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email);
	
	/**
	 * 新增用户
	 * */
	public boolean addUser(User user);
}
