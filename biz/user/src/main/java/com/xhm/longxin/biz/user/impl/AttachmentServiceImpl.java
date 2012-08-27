package com.xhm.longxin.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.xhm.longxin.biz.user.interfaces.AttachmentService;
import com.xhm.longxin.qth.dal.dao.AttachmentDao;
/**
 * ¸½¼þ·þÎñ
 * @author xhm.xuhm
 *
 */
public class AttachmentServiceImpl implements AttachmentService {
	@Autowired
	private AttachmentDao attachmentDao;
	public boolean deleteAttachmentById(Long id) {
		return attachmentDao.deleteAttachmentById(id);
	}

}
