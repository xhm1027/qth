package com.xhm.longxin.biz.user.interfaces;

import java.util.List;

import com.xhm.longxin.biz.user.vo.LoginVO;
import com.xhm.longxin.biz.user.vo.UserAuditVO;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.query.UserQuery;

/**
 *
 * @author xhm.xuhm
 *
 */
public interface UserService {
	/**
	 * login
	 * @param vo
	 * @return User
	 */
	public User login(LoginVO vo);


	/**
	 * ����id��ѯ�û�
	 * @param loginId
	 * @return
	 */
	public User getUserByLoginId(String loginId);

	/**
	 * ����id��ѯ�û�
	 * @param loginId
	 * @return
	 */
	public User getUserById(Long id);
	/**
	 * ����email��ȡ�û�
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email);

	/**
	 * �����û�
	 * */
	public boolean addUser(User user);

	/**
	 * ��ѯ�û�
	 * */
	public List<User> queryUser(UserQuery userQuery,int pageStart,int pageSize);

	/**
	 * ��ѯ�û�
	 * */
	public int queryCount(UserQuery userQuery);

	/**
	 *����û�
	 * */
	public boolean auditUser(UserAuditVO userAuditVO);
}
