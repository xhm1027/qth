/**
 *
 */
package com.xhm.longxin.qth.dal.dao;
import org.jtester.annotations.SpringBeanByName;
import org.jtester.core.IJTester.DataMap;
import org.testng.annotations.Test;
import com.xhm.longxin.qth.dal.dataobject.AdminUser;
import com.xhm.longxin.qth.dal.query.AdminUserQuery;

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

	@Test
	public void testAdminUserQuery() {
		db.table("admin_user").clean().insert(3, new DataMap() {
			{
				this.put("name",DataGenerator.repeat("zhangren","xhm","admin"));
				this.put("login_id", DataGenerator.repeat("zhangren","xhm","admin"));
				this.put("is_deleted", "N");
				this.put("email", DataGenerator.repeat("a@123.com","b@123.com","c@123.com"));
			}
		}).commit();
		AdminUserQuery adminUserQuery=new AdminUserQuery();
		adminUserQuery.setLoginId("zhangren");
		//queryCount
		want.number(adminUserDao.queryCount(adminUserQuery)).eq(1);
		//query
		AdminUser user =adminUserDao.query(adminUserQuery).get(0);
		want.string(user.getEmail()).isEqualTo("a@123.com");
		adminUserQuery.setLoginId(null);
		//query all
		want.number(adminUserDao.queryCount(adminUserQuery)).eq(3);
		//query by page
		want.number(adminUserDao.query(adminUserQuery,0,2).size()).eq(2);
	}
}
