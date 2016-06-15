package com.shangpin.mobileShangpin.common.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.shangpin.mobileShangpin.businessbean.TopicConfig;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.CookieUtil;
import com.shangpin.mobileShangpin.common.util.DataContainerPool;
import com.shangpin.mobileShangpin.common.util.SysContent;
import com.shangpin.mobileShangpin.common.util.WebUtil;

public class CheckURIFilter implements Filter {
	private FilterConfig filterConfig;

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if(request.getSession().getAttribute("islogin")==null){
			
			request.getSession().setAttribute("islogin", "0");
		}
		String uri = request.getRequestURI();
		String url = request.getRequestURL().toString();
		String localAddr = request.getLocalAddr();
		String serverName=request.getServerName();
		String ua = request.getHeader("User-Agent");// ua
		String ch = (String) request.getSession().getAttribute(Constants.CHANNEL_PARAM_NAME);
		if (ch == null) {
			ch = "";
		}
		String fullURL = getRequestURL(request);
		String tmp = "http://cmbc.m.shangpin.com/download.action";
		// tmp = "http://192.168.6.18:8080/MobileShangPin/download.action";
		if (fullURL.indexOf(tmp) >= 0) {
			fullURL = fullURL.replace("download.action", "appdownloadaction!download");
			response.sendRedirect(fullURL);
			return;
		}
		if (url.indexOf("http://cmbc.m.shangpin.com/download") >= 0) {
			request.getRequestDispatcher("/mobileapp.html").forward(request, response);
			return;
		}
		if ("http://cmbc.m.shangpin.com/bazaar".equals(url) && ua != null && ua.indexOf("iPad") < 0 && ua.indexOf("Mobile") < 0) {
			// if (ua != null && ua.indexOf("iPad") < 0 && ua.indexOf("Mobile") < 0) {
			response.sendRedirect("http://www.shangpin.com/bazaar");
			return;
		}
		StringBuffer para = new StringBuffer();
		// 获取推广链接（3个级别），将参数保存到cookie中
		String contain = "http://cmbc.m.shangpin.com/promotion";
		contain = "http://192.168.6.18:8080/MobileShangPin/promotion";
		if (url.indexOf(contain) >= 0) {
			para.append(url.substring(url.lastIndexOf("/") + 1));
			para.append(Constants.SP_COOKIE_SEPARATOR_CHAR);
			para.append(new Date().getTime());
			if (url.indexOf(Constants.SP_COOKIE_NAME_PROMOTION1ST) > 0) {
				CookieUtil.addCookie(response, Constants.SP_COOKIE_NAME_PROMOTION1ST, para.toString(), 30 * 24 * 60 * 60);
			}
			if (url.indexOf(Constants.SP_COOKIE_NAME_PROMOTION2ND) > 0) {
				CookieUtil.addCookie(response, Constants.SP_COOKIE_NAME_PROMOTION2ND, para.toString(), 7 * 24 * 60 * 60);
			}
			if (url.indexOf(Constants.SP_COOKIE_NAME_PROMOTION3TH) > 0) {
				CookieUtil.addCookie(response, Constants.SP_COOKIE_NAME_PROMOTION3TH, para.toString(), 1 * 24 * 60 * 60);
			}
		}
		if (uri != null) {
			String[] split = uri.split("/");
			String basePath = "";
			if ("172.20.10.43".equals(localAddr)) {
				basePath = "http://cmbc.m.shangpin.com/";
			}else if ("172.20.10.143".equals(localAddr)) {
				basePath = "http://cmbc.m.shangpin.com/";
			}
			else if ("172.20.10.100".equals(localAddr)) {
				basePath = "http://cmbc.m.shangpin.com/";
			}
			else if ("172.20.10.113".equals(localAddr)) {
				basePath = "http://cmbc.m.shangpin.com/";
			}
			else if ("172.20.10.114".equals(localAddr)) {
				basePath = "http://cmbc.m.shangpin.com/";
			}
			else if("123.124.211.164".equals(serverName)){
				basePath = request.getScheme() + "://" + serverName + ":" + request.getServerPort() + request.getContextPath() + "/";
			}
			else {
				basePath = request.getScheme() + "://" + localAddr + ":" + request.getServerPort() + request.getContextPath() + "/";
			}
			// 活动匹配
			if (DataContainerPool.topicConfigContainer.getQueueSize() > 0) {
				Map<Object, Object> cache = DataContainerPool.topicConfigContainer.getAll();
				Set<Object> set = cache.keySet();
				Iterator<Object> it = set.iterator();
				while (it.hasNext()) {
					TopicConfig topicConfig = (TopicConfig) cache.get(it.next().toString());
					if (url.indexOf(topicConfig.getUriSuffix()) > 0) {
						para.delete(0, para.length());
						para.append(topicConfig.getCh());
						para.append(Constants.SP_COOKIE_SEPARATOR_CHAR);
						para.append(new Date().getTime());
						CookieUtil.addCookie(response, Constants.SP_COOKIE_NAME_COOPERATION, para.toString(), 1 * 24 * 60 * 60);
						url = basePath + "merchandiseaction!splist?ch=" + topicConfig.getCh() + "&topicid=" + topicConfig.getId();
						response.sendRedirect(url);
						return;
					}
				}
			}
			/*if (url.indexOf("topic") > 0) {
				System.out.println("topic");
				String[] split2 = url.substring(url.indexOf("topic"), url.length()).split("/");
				if (split2.length == 2) {
					System.out.println(split2[1]);
					if ("20130401564".equals(split2[1]))
						url = basePath + "merchandiseaction!splist?ch=31&topicid=" + split2[1];
					else if ("20130422715".equals(split2[1]))
						url = basePath + "merchandiseaction!splist?ch=35&topicid=" + split2[1];
					else
						url = basePath + "merchandiseaction!splist?ch=4&topicid=" + split2[1];
					System.out.println(url);
					response.sendRedirect(url);
					return;
				}
			} else if (url.indexOf("product") > 0 && url.indexOf("merchandiseaction") < 0) {
				System.out.println("product");
				String[] split3 = url.substring(url.indexOf("product"), url.length()).split("/");
				if (split3.length == 2) {
					url = basePath + "merchandiseaction!spdetail?ch=4&productid=" + split3[1] + "&topicid=";
					System.out.println(url);
					response.sendRedirect(url);
					return;
				}
			}
			if ("http://cmbc.m.shangpin.com/".equals(url) || "http://cmbc.m.shangpin.com".equals(url)) {
				url = basePath + "merchandiseaction!splist?ch=&topicid=20130401564";
				System.out.println(url);
				response.sendRedirect(url);
				return;
			}*/
			/*if (split.length > 1) {
				uri = split[split.length - 1];
				System.out.println("11111111111111111"+uri);
				if ("women".equals(uri)) {
					response.sendRedirect(basePath);
					return;
				} else if ("men".equals(uri)) {
					response.sendRedirect(basePath);
					return;
				} else if (uri.startsWith("cartaction!deletecart") || uri.startsWith("cartaction!showcart")) {
					Object obj = request.getSession().getAttribute(WebUtil.SESSION_USER_PARAM);
					if (obj == null) {
						System.out.println("22222222222222"+basePath);
						response.sendRedirect(basePath + "accountaction!loginui?loginFrom=4&ch=" + ch);
						return;
					}
				} else if (uri.startsWith("orderaction")) {
					Object obj = request.getSession().getAttribute(WebUtil.SESSION_USER_PARAM);
					if (obj == null) {
						response.sendRedirect(basePath + "accountaction!loginui?loginFrom=2&ch=" + ch);
						return;
					}
				} else if (uri.startsWith("accountaction!addresslist") || uri.startsWith("accountaction!deleteaddress") || uri.startsWith("accountaction!newaddress") || uri.startsWith("accountaction!modifyaddress") || uri.startsWith("accountaction!saveaddress") || uri.startsWith("accountaction!ajaxsaveaddress") || uri.startsWith("accountaction!ajaxsaveinvoiceaddress")) {
					Object obj = request.getSession().getAttribute(WebUtil.SESSION_USER_PARAM);
					if (obj == null) {
						response.sendRedirect(basePath + "accountaction!loginui?loginFrom=3&ch=" + ch);
						return;
					}
				}
			}*/
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		init();
	}

	public void init() throws ServletException {
	}

	public void destroy() {
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	/**
	 * 获取完整URL
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 完整URL
	 */
	@SuppressWarnings("rawtypes")
	private String getRequestURL(HttpServletRequest request) {
		if (request == null) {
			return "";
		}
		String url = request.getRequestURL().toString();
		int i = 0;
		if (StringUtils.isNotEmpty(request.getQueryString())) {
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

	public static void main(String[] args) {
		// http://www.shangpin.com/bazaar
		// http://www.shangpin.com/women/topic/20130408595
		// http://www.shangpin.com/women/product/02263571
		String basePath = "http://cmbc.m.shangpin.com/";
		String url = "http://cmbc.m.shangpin.com/women/topic/20130401564";
		if (url.indexOf("bazaar") > 0) {
			url = basePath + "merchandiseaction!splist?ch=31&topicid=20130401564";
			System.out.println(url);
			return;
		} else if (url.indexOf("topic") > 0) {
			System.out.println("topic");
			String[] split2 = url.substring(url.indexOf("topic"), url.length()).split("/");
			if (split2.length == 2) {
				System.out.println(split2[1]);
				url = basePath + "merchandiseaction!splist?ch=4&topicid=" + split2[1];
				System.out.println(url);
				return;
			}
		} else if (url.indexOf("product") > 0 && url.indexOf("merchandiseaction") < 0) {
			System.out.println("product");
			String[] split3 = url.substring(url.indexOf("product"), url.length()).split("/");
			if (split3.length == 2) {
				url = basePath + "merchandiseaction!spdetail?ch=4&productid=" + split3[1] + "&topicid=";
				System.out.println(url);
				return;
			}
		}
	}
}
