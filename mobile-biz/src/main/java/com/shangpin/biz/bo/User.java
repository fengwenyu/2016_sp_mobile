package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

	private static final long serialVersionUID = 4305374828766081854L;

	public String userid;
	public String gender;//性别0女 1男
	public String name;
	public String mobile;
	public String email;
	public String regTime;//注册时间
	public String regOrigin;
	public String invitefriendcode;
	public String cartcount;
	public String nopaycount;
	public String level;//等级：汉字
	public String lv;//等级：数字
	public String priceindex;
	public String sessionid;
	
	//2.9.7版本新添加属性开始
	private String bindPhone="";//0未绑定手机号   1绑定了手机号 
	private String bindGiftPassword="";//0:未设置礼品卡密码 1：绑定了礼品卡密码
	private String icon="";//头像url
	private String nickName="";//昵称
    private String birthday="";//生日 
    private List<AttributeArr> userRight=new ArrayList<AttributeArr>();//用户会员权益
    private String bindBirthday="";//是否绑定了生日  0未绑定   1绑定了 
	//2.9.7版本新添加属性结束
    
    /*//2.9.9版本新添加属性开始
    private String unCommentAmount;//未评价数量
    //2.9.9版本新添加属性开始
*/    
    
	public String getUserid() {
		return userid;
	}

	public String getBindBirthday() {
		return bindBirthday;
	}

	public void setBindBirthday(String bindBirthday) {
		this.bindBirthday = bindBirthday;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getInvitefriendcode() {
		return invitefriendcode;
	}

	public void setInvitefriendcode(String invitefriendcode) {
		this.invitefriendcode = invitefriendcode;
	}

	public String getCartcount() {
		return cartcount;
	}

	public void setCartcount(String cartcount) {
		this.cartcount = cartcount;
	}

	public String getNopaycount() {
		return nopaycount;
	}

	public void setNopaycount(String nopaycount) {
		this.nopaycount = nopaycount;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLv() {
		return lv;
	}

	public void setLv(String lv) {
		this.lv = lv;
	}

	public String getPriceindex() {
		return priceindex;
	}

	public void setPriceindex(String priceindex) {
		this.priceindex = priceindex;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
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
	
	
}
