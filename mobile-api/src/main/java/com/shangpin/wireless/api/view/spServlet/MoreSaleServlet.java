package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.biz.service.ASPBizSaleService;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

public class MoreSaleServlet extends BaseServlet {

	private static final long serialVersionUID = -8205122175402669736L;

	protected final Log log = LogFactory.getLog(MoreSaleServlet.class);

	ASPBizSaleService aspBizSaleService;

	@Override
	public void init() throws ServletException {
		aspBizSaleService = (ASPBizSaleService) getBean(ASPBizSaleService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String productNo = request.getHeader("p");
		final String imei = request.getHeader("imei");
		final String type = request.getParameter("type");
		final String pageIndex = request.getParameter("pageIndex");
		final String pageSize = request.getParameter("pageSize");
		final String channelNo = ChannelNoUtil.getChannelNo(request);// 获取渠道号
		if (StringUtil.isNotEmpty(type, pageIndex, pageSize)) {
			try {
				String bizData = aspBizSaleService.getMoreSale(type, pageIndex, pageSize);
				response.getWriter().print(bizData);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("MoreSaleServlet:" + e);
				returnError(response, "api");
			}
			// 记录访问日志
			FileUtil.addLog(request, "MoreSaleServlet", channelNo, "imei", imei, "productNo", productNo, "type", type, "pageIndex", pageIndex, "pageSize", pageSize);
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
