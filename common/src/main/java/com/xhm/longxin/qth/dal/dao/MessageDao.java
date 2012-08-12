/**
 *
 */
package com.xhm.longxin.qth.dal.dao;
import com.xhm.longxin.qth.dal.dataobject.Message;

/**
 * @author ren.zhangr
 *
 */
public interface MessageDao {
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
}
