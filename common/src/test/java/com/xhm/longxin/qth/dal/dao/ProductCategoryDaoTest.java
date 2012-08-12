/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import junit.framework.Assert;
import org.testng.annotations.Test;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;

/**
 * @author ren.zhangr
 *
 */
public class ProductCategoryDaoTest extends BaseDaoTest {
	@Test
	public void testAddProductCategory() {
		ProductCategory cate = new ProductCategory();
		cate.setName("ÀàÄ¿1");
		cate.setIsMaterial(IS.Y);
		Assert.assertTrue(productCategoryDao.addProductCategory(cate));
	}
}