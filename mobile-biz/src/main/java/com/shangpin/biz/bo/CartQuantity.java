package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * 购物车数量
 * 
 * @author zghw
 *
 */
public class CartQuantity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private String InventoryQuantity;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInventoryQuantity() {
		return InventoryQuantity;
	}

	public void setInventoryQuantity(String inventoryQuantity) {
		InventoryQuantity = inventoryQuantity;
	}

}
