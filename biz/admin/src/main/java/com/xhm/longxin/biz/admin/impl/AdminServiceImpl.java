package com.xhm.longxin.biz.admin.impl;

import com.alibaba.citrus.util.StringUtil;
import com.xhm.longxin.biz.admin.interfaces.AdminService;
import com.xhm.longxin.biz.admin.vo.LoginVO;
import com.xhm.longxin.common.daoobject.Admin;

public class AdminServiceImpl implements AdminService {

	public Admin login(LoginVO vo) {
		if(StringUtil.isEqualsIgnoreCase(vo.getName(), "admin")){
			Admin admin=  new Admin();
			admin.setName(vo.getName());
			admin.setPassword(vo.getPassword());
			admin.setId(1l);
			return admin;
		}else{
			return null;
		}
	}

}
