package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class NewGoods implements Serializable {

	private static final long serialVersionUID = 390752297342652385L;

	private String time;

	private List<Brand> brandList;//新到货的品牌

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<Brand> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<Brand> brandList) {
		this.brandList = brandList;
	}
}
