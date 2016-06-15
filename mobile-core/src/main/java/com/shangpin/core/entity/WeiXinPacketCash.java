package com.shangpin.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 微信红包现金卷实体类
 * @author tongwenli
 */
@Entity
@Table(name="weixin_packet_cash")
public class WeiXinPacketCash implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String  cashName;
	private int cashValue;
	private String threshold;//额度
	private String validTime;
	private String actiCode;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="cash_name")
	public String getCashName() {
		return cashName;
	}
	public void setCashName(String cashName) {
		this.cashName = cashName;
	}
	@Column(name="cash_value")
	public int getCashValue() {
		return cashValue;
	}
	public void setCashValue(int cashValue) {
		this.cashValue = cashValue;
	}
	
	@Column(name="threshold")
	public String getThreshold() {
		return threshold;
	}
	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}
	
	@Column(name="valid_time")
	public String getValidTime() {
		return validTime;
	}
	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}
	
	@Column(name="acti_code")
	public String getActiCode() {
		return actiCode;
	}
	public void setActiCode(String actiCode) {
		this.actiCode = actiCode;
	}

	
}
