/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.query.CategoryQuery;
import com.xhm.longxin.qth.dal.query.QueryObject;
import com.xhm.longxin.qth.dal.query.UserQuery;

/**
 * @author ren.zhangr
 *
 */
public class ListUser {
	@Autowired
	private UserService userService;

	public void execute(@Param(name = "company") String company,
			@Param(name = "page") int page,
			@Param(name = "pageSize") int pageSize,
			@Param(name = "userLevel") String userLevel,
			@Param(name = "status") String status,
			@Param(name = "name") String name, Context context) {
		UserQuery userQuery = new UserQuery();
		if (page == 0) {
			page = 1;
		}
		if (pageSize == 0 || pageSize > QueryObject.maxPageSize) {
			pageSize = QueryObject.defaultPageSize;
		}
		userQuery.setStatus(status);
		userQuery.setUserLevel(userLevel);
		userQuery.setCompany(company);
		userQuery.setName(name);
		context.put("userList", userService.queryUser(userQuery,
				(page - 1) * 20, pageSize));
		context.put("company", company);
		context.put("name", name);
		context.put("status", status);
		context.put("userLevel", userLevel);
		context.put("page", page);
		context.put("pageSize", pageSize);
		int totalCount = userService.queryCount(userQuery);
		context.put("totalCount", totalCount);
		context.put("totalPage", totalCount / pageSize + 1);
	}
}
