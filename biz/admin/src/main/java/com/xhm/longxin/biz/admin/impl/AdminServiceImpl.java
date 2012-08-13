package com.xhm.longxin.biz.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.xhm.longxin.biz.admin.interfaces.AdminService;
import com.xhm.longxin.biz.admin.vo.LoginVO;
import com.xhm.longxin.qth.dal.dao.AdminUserDao;
import com.xhm.longxin.qth.dal.dataobject.AdminUser;

public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminUserDao adminUserDao;
	
	public AdminUser login(LoginVO vo) {
		
		return adminUserDao.getAdminUserByLoginIdAndPass(vo.getName(), vo.getPassword());
	}

	public AdminUser getAdminUserByLoginId(String loginId) {
		return adminUserDao.getAdminUserByLoginId(loginId);
	}
	
	
	public boolean updateAdminUser(AdminUser user){
		user.setLoginId(null);
		return adminUserDao.updateAdminUser(user);
	}

	public AdminUser getAdminUserById(Long id) {
		return adminUserDao.getAdminUserById(id);
	}

}
