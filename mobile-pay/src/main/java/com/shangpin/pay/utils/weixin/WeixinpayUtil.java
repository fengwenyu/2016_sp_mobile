/**
 * create on 2014-10-08
 */
package com.shangpin.pay.utils.weixin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;




import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sunweiwei
 * @version v1.0
 */
public class WeixinpayUtil {
	public static Logger logger = LoggerFactory.getLogger(WeixinpayUtil.class);
	public static String TRADESTATE_SUCCESS="0";
	public static String TRADEMODE_SUCCESS="1";
	public static final String CHARSET = "UTF-8";
	// 生成签名
	static final String buildSignature(Map<String, String> params,
			String partnerKey) {
		final String digest = attachParams(params, true, false);
		// final String digest = WeixinpayPropertyUtil.attachParams(params,
		// true, false);
		final String origin = digest + "&key=" + partnerKey;
		String signature = null;
		try {
			signature = DigestUtils.md5Hex(origin).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return signature;
	}

	public static final String attachParams(Map<String, String> params,
			boolean sort, boolean encode) {
		ArrayList<String> keys = new ArrayList<String>(params.keySet());
		if (sort) {
			Collections.sort(keys); // 参数排序
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (encode) {
				try {
					value = URLEncoder.encode(value,
							WeixinpayUtil.CHARSET);

				} catch (UnsupportedEncodingException e) {
				}
			}
			sb.append(key).append("=").append(value);
			if (i < keys.size() - 1) {// 拼接时，不包括最后一个&字符
				sb.append("&");
			}
		}
		return sb.toString();
	}

//	public static final String getIpAddr(HttpServletRequest request) {
//		String ip = request.getHeader("x-forwarded-for");
//		// System.out.println("x-forwarded-for: " + ip);
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("Proxy-Client-IP");
//			System.out.println("Proxy-Client-IP: " + ip);
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("WL-Proxy-Client-IP");
//			System.out.println("WL-Proxy-Client-IP: " + ip);
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getRemoteAddr();
//		}
//		return ip;
//	}

	public static String CreateNoncestr(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < length; i++) {
			Random rd = new Random();
			res += chars.indexOf(rd.nextInt(chars.length() - 1));
		}
		return res;
	}

	public static String CreateNoncestr() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < 16; i++) {
			Random rd = new Random();
			res += chars.charAt(rd.nextInt(chars.length() - 1));
		}
		return res;
	}

	public static String FormatQueryParaMap(HashMap<String, String> parameters)
			throws SDKRuntimeException {

		String buff = "";
		try {
			List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
					parameters.entrySet());

			Collections.sort(infoIds,
					new Comparator<Map.Entry<String, String>>() {
						public int compare(Map.Entry<String, String> o1,
								Map.Entry<String, String> o2) {
							return (o1.getKey()).toString().compareTo(
									o2.getKey());
						}
					});

			for (int i = 0; i < infoIds.size(); i++) {
				Map.Entry<String, String> item = infoIds.get(i);
				if (!"".equals(item.getKey())) {
					buff += item.getKey()
							+ "="
							+ URLEncoder.encode(item.getValue(),
									WeixinpayUtil.CHARSET) + "&";
				}
			}
			if (buff.isEmpty() == false) {
				buff = buff.substring(0, buff.length() - 1);
			}
		} catch (Exception e) {
			throw new SDKRuntimeException(e.getMessage());
		}

		return buff;
	}

	public static String FormatBizQueryParaMap(HashMap<String, String> paraMap,
			boolean urlencode) throws SDKRuntimeException {

		String buff = "";
		try {
			List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
					paraMap.entrySet());

			Collections.sort(infoIds,
					new Comparator<Map.Entry<String, String>>() {
						public int compare(Map.Entry<String, String> o1,
								Map.Entry<String, String> o2) {
							return (o1.getKey()).toString().compareTo(
									o2.getKey());
						}
					});

			for (int i = 0; i < infoIds.size(); i++) {
				Map.Entry<String, String> item = infoIds.get(i);
				// System.out.println(item.getKey());
				if (!"".equals(item.getKey())) {

					String key = item.getKey();
					String val = item.getValue();
					if (urlencode) {
						val = URLEncoder.encode(val,
								WeixinpayUtil.CHARSET);

					}
					buff += key.toLowerCase() + "=" + val + "&";

				}
			}

			if (buff.isEmpty() == false) {
				buff = buff.substring(0, buff.length() - 1);
			}
		} catch (Exception e) {
			throw new SDKRuntimeException(e.getMessage());
		}
		return buff;
	}

	public static boolean IsNumeric(String str) {
		if (str.matches("\\d *")) {
			return true;
		} else {
			return false;
		}
	}

	public static String ArrayToXml(HashMap<String, String> arr) {
		String xml = "<xml>";

		Iterator<Entry<String, String>> iter = arr.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			String key = entry.getKey();
			String val = entry.getValue();
			if (IsNumeric(val)) {
				xml += "<" + key + ">" + val + "</" + key + ">";

			} else
				xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";
		}

		xml += "</xml>";
		return xml;
	}

	public static final String attachParams(Map<String, String> params,
			boolean sort, String charset) {
		ArrayList<String> keys = new ArrayList<String>(params.keySet());
		if (sort) {
			Collections.sort(keys); // 参数排序
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			sb.append(key).append("=").append(value);
			if (i < keys.size() - 1) {// 拼接时，不包括最后一个&字符
				sb.append("&");
			}
		}
		return sb.toString();
	}
	public static final String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
