package com.shangpin.biz.service;

import javax.servlet.http.HttpServletRequest;

import com.shangpin.biz.service.basic.IBizIndexService;

public interface ASPBizIndexInfoService extends IBizIndexService {

	/** 首页第一部分 */
	String getAppFirstIndex(HttpServletRequest request) throws Exception;

	/** 首页标签 */
	String getAppRevealLabel(HttpServletRequest request) throws Exception;

	/** 获取首页弹窗 */
	String getAppShellWindow(String pageIndex, String pageSize);
	
	/** 获取底部导航栏图标 */
	String getBottomNavigatePic(String plateForm);
}
