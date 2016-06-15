package com.shangpin.core.entity;

// Generated 2014-5-26 18:24:56 

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Account
 * 
 * @author liyongqiao
 */
@Entity
@Table(name = "receive")
public class Receive implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String Fuserid;
    private String FspuId;
    private String ForderId;
    private String LuserId;
    private String addressId;
	private String skuId;
    private String phone;
    private Date createTime;
    private String FspuNo;
    private String lorderId;//领取订单号
    private String mailAddress;
    
    @Column(name = "mailAddress", length = 200)
    public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	@Column(name = "lorderId", length = 200)
    public String getLorderId() {
		return lorderId;
	}
	public void setLorderId(String lorderId) {
		this.lorderId = lorderId;
	}
	@Column(name = "FspuNo", length = 200)
    public String getFspuNo() {
		return FspuNo;
	}
	public void setFspuNo(String fspuNo) {
		FspuNo = fspuNo;
	}
	public Receive() {
    }
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
		this.id = id;
	}
    @Column(name = "Fuserid", length = 200)
    public String getFuserid() {
		return Fuserid;
	}
	public void setFuserid(String fuserid) {
		Fuserid = fuserid;
	}
	@Column(name = "FspuId", length = 200)
	public String getFspuId() {
		return FspuId;
	}
	public void setFspuId(String fspuId) {
		FspuId = fspuId;
	}
	@Column(name = "ForderId", length = 200)
	public String getForderId() {
		return ForderId;
	}
	public void setForderId(String forderId) {
		ForderId = forderId;
	}
	@Column(name = "LuserId", length = 200)
	public String getLuserId() {
		return LuserId;
	}
	public void setLuserId(String luserId) {
		LuserId = luserId;
	}
	@Column(name = "addressId", length = 200)
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	@Column(name = "skuId", length = 200)
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	@Column(name = "phone", length = 200)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
