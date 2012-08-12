/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import java.util.List;
import java.util.Map;

import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.dataobject.UserInterest;

/**
 * @author ren.zhangr
 *
 */
public interface UserDao {

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
	public List<User> queryUser(Map<String, Object> param);

	/**
	 * �����û����޸���Ϣ�����Ա�޸��û���Ϣʱ��
	 * */
	public boolean updateUser(User user);

	/**
	 * ɾ���û�������Ա��
	 * */
	public boolean deleteUserById(Long id);

	/**
	 * ����
	 * */
	public boolean addOrUpdateUserInterest(UserInterest userInterest);

}
