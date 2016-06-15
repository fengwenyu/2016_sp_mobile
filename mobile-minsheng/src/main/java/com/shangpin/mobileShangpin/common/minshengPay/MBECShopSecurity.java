package com.shangpin.mobileShangpin.common.minshengPay;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.log4j.Logger;

import Union.JnkyServer;

import com.shangpin.mobileShangpin.common.util.Constants;

public class MBECShopSecurity {
	private static Logger logger = Logger.getLogger(MBECShopSecurity.class);

	String bankPublicKey=null;
	String ecShopPrivateKey=null;
	String ecShopPrivateKeyPwd = "1111";

	/**
	 * 商户加密订单
	 */
	public String encryptOrder(String orderPlain) {
		logger.debug("orderPlain:" + orderPlain);
		JnkyServer my = null;
		try {
			if(Constants.MINSHENG_PAYMENT){
				bankPublicKey=MBECShopSecurity.class.getResource("new_bank1024.cer").getPath();
				ecShopPrivateKey=MBECShopSecurity.class.getResource("06005.pfx").getPath();
				ecShopPrivateKeyPwd="1111";
			}
			else{
				bankPublicKey=MBECShopSecurity.class.getResource("cmbc1024-BASE64.cer").getPath();
				ecShopPrivateKey=MBECShopSecurity.class.getResource("01287.pfx").getPath();
				ecShopPrivateKeyPwd="438117";
			}
			my = new JnkyServer(bankPublicKey, ecShopPrivateKey,
					ecShopPrivateKeyPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (my == null)
			return "JnkyServer is null";
		String envolopData = "";
		try {
			envolopData = my.EnvelopData(orderPlain);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("商户加密订单:" + envolopData);
		return envolopData;
	}

	/**
	 * 商户解密交易结果
	 * 
	 * @param tradeResultEncrypt
	 *            交易结果明文
	 * @return 解密后的交易结果
	 */
	public String decryptTradeResult(String tradeResultEncrypt)
			throws Exception {
			if(Constants.MINSHENG_PAYMENT){
				bankPublicKey=MBECShopSecurity.class.getResource("new_bank1024.cer").getPath();
				ecShopPrivateKey=MBECShopSecurity.class.getResource("06005.pfx").getPath();
				ecShopPrivateKeyPwd="1111";
			}
			else{
				bankPublicKey=MBECShopSecurity.class.getResource("cmbc1024-BASE64.cer").getPath();
				ecShopPrivateKey=MBECShopSecurity.class.getResource("01287.pfx").getPath();
				ecShopPrivateKeyPwd="438117";
			}
		JnkyServer my1 = new JnkyServer(bankPublicKey, ecShopPrivateKey,
				ecShopPrivateKeyPwd);
		String plainText = my1.DecryptData(tradeResultEncrypt);
		logger.debug("商户解密交易结果:" + plainText);

		return plainText;
	}

	public String readFile(String path) {
		StringBuffer result = new StringBuffer();
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String str = "";
			while ((str = in.readLine()) != null) {
				result.append(str.trim());
			}
		} catch (Exception e) {
			logger.error("读取证书失败," + e.getMessage());
			
		}
		return result.toString();
	}

}
