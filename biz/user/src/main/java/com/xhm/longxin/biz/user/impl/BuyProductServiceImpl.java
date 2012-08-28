package com.xhm.longxin.biz.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xhm.longxin.biz.user.interfaces.BuyProductService;
import com.xhm.longxin.qth.dal.dao.BuyProductDao;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.dal.query.BuyProductQuery;

/**
 *
 * @author xhm.xuhm
 *
 */
public class BuyProductServiceImpl implements BuyProductService {
	@Autowired
	private BuyProductDao buyProductDao;
	public boolean addBuyProduct(BuyProduct buyProduct) {
		return buyProductDao.addBuyProduct(buyProduct);
	}
	public BuyProduct getBuyProductById(Long id) {
		return buyProductDao.getBuyProductById(id);
	}
	public boolean updateBuyProduct(BuyProduct product) {
		return buyProductDao.updateBuyProduct(product);
	}
	public List<BuyProduct> query(BuyProductQuery buyProductQuery) {
		return buyProductDao.query(buyProductQuery);
	}
	public List<BuyProduct> query(BuyProductQuery buyProductQuery,
			int pageStart, int pageSize) {
		return buyProductDao.query(buyProductQuery, pageStart, pageSize);
	}
	public int queryCount(BuyProductQuery buyProductQuery) {
		return buyProductDao.queryCount(buyProductQuery);
	}

}
