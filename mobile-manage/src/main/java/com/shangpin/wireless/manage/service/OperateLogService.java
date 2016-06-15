package com.shangpin.wireless.manage.service;

import java.util.List;

import com.shangpin.wireless.domain.OperateLog;
import com.shangpin.wireless.domain.User;

/**
 * 操作日志
 * 
 * @Author zhouyu
 * @CreatDate 2012-07-13
 */
public interface OperateLogService {

	public final static String SERVICE_NAME = "com.shangpin.wireless.service.impl.OperateLogServiceImpl";

	/**
	 * 保存操作日志
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-16
	 * @param user
	 *            用户
	 * @param content
	 *            操作日志内容
	 * @Return
	 * @throws Exception
	 */
	public void saveLog(User user, String content);

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
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
	 * @CreatDate 2012-07-11
	 * @param sql
	 *            查询语句
	 * @Return Long
	 */
	public Integer executeSqlCount(String sql) throws Exception;

	/**
	 * 根据id获取实体，如果id为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param id
	 * @Return T
	 */
	public OperateLog getById(Long id) throws Exception;

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
