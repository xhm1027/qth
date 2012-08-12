/**
 *
 */
package com.xhm.longxin.qth.dal.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.xhm.longxin.qth.dal.dao.ProductCategoryDao;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;

/**
 * @author ren.zhangr
 *
 */
public class ProductCategoryDaoImpl  extends SqlMapClientDaoSupport implements ProductCategoryDao{

	private static final String NAMESPACE_PRODUCT_CATEGORY = "QTH_PRODUCT_CATEGORY";
	private static final String INSERT_ID = "INSERT_PRODUCT_CATEGORY";
	private static final String UPDATE_ID = "UPDATE_PRODUCT_CATEGORY";
	public boolean addProductCategory(ProductCategory cate) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_PRODUCT_CATEGORY + "." + INSERT_ID, cate);
		return res > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.xhm.longxin.qth.dal.dao.ProductCategoryDao#deleteProductCategoryById(java.lang.Long)
	 */
	public boolean deleteProductCategoryById(Long id) {
		ProductCategory cate = new ProductCategory();
		cate.setId(id);
		cate.setIsDeleted("y");
		Integer res = (Integer) getSqlMapClientTemplate().delete(
				NAMESPACE_PRODUCT_CATEGORY + "." + UPDATE_ID, cate);
		return res > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.xhm.longxin.qth.dal.dao.ProductCategoryDao#updateProductCategory(com.xhm.longxin.qth.dal.dataobject.ProductCategory)
	 */
	public boolean updateProductCategory(ProductCategory cate) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_PRODUCT_CATEGORY + "." + UPDATE_ID, cate);
		return res > 0 ? true : false;
	}

}
