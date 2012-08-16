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
	private Long id;

	private String isMaterial;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
