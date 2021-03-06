package com.shangpin.wireless.manage.service;

import com.shangpin.wireless.domain.AoLaiWapPageView;

/**
 * PV统计
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-24
 */
public interface AoLaiWapPageViewService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.manage.service.impl.AoLaiWapPageViewServiceImpl";

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	public void save(AoLaiWapPageView entity) throws Exception;
}
