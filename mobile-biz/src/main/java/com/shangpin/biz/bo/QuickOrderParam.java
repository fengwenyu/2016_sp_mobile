package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * @ClassName: QuickOrderParam
 * @Description:立即购买快速提交订单参数实体类
 * @author qinyingchun
 * @date 2014年11月20日
 * @version 1.0
 */
public class QuickOrderParam implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
	private String productId;
	private String skuId;
	private String payTypeId;
	private String payTypeChildId;
	private String orderOrigin;
	private String shopType;
	private String consigneeName;
	private String province;
	private String provinceName;
	private String city;
	private String cityName;
	private String area;
	private String areaName;
	private String town;
	private String townName;
	private String address;
	private String zip;
	private String tel;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getPayTypeId() {
		return payTypeId;
	}
	public void setPayTypeId(String payTypeId) {
		this.payTypeId = payTypeId;
	}
	public String getPayTypeChildId() {
		return payTypeChildId;
	}
	public void setPayTypeChildId(String payTypeChildId) {
		this.payTypeChildId = payTypeChildId;
	}
	public String getOrderOrigin() {
		return orderOrigin;
	}
	public void setOrderOrigin(String orderOrigin) {
		this.orderOrigin = orderOrigin;
	}
	public String getShopType() {
		return shopType;
	}
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	

}
