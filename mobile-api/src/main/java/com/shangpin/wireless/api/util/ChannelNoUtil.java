package com.shangpin.wireless.api.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;


public class ChannelNoUtil {
		
	 /**
		 * 获取渠道号
		 */
	public static String  getChannelNo(HttpServletRequest request){
		String channelNo = request.getHeader(Constants.CHANNEL_PARAM_NAME);
		String productNo = request.getHeader(Constants.PRODUCT_PARAM_NAME);
		if(StringUtils.isEmpty(channelNo)){
			if(CookieUtil.getCookieByName(request, Constants.CHANNEL_PARAM_NAME) != null){
				channelNo = CookieUtil.getCookieByName(request, Constants.CHANNEL_PARAM_NAME).getValue();
				}else {
					//根据产品号，判断尚品/奥莱渠道号
					if(Constants.SP_PRODUCT_NAME.equals(productNo)){
						channelNo = Constants.SP_WAP_DEFAULT_CHANNELNO;
					}else if(Constants.AL_PRODUCT_NAME.equals(productNo)){
						channelNo = Constants.AOLAI_WAP_DEFAULT_CHANNELNO;
					}
				}
			}
			return channelNo;
		}
}
