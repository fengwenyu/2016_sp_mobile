package com.shangpin.wireless.api.domain;


public enum ProductTypeEnum {
	SHANGPIN(0), AOLAI(1);
	public int value;

	public int getValue() {
		return value;
	}

	ProductTypeEnum(int value) {
		this.value = value;
	}
}
