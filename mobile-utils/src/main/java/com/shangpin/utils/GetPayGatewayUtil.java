package com.shangpin.utils;



import org.apache.commons.lang3.StringUtils;

import com.shangpin.utils.JedisUtilFactory;



public class GetPayGatewayUtil {
	private static final String ALI_GATEWAY_KEY="ALIPayGateway";
	private static final String ALI_MAIN_PAY_MODE="30";
	private static final String ALI_FZAPP_PAY="ALIFZAPP";
    private static final String ALI_APPSEA_PAY="ALIAPPSEA";
	public static String getGateway(){
		String value=JedisUtilFactory.getDefaultJedisUtil().get(GetPayGatewayUtil.getkey());
		if(StringUtils.isEmpty(value)){
			return ALI_APPSEA_PAY;
		}
		return value;
	}
	public static String setGateway(String val){
		if(StringUtils.isBlank(val)||val.length()!=1){
			return null;
		}
		String key = GetPayGatewayUtil.getkey();
		if("0".equals(val)){
			JedisUtilFactory.getDefaultJedisUtil().set(key,ALI_FZAPP_PAY );
			JedisUtilFactory.getDefaultJedisUtil().expire(key, 0);//永久保存
			return ALI_FZAPP_PAY;
		}
		if("1".equals(val)){
			JedisUtilFactory.getDefaultJedisUtil().set(key,ALI_APPSEA_PAY );
			JedisUtilFactory.getDefaultJedisUtil().expire(key, 0);//永久保存
			return ALI_APPSEA_PAY;
		}
		return null;
	}
	public static String getkey(){
		StringBuilder gateway = new StringBuilder();
		gateway.append(ALI_GATEWAY_KEY);
		gateway.append("_");
		gateway.append(ALI_MAIN_PAY_MODE);
		return gateway.toString();
	}
}
