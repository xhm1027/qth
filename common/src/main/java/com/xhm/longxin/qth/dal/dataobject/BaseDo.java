/**
 *
 */
package com.xhm.longxin.qth.dal.dataobject;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.citrus.util.StringUtil;

/**
 * @author ren.zhangr
 *
 */
public class BaseDo {

    protected Long           id;
    protected Date             gmtCreated;
    protected Date             gmtModified;
    protected String           creator;
    protected String           modifier;
    protected String           isDeleted;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGmtCreated() {
		if(gmtCreated==null||StringUtil.isEmpty(gmtCreated.toString())){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(gmtCreated);
	}
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	public String getGmtModified() {
		if(gmtModified==null||StringUtil.isEmpty(gmtModified.toString())){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(gmtModified);
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}


}
