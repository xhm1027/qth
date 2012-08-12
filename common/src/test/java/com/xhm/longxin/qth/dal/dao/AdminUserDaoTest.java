/**
 *
 */
package com.xhm.longxin.qth.dal.dao;
import junit.framework.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.xhm.longxin.qth.dal.dataobject.AdminUser;

/**
 * @author ren.zhangr
 *
 */
public class AdminUserDaoTest extends BaseDaoTest {
	@BeforeMethod
	public void setUp() {
		AdminUser user = adminUserDao.getAdminUserByLoginId("admin");
		if (user != null) {
			adminUserDao.deleteAdminUserById(user.getId());
		}
	}

	@Test
	public void testAddAdminUser() {
		AdminUser user = new AdminUser();
		String loginId = "admin";
		user.setLoginId(loginId);
		// user.setBuyCategory("1,2,3");
		user.setName("李四");
		user.setPassword("11234");
		user.setEmail("afb@123.com");
		Assert.assertTrue(adminUserDao.addAdminUser(user));
		Assert.assertNotNull(adminUserDao.getAdminUserByLoginId(loginId));
		AdminUser u = adminUserDao.getAdminUserByLoginIdAndPass(loginId,
				"11234");
		Assert.assertNotNull(u);
	}

	@Test
	public void testAdminUserUpdate() {
		AdminUser user = new AdminUser();
		String loginId = "admin";
		user.setLoginId(loginId);
		// user.setBuyCategory("1,2,3");
		user.setName("李四");
		user.setPassword("11234");
		user.setEmail("afb@123.com");
		Assert.assertTrue(adminUserDao.addAdminUser(user));
		AdminUser u = adminUserDao.getAdminUserByLoginId(loginId);
		// 修改
		u.setName("新李四");
		adminUserDao.updateAdminUser(u);
		Assert.assertEquals("新李四", adminUserDao.getAdminUserByLoginId(loginId)
				.getName());
		// 删除
		adminUserDao.deleteAdminUserById(u.getId());
		Assert.assertNull(adminUserDao.getAdminUserByLoginId(loginId));
	}
}
