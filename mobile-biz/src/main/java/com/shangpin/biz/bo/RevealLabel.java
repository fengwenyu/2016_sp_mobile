package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class RevealLabel  implements Serializable{
	
	private static final long serialVersionUID = -8435226342283898938L;
	private List<CommonRules> list;

	public List<CommonRules> getList() {
		return list;
	}

	public void setList(List<CommonRules> list) {
		this.list = list;
	}
	
}
