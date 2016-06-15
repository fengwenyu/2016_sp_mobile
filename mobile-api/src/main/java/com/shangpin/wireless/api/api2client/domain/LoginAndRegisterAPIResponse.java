package com.shangpin.wireless.api.api2client.domain;

import java.util.List;

import net.sf.json.JSONObject;

import com.shangpin.biz.bo.AttributeArr;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBaseNew;
import com.shangpin.wireless.api.api2server.domain.LoginAndRegisterServerResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.StringUtil;

/**
 * 用于API接口的返回数据
 * 
 * @Author: zhouyu
 * @CreateDate: 2012-09-04
 */
public class LoginAndRegisterAPIResponse {

	private String code;
	private String msg;
	private String userid;
	private String gender;
	private String sessionid;
	private String nopaycount;
	private String cartcount;
	private String isopen;
	private String msgtype;
	private String name;
	private String email;
	private String level; 
	private String priceindex;
	private String regOrigin;
	private String regTime;
	private String invitefriendcode;
	private String mobile;
	private String lv;
	
	//2.9.7版本新添加属性开始
		private String bindPhone;//0未绑定手机号   1绑定了手机号 
		private String bindGiftPassword;//0:未设置礼品卡密码 1：绑定了礼品卡密码
		private String icon;//头像url
		private String nickName;//昵称
	    private String birthday;//生日 
	    private List<AttributeArr> userRight;//用户会员权益
	    private String bindBirthday;//是否绑定了生日  0未绑定   1绑定了
	//2.9.7版本新添加属性结束
	    
    public String getBindBirthday() {
		return bindBirthday;
	}

	public void setBindBirthday(String bindBirthday) {
		this.bindBirthday = bindBirthday;
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

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	/**
	 * 返给客户端的json数据
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-09-04
	 * @param
	 * @Return
	 */
/*	public String obj2Json() {
		JSONObject obj = new JSONObject();
		obj.put("code", code);
		obj.put("msg", msg);
		JSONObject content = new JSONObject();
		if (Constants.SUCCESS.equals(code)) {
			content.put("userid", userid);
			content.put("gender", gender);
			content.put("sessionid", sessionid);
			if(StringUtil.isNotEmpty(mobile)) {
				content.put("nopaycount", nopaycount);
			}
			if(StringUtil.isNotEmpty(mobile)) {
				content.put("cartcount", cartcount);
			}
			if(StringUtil.isNotEmpty(isopen,msgtype)) {
				content.put("isopen", isopen);
				content.put("msgtype", msgtype);
			}
			if(StringUtil.isNotEmpty(priceindex)) {
				content.put("priceindex", priceindex);
			}
			content.put("name", name);
			content.put("email", email);
			content.put("level", level);
			content.put("regOrigin", regOrigin);
			content.put("regTime", regTime);
			if(StringUtil.isNotEmpty(mobile)) {
				content.put("mobile", mobile);
			}
			if(StringUtil.isNotEmpty(lv)) {
				content.put("lv", lv);
			}
			if(StringUtil.isNotEmpty(invitefriendcode)) {
				content.put("invitefriendcode", invitefriendcode);
			}
		}
		obj.put("content", content);
		return obj.toString();
	}*/

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

	public String getIsopen() {
		return isopen;
	}

	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
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

	public String getPriceindex() {
		return priceindex;
	}

	public void setPriceindex(String priceindex) {
		this.priceindex = priceindex;
	}

	public String getRegOrigin() {
		return regOrigin;
	}

	public void setRegOrigin(String regOrigin) {
		this.regOrigin = regOrigin;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public String getInvitefriendcode() {
		return invitefriendcode;
	}

	public void setInvitefriendcode(String invitefriendcode) {
		this.invitefriendcode = invitefriendcode;
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

	public String getBindPhone() {
		return bindPhone;
	}

	public void setBindPhone(String bindPhone) {
		this.bindPhone = bindPhone;
	}

	public String getBindGiftPassword() {
		return bindGiftPassword;
	}

	public void setBindGiftPassword(String bindGiftPassword) {
		this.bindGiftPassword = bindGiftPassword;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public List<AttributeArr> getUserRight() {
		return userRight;
	}

	public void setUserRight(List<AttributeArr> userRight) {
		this.userRight = userRight;
	}
	
	/**
	 * 返回给客户端的数据
	 * @return
	 */
	public String obj2Json() {
		JSONObject obj = new JSONObject();
		obj.put("code", code);
		obj.put("msg", msg);
		JSONObject content = new JSONObject();
		if (Constants.SUCCESS.equals(code)) {
			content.put("userid", userid);
			content.put("gender", gender);
			content.put("sessionid", sessionid);
			if(StringUtil.isNotEmpty(mobile)) {
				content.put("nopaycount", nopaycount);
			}
			if(StringUtil.isNotEmpty(mobile)) {
				content.put("cartcount", cartcount);
			}
			if(StringUtil.isNotEmpty(isopen,msgtype)) {
				content.put("isopen", isopen);
				content.put("msgtype", msgtype);
			}
			if(StringUtil.isNotEmpty(priceindex)) {
				content.put("priceindex", priceindex);
			}
			content.put("name", name);
			content.put("email", email);
			content.put("level", level);
			content.put("regOrigin", regOrigin);
			content.put("regTime", regTime);
			if(StringUtil.isNotEmpty(mobile)) {
				content.put("mobile", mobile);
			}
			if(StringUtil.isNotEmpty(lv)) {
				content.put("lv", lv);
			}
			if(StringUtil.isNotEmpty(invitefriendcode)) {
				content.put("invitefriendcode", invitefriendcode);
			}
			content.put("bindPhone", bindPhone);
			content.put("bindGiftPassword", bindGiftPassword);
			content.put("icon", icon);
			content.put("nickName", nickName);
			content.put("birthday", birthday);
			content.put("userRight", userRight);
		}
		obj.put("content", content);
		return obj.toString();
	}
}
