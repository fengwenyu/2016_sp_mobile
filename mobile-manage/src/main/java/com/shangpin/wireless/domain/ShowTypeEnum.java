package com.shangpin.wireless.domain;

public enum ShowTypeEnum {
	NATIVE(0), READONLY(1),HTML(2);
	public int value;

	public int getValue() {
		return value;
	}

	ShowTypeEnum(int value) {
		this.value = value;
	}
	 public static ShowTypeEnum getValue(String value) {

	        if (value.equals("NATIVE")) return ShowTypeEnum.NATIVE;
	        else if(value.equals("READONLY")) return ShowTypeEnum.READONLY;
	        else if(value.equals("HTML")) return ShowTypeEnum.HTML;
	        else return null;
	    }
}
