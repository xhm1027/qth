/**
 *
 */
package com.xhm.longxin.qth.web.user.module.screen;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.MessageService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.dal.query.MessageQuery;
import com.xhm.longxin.qth.web.user.common.QthUser;
import com.xhm.longxin.qth.web.user.common.UserConstant;

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
		QthUser qthUser = (QthUser) session
				.getAttribute(UserConstant.QTH_USER_SESSION_KEY);

		if (qthUser == null || qthUser.getId() == null) {
			return;
		}
		MessageQuery messageQuery = new MessageQuery();
		messageQuery.setId(id);
		Message message = messageService.getMessageById(id);
		if (message == null// 该 消息记录不属于当前用户，admin要加再条件区分
				|| (!message.getSender().equals(qthUser.getId()) && !message
						.getReceiver().equals(qthUser.getId()))) {
			return;
		}
		if (message.getIsOpened().equalsIgnoreCase(IS.N)
				&& message.getReceiver().equals(qthUser.getId())) {
			messageService.openMessage(id, qthUser.getId());
		}
		context.put("message", message);
	}
}
