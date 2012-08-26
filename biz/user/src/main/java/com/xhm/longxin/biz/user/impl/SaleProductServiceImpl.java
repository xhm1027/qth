package com.xhm.longxin.biz.user.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.xhm.longxin.biz.user.interfaces.SaleProductService;
import com.xhm.longxin.qth.dal.dao.SaleProductDao;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;
import com.xhm.longxin.qth.dal.query.SaleProductQuery;

public class SaleProductServiceImpl implements SaleProductService {
	@Autowired
	private SaleProductDao saleProductDao;

	public boolean addSaleProduct(SaleProduct product) {
		return saleProductDao.addSaleProduct(product);
	}

	public boolean deleteSaleProductById(Long id) {
		return saleProductDao.deleteSaleProductById(id);
	}

	public SaleProduct getSaleProductById(Long id) {
		return saleProductDao.getSaleProductById(id);
	}

	public List<SaleProduct> query(SaleProductQuery saleProductQuery) {
		return saleProductDao.query(saleProductQuery);
	}

	public List<SaleProduct> query(SaleProductQuery saleProductQuery,
			int pageStart, int pageSize) {
		return saleProductDao.query(saleProductQuery, pageStart, pageSize);
	}

	public int queryCount(SaleProductQuery saleProductQuery) {
		return saleProductDao.queryCount(saleProductQuery);
	}

	public boolean updateSaleProduct(SaleProduct product) {
		return saleProductDao.updateSaleProduct(product);
	}
}
