/**
 *
 */
package com.xhm.longxin.qth.dal.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.xhm.longxin.qth.dal.dao.AttachmentDao;
import com.xhm.longxin.qth.dal.dataobject.Attachment;

/**
 * @author ren.zhangr
 *
 */
public class AttachmentDaoImpl extends SqlMapClientDaoSupport implements
		AttachmentDao {
	private static final String NAMESPACE_ATTACHMENT = "QTH_ATTACHMENT";
	private static final String INSERT_ID = "INSERT_ATTACHMENT";
	private static final String UPDATE_ID = "UPDATE_ATTACHMENT";

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.AttachmentDao#addAttachment(com.xhm.longxin
	 * .qth.dal.dataobject.Attachment)
	 */
	public boolean addAttachment(Attachment attachment) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_ATTACHMENT + "." + INSERT_ID, attachment);
		return res > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.AttachmentDao#deleteAttachmentById(java.lang
	 * .Long)
	 */
	public boolean deleteAttachmentById(Long id) {
		Attachment attachment = new Attachment();
		attachment.setId(id);
		attachment.setIsDeleted("Y");
		Integer res = (Integer) getSqlMapClientTemplate().delete(
				NAMESPACE_ATTACHMENT + "." + UPDATE_ID, attachment);
		return res > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.AttachmentDao#updateAttachment(com.xhm.longxin
	 * .qth.dal.dataobject.Attachment)
	 */
	public boolean updateAttachment(Attachment attachment) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_ATTACHMENT + "." + UPDATE_ID, attachment);
		return res > 0 ? true : false;
	}

}
