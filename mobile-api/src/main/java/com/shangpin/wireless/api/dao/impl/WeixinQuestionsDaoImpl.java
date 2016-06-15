package com.shangpin.wireless.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.WeixinQuestionsDao;
import com.shangpin.wireless.api.domain.WeixinQuestions;

/**
 * @Author wangwenguan
 * @CreateDate: 2013-08-01
 */
@Repository(WeixinQuestionsDao.DAO_NAME)
public class WeixinQuestionsDaoImpl extends BaseDaoImpl<WeixinQuestions> implements WeixinQuestionsDao {

}
