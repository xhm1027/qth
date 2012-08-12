package com.xhm.longxin.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.biz.user.vo.LoginVO;
import com.xhm.longxin.qth.dal.dao.UserDao;
import com.xhm.longxin.qth.dal.dataobject.User;

public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	public User login(LoginVO vo) {
		return userDao.getUserByLoginIdAndPass(vo.getName(), vo.getPassword());
	}

}
