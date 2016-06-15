package com.shangpin.wireless.api.domain;


import java.io.Serializable;
import java.util.Date;
/**
 * 首页推荐新品和品牌
 * 
 * @Author: liling
 * @CreateDate: 2013-09-02
 */
public class NewProductBrand implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//品类id
	private String categoryId;
	//新品推荐一id
	private String firstNewProductId;
	//新品推荐二id
	private String secondNewProductId;
	//品牌推荐一id
	private String firstBrandId;
	//品牌推荐一下的推荐商品
	private String firstBrandProductId;
	//品牌推荐一名称
	private String firstBrandName;
	//品牌推荐二id
	private String secondBrandId;
	//品牌推荐二名称
	private String secondBrandName;
	//品牌推荐 二下的推荐商品
	private String secondBrandProductId;
	//品牌推荐三id
	private String threeBrandId;
	//品牌推荐三名称
	private String threeBrandName;
	//品牌推荐三下的推荐商品
	private String threeBrandProductId;
	//品牌推荐四id
	private String fourthBrandId;
	//品牌推荐四名称
	private String fourthBrandName;
	//品牌推荐四下的推荐商品
	private String fourthBrandProductId;
	//品牌推荐五id
	private String fifthBrandId;
	//品牌推荐五名称
	private String fifthBrandName;
	//品牌推荐五下的推荐商品
	private String fifthBrandProductId;
	//品牌推荐六id
	private String sixthBrandId;
	//品牌推荐六名称
	private String sixthBrandName;
	//品牌推荐六下的推荐商品
	private String sixthBrandProductId;
	//品牌推荐七id
	private String seventhBrandId;
	//品牌推荐七名称
	private String seventhBrandName;
	//品牌推荐七下的推荐商品
	private String seventhBrandProductId;
	// 创建日期
	private Date createTime;
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getFirstNewProductId() {
		return firstNewProductId;
	}
	public void setFirstNewProductId(String firstNewProductId) {
		this.firstNewProductId = firstNewProductId;
	}
	public String getSecondNewProductId() {
		return secondNewProductId;
	}
	public void setSecondNewProductId(String secondNewProductId) {
		this.secondNewProductId = secondNewProductId;
	}
	public String getFirstBrandId() {
		return firstBrandId;
	}
	public void setFirstBrandId(String firstBrandId) {
		this.firstBrandId = firstBrandId;
	}
	public String getFirstBrandProductId() {
		return firstBrandProductId;
	}
	public void setFirstBrandProductId(String firstBrandProductId) {
		this.firstBrandProductId = firstBrandProductId;
	}
	public String getFirstBrandName() {
		return firstBrandName;
	}
	public void setFirstBrandName(String firstBrandName) {
		this.firstBrandName = firstBrandName;
	}
	public String getSecondBrandId() {
		return secondBrandId;
	}
	public void setSecondBrandId(String secondBrandId) {
		this.secondBrandId = secondBrandId;
	}
	public String getSecondBrandName() {
		return secondBrandName;
	}
	public void setSecondBrandName(String secondBrandName) {
		this.secondBrandName = secondBrandName;
	}
	public String getSecondBrandProductId() {
		return secondBrandProductId;
	}
	public void setSecondBrandProductId(String secondBrandProductId) {
		this.secondBrandProductId = secondBrandProductId;
	}
	public String getThreeBrandId() {
		return threeBrandId;
	}
	public void setThreeBrandId(String threeBrandId) {
		this.threeBrandId = threeBrandId;
	}
	public String getThreeBrandName() {
		return threeBrandName;
	}
	public void setThreeBrandName(String threeBrandName) {
		this.threeBrandName = threeBrandName;
	}
	public String getThreeBrandProductId() {
		return threeBrandProductId;
	}
	public void setThreeBrandProductId(String threeBrandProductId) {
		this.threeBrandProductId = threeBrandProductId;
	}
	public String getFourthBrandId() {
		return fourthBrandId;
	}
	public void setFourthBrandId(String fourthBrandId) {
		this.fourthBrandId = fourthBrandId;
	}
	public String getFourthBrandName() {
		return fourthBrandName;
	}
	public void setFourthBrandName(String fourthBrandName) {
		this.fourthBrandName = fourthBrandName;
	}
	public String getFourthBrandProductId() {
		return fourthBrandProductId;
	}
	public void setFourthBrandProductId(String fourthBrandProductId) {
		this.fourthBrandProductId = fourthBrandProductId;
	}
	public String getFifthBrandId() {
		return fifthBrandId;
	}
	public void setFifthBrandId(String fifthBrandId) {
		this.fifthBrandId = fifthBrandId;
	}
	public String getFifthBrandName() {
		return fifthBrandName;
	}
	public void setFifthBrandName(String fifthBrandName) {
		this.fifthBrandName = fifthBrandName;
	}
	public String getFifthBrandProductId() {
		return fifthBrandProductId;
	}
	public void setFifthBrandProductId(String fifthBrandProductId) {
		this.fifthBrandProductId = fifthBrandProductId;
	}
	public String getSixthBrandId() {
		return sixthBrandId;
	}
	public void setSixthBrandId(String sixthBrandId) {
		this.sixthBrandId = sixthBrandId;
	}
	public String getSixthBrandName() {
		return sixthBrandName;
	}
	public void setSixthBrandName(String sixthBrandName) {
		this.sixthBrandName = sixthBrandName;
	}
	public String getSixthBrandProductId() {
		return sixthBrandProductId;
	}
	public void setSixthBrandProductId(String sixthBrandProductId) {
		this.sixthBrandProductId = sixthBrandProductId;
	}
	public String getSeventhBrandId() {
		return seventhBrandId;
	}
	public void setSeventhBrandId(String seventhBrandId) {
		this.seventhBrandId = seventhBrandId;
	}
	public String getSeventhBrandName() {
		return seventhBrandName;
	}
	public void setSeventhBrandName(String seventhBrandName) {
		this.seventhBrandName = seventhBrandName;
	}
	public String getSeventhBrandProductId() {
		return seventhBrandProductId;
	}
	public void setSeventhBrandProductId(String seventhBrandProductId) {
		this.seventhBrandProductId = seventhBrandProductId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
