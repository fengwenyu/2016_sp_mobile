package com.shangpin.biz.service;

import com.shangpin.biz.bo.RecProductFor;

public interface SPBizExclusiveRecProductService {
	
	public RecProductFor queryRecProduct(String type, String userId, String imei, String coord, String ip, String pageIndex, String pageSize);
}
