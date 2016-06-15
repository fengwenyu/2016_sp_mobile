package com.shangpin.mobileShangpin.common.util;

import java.security.MessageDigest;

/**
 * MD5加密工具类
 */
public class MD5Util {
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
}
