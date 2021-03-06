package com.shangpin.wireless.api.service;

import java.util.List;

import com.shangpin.wireless.domain.Feedback;

/**
 * 信息反馈管理
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-22
 */
public interface FeedbackService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.FeedbackServiceImpl";

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-22
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
	 * 根据sql语句获取结果集条数
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-22
	 * @param sql
	 *            查询语句
	 * @Return Long
	 */
	public Integer executeSqlCount(String sql) throws Exception;

	/**
	 * 根据id获取实体，如果id为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param id
	 * @Return Feedback
	 */
	Feedback getById(Long id) throws Exception;
}
