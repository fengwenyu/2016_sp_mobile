package com.shangpin.biz.service.impl;

import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.RecProductFor;
import com.shangpin.biz.service.SPBizExclusiveRecProductService;
import com.shangpin.biz.service.abstraction.AbstractBizRecommendService;

@Service
public class SPBizExclusiveRecProductServiceImpl extends  AbstractBizRecommendService implements SPBizExclusiveRecProductService{

	@Override
	public RecProductFor queryRecProduct(String type, String userId,
			String vuid, String coord, String ip, String pageIndex,
			String pageSize)  {
		RecProductFor recProductFor = null;
		try {
			recProductFor = doBaseRecProduct( type, userId,  vuid, coord,  ip, pageIndex,  pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recProductFor;
	}
}
