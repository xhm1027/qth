/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import java.util.List;

import com.xhm.longxin.qth.dal.dataobject.AuditLog;
import com.xhm.longxin.qth.dal.query.AuditLogQuery;
/**
 * @author ren.zhangr
 *
 */
public interface AuditLogDao {
	/**
	 * ͨ��������ͼ�id��ѯ��˼�¼������Ҫ��ҳ����ʱ�併��
	 * */
	public List<AuditLog> query(AuditLogQuery auditLogQuery);

	/**
	 * ������˼�¼
	 * */
	public boolean addAuditLog(AuditLog auditLog);

	/**
	 * ������˼�¼��˼�¼
	 * */
	public boolean updateAuditLog(AuditLog auditLog);

	/**
	 * ɾ����˼�¼
	 * */
	public boolean deleteAuditLogById(Long id);


}
