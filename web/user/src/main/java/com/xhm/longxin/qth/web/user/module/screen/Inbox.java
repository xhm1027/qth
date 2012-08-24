/**
 *
 */
package com.xhm.longxin.qth.web.user.module.screen;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.MessageService;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.dal.query.QueryObject;
import com.xhm.longxin.qth.web.user.common.QthUser;
import com.xhm.longxin.qth.web.user.common.UserConstant;

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
			@Param("pageSize") int pageSize, Context context) {
		QthUser qthUser = (QthUser) session
				.getAttribute(UserConstant.QTH_USER_SESSION_KEY);

		if (qthUser == null) {
			context.put("messageList", new ArrayList<Message>());
			return;
		}
		if (page == 0) {
			page = 1;
		}
		if (pageSize == 0 || pageSize > QueryObject.maxPageSize) {
			pageSize = QueryObject.messagePageSize;
		}
		context.put("messageList", messageService.getInboxMessageList(qthUser
				.getId(), (page - 1) * pageSize, pageSize));
		context.put("page", page);
		context.put("pageSize",pageSize);
		int totalCount =messageService.getInboxMessageCount(qthUser
				.getId());
		context.put("totalCount", totalCount);
		context.put("totalPage", (totalCount - 1) / pageSize + 1);
	}
}
