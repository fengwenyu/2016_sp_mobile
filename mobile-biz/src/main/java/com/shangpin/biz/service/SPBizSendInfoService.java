package com.shangpin.biz.service;

import java.util.Map;

import com.shangpin.biz.bo.base.ResultBase;

/** 
* @ClassName: BizeSendInfoService 
* @Description: 发送手机信息 
* @author qinyingchun
* @date 2014年11月20日
* @version 1.0 
*/
public interface SPBizSendInfoService {
	
	/**
	 * 
	* @Title: sendInfo 
	* @Description:下发短息通知
	* @param userid 用户id标识
	* @param phone 用户手机号
	* @param msgTempl 短信模板
	* @return boolean
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年11月20日
	 */
	public boolean sendInfo(String userid, String phone, String msgTempl);
	
	/**
	 * 
	* @Title: verifyPhoneCode 
	* @Description: 验证手机发送的验证码
	* @param 
	* @return 
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年12月4日
	 */
	public ResultBase verifyPhoneCode(String userid, String phone, String verifycode);

	Map<String, Object> sendPhoneCode(String userid, String phone,
			String msgTempl);

}
