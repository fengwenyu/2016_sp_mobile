package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class Gift implements Serializable {

	private static final long serialVersionUID = -6497759123811588166L;

	private String url;

	private List<GiftCardKeytContent> list;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<GiftCardKeytContent> getList() {
		return list;
	}

	public void setList(List<GiftCardKeytContent> list) {
		this.list = list;
	}

}
