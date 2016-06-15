package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 商品数量
 * @author qinyingchun
 *
 */
public class ProductCount implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String totalCount;
	private List<SkuCount> sku;
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public List<SkuCount> getSku() {
		return sku;
	}
	public void setSku(List<SkuCount> sku) {
		this.sku = sku;
	}
	

}
