/**
 *
 */
package com.xhm.longxin.qth.dal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.xhm.longxin.qth.dal.dao.SaleProductDao;
import com.xhm.longxin.qth.dal.dataobject.Attachment;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;
import com.xhm.longxin.qth.dal.query.AttachmentUpdateVo;
import com.xhm.longxin.qth.dal.query.SaleProductQuery;

/**
 * @author ren.zhangr
 *
 */
public class SaleProductDaoImpl extends SqlMapClientDaoSupport implements
		SaleProductDao {
	private static final String NAMESPACE_PRODUCT = "QTH_SALE_PRODUCT";
	private static final String INSERT_ID = "INSERT_PRODUCT";
	private static final String UPDATE_ID = "UPDATE_PRODUCT";

	private static final String QUERY_ID = "QUERY_PRODUCT";
	private static final String QUERY_COUNT = "QUERY_COUNT";

	private static final String NAMESPACE_ATTACHMENT = "QTH_ATTACHMENT";
	private static final String INSERT_ATTACHMENT = "INSERT_ATTACHMENT";
	private static final String UPDATE_ATTACHMENT = "UPDATE_ATTACHMENT";
	private static final String DELETE_ATTACHEMENT_NOT_IN_IDS = "DELETE_ATTACHEMENT_NOT_IN_IDS";

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.SaleProductDao#addSaleProduct(com.xhm.longxin
	 * .qth.dal.dataobject.SaleProduct)
	 */
	public boolean addSaleProduct(SaleProduct product) {
		Long res = (Long) getSqlMapClientTemplate().insert(
				NAMESPACE_PRODUCT + "." + INSERT_ID, product);
		if (product.getImgs() != null) {
			for (Attachment att : product.getImgs()) {
				att.setOwnerId(res);
				getSqlMapClientTemplate().update(
						NAMESPACE_ATTACHMENT + "." + INSERT_ATTACHMENT, att);
			}
		}
		return res > 0 ? true : false;
	}


	public boolean deleteSaleProductById(Long id) {
		SaleProduct product = getSaleProductById(id);
		// 删除之前先删除附件
		if (product.getImgs() != null && product.getImgs().size() != 0) {
			for (Attachment att : product.getImgs()) {
				deleteAttachmentById(att.getId());
			}
		}
		product.setId(id);
		product.setIsDeleted("Y");
		Integer res = (Integer) getSqlMapClientTemplate().delete(
				NAMESPACE_PRODUCT + "." + UPDATE_ID, product);
		return res > 0 ? true : false;
	}


	public boolean updateSaleProduct(SaleProduct product) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_PRODUCT + "." + UPDATE_ID, product);
		List<Long> imgIds = new ArrayList<Long>();
		if (product.getImgs() != null && product.getImgs().size() != 0) {
			for (Attachment att : product.getImgs()) {
				if (att.getId() != null) {
					imgIds.add(att.getId());
				}
			}
		}
		// 删除该产品的这在imgIds中的附件
		AttachmentUpdateVo attachementUpdateVo = new AttachmentUpdateVo();
		attachementUpdateVo.setImgIds(imgIds);
		attachementUpdateVo.setKey("sale");// 表示产品id是product_sale表中的
		attachementUpdateVo.setOwnerId(product.getId());// 产品ID很重要，否则会全表删除
		getSqlMapClientTemplate().update(
				NAMESPACE_ATTACHMENT + "." + DELETE_ATTACHEMENT_NOT_IN_IDS,
				attachementUpdateVo);
		// 遍历附件，如果图片没有id则说明是新加的，有id则说明是修改的
		if (product.getImgs() != null && product.getImgs().size() != 0) {
			for (Attachment att : product.getImgs()) {
				if (att.getId() == null) {// 没有id，插入
					getSqlMapClientTemplate()
							.update(
									NAMESPACE_ATTACHMENT + "."
											+ INSERT_ATTACHMENT, att);
				} else {
					getSqlMapClientTemplate()
							.update(
									NAMESPACE_ATTACHMENT + "."
											+ UPDATE_ATTACHMENT, att);
				}
			}
		}
		return res > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.SaleProductDao#query(com.xhm.longxin.qth.
	 * dal.query.SaleProductQuery)
	 */
	public List<SaleProduct> query(SaleProductQuery saleProductQuery) {
		List<SaleProduct> list = (List<SaleProduct>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_PRODUCT + "." + QUERY_ID,
						saleProductQuery);
		return list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.SaleProductDao#query(com.xhm.longxin.qth.
	 * dal.query.SaleProductQuery, int, int)
	 */
	public List<SaleProduct> query(SaleProductQuery saleProductQuery,
			int pageStart, int pageSize) {
		saleProductQuery.setPageStart(pageStart);
		saleProductQuery.setPageSize(pageSize);
		List<SaleProduct> list = (List<SaleProduct>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_PRODUCT + "." + QUERY_ID,
						saleProductQuery);
		return list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.SaleProductDao#queryCount(com.xhm.longxin
	 * .qth.dal.query.SaleProductQuery)
	 */
	public int queryCount(SaleProductQuery saleProductQuery) {
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				NAMESPACE_PRODUCT + "." + QUERY_COUNT, saleProductQuery);
		return count;
	}


	/* (non-Javadoc)
	 * @see com.xhm.longxin.qth.dal.dao.SaleProductDao#getSaleProductById(java.lang.Long)
	 */
	public SaleProduct getSaleProductById(Long id) {

		SaleProductQuery saleProductQuery = new SaleProductQuery();
		saleProductQuery.setId(id);
			List<SaleProduct> list = (List<SaleProduct>) getSqlMapClientTemplate()
					.queryForList(NAMESPACE_PRODUCT + "." + QUERY_ID,
							saleProductQuery);
			if (list == null || list.size() == 0) {
				return null;
			}
			return list.get(0);

	}
	private boolean deleteAttachmentById(Long id) {
		Attachment attachment = new Attachment();
		attachment.setId(id);
		attachment.setIsDeleted("Y");
		Integer res = (Integer) getSqlMapClientTemplate().delete(
				NAMESPACE_ATTACHMENT + "." + UPDATE_ATTACHMENT, attachment);
		return res > 0 ? true : false;
	}

}
