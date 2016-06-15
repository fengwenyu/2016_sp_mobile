package com.shangpin.wechat.bo.base;

import java.io.Serializable;

public class CardInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String card_id;// 	卡券ID
	private String begin_time;// 	起始使用时间
	private String end_time;// 	结束时间
//	private String can_consume;// 	是否可以核销，true为可以核销，false为不可核销 
//	private String user_card_status;//当前code对应卡券的状态， NORMAL 正常 CONSUMED 已核销 EXPIRE 已过期 GIFTING 转赠中 GIFT_TIMEOUT 转赠超时 DELETE 已删除，UNAVAILABLE 已失效 code未被添加或被转赠领取的情况则统一报错：invalid serial code 
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	@Override
	public String toString() {

		return "card_id:" + card_id + ",begin_time:" + begin_time
				+ ",end_time:" + end_time ;
	}
	
	
}