package com.shangpin.wireless.dao;

import java.math.BigInteger;
import java.util.List;

import com.shangpin.wireless.base.dao.ApiBaseDao;
import com.shangpin.wireless.domain.PushManageAndroid;

/**
 * @Author zhouyu
 * @CreatDate 2013-02-25
 */
public interface PushManageAndroidDao extends ApiBaseDao<PushManageAndroid> {
	public final static String DAO_NAME = "com.shangpin.wireless.dao.impl.PushManageAndroidDaoImpl";

	/**
	 * 查询最大id
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @Return long
	 */
	long getMaxId() throws Exception;
	
	/**
	 * 获取24小时之内及定时发送时间大于当前时间的push信息内容(Android平台广播)
	 * 
	 * @param gender
	 *            0：女；1：男；2全部
	 *         @param   productNum
	 *            产品号：101为奥莱；102为尚品
	 * @return push信息列表(广播)
	 * @throws Exception
	 */
	public List<PushManageAndroid> findPushManageAndroid(int gender, BigInteger productNum) throws Exception;

	/**
	 * 获取与个人相关的push信息内容(Android平台)
	 * 
	 * @param userId
	 *            用户ID
	 * @param productNum
	 *            产品号
	 * @return push信息列表
	 * @throws Exception
	 */
	public List<PushManageAndroid> findPushManageAndroidByUser(String userId, BigInteger productNum) throws Exception;

	/**
	 * 根据id，更新push状态为已发送
	 * 
	 * @param id
	 *            主键
	 * @throws Exception
	 */
	public void updatePushManageAndroidType(Long id) throws Exception;
}
