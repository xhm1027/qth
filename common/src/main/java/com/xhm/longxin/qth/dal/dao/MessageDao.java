/**
 *
 */
package com.xhm.longxin.qth.dal.dao;
import java.util.List;

import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.dal.query.MessageQuery;
/**
 * @author ren.zhangr
 *
 */
public interface MessageDao  {
	/**
	 * ������¼
	 * */
	public boolean addMessage(Message message);

	/**
	 * ���¼�¼
	 * */
	public boolean updateMessage(Message message);

	/**
	 * ɾ����¼
	 * */
	public boolean deleteMessageById(Long id);
	/**
	 * ��ѯ��¼
	 * */
	public List<Message> query(MessageQuery messageQuery);
	/**
	 * ��ҳ��ѯ��¼
	 * */
	public List<Message> query(MessageQuery messageQuery,int pageStart,int pageSize);
	/**
	 * ��¼��
	 * */
	public int queryCount(MessageQuery messageQuery);
}
