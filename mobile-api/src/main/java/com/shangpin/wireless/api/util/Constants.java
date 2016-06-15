package com.shangpin.wireless.api.util;

public class Constants {

    public static final String CHANNEL_PARAM_NAME = "ch";
    public static final String PRODUCT_PARAM_NAME = "P";

    // 产品号：101为奥莱；102为尚品
    public static final String SP_PRODUCT_NAME = "102";
    public static final String AL_PRODUCT_NAME = "101";

    /** 尚品wap默认的渠道号，芭莎渠道号为：31 */
    public static final String SP_WAP_DEFAULT_CHANNELNO = "4";
    // 奥莱渠道号
    public static final String AOLAI_WAP_DEFAULT_CHANNELNO = "3";
    
    public static final String RECEIVE_MSG = WeixinPacketPropertyUtil.getString("receive_msg");
    
    public static final String FIRST_FOCUS_MSG = WeixinPacketPropertyUtil.getString("first_focus_msg");

}
