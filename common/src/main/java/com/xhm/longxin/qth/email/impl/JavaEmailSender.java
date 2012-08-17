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
	private static final String RESET_PASS_EMAIL_TITLE = "��̨��ϵͳ��������";
	private static final String RESET_PASS_EMAIL_CONTENT = "�𾴵��û����ã�<br>������̨����Ϣ����ϵͳ��ע��ĵ�¼��ΪLOGINID���˺���������ΪNEWPASS��������ʱ��¼ϵͳ�޸����벢���Ʊ��ܣ�ף����죡<br>"
			+ new Date();

	public boolean sendPasswordResetEmail(User user, String newPass) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(mailServerHost);
		mailInfo.setMailServerPort(mailServerPort);
		mailInfo.setValidate(validate);
		mailInfo.setUserName(userName);
		mailInfo.setPassword(password);// ������������
		mailInfo.setFromAddress(userName);
		mailInfo.setToAddress(user.getEmail());
		mailInfo.setSubject(RESET_PASS_EMAIL_TITLE);
		String emailContent = RESET_PASS_EMAIL_CONTENT;
		emailContent = emailContent.replace("LOGINID", user.getLoginId());
		emailContent = emailContent.replace("NEWPASS", newPass);
		mailInfo.setContent(emailContent);
		return sendHtmlMail(mailInfo);// ����html��ʽ
	}

	/**
	 * ��HTML��ʽ�����ʼ�
	 *
	 * @param mailInfo
	 *            �����͵��ʼ���Ϣ
	 */
	private boolean sendHtmlMail(MailSenderInfo mailInfo) {
		// �ж��Ƿ���Ҫ�����֤
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// �����Ҫ�����֤���򴴽�һ��������֤��
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// ����session����һ���ʼ���Ϣ
			Message mailMessage = new MimeMessage(sendMailSession);
			// �����ʼ������ߵ�ַ
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// �����ʼ���Ϣ�ķ�����
			mailMessage.setFrom(from);
			// �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO���Ա�ʾ�����ߵ�����ΪTO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// �����ʼ���Ϣ������
			mailMessage.setSubject(mailInfo.getSubject());
			// �����ʼ���Ϣ���͵�ʱ��
			mailMessage.setSentDate(new Date());
			// MiniMultipart����һ�������࣬����MimeBodyPart���͵Ķ���
			Multipart mainPart = new MimeMultipart();
			// ����һ������HTML���ݵ�MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// ����HTML����
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// ��MiniMultipart��������Ϊ�ʼ�����
			mailMessage.setContent(mainPart);
			// �����ʼ�
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
