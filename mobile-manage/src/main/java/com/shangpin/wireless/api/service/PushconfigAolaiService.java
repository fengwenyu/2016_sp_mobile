package com.shangpin.wireless.api.service;

import java.util.List;

import com.shangpin.wireless.domain.PushconfigAolai;
import com.shangpin.wireless.util.HqlHelper;

/**
 * 
 * 
 * @Author  zhouyu
 * @CreatDate  2012-07-16
 */
public interface PushconfigAolaiService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.PushconfigAolaiServiceImpl";
	@SuppressWarnings("rawtypes")
	public List executeHql(String hql) throws Exception;

	/**
	 * 保存实体
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-14
	 * @param model
	 * @param dbType
	 *            数据库类型
	 * @Return:
	 */
	public void save(PushconfigAolai model) throws Exception;

	/**
	 * 根据userId查询pushconf_aolai
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-14
	 * @param hqlHelper
	 *            查询对象
	 * @param dbType
	 *            数据库类型
	 * @Return:
	 */
	public PushconfigAolai getByCondition(HqlHelper hqlHelper) throws Exception;

	/**
	 * 更新实体
	 * 
	 * @Author: zhouyu
	 * 
	 * @CreateDate: 2012-09-16
	 * @param entity
	 *            实体
	 * @param dbType
	 *            数据库类型
	 * @Return:
	 */
	void update(PushconfigAolai model) throws Exception;

	/**
	 * 根据HQL语句获取数据集合
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-17
	 * @param flag
	 *            push消息类型标识
	 * @Return List
	 */
	public List<String> executeHql(int flag) throws Exception;

	/**
	 * 根据HQL语句获取token数据集合
	 * 
	 * @param flag
	 *            push消息类型标识
	 * @Return List
	 */
	public List<String> getToken(int flag) throws Exception;
	
	/**
	 * 根据HQL语句获取token数据集合
	 * @param flag
	 *  		push消息类型标识
	 * @param start
	 * 			分页开始标记
	 * @param size
	 * 			分页数据大小
	 * @return
	 * @throws Exception
	 */
	public List<String> getToken(int flag, int start, int size) throws Exception;

	/**
	 * 发送push信息
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-17
	 * @Return
	 */
	public void sendPushMsg() throws Exception;

	/**
	 * 根据符合条件查询pushconf_aolai结果集
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-14
	 * @param hqlHelper
	 *            查询对象
	 * @param dbType
	 *            数据库类型
	 * @Return:
	 */
	public List<PushconfigAolai> getListByCondition(HqlHelper hqlHelper) throws Exception;

	/**
	 * 保存Token
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2013-01-16
	 * @param token
	 *            iPhone token
	 * @param userId
	 *            用户id
	 * @param imei
	 *            手机imei
	 * @param gender
	 *            性别
	 * @Return:
	 */
	public void saveToken(String token, String userId, String imei, String gender) throws Exception;

	/**
	 * 保存push设置
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2013-01-16
	 * @param userId
	 *            用户id
	 * @param gender
	 *            性别
	 * @param token
	 *            iPhone token
	 * @param imei
	 *            手机imei
	 * @param isOpen
	 *            是否接收push信息
	 * @param msgType
	 *            接收push的消息类型
	 * @Return:
	 */
	public void savePushConfig(String userId, String gender, String token, String imei, int isOpen, int msgType) throws Exception;

	/**
	 * 根据条件删除
	 * @param condition
	 * @throws Exception
	 */
	public void deleteByCondition(String condition) throws Exception;
}
