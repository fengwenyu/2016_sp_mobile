package com.aolai.web.filter;

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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 拦截客户端请求URL做转换处理
 * 
 * @author cuibinqiang
 */
public class CheckURLFilter implements Filter {

	public static final Logger logger = LoggerFactory.getLogger(CheckURLFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String fullURL = getRequestURL(request);

		boolean loginflag = fullURL.contains("accountaction!loginui");
		if (loginflag) {// 如果请求来源是客户端，作如下转换处理
			fullURL = fullURL.replace("accountaction!loginui", "login");
			response.sendRedirect(fullURL);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@SuppressWarnings({ "rawtypes" })
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

}
