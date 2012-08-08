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


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.form.CustomErrors;
import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormField;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.util.StringUtil;

import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.biz.user.vo.LoginVO;
import com.xhm.longxin.common.daoobject.User;
import com.xhm.longxin.qth.web.user.common.QthUser;
import com.xhm.longxin.qth.web.user.common.UserConstant;

public class UserAction {
	@Autowired
    private UserService userService;
    public void doLogin(@FormGroup("login") LoginVO vo,@FormField(name = "loginError", group = "login") CustomErrors err,HttpSession session, Navigator nav, ParameterParser params) {

    	User user = userService.login(vo);
    	 if (user != null) {
    		 QthUser qthUser = (QthUser) session.getAttribute(UserConstant.QTH_USER_SESSION_KEY);

    		 if (qthUser == null || qthUser.hasLoggedIn()) {
    			 qthUser = new QthUser();
             }
    		 qthUser.upgrade(user.getId(), user.getName(), user.getType());
    		 
             session.setAttribute(UserConstant.QTH_USER_SESSION_KEY, qthUser);

             // 跳转到return页面
             redirectToReturnPage(nav, params);
         } else {
             err.setMessage("invalidUserOrPassword");
         }
        
    }
    
    public void doLogout(HttpSession session, Navigator nav, ParameterParser params) throws Exception {
        // 清除session中的user
        session.removeAttribute(UserConstant.QTH_USER_SESSION_KEY);

        // 跳转到return页面
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
}
