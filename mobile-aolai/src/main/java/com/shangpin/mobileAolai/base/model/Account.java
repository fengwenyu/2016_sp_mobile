package com.shangpin.mobileAolai.base.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 账号信息表
 * 
 * @author: yumeng
 * @date:2012-10-26
 */

@Entity
@Table(name = "account")
public class Account implements Serializable {

	/** 序列化ID **/
	private static final long serialVersionUID = -9148504669195378045L;

	/** 主键ID **/
	private int id;
	/** 用户ID **/
	private String userId;
	/** 登录用户名 **/
	private String loginName;
	/** 性别 **/
	private String gender;
	/** 渠道号 **/
	private BigInteger channel;
	/** 产品号 **/
	private BigInteger product;
	/** 注册来源 **/
	private String regOrigin;
	/** 登录时间 **/
	private Date loginTime;
	/** 注册日期 **/
	private Date regTime;
	/** 创建记录时间 **/
	private Date createTime;
	private String reserve0;
	private String reserve1;
	private String reserve2;
	private String reserve3;
	private String reserve4;
	private String reserve5;
	/** 来源平台 **/
	private String platform;
	/**  **/
	private String phoneType;
	/** 手机型号 **/
	private String phoneModel;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "userId", length = 50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "loginName", length = 50)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "gender", length = 4)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "channel" ,length=20)
	public BigInteger getChannel() {
		return channel;
	}

	public void setChannel(BigInteger channel) {
		this.channel = channel;
	}

	@Column(name = "product" ,length=20)
	public BigInteger getProduct() {
		return product;
	}

	public void setProduct(BigInteger product) {
		this.product = product;
	}

	@Column(name = "regOrigin", length = 10)
	public String getRegOrigin() {
		return regOrigin;
	}

	public void setRegOrigin(String regOrigin) {
		this.regOrigin = regOrigin;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "loginTime")
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "regTime")
	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "reserve0", length = 255)
	public String getReserve0() {
		return reserve0;
	}

	public void setReserve0(String reserve0) {
		this.reserve0 = reserve0;
	}

	@Column(name = "reserve1", length = 255)
	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	@Column(name = "reserve2", length = 255)
	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	@Column(name = "reserve3", length = 255)
	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	@Column(name = "reserve4", length = 255)
	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	@Column(name = "reserve5", length = 255)
	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

	@Column(name = "platform", length = 10)
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Column(name = "phoneType", length = 20)
	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	@Column(name = "phoneModel", length = 40)
	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}
}
