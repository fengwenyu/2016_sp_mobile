package com.shangpin.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class WXShareSign {
	 public static void main(String[] args) {
	        String jsapi_ticket = "jsapi_ticket";

	        // 注意 URL 一定要动态获取，不能 hardcode
	        String url = "http://example.com";
	        Map<String, String> ret = sign(jsapi_ticket, url);
	        for (@SuppressWarnings("rawtypes") Map.Entry entry : ret.entrySet()) {
	            System.out.println(entry.getKey() + ", " + entry.getValue());
	        }
	    };

	    /**
	     * 微信分享取得参数
	     * @param jsapi_ticket
	     * @param request
	     * @return
	     */
	    public static Map<String,String> getShareConfig(String jsapi_ticket,HttpServletRequest request){
	    	String url=request.getRequestURL().toString();
			String  queryStr=request.getQueryString();
			if(!StringUtils.isEmpty(queryStr)){
				url+="?"+queryStr;
			}
			Map<String,String> shareConfig=sign(jsapi_ticket, url);
			return shareConfig;
	    }
	    
	    public static Map<String, String> sign(String jsapi_ticket, String url) {
	        Map<String, String> ret = new HashMap<String, String>();
	        String nonce_str = create_nonce_str();
	        String timestamp = create_timestamp();
	        String string1;
	        String signature = "";

	        //注意这里参数名必须全部小写，且必须有序
	        string1 = "jsapi_ticket=" + jsapi_ticket +
	                  "&noncestr=" + nonce_str +
	                  "&timestamp=" + timestamp +
	                  "&url=" + url;
	        System.out.println(string1);

	        try
	        {
	            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	            crypt.reset();
	            crypt.update(string1.getBytes("UTF-8"));
	            signature = byteToHex(crypt.digest());
	        }
	        catch (NoSuchAlgorithmException e)
	        {
	            e.printStackTrace();
	        }
	        catch (UnsupportedEncodingException e)
	        {
	            e.printStackTrace();
	        }

	        ret.put("url", url);
	        ret.put("jsapi_ticket", jsapi_ticket);
	        ret.put("nonceStr", nonce_str);
	        ret.put("timestamp", timestamp);
	        ret.put("signature", signature);
	        ret.put("appId","wx4e93df954af2f52c");
	        return ret;
	    }

	    private static String byteToHex(final byte[] hash) {
	        Formatter formatter = new Formatter();
	        for (byte b : hash)
	        {
	            formatter.format("%02x", b);
	        }
	        String result = formatter.toString();
	        formatter.close();
	        return result;
	    }

	    private static String create_nonce_str() {
	        return UUID.randomUUID().toString();
	    }

	    private static String create_timestamp() {
	        return Long.toString(System.currentTimeMillis() / 1000);
	    }
}