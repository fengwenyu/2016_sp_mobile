package com.shangpin.biz.bo;

import java.io.Serializable;


/** 
* @ClassName: SearchCorrect 
* @Description:搜索纠错类
* @author wangfeng
* @date 2015年09月22日
* @version 1.0 
*/

public class SearchCorrect implements Serializable{
	
	/**
     * 
     */
    private static final long serialVersionUID = 1918205989511935782L;
    private String word;
	private String desc;
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
	
	

}
