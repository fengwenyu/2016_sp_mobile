package com.shangpin.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * @author qinyingchun
 *微信红包记录实体类
 */
@Entity
@Table(name = "weixin_packet_record")
public class WeiXinPacketRecord implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String sendOpenId;
	private String sendLoginName;
	private String sendNickName;
	private String receiveOpenId;
	private String receiveLoginName;
	private String receiveNickName;
	private double packetMoney;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String viewTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "send_login_name")
	public String getSendLoginName() {
		return sendLoginName;
	}
	public void setSendLoginName(String sendLoginName) {
		this.sendLoginName = sendLoginName;
	}
	@Column(name = "send_nick_name")
	public String getSendNickName() {
		return sendNickName;
	}
	public void setSendNickName(String sendNickName) {
		this.sendNickName = sendNickName;
	}
	@Column(name = "receive_login_name")
	public String getReceiveLoginName() {
		return receiveLoginName;
	}
	public void setReceiveLoginName(String receiveLoginName) {
		this.receiveLoginName = receiveLoginName;
	}
	@Column(name = "receive_nick_name")
	public String getReceiveNickName() {
		return receiveNickName;
	}
	public void setReceiveNickName(String receiveNickName) {
		this.receiveNickName = receiveNickName;
	}
	@Column(name = "packet_money")
	public double getPacketMoney() {
		return packetMoney;
	}
	public void setPacketMoney(double packetMoney) {
		this.packetMoney = packetMoney;
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
	@Transient
	public String getViewTime() {
		return viewTime;
	}
	public void setViewTime(String viewTime) {
		this.viewTime = viewTime;
	}
	@Column(name = "send_open_id")
	public String getSendOpenId() {
		return sendOpenId;
	}
	public void setSendOpenId(String sendOpenId) {
		this.sendOpenId = sendOpenId;
	}
	@Column(name = "receive_open_id")
	public String getReceiveOpenId() {
		return receiveOpenId;
	}
	public void setReceiveOpenId(String receiveOpenId) {
		this.receiveOpenId = receiveOpenId;
	}			    
	
	
}
