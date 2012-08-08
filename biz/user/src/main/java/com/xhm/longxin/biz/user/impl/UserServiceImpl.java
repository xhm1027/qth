package com.xhm.longxin.biz.user.impl;

import com.alibaba.citrus.util.StringUtil;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.biz.user.vo.LoginVO;
import com.xhm.longxin.common.daoobject.User;

public class UserServiceImpl implements UserService {

	public User login(LoginVO vo) {
		if(StringUtil.isEqualsIgnoreCase(vo.getName(), "test")){
			User user=  new User();
			user.setName(vo.getName());
			user.setPassword(vo.getPassword());
			user.setType("in");
			user.setId(1l);
			return user;
		}else{
			return null;
		}
	}

}
