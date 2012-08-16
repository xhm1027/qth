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
import com.xhm.longxin.qth.dal.query.QueryObject;

/**
 * @author ren.zhangr
 *
 */
public class ListProductCategory {
	@Autowired
	private ProductCategoryService productCategoryService;

	public void execute(@Param(name = "isMaterial") String isMaterial,
			@Param(name = "page") int page,
			@Param(name = "pageSize") int pageSize,
			@Param(name = "name") String name, Context context) {

		CategoryQuery categoryQuery = new CategoryQuery();
		if (page == 0) {
			page = 1;
		}
		if (pageSize == 0 || pageSize > QueryObject.maxPageSize) {
			pageSize = QueryObject.defaultPageSize;
		}
		if (isMaterial == null || !isMaterial.equalsIgnoreCase(IS.N)) {
			categoryQuery.setIsMaterial(IS.Y);
		} else {
			categoryQuery.setIsMaterial(IS.N);
		}
		categoryQuery.setName(name);
		context.put("categoryList", productCategoryService.query(categoryQuery,
				(page - 1) * 20, pageSize));
		context.put("isMaterial", isMaterial);
		context.put("name", name);
		context.put("page", page);
		context.put("pageSize", pageSize);
		int totalCount = productCategoryService.queryCount(categoryQuery);
		context.put("totalCount", totalCount);
		context.put("totalPage", totalCount / pageSize + 1);
	}
}
