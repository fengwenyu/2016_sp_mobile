package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * 明星发红包Bean
 * 
 * @author wh
 * 
 */
public class AwardsInfo implements Serializable {
    private static final long serialVersionUID = 994358010403827310L;
    /** 1现金2优惠券 */
    private String type;
    /** 金额 */
    private String amount;
    /** 0为未激活1激活 */
    private String isActive;
    /** 优惠券图片 */
    private String pic;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the isActive
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * @param isActive
     *            the isActive to set
     */
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the pic
     */
    public String getPic() {
        return pic;
    }

    /**
     * @param pic
     *            the pic to set
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

}
