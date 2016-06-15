package com.shangpin.wireless.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.shangpin.wireless.ws.ip.ArrayOfString;
import com.shangpin.wireless.ws.ip.IpAddressSearchWebService;
import com.shangpin.wireless.ws.ip.IpAddressSearchWebServiceSoap;

/**
 * 获取本地IP
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-08-01
 */
public class IPUtil {
	/**
	 * 判断当前操作是否Windows
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-08-01
	 * @param
	 * @Return true 是Windows操作系统
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 获取本机IP地址，并自动区分Windows还是Linux操作系统
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-08-01
	 * @param
	 * @Return String
	 */
	public static String getLocalIP() {
		String sIP = "";
		InetAddress ip = null;
		try {
			// 如果是Windows操作系统
			if (isWindowsOS()) {
				ip = InetAddress.getLocalHost();
			}
			// 如果是Linux操作系统
			else {
				boolean bFindIP = false;
				Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
				while (netInterfaces.hasMoreElements()) {
					if (bFindIP) {
						break;
					}
					NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
					// ----------特定情况，可以考虑用ni.getName判断
					// 遍历所有ip
					Enumeration<InetAddress> ips = ni.getInetAddresses();
					while (ips.hasMoreElements()) {
						ip = (InetAddress) ips.nextElement();
						if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 127.开头的都是lookback地址
							bFindIP = true;
							break;
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (null != ip) {
			sIP = ip.getHostAddress();
		}
		return sIP;
	}

	private static final int IP_CACHE_KEEP_TIME = 30 * 60 * 1000; // 30分钟
	private static final HashMap<String, String> IpCache = new HashMap<String, String>();
	private static long IpCacheTime;
	/**
	 * 调用远程webservice来根据ip查询城市
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2013-03-27
	 * @param
	 * @throws Exception
	 * @Return String
	 */
	public static String getCountryCityByIp(String ip) {
		String city = null;

		long now = System.currentTimeMillis();
		if (now > IpCacheTime + IP_CACHE_KEEP_TIME) {
			IpCache.clear();
		}
		city = IpCache.get(ip);
		if (StringUtils.isNotEmpty(city)) {
			return city;
		}
		try {
			String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php";
			Map<String, String> map = new HashMap<String, String>();
			map.put("format", "json");
			map.put("ip", ip);
			String data = WebUtil.readContentFromGet(url, map);
			net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(data);
			StringBuilder sb = new StringBuilder();
			if (obj.has("country")) {
				sb.append(obj.getString("country")).append(" ");
			}
			if (obj.has("province")) {
				sb.append(obj.getString("province")).append(" ");
			}
			if (obj.has("city"))
				sb.append(obj.getString("city"));
			if (sb.length() == 0) {
				city = sb.toString();
			}
//			city = obj.get("country") + " " + obj.get("province") + " " + obj.get("city");
//			if (city.indexOf("null") > 0) {
			if (StringUtils.isEmpty(city)) {
				IpAddressSearchWebService ipAddressSearchWebService = new IpAddressSearchWebService();
				IpAddressSearchWebServiceSoap ipAddressSearchWebServiceSoap = ipAddressSearchWebService.getIpAddressSearchWebServiceSoap();
				ArrayOfString countryCityByIp = ipAddressSearchWebServiceSoap.getCountryCityByIp(ip);
				if (countryCityByIp.getString().size() == 2) {
					city = countryCityByIp.getString().get(1).split(" ")[0];
				}
			}
			if (IpCache.size() > 100) {
				IpCache.clear();
			}
			if (StringUtils.isNotEmpty(city)) {
				IpCache.put(ip, city);
			}
			IpCacheTime = now;
		} catch (Exception e) {
			e.printStackTrace();
			city = null;
		}
		return city;
	}

	public static void main(String[] args) throws Exception {
		// String serverIP = IPUtil.getLocalIP();
		// System.out.println("serverIP:" + serverIP);
		System.out.println(getCountryCityByIp("96.49.8.42"));
	}
}
