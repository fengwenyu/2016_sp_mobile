package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * @ClassName: ProductBiz
 * @Description:商品详情客户端业务实体类
 * @author wangfeng
 * @date 2015年3月11日
 * @version 1.0
 */
public class ProductBiz implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6004506427498665526L;
    private String name;//名称
    private String type;
    private String refContent;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getRefContent() {
        return refContent;
    }
    public void setRefContent(String refContent) {
        this.refContent = refContent;
    }
    

   

}
