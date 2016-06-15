package com.shangpin.core.entity.main;

public enum AppType {
    AOLAI(0), SHANGPIN(1);
    public int value;

    public int getValue() {
        return value;
    }

    AppType(int value) {
        this.value = value;
    }
}
