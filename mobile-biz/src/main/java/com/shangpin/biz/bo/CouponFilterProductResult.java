package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class CouponFilterProductResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5967538648141941394L;
	private String QTime;
	private String discription;
	private List<Product> docs;
	private String end;
	private String sid;
	private String start;
	private String status;
	private String total;
	public String getQTime() {
		return QTime;
	}
	public void setQTime(String qTime) {
		QTime = qTime;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public List<Product> getDocs() {
		return docs;
	}
	public void setDocs(List<Product> docs) {
		this.docs = docs;
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
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
}
