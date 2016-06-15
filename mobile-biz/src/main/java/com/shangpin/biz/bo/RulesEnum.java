package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * 通用规则枚举
 * 
 * @author huangxiaoliang
 *
 */
public enum RulesEnum implements Serializable{
	/** 主站的通用规则  **/
	MASTER_TYPE_ONE("1"),
	MASTER_TYPE_TWO("2"),
	MASTER_TYPE_THREE("3"),
	MASTER_TYPE_FOUR("4"),
	MASTER_TYPE_FIVE("5"),
	MASTER_TYPE_SIX("6"),
	MASTER_TYPE_SEVEN("7"),
	MASTER_TYPE_EIGHT("8"),
	
	/** 客户端的通用规则  **/
	CLIENT_TYPE_ONE("1"),
	CLIENT_TYPE_TWO("2"),
	CLIENT_TYPE_THREE("3"),
	CLIENT_TYPE_FOUR("4"),
	CLIENT_TYPE_FIVE("5"),
	CLIENT_TYPE_SIX("6"),
	CLIENT_TYPE_SEVEN("7"),
	CLIENT_TYPE_EIGHT("8");
	
	private String info;
	
	RulesEnum(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}
}
