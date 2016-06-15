package com.shangpin.wireless.domain;

public enum TypeEnum {
	IMAGETEXT,
	ACTIVITY,
	STATICATC;
	 public static TypeEnum getValue(String value) {

	        if (value.equals("activity")) return TypeEnum.ACTIVITY;
	        else if (value.equals("imgtext")) return TypeEnum.IMAGETEXT;
	        else if(value.equals("staticatc")) return TypeEnum.STATICATC;
	        else return null;
	    }
}
