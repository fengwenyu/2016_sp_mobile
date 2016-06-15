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
 * HotBrands 
 */
@Entity
@Table(name = "hotBrands")
public class HotBrands implements java.io.Serializable {

    private static final long serialVersionUID = -1622745783112722216L;
    private Long id;
    private String brandName;
    private String brandId;
    private String imgUrl;
    private String imgWidth;
    private String imgHeight;
    private String topImgUrl;
    private String topImgWidth;
    private String topImgHeight;
    private Integer sort;
    private Date createDate;
    private String reserve0;
    private String reserve1;
    private String reserve2;

    public HotBrands() {
    }

    public HotBrands(String brandName, String brandId, String imgUrl, String imgWidth, String imgHeight, String topImgUrl, String topImgWidth, String topImgHeight, Integer sort,
            Date createDate, String reserve0, String reserve1, String reserve2) {
        this.brandName = brandName;
        this.brandId = brandId;
        this.imgUrl = imgUrl;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.topImgUrl = topImgUrl;
        this.topImgWidth = topImgWidth;
        this.topImgHeight = topImgHeight;
        this.sort = sort;
        this.createDate = createDate;
        this.reserve0 = reserve0;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
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

    @Column(name = "brandName", length = 100)
    public String getBrandName() {
        return this.brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Column(name = "brandId", length = 50)
    public String getBrandId() {
        return this.brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    @Column(name = "imgUrl", length = 200)
    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Column(name = "imgWidth", length = 50)
    public String getImgWidth() {
        return this.imgWidth;
    }

    public void setImgWidth(String imgWidth) {
        this.imgWidth = imgWidth;
    }

    @Column(name = "imgHeight", length = 50)
    public String getImgHeight() {
        return this.imgHeight;
    }

    public void setImgHeight(String imgHeight) {
        this.imgHeight = imgHeight;
    }

    @Column(name = "topImgUrl", length = 200)
    public String getTopImgUrl() {
        return this.topImgUrl;
    }

    public void setTopImgUrl(String topImgUrl) {
        this.topImgUrl = topImgUrl;
    }

    @Column(name = "topImgWidth", length = 50)
    public String getTopImgWidth() {
        return this.topImgWidth;
    }

    public void setTopImgWidth(String topImgWidth) {
        this.topImgWidth = topImgWidth;
    }

    @Column(name = "topImgHeight", length = 50)
    public String getTopImgHeight() {
        return this.topImgHeight;
    }

    public void setTopImgHeight(String topImgHeight) {
        this.topImgHeight = topImgHeight;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate", length = 19)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "reserve0")
    public String getReserve0() {
        return this.reserve0;
    }

    public void setReserve0(String reserve0) {
        this.reserve0 = reserve0;
    }

    @Column(name = "reserve1")
    public String getReserve1() {
        return this.reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    @Column(name = "reserve2")
    public String getReserve2() {
        return this.reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }


}
