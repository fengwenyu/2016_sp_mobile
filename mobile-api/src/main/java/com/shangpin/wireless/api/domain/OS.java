package com.shangpin.wireless.api.domain;

/**
 * 客户端版本
 * @author Administrator
 *
 */
public enum OS {
	IPHONE4START("iphone4Start"),
	IPHONE5START("iphone5Start"),
	IPADSTART("ipadStart"),
	ANDRIODSTART("androidStart"),
	IPHONESHARE("iphoneFindShare"),
	ANDRIODSHARE("androidFindShare");
	private String code;
	private OS(String code){
		this.code = code;
	}
	@Override
	public String toString() {
		return code;
	}
	
}