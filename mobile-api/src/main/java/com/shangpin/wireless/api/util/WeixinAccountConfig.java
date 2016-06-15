package com.shangpin.wireless.api.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class WeixinAccountConfig {

	private final static HashMap<String, String> weixinAccountCashe = new HashMap<String,String>();
	static{
		if (weixinAccountCashe == null || weixinAccountCashe.size() == 0) {
			Properties props = PropertiesUtil.getInstance("/weixin/wxaccount.properties");
			for (Enumeration e = props.keys(); e.hasMoreElements();) {
				String propkey = (String) e.nextElement();
				String propvalue = props.getProperty(propkey);
				weixinAccountCashe.put(propkey, propvalue.toString());
			}
		}
	}
	public static String getAccValue(String key){
		return weixinAccountCashe.get(key);
	}
}
