package com.shangpin.web.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.shangpin.biz.bo.User;
import com.shangpin.utils.MD5Util;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.CookieUtil;

/**
 * @author qinyingchun
 *过滤用户访问地址，在浏览器的cookie中添加唯一标识。
 */
public class CookieFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(CookieFilter.class);
	private static final String CHANNEL_NAME = "ch";
	private static final String UUID_NAME = "uuid";
	private static final String USER_ID = "behaviorflag";
	private static final String SPDB_CHANNEL = "102";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String channelNo = request.getParameter(CHANNEL_NAME);
		if(!StringUtils.isEmpty(channelNo)){
			if(channelNo.equals(SPDB_CHANNEL)){
				CookieUtil.addCookie(response, CHANNEL_NAME, channelNo, Constants.SPDB_COOKIE_CHANNEL);
			}else{
				CookieUtil.addCookie(response, CHANNEL_NAME, channelNo, Constants.COOKIE_CHANNEL);
			}
			
		}else{
			Cookie chCookie = CookieUtil.getCookieByName(request, CHANNEL_NAME);
			if(null == chCookie){
				CookieUtil.addCookie(response, CHANNEL_NAME, Constants.SP_WAP_DEFAULT_CHANNELNO, Constants.COOKIE_CHANNEL);
			}
		}
		Cookie cookie = CookieUtil.getCookieByName(request, UUID_NAME);
		if(null == cookie){
			String uuid = generateUUID();
			CookieUtil.addCookie(response, UUID_NAME, uuid, Constants.COOKIE_UUID);
		}
		User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
		if(null != user){
			String userId = user.getUserid();
			String encrypt_user = MD5Util.MD5(MD5Util.MD5(userId));
			CookieUtil.addCookie(response, USER_ID, encrypt_user, Constants.COOKIE_TIME);
		}else {
			CookieUtil.removeCookie(response, USER_ID);
		}
		chain.doFilter(request, response);
	}

	public String generateUUID(){
		String uuid = UUID.randomUUID().toString();
		logger.debug("uuid:" + uuid);
		String newUuid = uuid.replace("-", "").trim();
		logger.debug("newUuid:" + newUuid);
		return newUuid;
	}
	
	@Override
	public void destroy() {
		
	}

}
