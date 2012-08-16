/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.action;

import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.citrus.service.form.CustomErrors;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormField;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.query.CategoryQuery;

/**
 * @author ren.zhangr
 *
 */
public class CategoryAction {
	@Autowired
	private ProductCategoryService productCategoryService;

	public void doAdd(
			@FormGroup("category") ProductCategory category,
			@FormField(name = "categoryErr", group = "category") CustomErrors err,
			@FormField(name = "saveCategoryInfo", group = "category") CustomErrors info,
			Navigator nav) {
		CategoryQuery categoryQuery=new CategoryQuery();
		categoryQuery.setName(category.getName());
		categoryQuery.setIsMaterial(category.getIsMaterial());
		if(productCategoryService.isCategoryExist(categoryQuery)){
			err.setMessage("categoryExist");
			return;
		}
		if (!productCategoryService.addCategory(category)) {
			err.setMessage("saveCategoryErr");
		}else{
			info.setMessage("addSuccess");
		}
	}

	public void doEdit(
			@FormGroup("category") ProductCategory category,
			@FormField(name = "categoryErr", group = "category") CustomErrors err,
			@FormField(name = "saveCategoryInfo", group = "category") CustomErrors info,
			Navigator nav) {
		CategoryQuery categoryQuery=new CategoryQuery();
		categoryQuery.setName(category.getName());
		categoryQuery.setIsMaterial(category.getIsMaterial());
		categoryQuery.setId(category.getId());
		if(productCategoryService.isCategoryExist(categoryQuery)){
			err.setMessage("categoryExist");
			return;
		}
		if (!productCategoryService.updateCategory(category)) {
			err.setMessage("saveCategoryErr");
		}else{
			info.setMessage("editSuccess");
		}
	}
}
