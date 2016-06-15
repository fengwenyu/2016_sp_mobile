package com.shangpin.biz.bo.base;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
public class Suggestion implements Serializable {

	private static final long serialVersionUID = 451443944152507079L;

	private String name;

	private String count;
	
	private List<SuggestLabel> labels;
	
	
	@XmlElementWrapper(name = "labels")
	@XmlElement(name = "label")
	public List<SuggestLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<SuggestLabel> labels) {
		this.labels = labels;
	}	
	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "count")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
