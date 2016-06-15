package com.shangpin.wireless.manage.service;

import com.shangpin.wireless.api.base.dao.BaseDao;
import com.shangpin.wireless.api.domain.OperateLog;

/**
 * 操作日志
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-08-02
 */
public interface OperateLogService extends BaseDao<OperateLog> {

	public final static String SERVICE_NAME = "com.shangpin.wireless.manage.service.impl.OperateLogServiceImpl";

	/**
	 * 保存操作日志
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-08-02
	 * @param userName
	 *            用户
	 * @param content
	 *            操作日志内容
	 * @Return:
	 */
	public void saveLog(String userName, String content);

}
