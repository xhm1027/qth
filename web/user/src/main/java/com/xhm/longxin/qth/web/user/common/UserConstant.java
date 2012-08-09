package com.xhm.longxin.qth.web.user.common;

public interface UserConstant {
	public final String QTH_USER_SESSION_KEY = "qthUser";
	
	/** 如果未指定return，登录以后就跳到该URL。 */
    public final String LOGIN_RETURN_DEFAULT_LINK = "qthHomeLink";
    
    /** 图片验证码 **/
    public final String VALIDATE_CODE="validateCode";
}
