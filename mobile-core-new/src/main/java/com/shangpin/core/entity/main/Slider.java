package com.shangpin.core.entity.main;

public enum Slider {
    NO(0), YES(1);
    public int value;

    public int getValue() {
        return value;
    }

    Slider(int value) {
        this.value = value;
    }
}
