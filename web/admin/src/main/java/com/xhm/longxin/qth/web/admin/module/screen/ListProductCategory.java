/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.screen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.query.CategoryQuery;

/**
 * @author ren.zhangr
 *
 */
public class ListProductCategory {
	@Autowired
	private ProductCategoryService productCategoryService;

	public void execute(@Param(name = "isMaterial") int isMaterial,
			@Param(name = "name") String name, Context context) {

		CategoryQuery categoryQuery = new CategoryQuery();
		if (isMaterial != 0) {
			categoryQuery.setIsMaterial(IS.Y);
		} else {
			categoryQuery.setIsMaterial(IS.N);
		}
		categoryQuery.setName(name);
		List<ProductCategory> categoryList = productCategoryService.queryCategory(categoryQuery);
		context.put("categoryList", categoryList);
		context.put("isMaterial", isMaterial);
		context.put("name", name);
	}
}
