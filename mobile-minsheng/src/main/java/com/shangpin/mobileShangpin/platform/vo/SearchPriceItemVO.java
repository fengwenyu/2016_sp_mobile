package com.shangpin.mobileShangpin.platform.vo;

import java.util.regex.Pattern;

/**
 * 搜索价格条件类
 * 
 */
public class SearchPriceItemVO {

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
	
	public void dealAmong(){
		String among = this.among;
		if(Pattern.compile("^\\d+$").matcher(among).matches()){
			this.among = among +"以上";
		}
	}
}
