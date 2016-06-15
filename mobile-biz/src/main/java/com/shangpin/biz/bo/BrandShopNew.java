package com.shangpin.biz.bo;

import java.io.Serializable;

public class BrandShopNew implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1020466533804783162L;
    
    private OperatHeader operat;
	private SearchResultApp result;

    public OperatHeader getOperat() {
        return operat;
    }

    public void setOperat(OperatHeader operat) {
        this.operat = operat;
    }

    public SearchResultApp getResult() {
        return result;
    }

    public void setResult(SearchResultApp result) {
        this.result = result;
    }

    
	
	

}
