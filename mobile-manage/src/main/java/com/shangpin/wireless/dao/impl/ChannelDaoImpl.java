package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.ChannelDao;
import com.shangpin.wireless.domain.Channel;

/**
 * @Author zhouyu
 * @CreatDate  2012-07-16
 */
@Repository(ChannelDao.DAO_NAME)
public class ChannelDaoImpl extends ManageBaseDaoImpl<Channel> implements ChannelDao {
	/**
	 *  验证渠道名是否存在
	 * @Author  zhouyu
	 * @CreatDate  2012-07-16
	 * @param   channelName
	 * @Return  返回一个游离态的Channel实体
	 */
	public Channel findByProductName(String channelName) {
		return (Channel) getSession().createQuery(//
				"FROM Channel c WHERE c.channelName=?")//
				.setParameter(0, channelName)//
				.uniqueResult();
	}
}
