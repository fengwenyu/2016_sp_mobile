package com.shangpin.wechat.bo.base;

import java.io.Serializable;

/**
 * Created by cuibinqiang on 2015/12/14.
 */
public class BaseResult implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errcode;
    private String errmsg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
