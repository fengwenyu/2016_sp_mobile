package com.shangpin.wireless.api.api2client.domain;

/**
 * 收货地址对象
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-06-24
 */
public class AddressVO {
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
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String getCardID() {
        return cardID;
    }
    public void setCardID(String cardID) {
        this.cardID = cardID;
    }
	public String getPostcode() {
		return zip;
	}
    
}
