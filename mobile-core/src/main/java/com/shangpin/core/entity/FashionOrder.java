package com.shangpin.core.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "fashion_order")
public class FashionOrder implements Serializable{
	
    private static final long serialVersionUID = -8604123515013285465L;
    /**
	 * 主键id
	 */
	private Long id;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 装备id
	 */
	private Long packId;
	/**
	 * 报名人姓名
	 */
	private String name;
	/**
	 * 报名人性别
	 */
	private String sex;

	/**
	 * 报名人生日
	 */
	private Date birthday;

	/**
	 * 报名人手机号
	 */
	private String phone;
	/**
	 * 报名人邮箱
	 */
	private String email;
	/**
	 * 报名人所在省
	 */
	private String province;
	/**
	 * 报名人所在市
	 */
	private String city;
	/**
	 * 报名人所在市
	 */
	private String area;
	/**
	 * 报名人收取装备地址
	 */
	private String addr;
	/**
	 * 报名人身份证
	 */
	private String pid;
	/**
	 * 紧急联系人姓名
	 */
	private String contacts;
	/**
	 * 紧急联系人手机
	 */
	private String contactsPhone;
	/**
	 * 装备尺码
	 */
	private String size;
	/**
	 * 1:alipay 2：银联 3:weixin
	 */
	private String payType;
	/**
	 * 001:待支付 002:已支付（报名成功；未领取） 003：待确认 004：已确认 005：已领取
	 */
	private String status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 支付成功时间
	 */
	private Date payTime;
	/**
	 * 发货时间
	 */
	private Date deliveryTime;
	/**
	 * 申请领取包裹时间
	 */
	private Date applyTime;
	/**
	 * 回访成功时间
	 */
	private Date visiteTime;
	/**
	 * 收货人邮编
	 */
	private String zipCode;
	/**
	 * 物流公司名称
	 */
	private String logisticsName;
	/**
	 * 物流编号
	 */
	private String logisticsNo;
	/**
	 * 活动类型（扩展用）1 fashion run;
	 */
	private String fashionType;
	/**
	 * 支付金额
	 */
	private BigDecimal  payAmount;
	/**
	 * 装备金额
	 */
	private BigDecimal packAmount;
	/**
	 * 交易号
	 */
	private String tradeNo;
	/**
	 * 乡镇
	 */
	private String town;
	/**
	 * 团体标识 1美瑞克斯 2黎明脚步 3光明乐跑 4妈妈Run 5Runningcat 6酷跑中国 7益跑网 8爱败妈妈 9大红包
	 */
	private String teamFlag;
	/**
	 * 邀请码
	 */
	private String InviteCode;
	/**
	 * 所属公司
	 */
	private String company; 
	/*public FashionOrder(Long id, String orderId, Long packId, String name,
			String sex, Date birthday, String phone, String email,
			String province, String city, String addr, String pid,
			String contacts, String contactsPhone, String size, String payType,
			String status, Date createTime, Date payTime, Date deliveryTime,
			Date applyTime, Date visiteTime, String zipCode,
			String logisticsName, String logisticsNo, String fashionType,
			BigDecimal pay_amount) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.packId = packId;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.province = province;
		this.city = city;
		this.addr = addr;
		this.pid = pid;
		this.contacts = contacts;
		this.contactsPhone = contactsPhone;
		this.size = size;
		this.payType = payType;
		this.status = status;
		this.createTime = createTime;
		this.payTime = payTime;
		this.deliveryTime = deliveryTime;
		this.applyTime = applyTime;
		this.visiteTime = visiteTime;
		this.zipCode = zipCode;
		this.logisticsName = logisticsName;
		this.logisticsNo = logisticsNo;
		this.fashionType = fashionType;
		this.pay_amount = pay_amount;
	}*/

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "order_id", length = 50)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "pack_id")
	public Long getPackId() {
		return packId;
	}

	public void setPackId(Long packId) {
		this.packId = packId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sex", length = 50)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birthday", length = 19)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "phone", length = 50)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "province", length = 50)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	@Column(name = "area", length = 50)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "addr", length = 200)
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(name = "pid", length = 50)
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Column(name = "contacts", length = 50)
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	@Column(name = "contacts_phone", length = 50)
	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	@Column(name = "size", length = 50)
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "pay_type", length = 50)
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Column(name = "status", length = 50)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pay_time", length = 19)
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delivery_time", length = 19)
	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "apply_time", length = 19)
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "visite_time", length = 19)
	public Date getVisiteTime() {
		return visiteTime;
	}

	public void setVisiteTime(Date visiteTime) {
		this.visiteTime = visiteTime;
	}

	@Column(name = "zip_code", length = 50)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "logistics_name", length = 50)
	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	@Column(name = "logistics_no", length = 50)
	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	@Column(name = "fashion_type", length = 50)
	public String getFashionType() {
		return fashionType;
	}

	public void setFashionType(String fashionType) {
		this.fashionType = fashionType;
	}
	@Column(name="pay_amount",precision=12,scale=2)  
	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	@Column(name="pack_amount",precision=12,scale=2)  
	public BigDecimal getPackAmount() {
		return packAmount;
	}

	public void setPackAmount(BigDecimal packAmount) {
		this.packAmount = packAmount;
		
	}
	@Column(name="trade_no", length = 100)  
	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	@Column(name="town", length = 50)  
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
	@Column(name="team_Flag", length = 50)  
	public String getTeamFlag() {
		return teamFlag;
	}

	public void setTeamFlag(String teamFlag) {
		this.teamFlag = teamFlag;
	}
	@Column(name="invite_code", length = 50)  
	public String getInviteCode() {
		return InviteCode;
	}

	public void setInviteCode(String inviteCode) {
		InviteCode = inviteCode;
	}
	@Column(name="company", length = 100)  
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
