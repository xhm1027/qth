/**
 *
 */
package com.xhm.longxin.qth.vo;

import com.xhm.longxin.qth.dal.dataobject.ProductCategory;

/**
 * @author xhm
 *
 */
public class InterestCategoryVO  {

	private boolean has;
	private ProductCategory category;

	public boolean isHas() {
		return has;
	}

	public void setHas(boolean has) {
		this.has = has;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	

}
