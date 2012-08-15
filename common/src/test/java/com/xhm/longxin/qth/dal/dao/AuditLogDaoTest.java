/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import junit.framework.Assert;

import org.jtester.annotations.SpringBeanByName;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.xhm.longxin.qth.dal.constant.AuditResult;
import com.xhm.longxin.qth.dal.constant.AuditType;
import com.xhm.longxin.qth.dal.dataobject.AuditLog;
import com.xhm.longxin.qth.dal.query.AuditLogQuery;

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
		AuditLogQuery auditLogQuery = new AuditLogQuery();
		auditLogQuery.setAuditType(AuditType.USER);
		auditLogQuery.setAuditId(1L);
		log = auditLogDao.query(auditLogQuery).get(0);
		log.setAuditResult(AuditResult.FAIL);
		//
		auditLogDao.updateAuditLog(log);
		Assert.assertEquals(AuditResult.FAIL, auditLogDao.query(auditLogQuery)
				.get(0).getAuditResult());
	}

}
