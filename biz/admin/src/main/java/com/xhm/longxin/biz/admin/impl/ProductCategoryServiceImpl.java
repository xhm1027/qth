/**
 *
 */
package com.xhm.longxin.biz.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xhm.longxin.biz.admin.interfaces.ProductCategoryService;
import com.xhm.longxin.qth.dal.dao.AdminUserDao;
import com.xhm.longxin.qth.dal.dao.ProductCategoryDao;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;

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

	public List<ProductCategory> queryCategory(Map param) {
		return productCategoryDao.queryProductCategory(param);
	}

	public boolean delCategoryById(Long id) {
		return productCategoryDao.deleteProductCategoryById(id);
	}
}
