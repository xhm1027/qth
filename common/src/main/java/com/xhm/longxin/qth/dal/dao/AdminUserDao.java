/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import com.xhm.longxin.qth.dal.dataobject.AdminUser;

/**
 * @author ren.zhangr
 *
 */
public interface AdminUserDao {
	/**
	 * ͨ��id��ѯ�û�
	 * */
	public AdminUser getAdminUserById(Long id);

	/**
	 * ͨ��id��ѯ�û�
	 * */
	public AdminUser getAdminUserByLoginId(String loginId);

	/**
	 * ͨ���û���/�����ѯ�û�����¼ʹ��
	 * */
	public AdminUser getAdminUserByLoginIdAndPass(String loginId, String password);

	/**
	 * �����û���ע��ʹ��
	 * */
	public boolean addAdminUser(AdminUser user);


	public boolean updateAdminUser(AdminUser user);

	/**
	 * ɾ���û�������Ա��
	 * */
	public boolean deleteAdminUserById(Long id);

}
