/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import junit.framework.Assert;
import org.testng.annotations.Test;
import com.xhm.longxin.qth.dal.constant.ProductStatus;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;

/**
 * @author ren.zhangr
 *
 */
public class SaleProductDaoTest  extends BaseDaoTest {
	@Test
	public void testAddProduct() {
		SaleProduct product = new SaleProduct();
		product.setOwner("zhangren");
		product.setName("产品名称");
		product.setQuantity(12345L);
		product.setStatus(ProductStatus.NEW);
		// 增
		Assert.assertTrue(saleProductDao.addSaleProduct(product));
	}
}
