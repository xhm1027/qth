/**
 *
 */
package com.xhm.longxin.qth.dal.dataobject;

import java.io.Serializable;

/**
 * @author ren.zhangr
 *
 */
public class AdminUser  extends BaseDo implements Serializable{
	private static final long serialVersionUID = -7733273188478856191L;
	private String   name;
    private String   password;
    private String loginId;
    private String email;

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
}
