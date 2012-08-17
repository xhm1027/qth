/**
 *
 */
package com.xhm.longxin.biz.user.vo;

/**
 * @author ren.zhangr
 *
 */
public class UserAuditVO {

	private Long id;
	private String description;
	private String auditResult;
	private String auditor;

	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

}
