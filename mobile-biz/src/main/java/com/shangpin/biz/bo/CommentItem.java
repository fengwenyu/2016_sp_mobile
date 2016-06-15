package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class CommentItem implements Serializable{

	private static final long serialVersionUID = -1369591548338460598L;
	private String id;
	private String name;
	private String type;
	private String defaultValue;
	private List<Attribute> tags;
	private List<CommentItemAttr> commentItemAttr;
	private String unit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public List<Attribute> getTags() {
		return tags;
	}
	public void setTags(List<Attribute> tags) {
		this.tags = tags;
	}
	public List<CommentItemAttr> getCommentItemAttr() {
		return commentItemAttr;
	}
	public void setCommentItemAttr(List<CommentItemAttr> commentItemAttr) {
		this.commentItemAttr = commentItemAttr;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
