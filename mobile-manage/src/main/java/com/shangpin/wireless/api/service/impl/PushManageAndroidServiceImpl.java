package com.shangpin.wireless.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.service.PushManageAndroidService;
import com.shangpin.wireless.dao.PushManageAndroidDao;
import com.shangpin.wireless.domain.PushManageAndroid;
import com.shangpin.wireless.util.HqlHelper;

/**
 * 推送消息配置
 * 
 * @Author zhouyu
 * @CreatDate 2013-02-25
 */
@Service(PushManageAndroidService.SERVICE_NAME)
public class PushManageAndroidServiceImpl implements PushManageAndroidService {
	@Resource(name = PushManageAndroidDao.DAO_NAME)
	private PushManageAndroidDao pushManageAndroidDao;

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
		return pushManageAndroidDao.executeSqlToMap(sql, firstRow, maxRow);
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
		return pushManageAndroidDao.executeSqlCount(sql);
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

	public void save(PushManageAndroid model) throws Exception {
		pushManageAndroidDao.save(model);
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
	public PushManageAndroid getByCondition(HqlHelper hqlHelper) throws Exception {
		return pushManageAndroidDao.getByCondition(hqlHelper);
	}

	/**
	 * 根据id获取实体，如果id为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param id
	 * @Return T
	 */
	public PushManageAndroid getById(Long id) throws Exception {
		return pushManageAndroidDao.getById(id);
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
		pushManageAndroidDao.delete(id);
	}

	/**
	 * 更新实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void update(PushManageAndroid entity) throws Exception {
		pushManageAndroidDao.update(entity);
	}

	/**
	 * 查询最大id
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @Return long
	 */
	public long getMaxId() throws Exception {
		return pushManageAndroidDao.getMaxId();
	}

	public List executeHql(final String hql) throws Exception {
		return pushManageAndroidDao.executeHql(hql);
	}
}
