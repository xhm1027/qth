/**
 *
 */
package com.xhm.longxin.biz.user.interfaces;

import java.util.List;

import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.query.CategoryQuery;

/**
 * @author ren.zhangr
 *
 */
public interface ProductCategoryService {

	public boolean addCategory(ProductCategory category);

	public boolean updateCategory(ProductCategory category);

	public List<ProductCategory> queryCategory(CategoryQuery categoryQuery);

	public boolean delCategoryById(Long id);

	/**
	 * 获取所有原材料的类别
	 * @return
	 */
	public List<ProductCategory> getAllMaterialCategory();
	
	/**
	 * 获取所有资源的类别
	 * @return
	 */
	public List<ProductCategory> getAllResourceCategory();
}
