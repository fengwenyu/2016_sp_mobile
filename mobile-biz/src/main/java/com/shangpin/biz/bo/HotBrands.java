package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.Date;

/** 
* @ClassName: HotBrands 
* @Description: 热门品牌实体类 
* @author qinyingchun
* @date 2014年10月25日
* @version 1.0 
*/
public class HotBrands implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String brandName;
    private String brandId;
    private String imgUrl;
    private String imgWidth;
    private String imgHeight;
    private String topImgUrl;
    private String topImgWidth;
    private String topImgHeight;
    private Integer sort;
    private Date createDate;
    private String encodeName;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * @return the brandId
	 */
	public String getBrandId() {
		return brandId;
	}
	/**
	 * @param brandId the brandId to set
	 */
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * @return the imgWidth
	 */
	public String getImgWidth() {
		return imgWidth;
	}
	/**
	 * @param imgWidth the imgWidth to set
	 */
	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}
	/**
	 * @return the imgHeight
	 */
	public String getImgHeight() {
		return imgHeight;
	}
	/**
	 * @param imgHeight the imgHeight to set
	 */
	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}
	/**
	 * @return the topImgUrl
	 */
	public String getTopImgUrl() {
		return topImgUrl;
	}
	/**
	 * @param topImgUrl the topImgUrl to set
	 */
	public void setTopImgUrl(String topImgUrl) {
		this.topImgUrl = topImgUrl;
	}
	/**
	 * @return the topImgWidth
	 */
	public String getTopImgWidth() {
		return topImgWidth;
	}
	/**
	 * @param topImgWidth the topImgWidth to set
	 */
	public void setTopImgWidth(String topImgWidth) {
		this.topImgWidth = topImgWidth;
	}
	/**
	 * @return the topImgHeight
	 */
	public String getTopImgHeight() {
		return topImgHeight;
	}
	/**
	 * @param topImgHeight the topImgHeight to set
	 */
	public void setTopImgHeight(String topImgHeight) {
		this.topImgHeight = topImgHeight;
	}
	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the encodeName
	 */
	public String getEncodeName() {
		return encodeName;
	}
	/**
	 * @param encodeName the encodeName to set
	 */
	public void setEncodeName(String encodeName) {
		this.encodeName = encodeName;
	}

}
