package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.biz.service.ASPBizBuyNowService;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

public class BuyNowServlet extends BaseServlet {

	private static final long serialVersionUID = 1602142647118883733L;

	protected final Log log = LogFactory.getLog(BuyNowServlet.class);

	ASPBizBuyNowService bizBuyNowService;

	@Override
	public void init() throws ServletException {
		bizBuyNowService = (ASPBizBuyNowService) getBean(ASPBizBuyNowService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String productNo = request.getHeader("p");
		final String imei = request.getHeader("imei");
		final String userId = request.getParameter("userId");
		final String skuId = request.getParameter("skuId");
		final String productId = request.getParameter("productId");
		final String activityId = request.getParameter("activityId");
		final String amount = request.getParameter("amount");
		final String region = request.getParameter("region");
		final String version = request.getHeader("ver");

		if (StringUtil.isNotEmpty(imei, productNo, userId, skuId, productId, amount, region, version)) {
			try {
				String bizData = bizBuyNowService.queryBuyNow(userId, skuId, productId, activityId, amount, region, version);
				sendMessage(response, bizData);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("BuyNowServlet:" + e);
				responseError(response, "api");
			}
		} else {
			responseError(response, "params");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
