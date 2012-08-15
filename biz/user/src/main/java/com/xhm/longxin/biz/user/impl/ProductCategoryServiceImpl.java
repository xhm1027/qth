/**
 *
 */
package com.xhm.longxin.biz.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dao.ProductCategoryDao;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.query.CategoryQuery;

/**
 * @author ren.zhangr
 *
 */
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;

	public boolean addCategory(ProductCategory category) {
		return productCategoryDao.addProductCategory(category);
	}

	public boolean updateCategory(ProductCategory category) {
		return productCategoryDao.updateProductCategory(category);
	}

	public List<ProductCategory> queryCategory(CategoryQuery categoryQuery) {
		return productCategoryDao.query(categoryQuery);
	}

	public boolean delCategoryById(Long id) {
		return productCategoryDao.deleteProductCategoryById(id);
	}

	public List<ProductCategory> getAllMaterialCategory() {
		CategoryQuery categoryQuery = new CategoryQuery();
		categoryQuery.setIsMaterial(IS.Y);
		return productCategoryDao.query(categoryQuery);
	}

	public List<ProductCategory> getAllResourceCategory() {
		CategoryQuery categoryQuery = new CategoryQuery();
		categoryQuery.setIsMaterial(IS.N);
		return productCategoryDao.query(categoryQuery);
	}
	
}
