package com.shangpin.api.utils;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 /**
 * MD5加密工具类
 */
public class MD5Util {
    // 日志
    private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);
  
	/**
	 * 使用MD5加密
	 * 
	 * @param content
	 *            待加密的字符串内容
	 * 
	 * @return 返回MD5加密后的字符串
	 * @throws Exception
	 */
	public static String md5Digest(String content) throws Exception {
		MessageDigest md5 = null;
		md5 = MessageDigest.getInstance("MD5");
		char[] charArray = content.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	
    /**
     * 进行参数加密
     * 
     * @param params
     *            多个参数
     * @return String
     */
    public static String md5PackParameter(String... params) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < params.length; i++) {
                stringBuilder.append(params[i]);
            }
            return MD5Util.md5Digest(stringBuilder.toString());
        } catch (Exception e) {
            logger.error("md5 error:", e);
            return null;
        }
    }
} 
