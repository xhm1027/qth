/**
 *
 */
package com.xhm.longxin.qth.dal.query;

import java.util.List;

/**
 * @author ren.zhangr
 *
 */
public class AttachmentUpdateVo {

	private List<Long> imgIds;
	private String key;
	private String type;
	private Long ownerId;

	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public List<Long> getImgIds() {
		return imgIds;
	}
	public void setImgIds(List<Long> imgIds) {
		this.imgIds = imgIds;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
