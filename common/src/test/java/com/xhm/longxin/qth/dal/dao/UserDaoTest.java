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
	}
	@Test
	public void testUserAdd() {
		String loginId="zhangren";
		User user = new User();
		user.setBusiLicense("11234");
		//user.setBuyCategory("1,2,3");
		user.setCompany("���⹫˾��");
		user.setCompanyAddress("��Ԫ���Թ�˾��ַ");
		user.setEmail("afb@123.com");
		user.setGender(Gender.FEMALE);
		user.setIdCardNum("365587458755");
		user.setLoginId(loginId);
		user.setMobilePhone("12355478965");
		user.setName("��������");
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
		i.setValue("123");
		i.setLoginId(loginId);
		buyInsterests.add(i);
		user.setBuyInterests(buyInsterests);
		//������
		Assert.assertTrue(userDao.addUser(user));
		//��֤
		Assert.assertNotNull(userDao.getUserByLoginId(loginId));
		User u=userDao.getUserByLoginIdAndPass(loginId, "11234");
		Assert.assertNotNull(u);
	}

	@Test
	public void testUserUpdate() {

		String loginId="zhangren";
		User user = new User();
		user.setBusiLicense("11234");
		//user.setBuyCategory("1,2,3");
		user.setCompany("���⹫˾��");
		user.setCompanyAddress("���⹫˾��ַ");
		user.setEmail("afb@123.com");
		user.setGender(Gender.FEMALE);
		user.setIdCardNum("365587458755");
		user.setLoginId(loginId);
		user.setMobilePhone("12355478965");
		user.setName("��������");
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
		bi.setValue("123");
		bi.setLoginId(loginId);
		buyInsterests.add(bi);
		user.setBuyInterests(buyInsterests);
		//������
		userDao.addUser(user);
		User u=userDao.getUserByLoginIdAndPass(loginId, "11234");
		u.setPassword("123");
		u.setCompany("���⹫˾��");
		bi.setValue("321");
		//�༭
		u.setBuyInterests(buyInsterests);
		userDao.updateUser(u);
		u=userDao.getUserByLoginIdAndPass(loginId, "123");
		Assert.assertEquals(u.getCompany(), userDao.getUserById(u.getId()).getCompany());
		//�༭��Ʒ��
		Map<String,Object> param=new HashMap<String,Object>();
		List<String> buyInterests=new ArrayList<String>();
		buyInterests.add("321");
		param.put("buyInterests", buyInterests);
		Assert.assertNotNull(userDao.queryUser(param));
		//ɾ��
		userDao.deleteUserById(u.getId());
		Assert.assertNull(userDao.getUserByLoginId(loginId));
	}
}
