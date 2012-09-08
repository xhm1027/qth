/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.screen;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.MessageService;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.dal.query.QueryObject;
import com.xhm.longxin.qth.web.admin.common.AdminConstant;
import com.xhm.longxin.qth.web.admin.common.QthAdmin;

/**
 * @author ren.zhangr
 *
 */
public class Inbox {
	@Autowired
	private MessageService messageService;
	@Autowired
	HttpSession session;

	public void execute(@Param("page") int page,
			@Param("opened") String isOpened, @Param("pageSize") int pageSize,
			Context context) {
		QthAdmin qthAdmin = (QthAdmin) session
				.getAttribute(AdminConstant.QTH_ADMIN_SESSION_KEY);
		if (qthAdmin == null || qthAdmin.getId() == null) {
			context.put("messageList", new ArrayList<Message>());
			return;
		}
		if (page == 0) {
			page = 1;
		}
		if (pageSize == 0 || pageSize > QueryObject.maxPageSize) {
			pageSize = QueryObject.messagePageSize;
		}
		if (IS.N.equals(isOpened)) {// 未读信息列表
			context.put("messageList", messageService
					.getUnopenInboxMessageList(Message.adminReceiver,
							(page - 1) * pageSize, pageSize));
		} else {// 全部信息列表
			context.put("messageList", messageService.getInboxMessageList(
					Message.adminReceiver, (page - 1) * pageSize, pageSize));
		}
		context.put("page", page);
		context.put("opened", isOpened);
		context.put("pageSize", pageSize);
		int unOpenCount = messageService
				.getInboxUnopenMessageCount(Message.adminReceiver);
		int totalCount = messageService
				.getInboxMessageCount(Message.adminReceiver);
		context.put("totalCount", totalCount);
		context.put("unOpenCount", unOpenCount);
		if (IS.N.equals(isOpened)) {// 未读信息分页
			context.put("totalPage", (unOpenCount - 1) / pageSize + 1);
		} else {
			context.put("totalPage", (totalCount - 1) / pageSize + 1);
		}
	}
}
