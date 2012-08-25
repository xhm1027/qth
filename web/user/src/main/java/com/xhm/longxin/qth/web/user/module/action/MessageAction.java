/**
 *
 */
package com.xhm.longxin.qth.web.user.module.action;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.MessageService;
import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.web.user.common.QthUser;
import com.xhm.longxin.qth.web.user.common.UserConstant;

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
		QthUser qthUser = (QthUser) session
				.getAttribute(UserConstant.QTH_USER_SESSION_KEY);
		if (qthUser == null) {
			log.warn("user not logged in.");
			return;
		}
		Message message = messageService.getMessageById(id);
		if (message == null) {
			log.warn("message not exist in db. id=" + id);
			return;
		}
		if (message.getReceiver().equals(qthUser.getId())) {// 收信人删除
			messageService.deleteMessageByReceiver(message, qthUser.getId());
		} else {
			messageService.deleteMessageBySender(message, qthUser.getId());
		}
	}
}
