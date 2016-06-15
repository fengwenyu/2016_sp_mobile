package com.shangpin.biz.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于尚品客户端api的首页接口
 * 
 * @author huangxiaoliang
 *
 */
public interface ASPBizIndexService {
	/**
	 * 获取首页第一部分
	 * 
	 * 
	 * @return
	 * @author wangfeng
	 * @throws Exception
	 */
	public String queryAppIndexFirst(HttpServletRequest request) throws Exception;

	/**
	 * 获取首页第二部分数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getHomeSecond(String userId) throws Exception;

}
