package com.shangpin.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @author sunweiwei
 * @version v1.0
 */
public class MD5Util {

    public static String MD5(String sourceStr) {
        try {
            // 获得MD5摘要算法的 MessageDigest对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(sourceStr.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int tmp = md[i];
                if (tmp < 0)
                    tmp += 256;
                if (tmp < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(tmp));
            }
            // return buf.toString().substring(8, 24);// 16位加密
            return buf.toString().toUpperCase();// 32位加密
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String MD5NoUpper(String sourceStr) {
    	try {
    		// 获得MD5摘要算法的 MessageDigest对象
    		MessageDigest mdInst = MessageDigest.getInstance("MD5");
    		// 使用指定的字节更新摘要
    		mdInst.update(sourceStr.getBytes());
    		// 获得密文
    		byte[] md = mdInst.digest();
    		// 把密文转换成十六进制的字符串形式
    		StringBuffer buf = new StringBuffer();
    		for (int i = 0; i < md.length; i++) {
    			int tmp = md[i];
    			if (tmp < 0)
    				tmp += 256;
    			if (tmp < 16)
    				buf.append("0");
    			buf.append(Integer.toHexString(tmp));
    		}
    		// return buf.toString().substring(8, 24);// 16位加密
    		return buf.toString();// 32位加密
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    /**
     * Used building output as Hex
     */

    /**
     * 对字符串进行MD5加密
     *
     * @param text
     *            明文
     * @param charset 字符编码，nullable
     * @return 密文
     */
    public static String md5(String text,String charset) {
    	try {
    		if(charset==null)
    			return DigestUtils.md5Hex(text);
			return DigestUtils.md5Hex(text.getBytes(charset));
		} catch (UnsupportedEncodingException e1) {
			return null;
		}
        /*MessageDigest msgDigest = null;

        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    "System doesn't support MD5 algorithm.");
        }

        try {
            msgDigest.update(text.getBytes(charset));

        } catch (UnsupportedEncodingException e) {

            throw new IllegalStateException(
                    "System doesn't support your  EncodingException.");

        }

        byte[] bytes = msgDigest.digest();

        String md5Str = new String(encodeHex(bytes));

        return md5Str;*/
    }
    public static void main(String[] args) {
        String str = "ALIWAP|2015100908096|http://pre.shangpin.com/mshangpin/pay/callback/ALIWAP|订单号:2015100908096|243.0020d25a781966242057ed941c39e43713";
        String encryptStr = MD5(str);
        System.out.println("加密前：" + str);
        System.out.println("加密后：" + encryptStr);
    }
}