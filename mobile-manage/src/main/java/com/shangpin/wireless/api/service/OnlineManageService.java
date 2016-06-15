package com.shangpin.wireless.api.service;

import java.util.List;

import com.shangpin.wireless.domain.OnlineManage;
import com.shangpin.wireless.util.HqlHelper;

/**
 * 上线管理
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-18
 */
public interface OnlineManageService {

	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.OnlineManageServiceImpl";

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
	 * 根据sql语句获取结果集条数
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-18
	 * @param sql
	 *            查询语句
	 * @Return Long
	 */
	public Integer executeSqlCount(String sql) throws Exception;

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-18
	 * @param entity
	 * @Return
	 */
	public void save(OnlineManage model) throws Exception;

	/**
	 *根据版本号，渠道号和产品号来查找一个上线产品
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-18
	 * @param hqlHelper
	 *            查询对象
	 * @Return
	 */
	public OnlineManage getByCondition(HqlHelper hqlHelper) throws Exception;

	/**
	 * 根据id获取实体，如果id为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param id
	 * @Return T
	 */
	OnlineManage getById(Long id) throws Exception;

	/**
	 * 删除实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param id
	 * @Return
	 */
	void delete(Long id) throws Exception;

	/**
	 * 更新实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void update(OnlineManage entity) throws Exception;
}
