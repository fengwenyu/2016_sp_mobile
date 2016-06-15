package com.shangpin.wireless.domain;

import java.util.Date;

public class HotBrands {
	// 主键id;
	private Long id;
	//品牌名称
	private String brandName;
	//品牌ID
	private String brandId;
	//图片链接
	private String imgUrl;
	//图片宽度
	private String imgWidth;
	//图片高度
	private String imgHeight;
	//排序字段，如果相同以创建日期排序，新创建的在前边
	private Integer sort;
	//创建日期
	private Date createDate;
	//头图链接
	private String topImgUrl;
	//头图宽度
	private String topImgWidth;
	//头图高度
	private String topImgHeight;
	private String reserve0;// 备用字段
	private String reserve1;// 备用字段
	private String reserve2;// 备用字段

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}

	public String getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	public String getTopImgUrl() {
		return topImgUrl;
	}

	public void setTopImgUrl(String topImgUrl) {
		this.topImgUrl = topImgUrl;
	}

	public String getTopImgWidth() {
		return topImgWidth;
	}

	public void setTopImgWidth(String topImgWidth) {
		this.topImgWidth = topImgWidth;
	}

	public String getTopImgHeight() {
		return topImgHeight;
	}

	public void setTopImgHeight(String topImgHeight) {
		this.topImgHeight = topImgHeight;
	}

	public String getReserve0() {
		return reserve0;
	}

	public void setReserve0(String reserve0) {
		this.reserve0 = reserve0;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
}
