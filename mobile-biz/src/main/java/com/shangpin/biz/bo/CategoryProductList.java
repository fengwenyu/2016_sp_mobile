package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class CategoryProductList implements Serializable{

	private static final long serialVersionUID = 1L;
	private String sid;
	private String status;
	private String discription;
	private String qtime;
	private String total;
	private String start;
	private String end;
	private List<Fact> facets;
	private List<Product> docs;
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getQtime() {
		return qtime;
	}
	public void setQtime(String qtime) {
		this.qtime = qtime;
	}
	
	public List<Fact> getFacets() {
		return facets;
	}
	public void setFacets(List<Fact> facets) {
		this.facets = facets;
	}
	public List<Product> getDocs() {
		return docs;
	}
	public void setDocs(List<Product> docs) {
		this.docs = docs;
	}
	
}
