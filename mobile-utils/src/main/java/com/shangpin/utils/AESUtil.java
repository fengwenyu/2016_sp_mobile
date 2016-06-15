package com.shangpin.utils;

import java.security.Key;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * 加密算法：Base64.encode(AES.encrypt(data))
 * <p/>
 * 解密算法：AES.decrypt(Base64.decode(data))
 * 
 * @author sunweiwei
 * @version v1.0
 */
public class AESUtil {
	/** 密钥算法 */
	private static final String KEY_ALGORITHM = "AES";

	private static final String KEY = "1936168592476099";
	/** 身份证KEY */
	public static final String IDCARD_KEY = "1936168592476099";

	/**
	 * 解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static String decrypt(byte[] data, byte[] key) throws Exception {
		// 还原密钥
		Key k = new SecretKeySpec(key, KEY_ALGORITHM);
		/**
		 * 实例化 使用PKCS7Padding填充方式，按如下方式实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
		 */
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		// 初始化，设置解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		// 执行操作
		return new String(cipher.doFinal(data), "UTF-8");
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static String decrypt(String data, String key) throws Exception {
		if (StringUtils.isEmpty(data)) {
			return null;
		}
		key = checkKey(key);
		byte[] keyBytes = key.getBytes("UTF-8");
		return decrypt(Base64.decodeBase64(data), keyBytes);
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @return bytes[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 还原密钥
		Key k = new SecretKeySpec(key, KEY_ALGORITHM);
		/**
		 * 实例化 使用PKCS7Padding填充方式，按如下方式实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
		 */
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}

	public static String encrypt(String data, String key) throws Exception {
		key = checkKey(key);
		byte[] dataBytes = data.getBytes("UTF-8");
		byte[] keyBytes = key.getBytes("UTF-8");
		return Base64.encodeBase64String(encrypt(dataBytes, keyBytes));
	}

	/**
	 * 验证key是否存在，如果不存在，使用默认的key
	 * 
	 * @param key
	 * @return
	 */
	public static String checkKey(String key) {
		if (StringUtils.isEmpty(key)) {
			key = KEY;
		}
		return key;
	}

	/**
	 * 
	 * 解码、解压、解密后的返回参数值
	 * 
	 * @param data
	 * @return Map<String, String>
	 * @throws Exception
	 */
	public static Map<String, String> base64ZipAES(String data) throws Exception {
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		data = data.replaceAll(" ", "\\+");
		String bZAResultStr = AESUtil.decrypt(ZipUtil.gunzip(data), KEY);
		String[] strs = bZAResultStr.split("\\&");
		for (String str : strs) {
			String[] param = str.split("\\=");
			if (param.length == 2) {
				map.put(param[0], param[1]);
			} else {
				map.put(param[0], "");
			}
		}
		return map;
	}

	public static void main(String[] args) throws Exception {
		// String key = "";
		// String mingwen = "18632261856|6926668";
		// String mw = "ZFm96f6LHvbwnYf39imp+OS2L/gAO09O5efiO5nzlb8=";
		//
		// System.out.println("密文：" + encrypt(mingwen, "1936168592476088"));
		// String jm = AESUtil.decrypt(mw, key);
		// System.out.println("明文:" + jm);

		// String content = "附件是开发商对开发商的借口就发生发到付件到萨科技法师打发范德萨减肥的萨科技发大水";
		// String password = "12345678";
		// password = checkKey(key);
		// byte[] dataBytes = content.getBytes("UTF-8");
		// byte[] keyBytes = password.getBytes("UTF-8");
		// //加密
		// System.out.println("加密前：" + content);
		// byte[] encryptResult = encrypt(dataBytes, keyBytes);
		// String encryptResultStr = parseByte2HexStr(encryptResult);
		// System.out.println("加密后：" + encryptResultStr);
		// //解密
		// byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);
		// String decryptResult = decrypt(decryptFrom,keyBytes);
		// System.out.println("解密后：" + decryptResult);

	}
}
