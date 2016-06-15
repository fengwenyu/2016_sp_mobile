package com.shangpin.pay.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shangpin.pay.bo.CommonBackBo;
import com.shangpin.pay.bo.UnionPayBo;
import com.shangpin.pay.exception.ServiceException;
/**
 * 银联支付请求接口
 * User: liling
 * Date: 14-11-10
 * Time: 下午3:23
 */
public interface UnionpayService extends BaseService {
	/**
	 * 银联wap支付 
	 * return 银联支付url
	 * 
	 * */
	String wapPay(UnionPayBo unionPayBo) throws ServiceException;
	/**
	 * 银联wap支付同步通知处理
	 * return 是否验签成功以及业务参数
	 * 
	 * */
	CommonBackBo callBack(HttpServletRequest request) throws ServiceException;
	/**
	 * 银联wap支付异步通知处理
	 * return 是否验签成功
	 * 
	 * */
	CommonBackBo notify(HttpServletRequest request) throws ServiceException;
	Map<String, Object> wapPayV2(UnionPayBo unionPayBo) throws ServiceException;
}
