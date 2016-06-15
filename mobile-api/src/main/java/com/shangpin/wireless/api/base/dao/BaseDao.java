package com.shangpin.wireless.api.base.dao;

import com.shangpin.wireless.api.domain.PageBean;
import com.shangpin.wireless.api.util.HqlHelper;
import org.hibernate.Session;

import java.util.List;

/**
 * @Author zhouyu
 * @CreateDate: 2012-07-26
 */
@SuppressWarnings("rawtypes")
public interface BaseDao<T> {
	/**
	 * 保存实体
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-26
	 * @param entity
	 *            实体
	 * @param entity
	 *            数据库类型
	 * @Return:
	 */
	void save(T entity, String dbType) throws Exception;

	/**
	 * @see com.shangpin.wireless.api.base.dao.BaseDao#save(T,java.lang.String)
	 */
	T save(T entity) throws Exception;

	
	/**
	 * 保存或者修改
	 * @param entity
	 * @throws Exception
	 */
	public void saveOrUpdate(T entity) throws Exception;
	/**
	 * 删除实体
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-26
	 * @param id
	 * @param dbType
	 *            数据库类型
	 * @Return:
	 */
	void delete(Long id, String dbType) throws Exception;

	/**
	 * 更新实体
	 * 
	 * @Author: zhouyu
	 * 
	 * @CreateDate: 2012-07-26
	 * @param entity
	 *            实体
	 * @param dbType
	 *            数据库类型
	 * @Return:
	 */
	void update(T entity, String dbType) throws Exception;

	/**
	 * 根据id获取实体，如果id为null，则返回null
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-26
	 * @param id
	 * @param dbType
	 *            数据库类型
	 * @Return: T
	 */
	T getById(Long id, String dbType) throws Exception;

	/**
	 * 根据组合条件获取实体集合
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-17
	 * @param hqlHelper
	 *            查询对象
	 * @param dbType
	 *            数据库类型
	 * @Return: T
	 */
	List<T> getListByCondition(HqlHelper hqlHelper, String dbType) throws Exception;

	/**
	 * 根据组合条件获取实体，如果条件为null，则返回null
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-17
	 * @param hqlHelper
	 *            查询对象
	 * @param dbType
	 *            数据库类型
	 * @Return: List<T>
	 */
	T getByCondition(HqlHelper hqlHelper, String dbType) throws Exception;

	/**
	 * 根据id数组获取实体集合，如果ids为null或是没有元素，则返回一个空的集合
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-26
	 * @param ids
	 *            id数组
	 * @param dbType
	 *            数据库类型
	 * @Return: List
	 */
	List<T> getByIds(Long[] ids, String dbType) throws Exception;

	/**
	 * 获取所有实体集合
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-26
	 * @param dbType
	 *            数据库类型
	 * @Return: List
	 */
	List<T> findAll(String dbType) throws Exception;

	/**
	 * 获取分页数据对象
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-26
	 * @param pageNum
	 *            页码
	 * @param hqlHelper
	 *            查询对象
	 * @param dbType
	 *            数据库类型
	 * @Return: PageBean
	 */
	PageBean getPageBean(int pageNum, HqlHelper hqlHelper, String dbType) throws Exception;
	/**
	 * 分页查找某一页数据，为limit 
	 * <br/>
	 * @param pageSize 页大小
	 * @param page 页码 ,从1开始
	 * @param sql hql语句
	 * @param dbType db类型，不可为null
	 * @return 某页的数据
	 * @throws Exception
	 */
	List<T> executeHqlPage(int pageSize,int page,String sql,String dbType) throws Exception;
	/**
	 * 根据sql语句获取结果集条数
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-26
	 * @param sql
	 *            查询语句
	 * @param dbType
	 *            数据库类型
	 * @Return: Long
	 */
	public Integer executeSqlCount(String sql, String dbType) throws Exception;

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-26
	 * @param sql
	 *            查询语句
	 * @param dbType
	 *            数据库类型
	 * @Return: List
	 */
	public List executeSqlToMap(String sql, String dbType) throws Exception;

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-26
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
	public List executeSqlToMap(String sql, int firstRow, int maxRow, String dbType) throws Exception;

	/**
	 * 根据sql语句获取数据集合
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-26
	 * @param sql
	 *            查询语句
	 * @param firstRow
	 *            起始位置
	 * @param maxRow
	 *            每页需要显示的条数
	 * @param cls
	 *            实体类型
	 * @param dbType
	 *            数据库类型
	 * @Return List
	 */
	public List executeSql(String sql, int firstRow, int maxRow, Class cls, String dbType) throws Exception;

	/**
	 * 根据HQL语句获取数据集合
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-26
	 * @param hql
	 *            查询语句
	 * @param dbType
	 *            数据库对象
	 * @Return List
	 */
	public List executeHql(String hql, String dbType) throws Exception;

	
	/**
	 * 根据HQL语句获取数据集合
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-26
	 * @param hql
	 *            查询语句
	 * @param firstRow
	 *            起始位置
	 * @param maxRow
	 *            每页需要显示的条数
	 * @param dbType
	 *            数据库类型
	 * @Return: List
	 */
	public List executeHql(String hql, int firstRow, final int maxRow, String dbType) throws Exception;

	/**
	 * 获取当前可用的Session
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-11
	 * @param dbType
	 *            数据库类型
	 * @Return: Session
	 */
	public Session getSession(String dbType);
}
