package com.shangpin.wireless.api.dao;

import com.shangpin.wireless.api.base.dao.BaseDao;
import com.shangpin.wireless.api.domain.Feedback;

/**
 * 用户反馈
 * 
 * @Author zhouyu
 * @CreateDate: 2012-09-11
 */
public interface FeedbackDao extends BaseDao<Feedback> {
	public final static String DAO_NAME = "com.shangpin.wireless.api.dao.impl.FeedbackDaoImpl";
}
