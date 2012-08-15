/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import java.util.List;

import com.xhm.longxin.qth.dal.dataobject.AdminUser;
import com.xhm.longxin.qth.dal.query.AdminUserQuery;

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
	public AdminUser getAdminUserByLoginIdAndPass(String loginId,
			String password);

	/**
	 * �����û���ע��ʹ��
	 * */
	public boolean addAdminUser(AdminUser user);

	public boolean updateAdminUser(AdminUser user);

	/**
	 * ɾ���û�������Ա��
	 * */
	public boolean deleteAdminUserById(Long id);

	/**
	 * ��ѯ�û�����ҳ
	 * */
	public List<AdminUser> query(AdminUserQuery adminUserQuery, int pageStart,
			int pageSize);

	/**
	 *��ѯ�û�������ҳ
	 * */
	public List<AdminUser> query(AdminUserQuery adminUserQuery);

	/**
	 *��ѯ�û�count
	 * */
	public int queryCount(AdminUserQuery adminUserQuery);

}
