package com.shangpin.mobileShangpin.common.util;

import java.io.IOException;
import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 3DES加密解密工具类
 */
public class ThreeDES {
	/**
	 * 3DES加密解密算法
	 */
	private static final String Algorithm = "DESede";
	/**
	 * 密钥，长度为24字节
	 */
	private static final String key = "shangpinco.ltd3descipher";

	/**
	 * 通过密钥使用DES方式为byte源数据解密
	 * 
	 * @param keybyte
	 *            加、解密密钥
	 * @param src
	 *            加密byte源数据
	 * 
	 * @return DES解密后的byte数据
	 */
	public static byte[] encrypt(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);// 在单一方面的加密或解密
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过密钥使用DES方式为byte源数据加密
	 * 
	 * @param keybyte
	 *            加、解密密钥
	 * @param src
	 *            解密byte源数据
	 * 
	 * @return DES加密后的byte数据
	 */
	public static byte[] decrypt(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用BASE64将字符串byte数组编码
	 * 
	 * @param arg
	 *            字符串编码为byte数组
	 * 
	 * @return BASE64编码后的字符串
	 */
	public static String base64Enconder(byte[] arg) {
		return new BASE64Encoder().encode(arg);
	}

	/**
	 * 使用BASE64将字符串解码
	 * 
	 * @param arg
	 *            解码字符串
	 * 
	 * @return BASE64解码后的byte[]数组
	 * 
	 * @throws IOException
	 */
	public static byte[] base64Decoder(String arg) throws IOException {
		return new BASE64Decoder().decodeBuffer(arg);
	}

	/**
	 * 将字符串使用DES方式加密、base64方式编码
	 * 
	 * @param str
	 *            加密字符串
	 * 
	 * @return 字符串
	 */
	public static String encryptToString(String str) {
		byte[] keyBytes = key.getBytes();
		byte[] encoded = encrypt(keyBytes, str.getBytes());
		return base64Enconder(encoded);
	}

	/**
	 * 将字符串使用base64方式解码、DES方式解密
	 * 
	 * @param str
	 *            解密字符串
	 * 
	 * @return 字符串
	 */
	public static String decryptToString(String str) throws Exception {
		byte[] keyBytes = key.getBytes();
		byte[] srcBytes = decrypt(keyBytes, base64Decoder(str));
		return (new String(srcBytes));
	}

	public static void main(String[] args) throws Exception {
		String szSrc = "asdfghjklpasdfghjklpasdfghjklppl|logisticeaction!logisticeDetail?orderid=2013020508053&ticketno=120684667147";
		System.out.println(URLEncoder.encode(encryptToString(szSrc)));
		System.out.println(decryptToString(encryptToString(szSrc)));
	}
}