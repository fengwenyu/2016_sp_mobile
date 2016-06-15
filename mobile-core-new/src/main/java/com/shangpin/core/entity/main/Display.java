package com.shangpin.core.entity.main;

public enum Display {
    NO(0), YES(1);
    public Integer value;

    public Integer getValue() {
        return value;
    }

    Display(Integer value) {
        this.value = value;
    }
}
