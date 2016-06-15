package com.shangpin.wireless.api.domain;

import java.util.Date;


public class AppPictures {
	// 主键id
	private Long id;
	// 图片链接
	private String imgUrl;
	private String osType;
	// 图片宽度
	private String imgWidth;
	// 图片高度
	private String imgHeight;
	// 分享链接
	private String shareUrl;
	// 文字描述
	private String description;
	private Date createDate;
	private String shareTitle;
	//产品类型，奥莱或者尚品
	private ProductTypeEnum productType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
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
	public String getShareUrl() {
		return shareUrl;
	}
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getShareTitle() {
		return shareTitle;
	}
	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}
	public ProductTypeEnum getProductType() {
		return productType;
	}
	public void setProductType(ProductTypeEnum productType) {
		this.productType = productType;
	}
	
	
}
