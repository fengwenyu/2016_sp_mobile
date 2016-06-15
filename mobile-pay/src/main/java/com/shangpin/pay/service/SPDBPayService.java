package com.shangpin.pay.service;

import java.util.Map;

import com.shangpin.pay.bo.SPDBPayBackBo;
import com.shangpin.pay.bo.SPDBPayBo;
import com.shangpin.pay.bo.SPDBPayParamsBo;
import com.shangpin.pay.exception.ServiceException;

public interface SPDBPayService {
	/**
	 * 浦发银行支付 
	 * 
	 * */
	SPDBPayParamsBo goSPDBPay(SPDBPayBo spdbPayBo) throws ServiceException;
	Map<String,Object>  spdbPay(SPDBPayBo spdbPayBo) throws ServiceException;
	SPDBPayBackBo payBack(Map<String,String[]> parameterMap, String string) throws ServiceException;

}
