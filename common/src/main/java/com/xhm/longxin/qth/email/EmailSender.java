/**
 *
 */
package com.xhm.longxin.qth.email;

import com.xhm.longxin.qth.dal.dataobject.User;

/**
 * @author ren.zhangr
 *
 */
public interface EmailSender {

	public static final String EMAIL_SEND_ERR="email_send_error";
	public boolean sendPasswordResetEmail(User user,String newPass);
}
