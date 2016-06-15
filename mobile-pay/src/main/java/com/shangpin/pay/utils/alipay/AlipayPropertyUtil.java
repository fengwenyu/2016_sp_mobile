/**
 * create on 2014-10-08
 */
package com.shangpin.pay.utils.alipay;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sunweiwei
 * @version v1.0
 */
public class AlipayPropertyUtil {
    public static Logger logger = LoggerFactory.getLogger(AlipayPropertyUtil.class);
    
    private static final String CONFIG_FILE_NAME = "alipay.properties";
    
    private static final PropertiesConfiguration config = new PropertiesConfiguration();
    
    public static final String CHARSET = "UTF-8";
    
    static {
        config.setEncoding(CHARSET);
        try {
            config.load(AlipayPropertyUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        } catch (ConfigurationException e) {
            logger.error("init config error:", e);
        }
    }
    
    public static boolean devModule = config.getBoolean("dev_module", false);

	public static final String payFacadeUrl= config.getString("pay_facade_url","http://192.168.3.79:9080/pay/request");;
    
    public static final String OUTSIDE_SIGN_TYPE_RSA = "RSA";
    
    public static final String OUTSIDE_SIGN_TYPE_MD5 = "MD5";

    public static final String OUTSIDE_PAYMENT_TYPE = "1";
    
	public static final String OUTSIDE_FOREX_BIZ = "FP";

	// 海外支付宝海关备案号
	public static final String OUTSIDE_FILING_NUMBER = "PTE51001412170000003";
	// 海外支付宝海关备案名称
	public static final String OUTSIDE_FILING_NAME = "北京尚品百姿电子商务有限公司";
	// 海外支付宝海关编号
	public static final String OUTSIDE_CUSTOMS_NAME = "GUANGZHOU";
    
	public static final String SEC_ID = config.getString("sec_id","0001");

	public static final String REQ_URL = config.getString("req_url","https://wappaygw.alipay.com/service/rest.htm");
	// 商户自己生成的RSA私钥
	public static final String RSA_PRIVATE =  config.getString("rsa_private","MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALvooo3sKEvnX0fDLSz5z48pbKdRwlwHadGjSSwLxAGvlCU5ce5cx1PnSnLvljSenH6W9CO1GOdkLEmycCVLWaagOUD0/T4m4O7mZtQ/Tf2ybzgD8eyGBr2DvYCgmdyvFZMNmWJyUz75RlT+ZHwVyLcR9swk7v0wWEcGRMXb7NdVAgMBAAECgYBgcFW1JwXLZvTUnMv7sD4hSG5WNTTeDIP3rqjoLSWsg9Mxg5kAb6dxXVwtPt9FJD5HDexAwzoDz7qXHDhIO7LHW4Hqbr86o7RZBb/9Hb12jfRsqepG67Ii/rJPBkxuz4nVKMwvoBoLyemWVu3T/9vZAtcgFpKCwSOhZOcOqjq8RQJBAN1g0mEfAMANVY/rZYctUxLvO4Ot0QJmRbOoTIZRmnRx2LMtDUqoWu0gdAhMntwQganWef03bBGKfrConjpCXM8CQQDZS9Ek6H8nlfs/G7s2izwyn3/QJx4zezbE+8bi1uH0y4degAbpE4GoqQBOvRgw8fKTbMR93tCfYCPsLWS7PHqbAkAE7odBSqOFo8ZoMmJMpKHZJiM5R8IEP6sF2ZX62A6M0Yx2UWaeZym/Zp2vIaXYWTXKRtDo5zctHNy9qsP7oEbZAkBShqLlu48fP6zuKOAPNQ4lw8HNEi48Kx1/7od4e1fRmsLMtx7owknJ/nVpchOP8zPHndV6YHcnMGI0PoE1QyDlAkEAmwEMtZMPGIlih7hUX8vQR05/plEH1U+yme/sH0zKjLHVcT3qC/Za+XL8ed9sGJAy9iR5I6mkhEJPj9+hGLLGCA==");;
	// 国内支付宝商户编号
	public static final String PARTNER = config.getString("partner","2088101954925026");
	// 国内支付宝商户编号
	public static final String SELLER_ID = config.getString("partner","2088101954925026");
	// 国内支付宝RSA公钥串
	public static final String RSA_ALIPAY_PUBLIC = config.getString("rsa_alipay_public","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDdIZIDa4jnhlq/2lGi9cuh5HHk0sKyxDdvp4tHrk836qDCEjtSbAUDS+sA2VHKgtEey2Vb25TnOzohoZpYy7ohW5V0VZ13zj68bfUoqAXWbtzBP4nwmgarl7dsmz7f9QxpGo5TS5sj+BRB+b/ZC1foJ5QorY0v0uBGtO1M9ELLqwIDAQAB");
	// 支付宝商户email
	public static final String SELLER_EMAIL = config.getString("seller_email","zhifubao@vansky.cn");
	
    // 海外支付宝请求接口url
	public static final String OUTSIDE_URL = config.getString("alipay.outside_url","https://mapi.alipay.com/gateway.do");
	// 海外支付宝商户编号
	public static final String OUTSIDE_PARTNER = config.getString("alipay.outside_partner","2088511068544509");
	// 海外支付宝商户编号
	public static final String OUTSIDE_SELLER_ID = config.getString("alipay.outside_partner","2088511068544509");
	// 海外支付宝提供的MD5私钥
	public static final String OUTSIDE_MD5_PRIVATE_KEY = config.getString("alipay.outside_md5_private_key","7x902h7nig4if8zc17mxvyic4qv7r35f");
	// 海外支付宝提供的RSA公钥
	public static final String OUTSIDE_RSA_PUBLIC_KEY = config.getString("alipay.outside_rsa_public_key","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB");
	// 商户自己生成的RSA私钥
	public static final String OUTSIDE_RSA_PRIVATE_KEY = config.getString("alipay.outside_rsa_private_key","MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMk01Zogv9FIb7r1UtJN5CKbIce872m3xXMZJ/rIjnJpMk2fNNVQ/5wOJLMQ17NOaoZ/14mlU1Ok8g1QDkEH+xCM9pkTaecyN8dQ+PS4Hy2kiWAlIkCzsJftNNYMxUpLcmMamrZhAS2GQQo9sQNUGbrLWIrwFst9wN9/VNVmPaDPAgMBAAECgYAhLDHPUHjvk0bAhzPZcngbcII4QSEjx+Wo3UvnH+vEVy2w+H+ob8tEulw4nZ1CdbpAZJFvoy9Cyh4SLdG7zQoKXJDyM7eMa9VVAYCso8xbMMXgQKO7OsjaEP2DhBwXq5lb6nIFoGNRQOHZgkF3Dw7mmVVezgRBfDPv6XNt5scymQJBAO+VTAJZP5V7KBEq6tGaeBUqwfR5YTC+dXkilC4xaiz1dVAlsbNvD49YcawgSO22XYbhnLby9HSMzRP0iR+4m+sCQQDW/leL/Sb/wSzFFYQq3z1K63Jq2XJAiH4BgNG9m0vnl1uWbeijyXjGJYAvsPdrh8nGTS5BaEbCkLtEs5ffwwmtAkEAr/79kjAbvGR3P31GeGk/01TQnBXaZqNSyoSzU+eq9pHt9s1p58UrKgMvpeElkbJIrD/qDp8VItUYBZO6c5n6twJBAIMIW0VX0lgYXXf1CCu1Cb9Kxt52jxrj1aCG6c5dAyMwbcK+VAbXOUPTAVCP1Ax2ozS4P5PfwRi+a1SQ/OSFE7UCQFBtlOcRKyl4VRPKp2BrXm0ZGL3bEZAYU0/mvstPcH/CFiWckW/ay0BjMhI6q0/ixF7GCj1GOsrzDnxFa2wG43s=");
	// 海外支付宝结算币种
	public static final String OUTSIDE_CURRENCY = config.getString("alipay.outside_currency", "USD");
    // 海外支付宝未支付的过期时间
    public static final String OUTSIDE_IT_B_PAY ="30m";
	// 海外支付宝APP请求支付的service名称
	public static final String OUTSIDE_APP_PAY_SERVICE = config.getString("alipay.outside_app_pay_service","mobile.securitypay.pay");
	// 海外支付宝WAP请求支付的service名称
	public static final String OUTSIDE_WAP_TRADE_SERVICE = config.getString("alipay.outside_wap_trade_service","create_forex_trade_wap");
	// 海外支付宝验证notifyId的service名称
	public static final String OUTSIDE_NOTIFY_VERIFY_SERVICE = config.getString("alipay.outside_notify_verify_service", "notify_verify");
	
    public static void main(String[] args) {
        System.out.println(devModule);
    }

}
