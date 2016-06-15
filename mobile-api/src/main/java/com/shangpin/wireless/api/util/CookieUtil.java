package com.shangpin.wireless.api.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	/**
	 * 创建Cookie
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            cookie生命周期(单位：秒)
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}

	/**
	 *  通过Cookie名称获取Cookie
	 * 
	 * @param request
	 *            HttpServletRequest 
	 * @param name
	 *            Cookie名称
	 * @return Cookie 
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		return cookieMap(request).get(name);
	}

	

	
	/**
	 * 通过Cookie名称清除Cookie
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            Cookie名称
	 */
	public static void removeCookie(HttpServletResponse response, String name) {
		Cookie cookie = new Cookie(name, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 *  * 将cookie封装到Map中
	 * 
	 * @param request
	 *            HttpServletRequest  
	 * @return  cookie map
	 */
	private static Map<String, Cookie> cookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	
	
	/**
	 *  * 将cookie封装到Map中
	 * 
	 * @param request
	 *            HttpServletRequest  
	 * @return  cookie map
	 */
	@SuppressWarnings("unused")
    private static Map<String, Cookie> chanelCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
			
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
}
