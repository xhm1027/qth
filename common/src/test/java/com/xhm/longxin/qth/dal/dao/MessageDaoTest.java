/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import junit.framework.Assert;

import org.testng.annotations.Test;

import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.constant.ProductStatus;
import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;

/**
 * @author ren.zhangr
 *
 */
public class MessageDaoTest  extends BaseDaoTest{
	@Test
	public void testAddMessage() {
		Message m = new Message();
		m.setContent("ÄÚÈÝ");
		m.setTitle("±êÌâ");
		m.setSender("sender");
		m.setReceiver("receiver");
		m.setIsOpened(IS.N);
		m.setReceiverDeleted(IS.N);
		m.setSenderDeleted(IS.N);
		Assert.assertTrue(messageDao.addMessage(m));
	}
}
