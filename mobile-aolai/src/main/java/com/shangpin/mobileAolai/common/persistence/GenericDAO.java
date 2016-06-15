package com.shangpin.mobileAolai.common.persistence;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;

import com.shangpin.mobileAolai.common.page.Page;

@SuppressWarnings("rawtypes")
public interface GenericDAO<T extends Serializable, PK extends Serializable>
		extends Serializable {

	// -------------------- 基本检索、增加、修改、删除操作 --------------------

	// 根据主键获取实体。如果没有相应的实体，返回 null。
	public T get(PK id);

	// 根据主键获取实体并加锁。如果没有相应的实体，返回 null。
	public T getWithLock(PK id, LockMode lock);

	// 根据主键获取实体。如果没有相应的实体，抛出异常。
	public T load(PK id);

	// 根据主键获取实体并加锁。如果没有相应的实体，抛出异常。
	public T loadWithLock(PK id, LockMode lock);

	// 获取全部实体。
	public List<T> loadAll();

	// 更新实体
	public void update(T entity);

	// 更新实体并加锁
	public void updateWithLock(T entity, LockMode lock);

	// 存储实体到数据库
	public Serializable save(T entity);

	// 增加或更新实体
	public void saveOrUpdate(T entity);

	// 增加或更新集合中的全部实体
	public void saveOrUpdateAll(Collection<T> entities);

	// 删除指定的实体
	public void delete(T entity);

	// 加锁并删除指定的实体
	public void deleteWithLock(T entity, LockMode lock);

	// 根据主键删除指定实体
	public void deleteByKey(PK id);

	// 根据主键加锁并删除指定的实体
	public void deleteByKeyWithLock(PK id, LockMode lock);

	// 删除集合中的全部实体
	public void deleteAll(Collection<T> entities);

	// -------------------- HSQL ----------------------------------------------

	// 使用HSQL语句直接增加、更新、删除实体
	public int bulkUpdate(String queryString);

	// 使用带参数的HSQL语句增加、更新、删除实体
	public int bulkUpdate(String queryString, Object[] values);

	// 使用HSQL语句检索数据
	public List find(String queryString);

	// 使用带参数的HSQL语句检索数据
	public List find(String queryString, Object[] values);

	public T findByNamedParam(String queryString, String paramName, Object value);

	// 使用带命名的参数的HSQL语句检索数据
	public List findByNamedParam(String queryString, String[] paramNames,
			Object[] values);

	// 使用命名的HSQL语句检索数据
	public List findByNamedQuery(String queryName);

	// 使用带参数的命名HSQL语句检索数据
	public List findByNamedQuery(String queryName, Object[] values);

	// 使用带命名参数的命名HSQL语句检索数据
	public List findByNamedQueryAndNamedParam(String queryName,
			String[] paramNames, Object[] values);

	// 使用HSQL语句检索数据，返回 Iterator

	public Iterator iterate(String queryString);

	// 使用带参数HSQL语句检索数据，返回 Iterator

	public Iterator iterate(String queryString, Object[] values);

	// 关闭检索返回的 Iterator

	public void closeIterator(Iterator it);

	// -------------------------------- Criteria ------------------------------

	// 创建与会话无关的检索标准对象

	public DetachedCriteria createDetachedCriteria();

	// 创建与会话绑定的检索标准对象

	public Criteria createCriteria();

	// 使用指定的检索标准检索数据
	// public List findByCriteria(DetachedCriteria criteria);

	// 使用指定的检索标准检索数据，返回部分记录
	public List findByCriteria(DetachedCriteria criteria, int firstResult,
			int maxResults);

	// 使用指定的实体及属性检索（满足除主键外属性＝实体值）数据
	public List<T> findEqualByEntity(T entity, String[] propertyNames);

	// 使用指定的实体及属性(非主键)检索（满足属性 like 串实体值）数据
	// public List<T> findLikeByEntity(T entity, String[] propertyNames);

	// 使用指定的检索标准检索数据，返回指定范围的记录

	public Integer getRowCount(DetachedCriteria criteria);

	// 使用指定的检索标准检索数据，返回指定统计值
	public Object getStatValue(DetachedCriteria criteria, String propertyName,
			String StatName);

	// -------------------------------- Others --------------------------------

	// 加锁指定的实体
	public void lock(T entity, LockMode lockMode);

	// 强制初始化指定的实体
	public void initialize(Object proxy);

	// 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）
	public void flush();

	// session的clear
	public void clear();

	// -----------------------------page-------------------------------------

	public Page findPageByCriteria(final DetachedCriteria detachedCriteria);

	public Page findPageByCriteria(final DetachedCriteria detachedCriteria,
			final int startIndex);

	public Page findPageByCriteria(final DetachedCriteria detachedCriteria,
			final int startIndex, final int pageSize);

	public Object queryForObject(final String select, final Object[] values);
	
	public List<T> queryForList(final String select);

	public Page queryForpage(String select, Object[] values, int pagestart,
			int pagesize, LinkedHashMap<String, String> orderby);

	public Page queryForpage(String hql, Object values, int offset,
			int pagesize, LinkedHashMap<String, String> orderby);

	public Page queryForpage(String hql, int offset, int pagesize,
			LinkedHashMap<String, String> orderby);

	public Page queryForpage(String hql, Object[] params,
			LinkedHashMap<String, String> orderby);

	public Page queryForpage(String hql, LinkedHashMap<String, String> orderby);

	public Page queryForpage(String hql);

	public Page queryForPage(final String selectCount, final String select,
			final Object[] values, int pagestart, final int pagesize,
			final LinkedHashMap<String, String> orderby);

	/**
	 * 通过sql查询分页列表
	 * 
	 * @param listSql
	 *            获取列表的SQL
	 * @param countSql
	 *            查询总数的SQL
	 * @param rowMapper
	 *            结果数组转化成对象
	 * @param pagestart
	 * @param pagesize
	 * @return
	 */
	public Page sqlQueryForPage(String listSql, Object[] params, int pagestart,
			int pagesize, LinkedHashMap<String, String> orderby);

	/**
	 * 通过sql查询分页列表
	 * 
	 * @param listSql
	 * @param countSql
	 * @param rowMapper
	 * @return
	 */
	public Page sqlQueryForPage(String listSql, Object[] params,
			LinkedHashMap<String, String> orderby);

	/**
	 * 通过SQL查询实体列表
	 * 
	 * @param sql
	 *            查询的sql
	 * @param params
	 *            参数
	 * @param rowMapper
	 *            对象映射接口
	 * @return
	 * @see com.cmbc.mall.util.dao.RowMapper
	 */

	/**
	 * 获取sql查询的结果集
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> sqlQueryForList(String sql,
			Object[] params, LinkedHashMap<String, String> orderby);

	/**
	 * 获取sql分页查询的结果集
	 * 
	 * @param sql
	 * @param params
	 * @param offSet
	 * @param pagesize
	 * @return
	 */
	public List<Map<String, Object>> sqlQueryForList(String sql,
			Object[] params, Integer offSet, Integer pagesize,
			LinkedHashMap<String, String> orderby);

	/**
	 * 根据查询条件查询对象 example: QueryCondition conn = new QueryCondition();
	 * 
	 * conn.putCondition("productName", "test001")
	 * .putCondition("categoryId",12288) .addOrder("checkDate");
	 * 
	 * Page page = productsDAO.queryForPageByConditon(conn);
	 * 
	 * @param conn
	 * @return
	 */
	public Page queryForPageByConditon(QueryCondition conn);
}
