/**
 *
 */
package com.xhm.longxin.qth.dal.dataobject;

import java.util.List;

/**
 * @author ren.zhangr
 *
 */
public class SaleProduct extends BaseDo {
	private String name;
	private Long categoryId;
	private Long quantity;
	private String unit;
	private Double price;
	private Double lowestDealSize;
	private String status;
	private String owner;
	private String productType;
	private String isSale;
	private List<Attachment> imgs;

	public List<Attachment> getImgs() {
		return imgs;
	}

	public void setImgs(List<Attachment> imgs) {
		this.imgs = imgs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getLowestDealSize() {
		return lowestDealSize;
	}

	public void setLowestDealSize(Double lowestDealSize) {
		this.lowestDealSize = lowestDealSize;
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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getIsSale() {
		return isSale;
	}

	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}
}
