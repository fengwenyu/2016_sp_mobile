package com.shangpin.wireless.manage.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.shangpin.wireless.domain.AccountStatistics;
import com.shangpin.wireless.util.HqlHelper;

/**
 * 用户统计
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-24
 */
public interface AccountStatisticsService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.manage.service.impl.AccountStatisticsServiceImpl";

	public void update(AccountStatistics entity) throws Exception;

	/**
	 * 根据HQL语句获取数据集合
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param hql
	 * @Return List
	 */
	public List executeHql(String hql) throws Exception;

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void save(AccountStatistics entity) throws Exception;

	/**
	 * 根据组合条件获取实体，如果条件为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-17
	 * @param hqlHelper
	 * @Return T
	 */
	AccountStatistics getByCondition(HqlHelper hqlHelper) throws Exception;

	/**
	 * 根据sql语句获取结果集条数
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param sql
	 *            查询语句
	 * @Return Long
	 */
	public Integer executeSqlCount(String sql) throws Exception;

	/**
	 * 根据日期生成用户柱状图
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param outputStream
	 *            图输出流
	 * @param type
	 *            标识符来判断查询独立用户还是新增用户
	 * @Return OutputStream
	 */
	public OutputStream getAccountImageByDate(String startDate, String endDate, OutputStream outputStream, String type, Long productNum, Long channelNum) throws Exception;

	/**
	 * 根据日期生成用户柱状图
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param outputStream
	 *            图输出流
	 * @param type
	 *            标识符来判断查询独立用户还是新增用户
	 * @Return Map
	 */
	public OutputStream getAccountImageByHour(String startDate, String endDate, OutputStream outputStream, String type, Long productNum, Long channelNum) throws Exception;

	/**
	 * 根据时间查询用户量
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param type
	 *            标识符来判断查询独立用户还是新增用户
	 * @Return List
	 */
	public List getAccountByDate(String startDate, String endDate, String type, Long productNum, Long channelNum) throws Exception;

	/**
	 * 根据时间段来划分用户
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param type
	 *            标识符来判断查询独立用户还是新增用户
	 * @Return Map
	 */
	public Map<String, String> sortAccountByDate(String startDate, String endDate, String type, Long productNum, Long channelNum) throws Exception;

	/**
	 * 获取所有实体集合
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-04-16
	 * @param
	 * @Return List
	 */
	List<AccountStatistics> findAll() throws Exception;

	/**
	 * 删除实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param id
	 * @Return
	 */
	public void delete(Long id) throws Exception;
}
