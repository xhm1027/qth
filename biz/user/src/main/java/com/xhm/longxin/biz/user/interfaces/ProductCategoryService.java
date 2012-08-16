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

	public List<ProductCategory> query(CategoryQuery categoryQuery);
	public List<ProductCategory> query(CategoryQuery categoryQuery,int pageStart,int pageSize);
	public int queryCount(CategoryQuery categoryQuery);

	public boolean delCategoryById(Long id);
	public ProductCategory getCategoryById(Long id);
	/**
	 * ��ȡ����ԭ���ϵ����
	 * @return
	 */
	public List<ProductCategory> getAllMaterialCategory();

	/**
	 * ��ȡ������Դ�����
	 * @return
	 */
	public List<ProductCategory> getAllResourceCategory();
	public boolean isCategoryExist(CategoryQuery categoryQuery);
}
