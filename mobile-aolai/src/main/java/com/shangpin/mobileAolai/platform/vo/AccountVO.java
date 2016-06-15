package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;

import net.sf.json.JSONObject;

import com.shangpin.mobileAolai.common.util.Constants;

/**
 * 用于封装主站登录和注册接口的返回数据
 * 
 * @Author zhouyu
 * @CreateDate 2012-10-29
 */
public class AccountVO implements Serializable{
    
    private static final long serialVersionUID = 4131941689037474703L;
    
    private String code;
	private String msg;
	private String userid;
	private String gender;
	private String nopaycount; // 未支付订单数
	private String cartcount;// 购物车中的商品数量
	private String name;// 用户昵称
	private String email;
	private String level;
	private String regTime;// 注册时间
	private String regOrigin;// 注册来源
	/**手机号*/
	private String mobileNumber;
    private String mobile;
    private String lv;

	/**
	 * 解析主站返回的json数据
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-29
	 * @param jsonStr
	 *            主站返回的json数据
	 * @Return
	 */
	public AccountVO json2Obj(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(code)) {
			obj = JSONObject.fromObject(obj.getJSONObject("content"));
			this.setUserid(obj.getString("userid"));
			this.setGender(obj.getString("gender"));
			this.setNopaycount(obj.getString("nopaycount"));
			this.setCartcount(obj.getString("cartcount"));
			this.setLevel(obj.getString("level"));
			this.setEmail(obj.getString("email"));
			this.setName(obj.getString("name"));
			this.setRegTime(obj.getString("regTime"));
			this.setRegOrigin(obj.getString("regOrigin"));
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNopaycount() {
		return nopaycount;
	}

	public void setNopaycount(String nopaycount) {
		this.nopaycount = nopaycount;
	}

	public String getCartcount() {
		return cartcount;
	}

	public void setCartcount(String cartcount) {
		this.cartcount = cartcount;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public String getRegOrigin() {
		return regOrigin;
	}

	public void setRegOrigin(String regOrigin) {
		this.regOrigin = regOrigin;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }
	
}
