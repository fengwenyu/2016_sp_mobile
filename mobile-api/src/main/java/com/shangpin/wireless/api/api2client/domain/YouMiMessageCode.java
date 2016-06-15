package com.shangpin.wireless.api.api2client.domain;

/**
 * 有米反馈信息
 * 0 正常
-3106 参数无法解析，如果传来的参数格式不正确则返回该错误。
-3201 无法解析设备，回调参数不足或有误导致无法解析到对应的设备id
-2103 无法解析推广产品，回调参数不足或有误导致无法解析推广产品的产品id
 * @author xupengcheng
 *
 */
public enum YouMiMessageCode {

	Normal("0", "正常"), Error_3106("-3106", "参数无法解析"), Error_3201("-3201", "无法解析设备"), Error_2103("-2103", "无法解析推广产品");
	public String id, name;

	YouMiMessageCode(String id, String name) {
		this.id = id;
		this.name = name;
	}

}
