/**
 *
 */
package com.xhm.longxin.qth.dal.dataobject;

import java.io.Serializable;
import java.util.List;

/**
 * @author ren.zhangr
 *
 */
public class User extends BaseDo implements Serializable {
	private static final long serialVersionUID = -7733273188478856191L;
	private String name;
	private String loginId;
	private String password;
	private String gender;
	private String email;
	private String phoneArea;
	private String phoneNumber;
	private String mobilePhone;
	private String company;
	private String companyAddress;
	private String role;
	private String idCardNum;
	private String busiLicense;
	private String userLevel;
	private String status;
	private List<UserInterest> buyInterests;
	private List<UserInterest> saleInterests;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneArea() {
		return phoneArea;
	}

	public void setPhoneArea(String phoneArea) {
		this.phoneArea = phoneArea;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getBusiLicense() {
		return busiLicense;
	}

	public void setBusiLicense(String busiLicense) {
		this.busiLicense = busiLicense;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<UserInterest> getBuyInterests() {
		return buyInterests;
	}

	public void setBuyInterests(List<UserInterest> buyInterests) {
		this.buyInterests = buyInterests;
	}

	public List<UserInterest> getSaleInterests() {
		return saleInterests;
	}

	public void setSaleInterests(List<UserInterest> saleInterests) {
		this.saleInterests = saleInterests;
	}

}
