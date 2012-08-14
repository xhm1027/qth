/**
 *
 */
package com.xhm.longxin.biz.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xhm.longxin.biz.user.interfaces.UserProductCategoryService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dao.ProductCategoryDao;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;

/**
 * @author ren.zhangr
 *
 */
public class UserProductCategoryServiceImpl implements UserProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;

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
