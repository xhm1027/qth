/**
 *
 */
package com.xhm.longxin.qth.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.xhm.longxin.qth.dal.dao.AuditLogDao;
import com.xhm.longxin.qth.dal.dataobject.AuditLog;
import com.xhm.longxin.qth.dal.query.AuditLogQuery;

/**
 * @author ren.zhangr
 *
 */
public class AuditLogDaoImpl extends SqlMapClientDaoSupport implements
		AuditLogDao {

	private static final String NAMESPACE_AUDIT_LOG = "QTH_AUDIT_LOG";
	private static final String INSERT_ID = "INSERT_AUDIT_LOG";
	private static final String UPDATE_ID = "UPDATE_AUDIT_LOG";
	private static final String QUERY_BY_MAP = "QUERY_AUDIT_LOG_BYMAP";

	public boolean addAuditLog(AuditLog auditLog) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_AUDIT_LOG + "." + INSERT_ID, auditLog);
		return res > 0 ? true : false;
	}

	public List<AuditLog> query(AuditLogQuery auditLogQuery){
		List<AuditLog> logList = (List<AuditLog>) getSqlMapClientTemplate()
				.queryForList(NAMESPACE_AUDIT_LOG + "." + QUERY_BY_MAP, auditLogQuery);
		return logList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.AuditLogDao#updateAuditLog(com.xhm.longxin
	 * .qth.dal.dataobject.AuditLog)
	 */
	public boolean updateAuditLog(AuditLog auditLog) {
		Integer res = (Integer) getSqlMapClientTemplate().update(
				NAMESPACE_AUDIT_LOG + "." + UPDATE_ID, auditLog);
		return res > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xhm.longxin.qth.dal.dao.AuditLogDao#deleteAuditLogById(java.lang.
	 * Long)
	 */
	public boolean deleteAuditLogById(Long id) {
		AuditLog log = new AuditLog();
		log.setId(id);
		log.setIsDeleted("y");
		Integer res = (Integer) getSqlMapClientTemplate().delete(
				NAMESPACE_AUDIT_LOG + "." + UPDATE_ID, log);
		return res > 0 ? true : false;
	}

}
