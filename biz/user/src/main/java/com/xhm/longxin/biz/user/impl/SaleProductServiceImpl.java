package com.xhm.longxin.biz.user.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.xhm.longxin.biz.user.interfaces.SaleProductService;
import com.xhm.longxin.biz.user.vo.AuditProductVO;
import com.xhm.longxin.qth.dal.constant.AuditResult;
import com.xhm.longxin.qth.dal.constant.AuditType;
import com.xhm.longxin.qth.dal.constant.ProductStatus;
import com.xhm.longxin.qth.dal.dao.AuditLogDao;
import com.xhm.longxin.qth.dal.dao.SaleProductDao;
import com.xhm.longxin.qth.dal.dataobject.AuditLog;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;
import com.xhm.longxin.qth.dal.query.SaleProductQuery;

public class SaleProductServiceImpl implements SaleProductService {
	@Autowired
	private SaleProductDao saleProductDao;
	@Autowired
	private AuditLogDao aditLogDao;

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

	public boolean auditSaleProductById(AuditProductVO auditVO) {
		SaleProduct product = saleProductDao
				.getSaleProductById(auditVO.getAuditId());
		if (AuditResult.PASS.equalsIgnoreCase(auditVO.getAuditResult())) {
			product.setStatus(ProductStatus.ON_SHELF);
		} else {
			product.setStatus(ProductStatus.AUDIT_FAILED);
		}
		saleProductDao.updateSaleProduct(product);
		AuditLog auditLog = new AuditLog();
		auditLog.setAuditId(auditVO.getAuditId());
		if (AuditResult.PASS.equalsIgnoreCase(auditVO.getAuditResult())) {
			auditLog.setAuditResult(AuditResult.PASS);
		} else {
			auditLog.setAuditResult(AuditResult.FAIL);
		}
		auditLog.setDescription(auditVO.getDescription());
		auditLog.setAuditType(AuditType.SALE_PRODUCT);
		auditLog.setAuditor(auditVO.getAuditor());
		aditLogDao.addAuditLog(auditLog);
		return true;
	}
}
