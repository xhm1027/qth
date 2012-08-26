/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.screen;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.MessageService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.dal.query.MessageQuery;
import com.xhm.longxin.qth.web.admin.common.AdminConstant;
import com.xhm.longxin.qth.web.admin.common.QthAdmin;

/**
 * @author ren.zhangr
 *
 */
public class OpenMessage {
	@Autowired
	HttpSession session;
	@Autowired
	private MessageService messageService;

	public void execute(@Param("id") Long id, Context context) {
		QthAdmin qthAdmin = (QthAdmin) session
		.getAttribute(AdminConstant.QTH_ADMIN_SESSION_KEY);

		if (qthAdmin == null) {
			return;
		}
		MessageQuery messageQuery = new MessageQuery();
		messageQuery.setId(id);
		Message message = messageService.getMessageById(id);
		if (message == null// 该 消息记录不属于admin用户
				|| (!message.getSender().equals(Message.adminSender) && !message
						.getReceiver().equals(Message.adminReceiver))) {
			return;
		}
		if (message.getIsOpened().equalsIgnoreCase(IS.N)
				&& message.getReceiver().equals(Message.adminReceiver)) {
			messageService.openMessage(id, Message.adminReceiver);
		}
		context.put("message", message);
	}
}
