package com.shangpin.biz.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.MallCategory;
import com.shangpin.biz.bo.base.CommonObj;
import com.shangpin.biz.service.ASPBizCategoryService;
import com.shangpin.utils.JSONUtils;

@Service
public class ASPBizCategoryServiceImpl implements ASPBizCategoryService {

	@Autowired
	ShangPinService shangPinService;

	@SuppressWarnings("rawtypes")
	private MallCategory doBase() throws Exception {
		String baseData = shangPinService.queryMallCategoryList();
		MallCategory mc = new MallCategory();
		if (StringUtils.isNotEmpty(baseData)) {
			CommonObj obj = JSONUtils.toGenericsCollection(baseData, CommonObj.class, MallCategory.class);
			mc = (MallCategory) obj.getContent();
		}
		return mc;
	}

	@Override
	public MallCategory doCategory() throws Exception {
		return doBase();
	}

}
