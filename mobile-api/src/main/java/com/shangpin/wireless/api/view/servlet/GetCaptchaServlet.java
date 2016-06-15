package com.shangpin.wireless.api.view.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.shangpin.biz.utils.ApiBizData;

/**
 * 获取图片验证码
 * 
 * @author liling
 */
public class GetCaptchaServlet extends BaseServlet {

	private static final long serialVersionUID = -7443091457045184976L;
	protected final Log log = LogFactory.getLog(GetCaptchaServlet.class);
	
	private ImageCaptchaService imageCaptchaService;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		imageCaptchaService = (ImageCaptchaService) getBean(ImageCaptchaService.class);
	}

	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
		String captchaId = httpServletRequest.getHeader("imei");
		genernateCaptchaImage(httpServletRequest, captchaId, httpServletResponse);
	}

	/**
	 * 生成验证码图片.
	 */
	private void genernateCaptchaImage(HttpServletRequest request, String captchaId, HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		try {

//			BufferedImage challenge = (BufferedImage) CaptchaServiceSingleton.getInstance().getChallengeForID(captchaId, request.getLocale());
			if (!ApiBizData.invalidKey(captchaId)) {
				ApiBizData.deleteKey(captchaId);
			}
			
			BufferedImage challenge = imageCaptchaService.getImageChallengeForID(captchaId, request.getLocale());
			ImageIO.write(challenge, "jpg", out);
			out.flush();
		} catch (CaptchaServiceException e) {
			e.printStackTrace();
			log.error("GetCaptchaServlet:" + e);
		} finally {
			out.close();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
