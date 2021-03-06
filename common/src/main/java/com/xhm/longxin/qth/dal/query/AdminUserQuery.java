/**
 *
 */
package com.xhm.longxin.qth.dal.query;

/**
 * @author ren.zhangr
 *
 */
public class AdminUserQuery extends QueryObject {
	private String name;
	private String password;
	private String loginId;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
