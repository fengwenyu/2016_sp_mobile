package com.shangpin.mobileShangpin.base.model;

import java.io.Serializable;
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
 * 微信绑定信息表
 * 
 * @author: liling
 * @date:2013-8-01
 */
@Entity
@Table(name = "accountweixinbind")
public class AccountWeixinBind implements Serializable {
	private static final long serialVersionUID = -7784519026052681678L;
	/** 主键ID **/
	private int id;
	/** 微信ID **/
	private String weixinId;
	/** 用户ID **/
	private String userId;
	/** 登录用户名 **/
	private String loginName;
	/** 性别 **/
	private String gender;
	private String nickname;
	private String city;
	private String regOrigin;
	private Date regTime;
	private Date bindTime;
	private Date unbindTime;
	private Date createTime;
	private String reserve0;
	private String reserve1;
	private String reserve2;
	private String reserve3;
	private String reserve4;
	private String reserve5;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "weixinId", length = 50)
	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
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

	@Column(name = "nickname", length = 50)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "regOrigin", length = 10)
	public String getRegOrigin() {
		return regOrigin;
	}

	public void setRegOrigin(String regOrigin) {
		this.regOrigin = regOrigin;
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
	@Column(name = "bindTime")
	public Date getBindTime() {
		return bindTime;
	}

	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "unbindTime")
	public Date getUnbindTime() {
		return unbindTime;
	}

	public void setUnbindTime(Date unbindTime) {
		this.unbindTime = unbindTime;
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
}
