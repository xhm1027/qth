/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import junit.framework.Assert;

import org.jtester.annotations.SpringBeanByName;
import org.testng.annotations.Test;
import com.xhm.longxin.qth.dal.constant.ProductStatus;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;

/**
 * @author ren.zhangr
 *
 */
public class BuyProductDaoTest  extends BaseDaoTest {
	@SpringBeanByName
	private BuyProductDao buyProductDao;
	@Test
	public void testAddProduct() {
		db.table("qth_product_buy").clean().commit();
		BuyProduct product = new BuyProduct();
		product.setOwner("zhangren");
		product.setName("单元测试产品名称");
		product.setQuantity(12345L);
		product.setStatus(ProductStatus.NEW);

		Assert.assertTrue(buyProductDao.addBuyProduct(product));
	}
}
