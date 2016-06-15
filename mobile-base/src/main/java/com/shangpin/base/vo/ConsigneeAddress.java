package com.shangpin.base.vo;

import java.io.Serializable;

/**
 * 收货地址相关参数
 * 
 * @author sunweiwei
 * 
 */
public class ConsigneeAddress  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId;// 用户的唯一标识
    private String consigneeName;// 收货人姓名
    private String province;// 省份id
    private String city;// 城市id
    private String area;// 县（区）id
    private String address;// 街道详细地址
    private String town;//乡镇
    private String postcode;// 邮编
    private String tel;// 手机
    private String cardID;//身份证号码
    private String setd;// 设为默认:true设定，false取消默认，空值则不改变状态
    private String addrId;// 用户收货地址的唯一标识

    public String getAddrId() {
        return addrId;
    }

    public void setAddrId(String addrId) {
        this.addrId = addrId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSetd() {
        return setd;
    }

    public void setSetd(String setd) {
        this.setd = setd;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

}
