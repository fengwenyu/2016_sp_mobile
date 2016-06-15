package com.shangpin.mobileShangpin.platform.vo;


/**
 * 搜索品牌条件类
 * 
 */
public class SearchBrandItemVO implements Comparable{

	private String brandId;//品牌id
	private String brandEnName;//品牌英文名称
	private String brandCnName;//品牌中文名称
	private String brandEnViewName;
	private String count;//条数
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getBrandEnName() {
		return brandEnName;
	}
	public void setBrandEnName(String brandEnName) {
		this.brandEnName = brandEnName;
		setBrandEnViewName(brandEnName);
	}
	public String getBrandCnName() {
		return brandCnName;
	}
	public void setBrandCnName(String brandCnName) {
		this.brandCnName = brandCnName;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	public String getBrandEnViewName() {
		return brandEnViewName;
	}
	public void setBrandEnViewName(String brandEnViewName) {
		this.brandEnViewName = brandEnViewName.replace("'", "\\'");
	}
	@Override
	public int compareTo(Object obj) {
		SearchBrandItemVO target = (SearchBrandItemVO) obj;
		return this.getBrandEnName().compareTo(target.getBrandEnName());
	}
}
