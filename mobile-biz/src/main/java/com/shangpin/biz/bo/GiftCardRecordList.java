package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class GiftCardRecordList implements Serializable {
	private static final long serialVersionUID = 1L;
	private String mainBalance;
	private String recordType;
	private String url;
	private List<GiftCardRecordListItem> list;

	public String getMainBalance() {
		return mainBalance;
	}

	public void setMainBalance(String mainBalance) {
		this.mainBalance = mainBalance;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public List<GiftCardRecordListItem> getList() {
		return list;
	}

	public void setList(List<GiftCardRecordListItem> list) {
		this.list = list;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
