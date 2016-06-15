package com.shangpin.core.entity;

// Generated 2014-7-1 11:11:10

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
 * AppNavigation
 */
@Entity
@Table(name = "app_navigation")
public class AppNavigation implements java.io.Serializable {

    private static final long serialVersionUID = 8312825540530910805L;
    private Long id;
    private int showType;
    private String name;
    private String link;
    private Date createDate;
    private String tabId;
    private String sort;

    public AppNavigation() {
    }

    public AppNavigation(int showType) {
        this.showType = showType;
    }

    public AppNavigation(int showType, String name, String link, Date createDate, String tabId, String sort) {
        this.showType = showType;
        this.name = name;
        this.link = link;
        this.createDate = createDate;
        this.tabId = tabId;
        this.sort = sort;
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

    @Column(name = "showType", nullable = false)
    public int getShowType() {
        return this.showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "link", length = 200)
    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate", length = 19)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "tabId", length = 50)
    public String getTabId() {
        return this.tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    @Column(name = "sort", length = 5)
    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

}
