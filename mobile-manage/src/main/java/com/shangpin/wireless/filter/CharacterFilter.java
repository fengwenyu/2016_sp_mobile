package com.shangpin.wireless.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharacterFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Myrequest myrequest = new Myrequest(request);
		chain.doFilter(myrequest, response);

	}

}

class Myrequest extends HttpServletRequestWrapper {

	public Myrequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String string = super.getParameter(name);
		String method = super.getMethod();
		if (string != null && "get".equalsIgnoreCase((method)) && !"".equals(string.trim())) {
			try {
				string = new String(string.getBytes("ISO-8859-1"), "utf-8");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return string;
	}

}
