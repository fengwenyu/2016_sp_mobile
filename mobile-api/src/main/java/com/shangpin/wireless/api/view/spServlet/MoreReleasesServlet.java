package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.biz.service.ASPBizReleasesService;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

public class MoreReleasesServlet extends BaseServlet {

	private static final long serialVersionUID = -7353706266177675836L;

	protected final Log log = LogFactory.getLog(MoreReleasesServlet.class);

	ASPBizReleasesService aspBizReleasesService;

	@Override
	public void init() throws ServletException {
		aspBizReleasesService = (ASPBizReleasesService) getBean(ASPBizReleasesService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String productNo = request.getHeader("p");
		final String imei = request.getHeader("imei");
		final String pageIndex = request.getParameter("pageIndex");
		final String pageSize = request.getParameter("pageSize");
		final String channelNo = ChannelNoUtil.getChannelNo(request);// 获取渠道号
		if (StringUtil.isNotEmpty(imei, productNo, pageIndex, pageSize)) {
			try {
				String bizData = aspBizReleasesService.getMoreReleases(pageIndex, pageSize,"1");
				response.getWriter().print(bizData);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("MoreReleasesServlet:" + e);
				returnError(response, "api");
			}
			// 记录访问日志
			FileUtil.addLog(request, "MoreReleasesServlet", channelNo, "imei", imei, "productNo", productNo, "pageIndex", pageIndex, "pageSize", pageSize);
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
