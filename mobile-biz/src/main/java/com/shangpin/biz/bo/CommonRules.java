package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * 通用规则基类
 * 
 * @author huangxiaoliang
 * 
 */
public class CommonRules implements Serializable {

    private static final long serialVersionUID = -7034861936220570288L;

    private String name;

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
