package com.shangpin.mobileShangpin.common.page;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class PagerFilter implements Filter {

	public static final String PAGE_SIZE_NAME = "ps";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		PageContext.setOffset(getOffset(httpRequest));
		PageContext.setPagesize(getPagesize(httpRequest));
		try {
			chain.doFilter(request, response);
		} finally {
			PageContext.removeOffset();
			PageContext.removePagesize();
		}

	}

	private int getOffset(HttpServletRequest request) {
		int offset = 0;
		try {
			if (!StringUtils.isEmpty(request.getParameter("pager.offset")))
				offset = Integer.parseInt(request.getParameter("pager.offset"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offset;
	}

	private int getPagesize(HttpServletRequest httpRequest) {
		String psvalue = httpRequest.getParameter("pagesize");
		if (psvalue != null && !psvalue.trim().equals("")) {
			Integer ps = 0;
			try {
				ps = Integer.parseInt(psvalue);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (ps != 0) {
				httpRequest.getSession().setAttribute(PAGE_SIZE_NAME, ps);
			}
		}

		Integer pagesize = (Integer) httpRequest.getSession().getAttribute(
				PAGE_SIZE_NAME);
		if (pagesize == null) {
			httpRequest.getSession().setAttribute(PAGE_SIZE_NAME,
					Page.DEFAULT_PAGE_SIZE);
			return Page.DEFAULT_PAGE_SIZE;
		}

		return pagesize;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
