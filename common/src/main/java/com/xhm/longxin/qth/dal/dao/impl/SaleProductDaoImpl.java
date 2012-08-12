/**
 *
 */
package com.xhm.longxin.qth.dal.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.xhm.longxin.qth.dal.dao.SaleProductDao;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;

/**
 * @author ren.zhangr
 *
 */
public class SaleProductDaoImpl  extends SqlMapClientDaoSupport implements SaleProductDao {
	private static final String NAMESPACE_PRODUCT = "QTH_SALE_PRODUCT";
	private static final String INSERT_ID = "INSERT_PRODUCT";
	private static final String UPDATE_ID = "UPDATE_PRODUCT";
	/* (non-Javadoc)
	 * @see com.xhm.longxin.qth.dal.dao.SaleProductDao#addSaleProduct(com.xhm.longxin.qth.dal.dataobject.SaleProduct)
	 */
	public boolean addSaleProduct(SaleProduct product) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_PRODUCT + "." + INSERT_ID, product);
		return res > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.xhm.longxin.qth.dal.dao.SaleProductDao#deleteSaleProductById(java.lang.Long)
	 */
	public boolean deleteSaleProductById(Long id) {
		SaleProduct product = new SaleProduct();
		product.setId(id);
		product.setIsDeleted("y");
		Integer res = (Integer) getSqlMapClientTemplate().delete(
				NAMESPACE_PRODUCT + "." + UPDATE_ID, product);
		return res > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.xhm.longxin.qth.dal.dao.SaleProductDao#updateSaleProduct(com.xhm.longxin.qth.dal.dataobject.SaleProduct)
	 */
	public boolean updateSaleProduct(SaleProduct product) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_PRODUCT + "." + UPDATE_ID, product);
		return res > 0 ? true : false;
	}

}
