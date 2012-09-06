package com.xhm.longxin.qth.web.user.vo;

import com.xhm.longxin.qth.dal.dataobject.SaleProduct;

public class BuyProductVO extends SaleProduct {
	private String ownerStatus;
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getOwnerStatus() {
		return ownerStatus;
	}

	public void setOwnerStatus(String ownerStatus) {
		this.ownerStatus = ownerStatus;
	}

}
