package com.shangpin.wireless.manage.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.shangpin.wireless.domain.OrderStatistics;
import com.shangpin.wireless.util.HqlHelper;

/**
 * 订单统计
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-24
 */
public interface OrderStatisticsService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.manage.service.impl.OrderStatisticsServiceImpl";

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	void save(OrderStatistics entity) throws Exception;

	/**
	 * 根据组合条件获取实体，如果条件为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-17
	 * @param hqlHelper
	 * @Return T
	 */
	OrderStatistics getByCondition(HqlHelper hqlHelper) throws Exception;
	
	/**
	 * 根据组合条件获取实体集合
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-11-18
	 * @param hqlHelper
	 *            查询对象
	 * @param dbType
	 *            数据库类型
	 * @Return: T
	 */
	public List<OrderStatistics> getListByCondition(HqlHelper hqlHelper) throws Exception;

	/**
	 * 更新实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	void update(OrderStatistics entity) throws Exception;

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
	 * 根据时间查询订单量
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @Return List
	 */
	public List getOrderByDate(String startDate, String endDate) throws Exception;

	/**
	 * 根据时间段来划分订单
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @Return Map
	 */
	public Map<String, String> sortOrderByDate(String startDate, String endDate) throws Exception;

	/**
	 * 根据日期生成订单柱状图
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param outputStream
	 *            图输出流
	 * @Return OutputStream
	 */
	public OutputStream getOrderImageByDate(String startDate, String endDate, OutputStream outputStream) throws Exception;

	/**
	 * 根据日期生成订单柱状图
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param outputStream
	 *            图输出流
	 * @Return Map
	 */
	public OutputStream getOrderImageByHour(String startDate, String endDate, OutputStream outputStream) throws Exception;

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-18
	 * @param sql
	 *            查询语句
	 * @param firstRow
	 *            起始位置
	 * @param maxRow
	 *            每页需要显示的条数
	 * @Return List
	 */
	public List executeSqlToMap(String sql, int firstRow, int maxRow) throws Exception;

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-18
	 * @param sql
	 *            查询语句
	 * @Return List
	 */
	public List executeSqlToMap(String sql) throws Exception;

	/**
	 * 获取所有实体集合
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-04-16
	 * @param
	 * @Return List
	 */
	List<OrderStatistics> findAll() throws Exception;
}
