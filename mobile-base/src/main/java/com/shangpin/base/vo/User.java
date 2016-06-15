package com.shangpin.base.vo;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 4305374828766081854L;

    public String userid;
    public String gender;
    public String name;
    public String mobile;
    public String email;
    public String regTime;
    public String regOrigin;
    public String invitefriendcode;
    public String cartcount;
    public String nopaycount;
    public String level;
    public String lv;
    public String priceindex;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getRegOrigin() {
        return regOrigin;
    }

    public void setRegOrigin(String regOrigin) {
        this.regOrigin = regOrigin;
    }

    public String getInvitefriendcode() {
        return invitefriendcode;
    }

    public void setInvitefriendcode(String invitefriendcode) {
        this.invitefriendcode = invitefriendcode;
    }

    public String getCartcount() {
        return cartcount;
    }

    public void setCartcount(String cartcount) {
        this.cartcount = cartcount;
    }

    public String getNopaycount() {
        return nopaycount;
    }

    public void setNopaycount(String nopaycount) {
        this.nopaycount = nopaycount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public String getPriceindex() {
        return priceindex;
    }

    public void setPriceindex(String priceindex) {
        this.priceindex = priceindex;
    }

}
