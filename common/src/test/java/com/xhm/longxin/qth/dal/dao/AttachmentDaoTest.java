/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import junit.framework.Assert;
import org.testng.annotations.Test;
import com.xhm.longxin.qth.dal.constant.AttachmentType;
import com.xhm.longxin.qth.dal.dataobject.Attachment;

/**
 * @author ren.zhangr
 *
 */
public class AttachmentDaoTest  extends BaseDaoTest {
	@Test
	public void testAddAttachment() {
		Attachment attachment = new Attachment();
		attachment.setOwnerId(1L);
		attachment.setKey("firstPicture");
		attachment.setType(AttachmentType.IMG);
		attachment.setPath("/xxx/123/abc.jpg");
		// Ôö
		Assert.assertTrue(attachmentDao.addAttachment(attachment));
	}
}
