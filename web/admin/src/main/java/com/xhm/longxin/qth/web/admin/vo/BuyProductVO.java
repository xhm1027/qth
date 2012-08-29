package com.xhm.longxin.qth.web.admin.vo;

import com.xhm.longxin.qth.dal.dataobject.SaleProduct;

public class BuyProductVO extends SaleProduct {
	private String company;
	private String category;
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


}
