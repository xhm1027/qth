/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.screen;

import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;

/**
 * @author ren.zhangr
 *
 */
public class EditCategory {
	@Autowired
	private ProductCategoryService productCategoryService;

	public void execute(@Param(name = "id") Long id, Context context) {
		context.put("category", productCategoryService.getCategoryById(id));
	}
}
