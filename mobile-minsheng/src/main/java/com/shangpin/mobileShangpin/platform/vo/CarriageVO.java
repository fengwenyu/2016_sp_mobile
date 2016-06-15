package com.shangpin.mobileShangpin.platform.vo;

/**
 * 运费对象
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-06-24
 */
public class CarriageVO {

    private String amount;//运费金额
    private String reductionAmount;//减免金额
    private String desc;//减免原因
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getReductionAmount() {
        return reductionAmount;
    }
    public void setReductionAmount(String reductionAmount) {
        this.reductionAmount = reductionAmount;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
}
