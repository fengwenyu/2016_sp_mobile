package com.shangpin.biz.bo;

import java.io.Serializable;

public class BrandShop implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1020466533804783162L;
    
    private OperatHeader operat;
	private SearchResult result;

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
	
	

}
