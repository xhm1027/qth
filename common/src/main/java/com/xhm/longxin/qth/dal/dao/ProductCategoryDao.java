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
	 * ���¼�¼
	 * */
	public boolean updateProductCategory(ProductCategory cate);

	/**
	 * ɾ����¼
	 * */
	public boolean deleteProductCategoryById(Long id);

}
