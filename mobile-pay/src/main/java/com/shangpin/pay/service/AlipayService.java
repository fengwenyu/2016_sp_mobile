package com.shangpin.pay.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shangpin.pay.bo.AlipayBo;
import com.shangpin.pay.bo.CommonBackBo;
import com.shangpin.pay.bo.OutsideAlipayBo;
import com.shangpin.pay.exception.ServiceException;
/**
 * 支付宝支付请求接口
 * User: liling
 * Date: 14-11-10
 * Time: 下午3:23
 */
public interface AlipayService  extends BaseService{
	/**
	 * 支付宝wap支付
	 * return 支付宝支付url
	 * 
	 * */
	String wapPay(AlipayBo alipayBo) throws ServiceException;
	/**
	 * 支付宝wap支付
	 * return 支付宝支付url
	 * 
	 * */
	Map<String, Object>  wapPayV2(AlipayBo alipayBo) throws ServiceException;
	 
	/**
	 * 支付宝同步通知处理
	 * return 是否验签成功以及业务参数
	 * 
	 * */
	CommonBackBo callBack(HttpServletRequest request) throws ServiceException;
	/**
	 * 支付宝异步通知处理
	 * return 是否验签成功以及业务参数
	 * 
	 * */
	CommonBackBo notify(HttpServletRequest request) throws ServiceException;
	
	/**
	 * 构造调起境外支付宝app的字符串
	 * @param alipayBo
	 * @return
	 */
	String outsideAppPay(OutsideAlipayBo alipayBo) throws ServiceException;
	
	/**
     * app支付宝境外支付异步通知处理
     * return 是否验签成功以及业务参数
     * 
     * */
    CommonBackBo outsideAppNotify(HttpServletRequest request) throws ServiceException;
	
    /**
     * 海外支付宝wap支付
     * return 支付宝支付url
     * 
     * */
    String outsideWapPay(OutsideAlipayBo alipayBo) throws ServiceException;
    
    /**
     * 境外支付宝同步通知处理
     * return 是否验签成功以及业务参数
     * 
     * */
    CommonBackBo outsideWapCallBack(HttpServletRequest request) throws ServiceException;
    /**
     * 境外支付宝异步通知处理
     * return 是否验签成功以及业务参数
     * 
     * */
    CommonBackBo outsideWapNotify(HttpServletRequest request) throws ServiceException;

}
