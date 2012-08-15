/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import java.util.ArrayList;
import java.util.List;
import org.jtester.annotations.SpringBeanByName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.xhm.longxin.qth.dal.constant.Gender;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.constant.UserInterestType;
import com.xhm.longxin.qth.dal.constant.UserLevel;
import com.xhm.longxin.qth.dal.constant.UserRole;
import com.xhm.longxin.qth.dal.constant.UserStatus;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.dataobject.UserInterest;
import com.xhm.longxin.qth.dal.query.UserQuery;

/**
 * @author ren.zhangr
 *
 */
public class UserDaoTest extends BaseDaoTest{
	@SpringBeanByName
	private UserDao userDao;
	@BeforeClass
	public void setUp(){

		db.table("qth_user").clean().insert(6, new DataMap() {
			{
				this.put("login_id", DataGenerator.repeat("zhangren","xhm","admin","zhangren1","xhm1","admin1"));
				this.put("name", DataGenerator.repeat("姓名1","姓名2","姓名3","姓名4","姓名5","姓名6"));
				this.put("gender",Gender.FEMALE );
				this.put("email", "abc@abc.com");
				this.put("phone_area", "0571");
				this.put("phone_number", "85022088");
				this.put("company", DataGenerator.repeat("公司1","公司2","公司3","公司4","公司5","公司6"));
				this.put("role", UserRole.INNER_USER);
				this.put("user_level", UserLevel.GOLDEN);
				this.put("status", UserStatus.NEW);
				this.put("is_deleted", IS.N);
			}
		}).commit();
		db.table("qth_pro_category").clean().insert(3, new DataMap() {
			{
				this.put("id",DataGenerator.repeat(1,2,3));
				this.put("name", DataGenerator.repeat("测试类别1","测试类别2","测试类别3"));
				this.put("description", "描述信息xxx");
				this.put("is_material", DataGenerator.repeat("Y","Y","N"));
				this.put("is_deleted", "N");
			}
		}).commit();
		db.table("qth_user_interest").clean().insert(7, new DataMap() {
			{
				this.put("login_id", DataGenerator.repeat("zhangren","zhangren","zhangren","xhm1","xhm1","xhm1","xhm"));
				this.put("interest", DataGenerator.repeat("buy","buy","sale"));
				this.put("value", DataGenerator.repeat(1,2,3));
				this.put("is_deleted", IS.N);
			}
		}).commit();
	}

	@Test
	public void testUserAdd() {
		String loginId="addzhangren";
		User user = new User();
		user.setBusiLicense("11234");
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
		want.bool(userDao.addUser(user)).eq(true);
		//验证
		want.object(userDao.getUserByLoginId(loginId)).notNull();
		//验证登录功能
		User loginUser=userDao.getUserByLoginIdAndPass(loginId, "11234");
		want.object(loginUser).notNull();
		//验证关联功能（是否插入，是否能查询出来）
		List<UserInterest> buyInterests=loginUser.getBuyInterests();
		want.object(buyInterests).notNull();
		want.number(buyInterests.size()).eq(1);
		UserInterest bi=buyInterests.get(0);
		want.string(bi.getName()).isEqualTo("测试类别1");
	}

	@Test
	public void testQueryUser() {
		UserQuery userQuery=new UserQuery();
		List<Long> interestIds=new ArrayList<Long>();
		//买的产品对类别1感兴趣的，有3个zhangren和xhm1和xhm
		interestIds.add(1L);
		userQuery.setBuyInterestIds(interestIds);
		List<User> userList=userDao.query(userQuery);
		want.number(userList.size()).eq(3);
		//对产品1或2感兴趣的，也有3个，结果不应该是6，而应该去重后的3
		interestIds.add(2L);
		userQuery.setBuyInterestIds(interestIds);
		userList=userDao.query(userQuery);
		want.number(userList.size()).eq(3);
		want.number(userDao.queryCount(userQuery)).eq(3);
		//测试分页
		userList=userDao.query(userQuery,0,1);
		want.number(userList.size()).eq(1);

		//对卖产品3感兴趣的有2个：xhm1和zhangren
		List<Long> saleInterestIds=new ArrayList<Long>();
		saleInterestIds.add(3L);
		userQuery.setSaleInterestIds(saleInterestIds);
		//参数清空掉
		userQuery.setBuyInterestIds(null);
		userQuery.setPageStart(null);
		userQuery.setPageSize(null);
		userList=userDao.query(userQuery);
		want.number(userList.size()).eq(2);
		want.number(userDao.queryCount(userQuery)).eq(2);
	}
}
