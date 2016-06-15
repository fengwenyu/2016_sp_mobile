package com.shangpin.biz.bo;

import java.io.Serializable;

import java.util.List;
/**
 * 明星发红包Bean
 * 
 * @author wh
 * 
 */
public class RedActivity implements Serializable {
    
    private static final long serialVersionUID = 994358010403827310L;
    /** 0否,1新*/
    private String isNew; 
    /** List 红包信息 */
    private List<AwardsInfo> list;
    /**
     * @return the isNew
     */
    public String getIsNew() {
        return isNew;
    }
    /**
     * @param isNew the isNew to set
     */
    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }
    /**
     * @return the awardsInfo
     */
    public List<AwardsInfo> getAwardsInfo() {
        return list;
    }
    /**
     * @param awardsInfo the awardsInfo to set
     */
    public void setAwardsInfo(List<AwardsInfo> awardsInfo) {
        this.list = awardsInfo;
    }

     
}
