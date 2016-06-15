package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/** 
* @ClassName: BrandList 
* @Description: 
* @author qinyingchun
* @date 2014年10月23日
* @version 1.0 
*/
public class BrandList implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String capital;
	private List<Brand> brands;
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public List<Brand> getBrands() {
		return brands;
	}
	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}
	
	

}
