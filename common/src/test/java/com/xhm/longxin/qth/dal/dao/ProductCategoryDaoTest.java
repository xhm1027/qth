/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import junit.framework.Assert;

import org.jtester.annotations.SpringBeanByName;
import org.testng.annotations.Test;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;

/**
 * @author ren.zhangr
 *
 */
public class ProductCategoryDaoTest extends BaseDaoTest {
	@SpringBeanByName
	private ProductCategoryDao productCategoryDao;
	
	@Test
	public void testAddProductCategory() {
		ProductCategory cate = new ProductCategory();
		cate.setName("��Ŀ1");
		cate.setIsMaterial(IS.Y);
		Assert.assertTrue(productCategoryDao.addProductCategory(cate));
	}
}