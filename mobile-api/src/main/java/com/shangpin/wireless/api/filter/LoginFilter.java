package com.shangpin.wireless.api.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

public class LoginFilter implements Filter{
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//  Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse rep = (HttpServletResponse) response;
			String responseUrl = req.getHeader("referer");
			HttpSession session = req.getSession();
			if(StringUtils.isNotEmpty(responseUrl)){
				session.setAttribute("url", responseUrl);
			}
//			req.getRequestDispatcher("/temp.jsp").forward(req, rep);
		    rep.sendRedirect(req.getContextPath()+"/temp.jsp");
		    chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		//  Auto-generated method stub
		
	}

}
