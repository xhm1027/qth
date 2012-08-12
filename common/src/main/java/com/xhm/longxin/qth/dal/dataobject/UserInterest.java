/**
 *
 */
package com.xhm.longxin.qth.dal.dataobject;

import java.io.Serializable;

/**
 * @author ren.zhangr
 *
 */
public class UserInterest extends BaseDo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7413848867690528240L;

	private String loginId;
	private String interest;
	private String value;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
