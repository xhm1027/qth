/**
 *
 */
package com.xhm.longxin.qth.dal.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.citrus.util.StringUtil;
import com.xhm.longxin.qth.dal.dao.AttachmentDao;
import com.xhm.longxin.qth.dal.dao.BuyProductDao;
import com.xhm.longxin.qth.dal.dataobject.Attachment;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.dal.query.AttachmentUpdateVo;
import com.xhm.longxin.qth.dal.query.BuyProductQuery;

/**
 * @author ren.zhangr
 *
 */
public class BuyProductDaoImpl extends SqlMapClientDaoSupport implements
		BuyProductDao {
	private static final String NAMESPACE_PRODUCT = "QTH_BUY_PRODUCT";
	private static final String INSERT_ID = "INSERT_PRODUCT";
	private static final String UPDATE_ID = "UPDATE_PRODUCT";

	private static final String QUERY_ID = "QUERY_PRODUCT";
	private static final String QUERY_COUNT = "QUERY_COUNT";

	private static final String QUERY_ID_WITH_COMPANY = "QUERY_PRODUCT_WITH_COMPANY";
	private static final String QUERY_COUNT_WITH_COMPANY = "QUERY_COUNT_WITH_COMPANY";

	private static final String NAMESPACE_ATTACHMENT = "QTH_ATTACHMENT";
	private static final String INSERT_ATTACHMENT = "INSERT_ATTACHMENT";
	private static final String UPDATE_ATTACHMENT = "UPDATE_ATTACHMENT";
	private static final String DELETE_ATTACHEMENT_NOT_IN_IDS = "DELETE_ATTACHEMENT_NOT_IN_IDS";

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.BuyProductDao#addBuyProduct(com.xhm.longxin
	 * .qth.dal.dataobject.BuyProduct)
	 */
	public boolean addBuyProduct(BuyProduct product) {
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

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.BuyProductDao#deleteBuyProductById(java.lang
	 * .Long)
	 */
	public boolean deleteBuyProductById(Long id) {
		BuyProduct product = getBuyProductById(id);
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

	public boolean updateBuyProduct(BuyProduct product) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_PRODUCT + "." + UPDATE_ID, product);
		List<Long> imgIds = null;
		if (product.getImgs() != null && product.getImgs().size() != 0) {
			imgIds = new ArrayList<Long>();
			for (Attachment att : product.getImgs()) {
				if (att.getId() != null) {
					imgIds.add(att.getId());
				}
			}
		}
		// 删除该产品的不在imgIds中的附件
		AttachmentUpdateVo attachementUpdateVo = new AttachmentUpdateVo();
		attachementUpdateVo.setImgIds(imgIds);
		attachementUpdateVo.setKey("buy");// 表示产品id是product_buy表中的
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
	 * com.xhm.longxin.qth.dal.dao.BuyProductDao#query(com.xhm.longxin.qth.dal
	 * .query.BuyProductQuery)
	 */
	public List<BuyProduct> query(BuyProductQuery buyProductQuery) {
		if (StringUtil.isEmpty(buyProductQuery.getCompany())) {
			List<BuyProduct> list = (List<BuyProduct>) getSqlMapClientTemplate()
					.queryForList(NAMESPACE_PRODUCT + "." + QUERY_ID,
							buyProductQuery);
			return list;
		} else {
			List<BuyProduct> list = (List<BuyProduct>) getSqlMapClientTemplate()
					.queryForList(
							NAMESPACE_PRODUCT + "." + QUERY_ID_WITH_COMPANY,
							buyProductQuery);
			return list;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.BuyProductDao#query(com.xhm.longxin.qth.dal
	 * .query.BuyProductQuery, int, int)
	 */
	public List<BuyProduct> query(BuyProductQuery buyProductQuery,
			int pageStart, int pageSize) {
		buyProductQuery.setPageStart(pageStart);
		buyProductQuery.setPageSize(pageSize);
		if (StringUtil.isEmpty(buyProductQuery.getCompany())) {
			List<BuyProduct> list = (List<BuyProduct>) getSqlMapClientTemplate()
					.queryForList(NAMESPACE_PRODUCT + "." + QUERY_ID,
							buyProductQuery);
			return list;
		} else {
			List<BuyProduct> list = (List<BuyProduct>) getSqlMapClientTemplate()
					.queryForList(
							NAMESPACE_PRODUCT + "." + QUERY_ID_WITH_COMPANY,
							buyProductQuery);
			return list;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.BuyProductDao#queryCount(com.xhm.longxin.
	 * qth.dal.query.BuyProductQuery)
	 */
	public int queryCount(BuyProductQuery buyProductQuery) {
		if (StringUtil.isEmpty(buyProductQuery.getCompany())) {
			int count = (Integer) getSqlMapClientTemplate().queryForObject(
					NAMESPACE_PRODUCT + "." + QUERY_COUNT, buyProductQuery);
			return count;
		} else {
			int count = (Integer) getSqlMapClientTemplate().queryForObject(
					NAMESPACE_PRODUCT + "." + QUERY_COUNT_WITH_COMPANY,
					buyProductQuery);
			return count;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.BuyProductDao#getBuyProductById(java.lang
	 * .Long)
	 */
	public BuyProduct getBuyProductById(Long id) {
		BuyProductQuery buyProductQuery = new BuyProductQuery();
		buyProductQuery.setId(id);
		List<BuyProduct> list = (List<BuyProduct>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_PRODUCT + "." + QUERY_ID,
						buyProductQuery);
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
