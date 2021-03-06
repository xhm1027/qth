/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.qth.dal.constant.UserStatus;
import com.xhm.longxin.qth.dal.dataobject.User;

/**
 * @author ren.zhangr
 *
 */
public class UnFreeseUser {
	@Autowired
	private UserService userService;

	public void execute(@Param(name = "id") Long id, Context context) {
		User user=userService.getUserById(id);
		if(user==null||!UserStatus.FREESE.equals(user.getStatus())){
			context.put("result", "fail");
			return;
		}
		if (userService.unFreeseUser(id)) {
			context.put("result", "success");
		} else {
			context.put("result", "fail");
		}
	}
}
