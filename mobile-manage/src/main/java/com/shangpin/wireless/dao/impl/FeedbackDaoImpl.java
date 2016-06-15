package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.FeedbackDao;
import com.shangpin.wireless.domain.Feedback;

/**
 * 信息反馈
 * 
 * @Author zhouyu
 * @CreateDate 2012-10-22
 */
@Repository(FeedbackDao.DAO_NAME)
public class FeedbackDaoImpl extends ApiBaseDaoImpl<Feedback> implements FeedbackDao {

}
