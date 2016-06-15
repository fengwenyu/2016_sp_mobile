package com.shangpin.biz.bo;

import java.io.Serializable;

public class ActivityIndexNew implements Serializable{

    public String getPicremind() {
		return picremind;
	}

	public void setPicremind(String picremind) {
		this.picremind = picremind;
	}

	/**
     * 
     */
    private static final long serialVersionUID = 1020466533804783162L;
    
    private String sysTime;
    
    private OperatHeader operat;
	
	private SearchResultApp result=new SearchResultApp();

	
	//2016 5.20 活动增加名称 是否提醒 wap title wap url
	
	
	 private String title;//"活动名称4.19add",
     private Boolean enableRemind;//"true"//是否可以提醒,
     private String remindUrl;//"XXXXXXXXXX可以提醒的wapURl",
     private String waptitle;//"waptitle wap页的名称"
     
     private String picremind;//提醒图标
	
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public Boolean getEnableRemind() {
		return enableRemind;
	}

	public void setEnableRemind(Boolean enableRemind) {
		this.enableRemind = enableRemind;
	}

	public String getRemindUrl() {
		return remindUrl;
	}

	public void setRemindUrl(String remindUrl) {
		this.remindUrl = remindUrl;
	}

	public String getWaptitle() {
		return waptitle;
	}

	public void setWaptitle(String waptitle) {
		this.waptitle = waptitle;
	}

	public OperatHeader getOperat() {
        return operat;
    }

    public void setOperat(OperatHeader operat) {
        this.operat = operat;
    }

    public String getSysTime() {
        return sysTime;
    }

    public void setSysTime(String sysTime) {
        this.sysTime = sysTime;
    }

    public SearchResultApp getResult() {
        return result;
    }

    public void setResult(SearchResultApp result) {
        this.result = result;
    }
	
	

}
