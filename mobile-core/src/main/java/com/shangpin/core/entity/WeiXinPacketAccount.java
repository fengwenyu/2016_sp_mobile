package com.shangpin.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author qinyingchun
 *微信红包微信账号实体类
 */
@Entity
@Table(name = "weixin_packet_account")
public class WeiXinPacketAccount implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String openId;
	private String loginName;
	private String nickName;
	private String headImgUrl;
	private String sharePicUrl;
	private String sex;
	private String country;
	private String province;
	private String city;
	private String language;
	private String unionid;
	private double shangpinPacket;
	private int receivePacketNum;
	private double receivePacketMoney;
	private int sendPacketNum;
	private double sendPacketMoney;
	private double balance;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "open_id")
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	@Column(name = "login_name")
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	@Column(name = "nick_name")
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Column(name = "head_img_url")
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	@Column(name = "receive_packet_num")
	public int getReceivePacketNum() {
		return receivePacketNum;
	}
	public void setReceivePacketNum(int receivePacketNum) {
		this.receivePacketNum = receivePacketNum;
	}
	@Column(name = "receive_packet_money")
	public double getReceivePacketMoney() {
		return receivePacketMoney;
	}
	public void setReceivePacketMoney(double receivePacketMoney) {
		this.receivePacketMoney = receivePacketMoney;
	}
	@Column(name = "send_packet_num")
	public int getSendPacketNum() {
		return sendPacketNum;
	}
	public void setSendPacketNum(int sendPacketNum) {
		this.sendPacketNum = sendPacketNum;
	}
	@Column(name = "send_packet_money")
	public double getSendPacketMoney() {
		return sendPacketMoney;
	}
	public void setSendPacketMoney(double sendPacketMoney) {
		this.sendPacketMoney = sendPacketMoney;
	}
	@Column(name = "balance")
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Column(name = "create_time")
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Column(name = "update_time")
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name = "sex")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name = "country")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Column(name = "province")
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "language")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Column(name = "unionid")
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	@Column(name = "shangpin_packet")
	public double getShangpinPacket() {
		return shangpinPacket;
	}
	public void setShangpinPacket(double shangpinPacket) {
		this.shangpinPacket = shangpinPacket;
	}
	@Column(name = "share_pic_url")
	public String getSharePicUrl() {
		return sharePicUrl;
	}
	public void setSharePicUrl(String sharePicUrl) {
		this.sharePicUrl = sharePicUrl;
	}
	@Override
	public String toString() {
		return "WeiXinPacketAccount [id=" + id + ", openId=" + openId
				+ ", loginName=" + loginName + ", nickName=" + nickName
				+ ", headImgUrl=" + headImgUrl + ", sharePicUrl=" + sharePicUrl
				+ ", sex=" + sex + ", country=" + country + ", province="
				+ province + ", city=" + city + ", language=" + language
				+ ", unionid=" + unionid + ", shangpinPacket=" + shangpinPacket
				+ ", receivePacketNum=" + receivePacketNum
				+ ", receivePacketMoney=" + receivePacketMoney
				+ ", sendPacketNum=" + sendPacketNum + ", sendPacketMoney="
				+ sendPacketMoney + ", balance=" + balance + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
}
