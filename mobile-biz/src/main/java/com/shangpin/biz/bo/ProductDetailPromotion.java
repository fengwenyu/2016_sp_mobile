package com.shangpin.biz.bo;

import java.io.Serializable;

/** 
* @ClassName: ProductDetailPromotion 
* @Description:尚品详情页促销信息实体类 
* @author wangfeng
* @date 2015年09月14日
* @version 1.0 
*/
public class ProductDetailPromotion implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3284098808808086731L;
    private String activityId;
    private String activityName;
    private String desc;
    public String getActivityId() {
        return activityId;
    }
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getActivityName() {
        return activityName;
    }
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
    


}
