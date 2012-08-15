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
				this.put("msg_title", DataGenerator.repeat("���Ա���1","���Ա���2","���Ա���3"));
				this.put("msg_content", "������Ϣxxx����������");
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
		m.setContent("���ݲ���");
		m.setTitle("�������");
		m.setSender("sender");
		m.setReceiver("receiver");
		m.setIsOpened(IS.N);
		m.setReceiverDeleted(IS.N);
		m.setSenderDeleted(IS.N);
		want.bool(messageDao.addMessage(m)).eq(true);
	}

	@Test
	public void testQueryMessage() {
		//��ѯ�ռ���Ϊs1����Ϣ
		MessageQuery messageQuery=new MessageQuery();
		messageQuery.setSender("s1");
		Message m=messageDao.query(messageQuery).get(0);
		want.string(m.getTitle()).eq("���Ա���1");
		want.number(messageDao.queryCount(messageQuery)).eq(1);
		//��ѯȫ��δ����Ϣ
		messageQuery.setSender(null);
		messageQuery.setIsOpened(IS.N);
		want.number(messageDao.queryCount(messageQuery)).eq(3);
		//���Էֲ�
		want.number(messageDao.query(messageQuery,0,2).size()).eq(2);
	}

	@Test
	public void testUpdateMessage() {
		MessageQuery messageQuery=new MessageQuery();
		messageQuery.setSender("s1");
		Message m=messageDao.query(messageQuery).get(0);
		m.setSender("s1����");
		messageDao.updateMessage(m);
		messageQuery.setSender("s1����");
		want.number(messageDao.query(messageQuery).size()).eq(1);
	}

}
