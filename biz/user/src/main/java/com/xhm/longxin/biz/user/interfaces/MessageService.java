package com.xhm.longxin.biz.user.interfaces;

import java.util.List;

import com.xhm.longxin.qth.dal.dataobject.Message;

public interface MessageService {
	/**
	 * 发件人删除
	 * */
	public boolean deleteMessageBySender(Long id,String sender);

	/**
	 * 收件人删除
	 * */
	public boolean deleteMessageByReceiver(Long id,String receiver);

	/**
	 * 置为已读
	 * */
	public Message openMessage(Long id,String receiver);

	/**
	 * 获取消息
	 * */
	public Message getMessageById(Long id);

	/**
	 * 收件箱列表
	 * */
	public List<Message> getInboxMessageList(String receiver,int pageStart,int pageSize);

	/**
	 * 发件箱列表
	 * */
	public List<Message> getOutboxMessageList(String sender,int pageStart,int pageSize);

	/**
	 * 发信
	 * */
	public boolean sendMessage(Message message);

	/**
	 * 收件数
	 * */
	public int getInboxMessageCount(String receiver);

	/**
	 * 发件数
	 * */
	public int getOutboxMessageCount(String receiver);

}
