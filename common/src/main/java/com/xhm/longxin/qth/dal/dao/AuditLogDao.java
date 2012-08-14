/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import java.util.List;

import com.xhm.longxin.qth.dal.dataobject.AuditLog;

/**
 * @author ren.zhangr
 *
 */
public interface AuditLogDao {
	/**
	 * ͨ��������ͼ�id��ѯ��˼�¼������Ҫ��ҳ����ʱ�併��
	 * */
	public List<AuditLog> getAuditLogByTypeAndId(String auditType, Long auditId);

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
