package com.shangpin.wireless.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.shangpin.wireless.dao.UserBehavioralStatisticsDao;

@Repository(UserBehavioralStatisticsDao.DAO_NAME)
public class UserBehavioralStatisticsDaoImpl extends HibernateDaoSupport implements UserBehavioralStatisticsDao {
	@Resource(name = "sessionFactoryManage")
	public void setSessionFactoryManage(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
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
}
