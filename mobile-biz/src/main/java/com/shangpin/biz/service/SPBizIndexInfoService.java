package com.shangpin.biz.service;

import javax.servlet.http.HttpServletRequest;

import com.shangpin.biz.bo.AdvertNewTitle;
import com.shangpin.biz.bo.AdvertTitle;
import com.shangpin.biz.bo.ModelTitle;
import com.shangpin.biz.bo.NewGoodsTitle;
import com.shangpin.biz.bo.ReleasesSPActivity;
import com.shangpin.biz.bo.RevealLabel;
import com.shangpin.biz.bo.WorthTitle;
import com.shangpin.biz.service.basic.IBizIndexService;

public interface SPBizIndexInfoService extends IBizIndexService {
	
	public String getAppFirstIndex(HttpServletRequest request) throws Exception ;

	public RevealLabel getRevealLabel(String pageIndex, String pageSize);
	public AdvertTitle getAdvertTitle(String type, String pageIndex, String pageSize) throws Exception;
	public AdvertNewTitle getAdvertNewTitle() throws Exception;
	/** 主题 */
	public ReleasesSPActivity getReleaseTitle(String pageIndex, String pageSize, String origin) throws Exception;
	/** 首页新品 */
	public NewGoodsTitle getNewGoodsTitle() throws Exception;
	public WorthTitle getWorthTitle(String type, String userId, String pageIndex, String pageSize) throws Exception;
	 /**
     * 
     * @Title:findModelInfo
     * @Description:模板广告
     * @date 2015年11月6日18:34:10
     */
    public ModelTitle findModelInfo() throws Exception;
}
