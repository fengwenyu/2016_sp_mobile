package com.shangpin.mobileAolai.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

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
		return cookieMap(SysContent.getRequest()).get(name);
	}

	/**
	 * Cookie值格式为xxxx&yyyy，xxxx表示推广参数，yyyy为时间戳 ，比较2个Cookie值，返回最近保存的Cookie信息
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 返回最近保存的Cookie信息
	 */
	public static Map<String, String> getCookieValue(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = cookieMap(request);
		Map<String, String> map = new HashMap<String, String>();
		if (null != cookieMap && cookieMap.size() > 0) {
			Cookie cp1st = cookieMap.get(Constants.SP_COOKIE_NAME_PROMOTION1ST);
			Cookie cp2nd = cookieMap.get(Constants.SP_COOKIE_NAME_PROMOTION2ND);
			Cookie cp3th = cookieMap.get(Constants.SP_COOKIE_NAME_PROMOTION3TH);
			if (null != cp1st) {
				map.put("c", StringUtils.split(cp1st.getValue(), Constants.SP_COOKIE_SEPARATOR_CHAR)[0]);// 回传参数
				map.put("st", StringUtils.split(cp1st.getValue(), Constants.SP_COOKIE_SEPARATOR_CHAR)[1]);// 访问wap站点时间
				map.put("type", "1");// 访问类型，1推广；2合作；3直接
				return map;
			}
			if (null != cp2nd) {
				map.put("c", StringUtils.split(cp2nd.getValue(), Constants.SP_COOKIE_SEPARATOR_CHAR)[0]);
				map.put("st", StringUtils.split(cp2nd.getValue(), Constants.SP_COOKIE_SEPARATOR_CHAR)[1]);
				map.put("type", "1");
				return map;
			}
			if (null != cp3th) {
				map.put("c", StringUtils.split(cp3th.getValue(), Constants.SP_COOKIE_SEPARATOR_CHAR)[0]);
				map.put("st", StringUtils.split(cp3th.getValue(), Constants.SP_COOKIE_SEPARATOR_CHAR)[1]);
				map.put("type", "1");
				return map;
			}
		}
		map.put("st", String.valueOf(new Date().getTime()));
		map.put("type", "3");
		return map;
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
}
