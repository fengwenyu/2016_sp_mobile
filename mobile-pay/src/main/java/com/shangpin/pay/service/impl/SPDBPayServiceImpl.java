package com.shangpin.pay.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shangpin.pay.base.ErrType;
import com.shangpin.pay.bo.SPDBPayBackBo;
import com.shangpin.pay.bo.SPDBPayBo;
import com.shangpin.pay.bo.SPDBPayParamsBo;
import com.shangpin.pay.exception.ServiceException;
import com.shangpin.pay.service.SPDBPayService;
import com.shangpin.pay.utils.common.StringUtil;
import com.shangpin.pay.utils.spdb.SPDBPayUtil;

@Service
public class SPDBPayServiceImpl implements SPDBPayService {
	public static Logger logger = LoggerFactory
			.getLogger(SPDBPayServiceImpl.class);

	@Override
	public SPDBPayParamsBo goSPDBPay(SPDBPayBo spdbPayBo)
			throws ServiceException {
		// TODO Auto-generated method stub
		logger.debug("*******PAY MODEL:come in SPDBPay method wapPay*****");

		if (spdbPayBo == null|| StringUtil.isEmpty(spdbPayBo.getCustomerIp(),
						spdbPayBo.getCurrency(), spdbPayBo.getExt(),
						spdbPayBo.getGateway(), spdbPayBo.getNotifyUrl(),
						spdbPayBo.getOrderNo(), spdbPayBo.getReturnUrl(),
						spdbPayBo.getTimeout(), spdbPayBo.getTotalFee())) {
			throw new ServiceException(ErrType.MISS_PARAMETER,"Missing parameters，please check!");
		}
		SPDBPayParamsBo spdbPayParamsBo = new SPDBPayParamsBo();
		try {
			SPDBPayUtil spdbPayUtil = new SPDBPayUtil();
			spdbPayParamsBo = spdbPayUtil.getSpdbPayObject(spdbPayBo);
		} catch (Exception e) {
			throw new ServiceException(ErrType.RESRESULT_ERROR,
					"request error，please check!");
		}
		if (spdbPayParamsBo.getErr() != null) {
			throw new ServiceException(ErrType.RESRESULT_ERROR,
					"request error，please check!");
		}
		return spdbPayParamsBo;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> spdbPay(SPDBPayBo spdbPayBo)
			throws ServiceException {
		// TODO Auto-generated method stub
		logger.debug("*******PAY MODEL:come in SPDBPay method wapPay*****");
		if (spdbPayBo == null|| StringUtil.isEmpty(spdbPayBo.getCustomerIp(),
						spdbPayBo.getCurrency(), spdbPayBo.getExt(),
						spdbPayBo.getGateway(), spdbPayBo.getNotifyUrl(),
						spdbPayBo.getOrderNo(), spdbPayBo.getReturnUrl(),
						spdbPayBo.getTimeout(), spdbPayBo.getTotalFee())) {
			throw new ServiceException(ErrType.MISS_PARAMETER,"Missing parameters，please check!");
		}
		Map<String, Object> spdbPayMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> valMap = new HashMap<String, Object>();
		try {
			SPDBPayUtil spdbPayUtil = new SPDBPayUtil();
			map = spdbPayUtil.getSpdbPay(spdbPayBo);
			valMap = (Map<String, Object>) map.get("val");
		} catch (Exception e) {
			throw new ServiceException(ErrType.RESRESULT_ERROR,"request error，please check!");
		}
		if (map.get("err") != null) {
			throw new ServiceException(ErrType.RESRESULT_ERROR,"request error，please check!");
		}
		spdbPayMap.put("gatewayUrl", valMap.get("gatewayUrl"));
		spdbPayMap.put("requestParams",(Map<String, String>) valMap.get("requestParams"));
		spdbPayMap.put("spdbPayInfo", map.get("spdbPayInfo"));
		return spdbPayMap;
	}

	@Override
	public SPDBPayBackBo payBack(Map<String, String[]> parameterMap,
			String gateway) throws ServiceException {
		SPDBPayBackBo spdbPayBackBo = new SPDBPayBackBo();
		try {
			SPDBPayUtil spdbPayUtil = new SPDBPayUtil();
			spdbPayBackBo = spdbPayUtil.handlePay(parameterMap, gateway);
		} catch (Exception e) {
			throw new ServiceException(ErrType.RESRESULT_ERROR,
					"request error，please check!");
		}
		return spdbPayBackBo;
	}

	public static void main(String[] args) throws ServiceException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("orderNo", "1234");
		parameterMap
				.put("Signature",
						"10c075c7ee4f056d1210ae6eb2dfafa6159b8b9701de7220ff8427a0804337e3b86098ddd8d1c9ba6176dc04290678bb93334f330229b8f9526eae7178c8cc96");

	}
}
