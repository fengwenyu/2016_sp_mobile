package com.shangpin.mobileShangpin.common.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 查询条件，包括条件表达式和排序规则
 */
public class QueryCondition {
	/** 查询条件集合 */
	private Set<Expression> conditions = new HashSet<Expression>();
	/**
	 * 排序规则
	 */
	private List<Order> orders = new ArrayList<Order>();

	public Set<Expression> getConditions() {
		return conditions;
	}

	public void setConditions(Set<Expression> conditions) {
		this.conditions = conditions;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	/**
	 * 添加查询表达式
	 * 
	 * @param exp
	 * @return
	 */
	public QueryCondition putCondition(Expression exp) {
		conditions.add(exp);
		return this;
	}

	/**
	 * 添加查询条件
	 * 
	 * @param filedName
	 * @param exp
	 * @param value
	 * @return
	 */
	public QueryCondition putCondition(String filedName, String exp,
			Object value) {
		conditions.add(new Expression(filedName, exp, value));
		return this;
	}

	/**
	 * 添加查询条件
	 * 
	 * @param filedName
	 * @param value
	 * @return
	 */
	public QueryCondition putCondition(String filedName, Object value) {
		conditions.add(new Expression(filedName, value));
		return this;
	}

	/**
	 * 清理所有查询条件
	 * 
	 * @return
	 */
	public QueryCondition clearCondition() {
		conditions = new HashSet<Expression>();
		return this;
	}

	/**
	 * 添加排序条件
	 * 
	 * @param order
	 * @return
	 */
	public QueryCondition addOrder(Order order) {
		orders.add(order);
		return this;
	}

	/**
	 * 添加排序条件
	 * 
	 * @param fieldName
	 * @param dir
	 * @return
	 */
	public QueryCondition addOrder(String fieldName, String dir) {
		orders.add(new Order(fieldName, dir));
		return this;
	}

	/**
	 * 添加排序条件
	 * 
	 * @param fieldName
	 * @return
	 */
	public QueryCondition addOrder(String fieldName) {
		orders.add(new Order(fieldName));
		return this;
	}

	/**
	 * 清除排序条件
	 * 
	 * @return
	 */
	public QueryCondition clearOrder() {
		orders = new ArrayList<Order>();
		return this;
	}

	/**
	 * 清楚查询条件和排序条件
	 * 
	 * @return
	 */
	public QueryCondition clear() {
		conditions = new HashSet<Expression>();
		orders = new ArrayList<Order>();
		return this;
	}

}