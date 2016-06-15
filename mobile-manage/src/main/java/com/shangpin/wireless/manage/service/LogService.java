package com.shangpin.wireless.manage.service;

import com.shangpin.wireless.domain.LogObject;

/**
 * 日志解析
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-24
 */
public interface LogService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.manage.service.impl.LogServiceImpl";

	/**
	 * 保存PV记录
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-24
	 * @param logObj
	 *            log实体
	 * @Return
	 */
	public void savePageView(LogObject logObj) throws Exception;
}
