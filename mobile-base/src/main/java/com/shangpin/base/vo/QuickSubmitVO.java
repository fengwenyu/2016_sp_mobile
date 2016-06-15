package com.shangpin.base.vo;

import java.io.Serializable;

/**
 * 一键购物相关参数
 * 
 * @author cuibinqiang
 * 
 */
public class QuickSubmitVO  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String userId;// 用户ID

	private String productId;// 商品编号

	private String skuId;// sku编号

	private String payTypeId;// 主支付方式

	private String payTypeChildId;// 子支付方式

	private String orderOrigin;// 订单来源

	private String shopType;// 表示订单来自尚品1，奥莱2

	private String consigneeName;// 收货人姓名

	private String province;// 省id

	private String provinceName;// 省份名称

	private String city;// 城市id

	private String cityName;// 城市名称

	private String area;// 区id

	private String areaName;// 区名称

	private String town;// 镇id

	private String townName;// 镇名

	private String address;// 详细地址

	private String zip;// 邮编

	private String tel;// 手机

	private String ssq;// 省市区

	private String flagPay;// 标示：0 支付宝，1 银联

	public String getFlagPay() {
		return flagPay;
	}

	public void setFlagPay(String flagPay) {
		this.flagPay = flagPay;
	}

	public String getSsq() {
		return ssq;
	}

	public void setSsq(String ssq) {
		this.ssq = ssq;
	}

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
