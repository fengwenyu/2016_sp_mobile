package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class ProductGroupForTopic implements Serializable{
	private static final long serialVersionUID = 1L;
	private String groupname;
	private String  grouporder;
	private String  grouplogo;
	private String  groupurl;
	private List<ProductsBizTopic> products;
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getGrouporder() {
		return grouporder;
	}
	public void setGrouporder(String grouporder) {
		this.grouporder = grouporder;
	}
	public String getGrouplogo() {
		return grouplogo;
	}
	public void setGrouplogo(String grouplogo) {
		this.grouplogo = grouplogo;
	}
	public String getGroupurl() {
		return groupurl;
	}
	public void setGroupurl(String groupurl) {
		this.groupurl = groupurl;
	}
	public List<ProductsBizTopic> getProducts() {
		return products;
	}
	public void setProducts(List<ProductsBizTopic> products) {
		this.products = products;
	}
	
	
}
