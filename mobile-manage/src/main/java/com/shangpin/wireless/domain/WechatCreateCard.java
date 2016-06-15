package com.shangpin.wireless.domain;


public class WechatCreateCard implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;	

	private WechatCreateCardBaseInfo base_info;

	private String deal_detail;//  是 	string(24) 	双人套餐\n -进口红酒一支。\n孜然牛肉一份。 	团购券专用，团购详情。 
	
	private String least_cost;// 	是 	int 	10000 	代金券专用，表示起用金额（单位为分）,如果无起用门槛则填0。
	private String reduce_cost;// 	是 	int 	10000 	代金券专用，表示减免金额。（单位为分）

	private String discount;// 	是 	int 	30 	折扣券专用，表示打折额度（百分比）。填30就是七折。

	private String gift;// 	是 	string(3072) 	可兑换音乐木盒一个。 	礼品券专用，填写礼品的名称。

	private String default_detail;// 	是 	string(3072) 	音乐木盒。 	优惠券专用，填写优惠详情。 

	public WechatCreateCardBaseInfo getBase_info() {
		return base_info;
	}

	public void setBase_info(WechatCreateCardBaseInfo base_info) {
		this.base_info = base_info;
	}

	public String getDeal_detail() {
		return deal_detail;
	}

	public void setDeal_detail(String deal_detail) {
		this.deal_detail = deal_detail;
	}

	public String getLeast_cost() {
		return least_cost;
	}

	public void setLeast_cost(String least_cost) {
		this.least_cost = least_cost;
	}

	public String getReduce_cost() {
		return reduce_cost;
	}

	public void setReduce_cost(String reduce_cost) {
		this.reduce_cost = reduce_cost;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}

	public String getDefault_detail() {
		return default_detail;
	}

	public void setDefault_detail(String default_detail) {
		this.default_detail = default_detail;
	}	
	
}
