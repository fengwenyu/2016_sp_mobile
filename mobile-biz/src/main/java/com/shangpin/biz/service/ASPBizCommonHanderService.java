package com.shangpin.biz.service;

public interface ASPBizCommonHanderService {
	
	/**
	 * 判断某个平台的某个版本是否支持合并支付
	 * @param version
	 * @param plateForm
	 * @return
	 */
	public boolean IsUseCombinePay(String member);

}
