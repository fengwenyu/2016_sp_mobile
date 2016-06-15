package com.shangpin.mobileShangpin.platform.service.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.octo.captcha.service.image.ImageCaptchaService;
import com.shangpin.mobileShangpin.platform.service.CaptchaService;

@Service("captchaService")
public class CaptchaServiceImpl implements CaptchaService {
	private static final String CAPTCHA_NAME = "verifyCode";
	@Autowired
	private ImageCaptchaService imageCaptchaService;

	@Override
	public boolean verifyCaptcha(HttpSession session, String captcha) {
		return this.verifyCaptcha(session, captcha, "");
	}

	@Override
	public void writeImage(HttpSession session, HttpServletResponse resp) {
		BufferedImage image = (BufferedImage) imageCaptchaService.getChallengeForID(session.getId() + CAPTCHA_NAME);
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		OutputStream output;
		try {
			output = resp.getOutputStream();
			// 输出图象到页面
			ImageIO.write(image, "JPEG", output);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean verifyCaptcha(HttpSession session, String captcha, String name) {
		boolean flag = true;
		try {
			flag = imageCaptchaService.validateResponseForID(session.getId() + CAPTCHA_NAME + name, captcha.toUpperCase());
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}
