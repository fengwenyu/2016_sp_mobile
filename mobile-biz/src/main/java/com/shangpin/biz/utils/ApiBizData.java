package com.shangpin.biz.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.octo.captcha.service.captchastore.CaptchaAndLocale;
import com.shangpin.base.utils.PropertyUtil;
import com.shangpin.biz.bo.Pay;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.JSONUtils;
import com.shangpin.utils.JedisUtil;

/**
 * 返回给客户端自定义信息工具类
 * 
 * @author huangxiaoliang
 *
 */
public class ApiBizData {

	private static JedisUtil jedisUtil = JedisUtil.getInstance();
	private final static Log log = LogFactory.getLog(ApiBizData.class);
	private static String BLACK_IP = "black";
	private static String WHITE_IP = "white";
	private static String IP_COUNT = "count";

	/**
	 * 给定的json串中的code存在并且等于0，则表示true，其他都是false
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static boolean isSuccess(String json) throws Exception {
		ResultBase resultBase = JSONUtils.json2pojo(json, ResultBase.class);
		return resultBase.isSuccess();
	}

	/**
	 * 缓存主站接口数据
	 * 
	 * @param data
	 *            主站接口返回的数据
	 * @param key
	 *            缓存key
	 * @param method
	 *            主站接口名
	 */
	public static String cacheResult(String data, String key, String method) {
		try {
		    int expire = PropertyUtil.getInt(method,0);
			if (expire != 0 && isSuccess(data)) {
				jedisUtil.set(key, data);
				jedisUtil.expire(key, expire);
			}
		} catch (Exception e) {
		}
		return data;
	}

	/**
	 * 返回需要的接口数据 (主要用缓存来管理数据)
	 * 
	 * @param methodType
	 *            使用的标识
	 * @param method
	 *            使用的方法名
	 * @param params
	 *            主站接口对应的url参数map
	 * @param url
	 *            主站接口对应的url
	 * @return
	 */
	public static String findData(String methodType, String method, Map<String, String> params, String url) {
		long start = System.currentTimeMillis();
		String key = calculateKey(methodType, method, params);
		String data = null;
		try {
			// 以下为默认缓存设置取数据
			if (invalidKey(key)) {
				data = refreshData(key, method, params, url);
				return data;
			}
			data = getCache(key);
			long end = System.currentTimeMillis();
			log.info(methodType + ":" + method + " get data from redis, cost time: " + (end - start) + "ms");
		} catch (Exception e) {
			data = httpDoGet(url, params);
			return data;
		}
		return data;
	}

	/**
	 * 计算出缓存key
	 * 
	 * @param methodType
	 *            方法的类型
	 * @param method
	 *            方法名称
	 * @param params
	 *            url对应的参数Map
	 * @return
	 */
	public static String calculateKey(String methodType, String method, Map<String, String> params) {
		String key = JedisUtil.piecedKey(methodType, method, params);
		return key;
	}

	/**
	 * 通过HTTP的get方法取得请求结果
	 * 
	 * @param url
	 *            url地址
	 * @param params
	 *            url对应的参数Map
	 * @return
	 */
	public static String httpDoGet(String url, Map<String, String> params) {
		String result = HttpClientUtil.doGet(url, params);
		return result;
	}

	/**
	 * 刷新缓存中的数据
	 * 
	 * @param key
	 *            缓存key
	 * @param method
	 *            方法名
	 * @param params
	 *            url对应的参数Map
	 * @param url
	 *            url地址
	 * @return
	 */
	public static String refreshData(String key, String method, Map<String, String> params, String url) {
		deleteKey(key);
		String data = httpDoGet(url, params);
		ApiBizData.cacheResult(data, key, method);
		return data;
	}

	/**
	 * 删除缓存中key对应的数据
	 * 
	 * @param key
	 */
	public static void deleteKey(String key) {
		jedisUtil.new Keys().del(key);
	}

	/**
	 * 验证key是否是无效的缓存key
	 * 
	 * @param key
	 * @return
	 */
	public static boolean invalidKey(String key) {
		return (!jedisUtil.exists(key) || jedisUtil.new Keys().ttl(key) == -1);
	}

	/**
	 * 通过Key从缓存中取出数据
	 * 
	 * @param key
	 * @return
	 */
	public static String getCache(String key) {
		return jedisUtil.get(key);
	}

	/**
	 * 通过Key从缓存中取出数据
	 * 
	 * @param key
	 * @return
	 */
	public static byte[] getCache(byte[] key) {
		return jedisUtil.get(key);
	}

	/**
	 * 是否黑名单
	 * 
	 * @param ip
	 * @return
	 */
	public static boolean isBlackIp(String ip) {
		return checkIpOfType(ip, BLACK_IP);
	}

	/**
	 * 是否白名单
	 * 
	 * @param ip
	 * @return
	 */
	public static boolean isWhiteIp(String ip) {
		return checkIpOfType(ip, WHITE_IP);
	}

	/**
	 * ip类型
	 * 
	 * @param ip
	 * @param ipType
	 * @return
	 */
	private static boolean checkIpOfType(String ip, String ipType) {
		boolean flag = false;
		try {
			String type = jedisUtil.get(ip);
			if (type != null && type.equals(ipType)) {
				flag = true;
			}
		} catch (Exception e) {

		}
		return flag;
	}

	/**
	 * ip访问次数
	 * 
	 * @param ip
	 * @return
	 */
	public static long findIpOfCount(String ip) {
		long count = 0;
		try {
			count = Long.parseLong(jedisUtil.get(ip + IP_COUNT));
		} catch (Exception e) {
		}
		return count;
	}

	/**
	 * 增加黑名单
	 * 
	 * @param ip
	 */
	public static void addIpToBlack(String ip) {
		try {
			jedisUtil.set(ip, "black");
		} catch (Exception e) {

		}
	}

	/**
	 * 增加ip访问次数
	 * 
	 * @param ip
	 */
	public static void incrIp(String ip) {
		try {
			String key = ip + IP_COUNT;
			if (!jedisUtil.exists(ip) || jedisUtil.new Keys().ttl(key) == -1) {// 不存在的话
																				// 或者过期
				jedisUtil.new Keys().del(key);
				jedisUtil.incr(ip);
				jedisUtil.expire(key, PropertyUtil.getInt("time_interval"));
			} else {
				jedisUtil.incr(ip);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 递增key对应的数据值，如果不存在或过期则设置了过期时间
	 * @param key
	 * @param expire
	 * @author zghw
	 */
	public static void incrKey(String key,int expire){
		try {
			if (!jedisUtil.exists(key) || jedisUtil.new Keys().ttl(key) == -1) {// 不存在的话
																				// 或者过期
				jedisUtil.new Keys().del(key);
				jedisUtil.incr(key);
				jedisUtil.expire(key,expire);
			} else {
				jedisUtil.incr(key);
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * 不建议使用，非动态参数，不易扩展。
	 * 
	 * @param code
	 * @param msg
	 * @param flag
	 *            是否针对客户端替换主站返回的msg
	 * @param parms
	 *            针对传入的唯一参数选择返回的code、msg,默认为null
	 * @param str
	 * @return
	 */
	@Deprecated
	public static String changeCode(String code, String msg, boolean flag, String parms, String str) {

		return returnData(str, code, msg);

	}

	/**
	 * 将主站数据处理后的对象，拼接上标识信息返回客户端
	 * 
	 * @param obj
	 *            对象：javaBean/list/map...
	 * @param params
	 *            动态参数，规则是第一下标是code信息，第二下标是msg信息、即：["code","msg"];第三下标是预留参数
	 * @return
	 * @throws Exception
	 */
	public static String spliceData(Object obj, String... params) throws Exception {
		String str = JSONUtils.obj2json(obj);
		return returnData(str, params);

	}

	public static String spliceData(String... params) throws Exception {
		String code = params[0];
		String msg = params[1];
		String data = "{\"code\":\"" + code + "\",\"msg\":\"" + msg + "\",\"content\":{}}";
		return data;

	}

	private static String returnData(String str, String... params) {
		String data = null;
		StringBuffer strBuffer = new StringBuffer();
		String code = params[0];
		String msg = params[1];
		if (Constants.SUCCESS.equals(code)) {
			strBuffer.append("{\"code\":\"" + code + "\",\"msg\":\"" + msg + "\",\"content\":");
			strBuffer.append(str);
			strBuffer.append("}");
			data = strBuffer.toString();

		} else {
			data = "{\"code\":\"" + code + "\",\"msg\":\"" + msg + "\",\"content\":{}}";
		}
		return data;
	}

	/**
	 * 客户端公共支付方式
	 * 
	 * @param region
	 *            1:国内;2:海外
	 * @param codFlag
	 *            1:支持货到付款;2:不支持货到付款
	 * @return
	 */
	public static List<Pay> payWay(String region, String codFlag) {
		List<Pay> payList = new ArrayList<Pay>();
		if ("2".equals(region)) {
			Pay pay0 = new Pay();
			pay0.setId("30");
			pay0.setName("支付宝钱包支付");
			pay0.setEnable("1");
			payList.add(pay0);

			Pay pay4 = new Pay();
			pay4.setId("32");
			pay4.setName("微信支付");
			pay4.setEnable("1");
			payList.add(pay4);
		} else if ("1".equals(region)) {
			Pay pay1 = new Pay();
			pay1.setId("20");
			pay1.setName("支付宝");
			pay1.setEnable("1");
			payList.add(pay1);

			Pay pay3 = new Pay();
			pay3.setId("27");
			pay3.setName("微信支付");
			pay3.setEnable("1");
			payList.add(pay3);
			
			Pay pay2 = new Pay();
			pay2.setId("19");
			pay2.setName("银联支付");
			pay2.setEnable("1");
			payList.add(pay2);

			if ("1".equals(codFlag)) {
				Pay pay4 = new Pay();
				pay4.setId("2");
				pay4.setName("货到付款");
				pay4.setEnable("0");
				payList.add(pay4);
			}
		}

		return payList;
	}
	
	public static List<Pay> payWay(String region, String codFlag, String version) {
		List<Pay> payList = new ArrayList<Pay>();
		if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.4", version) == 1) {
			if ("2".equals(region)) {
				Pay pay0 = new Pay();
				pay0.setId("32");
				pay0.setName("微信支付");
				pay0.setEnable("1");
				payList.add(pay0);
				Pay pay4 = new Pay();
				pay4.setId("30");
				pay4.setName("支付宝钱包支付");
				pay4.setEnable("1");
				payList.add(pay4);

			} else if ("1".equals(region)) {
				Pay pay1 = new Pay();
				pay1.setId("27");
				pay1.setName("微信支付");
				pay1.setEnable("1");
				payList.add(pay1);

				Pay pay3 = new Pay();
				pay3.setId("20");
				pay3.setName("支付宝");
				pay3.setEnable("1");
				payList.add(pay3);

				Pay pay2 = new Pay();
				pay2.setId("19");
				pay2.setName("银联支付");
				pay2.setEnable("1");
				payList.add(pay2);

				Pay pay4 = new Pay();
				pay4.setId("2");
				pay4.setName("货到付款");
				if ("1".equals(codFlag)) {
					pay4.setEnable("1");
				} else {
					pay4.setEnable("0");
				}
				payList.add(pay4);
			}
		} else {
			if ("2".equals(region)) {
				Pay pay0 = new Pay();
				pay0.setId("30");
				pay0.setName("支付宝钱包支付");
				pay0.setEnable("1");
				payList.add(pay0);
				Pay pay4 = new Pay();
				pay4.setId("32");
				pay4.setName("微信支付");
				pay4.setEnable("1");
				payList.add(pay4);

			} else if ("1".equals(region)) {
				Pay pay1 = new Pay();
				pay1.setId("20");
				pay1.setName("支付宝");
				pay1.setEnable("1");
				payList.add(pay1);

				Pay pay3 = new Pay();
				pay3.setId("27");
				pay3.setName("微信支付");
				pay3.setEnable("1");
				payList.add(pay3);

				Pay pay2 = new Pay();
				pay2.setId("19");
				pay2.setName("银联支付");
				pay2.setEnable("1");
				payList.add(pay2);

				Pay pay4 = new Pay();
				pay4.setId("2");
				pay4.setName("货到付款");
				if ("1".equals(codFlag)) {
					pay4.setEnable("1");
				} else {
					pay4.setEnable("0");
				}
				payList.add(pay4);
			}
		}

		return payList;
	}

	public static Object ByteToObject(byte[] bytes) {
		Object obj = null;
		try {
			// bytearray to object
			ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
			ObjectInputStream oi = new ObjectInputStream(bi);

			obj = oi.readObject();
			bi.close();
			oi.close();
		} catch (Exception e) {
			log.error("ByteToObject error: " + e);
			e.printStackTrace();
		}
		return obj;
	}

	public static byte[] ObjectToByte(java.lang.Object obj) {
		byte[] bytes = null;
		try {
			// object to bytearray
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);

			bytes = bo.toByteArray();

			bo.close();
			oo.close();
		} catch (Exception e) {
			log.error("ObjectToByte error: " + e);
			e.printStackTrace();
		}
		return bytes;
	}

	public static void cacheCaptcha(String key, CaptchaAndLocale image) {
		jedisUtil.set(key, ObjectToByte(image));
		jedisUtil.expire(key, 60);
	}

	public static CaptchaAndLocale findCaptcha(String key) {
		CaptchaAndLocale data = null;
		if (!invalidKey(key)) {
			byte[] s = key.getBytes();
			data = (CaptchaAndLocale) ByteToObject(getCache(s));
		}
		return data;
	}

}
