package com.shangpin.pay.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shangpin.pay.bo.CommonBackBo;
import com.shangpin.pay.bo.WXPayBo;
import com.shangpin.pay.bo.WXPayDataBo;
import com.shangpin.pay.bo.WeChatPayBo;
import com.shangpin.pay.exception.ServiceException;
/**
 * 微信支付请求接口
 * User: liling
 * Date: 14-11-10
 * Time: 下午3:23
 */
public interface WeixinpayService extends BaseService{
	
	/**
	 * 微信jsApi支付
	 * return WXPayDataBo
	 * 
	 * */
	WXPayDataBo jsApiPay(WXPayBo wxpayBo) throws ServiceException;
	
	/**
	 * 微信补单通知，验证签名是否正确
	 * return boolean
	 * 
	 * */
	CommonBackBo notify(HttpServletRequest request) throws ServiceException;
	
	/**
	 * 供客户端调起微信时所需的参数
	 * 
	 */
	WeChatPayBo WeChatPay(Map<String, String> inParams) throws Exception;

	Map<String, Object> pubPay(WXPayBo wxPayBo) throws ServiceException;

	Map<String, Object> wapPay(WXPayBo wxPayBo) throws ServiceException;

	Map<String, Object> pubSeaPay(WXPayBo wxPayBo) throws ServiceException;
}
