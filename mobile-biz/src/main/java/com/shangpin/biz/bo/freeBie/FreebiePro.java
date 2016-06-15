package com.shangpin.biz.bo.freeBie;

import java.io.Serializable;

/**
 * 520买赠活动对象
 * 
 * @author fengwenyu
 * 
 */
public class FreebiePro  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String orderId;
	private String spu;
	private String sku;
	private String payNum;
	
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSpu() {
		return spu;
	}
	public void setSpu(String spu) {
		this.spu = spu;
	}
	public String getPayNum() {
		return payNum;
	}
	public void setPayNum(String payNum) {
		this.payNum = payNum;
	}
	
	
	
}
