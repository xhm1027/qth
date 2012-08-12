/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.testng.annotations.BeforeClass;

/**
 * @author ren.zhangr
 *
 */
public class BaseDaoTest {

	protected UserDao userDao;
	protected AdminUserDao adminUserDao;
	protected AttachmentDao attachmentDao;
	protected AuditLogDao auditLogDao;
	protected BuyProductDao buyProductDao;
	protected SaleProductDao saleProductDao;
	protected MessageDao messageDao;
	protected ProductCategoryDao productCategoryDao;

	public BaseDaoTest() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"classpath:spring/biz-dal.xml");
		try {
			userDao = (UserDao) ctx.getBean("userDao");
			adminUserDao = (AdminUserDao) ctx.getBean("adminUserDao");

			attachmentDao = (AttachmentDao) ctx.getBean("attachmentDao");
			auditLogDao = (AuditLogDao) ctx.getBean("auditLogDao");
			buyProductDao = (BuyProductDao) ctx.getBean("buyProductDao");
			saleProductDao = (SaleProductDao) ctx.getBean("saleProductDao");
			messageDao = (MessageDao) ctx.getBean("messageDao");
			productCategoryDao = (ProductCategoryDao) ctx
					.getBean("productCategoryDao");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
