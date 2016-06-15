package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.biz.service.ASPBizIndexService;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

public class AppIndexSecondServlet extends BaseServlet {

	private static final long serialVersionUID = 5430003526925943465L;

	protected final Log log = LogFactory.getLog(AppIndexSecondServlet.class);

	ASPBizIndexService aspBizIndexService;

	@Override
	public void init() throws ServletException {
		aspBizIndexService = (ASPBizIndexService) getBean(ASPBizIndexService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String productNo = request.getHeader("p");
		final String imei = request.getHeader("imei");
		final String userId = request.getParameter("userId");
		final String channelNo = ChannelNoUtil.getChannelNo(request);// 获取渠道号
		if (StringUtil.isNotEmpty(imei)) {
			try {
				String bizData = aspBizIndexService.getHomeSecond(userId);
				response.getWriter().print(bizData);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("AppIndexSecondServlet:" + e);
				returnError(response, "api");
			}
			// 记录访问日志
			FileUtil.addLog(request, "AppIndexSecondServlet", channelNo, "imei", imei, "userId", userId, "productNo", productNo);
		} else {
			returnError(response, "params");
		}
	}

	/**
	 * 返回错误信息
	 * 
	 * @param response
	 * @param errorType
	 *            params:请求参数不能为空 api:服务器的错误信息
	 */
	private void returnError(HttpServletResponse response, String errorType) {
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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
