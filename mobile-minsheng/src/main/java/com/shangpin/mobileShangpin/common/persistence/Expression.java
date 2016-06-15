package com.shangpin.mobileShangpin.common.persistence;

public class Expression {
	public static  String OP_EQ = "="; //等于
	public static  String OP_GT = ">"; //大于
	public static  String OP_LT = "<"; //小于
	public static  String OP_GE = ">="; //大于等于
	public static  String OP_LE = "<="; //小于等于
	public static  String OP_NO_EQ = "!="; //不等于
	public static  String OP_LLIKE = "lLike"; //左边匹配
	public static  String OP_RLIKE = "rLike"; //右边匹配
	public static  String OP_LIKE = "like"; //包含,任意匹配
	public static  String OP_IN = "in"; //In 查询
	public static  String OP_NOT_IN = "notIn"; //not in 查询
	public static  String OP_IN_QUERY = "inQuery";//子查询  select * from table1 where id in (select table1_id from table2)
	
	public static String SEPSRATOR =","; //value的分隔符
	/**
	 * 对应的属性名
	 */
	private String filedName;
	/**
	 * 表达式符号
	 **/
	private String op;
	/**
	 * 表达式对应的值
	 */
	private Object value;
	
	/**
	 * 构造函数
	 * @param filedName
	 * @param op
	 * @param value
	 */
	public Expression(String filedName,String op,Object value){
		this.filedName = filedName;
		this.op = op;
		this.value = value;
	}
	/**
	 * 构造函数 默认为相等
	 * @param filedName
	 * @param value
	 */
	public Expression(String filedName,Object value){
		this.filedName = filedName;
		this.op = OP_EQ;
		this.value = value;
	}
	
	public String getFiledName() {
		return filedName;
	}

	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
}