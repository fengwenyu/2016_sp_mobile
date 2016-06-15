package com.shangpin.web.interceptor;

import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.wechat.service.WeChatPublicService;
import com.shangpin.wechat.utils.publicplatform.WeChatPublicUtil;

public class WeixinAuthorizeIntercept extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(WeixinAuthorizeIntercept.class);
	@Autowired
	private WeChatPublicService weChatPublicService;
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		  String useragent = request.getHeader("User-Agent");
          if (ClientUtil.CheckMircro(useragent) ) {
        	  	String url = URLEncoder.encode(getRequestURL(request), Constants.DEFAULT_ENCODE);
	      		logger.info("encode url={}", url);
	      		if(url.indexOf("WEIXINPUBSEA")>-1){
	      			return true;
	      		}else{
	      			String code = request.getParameter("code");
		      		logger.info("request url code===={}", code);
		      		if(StringUtils.isEmpty(code)){
		      			String authorizeURL = WeChatPublicUtil.authorize("code","snsapi_base", url, null);
		      			logger.info("authorized url={}", authorizeURL);
		      			response.sendRedirect(authorizeURL);
		      			return false;
		      		}
	      		}
	      		
          }
		
		
		return true;
	}
	
	
	@SuppressWarnings("rawtypes")
    private String getRequestURL(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String url = request.getRequestURL().toString();
        logger.debug("requestURL={}", url);
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
	
	public static void main(String[] args) throws Exception {
		String url = "http://m.shangpin.com/packet/receive";
		String result = URLEncoder.encode(url, "utf-8");
		System.out.println(result);
	}
}
