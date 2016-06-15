package com.shangpin.wireless.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于辅助生成HQL与参数列表的工具类
 * 
 * @Author  zhouyu
 * @CreatDate  2012-07-12
 */
public class HqlHelper {

	private String fromClause = ""; // From子句
	private String whereClause = ""; // Where子句
	private String orderByClause = ""; // OrderBy子句
	private int limitStart; // Limit子句
	private int limitCount; // Limit子句

	private List<Object> parameters = new ArrayList<Object>(); // 参数列表

	/**
	 * 生成From子句
	 * 
	 * @param clazz
	 */
	public HqlHelper(Class clazz) {
		fromClause = "FROM " + clazz.getSimpleName() + " o";
	}

	/**
	 * 生成From子句
	 * 
	 * @param clazz
	 * @param alias
	 *            别名
	 */
	public HqlHelper(Class clazz, String alias) {
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}

	/**
	 * 拼接Where子句
	 * 
	 * @param condition
	 * @param params
	 */
	public HqlHelper addWhereCondition(String condition, Object... params) {
		if (whereClause.length() == 0) {
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " AND " + condition;
		}

		if (params != null) {
			for (Object param : params) {
				parameters.add(param);
			}
		}

		return this;
	}

	/**
	 * 如果第1个参数为true，则拼接Where子句
	 * 
	 * @param append
	 * @param condition
	 * @param params
	 * @Return
	 */
	public HqlHelper addWhereCondition(boolean append, String condition, Object... params) {
		if (append) {
			addWhereCondition(condition, params);
		}
		return this;
	}

	/**
	 * 拼接OrderBy子句
	 * 
	 * @param propertyName
	 * @param isAsc
	 */
	public HqlHelper addOrderByProperty(String propertyName, boolean isAsc) {
		if (orderByClause.length() == 0) {
			orderByClause = " ORDER BY " + propertyName + (isAsc ? " ASC" : " DESC");
		} else {
			orderByClause += ", " + propertyName + (isAsc ? " ASC" : " DESC");
		}
		return this;
	}

	/**
	 * 如果第1个参数为true，则拼接OrderBy子句
	 * 
	 * @param append
	 * @param propertyName
	 * @param isAsc
	 * @Return
	 */
	public HqlHelper addOrderByProperty(boolean append, String propertyName, boolean isAsc) {
		if (append) {
			addOrderByProperty(propertyName, isAsc);
		}
		return this;
	}

	/**
	 * 拼接Limit子句
	 * 
	 * @param condition
	 * @param params
	 */
	public HqlHelper addLimit(int startindex, int count) {
		limitStart = startindex;
		limitCount = count;

		return this;
	}

	/**
	 * 获取生成的查询数据列表的HQL
	 * 
	 * @Return
	 */
	public String getQueryListHql() {
		return fromClause + whereClause + orderByClause;
	}

	/**
	 * 获取生成的查询总数量的HQL（没有OrderBy子句）
	 * 
	 * @Return
	 */
	public String getQueryCountHql() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	/**
	 * 获取参数列表
	 * 
	 * @Return
	 */
	public List<Object> getParameters() {
		return parameters;
	}

	public int getLimitStart() {
		return limitStart;
	}

	public int getLimitCount() {
		return limitCount;
	}
}
