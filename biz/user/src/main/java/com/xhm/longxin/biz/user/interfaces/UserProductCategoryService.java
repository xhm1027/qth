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
	 * ��ȡ����ԭ���ϵ����
	 * @return
	 */
	public List<ProductCategory> getAllMaterialCategory();
	
	/**
	 * ��ȡ������Դ�����
	 * @return
	 */
	public List<ProductCategory> getAllResourceCategory();
}
