package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索返回数据 json结构
 * @author fengwenyu
 *
 */
public class SearchProductJson<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String discription;//查询结果描述
	private int start;//开始条数
	private int end;//结束条数
	private int QTime;//查询耗时
	private String sid;//搜索ID
	private int status;//搜索请求状态
	private int total;//结果总数
	private List<T> docs;//查询结果集合
	
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	public int getQTime() {
		return QTime;
	}
	public void setQTime(int qTime) {
		QTime = qTime;
	}
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getDocs() {
		return docs;
	}
	public void setDocs(List<T> docs) {
		this.docs = docs;
	}
	
	
	
	
}
