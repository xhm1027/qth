/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.screen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;

/**
 * @author ren.zhangr
 *
 */
public class ListProductCategory {
	@Autowired
	private ProductCategoryService productCategoryService;

	public void execute(@Param(name = "isMaterial") int isMaterial,
			@Param(name = "name") String name, Context context) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (isMaterial != 0) {
			param.put("isMaterial", IS.Y);
		} else {
			param.put("isMaterial", IS.N);
		}
		param.put("name", name);
		List<ProductCategory> categoryList = productCategoryService
				.queryCategory(param);
		context.put("categoryList", categoryList);
		context.put("isMaterial", isMaterial);
		context.put("name", name);
	}
}
