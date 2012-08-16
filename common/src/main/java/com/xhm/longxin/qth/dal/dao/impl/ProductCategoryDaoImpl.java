/**
 *
 */
package com.xhm.longxin.qth.dal.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.xhm.longxin.qth.dal.dao.ProductCategoryDao;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.query.CategoryQuery;

/**
 * @author ren.zhangr
 *
 */
public class ProductCategoryDaoImpl extends SqlMapClientDaoSupport implements
		ProductCategoryDao {

	private static final String NAMESPACE_PRODUCT_CATEGORY = "QTH_PRODUCT_CATEGORY";
	private static final String INSERT_ID = "INSERT_PRODUCT_CATEGORY";
	private static final String UPDATE_ID = "UPDATE_PRODUCT_CATEGORY";
	private static final String QUERY_ID_BYVO = "QUERY_CATEGORY_BYVO";
	private static final String QUERY_CATEGORY_EXIST = "QUERY_CATEGORY_EXIST";
	private static final String QUERY_COUNT = "QUERY_CATEGORY_COUNT";

	public boolean addProductCategory(ProductCategory cate) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_PRODUCT_CATEGORY + "." + INSERT_ID, cate);
		return res > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.ProductCategoryDao#deleteProductCategoryById
	 * (java.lang.Long)
	 */
	public boolean deleteProductCategoryById(Long id) {
		ProductCategory cate = new ProductCategory();
		cate.setId(id);
		cate.setIsDeleted("Y");
		Integer res = (Integer) getSqlMapClientTemplate().delete(
				NAMESPACE_PRODUCT_CATEGORY + "." + UPDATE_ID, cate);
		return res > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.ProductCategoryDao#updateProductCategory(
	 * com.xhm.longxin.qth.dal.dataobject.ProductCategory)
	 */
	public boolean updateProductCategory(ProductCategory cate) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_PRODUCT_CATEGORY + "." + UPDATE_ID, cate);
		return res > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.ProductCategoryDao#queryProductCategory(java
	 * .util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<ProductCategory> query(CategoryQuery categoryQuery) {
		List<ProductCategory> cateList = (List<ProductCategory>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_PRODUCT_CATEGORY + "." + QUERY_ID_BYVO,
						categoryQuery);
		return cateList;
	}

	@SuppressWarnings("unchecked")
	public List<ProductCategory> query(CategoryQuery categoryQuery,
			int pageStart, int pageSize) {
		categoryQuery.setPageSize(pageSize);
		categoryQuery.setPageStart(pageStart);
		List<ProductCategory> cateList = (List<ProductCategory>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_PRODUCT_CATEGORY + "." + QUERY_ID_BYVO,
						categoryQuery);
		return cateList;
	}

	@SuppressWarnings("unchecked")
	public int queryCount(CategoryQuery categoryQuery) {
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				NAMESPACE_PRODUCT_CATEGORY + "." + QUERY_COUNT, categoryQuery);
		return count;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.ProductCategoryDao#getCategoryById(java.lang
	 * .Long)
	 */
	public ProductCategory getCategoryById(Long id) {
		CategoryQuery categoryQuery = new CategoryQuery();
		categoryQuery.setId(id);
		List<ProductCategory> cateList = (List<ProductCategory>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_PRODUCT_CATEGORY + "." + QUERY_ID_BYVO,
						categoryQuery);
		if (cateList == null || cateList.isEmpty()) {
			return null;
		} else {
			return cateList.get(0);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.ProductCategoryDao#isCategoryExist(com.xhm
	 * .longxin.qth.dal.query.CategoryQuery)
	 */
	public boolean isCategoryExist(CategoryQuery categoryQuery) {
		List<ProductCategory> cateList = (List<ProductCategory>) getSqlMapClientTemplate()
				.queryForList(
						NAMESPACE_PRODUCT_CATEGORY + "." + QUERY_CATEGORY_EXIST,
						categoryQuery);
		if (cateList != null && !cateList.isEmpty()) {
			return true;
		}
		return false;
	}

}
