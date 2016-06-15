package com.shangpin.pay.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shangpin.pay.base.ErrType;
import com.shangpin.pay.bo.CommonPayBo;
import com.shangpin.pay.exception.ServiceException;
import com.shangpin.pay.service.CMBCPayService;
import com.shangpin.pay.utils.common.CommonPropertyUtil;
import com.shangpin.pay.utils.common.MD5Util;
import com.shangpin.pay.utils.common.SortedValueUtil;
import com.shangpin.pay.utils.common.StringUtil;
@Service
public class CMBCPayServiceImpl implements CMBCPayService {
	public static Logger logger = LoggerFactory
			.getLogger(CMBCPayServiceImpl.class);

	@Override
	public Map<String, Object> cmbcWapPay(CommonPayBo commonPayBo)
			throws ServiceException {
		if (commonPayBo == null || StringUtil.isEmpty(commonPayBo.getOrderNo(), commonPayBo.getTotalFee())) {
			throw new ServiceException(ErrType.MISS_PARAMETER, "Missing parameters，please check!");
		}
		Map<String, String> requestParams = new TreeMap<String, String>();
		requestParams.put("gateWay", commonPayBo.getGateWay());
		requestParams.put("orderNo", commonPayBo.getOrderNo());
		requestParams.put("subject", commonPayBo.getSubject());
		String totalFee=commonPayBo.getTotalFee();
		if (CommonPropertyUtil.devModule) {// 判断是否为测试订单
			totalFee="0.01";
		} 
		requestParams.put("totalFee",new BigDecimal(totalFee).setScale(2).toString() );
		logger.info("cmbc wappay sign str:{}",SortedValueUtil.sortedValue(requestParams));
		logger.info("cmbc wappay sign str:{}",SortedValueUtil.sortedValue(requestParams)+CommonPropertyUtil.encryptKey);
		
		String sign=MD5Util.MD5(SortedValueUtil.sortedValue(requestParams)+CommonPropertyUtil.encryptKey);
		
		logger.info("cmbc wappay sign sign:{}",sign);
		requestParams.put("sign",sign);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("requestParams", requestParams);
		params.put("gatewayUrl", CommonPropertyUtil.payRequestUrl);
		return params;
	}

}
