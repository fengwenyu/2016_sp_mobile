package com.shangpin.wireless.api.weixin.bazaar;

import java.util.HashMap;

import net.sf.json.JSONObject;

import com.shangpin.wireless.api.util.WebUtil;

public class TokenUtil {

	private static final String URL_BASE = "https://api.weixin.qq.com/cgi-bin/";
	private static long updateTime;
	private static int accessExpiresSec;
	private static String APP_ID = "wx4e93df954af2f52c";
	private static String APP_SECRET = "2a55eede9fbd467e25e6a0eb7b17ce60";
	public static String getAccessToken(String appId, String appSecret) {
		String accessToken = null;
		if (System.currentTimeMillis() - updateTime >= accessExpiresSec * 1000) {
			String url = URL_BASE + "token";
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("grant_type", "client_credential");
			map.put("appid", appId);
			map.put("secret", appSecret);
			try {
				String data = WebUtil.readContentFromGet(url, map);
				JSONObject obj = JSONObject.fromObject(data);
				accessToken = obj.getString("access_token");
				accessExpiresSec = obj.getInt("expires_in"); // 访问令牌生命周期的秒数
				updateTime = System.currentTimeMillis();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return accessToken;
	}
	
	public static void main(String[] args){
//		System.out.println(2^24);
	}
}
