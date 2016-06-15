package com.shangpin.wireless.domain;

public enum DisplayEnum {
	NO(0),
	YES(1);
    public int value;

    public int getValue() {
        return value;
    }

    DisplayEnum(int value) {
        this.value = value;
    }
}
