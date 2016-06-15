package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.SPActivity;

 /**
 * @author qinyingchun
 * M站首页最新上架接口
 *
 */
public interface SPBizNewReleaseService {
	
	public String queryReleaseStr(String pageIndex, String pageSize, String origin);
	
	public List<SPActivity> activities(String pageIndex, String pageSize, String origin);

}
