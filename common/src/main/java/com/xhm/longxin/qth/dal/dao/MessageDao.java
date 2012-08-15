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
	/**
	 * 查询记录
	 * */
	public List<Message> query(MessageQuery messageQuery);
	/**
	 * 分页查询记录
	 * */
	public List<Message> query(MessageQuery messageQuery,int pageStart,int pageSize);
	/**
	 * 记录数
	 * */
	public int queryCount(MessageQuery messageQuery);
}
