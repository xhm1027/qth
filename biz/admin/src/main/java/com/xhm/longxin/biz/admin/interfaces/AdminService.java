package com.xhm.longxin.biz.admin.interfaces;

import com.xhm.longxin.biz.admin.vo.LoginVO;
import com.xhm.longxin.common.daoobject.Admin;

/**
 * 
 * @author xhm.xuhm
 *
 */
public interface AdminService {
	/**
	 * ��¼
	 * @param vo
	 * @return User
	 */
	public Admin login(LoginVO vo);
}
