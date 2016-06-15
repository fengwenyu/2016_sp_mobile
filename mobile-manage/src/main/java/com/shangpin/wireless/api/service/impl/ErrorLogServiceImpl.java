package com.shangpin.wireless.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.service.ErrorLogService;
import com.shangpin.wireless.dao.ErrorLogDao;
import com.shangpin.wireless.domain.ErrorLog;

/**
 * 错误日志管理
 * 
 * @Author  zhouyu
 * @CreatDate  2012-10-18
 */
@Service(ErrorLogService.SERVICE_NAME)
public class ErrorLogServiceImpl implements ErrorLogService {
	@Resource(name = ErrorLogDao.DAO_NAME)
	private ErrorLogDao errorLogDao;

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
	public List executeSqlToMap(String sql, int firstRow, int maxRow) throws Exception {
		return errorLogDao.executeSqlToMap(sql, firstRow, maxRow);
	}

	/**
	 * 根据sql语句获取结果集条数
	 * 
	 * @Author  zhouyu
	 * @CreateDate  2012-10-18
	 * @param sql
	 *            查询语句
	 * @Return  Long
	 */
	public Integer executeSqlCount(String sql) throws Exception {
		return errorLogDao.executeSqlCount(sql);
	}

	/**
	 * 保存实体
	 * 
	 * @Author  zhouyu
	 * @CreateDate  2012-10-18
	 * @param entity
	 * @throws Exception
	 * @Return 
	 */

	public void save(ErrorLog model) throws Exception {
		errorLogDao.save(model);
	}

	/**
	 * 根据ID获取实体
	 * 
	 * @Author  zhouyu
	 * @CreateDate  2012-10-18
	 * @param id
	 * @throws Exception
	 * @Return 
	 */
	public ErrorLog getById(Long id) throws Exception {
		return errorLogDao.getById(id);
	}
}
