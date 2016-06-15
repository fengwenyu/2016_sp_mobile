package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class GiftCardProductList implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;
	private List<GiftCardProductListItem> list;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<GiftCardProductListItem> getList() {
		return list;
	}

	public void setList(List<GiftCardProductListItem> list) {
		this.list = list;
	}
}
