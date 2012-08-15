/**
 *
 */
package com.xhm.longxin.qth.dal.query;

/**
 * @author ren.zhangr
 *
 */
public class MessageQuery extends QueryObject{
	private String receiver;
	private String sender;
	private String isOpened;
	private String senderDeleted;
	private String receiverDeleted;
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getIsOpened() {
		return isOpened;
	}
	public void setIsOpened(String isOpened) {
		this.isOpened = isOpened;
	}
	public String getSenderDeleted() {
		return senderDeleted;
	}
	public void setSenderDeleted(String senderDeleted) {
		this.senderDeleted = senderDeleted;
	}
	public String getReceiverDeleted() {
		return receiverDeleted;
	}
	public void setReceiverDeleted(String receiverDeleted) {
		this.receiverDeleted = receiverDeleted;
	}

}
