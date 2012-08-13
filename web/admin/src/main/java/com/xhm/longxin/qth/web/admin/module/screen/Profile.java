package com.xhm.longxin.qth.web.admin.module.screen;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.xhm.longxin.biz.admin.interfaces.AdminService;
import com.xhm.longxin.qth.dal.dataobject.AdminUser;
import com.xhm.longxin.qth.web.admin.common.AdminConstant;
import com.xhm.longxin.qth.web.admin.common.QthAdmin;

public class Profile {
	@Autowired
	private AdminService adminService;
	public void execute(Navigator nav,HttpSession session,Context context) {
		QthAdmin qthAdmin = (QthAdmin) session
				.getAttribute(AdminConstant.QTH_ADMIN_SESSION_KEY);
		if(qthAdmin==null){
			nav.redirectTo(AdminConstant.LOGIN_RETURN_DEFAULT_LINK);
			return;
		}
		AdminUser user = adminService.getAdminUserByLoginId(qthAdmin.getId());
		if(user==null){
			nav.redirectTo(AdminConstant.LOGIN_RETURN_DEFAULT_LINK);
			return;
		}
		context.put("user", user);
	}
	
	
}
