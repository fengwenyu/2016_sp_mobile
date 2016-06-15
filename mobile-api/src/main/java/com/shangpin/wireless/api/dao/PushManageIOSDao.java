package com.shangpin.wireless.api.dao;

import java.math.BigInteger;
import java.util.List;

import com.shangpin.wireless.api.base.dao.BaseDao;
import com.shangpin.wireless.api.domain.PushManageIos;

/**
 * push数据访问层接口，提供，IOS平台push信息内容
 * 
 * @Author yumeng
 * @CreateDate: 2013-02-22
 */
public interface PushManageIOSDao extends BaseDao<PushManageIos> {
	public final static String DAO_NAME = "com.shangpin.wireless.api.dao.impl.PushManageIOSDaoImpl";

	/**
	 * 获取24小时之内及定时发送时间大于当前时间的push信息内容(ios平台广播)
	 * 
	 * @param gender
	 *            0：女；1：男；2全部
	 * @param productNum
	 *            产品号：101为奥莱；102为尚品
	 * @return push信息列表(广播)
	 * @throws Exception
	 */
	public List<PushManageIos> findPushManageIOS(int gender, BigInteger productNum) throws Exception;

	/**
	 * 获取与个人相关的push信息内容(ios平台)
	 * 
	 * @param username
	 *            用户名
	 * @param productNum
	 *            产品号
	 * @return push信息列表
	 * @throws Exception
	 */
	public List<PushManageIos> findPushManageIOSByUser(BigInteger productNum) throws Exception;

	/**
	 * 根据id，更新push状态为已发送
	 * 
	 * @param id
	 *            主键
	 * @throws Exception
	 */
	public void updatePushManageIOSType(Long id) throws Exception;
}