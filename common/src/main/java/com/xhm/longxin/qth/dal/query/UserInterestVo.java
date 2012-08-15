/**
 *
 */
package com.xhm.longxin.qth.dal.query;

import java.util.List;

/**
 * @author ren.zhangr
 *
 */
public class UserInterestVo {
	private List<Long> buyInterestIds;
	private List<Long> saleInterestIds;
	private String loginId;
	public List<Long> getBuyInterestIds() {
		return buyInterestIds;
	}
	public void setBuyInterestIds(List<Long> buyInterestIds) {
		this.buyInterestIds = buyInterestIds;
	}
	public List<Long> getSaleInterestIds() {
		return saleInterestIds;
	}
	public void setSaleInterestIds(List<Long> saleInterestIds) {
		this.saleInterestIds = saleInterestIds;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

}
