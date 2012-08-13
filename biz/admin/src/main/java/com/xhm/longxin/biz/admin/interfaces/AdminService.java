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
	 * 登陆
	 * @param vo
	 * @return User
	 */
	public AdminUser login(LoginVO vo);
	
	/**
	 * 根据loginId获取对象
	 * @param loginId
	 * @return
	 */
	public AdminUser getAdminUserByLoginId(String loginId);
	
	
	/**
	 * 更新管理员密码
	 * @param user
	 * @return
	 */
	public boolean updateAdminUser(AdminUser user);
	
	/**
	 * 通过id查询用户
	 * */
	public AdminUser getAdminUserById(Long id);
}
