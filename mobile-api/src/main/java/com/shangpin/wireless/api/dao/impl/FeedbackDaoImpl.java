package com.shangpin.wireless.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.FeedbackDao;
import com.shangpin.wireless.api.domain.Feedback;

/**
 * 用户反馈
 * 
 * @Author zhouyu
 * @CreateDate: 2012-09-11
 */
@Repository(FeedbackDao.DAO_NAME)
public class FeedbackDaoImpl extends BaseDaoImpl<Feedback> implements FeedbackDao {

}
