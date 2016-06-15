package com.shangpin.wireless.api.pay.lakalapay;

import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;

import com.shangpin.wireless.api.domain.Constants;

public class LakalaPayModel {
	
	public static final String VERSION = "20060301";
	public static final String MERCHANT_PASSWORD = "SPBC3HH74RFGK98UI3Y7T87QWEGVBVN";
	public static final String MAC_TYPE = "2";
	private String data;
	private String merchant;	//	商户编号
	private String minCode;	//	快捷固定账单号
	private String random;	//	随机数

	public String getData () {return data;}
	public String getMerchant () {return merchant;}
	public String getMinCode () {return minCode;}
	public String getRandomNum () {return random;}

	/**
	 * 拉卡拉支付
	 * 
	 * @param orderid
	 *            订单编号
	 * @param totalFee
	 *            订单金额，以分为单位
	 * @param test
	 *            是否测试环境
	 * @return
	 */
	public LakalaPayModel(String orderid, int totalFee) throws SignatureException, Exception {

		final String SEPARATOR = "|";
		final String merKey;
		final String notifyUrl;
		
		merchant = Constants.LAKALAPAY_MERCHANT_VARIATE;
		minCode = Constants.LAKALAPAY_MINCODE_VARIATE;
		merKey = Constants.LAKALAPAY_MERKEY_VARIATE;
		notifyUrl = Constants.LAKALAPAY_URL +"lakala_notify";
		
		random = String.valueOf(System.currentTimeMillis());
		if (random.length() > 6) {
			random = random.substring(random.length()-6);
		}
		
//		ver|merId|商户密码|orderId|amount(单位分)|random|支付URL|macType|minCode
		StringBuilder digestBuff = new StringBuilder();
		digestBuff.append(VERSION).append(SEPARATOR);
		digestBuff.append(merchant).append(SEPARATOR);
		digestBuff.append(merKey).append(SEPARATOR);
		digestBuff.append(orderid).append(SEPARATOR);
		digestBuff.append(totalFee).append(SEPARATOR);
		digestBuff.append(random).append(SEPARATOR);
		digestBuff.append(notifyUrl).append(SEPARATOR);
		digestBuff.append(MAC_TYPE).append(SEPARATOR);
		digestBuff.append(minCode).append(SEPARATOR);
		final String digest = digestBuff.toString();
		String signature = DigestUtils.md5Hex(digest.getBytes("UTF-8"));

		data = signature;
	}

}
