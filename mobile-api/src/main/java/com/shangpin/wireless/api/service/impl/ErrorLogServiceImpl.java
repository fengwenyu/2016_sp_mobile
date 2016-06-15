package com.shangpin.wireless.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.ErrorLogDao;
import com.shangpin.wireless.api.domain.ErrorLog;
import com.shangpin.wireless.api.service.ErrorLogService;

/**
 * 错误日志管理
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-07-31
 */
@Service(ErrorLogService.SERVICE_NAME)
public class ErrorLogServiceImpl implements ErrorLogService {
	@Resource(name = ErrorLogDao.DAO_NAME)
	private ErrorLogDao errorLogDao;

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-31
	 * @param sql
	 *            查询语句
	 * @param firstRow
	 *            起始位置
	 * @param maxRow
	 *            每页需要显示的条数
	 * @param dbType
	 *            数据库类型
	 * @Return: List
	 */
	public List executeSqlToMap(String sql, int firstRow, int maxRow, String dbType) throws Exception {
		return errorLogDao.executeSqlToMap(sql, firstRow, maxRow, dbType);
	}

	/**
	 * 根据sql语句获取结果集条数
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-31
	 * @param sql
	 *            查询语句
	 * @param dbType
	 *            数据库类型
	 * @Return: Long
	 */
	public Integer executeSqlCount(String sql, String dbType) throws Exception {
		return errorLogDao.executeSqlCount(sql, dbType);
	}

	/**
	 * 保存实体
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-31
	 * @param entity
	 * @param dbType
	 *            数据库类型
	 * @throws Exception
	 * @Return:
	 */

	public void save(ErrorLog model, String dbType) throws Exception {
		errorLogDao.save(model, dbType);
	}

	/**
	 * 根据ID获取实体
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-31
	 * @param id
	 * @param dbType
	 *            数据库类型
	 * @throws Exception
	 * @Return:
	 */
	public ErrorLog getById(Long id, String dbType) throws Exception {
		return errorLogDao.getById(id, dbType);
	}
}
