package com.shangpin.wechat.bo.base;

import java.io.Serializable;

/**
 * 团购券
 * @author zrs
 *
 */
public class CardGroupon  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CardBaseInfo base_info;
	private String deal_detail;
	public CardBaseInfo getBase_info() {
		return base_info;
	}
	public void setBase_info(CardBaseInfo base_info) {
		this.base_info = base_info;
	}
	public String getDeal_detail() {
		return deal_detail;
	}
	public void setDeal_detail(String deal_detail) {
		this.deal_detail = deal_detail;
	}
	
}
