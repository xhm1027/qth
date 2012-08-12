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

package com.xhm.longxin.qth.web.admin.module.action;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.form.CustomErrors;
import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormField;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.util.StringUtil;

import com.xhm.longxin.biz.admin.interfaces.AdminService;
import com.xhm.longxin.biz.admin.vo.LoginVO;
import com.xhm.longxin.qth.dal.dataobject.AdminUser;
import com.xhm.longxin.qth.web.admin.common.AdminConstant;
import com.xhm.longxin.qth.web.admin.common.QthAdmin;
public class AdminAction {
	@Autowired
	private AdminService adminService;

	public void doLogin(@FormGroup("login") LoginVO vo,
			@FormField(name = "validateStr", group = "login") CustomErrors validateField,
			@FormField(name = "loginError", group = "login") CustomErrors err,
			HttpSession session, Navigator nav, ParameterParser params) {
		String validateCode = (String) session
				.getAttribute(AdminConstant.VALIDATE_CODE);
		if (validateCode == null
				|| StringUtils.equalsIgnoreCase(validateCode, vo.getValidateStr()) == false) {
			validateField.setMessage("validateError");
			return;
		}
		
		AdminUser admin = adminService.login(vo);
		if (admin != null) {
			QthAdmin qthAdmin = (QthAdmin) session
					.getAttribute(AdminConstant.QTH_ADMIN_SESSION_KEY);

			if (qthAdmin == null || qthAdmin.hasLoggedIn()) {
				qthAdmin = new QthAdmin();
			}
			qthAdmin.upgrade(admin.getLoginId(), admin.getName());

			session.setAttribute(AdminConstant.QTH_ADMIN_SESSION_KEY, qthAdmin);

			redirectToReturnPage(nav, params);
		} else {
			err.setMessage("invalidUserOrPassword");
		}

	}

	public void doLogout(HttpSession session, Navigator nav,
			ParameterParser params) throws Exception {

		session.removeAttribute(AdminConstant.QTH_ADMIN_SESSION_KEY);

		redirectToReturnPage(nav, params);
	}

	private void redirectToReturnPage(Navigator nav, ParameterParser params) {
		String returnURL = params.getString("return");

		if (StringUtil.isEmpty(returnURL)) {
			nav.redirectTo(AdminConstant.LOGIN_RETURN_DEFAULT_LINK);
		} else {
			nav.redirectToLocation(returnURL);
		}
	}
}
