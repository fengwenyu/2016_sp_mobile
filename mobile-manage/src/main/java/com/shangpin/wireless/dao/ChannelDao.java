package com.shangpin.wireless.dao;

import com.shangpin.wireless.base.dao.ManageBaseDao;
import com.shangpin.wireless.domain.Channel;

/**
 * @Author zhouyu
 * @CreatDate  2012-07-16
 */
public interface ChannelDao extends ManageBaseDao<Channel> {
	public final static String DAO_NAME = "com.shangpin.wireless.dao.impl.ChannelDaoImpl";

	/**
	 *  验证渠道名是否存在
	 * @Author  zhouyu
	 * @CreatDate  2012-07-16
	 * @param   channelName
	 * @Return  返回一个游离态的Channel实体
	 */
	public Channel findByProductName(String channelName);
}
