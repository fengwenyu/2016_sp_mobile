package com.shangpin.pay.service;

import java.util.Map;

import com.shangpin.pay.bo.APPPayCommonBo;
import com.shangpin.pay.bo.CommonPayBackBo;
import com.shangpin.pay.bo.CommonPayBo;
import com.shangpin.pay.bo.CommonQueryBackBo;
import com.shangpin.pay.exception.ServiceException;

public interface CommonPayService {
	public CommonPayBackBo payCallBack(Map<String,String[]> parameterMap, String gateway)throws ServiceException ;

	public CommonQueryBackBo queryTradeStatusWithGateway(String gateway, String orderId) throws ServiceException;

	public CommonQueryBackBo queryTradeStatus(String orderId) throws ServiceException;
	
	public Map<String,Object> appPay(APPPayCommonBo commonPayBo)throws ServiceException;

	public Map<String, Object> wapPay(CommonPayBo commonPayBo)throws ServiceException;
}
