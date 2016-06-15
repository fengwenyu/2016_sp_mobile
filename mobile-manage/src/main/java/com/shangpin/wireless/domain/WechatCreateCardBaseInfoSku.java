package com.shangpin.wireless.domain;


public class WechatCreateCardBaseInfoSku implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;
	
	
	private String quantity;//   是   int   100000  卡券库存的数量，上限为100000000。


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	
	
	
}
