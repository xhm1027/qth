/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import java.util.List;

import junit.framework.Assert;

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
	@BeforeMethod
	public void setUp() {
		List<AuditLog> logs = auditLogDao.getAuditLogByTypeAndId("user", 1L);
		for (AuditLog log : logs) {
			// 删
			auditLogDao.deleteAuditLogById(log.getId());
		}
		Assert.assertEquals(auditLogDao.getAuditLogByTypeAndId("user", 1L)
				.size(), 0);
	}

	@Test
	public void testAddAndQueryAuditLog() {
		AuditLog log = new AuditLog();
		log.setAuditId(1L);
		log.setAuditor("test auditor");
		log.setAuditResult(AuditResult.PASS);
		log.setAuditType(AuditType.USER);
		log.setDescription("描述信息");
		// 增
		Assert.assertTrue(auditLogDao.addAuditLog(log));
		// 查
		log = auditLogDao.getAuditLogByTypeAndId(AuditType.USER, 1L).get(0);
		log.setAuditResult(AuditResult.FAIL);
		//改
		auditLogDao.updateAuditLog(log);
		Assert.assertEquals(AuditResult.FAIL, auditLogDao
				.getAuditLogByTypeAndId(AuditType.USER, 1L).get(0)
				.getAuditResult());
	}

}
