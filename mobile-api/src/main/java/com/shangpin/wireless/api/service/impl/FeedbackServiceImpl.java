package com.shangpin.wireless.api.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.FeedbackDao;
import com.shangpin.wireless.api.domain.Feedback;
import com.shangpin.wireless.api.service.FeedbackService;

@Service(FeedbackService.SERVICE_NAME)
public class FeedbackServiceImpl implements FeedbackService {
	@Resource(name = FeedbackDao.DAO_NAME)
	private FeedbackDao feedbackDao;

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
	public void save(Feedback model, String dbType) throws Exception {
		feedbackDao.save(model, dbType);
	}

}
