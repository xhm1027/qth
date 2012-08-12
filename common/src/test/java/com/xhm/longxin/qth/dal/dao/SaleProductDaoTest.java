/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import junit.framework.Assert;

import org.jtester.annotations.SpringBeanByName;
import org.testng.annotations.Test;
import com.xhm.longxin.qth.dal.constant.ProductStatus;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;

/**
 * @author ren.zhangr
 *
 */
public class SaleProductDaoTest  extends BaseDaoTest {
	@SpringBeanByName
	private SaleProductDao saleProductDao;
	@Test
	public void testAddProduct() {
		SaleProduct product = new SaleProduct();
		product.setOwner("zhangren");
		product.setName("��Ʒ���");
		product.setQuantity(12345L);
		product.setStatus(ProductStatus.NEW);
		// ��
		Assert.assertTrue(saleProductDao.addSaleProduct(product));
	}
}
