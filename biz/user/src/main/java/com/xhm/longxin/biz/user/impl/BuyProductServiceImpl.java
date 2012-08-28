package com.xhm.longxin.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.xhm.longxin.biz.user.interfaces.BuyProductService;
import com.xhm.longxin.qth.dal.dao.BuyProductDao;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;

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

}
