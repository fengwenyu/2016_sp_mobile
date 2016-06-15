package com.shangpin.base.vo;

import java.io.Serializable;


public class ProductDetail implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String code;
    private String msg;
    private MerchandiseDetail content;
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }
    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
    /**
     * @return the content
     */
    public MerchandiseDetail getContent() {
        return content;
    }
    /**
     * @param content the content to set
     */
    public void setContent(MerchandiseDetail content) {
        this.content = content;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProductDetail [code=" + code + ", msg=" + msg + ", content=" + content + "]";
    }
    
}
