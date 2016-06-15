package com.shangpin.wireless.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class PushDictionary implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String dictName;
	private Timestamp createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
