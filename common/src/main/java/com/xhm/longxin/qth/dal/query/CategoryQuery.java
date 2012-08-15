/**
 *
 */
package com.xhm.longxin.qth.dal.query;

/**
 * @author ren.zhangr
 *
 */
public class CategoryQuery extends QueryObject{

	private String name;

	private String isMaterial;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsMaterial() {
		return isMaterial;
	}

	public void setIsMaterial(String isMaterial) {
		this.isMaterial = isMaterial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
