/**
 *
 */
package com.xhm.longxin.qth.dal.dao;
import com.xhm.longxin.qth.dal.dataobject.Attachment;


/**
 * @author ren.zhangr
 *
 */
public interface AttachmentDao {

	/**
	 * 新增审核记录
	 * */
	public boolean addAttachment(Attachment attachment);

	/**
	 * 更新审核记录审核记录
	 * */
	public boolean updateAttachment(Attachment attachment);

	/**
	 * 删除审核记录
	 * */
	public boolean deleteAttachmentById(Long id);
}
