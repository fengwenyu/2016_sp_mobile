package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/** 
* @ClassName: SearchProduct 
* @Description:搜索实体类 
* @author qinyingchun
* @date 2014年10月28日
* @version 1.0 
*/
public class SearchProduct implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String sid;//搜索ID
	private String status;//搜索请求状态
	private String discription;//描述
	private String keyword;//关键字
	private Integer total;//结果总数
	private String start;//分页起始/开始条数
	private String end;//分页末页
	private String qtime;//分页耗时
	private SearchFacet searchFacet;
	private List<Product> products;
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
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
	public String getQtime() {
		return qtime;
	}
	public void setQtime(String qtime) {
		this.qtime = qtime;
	}
	public SearchFacet getSearchFacet() {
		return searchFacet;
	}
	public void setSearchFacet(SearchFacet searchFacet) {
		this.searchFacet = searchFacet;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	

}
