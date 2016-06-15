package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class CommentItemAttr implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9055850977813497128L;
	private String id;
	private String defaultValue;
	private List<String> value;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getValue() {
		return value;
	}
	public void setValue(List<String> value) {
		this.value = value;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
