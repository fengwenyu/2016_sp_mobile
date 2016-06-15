package com.shangpin.biz.bo;

import java.io.Serializable;

public class BrandActivityCoupon implements Serializable{
	private static final long serialVersionUID = 1L;
	private String pic;
	private String code;
	private String type;
	private String isValid;
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

}
