package com.shangpin.core.entity;

// Generated 2014-5-26 18:24:56 

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Token 
 */
@Entity
@Table(name = "token")
public class Token implements java.io.Serializable {

    private static final long serialVersionUID = -1422831487821501378L;
    private Long id;
    private String code;
    private String status;
    private String serverId;
    private Date lastUpdated;
    private Date efftime;

    public Token() {
    }

    public Token(String code, String status, String serverId, Date lastUpdated, Date efftime) {
        this.code = code;
        this.status = status;
        this.serverId = serverId;
        this.lastUpdated = lastUpdated;
        this.efftime = efftime;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "code", length = 200)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "status", length = 16)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "serverId", length = 32)
    public String getServerId() {
        return this.serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated", length = 19)
    public Date getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "efftime", length = 19)
    public Date getEfftime() {
        return this.efftime;
    }

    public void setEfftime(Date efftime) {
        this.efftime = efftime;
    }

}
