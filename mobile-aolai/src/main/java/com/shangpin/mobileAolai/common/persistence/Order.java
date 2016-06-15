package com.shangpin.mobileAolai.common.persistence;

/**
 * 排序 
 */
public class Order {
	
	public static String DIR_DESC = "desc";//倒序
	public static String DIR_ASC = "asc";//正序
	/**属性*/
	private String fieldName;
	/**排序方向 asc 或 desc*/
	private String dir;
	
	public Order(String fieldName,String dir){
		this.fieldName = fieldName;
		this.dir = dir;
	}
	
	public Order(String fieldName){
		this.fieldName = fieldName;
		this.dir = DIR_ASC;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
	
}
