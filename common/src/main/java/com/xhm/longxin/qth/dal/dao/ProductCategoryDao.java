/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;

/**
 * @author ren.zhangr
 *
 */
public interface ProductCategoryDao {

	/**
	 * @param cate
	 * @return
	 */
	boolean addProductCategory(ProductCategory cate);

	/**
	 * 更新记录
	 * */
	public boolean updateProductCategory(ProductCategory cate);

	/**
	 * 删除记录
	 * */
	public boolean deleteProductCategoryById(Long id);

}
