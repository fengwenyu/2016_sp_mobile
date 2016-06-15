package com.shangpin.base.vo;

import java.io.Serializable;

public class Promotion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String pic;
	private String link;
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return this.pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getLink() {
		return this.link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	
}
