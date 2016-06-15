package com.shangpin.core.entity.main;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 奥莱客户端导航编辑实体.
 * 
 * @author yangtongchui
 * @since 2014-8-12
 */
@Entity
@Table(name = "manage_app_navigation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.shangpin.core.entity.main.AppNavigation")
public class AppNavigation implements java.io.Serializable {

    private static final long serialVersionUID = 2296259680330671313L;

    private long id;// 主键
    private Integer showType;// 展示类型
    private String navName;// 导航名称
    private String link;// 链接
    private Date createTime;// 创建时间
    private String tabId;// tabId
    private String sort;// 排序
    private Date startTime;// 开始时间
    private Date endTime;// 结束时间

    // Constructors

    /** default constructor */
    public AppNavigation() {
    }

    /** minimal constructor */
    public AppNavigation(Integer showType) {
        this.showType = showType;
    }

    /** full constructor */
    public AppNavigation(Integer showType, String navName, String link, Date createTime, String tabId, String sort, Date startTime, Date endTime) {
        this.showType = showType;
        this.navName = navName;
        this.link = link;
        this.createTime = createTime;
        this.tabId = tabId;
        this.sort = sort;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Property accessors
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "show_type", nullable = false)
    public Integer getShowType() {
        return this.showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    @Column(name = "nav_name", length = 100)
    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }

    @Column(name = "link", length = 200)
    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Column(name = "create_time", length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "tab_id", length = 50)
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

    @Column(name = "start_time", length = 19)
    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(name = "end_time", length = 19)
    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}