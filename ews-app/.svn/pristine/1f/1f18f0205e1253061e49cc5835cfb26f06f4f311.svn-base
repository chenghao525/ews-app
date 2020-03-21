package com.corp.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.corp.oa.util.G;
import com.corp.oa.util.RandomNumberString;

/*
 * 生成验证码
 */
@SuppressWarnings("serial")
public class ValidateNumberServlet extends HttpServlet {
	protected final Log logger = LogFactory.getLog(getClass());

	public static final String SESS_VALIDATE_NUM = "ValidateNumber";

	@Override
	public void init(ServletConfig config) throws ServletException {
		ImageIO.setUseCache(false);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set to expire far in the past.
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");

		response.setContentType("image/jpeg");
		String validateNum = RandomNumberString.generateTextCode(RandomNumberString.TYPE_NUM_ONLY,
				5, null); // genValidateNumber();
		OutputStream os = response.getOutputStream();

		request.getSession().setAttribute(ValidateNumberServlet.SESS_VALIDATE_NUM, validateNum);
		if (logger.isDebugEnabled()) {
			logger.debug("generated a validate number:" + validateNum);
		}

		int width = 80, height = 30;
		String w = G.nvl(request.getParameter("w"), "80");
		String h = G.nvl(request.getParameter("h"), "30");
		try {
			width = Integer.parseInt(w);
			height = Integer.parseInt(h);
		} catch (NumberFormatException e) {
			logger.warn("invalid input width and height");
		}
		createImage(validateNum, os, width, height);
		os.close();
	}

	/*
	 * 生成图像
	 */
	protected boolean createImage(String validateNum, OutputStream os, int width, int height) {
		Font font = new Font("Verdana", Font.PLAIN, 20);

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(new Color(240, 240, 240));
		g.fillRect(0, 0, width, height);

		// 随机产生255条干扰线，使图象中的认证码不易被其它程序探测到
		Random random = new Random();
		g.setColor(getRandColor(random, 160, 200));
		for (int i = 0; i < 350; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.setColor(new Color(x % 200, y % 200, (xl + yl) % 200));
			g.drawLine(x, y, x + xl, y + yl);
		}

		g.setColor(new Color(255, 255, 255));
		g.setFont(font);
		for (int i = 0; i < validateNum.length(); i++) {
			g.drawString(validateNum.substring(i, i + 1), 5 + 7 * (i + i), 22);
		}
		g.dispose();
		try {
			return ImageIO.write(image, "jpeg", os);
		} catch (IOException e) {
			logger.warn("create image error!", e);
			return false;
		}
	}

	private Color getRandColor(Random random, int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
