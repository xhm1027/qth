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
	 * 通过审核类型及id查询审核记录，不需要分页，按时间降序
	 * */
	public List<AuditLog> getAuditLogByTypeAndId(String auditType, Long auditId);

	/**
	 * 新增审核记录
	 * */
	public boolean addAuditLog(AuditLog auditLog);

	/**
	 * 更新审核记录审核记录
	 * */
	public boolean updateAuditLog(AuditLog auditLog);

	/**
	 * 删除审核记录
	 * */
	public boolean deleteAuditLogById(Long id);


}
