/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.action;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.form.CustomErrors;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormField;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.MessageService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.web.admin.common.AdminConstant;
import com.xhm.longxin.qth.web.admin.common.QthAdmin;

/**
 * @author ren.zhangr
 *
 */
public class MessageAction {
	@Autowired
	private MessageService messageService;
	@Autowired
	HttpSession session;
	private static Logger log = LoggerFactory.getLogger(MessageAction.class);

	/**
	 * 删除站内信
	 * */
	public void doDelete(@Param("id") Long id, Navigator nav, Context context) {
		QthAdmin qthAdmin = (QthAdmin) session
		.getAttribute(AdminConstant.QTH_ADMIN_SESSION_KEY);
		if (qthAdmin == null) {
			log.warn("user not logged in.");
			return;
		}
		Message message = messageService.getMessageById(id);
		if (message == null) {
			log.warn("message not exist in db. id=" + id);
			return;
		}
		if (message.getReceiver().equals(qthAdmin.getId())) {// 收信人删除
			messageService.deleteMessageByReceiver(message, Message.adminReceiver);
		} else {
			messageService.deleteMessageBySender(message,Message.adminSender);
		}
	}

	/**
	 * 删除站内信
	 * */
	public void doSend(
			@FormGroup("sendMessage") Message message,
			Context context,
			@FormField(name = "messageSendInfo", group = "sendMessage") CustomErrors info,
			@FormField(name = "messageSendErr", group = "sendMessage") CustomErrors err) {
		QthAdmin qthAdmin = (QthAdmin) session
		.getAttribute(AdminConstant.QTH_ADMIN_SESSION_KEY);
		if (qthAdmin == null) {
			log.warn("user not logged in.");
			err.setMessage("fail");
			return;
		}
		message.setSender(Message.adminSender);
		messageService.sendMessage(message);
		info.setMessage("success");
	}
}
