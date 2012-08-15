/**
 *
 */
package com.xhm.longxin.qth.dal.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.xhm.longxin.qth.dal.dao.MessageDao;
import com.xhm.longxin.qth.dal.dataobject.AdminUser;
import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.dal.query.MessageQuery;

/**
 * @author ren.zhangr
 *
 */
public class MessageDaoImpl extends SqlMapClientDaoSupport implements
		MessageDao {

	private static final String NAMESPACE_MESSAGE = "QTH_MESSAGE";
	private static final String INSERT_ID = "INSERT_MESSAGE";
	private static final String UPDATE_ID = "UPDATE_MESSAGE";
	private static final String QUERY_BY_QUERYVO = "QUERY_BY_QUERYVO";
	private static final String QUERY_COUNT_QUERYVO = "QUERY_COUNT_QUERYVO";

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

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.MessageDao#query(com.xhm.longxin.qth.dal.
	 * query.MessageQuery)
	 */
	public List<Message> query(MessageQuery messageQuery) {
		List<Message> msgList = (List<Message>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_MESSAGE + "." + QUERY_BY_QUERYVO,
						messageQuery);
		return msgList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.MessageDao#query(com.xhm.longxin.qth.dal.
	 * query.MessageQuery, int, int)
	 */
	public List<Message> query(MessageQuery messageQuery, int pageStart,
			int pageSize) {
		messageQuery.setPageStart(pageStart);
		messageQuery.setPageSize(pageSize);
		List<Message> msgList = (List<Message>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_MESSAGE + "." + QUERY_BY_QUERYVO,
						messageQuery);
		return msgList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.xhm.longxin.qth.dal.dao.MessageDao#queryCount(java.lang.Long)
	 */
	public int queryCount(MessageQuery messageQuery) {
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				NAMESPACE_MESSAGE + "." + QUERY_COUNT_QUERYVO, messageQuery);
		return count;
	}

}
