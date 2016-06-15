package com.shangpin.biz.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/** 
* @ClassName: Receive 
* @Description: 新版物流中的收货人信息
* @author 李灵
* @date 2014年11月29日 
* @version 1.0 
*/
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Receive implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String province;
	private String provName;
	private String city;
	private String cityName;
	private String area;
	private String areaName;
	private String town;
	private String townName;
	private String addr;
	private String zip;
	private String isd;
	private String tel;
	private String cod;
	private String cardID;
	private String isPos;
	private String enabled;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getIsd() {
		return isd;
	}

	public void setIsd(String isd) {
		this.isd = isd;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

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

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
}
