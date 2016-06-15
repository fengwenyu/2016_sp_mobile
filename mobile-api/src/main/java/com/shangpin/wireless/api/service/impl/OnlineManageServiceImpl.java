package com.shangpin.wireless.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.OnlineManageDao;
import com.shangpin.wireless.api.domain.OnlineManage;
import com.shangpin.wireless.api.service.OnlineManageService;
import com.shangpin.wireless.api.util.HqlHelper;

/**
 * 上线管理
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-07-30
 */
@Service(OnlineManageService.SERVICE_NAME)
public class OnlineManageServiceImpl implements OnlineManageService {
	@Resource(name = OnlineManageDao.DAO_NAME)
	private OnlineManageDao onlineManageDao;

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-30
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
		return onlineManageDao.executeSqlToMap(sql, firstRow, maxRow, dbType);
	}

	/**
	 * 根据sql语句获取结果集条数
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-30
	 * @param sql
	 *            查询语句
	 * @param dbType
	 *            数据库类型
	 * @Return: Long
	 */
	public Integer executeSqlCount(String sql, String dbType) throws Exception {
		return onlineManageDao.executeSqlCount(sql, dbType);
	}

	/**
	 * 保存实体
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-30
	 * @param entity
	 * @param dbType
	 *            数据库类型
	 * @throws Exception
	 * @Return:
	 */

	public void save(OnlineManage model, String dbType) throws Exception {
		onlineManageDao.save(model, dbType);
	}

	/**
	 *根据版本号，渠道号和产品号来查找一个上线产品
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-08-03
	 * @param hqlHelper
	 *            查询对象
	 * @param dbType
	 *            数据库类型
	 * @Return:
	 */
	public OnlineManage getByCondition(HqlHelper hqlHelper, String dbType) throws Exception {
		return onlineManageDao.getByCondition(hqlHelper, dbType);
	}
}
