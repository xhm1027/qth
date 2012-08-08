package com.xhm.longxin.biz.user.interfaces;

import com.xhm.longxin.biz.user.vo.LoginVO;
import com.xhm.longxin.common.daoobject.User;

/**
 * 
 * @author xhm.xuhm
 *
 */
public interface UserService {
	/**
	 * µÇÂ¼
	 * @param vo
	 * @return User
	 */
	public User login(LoginVO vo);
}
