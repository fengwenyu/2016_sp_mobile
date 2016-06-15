package com.shangpin.wireless.api.domain;

/**
 * 商品缓存
 * 
 * @Author: zhouyu
 * @CreateDate: 2013-05-06
 */
public class MerchandiseCache implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String key;
	private String highData;
	private String lowData;
	private long timestamp;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getLowData() {
		return lowData;
	}

	public void setLowData(String lowData) {
		this.lowData = lowData;
	}

	public String getHighData() {
		return highData;
	}

	public void setHighData(String highData) {
		this.highData = highData;
	}
}
