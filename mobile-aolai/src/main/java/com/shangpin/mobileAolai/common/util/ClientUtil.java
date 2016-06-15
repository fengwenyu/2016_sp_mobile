package com.shangpin.mobileAolai.common.util;

public class ClientUtil {
	
	//微信用户ua
	public static final String MIRCRO_MSG = "micromessenger";
	//app用户ua
	public static final String[] APP_MSG = {"AolaiIOSApp","ShangpinAndroidApp","AolaiAndroidApp","ShangpinIOSApp"};
	
	/**
	 * 判断客户端是否为微信
	 * 
	 * @Author wanghaibo
	 * @CreateDate 2014-1-28
	 * @param user-agent
	 * @Return
	 */
	public static boolean CheckMircro(String uaString){
		if(uaString.indexOf(MIRCRO_MSG)>-1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断客户端是否为APP
	 * 
	 * @Author wanghaibo
	 * @CreateDate 2014-1-28
	 * @param user-agent
	 * @Return
	 */
	public static boolean CheckApp(String uaString){
		 boolean appFlag = false;
		    for(int i=0;i<APP_MSG.length;i++){
		    	 if(uaString.indexOf(APP_MSG[i].toLowerCase())>-1){
		    		appFlag = true;
				    break;
		    	 }
		    }
		 return appFlag;
	}
}

