package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.SPActivity;

/**
 * 新品上架
 * 
 * @author huangxiaoliang
 *
 */
public interface ASPBizReleasesService {
	/**
	 * 首页新品上架更多页
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public String getMoreReleases(String pageIndex, String pageSize, String origin);

	/**
	 * 首页新品上架集合
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<SPActivity> queryNewReleases(String pageIndex, String pageSize, String origin);

}
