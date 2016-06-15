package com.shangpin.wireless.api.api2client.domain;

import com.shangpin.wireless.api.util.StringUtil;

import net.sf.json.JSONObject;


public class GetAlipayUserInfoAPIResponse {
	
	private String code;
	private String msg;
	private String aliUserId;	//	支付宝userid
	private String gender;	//	性别（F：女性；M：男性）
	private String status;	//	用户状态（Q/T/B/W）。	Q代表快速注册用户	T代表已认证用户	B代表被冻结账户	W代表已注册，未激活的账户
	private String name;	//	用户的真实姓名
	private String email;	//	用户支付宝账号绑定的邮箱地址
	private String mobile;	//	手机号码
	private String deliverMobile;	//	收货地址的联系人移动电话
	private String deliverFullname;	//	收货人全称
	private String province;	//	省份名称
	private String city;	//	市名称
	private String area;	//	区县名称
	private String address;	//	详细地址
	private String zip;	//	邮政编码
	
	public GetAlipayUserInfoAPIResponse json2Obj (String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		if (obj.has("alipay_user_userinfo_share_response")) {
			JSONObject resp = obj.getJSONObject("alipay_user_userinfo_share_response");
			setAliUserId(resp.getString("user_id"));
			if (resp.has("gender")) setGender(resp.getString("gender"));
			if (resp.has("user_status")) setStatus(resp.getString("user_status"));
			if (resp.has("real_name")) setName(resp.getString("real_name"));
			if (resp.has("email")) setEmail(resp.getString("email"));
			if (resp.has("mobile")) setMobile(resp.getString("mobile"));
			if (resp.has("deliver_mobile")) setDeliverMobile(resp.getString("deliver_mobile"));
			if (resp.has("deliver_fullname")) setDeliverFullname(resp.getString("deliver_fullname"));
			if (resp.has("province")) setProvince(resp.getString("province"));
			if (resp.has("city")) setCity(resp.getString("city"));
			if (resp.has("area")) setArea(resp.getString("area"));
			if (resp.has("address")) setAddress(resp.getString("address"));
			if (resp.has("zip")) setZip(resp.getString("zip"));
			
		} else if (obj.has("error_response")) {
			JSONObject error = obj.getJSONObject("error_response");
			setCode(error.getString("code"));
			setMsg(error.getString("msg") + "\n" + error.getString("sub_msg") );
		}
		
		return this;
	}
	
	public GetAlipayUserInfoAPIResponse xml2Obj (String xmlStr) {
		if ("T".equals(StringUtil.getTagContent(xmlStr, "<is_success>", "</is_success>"))) {
			setAliUserId(StringUtil.getTagContent(xmlStr, "<user_id>", "</user_id>"));
			setGender(StringUtil.getTagContent(xmlStr, "<gender>", "</gender>"));
			setStatus(StringUtil.getTagContent(xmlStr, "<user_status>", "</user_status>"));
			setName(StringUtil.getTagContent(xmlStr, "<real_name>", "</real_name>"));
			setEmail(StringUtil.getTagContent(xmlStr, "<email>", "</email>"));
			setMobile(StringUtil.getTagContent(xmlStr, "<mobile>", "</mobile>"));
			setDeliverMobile(StringUtil.getTagContent(xmlStr, "<deliver_mobile>", "</deliver_mobile>"));
			setDeliverFullname(StringUtil.getTagContent(xmlStr, "<deliver_fullname>", "</deliver_fullname>"));
			setProvince(StringUtil.getTagContent(xmlStr, "<province>", "</province>"));
			setCity(StringUtil.getTagContent(xmlStr, "<city>", "</city>"));
			setArea(StringUtil.getTagContent(xmlStr, "<area>", "</area>"));
			setAddress(StringUtil.getTagContent(xmlStr, "<address>", "</address>"));
			setZip(StringUtil.getTagContent(xmlStr, "<zip>", "</zip>"));

		} else {
			setCode("1");
			setMsg(StringUtil.getTagContent(xmlStr, "<error>", "</error>"));
		}
		
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAliUserId() {
		return aliUserId;
	}

	public void setAliUserId(String aliUserId) {
		this.aliUserId = aliUserId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDeliverMobile() {
		return deliverMobile;
	}

	public void setDeliverMobile(String deliverMobile) {
		this.deliverMobile = deliverMobile;
	}

	public String getDeliverFullname() {
		return deliverFullname;
	}

	public void setDeliverFullname(String deliverFullname) {
		this.deliverFullname = deliverFullname;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}
