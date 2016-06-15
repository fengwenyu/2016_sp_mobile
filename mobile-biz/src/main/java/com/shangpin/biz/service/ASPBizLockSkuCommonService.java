package com.shangpin.biz.service;

import java.util.List;
import java.util.Map;


import com.shangpin.biz.bo.SupplierSkuNoInfo;

public interface ASPBizLockSkuCommonService {
	
	public Map<String,String> orderLockSku(String orderNo,String userid,String mainOrderid);

	Map<String, String> orderLockSku(String orderNo, String userid,
			String mainOrderid, List<SupplierSkuNoInfo> list); 
}
