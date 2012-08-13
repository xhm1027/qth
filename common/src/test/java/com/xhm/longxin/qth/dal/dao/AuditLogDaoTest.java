/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import java.util.List;

import junit.framework.Assert;

import org.jtester.annotations.SpringBeanByName;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.xhm.longxin.qth.dal.constant.AuditResult;
import com.xhm.longxin.qth.dal.constant.AuditType;
import com.xhm.longxin.qth.dal.dataobject.AuditLog;

/**
 * @author ren.zhangr
 *
 */
public class AuditLogDaoTest extends BaseDaoTest {
	@SpringBeanByName
	private AuditLogDao auditLogDao;


	@BeforeMethod
	public void setUp() {
		db.table("qth_audit_log").clean().commit();
	}

	@Test
	public void testAddAndQueryAuditLog() {
		AuditLog log = new AuditLog();
		log.setAuditId(1L);
		log.setAuditor("test auditor");
		log.setAuditResult(AuditResult.PASS);
		log.setAuditType(AuditType.USER);
		log.setDescription("desc");
		//
		Assert.assertTrue(auditLogDao.addAuditLog(log));
		//
		log = auditLogDao.getAuditLogByTypeAndId(AuditType.USER, 1L).get(0);
		log.setAuditResult(AuditResult.FAIL);
		//
		auditLogDao.updateAuditLog(log);
		Assert.assertEquals(AuditResult.FAIL, auditLogDao
				.getAuditLogByTypeAndId(AuditType.USER, 1L).get(0)
				.getAuditResult());
	}

}
