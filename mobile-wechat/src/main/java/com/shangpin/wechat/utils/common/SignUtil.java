/**
 * 
 */
package com.shangpin.wechat.utils.common;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import com.shangpin.wechat.constants.CommonConstants;

/**
 * @author ZRS
 *
 */
public class SignUtil {
	
	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * 
	 * @param packageParams
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String createSign(SortedMap<String, String> packageParams, String key) {
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
		
		sb.append("key=" + key);
		String sign = MD5Util.MD5Encode(sb.toString(), CommonConstants.CHARSET).toUpperCase();
		
		return sign;

	}

}
