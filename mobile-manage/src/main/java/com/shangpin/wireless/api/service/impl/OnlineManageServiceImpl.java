package com.shangpin.wireless.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.service.OnlineManageService;
import com.shangpin.wireless.dao.OnlineManageDao;
import com.shangpin.wireless.domain.OnlineManage;
import com.shangpin.wireless.util.HqlHelper;

/**
 * 上线管理
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-17
 */
@Service(OnlineManageService.SERVICE_NAME)
public class OnlineManageServiceImpl implements OnlineManageService {
	@Resource(name = OnlineManageDao.DAO_NAME)
	private OnlineManageDao onlineManageDao;

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-17
	 * @param sql
	 *            查询语句
	 * @param firstRow
	 *            起始位置
	 * @param maxRow
	 *            每页需要显示的条数
	 * @Return List
	 */
	public List executeSqlToMap(String sql, int firstRow, int maxRow) throws Exception {
		return onlineManageDao.executeSqlToMap(sql, firstRow, maxRow);
	}

	/**
	 * 根据sql语句获取结果集条数
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-17
	 * @param sql
	 *            查询语句
	 * @Return Long
	 */
	public Integer executeSqlCount(String sql) throws Exception {
		return onlineManageDao.executeSqlCount(sql);
	}

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-17
	 * @param entity
	 * @throws Exception
	 * @Return
	 */

	public void save(OnlineManage model) throws Exception {
		onlineManageDao.save(model);
	}

	/**
	 *根据版本号，渠道号和产品号来查找一个上线产品
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-17
	 * @param hqlHelper
	 *            查询对象
	 * @Return
	 */
	public OnlineManage getByCondition(HqlHelper hqlHelper) throws Exception {
		return onlineManageDao.getByCondition(hqlHelper);
	}

	/**
	 * 根据id获取实体，如果id为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param id
	 * @Return T
	 */
	public OnlineManage getById(Long id) throws Exception {
		return onlineManageDao.getById(id);
	}

	/**
	 * 删除实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param id
	 * @Return
	 */
	public void delete(Long id) throws Exception {
		onlineManageDao.delete(id);
	}
	/**
	 * 更新实体
	 * 
	 * @Author  zhouyu
	 * @CreatDate  2012-07-11
	 * @param entity
	 * @Return 
	 */
	public void update(OnlineManage entity) throws Exception {
		onlineManageDao.update(entity);
	}
}
