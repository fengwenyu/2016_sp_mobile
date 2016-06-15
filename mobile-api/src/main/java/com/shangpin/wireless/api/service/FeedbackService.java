package com.shangpin.wireless.api.service;

import com.shangpin.wireless.api.domain.Feedback;

public interface FeedbackService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.FeedbackServiceImpl";

	/**
	 * 保存实体
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-11
	 * @param model
	 * @param dbType
	 *            数据库类型
	 * @Return:
	 */
	public void save(Feedback model, String dbType) throws Exception;

}
