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
import com.xhm.longxin.qth.dal.constant.IS;
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
			@Param("opened") String isOpened, @Param("pageSize") int pageSize,
			Context context) {
		QthUser qthUser = (QthUser) session
				.getAttribute(UserConstant.QTH_USER_SESSION_KEY);

		if (qthUser == null || qthUser.getId() == null) {
			context.put("messageList", new ArrayList<Message>());
			return;
		}
		if (page == 0) {
			page = 1;
		}
		if (pageSize == 0 || pageSize > QueryObject.maxPageSize) {
			pageSize = QueryObject.messagePageSize;
		}
		if (IS.N.equals(isOpened)) {// δ����Ϣ�б�
			context.put("messageList", messageService
					.getUnopenInboxMessageList(qthUser.getId(), (page - 1)
							* pageSize, pageSize));
		} else {// ȫ����Ϣ�б�
			context.put("messageList", messageService.getInboxMessageList(
					qthUser.getId(), (page - 1) * pageSize, pageSize));
		}
		context.put("page", page);
		context.put("opened", isOpened);
		context.put("pageSize", pageSize);
		int unOpenCount = messageService.getInboxUnopenMessageCount(qthUser
				.getId());
		int totalCount = messageService.getInboxMessageCount(qthUser.getId());
		context.put("totalCount", totalCount);
		context.put("unOpenCount", unOpenCount);
		if (IS.N.equals(isOpened)) {// δ����Ϣ��ҳ
			context.put("totalPage", (unOpenCount - 1) / pageSize + 1);
		} else {
			context.put("totalPage", (totalCount - 1) / pageSize + 1);
		}
	}
}
