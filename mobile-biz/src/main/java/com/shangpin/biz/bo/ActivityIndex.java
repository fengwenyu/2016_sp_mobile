package com.shangpin.biz.bo;

import java.io.Serializable;

public class ActivityIndex implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1020466533804783162L;
    
    private String sysTime;
    
    private OperatHeader operat;
	
	private SearchResult result=new SearchResult();

    public OperatHeader getOperat() {
        return operat;
    }

    public void setOperat(OperatHeader operat) {
        this.operat = operat;
    }

    public SearchResult getResult() {
        return result;
    }

    public void setResult(SearchResult result) {
        this.result = result;
    }

    public String getSysTime() {
        return sysTime;
    }

    public void setSysTime(String sysTime) {
        this.sysTime = sysTime;
    }
	
	

}
