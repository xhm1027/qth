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
	 * 新增记录
	 * */
	public boolean addMessage(Message message);

	/**
	 * 更新记录
	 * */
	public boolean updateMessage(Message message);

	/**
	 * 删除记录
	 * */
	public boolean deleteMessageById(Long id);
}
