package com.shangpin.wireless.api.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class RSACipher {
	
	private static final Log log = LogFactory.getLog(RSACipher.class.getSimpleName());
	private static final String KEY_ALGORITHM = "RSA";

	private static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";


	private static byte[] PUBLIC_KEYBYTE = null;

	private static byte[] PRIVATE_KEYBYTE = null;

	private static String CHARSET = "UTF-8";

	private static byte[] loadKeyFile(String keyFile) throws Exception {
		byte[] bArr = new byte[0];

		FileInputStream fis = new FileInputStream(keyFile);
		BufferedInputStream bis = new BufferedInputStream(fis);
		bArr = new byte[bis.available()];
		bis.read(bArr);
		bis.close();

		return bArr;
	}

	private static String bytes2hex(byte[] bArr) {
		// 一个字节的数转成16进制字符串
		String hs = "";
		String stmp = "";
		for (int i = 0; i < bArr.length; i++) {
			// 整数转成十六进制表示
			stmp = (Integer.toHexString(bArr[i] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase(); // 转成大写
	}

	private static byte[] hex2Bytes(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	public static void setPublicKey(String keyFile) throws Exception {
		PUBLIC_KEYBYTE = loadKeyFile(keyFile);
	}

	public static void setPrivateKey(String keyFile) throws Exception {
		PRIVATE_KEYBYTE = loadKeyFile(keyFile);
	}

	public static byte[] getPublicKey() {
		return PUBLIC_KEYBYTE;
	}

	public static byte[] getPrivateKey() {
		return PRIVATE_KEYBYTE;
	}


	// 签名
	public static String sign(String data) throws Exception {
		return encryptByPrivateKey(MD5Encode(data));
	}
	


	 //验证
	public static boolean verify(String data, String sign) throws Exception {
		String md5 = MD5Encode(data);
		String md5_2 = decryptByPublicKey(sign);
		if (md5.equals(md5_2)) return true;
		return false;
	}


	//MD5
	private static byte[] MD5Encode(byte[] data) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		return md.digest(data);

	}

	//	MD5
	private static String MD5Encode(String data) throws Exception {
		return bytes2hex(MD5Encode(data.getBytes(CHARSET)));

	}


	// 私钥解密
	public static byte[] decryptByPrivateKey(byte[] data) throws Exception {
		return decryptByPrivateKey(data, PRIVATE_KEYBYTE);
	}

	// 私钥解密
	public static String decryptByPrivateKey(String data) throws Exception {
		return new String(
				(decryptByPrivateKey(hex2Bytes(data), PRIVATE_KEYBYTE)),
				CHARSET);
	}

	// 私钥解密

	// 私钥解密
	public static byte[] decryptByPrivateKey(byte[] data, byte[] privateKeyBytes)
			throws Exception {

		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(
				privateKeyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	// 公钥解密
	public static byte[] decryptByPublicKey(byte[] data) throws Exception {
		return decryptByPublicKey(data, PUBLIC_KEYBYTE);
	}

	// 公钥解密
	public static String decryptByPublicKey(String data) throws Exception {
		return new String(decryptByPublicKey(hex2Bytes(data), PUBLIC_KEYBYTE),
				CHARSET);
	}

	// 公钥解密

	// 公钥解密
	public static byte[] decryptByPublicKey(byte[] data, byte[] publicKeyBytes)
			throws Exception {

		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	// 公钥加密
	public static byte[] encryptByPublicKey(byte[] data) throws Exception {
		return encryptByPublicKey(data, PUBLIC_KEYBYTE);
	}

	// 公钥加密
	public static String encryptByPublicKey(String data) throws Exception {
		return bytes2hex(encryptByPublicKey(data.getBytes(CHARSET),
				PUBLIC_KEYBYTE));
	}

	// 公钥加密

	// 公钥加密
	public static byte[] encryptByPublicKey(byte[] data, byte[] publicKeyBytes)
			throws Exception {

		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	// 私钥加密
	public static byte[] encryptByPrivateKey(byte[] data) throws Exception {
		return encryptByPrivateKey(data, PRIVATE_KEYBYTE);
	}

	// 私钥加密
	public static String encryptByPrivateKey(String data) throws Exception {
		return bytes2hex(encryptByPrivateKey(data.getBytes(CHARSET),
				PRIVATE_KEYBYTE));
	}

	// 私钥加密
	public static byte[] encryptByPrivateKey(byte[] data, byte[] privatekeyBytes)
			throws Exception {

		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(
				privatekeyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	public static void main(String[] args) {
		try {
			setPublicKey("mepf_publickey.der");
			setPrivateKey("mepf_privatekey.der");

			String plainStr = "txrd天讯瑞达11051010053119611051000680055910001000天下通10元1";

			// MD5
			String md5 = MD5Encode(plainStr);

			// 签名
			String hexSign = sign(plainStr);

			// byte[] utf8 = plainStr.getBytes("UTF-8");
			// byte[] gbk = plainStr.getBytes("GBK");

			// 认证
			boolean f = verify(plainStr, hexSign);
			if (f) {
				log.debug("RSACipher :verify success");
			} else {
				log.debug("RSACipher :verify fail");
			}

			// 加解密测试
			String cipherStr1 = encryptByPrivateKey(plainStr);
			// byte[] cipherByte =
			// encryptByPrivateKey(plainStr.getBytes("UTF-8"));
			String plainStr1 = decryptByPublicKey(cipherStr1);

			String cipherStr2 = encryptByPublicKey(plainStr);
			String plainStr2 = decryptByPrivateKey(cipherStr2);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("RSACipher :"+e);
		}
	}

}