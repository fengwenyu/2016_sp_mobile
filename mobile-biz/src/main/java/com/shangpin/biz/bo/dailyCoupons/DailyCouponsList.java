package com.shangpin.biz.bo.dailyCoupons;

import java.io.Serializable;
import java.util.List;

/**
 * 天天抢券列表数据结构
 * @author zkj
 *
 */
public class DailyCouponsList implements Serializable{
	
	
	private static final long serialVersionUID = -8650668695777504267L;
	private List<DailyCoupons> list;

	public List<DailyCoupons> getList() {
		return list;
	}

	public void setList(List<DailyCoupons> list) {
		this.list = list;
	}
	
}
