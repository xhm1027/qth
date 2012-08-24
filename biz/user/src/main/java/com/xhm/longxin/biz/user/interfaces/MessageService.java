package com.xhm.longxin.biz.user.interfaces;

import java.util.List;

import com.xhm.longxin.qth.dal.dataobject.Message;

public interface MessageService {
	/**
	 * ������ɾ��
	 * */
	public boolean deleteMessageBySender(Long id,String sender);

	/**
	 * �ռ���ɾ��
	 * */
	public boolean deleteMessageByReceiver(Long id,String receiver);

	/**
	 * ��Ϊ�Ѷ�
	 * */
	public Message openMessage(Long id,String receiver);

	/**
	 * ��ȡ��Ϣ
	 * */
	public Message getMessageById(Long id);

	/**
	 * �ռ����б�
	 * */
	public List<Message> getInboxMessageList(String receiver,int pageStart,int pageSize);

	/**
	 * �������б�
	 * */
	public List<Message> getOutboxMessageList(String sender,int pageStart,int pageSize);

	/**
	 * ����
	 * */
	public boolean sendMessage(Message message);

	/**
	 * �ռ���
	 * */
	public int getInboxMessageCount(String receiver);

	/**
	 * ������
	 * */
	public int getOutboxMessageCount(String receiver);

}
