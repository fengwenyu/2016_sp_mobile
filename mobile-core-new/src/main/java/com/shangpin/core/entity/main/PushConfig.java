package com.shangpin.core.entity.main;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.shangpin.core.entity.Idable;

@Entity
@Table(name = "manage_push_conf")
public class PushConfig implements Idable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type")
    private int type; // 0表示尚品，1表示奥莱

    @Column(name = "userid")
    private String userid;

    @Column(name = "gender")
    private int gender;// 0表示男，1表示女

    @Column(name = "token")
    private String token;

    @Column(name = "imei")
    private String imei;

    @Column(name = "isopen")
    private int isopen;

    @Column(name = "msg_type")
    private int msgType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, length = 19, updatable = false)
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid
     *            the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return the gender
     */
    public int getGender() {
        return gender;
    }

    /**
     * @param gender
     *            the gender to set
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the imei
     */
    public String getImei() {
        return imei;
    }

    /**
     * @param imei
     *            the imei to set
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * @return the isopen
     */
    public int getIsopen() {
        return isopen;
    }

    /**
     * @param isopen
     *            the isopen to set
     */
    public void setIsopen(int isopen) {
        this.isopen = isopen;
    }

    /**
     * @return the msgType
     */
    public int getMsgType() {
        return msgType;
    }

    /**
     * @param msgType
     *            the msgType to set
     */
    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PushConfig [id=" + id + ", type=" + type + ", userid=" + userid + ", gender=" + gender + ", token=" + token + ", imei=" + imei + ", isopen=" + isopen
                + ", msgType=" + msgType + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
    }

}
