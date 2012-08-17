package com.xhm.longxin.biz.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.biz.user.vo.LoginVO;
import com.xhm.longxin.biz.user.vo.UserAuditVO;
import com.xhm.longxin.qth.dal.constant.AuditResult;
import com.xhm.longxin.qth.dal.constant.AuditType;
import com.xhm.longxin.qth.dal.constant.UserStatus;
import com.xhm.longxin.qth.dal.dao.AuditLogDao;
import com.xhm.longxin.qth.dal.dao.UserDao;
import com.xhm.longxin.qth.dal.dataobject.AuditLog;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.query.UserQuery;

public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private AuditLogDao aditLogDao;

	public User login(LoginVO vo) {
		return userDao.getUserByLoginIdAndPass(vo.getName(), vo.getPassword());
	}

	public User getUserByLoginId(String loginId) {
		return userDao.getUserByLoginId(loginId);
	}

	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	public boolean addUser(User user) {
		return userDao.addUser(user);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.biz.user.interfaces.UserService#getUser(com.xhm.longxin
	 * .qth.dal.query.UserQuery)
	 */
	public List<User> queryUser(UserQuery userQuery, int pageStart, int pageSize) {
		return userDao.query(userQuery, pageStart, pageSize);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.biz.user.interfaces.UserService#queryCount(com.xhm.longxin
	 * .qth.dal.query.UserQuery)
	 */
	public int queryCount(UserQuery userQuery) {
		return userDao.queryCount(userQuery);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.biz.user.interfaces.UserService#auditUser(java.lang.Long,
	 * java.lang.String, java.lang.String)
	 */
	public boolean auditUser(UserAuditVO userAuditVO) {
		User user = userDao.getUserById(userAuditVO.getId());
		if (AuditResult.PASS.equalsIgnoreCase(userAuditVO.getAuditResult())) {
			user.setStatus(UserStatus.NORMAL);
		} else {
			user.setStatus(UserStatus.AUDIT_FAILED);
		}
		userDao.updateUser(user);
		AuditLog auditLog = new AuditLog();
		auditLog.setAuditId(userAuditVO.getId());
		if (AuditResult.PASS.equalsIgnoreCase(userAuditVO.getAuditResult())) {
			auditLog.setAuditResult(AuditResult.PASS);
		}
		else {
			auditLog.setAuditResult(AuditResult.FAIL);
		}
		auditLog.setDescription(userAuditVO.getDescription());
		auditLog.setAuditType(AuditType.USER);
		auditLog.setAuditor(userAuditVO.getAuditor());
		aditLogDao.addAuditLog(auditLog);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.xhm.longxin.biz.user.interfaces.UserService#getUserById(java.lang.Long)
	 */
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}

}
