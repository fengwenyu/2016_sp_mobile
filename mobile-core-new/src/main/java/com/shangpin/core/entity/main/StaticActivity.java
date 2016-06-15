package com.shangpin.core.entity.main;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 静态活动实体
 * 
 * @author liling
 */
@Entity
@Table(name = "manage_static_activity")
public class StaticActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /** 开始时间 */
    @Column(name = "start_time", length = 19)
    private Date startTime;
    
    /** 结束时间 */
    @Column(name = "end_time", length = 19)
    private Date endTime;
    
    /** 链接 */
    @Column(name = "get_url", length = 200)
    private String getUrl;
    
    /** 创建时间 */
    @Column(name = "create_time", length = 19)
    private Date createTime;
    
    /** 图片链接 */
    @Column(name = "img_url", length = 200)
    private String imgUrl;
    
    /** 图片宽度 */
    @Column(name = "img_width", length = 50)
    private String imgWidth;
    
    /** 图片高度 */
    @Column(name = "img_height", length = 50)
    private String imgHeight;
    
    /** 类型是活动静态页时静态页是否显示，0为不显示，1为显示 */
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "display")
    private Display display;
    
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "app")
    private AppType app;
    
    @Column(name = "description", length = 200)
    private String description;
    
    @Column(name = "title", length = 150)
    private String title;

    public StaticActivity() {
    }

    // public FindManage(int isSlider, int display) {
    // this.isSlider = isSlider;
    // this.display = display;
    // }
    public StaticActivity(Long id, Date startTime, Date endTime, String getUrl, Date createTime, String imgUrl, String imgWidth, String imgHeight, Display display, AppType app,
            String description, String title) {
        super();
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.getUrl = getUrl;
        this.createTime = createTime;
        this.imgUrl = imgUrl;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.display = display;
        this.app = app;
        this.description = description;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getGetUrl() {
        return getUrl;
    }

    public void setGetUrl(String getUrl) {
        this.getUrl = getUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(String imgWidth) {
        this.imgWidth = imgWidth;
    }

    public String getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(String imgHeight) {
        this.imgHeight = imgHeight;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public AppType getApp() {
        return app;
    }

    public void setApp(AppType app) {
        this.app = app;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
