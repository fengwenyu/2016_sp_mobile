package com.shangpin.biz.bo.base;

import javax.xml.bind.annotation.XmlAttribute;

public class SuggestLabel {
	
	private String name;
	
	private String value;
	
	private String type = "8";//默认搜索列表类型

	@XmlAttribute(name = "name")
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
	
	@XmlAttribute(name = "value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SuggestLabel [name=" + name + ", value=" + value + ", type=" + type + "]";
	}

}
