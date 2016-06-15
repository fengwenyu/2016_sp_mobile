package com.shangpin.wechat.utils.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.JedisUtil;
import com.shangpin.wechat.constants.CommonConstants;

/**
 * 微信请求数据、组装数据等工具类
 * 
 * @author huangxiaoliang
 *
 */
public class CacheUtil {
	public static Logger logger = LoggerFactory.getLogger(CacheUtil.class);
	private static JedisUtil jedisUtil = JedisUtil.getInstance();

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
	 * @param way
	 *            请求方式：post/get
	 * @return
	 */
	public static String findData(String methodType, String method, Map<String, String> params, String url, String way) {
		long start = System.currentTimeMillis();
		String key = calculateKey(methodType, method, params);
		String data = null;
		try {
			// 以下为默认缓存设置取数据
			if (invalidKey(key)) {
				data = refreshData(key, method, params, url, way);
				return data;
			}
			data = getCache(key);
			long end = System.currentTimeMillis();
			logger.info(methodType + ":" + method + " get data from redis, cost time: " + (end - start) + "ms");
			logger.info("key:" + key + ", get data from redis");
		} catch (Exception e) {
			data = doHttp(url, params, way);
			CacheUtil.cacheResult(data, key, method);
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
	 * 验证key是否是无效的缓存key
	 * 
	 * @param key
	 * @return
	 */
	public static boolean invalidKey(String key) {
		return (!jedisUtil.exists(key) || jedisUtil.new Keys().ttl(key) == -1);
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
	public static String refreshData(String key, String method, Map<String, String> params, String url, String way) {
		deleteKey(key);
		String data = doHttp(url, params, way);
		CacheUtil.cacheResult(data, key, method);
		return data;
	}

	/**
	 * 字符串中存在某个key的值，则表示true，其他都是false
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static boolean isSuccess(String json) throws Exception {

		return json.contains("expires_in");
	}

	/**
	 * 缓存接口数据
	 * 
	 * @param data
	 *            接口返回的数据
	 * @param key
	 *            缓存key
	 * @param method
	 *            接口名
	 */
	public static String cacheResult(String data, String key, String method) {
		try {
			if (isSuccess(data)) {
				jedisUtil.set(key, data);
				jedisUtil.expire(key, WeChatPropertyUtil.getIntValue(method, 0));
			}
		} catch (Exception e) {
		}
		return data;
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
	 * 删除缓存中key对应的数据
	 * 
	 * @param key
	 */
	public static void deleteKey(String key) {
		jedisUtil.new Keys().del(key);
	}

	/**
	 * 通过HTTP取得请求结果
	 * 
	 * @param url
	 *            url地址
	 * @param params
	 *            url对应的参数Map
	 * @return
	 */
	public static String doHttp(String url, Map<String, String> params, String way) {
		String result = "";
		if (CommonConstants.POST.equals(way)) {
			result = HttpClientUtil.doPost(url, params);
		} else if (CommonConstants.GET.equals(way)) {
			result = HttpClientUtil.doGet(url, params);
		}
		return result;
	}

}
