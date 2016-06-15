package com.shangpin.mobileShangpin.platform.vo;

import java.util.ArrayList;
import java.util.List;


/**
 * 支付方式对象
 * 
 * @Author wangfeng
 * @CreateDate 2013-8-16
 */
public class PaymentVO {
	/*** 渠道号*/
	private String ch;
	/*** 支付方式*/
	private List<PayVO> paymentlist=new ArrayList<PayVO>();
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public List<PayVO> getPaymentlist() {
		return paymentlist;
	}
	public void setPaymentlist(List<PayVO> paymentlist) {
		this.paymentlist = paymentlist;
	}
	
	
}
