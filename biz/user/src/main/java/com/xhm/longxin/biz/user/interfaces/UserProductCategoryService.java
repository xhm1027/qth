/**
 *
 */
package com.xhm.longxin.biz.user.interfaces;

import java.util.List;

import com.xhm.longxin.qth.dal.dataobject.ProductCategory;

/**
 * @author ren.zhangr
 *
 */
public interface UserProductCategoryService {

	/**
	 * 获取所有原材料的类别
	 * @return
	 */
	public List<ProductCategory> getAllMaterialCategory();
	
	/**
	 * 获取所有资源的类别
	 * @return
	 */
	public List<ProductCategory> getAllResourceCategory();
}
