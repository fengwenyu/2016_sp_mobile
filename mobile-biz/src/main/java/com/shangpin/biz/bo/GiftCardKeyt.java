package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class GiftCardKeyt  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5601612452590251488L;
	
	public List<GiftCardKeytContent> list;

	public List<GiftCardKeytContent> getList() {
		return list;
	}

	public void setList(List<GiftCardKeytContent> list) {
		this.list = list;
	}
	

}
