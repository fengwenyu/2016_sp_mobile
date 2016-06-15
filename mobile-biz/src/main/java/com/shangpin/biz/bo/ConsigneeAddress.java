package com.shangpin.biz.bo;

import java.io.Serializable;


/**
 * 收货人地址数据传输对象，用于前台展示
 * 
 */
public class ConsigneeAddress  implements Serializable{
	private static final long serialVersionUID = 1L;
	/** 收货人地址ID */
	private String id;
	/** 收货人姓名 */
	private String name;
	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 区 */
	private String area;
	/** 详细地址 */
	private String addr;
	/** 邮编 */
	private String zip;
	/** 联系电话 */
	private String tel;
	/** 是否支持货到付款；0不支持 */
	private String cod;
	/** 默认地址；默认为1 */
	private String isd;
	/** 省中文 */
	private String provName;
	/** 市中文 */
	private String cityName;
	/** 区中文 */
	private String areaName;
	/** 乡镇*/
	private String town;
	private String townName;
	private String isPos;//是否支持pos机刷卡，0不支持，1支持
	private String cardID;//身份证号
	
	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public String getIsPos() {
		return isPos;
	}

	public void setIsPos(String isPos) {
		this.isPos = isPos;
	}

	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getIsd() {
		return isd;
	}

	public void setIsd(String isd) {
		this.isd = isd;
	}
	
	
	
	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

}
