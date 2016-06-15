package com.shangpin.wireless.base.dao;

import java.util.Collection;
import java.util.List;

import com.shangpin.wireless.domain.PageBean;
import com.shangpin.wireless.util.HqlHelper;

/**
 * @Author zhouyu
 * @CreatDate 2012-10-19
 */
public interface ManageBaseDao<T> {
	public final static String DAO_NAME = "com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl";

	/**
	 *  保存实体
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	void save(T entity) throws Exception;

	/**
	 * 删除实体
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param id
	 * @Return
	 */
	void delete(Long id) throws Exception;

	/**
	 *  更新实体
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	void update(T entity) throws Exception;

	/**
	 * 根据id获取实体，如果id为null，则返回null
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param id
	 * @Return T
	 */
	T getById(Long id) throws Exception;

	/**
	 * 根据组合条件获取实体，如果条件为null，则返回null
	 * @Author zhouyu
	 * @CreatDate 2012-07-17
	 * @param hqlHelper
	 * @Return T
	 */
	T getByCondition(HqlHelper hqlHelper) throws Exception;

	/**
	 * 根据id数组获取实体集合，如果ids为null或是没有元素，则返回一个空的集合
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param ids
	 * @Return List
	 */
	List<T> getByIds(Long[] ids) throws Exception;
	
	/**
	 * 根据组合条件获取实体集合
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-11-18
	 * @param hqlHelper
	 *            查询对象
	 * @param dbType
	 *            数据库类型
	 * @Return: T
	 */
	public List<T> getListByCondition(HqlHelper hqlHelper) throws Exception;

	/**
	 * 获取所有实体集合
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param
	 * @Return List
	 */
	List<T> findAll() throws Exception;

	/**
	 * 获取分页数据对象
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @parampageNum 页码
	 * @paramhqlHelper 查询对象
	 * @Return PageBean
	 */
	PageBean getPageBean(int pageNum, HqlHelper hqlHelper) throws Exception;

	/**
	 * 根据sql语句获取结果集条数
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param sql 查询语句
	 * @Return Long
	 */
	public Integer executeSqlCount(String sql) throws Exception;

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param hql 查询语句
	 * @Return List
	 */
	public List executeSqlToMap(final String sql) throws Exception;

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param sql 查询语句
	 * @param firstRow 起始位置
	 * @param maxRow 每页需要显示的条数
	 * @Return List
	 */
	public List executeSqlToMap(final String sql, final int firstRow, final int maxRow) throws Exception;

	/**
	 * 根据sql语句获取数据集合
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param sql 查询语句
	 * @param firstRow 起始位置
	 * @param maxRow 每页需要显示的条数
	 * @param  cls 实体类型
	 * @Return List
	 */
	public List executeSql(final String sql, final int firstRow, final int maxRow, Class cls) throws Exception;

	/**
	 * 根据HQL语句获取数据集合
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param hql查询语句
	 * @Return List
	 */
	public List executeHql(String hql) throws Exception;

	/**
	 * 根据HQL语句获取数据集合
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param hql 查询语句
	 * @param firstRow 起始位置
	 * @param maxRow 每页需要显示的条数
	 * @Return List
	 */
	public List executeHql(final String hql, final int firstRow, final int maxRow) throws Exception;
	
	// 增加或更新实体
//	public void saveOrUpdate(T entity);
	
	/**
	 * 根据条件查询
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Collection<T> findByCondition(String condition) throws Exception;
	
	/**
	 * 根据条件删除
	 * @param condition
	 * @throws Exception
	 */
	public void deleteByCondition(String condition) throws Exception;
}
