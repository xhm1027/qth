/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.UserService;

/**
 * @author ren.zhangr
 *
 */
public class FreeseUser {
	@Autowired
	private UserService userService;

	public void execute(@Param(name = "id") Long id, Context context) {
		if (userService.freeseUser(id)) {
			context.put("result", "success");
		}else{
			context.put("result", "fail");
		}
	}
}
