package com.shangpin.wechat.utils.publicplatform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.shangpin.wechat.constants.PublicPlatformConstants;

/**
 * 微信公众平台请求数据、组装数据等工具类
 * 
 * @author huangxiaoliang
 *
 */
public class WeChatPublicUtil {

	public static Logger logger = LoggerFactory.getLogger(WeChatPublicUtil.class);
	
	/**
	 * 授权页面url组装
	 * @param response_type
	 * @param scope
	 * @param redirect_uri
	 * @param state
	 * @return
	 */
	public static String authorize(String response_type,String scope,String redirect_uri,String state) {
		 String authorizeurl= PublicPlatformConstants.AUTHORIZE.trim() + "?appid=" + PublicPlatformConstants.PUBLIC_APP_ID.trim() + "&redirect_uri="
				 			  + redirect_uri.trim() + "&response_type=" + response_type + "&scope="+scope;
		 if(!StringUtils.isEmpty(state)){
			 authorizeurl = authorizeurl + "&state=" +state;
		 }
		 authorizeurl= authorizeurl + "#wechat_redirect";
		 return authorizeurl;
	}

}
