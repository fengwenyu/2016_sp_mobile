package com.shangpin.mobileAolai.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SysContent {

	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();

	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();

	public static HttpServletRequest getRequest() {

		return (HttpServletRequest) requestLocal.get();
	}

	public static void setRequest(HttpServletRequest request) {

		requestLocal.set(request);
	}

	public static HttpServletResponse getResponse() {

		return (HttpServletResponse) responseLocal.get();
	}

	public static void setResponse(HttpServletResponse response) {

		responseLocal.set(response);
	}

	public static HttpSession getSession() {
		if (null == requestLocal
				|| null == requestLocal.get()
				|| null == ((HttpServletRequest) requestLocal.get())
						.getSession())
			return null;
		return (HttpSession) ((HttpServletRequest) requestLocal.get())
				.getSession();
	}

}
