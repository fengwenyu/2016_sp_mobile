package com.shangpin.biz.utils;
import java.io.Serializable;

public class Header implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4643833958619472930L;
	private String imei;
	private String os;// 手机平台:ios、android
	private String osv;// 系统版本
	private String productNum;// 产品号
	private String channelNum;// 渠道号
	private String ver;// 版本号
	private String apn;// 接入点
	private String wh;// 屏幕宽高640x960
	private String mt;// 手机类型（gsm，cdma）
	private String model; // 手机型号(iPhone 4S)
	private String operator;// 运营商
	private String ua;// UA(wap网站所用字段)

	public Header(String str, String type) {
		if ("iphone".equals(type)) {
			String[] split = str.split(",");
			this.imei = split[0] == null || "".equals(split[0]) || "#".equals(split[0]) ? null : split[0];
			this.os = split[1] == null || "".equals(split[1]) || "#".equals(split[1]) ? null : split[1];
			this.osv = split[2] == null || "".equals(split[2]) || "#".equals(split[2]) ? null : split[2];
			this.productNum = split[3] == null || "".equals(split[3]) || "#".equals(split[3]) ? null : split[3];
			this.channelNum = split[4] == null || "".equals(split[4]) || "#".equals(split[4]) ? null : split[4];
			this.ver = split[5] == null || "".equals(split[5]) || "#".equals(split[5]) ? null : split[5];
			this.apn = split[6] == null || "".equals(split[6]) || "#".equals(split[6]) ? null : split[6];
			this.wh = split[7] == null || "".equals(split[7]) || "#".equals(split[7]) ? null : split[7];
			this.mt = split[8] == null || "".equals(split[8]) || "#".equals(split[8]) ? null : split[8];
			this.model = split[9] == null || "".equals(split[9]) || "#".equals(split[9]) ? null : split[9];
			if (split.length > 10)
				this.operator = split[10] == null || "".equals(split[10]) || "#".equals(split[10]) ? null : split[10];
		} else if ("wap".equals(type)) {
			String[] split = str.split("%");
			this.imei = split[0] == null || "".equals(split[0]) || "#".equals(split[0]) ? null : split[0];
			this.os = split[1] == null || "".equals(split[1]) || "#".equals(split[1]) ? null : split[1];
			this.osv = split[2] == null || "".equals(split[2]) || "#".equals(split[2]) ? null : split[2];
			this.productNum = split[3] == null || "".equals(split[3]) || "#".equals(split[3]) ? null : split[3];
			this.channelNum = split[4] == null || "".equals(split[4]) || "#".equals(split[4]) ? null : split[4];
			this.ver = split[5] == null || "".equals(split[5]) || "#".equals(split[5]) ? null : split[5];
			this.apn = split[6] == null || "".equals(split[6]) || "#".equals(split[6]) ? null : split[6];
			this.wh = split[7] == null || "".equals(split[7]) || "#".equals(split[7]) ? null : split[7];
			this.mt = split[8] == null || "".equals(split[8]) || "#".equals(split[8]) ? null : split[8];
			this.model = split[9] == null || "".equals(split[9]) || "#".equals(split[9]) ? null : split[9];
			this.operator = split[10] == null || "".equals(split[10]) || "#".equals(split[10]) ? null : split[10];
			this.ua = split[11] == null || "".equals(split[11]) || "#".equals(split[11]) ? null : split[11];
		}
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOsv() {
		return osv;
	}

	public void setOsv(String osv) {
		this.osv = osv;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getChannelNum() {
		return channelNum;
	}

	public void setChannelNum(String channelNum) {
		this.channelNum = channelNum;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getApn() {
		return apn;
	}

	public void setApn(String apn) {
		this.apn = apn;
	}

	public String getWh() {
		return wh;
	}

	public void setWh(String wh) {
		this.wh = wh;
	}

	public String getMt() {
		return mt;
	}

	public void setMt(String mt) {
		this.mt = mt;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}
}
