package com.shangpin.wechat.bo.base;


public class CardBaseInfoSku implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;	
	
	private String quantity;//   是   int   100000  卡券现有库存的数量 
	private String total_quantity;//   是   int   100000  卡券全部库存的数量，上限为100000000。


	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotal_quantity() {
		return total_quantity;
	}


	public void setTotal_quantity(String total_quantity) {
		this.total_quantity = total_quantity;
	}


	
	
	
}
