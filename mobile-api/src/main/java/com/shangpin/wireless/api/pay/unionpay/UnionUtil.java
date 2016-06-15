package com.shangpin.wireless.api.pay.unionpay;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.WebUtil;

public class UnionUtil {
	
	protected static final Log log = LogFactory.getLog(UnionUtil.class.getSimpleName());
	
	static final String KEY_CHARSET = "charset";
	static final String KEY_SIGNATURE_METHOD	= "signMethod";
	static final String KEY_SIGNATURE	= "signature";
	static final String KEY_ORDER_ID = "orderNumber";
	static final String KEY_TN = "tn";
	static final String KEY_CODE = "respCode";
	static final String KEY_MESSAGE = "respMsg";
	static final String KEY_TRANS_STATUS = "transStatus";
	static final String CHARSET = "UTF-8";
	static final String SIGNATURE_METHOD = "MD5";

	static final String SETTLE_AMOUNT = "settleAmount"; //清算金额
	static final String MERCHANT; // 商户编号
	static final String SECRET;	//	商户密钥
	static final String URL_BUY;	//	订单推送地址
	static final String URL_QUERY;	//	订单查询地址
	static final String URL_NOTIFY;	//	接收通知URL
	static {
		
		MERCHANT = Constants.UNIONU_MERCHANT_VARIATE;
        SECRET = Constants.UNIONU_SECRET_VARIATE;
        URL_BUY = Constants.UNIONU_GATEWAY_URL +"merchant/trade";
        URL_QUERY = Constants.UNIONU_GATEWAY_URL +"merchant/query";
		URL_NOTIFY = Constants.UNIONU_NOTIFY_URL +"unionpay_notify";
		
	}
	
	static final HashMap<String, Entity> OrderQueue = new HashMap<String, Entity>();

	static final String buildSignature (Map<String, String> params, String secret) {
        final String digest = UnionUtil.attachParams(params, true, false);
//        System.out.println("digest = " + digest);
//        System.out.println("secret = " + DigestUtils.md5Hex(secret));
        final String origin = digest + "&" + DigestUtils.md5Hex(secret);
        String signature = null;
		try {
			signature = DigestUtils.md5Hex(origin.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
//        System.out.println("signature = " + signature);
        return signature;
	}
	
	static final String attachParams (Map<String, String> params, boolean sort, boolean encode) {

		ArrayList<String> keys = new ArrayList<String>(params.keySet());
		if (sort) {
			Collections.sort(keys);	//	参数排序
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
            if (i < keys.size() - 1) {//拼接时，不包括最后一个&字符
                sb.append("&");
            }
        }
        
        return sb.toString();
	}
	
	/**
	 * 应答解析
	 * @param respString 应答报文
	 * @param resp 应答要素
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
	            if (value == null || value.equals("") || key.equalsIgnoreCase(KEY_SIGNATURE)
	                || key.equalsIgnoreCase(KEY_SIGNATURE_METHOD)) {
	            	continue;
	            }
	            resp.put(key, value);
	        }
	        String signature = buildSignature(resp, secret);
	        if (null != respSignature && respSignature.equals(signature)) {
				return true;
			}else {
	            return false;
	        }
				
		}
		return false;
	}

	/**
	 * 解析应答字符串，生成应答要素
	 * 
	 * @param str 需要解析的字符串
	 * @return 解析的结果map
	 * @throws UnsupportedEncodingException
	 */
	private static Map<String, String> parseQString(String str) throws UnsupportedEncodingException {

		HashMap<String, String> map = new HashMap<String, String>();
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
			String key, Map<String, String> map) throws UnsupportedEncodingException {
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
	
	static void handleNotify (String notifyData, String secret) {

    	final String payTypeId = "19";
    	final String payTypeChildId = "36";
		HashMap<String, String> resp = new HashMap<String, String>();
		boolean valid = UnionUtil.verifyResponse(notifyData, secret, resp);
        if (valid) {
        	final String respCode = resp.get(UnionUtil.KEY_CODE);
        	if (!"00".equals(respCode)) {
        		log.debug(resp.get(UnionUtil.KEY_MESSAGE));
        		return;
        	}
        	final String transStatus = resp.get(UnionUtil.KEY_TRANS_STATUS);
        	if (!"00".equals(transStatus)) {
        		log.debug("UnionUtil::handleNotify: transStatus = " + transStatus);
        		return;
        	}
        	String orderid = resp.get(UnionUtil.KEY_ORDER_ID);
        	String settleAmount = resp.get(UnionUtil.SETTLE_AMOUNT);
			try {
				long now = System.currentTimeMillis();
				Entity entity = OrderQueue.get(orderid);
				
				if (entity == null) {
					entity = new Entity();
					entity.orderid = orderid;
					entity.firstTime = now;
				}
				if (!entity.isPaid) {
		        	int giftindex = orderid.indexOf("gift");
		        	if (giftindex > 0) {
		        		orderid = orderid.substring(0, giftindex);
		        	}
//		        	HashMap<String, String> reqmap = new HashMap<String, String>();
//					reqmap.put("orderno", orderid);
//					reqmap.put("paytypeId", payTypeId);
//					reqmap.put("paytypechildId", payTypeChildId);
//					String url = Constants.BASE_URL + "payconfirmpayment/";
					HashMap<String, String> reqmap = new HashMap<String, String>();
					reqmap.put("mainorderNo", orderid);
					reqmap.put("payTypeId", payTypeId);
					reqmap.put("childPayTypeId", payTypeChildId);
					reqmap.put("orderAmount", Constants.PAY_AMOUNT);
					String url = Constants.BASE_TRADE_URL + "order/UpdateOrderStatus";
					
					log.debug("UnionUtil::handleNotify:updateOrderStatus " + Thread.currentThread().getName());
    				String data = WebUtil.readContentFromGet(url, reqmap);
					JSONObject content = JSONObject.fromObject(data);
					final String code = content.getString("code");
					if ("0".equals(code)) {
						entity.isPaid = true;
						entity.payTime = now;
						// 记录访问日志
						FileUtil.addLog(null, "paysuccess", "",
								"orderid", orderid, 
								"payid", payTypeId, 
								"paychildid", payTypeChildId, 
								"code", "success");
					    log.debug("Unionpay success orderid = " + orderid + "; " + Thread.currentThread().getName());
					} else {
						FileUtil.addLog(null, "paysuccess", "", 
								"orderid", orderid, 
								"payid", payTypeId, 
								"paychildid", payTypeChildId, 
								"code", "fail", 
								"msg", content.getString("msg"));
					    log.debug("Unionpay failed orderid = " + orderid + " (" + code + ")(" + content.getString("msg") + ")" + Thread.currentThread().getName());
					}
				}
				entity.checkTimes ++;
			} catch (Exception e) {
				e.printStackTrace();
			}
        } else {
//        	("接收银联系统通知验证签名失败，请检查！");
        	log.debug("Receive YinLian notify verify fail");
        }
	}
	
	public static final void checkQueue () {
//		System.out.println("UniPay checkQueue " + OrderQueue.size());
		for (String key:OrderQueue.keySet()) {
			final long now = System.currentTimeMillis();
			Entity entity = OrderQueue.get(key);
			if (now - entity.firstTime >= 60*1000 * 60) {
				OrderQueue.remove(entity);
			} else if (!entity.isPaid) {
				final long elapse = now - entity.firstTime;
				if (entity.checkTimes > 5) {
					OrderQueue.remove(entity);
					continue;
				}
				if (elapse <= 60*1000 * 2	//	下单2分钟
						|| elapse <= 60*1000 * (2 + entity.checkTimes)) {
					continue;
				}
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("version", "1.0.0");
				map.put(UnionUtil.KEY_CHARSET, UnionUtil.CHARSET);
				map.put("transType", "01");	//	01 消费; 02 预授权
				map.put("merId", UnionUtil.MERCHANT);
				map.put("orderTime", entity.ordertime);
				map.put(UnionUtil.KEY_ORDER_ID, entity.orderid);
//				map.put("merReserved", "abc");	//	商户保留域
				
				try {
				String signature = UnionUtil.buildSignature(map, UnionUtil.SECRET);

				map.put(UnionUtil.KEY_SIGNATURE_METHOD, UnionUtil.SIGNATURE_METHOD);
				map.put(UnionUtil.KEY_SIGNATURE, signature);
				final String postData = UnionUtil.attachParams(map, false, true);
//				System.out.println("trade query postData = " + postData);
				
				String notifyData = WebUtil.readContentFromPost(UnionUtil.URL_QUERY, postData);
				UnionUtil.handleNotify(notifyData, UnionUtil.SECRET);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (now - entity.payTime >= 60*1000 * 5) {
				//	已经支付并且超过5分钟
				OrderQueue.remove(entity);
			}
		}
	}
	
    static class Entity {
    	String orderid;
    	String ordertime;
    	String tn;
    	String payamount;
    	boolean isPaid;
    	int checkTimes;
    	long firstTime;
    	long payTime;
    }
    
}
