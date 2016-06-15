package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 新品品牌
 * 
 * @author huangxiaoliang
 * 
 */
public class LatestBrand  implements Serializable{

	private static final long serialVersionUID = 1L;

	private String brandId;

	private String nameEN;

	private String nameCN;
	
	private String name;

	private String pic;

	private String isCollected;

	private String productCount;

	private String addTime;
	
	private String addTimeTitle;

	private String browseTimes;

	private String isFlagship;
	
	private String style;
	
	private String type;
	
	private String refContent;
	
	private Recommend recommend;
	
	private List<Product> list;
	
	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getNameCN() {
		return nameCN;
	}

	public void setNameCN(String nameCN) {
		this.nameCN = nameCN;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getIsCollected() {
		return isCollected;
	}

	public void setIsCollected(String isCollected) {
		this.isCollected = isCollected;
	}

	public String getProductCount() {
		return productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getBrowseTimes() {
		return browseTimes;
	}

	public void setBrowseTimes(String browseTimes) {
		this.browseTimes = browseTimes;
	}

	public String getIsFlagship() {
		return isFlagship;
	}

	public void setIsFlagship(String isFlagship) {
		this.isFlagship = isFlagship;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddTimeTitle() {
		return addTimeTitle;
	}

	public void setAddTimeTitle(String addTimeTitle) {
		this.addTimeTitle = addTimeTitle;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRefContent() {
		return refContent;
	}

	public void setRefContent(String refContent) {
		this.refContent = refContent;
	}

	public Recommend getRecommend() {
		return recommend;
	}

	public void setRecommend(Recommend recommend) {
		this.recommend = recommend;
	}

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}

}
