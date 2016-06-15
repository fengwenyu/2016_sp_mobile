
package com.shangpin.wireless.api.pay.alipay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.shangpin.wireless.api.api2client.domain.CommonAPIResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.StringUtil;

/**
 * 拼接支付宝登录数据
 * 
 * @Author:wangwenguan
 * @CreatDate: 2013-08-05
 */
public class GetAlipayLoginDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String alipayuid = request.getParameter("alipayuserid");

		StringBuilder digestBuff = new StringBuilder();
		digestBuff.append("app_name=\"mc\"&biz_type=\"trust_login");
		if (StringUtil.isNotEmpty(alipayuid)) {
			digestBuff.append("\"&app_userid=\"");
			digestBuff.append(alipayuid);
		}
		digestBuff.append("\"&partner=\"");
		digestBuff.append(PartnerConfigAlipay.PARTNER);
		digestBuff.append("\"&app_id=\"");
		digestBuff.append(PartnerConfigAlipay.APP_ID);
		digestBuff.append("\"&notify_url=\"");
		String notifyUrl = Constants.BASE_API +"alipaylogin_notify";
		digestBuff.append(notifyUrl);
		digestBuff.append("\"");
		final String digest = digestBuff.toString();
		String signature = RSASignatureAlipay.sign(digest);
		StringBuffer strBuff = new StringBuffer(digest);
		strBuff.append("&sign_type=\"RSA\"&sign=\"");
		strBuff.append(java.net.URLEncoder.encode(signature, "UTF-8"));
		strBuff.append("\"");

		CommonAPIResponse apiResponse = new CommonAPIResponse();
		JSONObject content = new JSONObject();
		content.put("data", strBuff.toString());
		apiResponse.setContent(content);
		response.getWriter().print(apiResponse.obj2Json());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
