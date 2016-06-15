package com.shangpin.base.vo;

import java.io.Serializable;

/**
 * 收货地址
 * 
 * @author zhanghongwei
 *
 */
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	/** id */
	private String id;
	/** 名称 */
	private String name;
	/** 省份ID */
	private String province;
	/** 省份名称 */
	private String provname;
	/** 市ID */
	private String city;// 城市id
	/** 市名 */
	private String cityname;
	/** 县(区)id */
	private String area;// 县（区）id
	/** 县(区)名称 */
	private String areaname;
	/** 城镇id */
	private String town;
	/** 城镇名称 */
	private String townname;
	/** 详细地址 */
	private String addr;
	/** 邮编 */
	private String postcode;
	/** 是否是默认地址 1是默认 */
	private String isd;
	/** 电话 */
	private String tel;
	/** */
	private String cod;
	
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

	public String getProvname() {
		return provname;
	}

	public void setProvname(String provname) {
		this.provname = provname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getTownname() {
		return townname;
	}

	public void setTownname(String townname) {
		this.townname = townname;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getIsd() {
		return isd;
	}

	public void setIsd(String isd) {
		this.isd = isd;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
