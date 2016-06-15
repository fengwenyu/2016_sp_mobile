package com.shangpin.wireless.manage.service;

import com.shangpin.wireless.domain.PageView;

/**
 * PV统计
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-24
 */
public interface PageViewService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.manage.service.impl.PageViewServiceImpl";

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	void save(PageView entity) throws Exception;
}
