package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class ReturnContent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Orderdetails> orderdetails;//订单明细
	private List<ReturnProgress> returnProgress;//退货进程
	private ReturnInfo returnInfo;//退货详细
	
	
	public List<Orderdetails> getOrderdetails() {
		return orderdetails;
	}
	public void setOrderdetails(List<Orderdetails> orderdetails) {
		this.orderdetails = orderdetails;
	}
	public List<ReturnProgress> getReturnProgress() {
		return returnProgress;
	}
	public void setReturnProgress(List<ReturnProgress> returnProgress) {
		this.returnProgress = returnProgress;
	}
	public ReturnInfo getReturnInfo() {
		return returnInfo;
	}
	public void setReturnInfo(ReturnInfo returnInfo) {
		this.returnInfo = returnInfo;
	}
	
}
