/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import junit.framework.Assert;

import org.jtester.annotations.SpringBeanByName;
import org.testng.annotations.Test;

import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dataobject.Message;

/**
 * @author ren.zhangr
 *
 */
public class MessageDaoTest  extends BaseDaoTest{
	@SpringBeanByName
	private MessageDao messageDao;
	@Test
	public void testAddMessage() {
		Message m = new Message();
		m.setContent("����");
		m.setTitle("����");
		m.setSender("sender");
		m.setReceiver("receiver");
		m.setIsOpened(IS.N);
		m.setReceiverDeleted(IS.N);
		m.setSenderDeleted(IS.N);
		Assert.assertTrue(messageDao.addMessage(m));
	}
}
