package com.xhm.longxin.qth.web.admin.common;

public interface AdminConstant {
	public final String QTH_ADMIN_SESSION_KEY = "qthAdmin";
	
	/** 如果未指定return，登录以后就跳到该URL。 */
    String LOGIN_ADMIN_LOGIN_LINK = "qthAdminLoginLink";
    
    /** Login页面返回URL的key。 */
    String LOGIN_RETURN_KEY = "return";
    
    /** 如果未指定return，登录以后就跳到该URL。 */
    String LOGIN_RETURN_DEFAULT_LINK = "qthAdminHomeLink";
}
