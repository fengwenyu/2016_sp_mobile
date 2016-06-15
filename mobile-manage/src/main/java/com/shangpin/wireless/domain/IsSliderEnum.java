package com.shangpin.wireless.domain;

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
