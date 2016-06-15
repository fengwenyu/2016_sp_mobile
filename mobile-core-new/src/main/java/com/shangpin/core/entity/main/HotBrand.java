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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.shangpin.core.entity.Idable;

/**
 * 热门品牌推荐.
 * 
 * @author yangtongchui
 * @date 2014-8-12
 */
@Entity
@Table(name = "manage_hot_brand")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.shangpin.core.entity.main.HotBrand")
public class HotBrand implements Idable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;// 主键

    @Column(name = "brand_name", length = 100)
    private String brandName;// 品牌名称

    @Column(name = "brand_id", length = 50)
    private String brandId;// 品牌ID

    @Column(name = "img_url", length = 200)
    private String imgUrl;// 图片链接

    @Column(name = "img_width", length = 50)
    private String imgWidth;// 图片宽度

    @Column(name = "img_height", length = 50)
    private String imgHeight;// 图片高度

    @Column(name = "top_img_url", length = 200)
    private String topImgUrl;// 头图链接

    @Column(name = "top_img_width", length = 50)
    private String topImgWidth;// 头图宽度

    @Column(name = "top_img_height", length = 50)
    private String topImgHeight;// 头图高度

    @Column(name = "sort")
    private Integer sort;// 排序

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, length = 19, updatable = false)
    private Date createTime;// 创建时间

    @Column(name = "reserve0")
    private String reserve0;// 预留字段1

    @Column(name = "reserve1")
    private String reserve1;// 预留字段2

    @Column(name = "reserve2")
    private String reserve2;// 预留字段3

    @Column(name = "encode_name")
    private String encodeName;// 编码名称

    // Constructors

    /** default constructor */
    public HotBrand() {
    }

    /** full constructor */
    public HotBrand(String brandName, String brandId, String imgUrl, String imgWidth, String imgHeight, String topImgUrl, String topImgWidth, String topImgHeight, Integer sort,
            Date createTime, String reserve0, String reserve1, String reserve2, String encodeName) {
        this.brandName = brandName;
        this.brandId = brandId;
        this.imgUrl = imgUrl;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.topImgUrl = topImgUrl;
        this.topImgWidth = topImgWidth;
        this.topImgHeight = topImgHeight;
        this.sort = sort;
        this.createTime = createTime;
        this.reserve0 = reserve0;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
        this.encodeName = encodeName;
    }

    // Property accessors
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandId() {
        return this.brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgWidth() {
        return this.imgWidth;
    }

    public void setImgWidth(String imgWidth) {
        this.imgWidth = imgWidth;
    }

    public String getImgHeight() {
        return this.imgHeight;
    }

    public void setImgHeight(String imgHeight) {
        this.imgHeight = imgHeight;
    }

    public String getTopImgUrl() {
        return this.topImgUrl;
    }

    public void setTopImgUrl(String topImgUrl) {
        this.topImgUrl = topImgUrl;
    }

    public String getTopImgWidth() {
        return this.topImgWidth;
    }

    public void setTopImgWidth(String topImgWidth) {
        this.topImgWidth = topImgWidth;
    }

    public String getTopImgHeight() {
        return this.topImgHeight;
    }

    public void setTopImgHeight(String topImgHeight) {
        this.topImgHeight = topImgHeight;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReserve0() {
        return this.reserve0;
    }

    public void setReserve0(String reserve0) {
        this.reserve0 = reserve0;
    }

    public String getReserve1() {
        return this.reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return this.reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getEncodeName() {
        return this.encodeName;
    }

    public void setEncodeName(String encodeName) {
        this.encodeName = encodeName;
    }

}