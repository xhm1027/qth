package com.xhm.longxin.biz.admin.interfaces;

import com.xhm.longxin.biz.admin.vo.LoginVO;
import com.xhm.longxin.qth.dal.dataobject.AdminUser;

/**
 * 
 * @author xhm.xuhm
 *
 */
public interface AdminService {
	/**
	 * ��½
	 * @param vo
	 * @return User
	 */
	public AdminUser login(LoginVO vo);
	
	/**
	 * ����loginId��ȡ����
	 * @param loginId
	 * @return
	 */
	public AdminUser getAdminUserByLoginId(String loginId);
	
	
	/**
	 * ���¹���Ա����
	 * @param user
	 * @return
	 */
	public boolean updateAdminUser(AdminUser user);
	
	/**
	 * ͨ��id��ѯ�û�
	 * */
	public AdminUser getAdminUserById(Long id);
}
