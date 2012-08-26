/**
 *
 */
package com.xhm.longxin.qth.web.user.module.screen;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.MessageService;
import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.web.user.common.QthUser;
import com.xhm.longxin.qth.web.user.common.UserConstant;

/**
 * @author ren.zhangr
 *
 */
public class SendMessage {
	@Autowired
	private MessageService messageService;
	@Autowired
	HttpSession session;
	private static Logger log = LoggerFactory.getLogger(SendMessage.class);

	public void execute(@Param("replyId") long replyId, Context context) {
		QthUser qthUser = (QthUser) session
				.getAttribute(UserConstant.QTH_USER_SESSION_KEY);

		if (qthUser == null) {
			log.warn("user not logged in yet! no rights to send message!");
			return;
		}
		if (replyId == 0) {
			return;
		}
		Message message = messageService.getMessageById(replyId);
		if (null == message || !message.getReceiver().equals(qthUser.getId())) {
			// 没找到消息或者该消息接收者非登录者，直接返回
			return;
		}
		context.put("message", message);
	}
}
