/**
 *
 */
package com.xhm.longxin.biz.admin.interfaces;

import java.util.List;
import java.util.Map;

import com.xhm.longxin.qth.dal.dataobject.ProductCategory;

/**
 * @author ren.zhangr
 *
 */
public interface ProductCategoryService {

	public boolean addCategory(ProductCategory category);

	public boolean updateCategory(ProductCategory category);

	public List<ProductCategory> queryCategory(Map param);

	public boolean delCategoryById(Long id);
}
