/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import java.util.List;
import java.util.Map;

import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.dataobject.UserInterest;
import com.xhm.longxin.qth.dal.query.UserQuery;

/**
 * @author ren.zhangr
 *
 */
public interface UserDao{

	/**
	 * ͨ��id��ѯ�û�
	 * */
	public User getUserById(Long id);

	/**
	 * ͨ��id��ѯ�û�
	 * */
	public User getUserByLoginId(String loginId);

	/**
	 * ͨ���û���/�����ѯ�û�����¼ʹ��
	 * */
	public User getUserByLoginIdAndPass(String loginId, String password);

	/**
	 * �����û���ע��ʹ��
	 * */
	public boolean addUser(User user);

	/**
	 * ��ѯ�û�����ͨ������������ѯ�����û��������䣬�û��ȼ��ȵȣ�����Աʹ��
	 * */
	public List<User> query(UserQuery userQuery);
	/**
	 * ��ѯ�û�����ͨ������������ѯ�����û��������䣬�û��ȼ��ȵȣ�����Աʹ��
	 * */
	public List<User> query(UserQuery userQuery,int pageStart,int pageSize);
	/**
	 * ��ѯ�û���
	 * */
	public int queryCount(UserQuery userQuery);

	/**
	 * �����û����޸���Ϣ�����Ա�޸��û���Ϣʱ��
	 * */
	public boolean updateUser(User user);

	/**
	 * ɾ���û�������Ա��
	 * */
	public boolean deleteUserById(Long id);

}
