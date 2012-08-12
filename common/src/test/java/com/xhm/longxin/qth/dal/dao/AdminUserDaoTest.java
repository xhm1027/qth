/**
 *
 */
package com.xhm.longxin.qth.dal.dao;


import org.jtester.annotations.DbFit;
import org.jtester.annotations.SpringBeanByName;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.xhm.longxin.qth.dal.dataobject.AdminUser;

/**
 * @author ren.zhangr
 * 
 */
public class AdminUserDaoTest extends BaseDaoTest {
	@SpringBeanByName
	private AdminUserDao adminUserDao;

	@Test
    @DbFit(when = "dbfit/adminUser/prepare_add_adminUser.when.wiki")
	public void testAddAdminUser() {
		AdminUser user = new AdminUser();
		String loginId = "admin";
		user.setLoginId(loginId);
		// user.setBuyCategory("1,2,3");
		user.setName("����");
		user.setPassword("11234");
		user.setEmail("afb@123.com");
		want.bool(adminUserDao.addAdminUser(user)).isEqualTo(true);
		want.object(adminUserDao.getAdminUserByLoginId(loginId)).notNull();
		AdminUser u = adminUserDao.getAdminUserByLoginIdAndPass(loginId,
				"11234");
		want.object(u).notNull();
	}

	@Test
	public void testAdminUserUpdate() {
		AdminUser user = new AdminUser();
		String loginId = "admin";
		user.setLoginId(loginId);
		// user.setBuyCategory("1,2,3");
		user.setName("����");
		user.setPassword("11234");
		user.setEmail("afb@123.com");
		Assert.assertTrue(adminUserDao.addAdminUser(user));
		AdminUser u = adminUserDao.getAdminUserByLoginId(loginId);
		// �޸�
		u.setName("������");
		adminUserDao.updateAdminUser(u);
		Assert.assertEquals("������",
				adminUserDao.getAdminUserByLoginId(loginId).getName());
		// ɾ��
		adminUserDao.deleteAdminUserById(u.getId());
		Assert.assertNull(adminUserDao.getAdminUserByLoginId(loginId));
	}
}
