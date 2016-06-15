package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * NBD鞋活动Bean对象
 * 
 * @author wh
 *
 */
public class ActivityUserDb  implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String userId;
    private String name;
    private String sex;
    private String size;
    private String Phone;
    private String userCode;
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }
    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }
    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }
    /**
     * @return the phone
     */
    public String getPhone() {
        return Phone;
    }
    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        Phone = phone;
    }
    /**
     * @return the userCode
     */
    public String getUserCode() {
        return userCode;
    }
    /**
     * @param userCode the userCode to set
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

}
