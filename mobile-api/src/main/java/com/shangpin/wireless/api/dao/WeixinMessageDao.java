package com.shangpin.wireless.api.dao;

import com.shangpin.wireless.api.base.dao.BaseDao;
import com.shangpin.wireless.api.domain.WeixinMessage;

/**
 * @Author wangwenguan
 * @CreateDate: 2013-08-19
 */
public interface WeixinMessageDao extends BaseDao<WeixinMessage> {
	public final static String DAO_NAME = "com.shangpin.wireless.api.dao.impl.WeixinMessageDaoImpl";
}
