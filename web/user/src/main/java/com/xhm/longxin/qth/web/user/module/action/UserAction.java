/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xhm.longxin.qth.web.user.module.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.form.CustomErrors;
import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormField;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.citrus.util.StringUtil;

import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.biz.user.vo.LoginVO;
import com.xhm.longxin.qth.dal.constant.UserInterestType;
import com.xhm.longxin.qth.dal.constant.UserLevel;
import com.xhm.longxin.qth.dal.constant.UserRole;
import com.xhm.longxin.qth.dal.constant.UserStatus;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.dataobject.UserInterest;
import com.xhm.longxin.qth.email.EmailSender;
import com.xhm.longxin.qth.web.user.common.QthUser;
import com.xhm.longxin.qth.web.user.common.UserConstant;

public class UserAction {
	@Autowired
	private UserService userService;
	@Autowired
	HttpSession session;

	public void doLogin(
			@FormGroup("login") LoginVO vo,
			@FormField(name = "validateStr", group = "login") CustomErrors validateField,
			@FormField(name = "loginError", group = "login") CustomErrors err,
			Navigator nav, ParameterParser params) {
		String validateCode = (String) session
				.getAttribute(UserConstant.VALIDATE_CODE);
		if (validateCode == null
				|| StringUtils.equalsIgnoreCase(validateCode,
						vo.getValidateStr()) == false) {
			validateField.setMessage("validateError");
			return;
		}

		User user = userService.login(vo);
		if (user != null) {
			setSession(user);
			redirectToReturnPage(nav, params);
		} else {
			err.setMessage("invalidUserOrPassword");
		}

	}

	private void setSession(User user) {
		QthUser qthUser = (QthUser) session
				.getAttribute(UserConstant.QTH_USER_SESSION_KEY);

		if (qthUser == null || qthUser.hasLoggedIn()) {
			qthUser = new QthUser();
		}
		qthUser.upgrade(user.getLoginId(), user.getName(), user.getRole());

		session.setAttribute(UserConstant.QTH_USER_SESSION_KEY, qthUser);
	}

	public void doLogout(HttpSession session, Navigator nav,
			ParameterParser params) throws Exception {

		session.removeAttribute(UserConstant.QTH_USER_SESSION_KEY);

		redirectToReturnPage(nav, params);
	}

	private void redirectToReturnPage(Navigator nav, ParameterParser params) {
		String returnURL = params.getString("return");

		if (StringUtil.isEmpty(returnURL)) {
			nav.redirectTo(UserConstant.LOGIN_RETURN_DEFAULT_LINK);
		} else {
			nav.redirectToLocation(returnURL);
		}
	}

	public void doRegister(
			@FormGroup("register") User user,
			@Param("buyInterests") Long[] buyInterests,
			@Param("sellInterests") Long[] sellInterests,
			@FormField(name = "registerError", group = "register") CustomErrors err,
			@FormField(name = "loginId", group = "register") CustomErrors loginField,
			@FormField(name = "email", group = "register") CustomErrors emailField,
			Navigator nav, ParameterParser params) {
		User checkUserByLoginId = userService.getUserByLoginId(user
				.getLoginId());
		User checkUserByEmail = userService.getUserByEmail(user.getEmail());
		if (checkUserByLoginId != null) {
			loginField.setMessage("existError");
		}
		if (checkUserByEmail != null) {
			emailField.setMessage("existError");
		}
		if (checkUserByLoginId != null || checkUserByEmail != null) {
			return;
		}

		// 设置兴趣点
		List<UserInterest> buyInsterestList = new ArrayList<UserInterest>();
		List<UserInterest> sellInsterestList = new ArrayList<UserInterest>();
		if (buyInterests != null) {
			for (Long catorgyId : buyInterests) {
				UserInterest buyInterest = new UserInterest();
				buyInterest.setInterest(UserInterestType.BUY);
				buyInterest.setValue(catorgyId);
				buyInterest.setLoginId(user.getLoginId());
				buyInsterestList.add(buyInterest);
			}
		}
		if (sellInterests != null) {
			for (Long catorgyId : sellInterests) {
				UserInterest buyInterest = new UserInterest();
				buyInterest.setInterest(UserInterestType.SALE);
				buyInterest.setValue(catorgyId);
				buyInterest.setLoginId(user.getLoginId());
				sellInsterestList.add(buyInterest);
			}
		}

		user.setBuyInterests(buyInsterestList);
		user.setSaleInterests(sellInsterestList);
		// 设置用户状态、级别、类型
		user.setStatus(UserStatus.NEW);// 新注册
		user.setUserLevel(UserLevel.COMMON);// 普通会员
		user.setRole(UserRole.OUTER_USER);// 用户类型
		boolean result = userService.addUser(user);
		if (result) {
			setSession(user);
			redirectToReturnPage(nav, params);
		} else {
			err.setMessage("registerFail");
		}

	}

	public void doResetUserPass(
			@FormGroup("userPasswordForget") User user,
			@FormField(name = "resetUserInfo", group = "userPasswordForget") CustomErrors info,
			@FormField(name = "resetUserErr", group = "userPasswordForget") CustomErrors err,
			Navigator nav, ParameterParser params) {
		// 重设密码逻辑
		user = userService.getUserByEmail(user.getEmail());
		if (user == null) {// 不存在会员
			err.setMessage("resetFailUserNotExist");
		} else {
			String newPass = userService.resetUserPass(user);
			if (!StringUtil.isBlank(newPass)// 发送邮件失败
					&& newPass.equalsIgnoreCase(EmailSender.EMAIL_SEND_ERR)) {
				err.setMessage("resetFailEmailError");
			} else if (!StringUtil.isBlank(newPass)) {// 成功
				Map<String, String> param = new HashMap<String, String>();
				param.put("email", user.getEmail());
				info.setMessage("resetSuccess", param);
			} else {// 其它异常
				err.setMessage("resetFail");
			}
		}
	}

	public void doEditProfile(
			@Param("password") String password,
			@FormGroup("profile") User user,
			@Param("buyInterests") Long[] buyInterests,
			@Param("sellInterests") Long[] sellInterests,
			@FormField(name = "profileError", group = "profile") CustomErrors err,
			@FormField(name = "id", group = "profile") CustomErrors idField,
			@FormField(name = "password", group = "profile") CustomErrors passwordField,
			@FormField(name = "email", group = "profile") CustomErrors emailField,
			Navigator nav, ParameterParser params, Context context) {
		User checkUserById = userService.getUserById(user.getId());
		if (checkUserById == null) {
			idField.setMessage("existError");
			return;
		}
		if(password==null){
			password="";
		}
		LoginVO vo = new LoginVO();
		vo.setName(checkUserById.getLoginId());
		vo.setPassword(password);
		User checkUserByPass = userService.login(vo);
		if(StringUtils.isNotBlank(user.getPassword())&&checkUserByPass==null){
			passwordField.setMessage("notEqualError");
			return;
		}
		User checkUserByEmail = userService.getUserByEmail(user.getEmail());
		if (checkUserByEmail != null
				&& checkUserByEmail.getLoginId().equals(user.getLoginId())) {
			emailField.setMessage("existError");
			return;
		}

		// 设置兴趣点
		List<UserInterest> buyInsterestList = new ArrayList<UserInterest>();
		List<UserInterest> sellInsterestList = new ArrayList<UserInterest>();
		if (buyInterests != null) {
			for (Long catorgyId : buyInterests) {
				UserInterest buyInterest = new UserInterest();
				buyInterest.setInterest(UserInterestType.BUY);
				buyInterest.setValue(catorgyId);
				buyInterest.setLoginId(checkUserById.getLoginId());
				buyInsterestList.add(buyInterest);
			}
		}
		if (sellInterests != null) {
			for (Long catorgyId : sellInterests) {
				UserInterest buyInterest = new UserInterest();
				buyInterest.setInterest(UserInterestType.SALE);
				buyInterest.setValue(catorgyId);
				buyInterest.setLoginId(checkUserById.getLoginId());
				sellInsterestList.add(buyInterest);
			}
		}

		user.setBuyInterests(buyInsterestList);
		user.setSaleInterests(sellInsterestList);
		// 设置用户状态、级别、类型
		if (hasToReAudit(checkUserById, user)) {
			user.setStatus(UserStatus.NEW);// 新注册
		}
		user.setLoginId(checkUserById.getLoginId());
		user.setRole(checkUserById.getRole());
		boolean result = userService.updateUser(user);
		if (result) {
			setSession(user);
			context.put("result", "success");
		} else {
			err.setMessage("profileFail");
		}

	}

	/**
	 * 判断用户是否需要重新审核
	 * 
	 * @param oldUser
	 * @param newUser
	 * @return
	 */
	private boolean hasToReAudit(User oldUser, User newUser) {
		// 要求邮箱、电话、手机号、公司名称、公司地址、身份证、营业执照变化则需要重新审核
		if (StringUtils.isNotBlank(newUser.getEmail())
				&& oldUser.getEmail().equals(newUser.getEmail()) == false) {
			return true;
		}
		if (StringUtils.isNotBlank(newUser.getPhoneArea())
				&& oldUser.getPhoneArea().equals(newUser.getPhoneArea()) == false) {
			return true;
		}
		if (StringUtils.isNotBlank(newUser.getPhoneNumber())
				&& oldUser.getPhoneNumber().equals(newUser.getPhoneNumber()) == false) {
			return true;
		}
		if (StringUtils.isNotBlank(newUser.getMobilePhone())
				&& oldUser.getMobilePhone().equals(newUser.getMobilePhone()) == false) {
			return true;
		}
		if (StringUtils.isNotBlank(newUser.getCompany())
				&& oldUser.getCompany().equals(newUser.getCompany()) == false) {
			return true;
		}
		if (StringUtils.isNotBlank(newUser.getCompanyAddress())
				&& oldUser.getCompanyAddress().equals(
						newUser.getCompanyAddress()) == false) {
			return true;
		}
		if (StringUtils.isNotBlank(newUser.getIdCardNum())
				&& oldUser.getIdCardNum().equals(newUser.getIdCardNum()) == false) {
			return true;
		}
		if (StringUtils.isNotBlank(newUser.getBusiLicense())
				&& oldUser.getBusiLicense().equals(newUser.getBusiLicense()) == false) {
			return true;
		}
		return false;
	}
}
