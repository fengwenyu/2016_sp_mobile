package com.shangpin.mobileAolai.platform.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码业务逻辑接口
 * 
 * @author yumeng
 */
public interface CaptchaService {
	/**
	 * 验证码内容验证
	 * 
	 * @param captcha
	 *            验证码内容
	 * @return true-验证码正确
	 */
	public boolean verifyCaptcha(HttpSession session, String captcha);

	public void writeImage(HttpSession session, HttpServletResponse resp);

	public boolean verifyCaptcha(HttpSession session, String captcha, String name);
}
