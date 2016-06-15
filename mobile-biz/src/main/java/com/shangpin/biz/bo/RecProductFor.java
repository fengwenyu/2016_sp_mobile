package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class RecProductFor implements Serializable {

	private static final long serialVersionUID = 6996507084210744011L;
	
	private String systime;
	
	private String recommendNum;
	
	private List<Product> list;
	private String titles;
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

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

}
