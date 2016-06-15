package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 按时间对新品品牌分组
 * 
 * @author huangxiaoliang
 * 
 */
public class LatestProduct  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String time;
	
	private String title;
	
	private String status;
	
	private List<LatestBrand> brandList;
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<LatestBrand> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<LatestBrand> brandList) {
		this.brandList = brandList;
	}

}
