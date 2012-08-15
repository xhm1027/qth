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
	 * ����id��ѯ�û�
	 * @param loginId
	 * @return
	 */
	public User getUserByLoginId(String loginId);
	
	
	/**
	 * ����email��ȡ�û�
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email);
	
	/**
	 * �����û�
	 * */
	public boolean addUser(User user);
}
