/**
 *
 */
package com.xhm.longxin.qth.email.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.email.EmailSender;
import com.xhm.longxin.qth.email.MailSenderInfo;
import com.xhm.longxin.qth.email.MyAuthenticator;

/**
 * @author ren.zhangr
 *
 */
public class JavaEmailSender implements EmailSender {
	private String mailServerHost;
	private String mailServerPort;
	private boolean validate = true;
	private String userName;
	private String password;
	private static final String RESET_PASS_EMAIL_TITLE = "七台河系统密码重置";
	private static final String RESET_PASS_EMAIL_CONTENT = "尊敬的用户您好：<br>您在七台河信息发布系统中注册的登录名为LOGINID的账号密码重置为NEWPASS，请您及时登录系统修改密码并妥善保管，祝您愉快！<br>"
			+ new Date();

	public boolean sendPasswordResetEmail(User user, String newPass) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(mailServerHost);
		mailInfo.setMailServerPort(mailServerPort);
		mailInfo.setValidate(validate);
		mailInfo.setUserName(userName);
		mailInfo.setPassword(password);// 您的邮箱密码
		mailInfo.setFromAddress(userName);
		mailInfo.setToAddress(user.getEmail());
		mailInfo.setSubject(RESET_PASS_EMAIL_TITLE);
		String emailContent = RESET_PASS_EMAIL_CONTENT;
		emailContent = emailContent.replace("LOGINID", user.getLoginId());
		emailContent = emailContent.replace("NEWPASS", newPass);
		mailInfo.setContent(emailContent);
		return sendHtmlMail(mailInfo);// 发送html格式
	}

	/**
	 * 以HTML格式发送邮件
	 *
	 * @param mailInfo
	 *            待发送的邮件信息
	 */
	private boolean sendHtmlMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
