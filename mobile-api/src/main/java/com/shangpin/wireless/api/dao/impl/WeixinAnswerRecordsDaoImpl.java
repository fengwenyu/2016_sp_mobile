package com.shangpin.wireless.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.WeixinAnswerRecordsDao;
import com.shangpin.wireless.api.domain.WeixinAnswerRecords;

/**
 * @Author wangwenguan
 * @CreateDate: 2013-08-01
 */
@Repository(WeixinAnswerRecordsDao.DAO_NAME)
public class WeixinAnswerRecordsDaoImpl extends BaseDaoImpl<WeixinAnswerRecords> implements WeixinAnswerRecordsDao {

}
