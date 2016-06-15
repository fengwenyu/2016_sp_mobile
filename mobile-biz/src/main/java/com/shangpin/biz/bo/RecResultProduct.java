package com.shangpin.biz.bo;

import java.io.Serializable;

public class RecResultProduct  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String recid;//商品来源 1国内 2海外

	private String time;
	
	private String num;
	
	private String[] results;

	public String getRecid() {
		return recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String[] getResults() {
		return results;
	}

	public void setResults(String[] results) {
		this.results = results;
	}


	
	
}
