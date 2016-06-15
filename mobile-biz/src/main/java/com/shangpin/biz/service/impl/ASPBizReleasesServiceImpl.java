package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ResultObjMapList;
import com.shangpin.biz.bo.SPActivity;
import com.shangpin.biz.service.ASPBizReleasesService;
import com.shangpin.biz.service.abstraction.AbstractBizNewReleaseService;
import com.shangpin.utils.JSONUtils;

@Service
public class ASPBizReleasesServiceImpl extends AbstractBizNewReleaseService implements ASPBizReleasesService {

	@Autowired
	ShangPinService shangPinService;
	
	@SuppressWarnings("unused")
	private ResultObjMapList<SPActivity> doBase(String pageIndex, String pageSize, String origin) throws Exception {
		String baseData = shangPinService.queryNewReleases(pageIndex, pageSize, origin);
		ResultObjMapList<SPActivity> obj = new ResultObjMapList<SPActivity>();
		if (StringUtils.isNotEmpty(baseData)) {
			obj =JSONUtils.toGenericsCollection(baseData, ResultObjMapList.class,SPActivity.class);
		}
		return obj;
	}

	@Override
	public String getMoreReleases(String pageIndex, String pageSize, String origin) {
		String apiData = shangPinService.queryNewReleases(pageIndex, pageSize, origin);
		return apiData;
	}

	@Override
	public List<SPActivity> queryNewReleases(String pageIndex, String pageSize, String origin) {
		List<SPActivity> sales=null;
        try {
            sales = this.activities(pageIndex, pageSize, origin);
            if (sales == null) {
            	sales = new ArrayList<SPActivity>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return sales;
	}
}
