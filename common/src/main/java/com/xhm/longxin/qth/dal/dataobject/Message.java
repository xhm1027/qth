/**
 *
 */
package com.xhm.longxin.qth.dal.dataobject;

/**
 * @author ren.zhangr
 *
 */
public class Message extends BaseDo {

	private String title;
	private String receiver;
	private String sender;
	private String content;
	private String isOpened;
	private String senderDeleted;
	private String receiverDeleted;
	public static final String adminReceiver="1";
	public static final String adminSender="1";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	@Override
	public String toString() {
		return "id=" + id + ",title=" + title + ",sender=" + sender
				+ ",receiver=" + receiver + ",senderDeleted=" + senderDeleted
				+ ",receiverDeleted=" + receiverDeleted + ",isOpened="
				+ isOpened;
	}

}
