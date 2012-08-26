package com.xhm.longxin.biz.user.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.xhm.longxin.biz.user.interfaces.MessageService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dao.MessageDao;
import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.dal.query.MessageQuery;

public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao messageDao;
	private static Logger log = LoggerFactory
			.getLogger(MessageServiceImpl.class);

	public boolean deleteMessageByReceiver(Message message, String receiver) {
		if (!receiver.equals(message.getReceiver())) {
			log.error("message is not send to " + receiver
					+ " \t no rights to delete it." + message);
			return true;// 没有找到需要删除的记录，直接返回
		}
		message.setReceiverDeleted(IS.Y);
		return messageDao.updateMessage(message);
	}

	public boolean deleteMessageBySender(Message message, String sender) {
		if (!sender.equals(message.getSender())) {
			log.error("message is not send from " + sender
					+ " \t no rights to delete it." + message);
			return true;// 没有找到需要删除的记录，直接返回
		}
		message.setSenderDeleted(IS.Y);
		return messageDao.updateMessage(message);
	}

	public List<Message> getInboxMessageList(String receiver, int pageStart,
			int pageSize) {
		MessageQuery messageQuery = new MessageQuery();
		messageQuery.setReceiver(receiver);
		messageQuery.setReceiverDeleted(IS.N);
		return messageDao.query(messageQuery, pageStart, pageSize);
	}

	public List<Message> getOutboxMessageList(String sender, int pageStart,
			int pageSize) {
		MessageQuery messageQuery = new MessageQuery();
		messageQuery.setSender(sender);
		messageQuery.setSenderDeleted(IS.N);
		return messageDao.query(messageQuery, pageStart, pageSize);
	}

	public Message openMessage(Long id, String receiver) {
		MessageQuery messageQuery = new MessageQuery();
		messageQuery.setId(id);
		messageQuery.setReceiver(receiver);
		// messageQuery.setIsOpened(IS.N);//只要是记录就先返回，同时置为已读，即使原为已读，也不影响
		List<Message> messageList = messageDao.query(messageQuery);
		if (null == messageList || messageList.isEmpty()) {
			return null;// 没有找到需要删除的记录，直接返回
		}
		Message message = messageList.get(0);
		message.setIsOpened(IS.Y);
		messageDao.updateMessage(message);
		return message;
	}

	public boolean sendMessage(Message message) {
		return messageDao.addMessage(message);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.biz.user.interfaces.MessageService#getInboxMessageCount
	 * (java.lang.String)
	 */
	public int getInboxMessageCount(String receiver) {
		MessageQuery messageQuery = new MessageQuery();
		messageQuery.setReceiver(receiver);
		messageQuery.setReceiverDeleted(IS.N);
		return messageDao.queryCount(messageQuery);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.biz.user.interfaces.MessageService#getOutboxMessageCount
	 * (java.lang.String)
	 */
	public int getOutboxMessageCount(String sender) {
		MessageQuery messageQuery = new MessageQuery();
		messageQuery.setSender(sender);
		messageQuery.setSenderDeleted(IS.N);
		return messageDao.queryCount(messageQuery);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.biz.user.interfaces.MessageService#getMessageById(java
	 * .lang.Long)
	 */
	public Message getMessageById(Long id) {
		return messageDao.getMessageById(id);
	}
}
