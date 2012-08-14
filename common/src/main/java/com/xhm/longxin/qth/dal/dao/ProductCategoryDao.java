/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import java.util.List;
import java.util.Map;

import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.dataobject.User;

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

	/**
	 * 查询记录
	 * */
	public List<ProductCategory> queryProductCategory(Map<String, Object> param);

}
