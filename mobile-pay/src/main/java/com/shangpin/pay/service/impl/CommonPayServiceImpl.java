package com.shangpin.pay.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.shangpin.pay.base.ErrType;
import com.shangpin.pay.bo.APPPayCommonBo;
import com.shangpin.pay.bo.CommonPayBackBo;
import com.shangpin.pay.bo.CommonPayBo;
import com.shangpin.pay.bo.CommonQueryBackBo;
import com.shangpin.pay.exception.ServiceException;
import com.shangpin.pay.service.CommonPayService;
import com.shangpin.pay.utils.common.CommonPropertyUtil;
import com.shangpin.pay.utils.common.MD5Util;
import com.shangpin.pay.utils.common.SortedValueUtil;
import com.shangpin.pay.utils.common.StringUtil;
import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CommonPayServiceImpl extends BaseServiceImpl implements CommonPayService{
	public static Logger logger = LoggerFactory.getLogger(CommonPayServiceImpl.class);
	@Override
	public CommonPayBackBo payCallBack(Map<String, String[]> parameterMap,
			String gateway)throws ServiceException {
		try{
			Map<String, String> params = new HashMap<String, String>();
			Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
			for (Object key : parameterMap.keySet()) {
				String value = parameterMap.get(key)[0].toString();
				if (value == null|| value.equals("")) {
					continue;
				}
				params.put((String) key, value);
			}
	
			
			String result = HttpClientUtil.doPostWithJSON(new StringBuilder(CommonPropertyUtil.payReturnUrl).append("/").append(gateway).toString(),gson.toJson(params),  CommonPropertyUtil.CHARSET);
			logger.info("Pay back result={}",result);
			logger.info("{} back result={}",gateway,result);
			if (!StringUtils.isEmpty(result)) {
				CommonPayBackBo commonPayBackBo = gson.fromJson(result,CommonPayBackBo.class);
				return commonPayBackBo;
			}
			return null;
		}catch(Exception e){
			  logger.error("Pay back  :e={}", e);
	          throw new ServiceException(ErrType.RESRESULT_ERROR, "pay back error ,please check! ");
		}
		
	}
	@Override
	public CommonQueryBackBo queryTradeStatusWithGateway(String gateway,
			String orderId) throws ServiceException {
		try{
			Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
			String result = HttpClientUtil.doPostWithJSON(new StringBuilder(CommonPropertyUtil.payQueryUrl).append("/").append(gateway).append("/").append(orderId).toString(),  CommonPropertyUtil.CHARSET);
			logger.info("Pay back result={}",result);
			logger.info("{} back result={}",gateway,result);
			if (!StringUtils.isEmpty(result)) {
				CommonQueryBackBo commonQueryBackBo = gson.fromJson(result,CommonQueryBackBo.class);
				return commonQueryBackBo;
			}
			return null;
		}catch(Exception e){
			  logger.error("Pay back  :e={}", e);
	          throw new ServiceException(ErrType.RESRESULT_ERROR, "pay back error ,please check! ");
		}
	}
	@Override
	public CommonQueryBackBo queryTradeStatus(String orderId) throws ServiceException {
		try{
			Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
			String result = HttpClientUtil.doGet(new StringBuilder(CommonPropertyUtil.payQueryUrl).append("/").append(orderId).toString());
			logger.info("Pay back result={}",result);
			logger.info("{} back result={}",result);
			if (!StringUtils.isEmpty(result)) {
				CommonQueryBackBo commonQueryBackBo = gson.fromJson(result,CommonQueryBackBo.class);
				return commonQueryBackBo;
			}
			return null;
		}catch(Exception e){
			  logger.error("Pay back  :e={}", e);
	          throw new ServiceException(ErrType.RESRESULT_ERROR, "pay back error ,please check! ");
		}
	}
	@Override
	public Map<String, Object> appPay(APPPayCommonBo commonPayBo) throws ServiceException{
		try{
			Map<String, String> params = new HashMap<String, String>();
			params.put("body", commonPayBo.getBody());
			params.put("customerIp", commonPayBo.getCustomerIp());
			params.put("gateway", commonPayBo.getGateway());
			params.put("orderNo", commonPayBo.getOrderNo());
			params.put("subject", commonPayBo.getSubject());
			String totalFee=commonPayBo.getTotalFee();
		    // 判断是否是测试环境
	        if (CommonPropertyUtil.devModule) {
	        	totalFee = "0.01";
				if(commonPayBo.getGateway().equals("ALIAPPSEA")){
					totalFee = "0.03";
				}
	        	else if(commonPayBo.getGateway().equals("ALIFZAPP")){
	        		totalFee = "0.06";
	        	}
	            
	        }
			params.put("totalFee",new BigDecimal(totalFee).setScale(2).toString() );
			params.put("ext", commonPayBo.getExt());
			params.put("openId", commonPayBo.getOpenId());
			Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
					.create();
			String reqStr = gson.toJson(params);
			
			logger.info("APP Pay {} request={}",commonPayBo.getGateway(),reqStr);
			String result = HttpClientUtil.doPostWithJSON(CommonPropertyUtil.payFacadeUrl.toString(), reqStr,CommonPropertyUtil.CHARSET);
			logger.info("APP Pay {} result={}",commonPayBo.getGateway(),result);
			if(StringUtils.isEmpty(result)){
				throw new ServiceException(ErrType.RESRESULT_ERROR,"result error，please check!");
			
			}
			Map<String, Object> map = new HashMap<String, Object>();
	
			map = JsonUtil.fromJson(result,new TypeToken<HashMap<String, Object>>() {});
			if (map.get("err") != null) {
				throw new ServiceException(ErrType.RESRESULT_ERROR,"result error，please check!");
			}
			
			return map;
		}catch(Exception e){
			  logger.error("pay appPay  :e={}", e);
	          throw new ServiceException(ErrType.RESRESULT_ERROR, "pay back error ,please check! ");
		}
	}
	@Override
	public Map<String, Object> wapPay(CommonPayBo commonPayBo) throws ServiceException {
		if (commonPayBo == null || StringUtil.isEmpty(commonPayBo.getOrderNo(), commonPayBo.getTotalFee())) {
			throw new ServiceException(ErrType.MISS_PARAMETER, "Missing parameters，please check!");
		}
		Map<String, String> requestParams = new TreeMap<String, String>();
		String gateWay=commonPayBo.getGateWay();
		requestParams.put("gateWay", gateWay);
		requestParams.put("orderNo", commonPayBo.getOrderNo());
		requestParams.put("subject", commonPayBo.getSubject());
		String totalFee=commonPayBo.getTotalFee();
		if (CommonPropertyUtil.devModule) {// 判断是否为测试订单
			totalFee="0.01";
		} 
		requestParams.put("totalFee",new BigDecimal(totalFee).setScale(2).toString() );
		logger.info("{} wappay sign str:{}",gateWay,SortedValueUtil.sortedValue(requestParams));
		logger.info("{} wappay sign str:{}",gateWay,SortedValueUtil.sortedValue(requestParams)+CommonPropertyUtil.encryptKey);
		
		String sign=MD5Util.MD5(SortedValueUtil.sortedValue(requestParams)+CommonPropertyUtil.encryptKey);
		
		logger.info("{} wappay sign sign:{}",gateWay,sign);
		requestParams.put("sign",sign);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("requestParams", requestParams);
		params.put("gatewayUrl", CommonPropertyUtil.payRequestUrl);
		return params;
	}
	
	
}
