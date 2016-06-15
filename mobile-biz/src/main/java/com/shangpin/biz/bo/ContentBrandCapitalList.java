package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class ContentBrandCapitalList implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<BrandCapitalList> list;

	/**
	 * @return the list
	 */
	public List<BrandCapitalList> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<BrandCapitalList> list) {
		this.list = list;
	}
	

}
