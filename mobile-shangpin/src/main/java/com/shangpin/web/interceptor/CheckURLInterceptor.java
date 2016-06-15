package com.shangpin.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shangpin.biz.service.SPBizWeixinService;

public class CheckURLInterceptor extends HandlerInterceptorAdapter {
	public static final Logger logger = LoggerFactory.getLogger(CheckURLInterceptor.class);
	@Autowired
	private SPBizWeixinService weixinService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String query = request.getQueryString();
		if (query != null && query.length() > 0) {
			// 过滤特殊字符 || (query.indexOf("\'")>0) // "
			boolean isNotValidate = (query.indexOf("--") > 0) || (query.indexOf("%22") > 0) // "
					|| (query.indexOf("%3C") > 0) // <
					|| (query.indexOf("%3E") > 0) // >
					|| (query.indexOf("?") > 0) || (query.indexOf("script") > 0);
			if (isNotValidate) {
				logger.debug("CheckURLInterceptor isNotValidate : {}", query);
				return false;
			}
		}

		StringBuffer appUrl = request.getRequestURL();
		if (query != null) {
			logger.info("=========== " + query);
			if (query.indexOf("8uuuuu8") > 1) {
				logger.info("CheckURLInterceptor ===========> " + query.replaceAll("8uuuuu8", "&"));
				response.sendRedirect(appUrl.append("?").append(query.replaceAll("8uuuuu8", "&")).toString());
				return false;
			}
		}

		
		return true;
	}

}