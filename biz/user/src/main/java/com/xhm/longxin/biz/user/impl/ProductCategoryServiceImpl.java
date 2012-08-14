/**
 *
 */
package com.xhm.longxin.biz.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.qth.dal.constant.IS;
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

	public List<ProductCategory> getAllMaterialCategory() {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("isMaterial", IS.Y);
		return productCategoryDao.queryProductCategory(param);
	}

	public List<ProductCategory> getAllResourceCategory() {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("isMaterial", IS.N);
		return productCategoryDao.queryProductCategory(param);
	}
	
}
