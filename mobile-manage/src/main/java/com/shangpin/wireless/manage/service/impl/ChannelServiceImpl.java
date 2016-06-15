package com.shangpin.wireless.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.ChannelDao;
import com.shangpin.wireless.domain.Channel;
import com.shangpin.wireless.domain.PageBean;
import com.shangpin.wireless.manage.service.ChannelService;
import com.shangpin.wireless.util.HqlHelper;

/**
 * 渠道管理
 * 
 * @Author zhouyu
 * @CreatDate 2012-07-16
 */
@Service(ChannelService.SERVICE_NAME)
public class ChannelServiceImpl implements ChannelService {
	@Resource(name = ChannelDao.DAO_NAME)
	private ChannelDao channelDao;

	/**
	 * 获取所有实体集合
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param
	 * @Return List
	 */
	public List<Channel> findAll() throws Exception {
		return channelDao.findAll();
	}

	/**
	 * 根据组合条件获取实体，如果条件为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-17
	 * @param hqlHelper
	 * @Return T
	 */
	public Channel getByCondition(HqlHelper hqlHelper) throws Exception {
		return channelDao.getByCondition(hqlHelper);
	}

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void save(Channel entity) throws Exception {
		channelDao.save(entity);
	}

	/**
	 * 获取分页数据对象
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param pageNum
	 *            页码
	 * @param：hqlHelper 查询对象
	 * @Return PageBean
	 */
	public PageBean getPageBean(int pageNum, HqlHelper hqlHelper) throws Exception {
		return channelDao.getPageBean(pageNum, hqlHelper);
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
		return channelDao.executeSqlToMap(sql, firstRow, maxRow);
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
		return channelDao.executeSqlCount(sql);
	}

	/**
	 * 根据id获取实体，如果id为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param id
	 * @Return T
	 */
	public Channel getById(Long id) throws Exception {
		return channelDao.getById(id);
	}

	/**
	 * 删除实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param id
	 * @Return
	 */
	public void delete(Long id) throws Exception {
		channelDao.delete(id);
	}

	/**
	 * 更新实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void update(Channel entity) throws Exception {
		channelDao.update(entity);
	}
}
