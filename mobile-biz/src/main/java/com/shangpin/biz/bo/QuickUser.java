package com.shangpin.biz.bo;

import java.io.Serializable;

/** 
* @ClassName: QuickUser 
* @Description:立即购买用户信息 
* @author qinyingchun
* @date 2014年11月20日
* @version 1.0 
*/
public class QuickUser implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userId;
	private String gender;
	private String name;
	private String email;
	private String level;
	private String regOrigin;
	private String regTime;
	private String mobile;
	private String lv;
	private String isNewUser;//是否为新用户，1是，0不是
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getRegOrigin() {
		return regOrigin;
	}
	public void setRegOrigin(String regOrigin) {
		this.regOrigin = regOrigin;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLv() {
		return lv;
	}
	public void setLv(String lv) {
		this.lv = lv;
	}
	public String getIsNewUser() {
		return isNewUser;
	}
	public void setIsNewUser(String isNewUser) {
		this.isNewUser = isNewUser;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	} 
	
}
