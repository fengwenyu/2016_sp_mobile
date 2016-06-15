package com.shangpin.wechat.utils.openplatform;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.wechat.constants.OpenPlatformConstants;
import com.shangpin.wechat.utils.common.MD5Util;

/**
 * 微信开放平台请求数据、组装数据等工具类
 * 
 * @author huangxiaoliang
 *
 */
public class WeChatOpenUtil {

	public static Logger logger = LoggerFactory.getLogger(WeChatOpenUtil.class);

	/**
	 * 编码特殊处理，将空格转化为%20而不是+
	 * 
	 * @param src
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String UrlEncode(String src) throws UnsupportedEncodingException {
		return URLEncoder.encode(src, OpenPlatformConstants.CHARSET).replace("+", "%20");
	}

	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * 
	 * @param packageParams
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String createSign(SortedMap<String, String> packageParams) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		
		sb.append("key=" + OpenPlatformConstants.OPEN_PARTNER_KEY);
		String sign = MD5Util.MD5Encode(sb.toString(), OpenPlatformConstants.CHARSET).toUpperCase();
		
		return sign;

	}

	/**
	 * 获取package带参数的签名包
	 * 
	 * @param packageParams
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("rawtypes")
	public static String getPackage(SortedMap<String, String> packageParams) throws UnsupportedEncodingException {
		String sign = createSign(packageParams);

		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + UrlEncode(v) + "&");
		}

		// 去掉最后一个&
		String packageValue = sb.append("sign=" + sign).toString();
		
		return packageValue;
	}

	/**
	 * 是否财付通签名,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * 
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isTenpaySign(SortedMap<String, String> parameters) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"sign".equals(k) && null != v && !"".equals(v)) {
				// if(!k.equals("PcacheTime")){
				sb.append(k + "=" + v + "&");
				// }
			}
		}

		sb.append("key=" + OpenPlatformConstants.OPEN_PARTNER_KEY);

		// 算出摘要
		String sign = MD5Util.MD5Encode(sb.toString(), OpenPlatformConstants.CHARSET).toLowerCase();

		String parameter = (String) parameters.get("sign");

		String tenpaySign = ((null == parameter) ? "" : parameter).toLowerCase();

		logger.debug(sb.toString() + " => sign:" + sign + " tenpaySign:" + tenpaySign);

		return tenpaySign.equals(sign);
	}

}
