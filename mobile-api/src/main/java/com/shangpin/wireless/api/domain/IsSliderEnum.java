package com.shangpin.wireless.api.domain;



public enum IsSliderEnum {
	NO(0),
	YES(1);
    public int value;

    public int getValue() {
        return value;
    }

    IsSliderEnum(int value) {
        this.value = value;
    }
}
