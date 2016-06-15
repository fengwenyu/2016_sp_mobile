package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.Fashion;
import com.shangpin.biz.service.ASPBizFashionService;
import com.shangpin.biz.service.abstraction.AbstractBizFashionService;

@Service
public class ASPBizFashionServiceImpl extends AbstractBizFashionService implements ASPBizFashionService {

	@Override
	public List<Fashion> doFashion(String type, String pageIndex, String pageSize) {
		List<Fashion> fashions = fromFindFashionList(type, pageIndex, pageSize);
		if (null == fashions) {
			fashions = new ArrayList<Fashion>();
		}
		return fashions;
	}


}
