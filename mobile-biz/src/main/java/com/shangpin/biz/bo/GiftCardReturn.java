package com.shangpin.biz.bo;

import java.io.Serializable;

/** 
* @ClassName: GiftCardReturn 
* @Description: 礼品卡支付返回的数据实体类
* @author qinyingchun
* @date 2014年12月3日
* @version 1.0 
*/
public class GiftCardReturn implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String amount;
	private String payresult;
	private String onlineamount ;
	private String payamount ;
	private String discountamount ;
	private String giftcardamount;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPayresult() {
		return payresult;
	}
	public void setPayresult(String payresult) {
		this.payresult = payresult;
	}
	public String getOnlineamount() {
		return onlineamount;
	}
	public void setOnlineamount(String onlineamount) {
		this.onlineamount = onlineamount;
	}
	public String getPayamount() {
		return payamount;
	}
	public void setPayamount(String payamount) {
		this.payamount = payamount;
	}
	public String getDiscountamount() {
		return discountamount;
	}
	public void setDiscountamount(String discountamount) {
		this.discountamount = discountamount;
	}
	public String getGiftcardamount() {
		return giftcardamount;
	}
	public void setGiftcardamount(String giftcardamount) {
		this.giftcardamount = giftcardamount;
	}
	

}
