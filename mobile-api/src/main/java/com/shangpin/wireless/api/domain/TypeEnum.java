package com.shangpin.wireless.api.domain;

public enum TypeEnum {
	//图文
	IMAGETEXT,
	//活动
	ACTIVITY, 
	//活动静态页
	STATICATC;
	public static TypeEnum getValue(String value) {
		if (value.equals("activity"))
			return TypeEnum.ACTIVITY;
		else if (value.equals("imgtext"))
			return TypeEnum.IMAGETEXT;
		else if (value.equals("staticatc"))
			return TypeEnum.STATICATC;
		else
			return null;
	}
}
