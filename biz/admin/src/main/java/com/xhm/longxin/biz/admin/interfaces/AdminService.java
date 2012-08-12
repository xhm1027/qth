package com.xhm.longxin.biz.admin.interfaces;

import com.xhm.longxin.biz.admin.vo.LoginVO;
import com.xhm.longxin.qth.dal.dataobject.AdminUser;

/**
 * 
 * @author xhm.xuhm
 *
 */
public interface AdminService {
	/**
	 * 登录
	 * @param vo
	 * @return User
	 */
	public AdminUser login(LoginVO vo);
}
