/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import java.util.List;
import java.util.Map;

import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.query.CategoryQuery;

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
	public List<ProductCategory> query(CategoryQuery categoryQuery);
	/**
	 * ��ѯ��¼
	 * */
	public List<ProductCategory> query(CategoryQuery categoryQuery,int pageStart,int pageSize);
	/**
	 * ��ѯ����
	 * */
	public int queryCount(CategoryQuery categoryQuery);

}
