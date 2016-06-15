package com.shangpin.core.entity.main;

public enum Type {
    IMAGETEXT, ACTIVITY, STATICATC;
    public static Type getValue(String value) {

        if (value.equals("activity"))
            return Type.ACTIVITY;
        else if (value.equals("imgtext"))
            return Type.IMAGETEXT;
        else if (value.equals("staticatc"))
            return Type.STATICATC;
        else
            return null;
    }
}
