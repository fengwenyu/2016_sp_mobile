package com.shangpin.wireless.api.businessbean;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

/**
 * 用于将更多品类XML文件转换为javabean实体类（品牌集合实体）
 * 
 * @author yumeng
 *
 */
@XObject(value = "brands")
public class Brands {
	/** 品牌列表 */
	@XNodeList(value = "brand", type = ArrayList.class, componentType = Brand.class)
	private List<Brand> brand;
	/** 首字母索引 */
	@XNode("@capital")
	private String capital;

	public List<Brand> getBrand() {
		return brand;
	}

	public void setBrand(List<Brand> brand) {
		this.brand = brand;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}
}