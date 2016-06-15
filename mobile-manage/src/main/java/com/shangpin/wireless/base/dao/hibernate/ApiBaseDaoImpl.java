package com.shangpin.wireless.base.dao.hibernate;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shangpin.wireless.base.dao.ApiBaseDao;
import com.shangpin.wireless.cfg.Configuration;
import com.shangpin.wireless.domain.PageBean;
import com.shangpin.wireless.util.HqlHelper;

/**
 * @Author zhouyu
 * @CreatDate 2012-07-11
 */
@SuppressWarnings("unchecked")
public class ApiBaseDaoImpl<T> extends HibernateDaoSupport implements ApiBaseDao<T> {

	@Resource(name = "sessionFactoryAPI")
	public void setSessionFactoryAPI(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	protected Class<T> clazz;

	public ApiBaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class) pt.getActualTypeArguments()[0];
	}

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void save(T entity) throws Exception {
		getHibernateTemplate().save(entity);
	}

	/**
	 * 更新实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void update(T entity) throws Exception {
		getHibernateTemplate().update(entity);
	}

	
	/**
	 * 删除实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param id
	 * @Return
	 */
	public void delete(final Long id) throws Exception {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				Object obj = session.get(clazz, id);
				getHibernateTemplate().delete(obj);
				return null;
			}
		};
		getHibernateTemplate().execute(callback);

	}

	/**
	 * 根据id获取实体，如果id为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param id
	 * @Return T
	 */
	public T getById(Long id) throws Exception {
		if (id == null) {
			return null;
		} else {
			return (T) getHibernateTemplate().get(clazz, id);
		}
	}

	/**
	 * 根据id数组获取实体集合，如果ids为null或是没有元素，则返回一个空的集合
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param ids
	 * @Return List
	 */
	public List<T> getByIds(final Long[] ids) throws Exception {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				if (ids == null || ids.length == 0) {
					return Collections.EMPTY_LIST;
				}
				return session.createQuery(//
						"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
						.setParameterList("ids", ids)//
						.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/**
	 * 获取所有实体集合
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param
	 * @Return List
	 */
	public List<T> findAll() throws Exception {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				return session.createQuery(//
						"FROM " + clazz.getSimpleName())//
						.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
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
	public PageBean getPageBean(final int pageNum, final HqlHelper hqlHelper) throws Exception {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				// pageSize是配置的值
				int pageSize = Configuration.getPageSize();
				List<Object> parameters = hqlHelper.getParameters();
				// 查询本页的数据列表
				Query listQuery = session.createQuery(hqlHelper.getQueryListHql()); // 生成查询对象
				if (parameters != null) {
					for (int i = 0; i < parameters.size(); i++) { // 设置参数
						listQuery.setParameter(i, parameters.get(i));
					}
				}
				listQuery.setFirstResult((pageNum - 1) * pageSize);
				listQuery.setMaxResults(pageSize);
				List list = listQuery.list(); // 执行查询
				// 查询总记录数
				Query countQuery = session.createQuery(hqlHelper.getQueryCountHql()); // 生成查询对象
				if (parameters != null) {
					for (int i = 0; i < parameters.size(); i++) { // 设置参数
						countQuery.setParameter(i, parameters.get(i));
					}
				}
				Long count = (Long) countQuery.uniqueResult(); // 执行查询

				return new PageBean(pageNum, pageSize, count.intValue(), list);
			}
		};
		return (PageBean) getHibernateTemplate().execute(callback);
	}

	/**
	 * 根据HQL语句获取数据集合
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param hql
	 * @Return List
	 */
	public List executeHql(final String hql) throws Exception {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				Query query = session.createQuery(hql);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);

	}

	/**
	 * 根据HQL语句获取数据集合
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param hql
	 *            查询语句
	 * @param firstRow
	 *            起始位置
	 * @param maxRow
	 *            每页需要显示的条数
	 * @Return List
	 */
	public List executeHql(final String hql, final int firstRow, final int maxRow) throws Exception {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult((firstRow - 1) * maxRow);
				query.setMaxResults(maxRow);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/**
	 * 根据sql语句获取数据集合
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param sql
	 *            查询语句
	 * @param firstRow
	 *            起始位置
	 * @param maxRow
	 *            每页需要显示的条数
	 * @param cls
	 *            实体类型
	 * @Return List
	 */
	public List executeSql(final String sql, final int firstRow, final int maxRow, final Class cls) throws Exception {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				if (firstRow > 0) {
					sqlQuery.setFirstResult((firstRow - 1) * maxRow);
				}
				if (maxRow > 0) {
					sqlQuery.setMaxResults(maxRow);
				}
				List lstResults = sqlQuery.addEntity(cls).list();
				return lstResults;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
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
		List executeSqlToMap = executeSqlToMap(sql);
		return Integer.parseInt(((Map) executeSqlToMap.get(0)).get("count(*)").toString());
	}

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param hql
	 *            查询语句
	 * @Return List
	 */
	public List executeSqlToMap(final String sql) throws Exception {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				return session.createSQLQuery(sql).setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
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
	public List executeSqlToMap(final String sql, final int firstRow, final int maxRow) throws Exception {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				if (firstRow > 0) {
					sqlQuery.setFirstResult((firstRow - 1) * maxRow);
				}
				if (maxRow > 0) {
					sqlQuery.setMaxResults(maxRow);
				}
				List lstResults = sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
				return lstResults;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/**
	 * 根据组合条件获取实体，如果条件为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-17
	 * @param hqlHelper
	 * @Return T
	 */
	public T getByCondition(final HqlHelper hqlHelper) throws Exception {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				List<Object> parameters = hqlHelper.getParameters();
				// 查询本页的数据列表
				Query listQuery = session.createQuery(hqlHelper.getQueryListHql()); // 生成查询对象
				if (parameters != null) {
					for (int i = 0; i < parameters.size(); i++) { // 设置参数
						listQuery.setParameter(i, parameters.get(i));
					}
				}
				return (T) listQuery.uniqueResult(); // 执行查询
			}
		};
		return (T) getHibernateTemplate().execute(callback);

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
	public List<T> getListByCondition(HqlHelper hqlHelper) throws Exception {
		List<Object> parameters = hqlHelper.getParameters();
		// 查询本页的数据列表
		Query listQuery = getSession().createQuery(hqlHelper.getQueryListHql()); // 生成查询对象
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
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param
	 * @Return Session
	 */
	// protected Session getSession() {
	// return sessionFactoryAPI.getCurrentSession();
	// }
	// 增加或更新实体
	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}
	
	public Collection<T> findByCondition(String condition){
		String queryString = "from " + this.clazz.getName() + " where " + condition;
		return (List<T>) this.getHibernateTemplate().find(queryString);
	}
	
	public void deleteByCondition(String condition){
		Collection<T> entities = findByCondition(condition);
		getHibernateTemplate().deleteAll(entities);
	}
}
