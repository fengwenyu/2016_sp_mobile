package com.shangpin.wechat.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author ZRS
 * @version v1.0
 * @Date 2015-10-15 09:15:51
 */
public class MerchantPlatformConstants extends CommonConstants {
    public static Logger logger = LoggerFactory.getLogger(MerchantPlatformConstants.class);

    //密钥
    public static final String PARTNER_KEY = "030d62e64075b703a28592ce672220da";
    //加密证书路径
//    public static final String CERT_PATH = "D:\\cert\\apiclient_cert.p12";
    public static final String CERT_PATH = "com/shangpin/wechat/certs/wxcash_apiclient_cert.p12";
//    public static final String CERT_PATH = "wxcash_apiclient_cert.p12";
    //证书密码
    public static final String CERT_PASSWORD = "1217974201";
	//商户号
    public static final String MERCHANT_ID = "1217974201";
	//商户名称
    public static final String MERCHANT_NAME = "尚品网CEO";//32个字符以内
    //APPID
    public static final String APP_ID = "wx4e93df954af2f52c";
    //现金红包发送地址
    public static final String MERCHANT_CASH_BONUS_URI = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
    //代金券发送地址
    public static final String MERCHANT_COUPON_URI = "https://api.mch.weixin.qq.com/mmpaymkttransfers/send_coupon";
    
	
}
