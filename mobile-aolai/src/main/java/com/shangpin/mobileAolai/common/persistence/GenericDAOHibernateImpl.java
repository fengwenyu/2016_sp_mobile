package com.shangpin.mobileAolai.common.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shangpin.mobileAolai.common.page.Page;
import com.shangpin.mobileAolai.common.page.PageContext;

@SuppressWarnings({ "unchecked", "rawtypes" })
// @Transactional
public class GenericDAOHibernateImpl<T extends Serializable, PK extends Serializable>
		extends HibernateDaoSupport {

	@Resource(name = "sessionFactory")
	// 为父类HibernateDaoSupport注入sessionFactory的值
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	// 实体类类型(由构造方法自动赋值)
	private Class<T> entityClass;

	// 构造方法，根据实例类自动获取实体类类型
	public GenericDAOHibernateImpl() {
		this.entityClass = null;
		Class c = getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.entityClass = (Class<T>) p[0];
		}
	}

	// -------------------- 基本检索、增加、修改、删除操作 --------------------

	// 根据主键获取实体。如果没有相应的实体，返回 null。

	/**
	 * 
	 */
	public T get(PK id) {

		return (T) getHibernateTemplate().get(entityClass, id);
	}

	// 根据主键获取实体并加锁。如果没有相应的实体，返回 null。
	public T getWithLock(PK id, LockMode lock) {
		T t = (T) getHibernateTemplate().get(entityClass, id, lock);
		if (t != null) {
			this.flush(); // 立即刷新，否则锁不会生效。
		}
		return t;
	}

	// 根据主键获取实体。如果没有相应的实体，抛出异常。
	public T load(PK id) {
		return (T) getHibernateTemplate().load(entityClass, id);
	}

	// 根据主键获取实体并加锁。如果没有相应的实体，抛出异常。
	public T loadWithLock(PK id, LockMode lock) {
		T t = (T) getHibernateTemplate().load(entityClass, id, lock);
		if (t != null) {
			this.flush(); // 立即刷新，否则锁不会生效。
		}
		return t;
	}

	// 获取全部实体。
	public List<T> loadAll() {
		return (List<T>) getHibernateTemplate().loadAll(entityClass);
	}

	// loadAllWithLock() ?

	// 更新实体
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	// 更新实体并加锁
	public void updateWithLock(T entity, LockMode lock) {
		getHibernateTemplate().update(entity, lock);
		this.flush(); // 立即刷新，否则锁不会生效。
	}

	// 存储实体到数据库
	public Serializable save(T entity) {
		return getHibernateTemplate().save(entity);
	}

	// saveWithLock()？

	// 增加或更新实体
	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	// 增加或更新集合中的全部实体
	public void saveOrUpdateAll(Collection<T> entities) {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}

	// 删除指定的实体
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	// 加锁并删除指定的实体
	public void deleteWithLock(T entity, LockMode lock) {
		getHibernateTemplate().delete(entity, lock);
		this.flush(); // 立即刷新，否则锁不会生效。
	}

	// 根据主键删除指定的实体
	public void deleteByKey(PK id) {
		this.delete(this.get(id));
	}

	// 根据主键加锁并删除指定的实体
	public void deleteByKeyWithLock(PK id, LockMode lock) {
		this.deleteWithLock(this.load(id), lock);
	}

	// 删除集合中的全部实体
	public void deleteAll(Collection<T> entities) {
		getHibernateTemplate().deleteAll(entities);
	}

	// -------------------- HSQL ----------------------------------------------

	// 使用HSQL语句直接增加、更新、删除实体
	public int bulkUpdate(String queryString) {
		return getHibernateTemplate().bulkUpdate(queryString);
	}

	// 使用带参数的HSQL语句增加、更新、删除实体
	public int bulkUpdate(String queryString, Object[] values) {
		return getHibernateTemplate().bulkUpdate(queryString, values);
	}

	// 使用HSQL语句检索数据
	public List find(String queryString) {
		return getHibernateTemplate().find(queryString);
	}

	// 使用带参数的HSQL语句检索数据
	public List find(String queryString, Object[] values) {
		return getHibernateTemplate().find(queryString, values);
	}

	public List<T> findByCondition(int max, String condition) {
		String queryString = "from " + this.entityClass.getName()
				+ " where 1=1 and " + condition;
		this.getHibernateTemplate().setMaxResults(max);
		return (List<T>) this.getHibernateTemplate().find(queryString);
	}

	public List<T> findByCondition(String condition) {
		String queryString = "from " + this.entityClass.getName()
				+ " where 1=1 and " + condition;
		return (List<T>) this.getHibernateTemplate().find(queryString);
	}

	public List<T> findByFK(int max, String fKName, Long fKId) {
		String queryString = "from " + this.entityClass.getName()
				+ " where 1=1 and " + fKName + "=" + fKId;
		this.getHibernateTemplate().setMaxResults(max);
		return (List<T>) this.getHibernateTemplate().find(queryString);
	}

	public List<T> findByFKOrderByTimeDesc(int max, String fKName, Long fKId) {
		String queryString = "from " + this.entityClass.getName()
				+ " where 1=1 and " + fKName + "=" + fKId
				+ " order by createTime desc";
		this.getHibernateTemplate().setMaxResults(max);
		return (List<T>) this.getHibernateTemplate().find(queryString);
	}

	public List<T> findByFK(String fKName, Long fKId) {
		String queryString = "from " + this.entityClass.getName()
				+ " where 1=1 and " + fKName + "=" + fKId;
		return (List<T>) find(queryString);
	}

	public List<T> findBySingleProperty(String propertyName,
			String propertyValue) {
		String queryString = "from " + this.entityClass.getName()
				+ " where 1=1 and " + propertyName + "=" + propertyValue;
		return (List<T>) find(queryString);
	}

	public List<T> findBySingleProperty(int max, String propertyName,
			String propertyValue) {
		String queryString = "from " + this.entityClass.getName()
				+ " where 1=1 and " + propertyName + "=" + "'" + propertyValue
				+ "'";
		this.getHibernateTemplate().setMaxResults(max);
		return (List<T>) this.getHibernateTemplate().find(queryString);
	}

	public List<T> findByFKOrderByTimeDesc(String fKName, Long fKId) {
		String queryString = "from " + this.entityClass.getName()
				+ " where 1=1 and " + fKName + "=" + fKId
				+ " order by createTime desc";
		return (List<T>) find(queryString);
	}

	// 使用带命名的参数的HSQL语句检索数据
	public T findByNamedParam(String queryString, String paramName, Object value) {
		return (T) getHibernateTemplate().findByNamedParam(queryString,
				paramName, value).get(0);
	}

	// 使用带命名的参数的HSQL语句检索数据
	public List findByNamedParam(String queryString, String[] paramNames,
			Object[] values) {
		return getHibernateTemplate().findByNamedParam(queryString, paramNames,
				values);
	}

	// 使用命名的HSQL语句检索数据
	public List findByNamedQuery(String queryName) {
		return getHibernateTemplate().findByNamedQuery(queryName);
	}

	// 使用带参数的命名HSQL语句检索数据
	public List findByNamedQuery(String queryName, Object[] values) {
		return getHibernateTemplate().findByNamedQuery(queryName, values);
	}

	// 使用带命名参数的命名HSQL语句检索数据
	public List findByNamedQueryAndNamedParam(String queryName,
			String[] paramNames, Object[] values) {
		return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName,
				paramNames, values);
	}

	// 使用HSQL语句检索数据，返回 Iterator
	public Iterator iterate(String queryString) {
		return getHibernateTemplate().iterate(queryString);
	}

	// 使用带参数HSQL语句检索数据，返回 Iterator
	public Iterator iterate(String queryString, Object[] values) {
		return getHibernateTemplate().iterate(queryString, values);
	}

	// 关闭检索返回的 Iterator
	public void closeIterator(Iterator it) {
		getHibernateTemplate().closeIterator(it);
	}

	// -------------------------------- Criteria ------------------------------

	// 创建与会话无关的检索标准
	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(this.entityClass);
	}

	// 创建与会话绑定的检索标准
	public Criteria createCriteria() {
		return this.createDetachedCriteria().getExecutableCriteria(
				this.getSession());
	}

	// 检索满足标准的数据

	/*
	 * public List findByCriteria(DetachedCriteria criteria) { // return
	 * getHibernateTemplate().findByCriteria(criteria);
	 * 
	 * return (List) getHibernateTemplate().execute(new HibernateCallback() {
	 * public Object doInHibernate(Session session) throws HibernateException {
	 * Criteria criteria = detachedCriteria .getExecutableCriteria(session);
	 * return criteria.list(); } }, true);
	 * 
	 * }
	 */
	// 检索满足标准的数据，返回指定范围的记录
	public List findByCriteria(DetachedCriteria criteria, int firstResult,
			int maxResults) {
		return getHibernateTemplate().findByCriteria(criteria, firstResult,
				maxResults);
	}

	// 使用指定的实体及属性检索（满足除主键外属性＝实体值）数据
	public List<T> findEqualByEntity(T entity, String[] propertyNames) {
		Criteria criteria = this.createCriteria();
		Example exam = Example.create(entity);
		exam.excludeZeroes();
		String[] defPropertys = getSessionFactory().getClassMetadata(
				entityClass).getPropertyNames();
		for (String defProperty : defPropertys) {
			int ii = 0;
			for (ii = 0; ii < propertyNames.length; ++ii) {
				if (defProperty.equals(propertyNames[ii])) {
					criteria.addOrder(org.hibernate.criterion.Order
							.asc(defProperty));
					break;
				}
			}
			if (ii == propertyNames.length) {
				exam.excludeProperty(defProperty);
			}
		}
		criteria.add(exam);
		return (List<T>) criteria.list();
	}

	// 使用指定的实体及属性检索（满足属性 like 串实体值）数据
	/*
	 * public List<T> findLikeByEntity(T entity, String[] propertyNames) {
	 * Criteria criteria = this.createCriteria(); for (String property :
	 * propertyNames) { try { Object value = PropertyUtils.getProperty(entity,
	 * property); if (value instanceof String) {
	 * criteria.add(Restrictions.like(property, (String) value,
	 * MatchMode.ANYWHERE)); criteria.addOrder(Order.asc(property)); } else {
	 * criteria.add(Restrictions.eq(property, value));
	 * criteria.addOrder(Order.asc(property)); } } catch (Exception ex) { //
	 * 忽略无效的检索参考数据。 } } return (List<T>) criteria.list(); }
	 */

	// 使用指定的检索标准获取满足标准的记录数
	public Integer getRowCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List list = this.findByCriteria(criteria, 0, 1);
		return (Integer) list.get(0);
	}

	// 使用指定的检索标准检索数据，返回指定统计值(max,min,avg,sum)
	public Object getStatValue(DetachedCriteria criteria, String propertyName,
			String StatName) {
		if (StatName.toLowerCase().equals("max"))
			criteria.setProjection(Projections.max(propertyName));
		else if (StatName.toLowerCase().equals("min"))
			criteria.setProjection(Projections.min(propertyName));
		else if (StatName.toLowerCase().equals("avg"))
			criteria.setProjection(Projections.avg(propertyName));
		else if (StatName.toLowerCase().equals("sum"))
			criteria.setProjection(Projections.sum(propertyName));
		else
			return null;
		List list = this.findByCriteria(criteria, 0, 1);
		return list.get(0);
	}

	// -------------------------------- Others --------------------------------

	// 加锁指定的实体
	public void lock(T entity, LockMode lock) {
		getHibernateTemplate().lock(entity, lock);
	}

	// 强制初始化指定的实体
	public void initialize(Object proxy) {
		getHibernateTemplate().initialize(proxy);
	}

	// 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）
	public void flush() {
		getHibernateTemplate().flush();
	}

	public void clear() {
		this.getSession().clear();
	}

	/**
	 * 分页查询
	 * 
	 * @param sql
	 *            String
	 * @param firstRow
	 *            int
	 * @param maxRow
	 *            int
	 * @return List
	 */
	public List findPage(final String sql, final int firstRow, final int maxRow) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException,
					HibernateException {
				Query q = session.createQuery(sql);
				q.setFirstResult(firstRow);
				q.setMaxResults(maxRow);
				return q.list();
			}
		});
	}

	public Page findPageByCriteria(final DetachedCriteria detachedCriteria) {
		return findPageByCriteria(detachedCriteria, 0, Page.DEFAULT_PAGE_SIZE);
	}

	public Page findPageByCriteria(final DetachedCriteria detachedCriteria,
			final int startIndex) {
		return findPageByCriteria(detachedCriteria, startIndex,
				Page.DEFAULT_PAGE_SIZE);
	}

	/**
	 * 
	 * @param detachedCriteria
	 * @param pageSize
	 *            每页显示行数
	 * @param startIndex
	 *            记录起始
	 * @return page对象
	 */
	public Page findPageByCriteria(final DetachedCriteria detachedCriteria,
			final int startIndex, final int pageSize) {
		return (Page) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Criteria criteria = detachedCriteria
						.getExecutableCriteria(session);
				int totalCount = ((Integer) criteria.setProjection(
						Projections.rowCount()).uniqueResult()).intValue();
				criteria.setProjection(null);

				int firstResult = (startIndex - 1) * pageSize < 0 ? 0
						: (startIndex - 1) * pageSize;
				int maxResult = pageSize;

				List items = criteria.setFirstResult(firstResult)
						.setMaxResults(maxResult).list();

				Page ps = new Page(items, totalCount, pageSize, startIndex);
				return ps;
			}
		});
	}

	public Page queryForpage(String hql) {
		return queryForpage(hql, null, null);
	}

	public Page queryForpage(String hql, LinkedHashMap<String, String> orderby) {
		return queryForpage(hql, null, orderby);
	}

	public Page queryForpage(String hql, Object[] params,
			LinkedHashMap<String, String> orderby) {
		return queryForpage(hql, params, PageContext.getOffset(),
				PageContext.getPagesize(), orderby);
	}

	public Page queryForpage(String hql, int offset, int pagesize,
			LinkedHashMap<String, String> orderby) {
		return queryForpage(hql, null, offset, pagesize, orderby);
	}

	public Page queryForpage(String hql, Object values, int offset,
			int pagesize, LinkedHashMap<String, String> orderby) {
		return queryForpage(hql, new Object[] { values }, offset, pagesize,
				orderby);
	}

	public Page queryForpage(String select, Object[] values, int pagestart,
			int pagesize, LinkedHashMap<String, String> orderby) {
		Page page = new Page(
				((PageContext.getOffset() - PageContext.getOffset()
						% PageContext.getPagesize()) / PageContext
						.getPagesize()) + 1,
				PageContext.getPagesize());
		String countHql = getCountQuery(select);
		Long totalCount = (Long) queryForObject(countHql, values);
		page.setTotalCount(totalCount.intValue());
		page.setItems(queryForList(select, values, pagestart, pagesize, orderby));
		return page;
	}

	protected List queryForList(final String select, final Object[] values,
			final int pagestart, final int pagesize,
			final LinkedHashMap<String, String> orderby) {
		HibernateCallback selectCallback = new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(select
						+ buildOrderby(orderby));
				if (values != null) {
					for (int i = 0; i < values.length; i++)
						query.setParameter(i, values[i]);
				}
				return query.setFirstResult(pagestart).setMaxResults(pagesize)
						.list();
			}
		};
		return (List) getHibernateTemplate().executeFind(selectCallback);
	}

	public Page queryForPage(final String selectCount, final String select,
			final Object[] values, int pagestart, final int pagesize,
			final LinkedHashMap<String, String> orderby) {
		Page page = new Page();
		Long totalCount = (Long) queryForObject(selectCount, values);
		page.setTotal(totalCount.intValue());
		page.setItems(queryForList(select, values, pagestart, pagesize, orderby));
		return page;
	}

	public Object queryForObject(final String select, final Object[] values) {

		HibernateCallback selectCallback = new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(select);
				if (values != null) {
					for (int i = 0; i < values.length; i++)
						query.setParameter(i, values[i]);
				}
				return query.uniqueResult();
			}
		};
		return getHibernateTemplate().execute(selectCallback);
	}

	public List<T> queryForList(final String select) {

		return getSession().createQuery(select).list();
	}

	/**
	 * ��װorder by���
	 * 
	 * @param orderby
	 * @return
	 */
	protected static String buildOrderby(LinkedHashMap<String, String> orderby) {
		StringBuffer orderbyql = new StringBuffer("");
		if (orderby != null && orderby.size() > 0) {
			orderbyql.append(" order by ");
			for (String key : orderby.keySet()) {
				orderbyql.append(key).append(" ").append(orderby.get(key))
						.append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length() - 1);
		}
		return orderbyql.toString();
	}

	protected String getCountQuery(String hql) {
		int index = hql.indexOf("from");
		if (index != -1) {
			return "select count(*) " + hql.substring(index);
		}
		throw new RuntimeErrorException(null, "��Ч��SQL���");
	}

	/**
	 * 根据查询sql语句，获取sql总记录
	 * 
	 * @param sql
	 *            查询语句
	 * 
	 * @return 查询语句的总记录数
	 */
	protected String getCountSQL(String sql) {
		if (null != sql && !"".equals(sql) & -1 != sql.indexOf("select")) {
			return "select count(*) from (" + sql + ")";
		}
		throw new RuntimeErrorException(null, "SQL Format error ");
	}

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
	 * @param orderby
	 * @return
	 */
	public Page sqlQueryForPage(String sql, Object[] params, int offSet,
			int pagesize, LinkedHashMap<String, String> orderby) {
		// 查询总数
		SQLQuery countQuery = getSession().createSQLQuery(getCountQuery(sql));
		for (int i = 0; i < params.length; i++) {
			countQuery.setParameter(i, params[i]);
		}
		int totalCount = Integer.valueOf(countQuery.uniqueResult().toString());
		Page page = new Page((offSet - offSet % pagesize) / pagesize + 1,
				pagesize);
		page.setTotalCount(totalCount);
		page.setItems(sqlQueryForList(sql, params, offSet, pagesize, orderby));

		return page;
	}

	// //通过sql获取对象
	// public List<T> sqlQueryForList(String sql,Object[] params,RowMapper<T>
	// rowMapper){
	// return sqlQueryForList(sql, params, rowMapper, null, null);
	// }

	public List<Map<String, Object>> sqlQueryForList(String sql,
			Object[] params, LinkedHashMap<String, String> orderby) {
		return sqlQueryForList(sql, params, null, null, orderby);
	}

	public List<Map<String, Object>> sqlQueryForList(String sql,
			Object[] params, Integer offSet, Integer pagesize,
			LinkedHashMap<String, String> orderby) {
		String querySql = null;
		if (offSet != null && pagesize != null) {
			querySql = getSqlPagingQuery(sql + buildOrderby(orderby));
		} else {
			querySql = sql;
		}

		SQLQuery listQuery = getSession().createSQLQuery(querySql);
		listQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		int i = 0;
		if (params != null) {
			for (; i < params.length; i++) {
				listQuery.setParameter(i, params[i]);
			}
		}
		if (offSet != null && pagesize != null) {
			listQuery.setParameter(i++, offSet + pagesize);
			listQuery.setParameter(i++, offSet);
		}

		return listQuery.list();
	}

	// 通过sql获取对象
	/*
	 * public List<T> sqlQueryForList(String sql,Object[] params,RowMapper<T>
	 * rowMapper,Integer offSet,Integer pagesize){
	 * 
	 * List<Map<String,Object>> list = sqlQueryForList(sql, params, offSet,
	 * pagesize);
	 * 
	 * //结果数据转化成Domain Object List<T> items = new ArrayList<T>();
	 * for(Map<String,Object> item:list){ items.add(rowMapper.mapRow(item)); }
	 * 
	 * return items; }
	 */

	protected String getSqlPagingQuery(String sql) {
		StringBuffer buffer = new StringBuffer(
				"select * from (select t1.*,rownum rownum_ from (").append(sql)
				.append(")t1 where rownum <= ?")
				.append(")t2 where t2.rownum_ >?");

		return buffer.toString();
	}

	/**
	 * 通过sql查询分页列表
	 * 
	 * @param listSql
	 * @param countSql
	 * @param rowMapper
	 * @param orderby
	 * @return
	 */
	public Page sqlQueryForPage(String sql, Object[] params,
			LinkedHashMap<String, String> orderby) {
		return sqlQueryForPage(sql, params, PageContext.getOffset(),
				PageContext.getPagesize(), orderby);
	}

	/**
	 * 通过sql查询分页列表
	 * 
	 * @param sql
	 *            查询语句
	 * @param params
	 *            参数数组
	 * @param orderby
	 *            排序字符串
	 * @param flag
	 *            true:直接查询sql的总记录；false:截取sql后，再查询总记录
	 * 
	 * @return 分页列表
	 */
	public Page sqlQueryForPage(String sql, Object[] params,
			LinkedHashMap<String, String> orderby, boolean flag) {
		int offSet = PageContext.getOffset();
		int pagesize = PageContext.getPagesize();
		// 查询总数
		SQLQuery countQuery = getSession().createSQLQuery(
				flag ? getCountSQL(sql) : getCountQuery(sql));
		for (int i = 0; i < params.length; i++) {
			countQuery.setParameter(i, params[i]);
		}
		Object obj = countQuery.uniqueResult();
		if (null == obj || Integer.valueOf(obj.toString()) < 1)
			return null;

		int totalCount = Integer.valueOf(obj.toString());
		Page page = new Page((offSet - offSet % pagesize) / pagesize + 1,
				pagesize);
		page.setTotalCount(totalCount);
		page.setItems(sqlQueryForList(sql, params, offSet, pagesize, orderby));

		return page;
	}

	protected String queryCondition2HQL(QueryCondition conn) {
		Set<Expression> conditions = conn.getConditions();
		List<Order> orders = conn.getOrders();

		StringBuffer buf = new StringBuffer();

		buf.append(" from ");

		buf.append(this.entityClass.getName() + " entity");

		buf.append(" where ");

		// 根据条件表达式，拼接查询条件

		for (Expression exp : conditions) {

			String name = exp.getFiledName();
			String op = exp.getOp();
			Object value = exp.getValue();

			String strVal = expressionValue2String(value);

			buf.append("entity." + name);

			if (op == null || Expression.OP_EQ.equals(op) || "".equals(op)) {
				buf.append(" " + Expression.OP_EQ);
				buf.append(" '" + strVal + "'");

			} else if (Expression.OP_GE.equals(op)
					|| Expression.OP_GT.equals(op)
					|| Expression.OP_LT.equals(op)
					|| Expression.OP_LE.equals(op)) {
				buf.append(" " + op);
				buf.append(" " + strVal);
			} else if (Expression.OP_LLIKE.equals(op)) {
				buf.append(" like ");
				buf.append("'" + strVal + "%'");
			} else if (Expression.OP_RLIKE.equals(op)) {
				buf.append(" like ");
				buf.append("'%" + strVal + "'");
			} else if (Expression.OP_LIKE.equals(op)) {
				buf.append(" like ");
				buf.append("'%" + strVal + "%'");
			} else if (Expression.OP_IN.equals(op)
					|| Expression.OP_NOT_IN.equals(op)) {
				buf.append(" " + op + "(");
				String[] values = strVal.split(Expression.SEPSRATOR);

				for (int i = 0; i < values.length; i++) {
					String inValue = values[i];
					buf.append("'" + inValue + "'");
					if (i != values.length - 1) {
						buf.append(",");
					}
				}

				buf.append(")");
			} else if (Expression.OP_IN_QUERY.equals(op)) {
				buf.append(" in (");
				buf.append(strVal + ")");
			}

			buf.append(" and ");

		}

		// 当条件结束时，截取多余连接字符
		String endStr = buf.substring(buf.length() - 5, buf.length()).trim();
		if ("and".equals(endStr))
			buf.delete(buf.length() - 5, buf.length());
		endStr = buf.substring(buf.length() - 7, buf.length()).trim();
		if ("where".equals(endStr))
			buf.delete(buf.length() - 7, buf.length());

		// 根据排序条件，拼接排序的HQL

		buf.append(" order by ");

		for (int i = 0; i < orders.size(); i++) {
			Order order = orders.get(i);
			buf.append(order.getFieldName() + " ");
			buf.append(order.getDir());

			if (i != orders.size() - 1)
				buf.append(",");
		}

		// 当无排序条件时，截取多余字符
		endStr = buf.substring(buf.length() - 10, buf.length()).trim();
		if ("order by".equals(endStr)) {
			buf.delete(buf.length() - 10, buf.length());
		}

		return buf.toString();
	}

	/**
	 * 把查询表达式的值转换成字符串，以便拼接HQL语句
	 * 
	 * @param value
	 * @return
	 */
	private String expressionValue2String(Object value) {
		String strValue = "";

		if (value instanceof String) {
			strValue = value.toString();
		} else if (value instanceof Number) {
			strValue = value.toString();
		} else if (value instanceof Date) {
			Date date = (Date) value;
			SimpleDateFormat format = new SimpleDateFormat(
					"YYYY-MM-dd HH:mm:ss");
			strValue = format.format(date);
		}

		return strValue;
	}

	public Page queryForPageByConditon(QueryCondition conn) {
		return queryForpage(queryCondition2HQL(conn));
	}
}