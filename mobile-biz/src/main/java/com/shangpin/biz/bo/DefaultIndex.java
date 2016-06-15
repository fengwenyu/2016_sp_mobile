package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * @ClassName: DefaultIndex
 * @Description:商品默认选择索引实体类
 * @author wangfeng
 * @date 2015年08月22日
 * @version 1.0
 */
public class DefaultIndex implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 753562769386897707L;
    private String firstPropIndex;
    private String secondPropIndex;
    public String getFirstPropIndex() {
        return firstPropIndex;
    }
    public void setFirstPropIndex(String firstPropIndex) {
        this.firstPropIndex = firstPropIndex;
    }
    public String getSecondPropIndex() {
        return secondPropIndex;
    }
    public void setSecondPropIndex(String secondPropIndex) {
        this.secondPropIndex = secondPropIndex;
    }
   
    
    

}
