package com.shangpin.wireless.api.service;

import java.util.List;

import com.shangpin.wireless.domain.ErrorLog;

/**
 * 错误日志管理
 * 
 * @Author  zhouyu
 * @CreatDate  2012-10-18
 */
public interface ErrorLogService {

	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.ErrorLogServiceImpl";

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author  zhouyu
	 * @CreateDate  2012-10-18
	 * @param sql
	 *            查询语句
	 * @param firstRow
	 *            起始位置
	 * @param maxRow
	 *            每页需要显示的条数
	 * @Return  List
	 */
	public List executeSqlToMap(String sql, int firstRow, int maxRow) throws Exception;

	/**
	 * 根据sql语句获取结果集条数
	 * 
	 * @Author  zhouyu
	 * @CreateDate  2012-10-18
	 * @param sql
	 *            查询语句
	 * @Return  Long
	 */
	public Integer executeSqlCount(String sql) throws Exception;

	/**
	 * 保存实体
	 * 
	 * @Author  zhouyu
	 * @CreateDate  2012-10-18
	 * @param model
	 * @Return 
	 */
	public void save(ErrorLog model) throws Exception;

	/**
	 * 根据ID获取实体
	 * 
	 * @Author  zhouyu
	 * @CreateDate  2012-10-18
	 * @param id
	 * @Return 
	 */
	public ErrorLog getById(Long id) throws Exception;
}
