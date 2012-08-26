/**
 *
 */
package com.xhm.longxin.qth.dal.query;

import java.util.List;

/**
 * @author ren.zhangr
 *
 */
public class SaleProductQuery extends QueryObject{
	private String name;
	private String status;
	private String owner;
	private String isSale;
	private String productType;
	private List<Long> categoryIds;
	private Long id;
	private Boolean orderByPrice=null;



	public Boolean getOrderByPrice() {
		return orderByPrice;
	}
	public void setOrderByPrice(Boolean orderByPrice) {
		this.orderByPrice = orderByPrice;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getIsSale() {
		return isSale;
	}
	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}

}
