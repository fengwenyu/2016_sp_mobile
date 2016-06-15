package com.shangpin.wireless.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HttpFilter implements Filter {
	private FilterConfig filterConfig;

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getRequestURL().toString();
		if (path.indexOf("./") > 0 || path.indexOf("../") > 0 || path.matches("^.*?\\.(jsp)$")) {
			if (path.indexOf("image.jsp") < 0 && path.indexOf("index.jsp") < 0) {
				request.getRequestDispatcher("/privilegeError.jsp").forward(request, response);
				return;
			}
		}
		doFilter(request, response, chain);

	}

	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

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

}
