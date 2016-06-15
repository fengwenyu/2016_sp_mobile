package com.shangpin.core.entity;

// Generated 2014-7-1 15:53:06 

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
 * AolaiActivity 
 */
@Entity
@Table(name = "aolai_activity")
public class AolaiActivity implements java.io.Serializable {

    private static final long serialVersionUID = -4211425251911280831L;
    private Long id;
    private Date startTime;
    private Date endTime;
    private int display;
    private String getUrl;
    private Date createDate;

    public AolaiActivity() {
    }

    public AolaiActivity(int display) {
        this.display = display;
    }

    public AolaiActivity(Date startTime, Date endTime, int display, String getUrl, Date createDate) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.display = display;
        this.getUrl = getUrl;
        this.createDate = createDate;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startTime", length = 19)
    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endTime", length = 19)
    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(name = "display", nullable = false)
    public int getDisplay() {
        return this.display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    @Column(name = "getUrl", length = 200)
    public String getGetUrl() {
        return this.getUrl;
    }

    public void setGetUrl(String getUrl) {
        this.getUrl = getUrl;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate", length = 19)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
