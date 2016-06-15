package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * sku数量
 * @author qinyingchun
 *
 */
public class SkuCount implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String skuNo;
	private String count;
	public String getSkuNo() {
		return skuNo;
	}
	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	

}
