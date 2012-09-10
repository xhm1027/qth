package com.xhm.longxin.biz.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xhm.longxin.biz.user.interfaces.BuyProductService;
import com.xhm.longxin.biz.user.vo.AuditProductVO;
import com.xhm.longxin.qth.dal.constant.AuditResult;
import com.xhm.longxin.qth.dal.constant.AuditType;
import com.xhm.longxin.qth.dal.constant.ProductStatus;
import com.xhm.longxin.qth.dal.constant.UserLevel;
import com.xhm.longxin.qth.dal.constant.UserStatus;
import com.xhm.longxin.qth.dal.dao.AuditLogDao;
import com.xhm.longxin.qth.dal.dao.BuyProductDao;
import com.xhm.longxin.qth.dal.dataobject.AuditLog;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.query.BuyProductQuery;

/**
 *
 * @author xhm.xuhm
 *
 */
public class BuyProductServiceImpl implements BuyProductService {
	@Autowired
	private BuyProductDao buyProductDao;
	@Autowired
	private AuditLogDao aditLogDao;

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

	public boolean deleteBuyProductById(Long id) {
		return buyProductDao.deleteBuyProductById(id);
	}

	public boolean auditBuyProductById(AuditProductVO auditVO) {
		BuyProduct product = buyProductDao.getBuyProductById(auditVO
				.getAuditId());
		if (AuditResult.PASS.equalsIgnoreCase(auditVO.getAuditResult())) {
			product.setStatus(ProductStatus.ON_SHELF);
		} else {
			product.setStatus(ProductStatus.AUDIT_FAILED);
		}
		buyProductDao.updateBuyProduct(product);
		AuditLog auditLog = new AuditLog();
		auditLog.setAuditId(auditVO.getAuditId());
		if (AuditResult.PASS.equalsIgnoreCase(auditVO.getAuditResult())) {
			auditLog.setAuditResult(AuditResult.PASS);
		} else {
			auditLog.setAuditResult(AuditResult.FAIL);
		}
		auditLog.setDescription(auditVO.getDescription());
		auditLog.setAuditType(AuditType.BUY_PRODUCT);
		auditLog.setAuditor(auditVO.getAuditor());
		aditLogDao.addAuditLog(auditLog);
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.biz.user.interfaces.BuyProductService#offShelf(java.lang
	 * .Long)
	 */
	public boolean offShelf(Long id, User user) {
		BuyProduct b = buyProductDao.getBuyProductById(id);
		if (b == null || !user.getLoginId().equals(b.getOwner())) {
			return false;
		}
		b.setStatus(ProductStatus.OFF_SHELF);
		return buyProductDao.updateBuyProduct(b);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.biz.user.interfaces.BuyProductService#onShelf(java.lang
	 * .Long)
	 */
	public boolean onShelf(Long id, User user) {
		BuyProduct b = buyProductDao.getBuyProductById(id);
		if (b == null || !user.getLoginId().equals(b.getOwner())) {
			return false;
		}
		if (UserLevel.GOLDEN.equals(user.getUserLevel())) {
			b.setStatus(ProductStatus.ON_SHELF);
		} else {
			b.setStatus(ProductStatus.NEW);
		}
		return buyProductDao.updateBuyProduct(b);
	}

}
