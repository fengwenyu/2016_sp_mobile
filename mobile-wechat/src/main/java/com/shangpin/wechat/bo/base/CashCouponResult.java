package com.shangpin.wechat.bo.base;

public class CashCouponResult implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;
	
	private String return_code;//返回状态码
	private String return_msg;//返回信息
	private String appid;//公众账号ID
	private String mch_id;//商户号
	private String device_info;//设备号
	private String nonce_str;//随机字符串
	private String sign;//签名
	private String result_code;//业务结果
	
	/**
	 *  USER_AL_GET_COUPON 	你已领取过该代金券 
	 *	NETWORK ERROR 	网络环境不佳，请重试 
	 *	AL_STOCK_OVER 	活动已结束
	 *	STOCK_IS_NOT_VALID 	抱歉，该代金券已失效
	 */
	private String err_code;//错误代码
	private String err_code_des;//错误代码描述
	private String coupon_stock_id;//代金券批次id
	private String resp_count;//返回记录数
	private String success_count;//成功记录数
	private String failed_count;//失败记录数
	private String openid;//用户标识
	private String ret_code;//返回码
	private String coupon_id;//代金券id
	private String ret_msg;//返回信息
	private String sub_mch_id;
	
	public String getSub_mch_id() {
		return sub_mch_id;
	}
	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
	}
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getCoupon_stock_id() {
		return coupon_stock_id;
	}
	public void setCoupon_stock_id(String coupon_stock_id) {
		this.coupon_stock_id = coupon_stock_id;
	}
	public String getResp_count() {
		return resp_count;
	}
	public void setResp_count(String resp_count) {
		this.resp_count = resp_count;
	}
	public String getSuccess_count() {
		return success_count;
	}
	public void setSuccess_count(String success_count) {
		this.success_count = success_count;
	}
	public String getFailed_count() {
		return failed_count;
	}
	public void setFailed_count(String failed_count) {
		this.failed_count = failed_count;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getRet_code() {
		return ret_code;
	}
	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}
	public String getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getRet_msg() {
		return ret_msg;
	}
	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}

	
	
	
	
}
