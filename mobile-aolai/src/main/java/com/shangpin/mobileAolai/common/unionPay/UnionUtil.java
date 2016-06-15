package com.shangpin.mobileAolai.common.unionPay;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.FileUtil;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.mobileAolai.common.alipay.util.Base64;
import com.shangpin.mobileAolai.common.util.WebUtil;

public class UnionUtil {
	static final String KEY_CHARSET = "charset";
	static final String KEY_SIGNATURE_METHOD = "signMethod";
	static final String KEY_SIGNATURE = "signature";
	static final String KEY_ORDER_ID = "orderNumber";
	static final String KEY_TN = "tn";
	static final String KEY_CODE = "respCode";
	static final String KEY_MESSAGE = "respMsg";
	static final String CHARSET = "UTF-8";
	static final String SIGNATURE_METHOD = "MD5";
	static final String PAYDATA = "paydata";
	static final String MERCHANT; // 商户编号
	static final String SECRET; // 商户密钥
	static final String URL_BUY; // 订单推送地址
	static final String URL_QUERY; // 订单查询地址
	static final String URL_NOTIFY; // 接收通知URL
	static final String RESULTURL_BASEPATH;// 返回url通用部分
	static final String KEY_TRANS_STATUS = "transStatus";
	private static Log logInfo = LogFactory.getLog(UnionUtil.class);
	static {
	
			MERCHANT = Constants.NUMBER_URL;
			SECRET = Constants.SECRET_KEY_URL;
			URL_BUY = Constants.TRADE_URL+"trade";
			URL_QUERY = Constants.TRADE_URL+"query";
			URL_NOTIFY = Constants.UNIONPAY_NOTIFY_URL;
			RESULTURL_BASEPATH = Constants.UNIONPAY_CALLBACK_URL;// 前面必须有"http://"
		
	}
	static final HashMap<String, Entity> OrderQueue = new HashMap<String, Entity>();

	static final String buildSignature(Map<String, String> params, String secret) {
		final String digest = UnionUtil.attachParams(params, true, false);
        final String origin = digest + "&" + DigestUtils.md5Hex(secret);
        String signature = null;
		try {
			signature = DigestUtils.md5Hex(origin.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return signature;
	}

	static final String attachParams(Map<String, String> params, boolean sort, boolean encode) {
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
					value = URLEncoder.encode(value, CHARSET);
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
	static boolean verifyResponse(String respString, String secret, Map<String, String> resp) {
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

	static boolean verifyParams(Map<String, String> params, String secret, Map<String, String> resp) {
		if (params.size() > 0) {
			String respSignature = params.get(KEY_SIGNATURE);
			// 除去数组中的空值和签名参数
			for (String key : params.keySet()) {
				String value = params.get(key);
				if (value == null || value.equals("") || key.equalsIgnoreCase(KEY_SIGNATURE) || key.equalsIgnoreCase(KEY_SIGNATURE_METHOD)) {
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

	static boolean verifyParamsfornew(Map<String, String[]> params, String secret, Map<String, String> resp) {
		if (params.size() > 0) {
			// String respSignature = params.get(KEY_SIGNATURE);
			// paravalue = ((String[])params.get(KEY_SIGNATURE))[0];
			String respSignature = ((String[]) params.get(KEY_SIGNATURE))[0];
			// 除去数组中的空值和签名参数
			for (Object key : params.keySet()) {
				String value = params.get(key)[0].toString();
				if (value == null || value.equals("") || ((String) key).equalsIgnoreCase(KEY_SIGNATURE) || ((String) key).equalsIgnoreCase(KEY_SIGNATURE_METHOD)) {
					continue;
				}
				resp.put((String) key, value);
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
	private static Map<String, String> parseQString(String str) throws UnsupportedEncodingException {
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

	private static void putKeyValueToMap(StringBuilder temp, boolean isKey, String key, Map<String, String> map) throws UnsupportedEncodingException {
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
			map.put(key, URLDecoder.decode(temp.toString(), CHARSET));
		}
	}

	public static void handleNotify(String notifyData, String secret) {
		final String payTypeId = "19";
		final String payTypeChildId = "49";
		HashMap<String, String> resp = new HashMap<String, String>();
		boolean valid = UnionUtil.verifyResponse(notifyData, secret, resp);
		if (valid) {
			final String respCode = resp.get(UnionUtil.KEY_CODE);
        	if (!"00".equals(respCode)) {
        		logInfo.debug(resp.get(UnionUtil.KEY_MESSAGE));
        		return;
        	}
        	final String transStatus = resp.get(UnionUtil.KEY_TRANS_STATUS);
        	if (!"00".equals(transStatus)) {
        		logInfo.debug(transStatus);
        		return;
        	}
          	String orderid = resp.get(UnionUtil.KEY_ORDER_ID);
			if(orderid.indexOf("gift")>-1){
				orderid=orderid.split("gift")[0];
			}
//			Map<String, String> reqmap = new HashMap<String, String>();
//			reqmap.put("orderno", orderid);
//			reqmap.put("paytypeId", payTypeId);
//			reqmap.put("paytypechildId", payTypeChildId);
//			String url = Constants.BASE_URL + "payconfirmpayment/";
			
			HashMap<String, String> reqmap = new HashMap<String, String>();
			reqmap.put("mainorderNo", orderid);
			reqmap.put("payTypeId", payTypeId);
			reqmap.put("childPayTypeId", payTypeChildId);
			reqmap.put("orderAmount", Constants.PAY_AMOUNT);
			String url = Constants.BASE_NEWAPI_URL_SP + "order/UpdateOrderStatus";
			
			try {
				long now = System.currentTimeMillis();
				Entity entity = OrderQueue.get(orderid);
				if (entity == null) {
					entity = new Entity();
					entity.orderid = orderid;
					entity.firstTime = now;
				}
				if (!entity.isPaid) {
					String data = WebUtil.readContentFromGet(url, reqmap);
					JSONObject content = JSONObject.fromObject(data);
					final String code = content.getString("code");
					if ("0".equals(code)) {
						entity.isPaid = true;
						// 记录访问日志
						FileUtil.unionutilAddLog(null, "paysuccess", "orderid", orderid, "payid", payTypeId, "paychildid", payTypeChildId, "success", "0");
					} else {
						FileUtil.unionutilAddLog(null, "paysuccess", "orderid", orderid, "payid", payTypeId, "paychildid", payTypeChildId, "success", "1", "code", code, "msg", content.getString("msg"));
					}
				}
				entity.checkTimes++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			logInfo.debug("Unionpay system notify verify signature failure!");
		}
	}

	public static void handleNotify(Map<String, String[]> notifyData, String secret) {
		final String payTypeId = "19";
		final String payTypeChildId = "49";
		HashMap<String, String> resp = new HashMap<String, String>();
		boolean valid = verifyParamsfornew(notifyData, secret, resp);
		if (valid) {
			final String respCode = resp.get(UnionUtil.KEY_CODE);
        	if (!"00".equals(respCode)) {
        		logInfo.debug(resp.get(UnionUtil.KEY_MESSAGE));
        		return;
        	}
        	final String transStatus = resp.get(UnionUtil.KEY_TRANS_STATUS);
        	if (!"00".equals(transStatus)) {
        		logInfo.debug(transStatus);
        		return;
        	}
          	String orderid = resp.get(UnionUtil.KEY_ORDER_ID);
			if(orderid.indexOf("gift")>-1){
				orderid=orderid.split("gift")[0];
			}
			HashMap<String, String> reqmap = new HashMap<String, String>();
			reqmap.put("mainorderNo", orderid);
			reqmap.put("payTypeId", payTypeId);
			reqmap.put("childPayTypeId", payTypeChildId);
			reqmap.put("orderAmount", Constants.PAY_AMOUNT);
			String url = Constants.BASE_NEWAPI_URL_SP+ "order/UpdateOrderStatus";
			try {
				long now = System.currentTimeMillis();
				Entity entity = OrderQueue.get(orderid);
				if (entity == null) {
					entity = new Entity();
					entity.orderid = orderid;
					entity.firstTime = now;
				}
				if (!entity.isPaid) {
					String data = WebUtil.readContentFromGet(url,  reqmap);
					JSONObject content = JSONObject.fromObject(data);
					final String code = content.getString("code");
					if ("0".equals(code)) {
						entity.isPaid = true;
						// 记录访问日志
						FileUtil.unionutilAddLog(null, "paysuccess", "orderid", orderid, "payid", payTypeId, "paychildid", payTypeChildId, "success", "1");
					} else {
						FileUtil.unionutilAddLog(null, "paysuccess", "orderid", orderid, "payid", payTypeId, "paychildid", payTypeChildId, "success", "0", "code", code, "msg", content.getString("msg"));
					}
				}
				entity.checkTimes++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			logInfo.debug("Unionpay system notify verify signature failure!");
		}
	}

	public static final void checkQueue() {
		for (String key : OrderQueue.keySet()) {
			final long now = System.currentTimeMillis();
			Entity entity = OrderQueue.get(key);
			if (now - entity.firstTime >= 60 * 1000 * 60) {
				OrderQueue.remove(entity);
			} else if (!entity.isPaid) {
				final long elapse = now - entity.firstTime;
				if (entity.checkTimes > 5) {
					OrderQueue.remove(entity);
					continue;
				}
				if (elapse <= 60 * 1000 * 2 // 下单2分钟
						|| elapse <= 60 * 1000 * (2 + entity.checkTimes)) {
					continue;
				}
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("version", "1.0.0");
				map.put(UnionUtil.KEY_CHARSET, UnionUtil.CHARSET);
				map.put("transType", "01"); // 01 消费; 02 预授权
				map.put("merId", UnionUtil.MERCHANT);
				map.put("orderTime", entity.ordertime);
				map.put(UnionUtil.KEY_ORDER_ID, entity.orderid);
				// map.put("merReserved", "abc"); // 商户保留域
				try {
					String signature = UnionUtil.buildSignature(map, UnionUtil.SECRET);
					map.put(UnionUtil.KEY_SIGNATURE_METHOD, UnionUtil.SIGNATURE_METHOD);
					map.put(UnionUtil.KEY_SIGNATURE, signature);
					final String postData = UnionUtil.attachParams(map, false, true);
					String notifyData = WebUtil.readContentFromPost(UnionUtil.URL_QUERY, postData);
					UnionUtil.handleNotify(notifyData, UnionUtil.SECRET);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class Entity {
		String orderid;
		String ordertime;
		String tn;
		boolean isPaid;
		int checkTimes;
		long firstTime;
	}

	/**
	 * 组装银联支付参数paydata
	 * 
	 * @param tn
	 *            交易流水号
	 * @param resultUrl
	 *            回调url
	 * @return paydata
	 * 
	 */
	public static String returnPayDate(String tn, String resultUrl) throws UnsupportedEncodingException {
		// usetestmode 是否使用银联测试环境标识 ture使用银联测试环境测试 false使用银联生产环境
		String paydata = Base64.getBASE64("tn=" + tn + ",resultURL=" + URLEncoder.encode(resultUrl.toString(), "UTF-8") + ",usetestmode=" + Constants.UNION_PAYMENT + "");
		paydata = URLEncoder.encode(paydata, "UTF-8");
		return paydata;
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
	public static String montageResultUrl(String orderNumber, String ch, String amount) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("orderid", orderNumber);
		map.put("amount", amount);
		final String digest = UnionUtil.attachParams(map, false, false);
		String resultUrl = UnionUtil.RESULTURL_BASEPATH + "?" + digest + "&argname=";
		return resultUrl;
	}


	/**
	 * 交易查询处理
	 * 
	 * @param req
	 *            请求要素
	 * @param resp
	 *            应答要素
	 * @return 是否成功
	 * @throws Exception
	 */
	public static boolean query(Map<String, String> req) throws Exception {
		String signature = UnionUtil.buildSignature(req, UnionUtil.SECRET);
		req.put(UnionUtil.KEY_SIGNATURE_METHOD, UnionUtil.SIGNATURE_METHOD);
		req.put(UnionUtil.KEY_SIGNATURE, signature);
		final String postData = UnionUtil.attachParams(req, false, true);
		String orderid = req.get("orderNumber");
		String ordertime = req.get("orderTime");
		long now = System.currentTimeMillis();
		Entity entity = UnionUtil.OrderQueue.get(orderid);
		if (entity == null) {
			entity = new Entity();
			entity.orderid = orderid;
			entity.firstTime = now;
		}
		entity.ordertime = ordertime;
		entity.tn = null;
		String content = WebUtil.readContentFromPost(UnionUtil.URL_QUERY, postData);
		HashMap<String, String> resp = new HashMap<String, String>();
		boolean valid = UnionUtil.verifyResponse(content, UnionUtil.SECRET, resp);
		if (valid) {
			String respCode = resp.get(UnionUtil.KEY_CODE);
			if ("00".equals(respCode)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static String getSecret() {
		return SECRET;
	}
}
