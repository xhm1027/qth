/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.jtester.annotations.SpringBeanByName;
import org.jtester.core.IJTester.DataGenerator;
import org.jtester.core.IJTester.DataMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.xhm.longxin.qth.dal.constant.Gender;
import com.xhm.longxin.qth.dal.constant.UserInterestType;
import com.xhm.longxin.qth.dal.constant.UserLevel;
import com.xhm.longxin.qth.dal.constant.UserRole;
import com.xhm.longxin.qth.dal.constant.UserStatus;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.dataobject.UserInterest;

/**
 * @author ren.zhangr
 *
 */
public class UserDaoTest extends BaseDaoTest{
	@SpringBeanByName
	private UserDao userDao;

	@BeforeMethod
	public void setUp(){
		db.table("qth_user").clean().commit();
		//db.table("qth_pro_category").clean().commit();
	}
	@Test
	public void testUserAdd() {
		String loginId="zhangren";
		User user = new User();
		user.setBusiLicense("11234");
		//user.setBuyCategory("1,2,3");
		user.setCompany("单测公司名");
		user.setCompanyAddress("单元测试公司地址");
		user.setEmail("afb@123.com");
		user.setGender(Gender.FEMALE);
		user.setIdCardNum("365587458755");
		user.setLoginId(loginId);
		user.setMobilePhone("12355478965");
		user.setName("单测张三");
		user.setPassword("11234");
		user.setPhoneArea("0571");
		user.setPhoneNumber("88889988");
		user.setRole(UserRole.INNER_USER);
		//user.setSaleCategory("3,2,1");
		user.setStatus(UserStatus.NEW);
		user.setUserLevel(UserLevel.COMMON);
		List<UserInterest> buyInsterests=new ArrayList<UserInterest>();
		UserInterest i=new UserInterest();
		i.setInterest(UserInterestType.BUY);
		i.setValue(1L);
		i.setLoginId(loginId);
		buyInsterests.add(i);
		user.setBuyInterests(buyInsterests);
		//新增
		Assert.assertTrue(userDao.addUser(user));
		//验证
		Assert.assertNotNull(userDao.getUserByLoginId(loginId));
		User u=userDao.getUserByLoginIdAndPass(loginId, "11234");
		Assert.assertNotNull(u);
	}

	@Test
	public void testUserUpdate() {
		db.table("qth_pro_category").clean().insert(1, new DataMap() {
			{
				this.put("id",1);
				this.put("name", "testCate1");
				this.put("is_material", "Y");
			}
		});
		String loginId="zhangren";
		User user = new User();
		user.setBusiLicense("11234");
		//user.setBuyCategory("1,2,3");
		user.setCompany("单测公司名");
		user.setCompanyAddress("单测公司地址");
		user.setEmail("afb@123.com");
		user.setGender(Gender.FEMALE);
		user.setIdCardNum("365587458755");
		user.setLoginId(loginId);
		user.setMobilePhone("12355478965");
		user.setName("单测李四");
		user.setPassword("11234");
		user.setPhoneArea("0571");
		user.setPhoneNumber("88889988");
		user.setRole(UserRole.INNER_USER);
		//user.setSaleCategory("3,2,1");
		user.setStatus(UserStatus.NEW);
		user.setUserLevel(UserLevel.COMMON);
		List<UserInterest> buyInsterests=new ArrayList<UserInterest>();
		UserInterest bi=new UserInterest();
		bi.setInterest(UserInterestType.BUY);
		bi.setValue(1L);
		bi.setLoginId(loginId);
		buyInsterests.add(bi);
		user.setBuyInterests(buyInsterests);
		//新增
		userDao.addUser(user);
		User u=userDao.getUserByLoginIdAndPass(loginId, "11234");
		u.setPassword("123");
		u.setCompany("单测公司名");
		bi.setValue(1L);
		//编辑
		u.setBuyInterests(buyInsterests);
		userDao.updateUser(u);
		u=userDao.getUserByLoginIdAndPass(loginId, "123");
		Assert.assertEquals(u.getCompany(), userDao.getUserById(u.getId()).getCompany());
		//检查测试类别关联
		Assert.assertEquals(u.getBuyInterests().get(0).getName(),"testCate1");
		//编辑产品
		Map<String,Object> param=new HashMap<String,Object>();
		List<String> buyInterests=new ArrayList<String>();
		buyInterests.add("321");
		param.put("buyInterests", buyInterests);
		Assert.assertNotNull(userDao.queryUser(param));
		//删除
		userDao.deleteUserById(u.getId());
		Assert.assertNull(userDao.getUserByLoginId(loginId));
	}
}
