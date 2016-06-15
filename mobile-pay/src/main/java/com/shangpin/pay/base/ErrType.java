package com.shangpin.pay.base;

/**
 * 异常code定义
 * 
 * @date 2014.11.07
 * @author 李灵
 * 
 */
public enum ErrType {

	// 系统错误
	SYSTEM_ERROR("0000"),
	// 缺少必要的参数，检查非空参数是否已传递
	MISS_PARAMETER("0001"),
	// 准备direct服务的参数时异常
	ILLEGAL_DIRECTPARAMETER("0002"),
	// 签名错误，请检查参数是否符合签名规范
	ILLEGAL_SIGN("0003"),
	// 解析返回结果异常
	RESRESULT_ERROR("0004"),
	// 服务接口错误，请检查SERVICE是否传递正确
	SERVICE_ERROR("0005"),
	// 不合法的Xml文件
	ILLEGAL_XML("0006"),
	// 授权签名错误，请检查参数是否符合签名规范
	ILLEGAL_AUTHSIGN("0007"),
	// 生成交易流水号失败，请检查请求数据
	TN_ERROR("0008"),
	// 生成请求链接错误
	ILLEGAL_REQURL("0009"),
	// 生成请求链接错误
	ILLEGAL_REQDATA("0010"),
	// 获取callback通知签名失败
	MISS_CALLBACKRAMETER("0011"),
	// callback通知验签异常
	CALLBACKSIFNCHECK_ERROR("0012"),
	// NOTIFY解析待验签数据失败
	NOTIFYVERIFYDATA_EEROR("0013"),
	// NOTIFY通知验签异常
	NOTIFYSIFNCHECK_ERROR("0014");
	private String errorCode;

	ErrType(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}
}
