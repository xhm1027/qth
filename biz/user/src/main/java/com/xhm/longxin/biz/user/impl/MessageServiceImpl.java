package com.xhm.longxin.biz.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xhm.longxin.biz.user.interfaces.MessageService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dao.MessageDao;
import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.dal.query.MessageQuery;

public class MessageServiceImpl implements MessageService{
	@Autowired
	private MessageDao messageDao;
	public boolean deleteMessageByReceiver(Long id, String receiver) {
		MessageQuery messageQuery=new MessageQuery();
		messageQuery.setId(id);
		messageQuery.setReceiver(receiver);
		messageQuery.setReceiverDeleted(IS.N);
		List<Message> messageList=messageDao.query(messageQuery);
		if(null==messageList||messageList.isEmpty()){
			return true;//没有找到需要删除的记录，直接返回
		}
		Message message=messageList.get(0);
		message.setReceiverDeleted(IS.Y);
		return messageDao.updateMessage(message);
	}

	public boolean deleteMessageBySender(Long id, String sender) {
		MessageQuery messageQuery=new MessageQuery();
		messageQuery.setId(id);
		messageQuery.setSender(sender);
		messageQuery.setSenderDeleted(IS.N);
		List<Message> messageList=messageDao.query(messageQuery);
		if(null==messageList||messageList.isEmpty()){
			return true;//没有找到需要删除的记录，直接返回
		}
		Message message=messageList.get(0);
		message.setSenderDeleted(IS.Y);
		return messageDao.updateMessage(message);
	}

	public List<Message> getInboxMessageList(String receiver, int pageStart,
			int pageSize) {
		MessageQuery messageQuery=new MessageQuery();
		messageQuery.setReceiver(receiver);
		return messageDao.query(messageQuery,pageStart,pageSize);
	}

	public List<Message> getOutboxMessageList(String sender, int pageStart,
			int pageSize) {
		MessageQuery messageQuery=new MessageQuery();
		messageQuery.setSender(sender);
		return messageDao.query(messageQuery,pageStart,pageSize);
	}

	public Message openMessage(Long id, String receiver) {
		MessageQuery messageQuery=new MessageQuery();
		messageQuery.setId(id);
		messageQuery.setReceiver(receiver);
		//messageQuery.setIsOpened(IS.N);//只要是记录就先返回，同时置为已读，即使原为已读，也不影响
		List<Message> messageList=messageDao.query(messageQuery);
		if(null==messageList||messageList.isEmpty()){
			return null;//没有找到需要删除的记录，直接返回
		}
		Message message=messageList.get(0);
		message.setIsOpened(IS.Y);
		messageDao.updateMessage(message);
		return message;
	}

	public boolean sendMessage(Message message) {
		return messageDao.addMessage(message);
	}

}
