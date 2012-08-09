package com.xhm.longxin.qth.web.user.module.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import com.alibaba.citrus.turbine.Context;
import com.xhm.longxin.qth.web.user.common.UserConstant;

public class CheckCode {

	@Resource
	private HttpSession session;

	/**
	 * ��֤��ͼƬ�Ŀ�ȡ�
	 */
	private int width = 80;

	/**
	 * ��֤��ͼƬ�ĸ߶ȡ�
	 */
	private int height = 20;

	/**
	 * ��֤���ַ�����
	 */
	private int codeCount = 4;

	/**
	 * xx
	 */
	private int xx = 0;

	/**
	 * ����߶�
	 */
	private int fontHeight;

	/**
	 * codeY
	 */
	private int codeY;

	/**
	 * codeSequence
	 */
	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * ��ʼ����֤ͼƬ����
	 */
	public void init(){
		xx = width / (codeCount + 1);
		fontHeight = height - 2;
		codeY = height - 4;

	}

	public void execute(HttpServletResponse response, Context context)
			throws IOException {
		init();
		// ����ͼ��buffer
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D gd = buffImg.createGraphics();

		// ����һ���������������
		Random random = new Random();

		// ��ͼ�����Ϊ��ɫ
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, width, height);

		// �������壬����Ĵ�СӦ�ø���ͼƬ�ĸ߶�������
		Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
		// �������塣
		gd.setFont(font);

		// ���߿�
		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, width - 1, height - 1);

		// �������80�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽��
		gd.setColor(Color.BLACK);
		for (int i = 0; i < 80; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}

		// randomCode���ڱ��������������֤�룬�Ա��û���¼�������֤��
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;

		// �������codeCount���ֵ���֤�롣
		for (int i = 0; i < codeCount; i++) {
			// �õ������������֤�����֡�
			String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
			// �����������ɫ������������ɫֵ�����������ÿλ���ֵ���ɫֵ������ͬ��
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);

			// �������������ɫ����֤����Ƶ�ͼ���С�
			gd.setColor(new Color(red, green, blue));
			gd.drawString(strRand, (i + 1) * xx, codeY);

			// ���������ĸ�����������һ��
			randomCode.append(strRand);
		}

		session.setAttribute(UserConstant.VALIDATE_CODE, randomCode.toString());

		// ��ֹͼ�񻺴档
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType("image/jpeg");

		// ��ͼ�������Servlet������С�
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();

	}
}
