/**
 *
 */
package com.xhm.longxin.qth.dal.dao;
import org.jtester.annotations.SpringBeanByName;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.dal.query.MessageQuery;

/**
 * @author ren.zhangr
 *
 */
public class MessageDaoTest  extends BaseDaoTest{
	@SpringBeanByName
	private MessageDao messageDao;

	@BeforeMethod
	public void setUp(){
		db.table("qth_msg").clean().insert(3, new DataMap() {
			{
				this.put("id",DataGenerator.repeat(1,2,3));
				this.put("msg_title", DataGenerator.repeat("测试标题1","测试标题2","测试标题3"));
				this.put("msg_content", "描述信息xxx，短信内容");
				this.put("sender", DataGenerator.repeat("s1","s2","s3"));
				this.put("receiver", DataGenerator.repeat("r1","r2","r3"));
				this.put("is_opened", "N");
				this.put("is_deleted", "N");
				this.put("receiver_deleted", "N");
				this.put("sender_deleted", "N");
			}
		}).commit();
	}

	@Test
	public void testAddMessage() {
		Message m = new Message();
		m.setContent("内容测试");
		m.setTitle("标题测试");
		m.setSender("sender");
		m.setReceiver("receiver");
		m.setIsOpened(IS.N);
		m.setReceiverDeleted(IS.N);
		m.setSenderDeleted(IS.N);
		want.bool(messageDao.addMessage(m)).eq(true);
	}

	@Test
	public void testQueryMessage() {
		//查询收件人为s1的消息
		MessageQuery messageQuery=new MessageQuery();
		messageQuery.setSender("s1");
		Message m=messageDao.query(messageQuery).get(0);
		want.string(m.getTitle()).eq("测试标题1");
		want.number(messageDao.queryCount(messageQuery)).eq(1);
		//查询全部未读消息
		messageQuery.setSender(null);
		messageQuery.setIsOpened(IS.N);
		want.number(messageDao.queryCount(messageQuery)).eq(3);
		//测试分布
		want.number(messageDao.query(messageQuery,0,2).size()).eq(2);
	}

	@Test
	public void testUpdateMessage() {
		MessageQuery messageQuery=new MessageQuery();
		messageQuery.setSender("s1");
		Message m=messageDao.query(messageQuery).get(0);
		m.setSender("s1更新");
		messageDao.updateMessage(m);
		messageQuery.setSender("s1更新");
		want.number(messageDao.query(messageQuery).size()).eq(1);
	}

}
