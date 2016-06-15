package com.shangpin.biz.bo.base;


import java.io.Serializable;
import java.util.List;


/**
 * 搜索接口返回的实体结构
 * @author qinyingchun
 *
 */
public class SearchContentList<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sid;
	private int status;
	private String discription;
	private long QTime;
	private long total;
	private List<T> docs;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public long getQTime() {
		return QTime;
	}
	public void setQTime(long qTime) {
		QTime = qTime;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getDocs() {
		return docs;
	}
	public void setDocs(List<T> docs) {
		this.docs = docs;
	}
	
}
