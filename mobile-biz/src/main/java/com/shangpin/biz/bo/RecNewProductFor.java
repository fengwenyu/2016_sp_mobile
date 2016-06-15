package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class RecNewProductFor implements Serializable {

	private static final long serialVersionUID = 6996507084210744011L;
	
	private String systime;
	
	private String recommendNum;
	
	private List<Product> list;
	

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}

	private String titles;
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSystime() {
		return systime;
	}

	public void setSystime(String systime) {
		this.systime = systime;
	}

	public String getRecommendNum() {
		return recommendNum;
	}

	public void setRecommendNum(String recommendNum) {
		this.recommendNum = recommendNum;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

}
