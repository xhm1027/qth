package com.xhm.longxin.qth.web.user.module.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.AttachmentService;

public class AttachmentAction {
	@Autowired
	private AttachmentService attachmentService;
	
	public void doDel(@Param("attachmentId") Long attachementId,
			Navigator nav, ParameterParser params, Context context) {
		boolean result = attachmentService.deleteAttachmentById(attachementId);
		if(result){
			context.put("result", "success");
			context.put("resultMessage", "ɾ����ƷͼƬ�ɹ���");
		}else{
			context.put("result", "fail");
			context.put("resultMessage", "ɾ����ƷͼƬʧ�ܣ�");
			
		}
	}
}
