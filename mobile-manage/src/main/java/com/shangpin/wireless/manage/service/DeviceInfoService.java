package com.shangpin.wireless.manage.service;

import java.util.List;

import com.shangpin.wireless.domain.DeviceInfo;

/**
 * 设备信息
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-24
 */
public interface DeviceInfoService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.manage.service.impl.DeviceInfoServiceImpl";

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	void save(DeviceInfo entity) throws Exception;

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

}
