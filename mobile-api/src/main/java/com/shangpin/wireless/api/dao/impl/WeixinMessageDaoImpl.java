package com.shangpin.wireless.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.WeixinMessageDao;
import com.shangpin.wireless.api.domain.WeixinMessage;

/**
 * @Author wangwenguan
 * @CreateDate: 2013-11-28
 */
@Repository(WeixinMessageDao.DAO_NAME)
public class WeixinMessageDaoImpl extends BaseDaoImpl<WeixinMessage> implements WeixinMessageDao {

}
