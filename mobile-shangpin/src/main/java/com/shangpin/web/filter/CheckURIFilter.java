package com.shangpin.web.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.biz.utils.StringUtil;
import com.shangpin.web.utils.Constants;

public class CheckURIFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(CheckURIFilter.class);

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String ctx = request.getContextPath();
		String fullURL = getRequestURL(request);
		logger.debug("CheckURIFilter fullURL={}", fullURL);
		// // 过滤物流查询
		if (checkLogistic(fullURL, request, response)) {
			return;
		}

		String tmp = ctx + "/download.action";
		if (fullURL.indexOf(tmp) >= 0) {
			fullURL = fullURL.replace("download.action", "goDownload");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("accountaction!loginui") >= 0) {
			fullURL = fullURL.replace("accountaction!loginui", "login");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("spindex!index") >= 0) {
			fullURL = fullURL.replace("spindex!index", "index");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("weixinaction!loginbind") >= 0) {
			fullURL = fullURL.replace("weixinaction!loginbind", "bind/info");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("accountaction!info") >= 0) {
			fullURL = fullURL.replace("accountaction!info", "user/home");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("couponaction!couponlist") >= 0) {
			fullURL = fullURL.replace("couponaction!couponlist", "coupon/list");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("orderaction!orderlist") >= 0) {
			fullURL = fullURL.replace("orderaction!orderlist", "order/list");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("logisticeaction!logisticeInfo") >= 0) {
			fullURL = fullURL.replace("logisticeaction!logisticeInfo", "logistice/list");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("merchandiseaction!spdetail") >= 0) {
			fullURL = fullURL.replace("merchandiseaction!spdetail", "product/detail");
			fullURL = fullURL.replace("productid", "productNo");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("topshopFlagship.jsp") >= 0) {
			fullURL = fullURL.replace("topshopFlagship.jsp", "topshop/index");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("fashionInfoaction!fashion") >= 0) {
			fullURL = fullURL.replace("fashionInfoaction!fashion", "fashion/index");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("flagship!index") >= 0) {
			fullURL = fullURL.replace("flagship!index", "flagshop/index");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("brandaction!getAllBrandList") >= 0) {
			fullURL = fullURL.replace("brandaction!getAllBrandList", "brand/list");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("categoryaction!index") >= 0) {
			fullURL = fullURL.replace("categoryaction!index", "category/index");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("weixinaction!loginbind") >= 0) {
			fullURL = fullURL.replace("weixinaction!loginbind", "weixin/bind/info");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("weixinaction!ok") >= 0) {
			fullURL = fullURL.replace("weixinaction!ok", "weixin/ok");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("merchandiseaction!spdetailForDev?productid=") >= 0) {
			fullURL = fullURL.replace("merchandiseaction!spdetailForDev?productid=", "product/app/detail?productNo=");
			fullURL = fullURL.replace("topicid", "topicId");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("weixinaction!weixinbindinfo") >= 0) {
			fullURL = fullURL.replace("weixinaction!weixinbindinfo", "weixin/bind/info");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("activityaction!activity") >= 0) {
			fullURL = fullURL.replace("activityaction!activity", "subject/index");
			response.sendRedirect(fullURL);
			return;
		}
		if (fullURL.indexOf("merchandiseaction!starDetail") >= 0) {
			fullURL = fullURL.replace("merchandiseaction!starDetail", "product/detail");
			fullURL = fullURL.replace("productId", "productNo");
			response.sendRedirect(fullURL);
			return;
		}
		if (checkCart(fullURL, request, response)) {
			return;
		}
		chain.doFilter(request, response);
	}

	private boolean checkLogistic(String fullURL, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 物流html5 IOS
		String logistic = "/orderLogisticInfo?";
		if (fullURL.indexOf(logistic) > 0) {
			String userId = request.getHeader("userId");
			userId = (userId == null || "".equals(userId)) ? request.getHeader("userid") : userId;
			userId = (userId == null || "".equals(userId)) ? request.getParameter("userId") : userId;
			String orderId = request.getParameter("orderId");
			String postArea = request.getParameter("postArea");
			String host = Constants.SHANGPIN_URL;
			String url1 = host + "/logistice/list?userId=" + userId + "&orderId=" + orderId + "&postArea=" + postArea;
			logger.debug("CheckURIFilter ios url1={}", url1);
			response.sendRedirect(url1);
			return true;
		}
		// andriod
		/*String logisticAndriod = "/orderLogisticInfo/";
		if (fullURL.indexOf(logisticAndriod) > 0) {
			String userId = request.getHeader("userId") == null ? request.getParameter("userId") : request.getHeader("userId");
			String orderId = request.getParameter("orderId");
			String postArea = request.getParameter("postArea");
			String host = Constants.SHANGPIN_URL;
			String url1 = host + "/logistice/list?userId=" + userId + "&orderId=" + orderId + "&postArea=" + postArea;
			logger.debug("CheckURIFilter andriod url1={}", url1);
			response.sendRedirect(url1);
			return true;
		}*/
		return false;
	}

	private boolean checkCart(String fullURL, HttpServletRequest request, HttpServletResponse response) {
		try {
			String ctx = request.getContextPath();
			if (fullURL.indexOf("allcartaction!listCart") >= 0) {
				response.sendRedirect(ctx + "/cart/list");
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;

	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void destroy() {
	}

	@SuppressWarnings("rawtypes")
	private String getRequestURL(HttpServletRequest request) {
		if (request == null) {
			return "";
		}
		String url = request.getRequestURL().toString();
		int i = 0;
		logger.debug("CheckURIFilter queryString={} ", request.getQueryString());
		if (StringUtil.isNotEmpty(request.getQueryString())) {
			url = url + "?" + request.getQueryString();
		} else {
			Enumeration names = request.getParameterNames();
			if (names != null) {
				while (names.hasMoreElements()) {
					String name = (String) names.nextElement();
					if (i == 0) {
						url = url + "?";
					} else {
						url = url + "&";
					}
					i++;
					String value = request.getParameter(name);
					if (value == null) {
						value = "";
					}
					url = url + name + "=" + value;
				}
			}
		}
		return url;
	}

}
