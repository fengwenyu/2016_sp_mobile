package com.shangpin.mobileShangpin.platform.vo;

/**
 * 上一次支付方式的对象
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-06-24
 */
public class LastPaymentVO {
    private String mainPayCode;//主支付编号
    private String subPayCode;//子支付编号
    public String getMainPayCode() {
        return mainPayCode;
    }
    public void setMainPayCode(String mainPayCode) {
        this.mainPayCode = mainPayCode;
    }
    public String getSubPayCode() {
        return subPayCode;
    }
    public void setSubPayCode(String subPayCode) {
        this.subPayCode = subPayCode;
    }
    
}
