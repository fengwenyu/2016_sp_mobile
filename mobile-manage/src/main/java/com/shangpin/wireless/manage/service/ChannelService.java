package com.shangpin.wireless.manage.service;

import java.util.List;

import com.shangpin.wireless.domain.Channel;
import com.shangpin.wireless.domain.PageBean;
import com.shangpin.wireless.util.HqlHelper;

/**
 * 渠道管理
 * 
 * @Author zhouyu
 * @CreatDate 2012-07-16
 */
public interface ChannelService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.service.impl.ChannelServiceImpl";

	/**
	 * 获取所有实体集合
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param
	 * @Return List
	 */
	public List<Channel> findAll() throws Exception;

	/**
	 * 根据组合条件获取实体，如果条件为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-17
	 * @param hqlHelper
	 * @Return T
	 */
	public Channel getByCondition(HqlHelper hqlHelper) throws Exception;

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void save(Channel entity) throws Exception;

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
	public PageBean getPageBean(int pageNum, HqlHelper hqlHelper) throws Exception;

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
	public Channel getById(Long id) throws Exception;

	/**
	 * 删除实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param id
	 * @Return
	 */
	public void delete(Long id) throws Exception;

	/**
	 * 更新实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void update(Channel entity) throws Exception;
}
