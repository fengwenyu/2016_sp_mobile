package com.shangpin.biz.bo;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 扫描实体类
 * @author wh
 *
 */
public class ScanCodeRecharge implements Serializable {
    
    private static final long serialVersionUID = 4600270645287911827L;
    @NotBlank
	private String id; 
    @NotBlank
    @Size(max =2)
    @Pattern(regexp="[0-9]*")
	private String t; 
    @NotBlank
	private String value; 
    @NotBlank
	private String pwd;
	
	/**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return the t
     */
    public String getT() {
        return t;
    }
    /**
     * @param t the t to set
     */
    public void setT(String t) {
        this.t = t;
    }
    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }
    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
	
}
