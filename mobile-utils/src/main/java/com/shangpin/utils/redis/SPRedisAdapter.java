package com.shangpin.utils.redis;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.shangpin.utils.MD5Util;

/** 
 * sp redis的抽象类适配器
 * @date    2016年5月4日 <br/> 
 * @author   陈小峰
 * @since    JDK 7
 */
public abstract class SPRedisAdapter implements SPRedis{

	/**
	 * 取得缓存key
	 * 
	 * @param type 缓存类型
	 *            base, business
	 * @param method 方法名
	 * @param params 请求参数
	 * @return
	 */
	public static String piecedKey(String type, String method, Map<String, String> params) {
		StringBuffer sb = new StringBuffer();
		sb.append(type.toLowerCase());
		sb.append(method.toLowerCase());
		if (params != null && !params.isEmpty()) {
			List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
			String[] keys = params.keySet().toArray(new String[0]);
			Arrays.sort(keys);
			for (String key : keys) {
				String value = params.get(key);
				//参数也统一大小写
				pairs.add(new BasicNameValuePair(key.toLowerCase(), value));
			}
			try {
				sb.append(MD5Util.MD5(EntityUtils.toString(new UrlEncodedFormEntity(pairs, CHARSET))));
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	@Override
	public boolean lock(String key, int timeout) {
		return false;
	}

	@Override
	public boolean isLock(String key) {
		return false;
	}

	@Override
	public boolean unlock(String key, int timeout) {
		return false;
	}
	
}
