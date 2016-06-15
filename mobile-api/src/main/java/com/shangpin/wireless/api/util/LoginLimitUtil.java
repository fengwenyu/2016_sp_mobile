package com.shangpin.wireless.api.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.LoginImeiCache;
import com.shangpin.wireless.api.domain.LoginIpCache;

/**
 * 登录限制
 * 
 * @Author: zhouyu
 * @CreatDate: 2013-05-14
 */
public class LoginLimitUtil {
	protected final static Log log = LogFactory.getLog(LoginLimitUtil.class);

	public static Map<String, LoginImeiCache> imeiMap = new HashMap<String, LoginImeiCache>();
	public static Map<String, Map<String, LoginIpCache>> ipMap = new HashMap<String, Map<String, LoginIpCache>>();

	/** 同一imei的设备，在5分钟，用5个账号以上登录，返回失败 **/
	// 添加Imei登录限制
	public static boolean addImeiLoginCache(String imei, String loginName) {
		LoginImeiCache cache = imeiMap.get(imei);
		if (cache == null) {
			cache = new LoginImeiCache();
			cache.setTimestamp(System.currentTimeMillis());
			cache.setImei(imei);
			cache.getSet().add(loginName);
			imeiMap.put(imei, cache);
		} else {
			long currentTimeMillis = System.currentTimeMillis();
			long timestamp = cache.getTimestamp();
			if ((currentTimeMillis - timestamp) <= (1000 * 60 * 5)) {
				if (cache.getSet().size() >= 5) {
					cache.setTimestamp(currentTimeMillis);
					imeiMap.put(imei, cache);
					return false; // 登录受限
				} else {
					cache.getSet().add(loginName);
					imeiMap.put(imei, cache);
				}
			} else {
				cache.getSet().clear();
				cache.getSet().add(loginName);
				cache.setTimestamp(currentTimeMillis);
				cache.setImei(imei);
				imeiMap.put(imei, cache);
			}
		}
		return true;// 登录无需限制
	}

	/** 同一IP 20秒钟不能超过5次把 **/
	// 添加IP登录限制
	public static boolean addIpLoginCache(String ip, String loginName, HttpServletRequest request) {
		long currentTimeMillis = System.currentTimeMillis();
//		FileUtil.addLog(request, "IpLoginLimit",//
//				"ip", ip,//
//				"loginName", loginName,//
//				"timestamp", currentTimeMillis + "");
//		System.out.println("IpLoginLimit--------ip:" + ip + ",loginName:" + loginName + ",timestamp:" + currentTimeMillis);
		Map<String, LoginIpCache> map = ipMap.get(ip);
		if (map == null) {
			map = new HashMap<String, LoginIpCache>();
			LoginIpCache cache = new LoginIpCache();
			cache.setTimestamp(currentTimeMillis);
			cache.setLimitTimestamp(-1l);
			cache.setCount(1);
			map.put(loginName, cache);
		} else {
			LoginIpCache cache = map.get(loginName);
			if (cache == null) {
				cache = new LoginIpCache();
				cache.setTimestamp(currentTimeMillis);
				cache.setLimitTimestamp(-1l);
				cache.setCount(1);
			} else {
				long timestamp = cache.getTimestamp();
				long limitTimestamp = cache.getLimitTimestamp();
				if ((currentTimeMillis - limitTimestamp) <= (1000 * 60 * 10)) {
					cache.setTimestamp(currentTimeMillis);
					cache.setLimitTimestamp(currentTimeMillis);
					map.put(loginName, cache);
					ipMap.put(ip, map);
					return false; // 登录受限
				} else {
					cache.setLimitTimestamp(-1l);
				}
				if ((currentTimeMillis - timestamp) <= (1000 * 20)) {
					if (cache.getCount() >= 5) {
						cache.setTimestamp(currentTimeMillis);
						cache.setLimitTimestamp(currentTimeMillis);
						map.put(loginName, cache);
						ipMap.put(ip, map);
						return false; // 登录受限
					} else {
						cache.setCount(cache.getCount() + 1);
					}
				} else {
					cache.setTimestamp(currentTimeMillis);
					cache.setCount(1);
				}
			}
			map.put(loginName, cache);
		}
		ipMap.put(ip, map);
		return true;// 登录无需限制
	}

	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		set.add("1");
		set.add("1");
//		System.out.println(set.size());
	}
}
