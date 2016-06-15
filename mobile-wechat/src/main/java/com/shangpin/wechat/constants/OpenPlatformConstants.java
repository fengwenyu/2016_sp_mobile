package com.shangpin.wechat.constants;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.wechat.utils.common.WeChatPropertyUtil;

/**
 * 常量（微信开放平台的）
 * @author huangxiaoliang
 *
 */
public class OpenPlatformConstants extends CommonConstants {
    public static Logger logger = LoggerFactory.getLogger(OpenPlatformConstants.class);

    public static final String OPEN_PLATFORM_CACHE = "WeChatOpenPlatform";
    
    public static final String OPEN_TOKEN_URL = WeChatPropertyUtil.getStrValue("open_token_url","https://api.weixin.qq.com/cgi-bin/token");
    
    public static final String OPEN_GATE_URL = WeChatPropertyUtil.getStrValue("open_gate_url","https://api.weixin.qq.com/pay/genprepay");
    
    public static final String OPEN_PARTNER = WeChatPropertyUtil.getStrValue("open_partner","1223657501");
    
	public static final String OPEN_PARTNER_KEY = WeChatPropertyUtil.getStrValue("open_partner_key","62d9b6650d3600fe78c6fd8e9440c4d3");
	
	public static final String OPEN_APP_ID = WeChatPropertyUtil.getStrValue("open_app_id","wx4c7b5d3aa8548d69");
	
	public static final String OPEN_APP_SECRET = WeChatPropertyUtil.getStrValue("open_app_secret","a5503a2112527efb4f21fba5d7f9002c");
	
	public static final String OPEN_APP_KEY = WeChatPropertyUtil.getStrValue("open_app_key","IM5hUtgvaqcUDFU4foBogBRtCcfp0PLkjD338ykFRxZagp3gXjRR7jm3SOL5gzvwImFeFfn69qzplx7AX6OdPkUPqO4gF51kVwGpRbBP4nsqfWlipzuPuGgKDHbs2XCO");

}
