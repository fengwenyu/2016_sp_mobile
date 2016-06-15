package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class CustomBrandItem implements Serializable {

	private static final long serialVersionUID = -9107155758245679271L;

	private List<CustomBrand> favList;

	private List<CustomBrand> list;

	public List<CustomBrand> getFavList() {
		return favList;
	}

	public void setFavList(List<CustomBrand> favList) {
		this.favList = favList;
	}

	public List<CustomBrand> getList() {
		return list;
	}

	public void setList(List<CustomBrand> list) {
		this.list = list;
	}

}
