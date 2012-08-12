/**
 *
 */
package com.xhm.longxin.qth.dal.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.xhm.longxin.qth.dal.dao.MessageDao;
import com.xhm.longxin.qth.dal.dataobject.Message;

/**
 * @author ren.zhangr
 *
 */
public class MessageDaoImpl extends SqlMapClientDaoSupport implements
		MessageDao {

	private static final String NAMESPACE_MESSAGE = "QTH_MESSAGE";
	private static final String INSERT_ID = "INSERT_MESSAGE";
	private static final String UPDATE_ID = "UPDATE_MESSAGE";

	public boolean addMessage(Message message) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_MESSAGE + "." + INSERT_ID, message);
		return res > 0 ? true : false;
	}

	public boolean deleteMessageById(Long id) {
		Message message = new Message();
		message.setId(id);
		message.setIsDeleted("y");
		Integer res = (Integer) getSqlMapClientTemplate().delete(
				NAMESPACE_MESSAGE + "." + UPDATE_ID, message);
		return res > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.MessageDao#updateMessage(com.xhm.longxin.
	 * qth.dal.dataobject.Message)
	 */
	public boolean updateMessage(Message message) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_MESSAGE + "." + UPDATE_ID, message);
		return res > 0 ? true : false;
	}

}
