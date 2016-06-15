package com.shangpin.wireless.api.api2server.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

import com.shangpin.biz.bo.AttributeArr;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBaseNew;
import com.shangpin.wireless.api.domain.Constants;

/**
 * 用于封装主站接口的返回数据
 * 
 * @Author: zhouyu
 * @CreateDate: 2012-09-04
 */
public class LoginAndRegisterServerResponse {

	private String code;
	private String msg;
	private String userid;
	private String gender;
	private String nopaycount;
	private String cartcount;
	private String name;
	private String email;
	private String level;
	private String regTime;
	private String regOrigin;
	private String priceindex;
	
	/**手机号*/
	private String mobileNumber;
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
	    
	public String getCode() {
		return code;
	}

	public String getBindPhone() {
		return bindPhone;
	}
	

	public String getBindBirthday() {
		return bindBirthday;
	}

	public void setBindBirthday(String bindBirthday) {
		this.bindBirthday = bindBirthday;
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

	/**
	 * 解析主站返回的json数据
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-09-04
	 * @param jsonStr
	 *            主站返回的json数据
	 * @Return
	 */
	public LoginAndRegisterServerResponse json2Obj(String jsonStr, String nickname) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(code)) {
			obj = JSONObject.fromObject(obj.getJSONObject("content"));
			this.setUserid(obj.getString("userid"));
			this.setGender(obj.getString("gender"));
			if(obj.has("nopaycount")) {
				this.setNopaycount(obj.getString("nopaycount"));
			}
			if(obj.has("cartcount")) {
				this.setCartcount(obj.getString("cartcount"));
			}
			this.setLevel(obj.getString("level"));
			this.setEmail(obj.getString("email"));
			if (nickname == null) {
				this.setName("");
			} else {
				this.setName(obj.getString("name"));
			}
			this.setRegTime(obj.getString("regTime"));
			this.setRegOrigin(obj.getString("regOrigin"));
			if(obj.has("priceindex")) {
				this.setPriceindex(obj.getString("priceindex"));
			}
			if(obj.has("mobile")){
				this.setMobile(obj.getString("mobile"));
			}
			if(obj.has("lv")){
				this.setLv(obj.getString("lv"));
			}
			
			 
		}
		return this;
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

	public String getPriceindex() {
		return priceindex;
	}

	public void setPriceindex(String priceindex) {
		this.priceindex = priceindex;
	}
	
	/**
	 * 解析主站返回的json数据
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-09-04
	 * @param jsonStr
	 *            主站返回的json数据
	 * @Return
	 */
	public LoginAndRegisterServerResponse json2ObjNew(String jsonStr, String nickname) {
		ResultBaseNew resultBaseNew = ResultBaseNew.formatToPojo(jsonStr,User.class);
		if (Constants.SUCCESS.equals(resultBaseNew.getCode())){
			User user = (User) resultBaseNew.getContent();
			this.setCode(resultBaseNew.getCode());
			this.setMsg(resultBaseNew.getMsg());
			this.setUserid(user.getUserid());
			this.setGender(user.getGender());
			this.setNopaycount(user.getNopaycount());
			this.setCartcount(user.getCartcount());
			this.setLevel(user.getLevel());
			this.setEmail(user.getEmail());
			if (nickname == null) {
				this.setName("");
			} else {
				this.setName(user.getName());
			}
			this.setRegTime(user.getRegTime());
			this.setRegOrigin(user.getRegOrigin());
			this.setPriceindex(user.getPriceindex());
			this.setMobile(user.getMobile());
			this.setLv(user.getLv());
			this.setBindPhone(user.getBindPhone()==null?"":user.getBindPhone());
			this.setBindGiftPassword(user.getBindGiftPassword()==null?"":user.getBindGiftPassword());
			//增加默认头像
			Map<String, String> iconMap = new HashMap<String, String>();
			iconMap.put("普通会员", "http://pic6.shangpin.com/group1/M00/D7/05/rBQKaFaKCrKAE-1MAAATatXinY0856.png");
			iconMap.put("黄金会员", "http://pic6.shangpin.com/group1/M00/D7/05/rBQKaFaKCrCAR-m-AAAZs-ncDX8318.png");
			iconMap.put("白金会员", "http://pic5.shangpin.com/group1/M00/D7/05/rBQKaFaKCrOAZOYiAAAaEcJFeUk549.png");
			iconMap.put("钻石会员", "http://pic6.shangpin.com/group1/M00/D7/05/rBQKaFaKCrGAHGORAAAbrQ3CN4c900.png");
			String icon = user.getIcon();
			String keyLv = user.getLevel().trim();
			if(StringUtils.isBlank(icon)){
				icon = iconMap.get(keyLv);
				if(icon==null){
					icon="";
				}
			}
			this.setIcon(icon==null?"":icon);
			this.setNickName(user.getNickName()==null?"":user.getNickName());
			this.setBirthday(user.getBirthday()==null?"":user.getBirthday());
			this.setUserRight(user.getUserRight()==null?new ArrayList<AttributeArr>():user.getUserRight());
			this.setBindBirthday(user.getBindBirthday()==null?"":user.getBindBirthday());
			return this;
		/*JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(code)) {
			obj = JSONObject.fromObject(obj.getJSONObject("content"));
			this.setUserid(obj.getString("userid"));
			this.setGender(obj.getString("gender"));
			if(obj.has("nopaycount")) {
				this.setNopaycount(obj.getString("nopaycount"));
			}
			if(obj.has("cartcount")) {
				this.setCartcount(obj.getString("cartcount"));
			}
			this.setLevel(obj.getString("level"));
			this.setEmail(obj.getString("email"));
			if (nickname == null) {
				this.setName("");
			} else {
				this.setName(obj.getString("name"));
			}
			this.setRegTime(obj.getString("regTime"));
			this.setRegOrigin(obj.getString("regOrigin"));
			if(obj.has("priceindex")) {
				this.setPriceindex(obj.getString("priceindex"));
			}
			if(obj.has("mobile")){
				this.setMobile(obj.getString("mobile"));
			}
			if(obj.has("lv")){
				this.setLv(obj.getString("lv"));
			}
			if(obj.has("bindPhone")){
				this.setBindPhone(obj.getString("bindPhone"));
			}
			if(obj.has("bindGiftPassword")){
				this.setBindGiftPassword(obj.getString("bindGiftPassword"));
			}
			if(obj.has("icon")){
				this.setIcon(obj.getString("icon"));
			}
			if(obj.has("nickName")){
				this.setNickName(obj.getString("nickName"));
			}
			if(obj.has("birthday")){
				this.setBirthday(obj.getString("birthday"));
			}
			if(obj.has("userRight")){
				this.setUserRight(obj.getJSONObject(("userRight"));
			}
			*/ 
		}else if(Constants.FAILURE_CODE.equals(resultBaseNew.getCode())){
			this.setCode(resultBaseNew.getCode());
			this.setMsg(resultBaseNew.getMsg());
			return this;
		}
		return null;
	}
}
