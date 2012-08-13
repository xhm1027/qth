/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import junit.framework.Assert;

import org.jtester.annotations.SpringBeanByName;
import org.testng.annotations.Test;
import com.xhm.longxin.qth.dal.constant.AttachmentType;
import com.xhm.longxin.qth.dal.dataobject.Attachment;

/**
 * @author ren.zhangr
 *
 */
public class AttachmentDaoTest  extends BaseDaoTest {
	@SpringBeanByName
	private AttachmentDao attachmentDao;
	@Test
	public void testAddAttachment() {
		db.table("qth_attachment").clean().commit();
		Attachment attachment = new Attachment();
		attachment.setOwnerId(1L);
		attachment.setKey("firstPicture");
		attachment.setType(AttachmentType.IMG);
		attachment.setPath("/xxx/123/abc.jpg");
		//
		Assert.assertTrue(attachmentDao.addAttachment(attachment));
	}
}
