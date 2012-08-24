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

package com.xhm.longxin.qth.web.user.module.screen;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.web.user.common.QthUser;
import com.xhm.longxin.qth.web.user.common.UserConstant;

public class addBuyProduct {
	@Autowired
	private UserService userService;
	
	public void execute(Navigator nav,HttpSession session, Context context) {
	
		QthUser qthUser = (QthUser) session
				.getAttribute(UserConstant.QTH_USER_SESSION_KEY);
		if(qthUser==null){
			nav.redirectTo(UserConstant.LOGIN_RETURN_DEFAULT_LINK);
			return;
		}
		User user = userService.getUserByLoginId(qthUser.getId());
		if(user==null){
			nav.redirectTo(UserConstant.LOGIN_RETURN_DEFAULT_LINK);
			return;
		}
		
		
		context.put("user", user);
	}
	
	
	
}
