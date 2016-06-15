package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 *  
 * 
 * @author wh
 * 
 */
public class RedIsHave  implements Serializable{
    private static final long serialVersionUID = 994358010403827310L;
    /** 是否存在  */
	private String isHave;
    /**
     * @return the isHave
     */
    public String getIsHave() {
        return isHave;
    }
    /**
     * @param isHave the isHave to set
     */
    public void setIsHave(String isHave) {
        this.isHave = isHave;
    }
	 
}
