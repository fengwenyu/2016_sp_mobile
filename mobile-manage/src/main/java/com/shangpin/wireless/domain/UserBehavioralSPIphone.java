package com.shangpin.wireless.domain;

import java.io.Serializable;
import java.util.Date;

public class UserBehavioralSPIphone implements Serializable{
	
	private static final long serialVersionUID = 5408777233935416600L;
	// 主键id
	private Long id;
	// 用户行为名称
	private String behaviorName;
	// 用户手机imei
	private String imei;
	// 用户行为时间
	private Date date;
	private String reserve0;// 备用字段
	private String reserve1;// 备用字段
	private String reserve2;// 备用字段
	private String reserve3;// 备用字段
	private String reserve4;// 备用字段
	private String reserve5;// 备用字段

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBehaviorName() {
		return behaviorName;
	}

	public void setBehaviorName(String behaviorName) {
		this.behaviorName = behaviorName;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
