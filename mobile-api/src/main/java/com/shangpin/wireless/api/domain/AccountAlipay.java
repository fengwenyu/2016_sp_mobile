package com.shangpin.wireless.api.domain;

import java.util.Date;

/**
 * 支付宝用户信息
 * 
 * @Author: wangwenguan
 * @CreateDate: 2013-07-28
 */
public class AccountAlipay implements java.io.Serializable {

	private static final long serialVersionUID = 100L;
	private long id;
	private String aliUserId;	//	支付宝userid
	private String userId;	//	尚品userid
	private String product;	//	产品号
	private String gender;	//	用户真实性别
	private String nickname;	//	昵称
	private String city;	//	所在城市
	private String mobile;	//	用户真实手机号
	private String email;	//	用户真实邮箱地址
	private String authcode;	//	授权码
	private String accesstoken;	//	访问令牌(短token)
	private int accessExpiresSec;	//	访问令牌生命周期的秒数
	private String refreshtoken;	//	刷新令牌(长token)
	private int refreshExpiresSec;	//	刷新令牌生命周期的秒数
	private String regOrigin;	//	注册来源
	private Date regTime;	//	用户的注册时间
	private Date updateTime;	//	最后的刷新时间
	private Date createTime;	//	创建时间
	private String reserve0;// 备用字段
	private String reserve1;// 备用字段
	private String reserve2;// 备用字段
	private String reserve3;// 备用字段
	private String reserve4;// 备用字段
	private String reserve5;// 备用字段

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAliUserId() {
		return aliUserId;
	}

	public void setAliUserId(String aliUserId) {
		this.aliUserId = aliUserId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	public int getAccessExpiresSec() {
		return accessExpiresSec;
	}

	public void setAccessExpiresSec(int accessExpiresSec) {
		this.accessExpiresSec = accessExpiresSec;
	}

	public String getRefreshtoken() {
		return refreshtoken;
	}

	public void setRefreshtoken(String refreshtoken) {
		this.refreshtoken = refreshtoken;
	}

	public int getRefreshExpiresSec() {
		return refreshExpiresSec;
	}

	public void setRefreshExpiresSec(int refreshExpiresSec) {
		this.refreshExpiresSec = refreshExpiresSec;
	}

	public String getRegOrigin() {
		return regOrigin;
	}
	public void setRegOrigin(String regOrigin) {
		this.regOrigin = regOrigin;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getReserve0() {
		return reserve0;
	}

	public void setReserve0(String reserve0) {
		this.reserve0 = reserve0;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

}
