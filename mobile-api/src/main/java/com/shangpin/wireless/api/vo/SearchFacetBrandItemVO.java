package com.shangpin.wireless.api.vo;
/**
 * 搜索品牌条件类
 * 
 */
public class SearchFacetBrandItemVO implements Comparable{

	private String brandId;//品牌id
	private String brandEnName;//品牌英文名称
	private String brandCnName;//品牌中文名称
	private String count;//条数

	public void parse (String attrstr, String text) {
		String[] itemarr = attrstr.split("\\|");
		setBrandId(itemarr[0]);
		setBrandEnName(itemarr[1]);
		if (itemarr.length == 2) {
			setBrandCnName(itemarr[1]);
		} else {
			setBrandCnName(itemarr[2]);
		}
		setCount(text);
	}
	
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

	@Override
	public int compareTo(Object obj) {
		SearchFacetBrandItemVO target = (SearchFacetBrandItemVO) obj;
		return this.getBrandEnName().compareTo(target.getBrandEnName());
	}

	
}
