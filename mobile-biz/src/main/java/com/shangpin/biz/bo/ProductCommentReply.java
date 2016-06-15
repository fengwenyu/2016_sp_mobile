package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * @ClassName: ProductCommentReply
 * @Description:评论回复实体类
 * @author wangfeng
 * @date 2015年3月12日
 * @version 1.0
 */
public class ProductCommentReply implements Serializable {


    

    /**
     * 
     */
    private static final long serialVersionUID = 3862688134807024694L;
    
    
    private String from;//来源
    private String desc;//内容
    private String date;//时间
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    

  

}
