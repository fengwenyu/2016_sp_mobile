package com.shangpin.wireless.api.domain;

import java.util.Date;

/**
 * 
 * IOS下载激活实体类
 * 
 * @author xupengcheng
 * 
 */
public class IOSDownloadActive {

	private Integer id;
	private String ifa;
	private String callbackUrl;
	private String mac;
	private String appType;// 应用类型 ： 奥莱、 尚品
	private Date downloadTime;// 下载时间
	private Date activeTime;// 激活时间
	private String isActive;//是否已经激活
	private String channel;// 下载渠道
	private String imei;// 设备唯一标识
	private String version;//应用版本号
	private String channelName;//渠道名称
	private String appid;//哇霸唯一标示
	private String ifas;//多个ifa
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}


	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIfa() {
		return ifa;
	}

	public void setIfa(String ifa) {
		this.ifa = ifa;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public Date getDownloadTime() {
		return downloadTime;
	}

	public void setDownloadTime(Date downloadTime) {
		this.downloadTime = downloadTime;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getIfas() {
        return ifas;
    }

    public void setIfas(String ifas) {
        this.ifas = ifas;
    }
	
	

}
