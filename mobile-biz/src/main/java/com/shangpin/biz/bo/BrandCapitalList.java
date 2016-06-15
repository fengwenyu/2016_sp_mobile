package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;


/** 
* @ClassName: BrandCapitalList
* @Description:尚品M站品牌类表实体类
* @author qinyingchun
* @date 2014年10月24日
* @version 1.0 
*/
public class BrandCapitalList implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String capital;
	private List<Sbrand> brands;
	/**
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}
	/**
	 * @param capital the capital to set
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}
	/**
	 * @return the brands
	 */
	public List<Sbrand> getBrands() {
		return brands;
	}
	/**
	 * @param brands the brands to set
	 */
	public void setBrands(List<Sbrand> brands) {
		this.brands = brands;
	}
	
}
