package com.shangpin.base.service;

import java.util.List;

public interface RecRuleService {
    //推荐规则
	String doRecRuleProduct(String userId, String imei,String offset,String num) throws Exception;
	
	
	public String findByProductNos(List<String> productNos);
	
	
}
