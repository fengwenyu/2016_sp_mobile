/**
 * create on 2014-10-08
 */
package com.shangpin.pay.utils.union;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;









import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * @author sunweiwei
 * @version v1.0
 */
public class UnionpayUtil {
    public static final String KEY_ORDER_ID = "orderNumber";
	public static final String KEY_SIGNATURE_METHOD = "signMethod";
	public static final String KEY_SIGNATURE = "signature";
	public static final String KEY_TN = "tn";
	public static final String KEY_CODE = "respCode";
	public static final String KEY_MESSAGE = "respMsg";
	public static final String SIGNATURE_METHOD = "MD5";
	public static final String PAYDATA = "paydata";
	public static final String KEY_TRANS_STATUS = "transStatus";
	public static final String CALLBACK_SUCCESS = "0";
	public static final String NOTIFY_SUCCESS= "00";
	public static Logger logger = LoggerFactory.getLogger(UnionpayUtil.class);
	public static final String CHARSET = "UTF-8";
	//银联支付时，如果用了礼品卡支付会在订单号里增加关键字比如：“201404545125gift400”
	public static final String GIFT_FLAG = "gift";
	public static final boolean USETEST_MODE=false;
    /**
     * 
     * @param params 
     * 			1:被签名字符串中的关键信息需要按照key值做升序排列
     * 			2:报文发送前，需将各接口定义的字段信息进行URL编码后再进行如下&形式的拼接。
     * 			3:需要对key=value中的value进行URL编码
     * @param secret
     * @return signature
     */
	public static final String buildSignature(Map<String, String> params, String secret) {
		final String digest = attachParams(params, true, false);
		final String origin = digest + "&" + DigestUtils.md5Hex(secret);
		String signature = null;
		try {
			signature = DigestUtils.md5Hex(origin.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return signature;
	}

	public static final String attachParams(Map<String, String> params, boolean sort,
			boolean encode) {

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
					value = URLEncoder.encode(value, UnionpayPropertyUtil.CHARSET);
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

	/**
	 * 应答解析
	 * 
	 * @param respString
	 *            应答报文
	 * @param resp
	 *            应答要素
	 * @return 应答是否成功
	 */
	public static boolean verifyResponse(String respString, String secret,
			Map<String, String> resp) {
		if (respString != null && !"".equals(respString)) {
			// 请求要素
			Map<String, String> para;
			try {
				para = parseQString(respString);
			} catch (Exception e) {
				return false;
			}
			return verifyParams(para, secret, resp);

		}
		return false;
	}

	static boolean verifyParams(Map<String, String> params, String secret,
			Map<String, String> resp) {
		if (params.size() > 0) {
			String respSignature = params.get(KEY_SIGNATURE);
			// 除去数组中的空值和签名参数

			for (String key : params.keySet()) {
				String value = params.get(key);
				if (value == null || value.equals("")
						|| key.equalsIgnoreCase(KEY_SIGNATURE)
						|| key.equalsIgnoreCase(KEY_SIGNATURE_METHOD)) {
					continue;
				}
				resp.put(key, value);
			}
			String signature = buildSignature(resp, secret);
			if (null != respSignature && respSignature.equals(signature)) {
				return true;
			} else {
				return false;
			}

		}
		return false;
	}


	/**
	 * 解析应答字符串，生成应答要素
	 * 
	 * @param str
	 *            需要解析的字符串
	 * @return 解析的结果map
	 * @throws UnsupportedEncodingException
	 */
	private static Map<String, String> parseQString(String str)
			throws UnsupportedEncodingException {

		Map<String, String> map = new HashMap<String, String>();
		int len = str.length();
		StringBuilder temp = new StringBuilder();
		char curChar;
		String key = null;
		boolean isKey = true;

		for (int i = 0; i < len; i++) {// 遍历整个带解析的字符串
			curChar = str.charAt(i);// 取当前字符

			if (curChar == '&') {// 如果读取到&分割符
				putKeyValueToMap(temp, isKey, key, map);
				temp.setLength(0);
				isKey = true;
			} else {
				if (isKey) {// 如果当前生成的是key
					if (curChar == '=') {// 如果读取到=分隔符
						key = temp.toString();
						temp.setLength(0);
						isKey = false;
					} else {
						temp.append(curChar);
					}
				} else {// 如果当前生成的是value
					temp.append(curChar);
				}
			}
		}

		putKeyValueToMap(temp, isKey, key, map);

		return map;
	}

	private static void putKeyValueToMap(StringBuilder temp, boolean isKey,
			String key, Map<String, String> map)
			throws UnsupportedEncodingException {
		if (isKey) {
			key = temp.toString();
			if (key.length() == 0) {
				throw new RuntimeException("QString format illegal");
			}
			map.put(key, "");
		} else {
			if (key.length() == 0) {
				throw new RuntimeException("QString format illegal");
			}
			map.put(key, URLDecoder.decode(temp.toString(), UnionpayPropertyUtil.CHARSET));
		}
	}


	/**
	 * 组装银联支付resultUrl
	 * 
	 * @param orderNumber
	 *            订单id
	 * @param ch
	 * @param amount订单金额
	 * @return resultUrl
	 * 
	 */
	public static String montageResultUrl(String orderNumber, String amount,String resultUrl) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("orderId", orderNumber);
		map.put("amount", amount);
		final String digest = attachParams(map, false, false);
		String unionResultUrl =resultUrl+"?" + digest + "&argname=";
		return unionResultUrl;
	}

	
	/**
//	 * 组装银联支付resultUrl
//	 * @param orderNumber
//	 * @param amount
//	 * @param payDate
//	 * @param payType
//	 * @return
//	 * @author cuibinqiang 
//	 */
//	public static String montageResultUrlNew(String orderNumber, String amount,
//			String payDate, String payType,String userId,String payFlag) {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("orderid", orderNumber);
//		map.put("amount", amount);
//		map.put("payDate", payDate);
//		map.put("payType", payType);
//		map.put("userId", userId);
//		map.put("payFlag", payFlag);
//		final String digest = attachParams(map, false, false);
////		String resultUrl = UnionUtil.RESULTURL_BASEPATH
////				+ "/payokaction!result?" + digest + "&argname="; 
////		String resultUrl = "http://111.204.231.199:9999/mshangpin"
////				+ "/payokaction!result?" + digest + "&argname=";
//		String resultUrl = "http://192.168.9.223/mshangpin"
//				+ "/payokaction!result?" + digest + "&argname=";
//		return resultUrl;
//	}
//
//	/**
//	 * 组装银联支付resultUrl mock
//	 * 
//	 * @param orderNumber
//	 *            订单id
//	 * @param ch
//	 * @param amount订单金额
//	 * @return resultUrl
//	 * 
//	 */
//	public static String mockMontageResultUrl(String orderNumber, String ch,
//			String amount) {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("orderNumber", orderNumber);
//		map.put("ch", ch);
//		map.put("amount", amount);
//		final String digest = attachParams(map, false, false);
//		String resultUrl = UnionUtil.RESULTURL_BASEPATH
//				+ "/payokaction!unionpayResult?" + digest + "&argName=";
//		return resultUrl;
//	}

//	/**
//	 * 交易查询处理
//	 * 
//	 * @param req
//	 *            请求要素
//	 * @param resp
//	 *            应答要素
//	 * @return 是否成功
//	 * @throws Exception
//	 */
//	public static boolean query(Map<String, String> req) throws Exception {
//		String signature = UnionUtil.buildSignature(req, UnionUtil.SECRET);
//		req.put(UnionUtil.KEY_SIGNATURE_METHOD, UnionUtil.SIGNATURE_METHOD);
//		req.put(UnionUtil.KEY_SIGNATURE, signature);
//		final String postData = UnionUtil.attachParams(req, false, true);
//		String orderid = req.get("orderNumber");
//		String ordertime = req.get("orderTime");
//		long now = System.currentTimeMillis();
//		Entity entity = UnionUtil.OrderQueue.get(orderid);
//		if (entity == null) {
//			entity = new Entity();
//			entity.orderid = orderid;
//			entity.firstTime = now;
//		}
//		entity.ordertime = ordertime;
//		entity.tn = null;
//		String content = WebUtil.readContentFromPost(UnionUtil.URL_QUERY,
//				postData);
//		HashMap<String, String> resp = new HashMap<String, String>();
//		boolean valid = UnionUtil.verifyResponse(content, UnionUtil.SECRET,
//				resp);
//		if (valid) {
//			String respCode = resp.get(UnionUtil.KEY_CODE);
//			if ("00".equals(respCode)) {
//				return true;
//			} else {
//				return false;
//			}
//		} else {
//			return false;
//		}
//	}

	
}
