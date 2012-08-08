package com.xhm.longxin.biz.user.vo;

import java.io.Serializable;
/**
 * 
 * @author xhm.xuhm
 *
 */
public class LoginVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1774919689100859503L;
	private String name;
	private String password;
	private String validateStr;
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
	public String getValidateStr() {
		return validateStr;
	}
	public void setValidateStr(String validateStr) {
		this.validateStr = validateStr;
	}
}
