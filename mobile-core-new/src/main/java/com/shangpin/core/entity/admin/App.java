package com.shangpin.core.entity.admin;

// default package
// Generated 2014-11-18 15:20:34

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

/**
 * ManagerApp
 */
@Entity
@Table(name = "manager_app")
public class App implements Idable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "num", length = 20)
    private String num;

    @Column(name = "code", length = 20)
    private String code;

    @Column(name = "platform", length = 10)
    private String platform;

    @Column(name = "description", length = 200)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, length = 19)
    private Date createTime;

    public App() {
    }

    public App(Date createTime) {
        this.createTime = createTime;
    }

    public App(String name, String num, String code, String platform, String description, Date createTime) {
        this.name = name;
        this.num = num;
        this.code = code;
        this.platform = platform;
        this.description = description;
        this.createTime = createTime;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
