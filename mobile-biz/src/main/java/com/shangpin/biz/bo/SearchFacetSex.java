package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class SearchFacetSex implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Sex> sexs;

	public List<Sex> getSexs() {
		return sexs;
	}

	public void setSexs(List<Sex> sexs) {
		this.sexs = sexs;
	}
	

}
