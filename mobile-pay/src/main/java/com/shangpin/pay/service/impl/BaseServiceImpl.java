package com.shangpin.pay.service.impl;

import org.springframework.stereotype.Service;

import com.shangpin.pay.bo.CommonBackBo;
import com.shangpin.pay.service.BaseService;
@Service
public class BaseServiceImpl  implements BaseService{

	@Override
	public CommonBackBo getCommonBackBo(boolean versign, String orderId,
			String totalFee,String tradeNo) {
		CommonBackBo commonBackBo=new CommonBackBo();
		commonBackBo.setVerifySign(versign);
		commonBackBo.setOrderId(orderId);
		commonBackBo.setTotalFee(totalFee);
		commonBackBo.setTradeNo(tradeNo);
		return commonBackBo;
	}
	@Override
	public CommonBackBo getCommonBackBo(boolean versign, String orderId,
			String totalFee) {
		CommonBackBo commonBackBo=new CommonBackBo();
		commonBackBo.setVerifySign(versign);
		commonBackBo.setOrderId(orderId);
		commonBackBo.setTotalFee(totalFee);
		return commonBackBo;
	}
}
