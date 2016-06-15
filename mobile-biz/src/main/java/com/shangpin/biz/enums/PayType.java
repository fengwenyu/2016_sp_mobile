package com.shangpin.biz.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <br/>
 * Date: 2016/4/22<br/>
 *
 * @author ZRS
 * @since JDK7
 */
public enum PayType {

    WEIXINAPP   ("1", "0", "1", "0", null, null, "微信支付", "27", "57"),
    ALIAPP      ("1", "0", "1", "0", null, null, "支付宝", "20", "38"),
    UNAPP       ("1", "0", "1", "0", null, null, "银联支付", "19", "36"),

    CASH        ("1", "0", "2", "0", null, null, "货到付款现金", "2", "68"),
    POS         ("1", "0", "2", "0", null, null, "货到付款POS机", "2", "67"),

    WEIXINAPPSEA("2", "0", "1", "0", null, null, "微信支付", "32", "110"),
    ALIAPPSEA   ("2", "0", "1", "0", null, null, "支付宝钱包支付", "30", "107"),

    ALIFZAPP    ("3", "0", "1", "0", null, null, "支付宝", "30", "120"),
    ;

    //1国内 2海外 3分账
    final private String type;
    //支持平台:101:安卓, 102:IOS 0:全部
    final private String platform;
    //1 线上支付 2线下支付
    final private String wey;
    //渠道号
    final private String channel;
    //开始使用版本(含)
    final private String startVersion;
    //停止使用版本(不含)
    final private String endVersion;
    //支付名称
    final private String name;
    //支付id
    final private String id;
    //子的支付id
    final private String subId;

    PayType(String type, String platform, String wey, String channel, String startVersion, String endVersion, String name, String id, String subId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.platform = platform;
        this.wey = wey;
        this.channel = channel;
        this.startVersion = startVersion;
        this.endVersion = endVersion;
        this.subId = subId;
    }



    /**
     * 根据子订单号获取PayType实体
     * @param subId
     * @return
     */
    public static PayType getPayTypeBySubId(String subId){
        if(StringUtils.isBlank(subId)){
            return null;
        }
        PayType[] values = PayType.values();
        for (PayType value : values) {
            if(value.getSubId().equals(subId)){
                return value;
            }
        }
        return null;
    }

    /**
     * 根据子订单号获取主订单号
     * @param subId
     * @return
     */
    public static String getIdBySubId(String subId){
        PayType payType = getPayTypeBySubId(subId);
        if(payType == null){
            return null;
        }
        return payType.getId();
    }


    public String getType() {
        return type;
    }

    public String getPlatform() {
        return platform;
    }

    public String getWey() {
        return wey;
    }

    public String getChannel() {
        return channel;
    }

    public String getStartVersion() {
        return startVersion;
    }

    public String getEndVersion() {
        return endVersion;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getSubId() {
        return subId;
    }
}
