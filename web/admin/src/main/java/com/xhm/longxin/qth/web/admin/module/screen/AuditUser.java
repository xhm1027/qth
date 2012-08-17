/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.qth.dal.dataobject.User;

/**
 * @author ren.zhangr
 *
 */
public class AuditUser {
	@Autowired
	private UserService userService;
	public void execute(
			@Param(name = "id") Long id,
			@Param(name = "name") String name, Context context) {
		User user=userService.getUserById(id);
		context.put("user", user);
	}
}
