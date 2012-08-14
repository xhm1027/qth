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

/**
 * @author ren.zhangr
 *
 */
public class CategoryAction {
	@Autowired
	private ProductCategoryService productCategoryService;


	public void doAddCategory(@FormGroup("category") ProductCategory category,
			@FormField(name = "categoryErr", group = "category") CustomErrors err,
			 Navigator nav) {
		if(!productCategoryService.addCategory(category)){
			err.setMessage("addCategoryErr");
		};
	}
}
