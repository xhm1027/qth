/**
 *
 */
package com.xhm.longxin.qth.dal.dataobject;

/**
 * @author ren.zhangr
 *
 */
public class ProductCategory  extends BaseDo{

	private String name;
	private String description;
	private String isMaterial;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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

}
