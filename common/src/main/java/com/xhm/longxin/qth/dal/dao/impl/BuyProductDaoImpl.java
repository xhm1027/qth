/**
 *
 */
package com.xhm.longxin.qth.dal.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.xhm.longxin.qth.dal.dao.BuyProductDao;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;

/**
 * @author ren.zhangr
 *
 */
public class BuyProductDaoImpl extends SqlMapClientDaoSupport implements
		BuyProductDao {
	private static final String NAMESPACE_PRODUCT = "QTH_BUY_PRODUCT";
	private static final String INSERT_ID = "INSERT_PRODUCT";
	private static final String UPDATE_ID = "UPDATE_PRODUCT";

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.BuyProductDao#addBuyProduct(com.xhm.longxin
	 * .qth.dal.dataobject.BuyProduct)
	 */
	public boolean addBuyProduct(BuyProduct product) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_PRODUCT + "." + INSERT_ID, product);
		return res > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.BuyProductDao#deleteBuyProductById(java.lang
	 * .Long)
	 */
	public boolean deleteBuyProductById(Long id) {
		BuyProduct product = new BuyProduct();
		product.setId(id);
		product.setIsDeleted("y");
		Integer res = (Integer) getSqlMapClientTemplate().delete(
				NAMESPACE_PRODUCT + "." + UPDATE_ID, product);
		return res > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.BuyProductDao#updateBuyProduct(com.xhm.longxin
	 * .qth.dal.dataobject.BuyProduct)
	 */
	public boolean updateBuyProduct(BuyProduct product) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_PRODUCT + "." + UPDATE_ID, product);
		return res > 0 ? true : false;
	}

}
