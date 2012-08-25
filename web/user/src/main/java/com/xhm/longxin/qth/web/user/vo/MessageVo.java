/**
 *
 */
package com.xhm.longxin.qth.web.user.vo;

import com.xhm.longxin.qth.dal.dataobject.Message;

/**
 * @author ren.zhangr
 *
 */
public class MessageVo extends Message{
private String sendToAdmin;

public String getSendToAdmin() {
	return sendToAdmin;
}

public void setSendToAdmin(String sendToAdmin) {
	this.sendToAdmin = sendToAdmin;
}

}
