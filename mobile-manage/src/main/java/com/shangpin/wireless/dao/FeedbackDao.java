package com.shangpin.wireless.dao;

import com.shangpin.wireless.base.dao.ApiBaseDao;
import com.shangpin.wireless.domain.Feedback;

/**
 * 信息反馈
 * 
 * @Author zhouyu
 * @CreateDate 2012-10-22
 */
public interface FeedbackDao extends ApiBaseDao<Feedback> {
	public final static String DAO_NAME = "com.shangpin.wireless.dao.impl.FeedbackDaoImpl";
}
