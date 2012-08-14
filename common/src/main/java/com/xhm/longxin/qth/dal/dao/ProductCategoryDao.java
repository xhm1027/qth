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
	 * ���¼�¼
	 * */
	public boolean updateProductCategory(ProductCategory cate);

	/**
	 * ɾ����¼
	 * */
	public boolean deleteProductCategoryById(Long id);

	/**
	 * ��ѯ��¼
	 * */
	public List<ProductCategory> queryProductCategory(Map<String, Object> param);

}
