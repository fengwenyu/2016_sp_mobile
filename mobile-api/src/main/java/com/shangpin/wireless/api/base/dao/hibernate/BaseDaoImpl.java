package com.shangpin.wireless.api.base.dao.hibernate;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;

import com.shangpin.wireless.api.base.dao.BaseDao;
import com.shangpin.wireless.api.cfg.Configuration;
import com.shangpin.wireless.api.domain.PageBean;
import com.shangpin.wireless.api.util.HqlHelper;

/**
 * @Author zhouyu
 * @CreateDate: 2012-07-11
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource(name = "sessionFactoryAPI")
	private SessionFactory sessionFactoryAPI;

	protected Class<T> clazz;

	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class) pt.getActualTypeArguments()[0];
	}

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
	public void save(T entity, String dbType) throws Exception {
		getSession(dbType).saveOrUpdate(entity);
	}
	
	public void saveOrUpdate(T entity) throws Exception{
		sessionFactoryAPI.getCurrentSession().saveOrUpdate(entity);
	}
	/**
	 * @see com.shangpin.wireless.api.base.dao.BaseDao#save(T,java.lang.String)
	 */
	public T save(T entity) throws Exception {
		return (T)getSession(null).save(entity);
	}

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
	public void update(T entity, String dbType) throws Exception {
		getSession(dbType).update(entity);
	}

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
	public void delete(Long id, String dbType) throws Exception {
		Object obj = getSession(dbType).get(clazz, id);
		getSession(dbType).delete(obj);
	}

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
	public T getById(Long id, String dbType) throws Exception {
		if (id == null) {
			return null;
		} else {
			return (T) getSession(dbType).get(clazz, id);
		}
	}

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
	public List<T> getByIds(Long[] ids, String dbType) throws Exception {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		}

		return getSession(dbType).createQuery(//
				"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
				.setParameterList("ids", ids)//
				.list();
	}

	/**
	 * 获取所有实体集合
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-26
	 * @param dbType
	 *            数据库类型
	 * @Return: List
	 */
	public List<T> findAll(String dbType) throws Exception {
		return getSession(dbType).createQuery(//
				"FROM " + clazz.getSimpleName())//
				.list();
	}

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
	public PageBean getPageBean(int pageNum, HqlHelper hqlHelper, String dbType) throws Exception {
		// pageSize是配置的值
		int pageSize = Configuration.getPageSize();
		List<Object> parameters = hqlHelper.getParameters();
		// 查询本页的数据列表
		Query listQuery = getSession(dbType).createQuery(hqlHelper.getQueryListHql()); // 生成查询对象
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) { // 设置参数
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		List list = listQuery.list(); // 执行查询

		// 查询总记录数
		Query countQuery = getSession(dbType).createQuery(hqlHelper.getQueryCountHql()); // 生成查询对象
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) { // 设置参数
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long count = (Long) countQuery.uniqueResult(); // 执行查询
		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}

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
	public List executeHql(String hql, String dbType) throws Exception {
		Query query = getSession(dbType).createQuery(hql);
		return query.list();
	}

	/**
	 * 根据HQL语句获取数据集合
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-11
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
	public List executeHql(String hql, int firstRow, int maxRow, String dbType) throws Exception {
		Query query = getSession(dbType).createQuery(hql);
		query.setFirstResult((firstRow - 1) * maxRow);
		query.setMaxResults(maxRow);
		return query.list();
	}

	/**
	 * 根据sql语句获取数据集合
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-11
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
	 * @Return: List
	 */
	public List executeSql(String sql, int firstRow, int maxRow, Class cls, String dbType) throws Exception {
		SQLQuery sqlQuery = getSession(dbType).createSQLQuery(sql);
		if (firstRow > 0) {
			sqlQuery.setFirstResult((firstRow - 1) * maxRow);
		}
		if (maxRow > 0) {
			sqlQuery.setMaxResults(maxRow);
		}
		List lstResults = sqlQuery.addEntity(cls).list();
		return lstResults;
	}

	/**
	 * 根据sql语句获取结果集条数
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-11
	 * @param sql
	 *            查询语句
	 * @param dbType
	 *            数据库类型
	 * @Return: Long
	 */
	public Integer executeSqlCount(String sql, String dbType) throws Exception {
		List executeSqlToMap = executeSqlToMap(sql, dbType);
		return Integer.parseInt(((Map) executeSqlToMap.get(0)).get("count(*)").toString());
	}

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-11
	 * @param hql
	 *            查询语句
	 * @param dbType
	 *            数据库类型
	 * @Return: List
	 */
	public List executeSqlToMap(String sql, String dbType) throws Exception {
		return getSession(dbType).createSQLQuery(sql).setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
	}

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-11
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
		SQLQuery sqlQuery = getSession(dbType).createSQLQuery(sql);
		if (firstRow > 0) {
			sqlQuery.setFirstResult((firstRow - 1) * maxRow);
		}
		if (maxRow > 0) {
			sqlQuery.setMaxResults(maxRow);
		}
		List lstResults = sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return lstResults;
	}

	/**
	 * 根据组合条件获取实体，如果条件为null，则返回null
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-17
	 * @param hqlHelper
	 *            查询对象
	 * @param dbType
	 *            数据库类型
	 * @Return: T
	 */
	public T getByCondition(HqlHelper hqlHelper, String dbType) throws Exception {
		List<Object> parameters = hqlHelper.getParameters();
		// 查询本页的数据列表
		Query listQuery = getSession(dbType).createQuery(hqlHelper.getQueryListHql()); // 生成查询对象
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) { // 设置参数
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		listQuery.setMaxResults(1);
		return (T) listQuery.uniqueResult(); // 执行查询
	}

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
	public List<T> getListByCondition(HqlHelper hqlHelper, String dbType) throws Exception {
		List<Object> parameters = hqlHelper.getParameters();
		// 查询本页的数据列表
		Query listQuery = getSession(dbType).createQuery(hqlHelper.getQueryListHql()); // 生成查询对象
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) { // 设置参数
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		return listQuery.list();// 执行查询
	}

	/**
	 * 获取当前可用的Session
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-07-11
	 * @param dbType
	 *            数据库类型
	 * @Return: Session
	 */
	public Session getSession(String dbType) {
		return sessionFactoryAPI.getCurrentSession();
	}

	@Override
	public List<T> executeHqlPage(int pageSize, int page, String sql,
			String dbType) throws Exception {
		return getSession(dbType).createQuery(sql).setMaxResults(pageSize).setFirstResult((page-1)*pageSize).list();
	}
}
