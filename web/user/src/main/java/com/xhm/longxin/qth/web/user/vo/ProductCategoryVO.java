/**
 *
 */
package com.xhm.longxin.qth.web.user.vo;

import com.xhm.longxin.qth.dal.dataobject.ProductCategory;

/**
 * @author ren.zhangr
 *
 */
public class ProductCategoryVO extends ProductCategory{
	private boolean isChecked=false;

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}


}
