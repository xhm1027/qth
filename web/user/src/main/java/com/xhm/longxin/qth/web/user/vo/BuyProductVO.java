package com.xhm.longxin.qth.web.user.vo;

import com.xhm.longxin.qth.dal.dataobject.SaleProduct;

public class BuyProductVO extends SaleProduct {
	private String ownerStatus;

	public String getOwnerStatus() {
		return ownerStatus;
	}

	public void setOwnerStatus(String ownerStatus) {
		this.ownerStatus = ownerStatus;
	}

}
