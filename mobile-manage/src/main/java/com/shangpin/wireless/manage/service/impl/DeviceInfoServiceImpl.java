package com.shangpin.wireless.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.DeviceInfoDao;
import com.shangpin.wireless.domain.DeviceInfo;
import com.shangpin.wireless.manage.service.DeviceInfoService;

/**
 * 设备信息
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-24
 */
@Service(DeviceInfoService.SERVICE_NAME)
public class DeviceInfoServiceImpl implements DeviceInfoService {
	@Resource(name = DeviceInfoDao.DAO_NAME)
	private DeviceInfoDao deviceInfoDao;

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	public void save(DeviceInfo entity) throws Exception {
		deviceInfoDao.save(entity);
	}

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
	public List executeSqlToMap(String sql, int firstRow, int maxRow) throws Exception {
		return deviceInfoDao.executeSqlToMap(sql, firstRow, maxRow);// 保存到数据库中
	}

	/**
	 * 根据sql语句获取结果集条数
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param sql
	 *            查询语句
	 * @Return Long
	 */
	public Integer executeSqlCount(String sql) throws Exception {
		return deviceInfoDao.executeSqlCount(sql);
	}
}
