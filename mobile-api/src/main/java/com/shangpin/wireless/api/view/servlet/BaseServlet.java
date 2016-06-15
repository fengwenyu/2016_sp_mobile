package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shangpin.wireless.api.util.WebUtil;

public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = -3841302159923488126L;
	static WebApplicationContext ctx = null;

	private void initWebApplicationContext() {
		ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object getBean(Class cls) {
		if (ctx == null) {
			initWebApplicationContext();
		}
		return ctx.getBean(cls);
	}
	
	/**
	 * 返回错误信息
	 * 
	 * @param response
	 * @param errorType
	 *            params:请求参数不能为空 api:服务器的错误信息
	 */
	protected void responseError(HttpServletResponse response, String errorType) {
		try {
			if ("params".equals(errorType)) {
				WebUtil.sendErrorInvalidParams(response);
			} else {
				WebUtil.sendApiException(response);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 返回处理结果
	 * 
	 * @param msg
	 *            发送的消息
	 * @throws IOException
	 */
	protected void sendMessage(HttpServletResponse response, String msg) throws IOException {
		String strHtml = msg;
		PrintWriter out = response.getWriter();
		out.println(strHtml);
		out.flush();
		out.close();

	}
}
