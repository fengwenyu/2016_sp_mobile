package com.shangpin.wireless.api.filter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.shangpin.pay.utils.common.StringUtil;
import com.shangpin.utils.AESUtil;
import com.shangpin.wireless.api.util.LoginLimitUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.util.FileUtil;

public class AccessFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String uri = request.getRequestURI();
		String imei = request.getHeader("imei");
		String ch = request.getHeader("ch");
		String os = request.getHeader("os");
		if ((uri.indexOf("aolaihelp") == -1) && (uri.indexOf("css") == -1)
				&& (uri.indexOf("js") == -1) && (uri.indexOf("weixin") == -1)
				&& (uri.indexOf("shangpin/help") == -1)
				&& (uri.indexOf("images") == -1)
				&& ((StringUtils.isEmpty(ch)) || (StringUtils.isEmpty(os)))) {
			return;
		}
		if (uri.indexOf("/sendsmscode") > -1 || uri.indexOf("/regist") > -1) {
			FileUtil.addLog(request, "----------------accessFilter-sms",
					new String[] { headerStr(request) });
			String ip = getIpAddr(request);
			if (ip != null) {
				boolean islimitIme = LoginLimitUtil.addImeiLoginCache(ip, imei);
				if (!islimitIme) {// 受限
					try {
						WebUtil.sendLimitException(response);
						return;
					} catch (Exception e) {
					}
				}
			}
			/*
			 * // 添加imei和ip注册限制 boolean addImeiLoginLimitImei =
			 * LoginLimitUtil.addImeiLoginCache(imei, request.getRemoteAddr());
			 * boolean addImeiLoginLimitIp =
			 * LoginLimitUtil.addImeiLoginCache(request.getRemoteAddr(), imei);
			 * 
			 * FileUtil.addLog(request,
			 * "----------------accessFilter-sms",sb.toString());
			 * 
			 * if (!addImeiLoginLimitImei||!addImeiLoginLimitIp) { try {
			 * WebUtil.sendLimitException(response); return; } catch (Exception
			 * e) { e.printStackTrace(); } }
			 */
		}
		List<String> list = BlackList.list;
		String token = request.getHeader("token");
		for (String str : list) {
			if (!uri.contains(str)) {
				continue;
			}
			if (!checkToken(token)) {
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private String headerStr(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String hn = headerNames.nextElement().toString();
			String value = request.getHeader(hn);
			sb.append(hn + ":" + value + ",");
		}
		return sb.toString();
	}

	public AccessFilter() {
		super();
	}

	private String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if ((ipAddress == null) || (ipAddress.length() == 0)
				|| ("unknown".equalsIgnoreCase(ipAddress))) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if ((ipAddress == null) || (ipAddress.length() == 0)
				|| ("unknown".equalsIgnoreCase(ipAddress))) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ipAddress == null) || (ipAddress.length() == 0)
				|| ("unknown".equalsIgnoreCase(ipAddress))) {
			ipAddress = request.getRemoteAddr();
			if ((ipAddress.equals("127.0.0.1"))
					|| (ipAddress.equals("0:0:0:0:0:0:0:1"))) {
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}

		if ((ipAddress != null) && (ipAddress.length() > 15)) {
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 判断token是否存在，如果不存在，false,如果解密出错，异常情况 false
	 * 
	 * @param token
	 * @return
	 */
	private static boolean checkToken(String token) {
		try {
			if (StringUtil.isEmpty(token)) {
				return false;
			}
			String decryptStr = AESUtil.decrypt(token, null);
			if (StringUtil.isEmpty(decryptStr)) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}
	class BlackList {
		static List<String> list = null;
		static {
			list = new ArrayList<String>();
			list.add("orderDetail");
			list.add("orderList");
			list.add("orderLogisticInfo");
			list.add("submitOrder");
		}
	}
