package com.shangpin.wireless.api.vo;
/**
 * 搜索价格条件类
 * 
 */
public class SearchFacetPriceItemVO {

	private String among;//价格区间
	private String count;//条数
	public String getAmong() {
		return among;
	}
	public void setAmong(String among) {
		this.among = among;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
}
