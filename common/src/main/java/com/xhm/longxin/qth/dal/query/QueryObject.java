/**
 *
 */
package com.xhm.longxin.qth.dal.query;

/**
 * @author ren.zhangr
 *
 */
public class QueryObject {

    private Integer pageStart;
    private Integer pageSize;
    public static final int maxPageSize=100;
    public static final int defaultPageSize=20;
	public Integer getPageStart() {
		return pageStart;
	}
	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
