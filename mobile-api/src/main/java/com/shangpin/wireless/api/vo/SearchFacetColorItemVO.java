package com.shangpin.wireless.api.vo;
/**
 * 搜索品牌条件类
 * 
 */
public class SearchFacetColorItemVO {

	private String colorId;//颜色id
	private String colorName;//颜色名称
	private String colorPicNo;//颜色图片编号
	private String colorPicUrl;//颜色图片url
	private String count;//条数
	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getColorPicNo() {
		return colorPicNo;
	}
	public void setColorPicNo(String colorPicNo) {
		this.colorPicNo = colorPicNo;
	}
	public String getColorPicUrl() {
		return colorPicUrl;
	}
	public void setColorPicUrl(String colorPicUrl) {
		this.colorPicUrl = colorPicUrl;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
}
