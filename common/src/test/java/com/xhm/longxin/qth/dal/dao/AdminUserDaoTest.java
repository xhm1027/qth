/**
 *
 */
package com.xhm.longxin.qth.dal.dao;
import org.jtester.annotations.SpringBeanByName;
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
	public void testAddAdminUser() {
		db.table("admin_user").clean().commit();
		AdminUser user = new AdminUser();
		String loginId = "admin";
		user.setLoginId(loginId);
		user.setName("管理员用户名");
		user.setPassword("11234");
		user.setEmail("afb@123.com");
		boolean result = adminUserDao.addAdminUser(user);
		want.bool(result).isEqualTo(true);
		user = adminUserDao.getAdminUserByLoginId(loginId);
		want.object(user).notNull();
		AdminUser u = adminUserDao.getAdminUserByLoginIdAndPass(loginId,
				"11234");
		want.object(u).notNull();
	}

	@Test
	public void testAdminUserUpdate() {
		db.table("admin_user").clean().commit();
		AdminUser user = new AdminUser();
		String loginId = "admin";
		user.setLoginId(loginId);
		user.setName("xhm");
		user.setPassword("11234");
		user.setEmail("afb@123.com");

		want.bool(adminUserDao.addAdminUser(user)).isEqualTo(true);
		AdminUser u = adminUserDao.getAdminUserByLoginId(loginId);

		u.setName("zr");
		adminUserDao.updateAdminUser(u);
		want.string(adminUserDao.getAdminUserByLoginId(loginId).getName()).eq("zr");

		adminUserDao.deleteAdminUserById(u.getId());
		want.object(adminUserDao.getAdminUserByLoginId(loginId)).isNull();
	}
}
