package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.octo.captcha.service.image.ImageCaptchaService;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

public class VerifyCaptchaServlet extends BaseServlet {

	private static final long serialVersionUID = -7443091457045184976L;
	protected final Log log = LogFactory.getLog(GetCaptchaServlet.class);
	
	private ImageCaptchaService imageCaptchaService;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		imageCaptchaService = (ImageCaptchaService) getBean(ImageCaptchaService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String productNo = "g";
		final String imei = request.getHeader("imei");
		String responsestr = request.getParameter("captcha");
		final String channelNo = ChannelNoUtil.getChannelNo(request);// 获取渠道号
		if (StringUtil.isNotEmpty(imei, productNo, responsestr)) {
			Boolean isResponseCorrect = Boolean.FALSE;

			try {
				JSONObject obj = new JSONObject();
				JSONObject content = new JSONObject();
//				isResponseCorrect = CaptchaServiceSingleton.getInstance().validateResponseForID(imei, responsestr.toUpperCase());
				isResponseCorrect = imageCaptchaService.validateResponseForID(imei, responsestr.toUpperCase());
				if (isResponseCorrect) {
					obj.put("code", "0");
					obj.put("msg", "");
				} else {
					obj.put("code", "1");
					obj.put("msg", "您输入验证码不正确，请重新输入");
				}
				obj.put("content", content == null ? new JSONObject() : content);
				response.getWriter().print(obj.toString());
			} catch (Exception e) {
				e.printStackTrace();
				log.error("VerifyCaptchaServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			FileUtil.addLog(request, "verifyCaptcha", channelNo, "imei", imei, "productNo", productNo, "captcha", responsestr);
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
