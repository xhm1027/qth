package com.xhm.longxin.biz.user.interfaces;

/**
 * 附件service
 * @author xhm.xuhm
 *
 */
public interface AttachmentService {
	/**
	 * 根据主键ID删除附件
	 * @param id
	 * @return
	 */
	public boolean deleteAttachmentById(Long id);
}
