/**
 *
 */
package com.xhm.longxin.qth.dal.dataobject;

/**
 * @author ren.zhangr
 *
 */
public class Attachment extends BaseDo {
	private String type;
	private String path;
	private Long ownerId;
	private String key;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

}
