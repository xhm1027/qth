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
	 * ������˼�¼
	 * */
	public boolean addAttachment(Attachment attachment);

	/**
	 * ������˼�¼��˼�¼
	 * */
	public boolean updateAttachment(Attachment attachment);

	/**
	 * ɾ����˼�¼
	 * */
	public boolean deleteAttachmentById(Long id);
}
