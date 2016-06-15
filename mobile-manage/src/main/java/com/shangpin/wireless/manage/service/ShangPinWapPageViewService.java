package com.shangpin.wireless.manage.service;

import com.shangpin.wireless.domain.ShangPinWapPageView;

/**
 * PV统计
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-24
 */
public interface ShangPinWapPageViewService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.manage.service.impl.ShangPinWapPageViewServiceImpl";

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	public void save(ShangPinWapPageView entity) throws Exception;
}
