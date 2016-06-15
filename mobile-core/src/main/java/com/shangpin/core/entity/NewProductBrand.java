package com.shangpin.core.entity;

// Generated 2014-5-26 18:24:56 

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NewProductBrand 
 */
@Entity
@Table(name = "newProductBrand")
public class NewProductBrand implements java.io.Serializable {

    private static final long serialVersionUID = 4609722189495783875L;
    private String categoryId;
    private String firstNewProductId;
    private String secondNewProductId;
    private String firstBrandId;
    private String firstBrandName;
    private String firstBrandProductId;
    private String secondBrandProductId;
    private String threeBrandProductId;
    private String fourthBrandProductId;
    private String fifthBrandProductId;
    private String sixthBrandProductId;
    private String seventhBrandProductId;
    private String secondBrandId;
    private String secondBrandName;
    private String threeBrandId;
    private String threeBrandName;
    private String fourthBrandId;
    private String fourthBrandName;
    private String fifthBrandId;
    private String fifthBrandName;
    private String sixthBrandId;
    private String sixthBrandName;
    private String seventhBrandId;
    private String seventhBrandName;
    private Date createTime;
    private String brandProductId;

    public NewProductBrand() {
    }

    public NewProductBrand(String categoryId) {
        this.categoryId = categoryId;
    }

    public NewProductBrand(String categoryId, String firstNewProductId, String secondNewProductId, String firstBrandId, String firstBrandName, String firstBrandProductId,
            String secondBrandProductId, String threeBrandProductId, String fourthBrandProductId, String fifthBrandProductId, String sixthBrandProductId,
            String seventhBrandProductId, String secondBrandId, String secondBrandName, String threeBrandId, String threeBrandName, String fourthBrandId, String fourthBrandName,
            String fifthBrandId, String fifthBrandName, String sixthBrandId, String sixthBrandName, String seventhBrandId, String seventhBrandName, Date createTime,
            String brandProductId) {
        this.categoryId = categoryId;
        this.firstNewProductId = firstNewProductId;
        this.secondNewProductId = secondNewProductId;
        this.firstBrandId = firstBrandId;
        this.firstBrandName = firstBrandName;
        this.firstBrandProductId = firstBrandProductId;
        this.secondBrandProductId = secondBrandProductId;
        this.threeBrandProductId = threeBrandProductId;
        this.fourthBrandProductId = fourthBrandProductId;
        this.fifthBrandProductId = fifthBrandProductId;
        this.sixthBrandProductId = sixthBrandProductId;
        this.seventhBrandProductId = seventhBrandProductId;
        this.secondBrandId = secondBrandId;
        this.secondBrandName = secondBrandName;
        this.threeBrandId = threeBrandId;
        this.threeBrandName = threeBrandName;
        this.fourthBrandId = fourthBrandId;
        this.fourthBrandName = fourthBrandName;
        this.fifthBrandId = fifthBrandId;
        this.fifthBrandName = fifthBrandName;
        this.sixthBrandId = sixthBrandId;
        this.sixthBrandName = sixthBrandName;
        this.seventhBrandId = seventhBrandId;
        this.seventhBrandName = seventhBrandName;
        this.createTime = createTime;
        this.brandProductId = brandProductId;
    }

    @Id
    @Column(name = "categoryId", unique = true, nullable = false)
    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "firstNewProductId", length = 50)
    public String getFirstNewProductId() {
        return this.firstNewProductId;
    }

    public void setFirstNewProductId(String firstNewProductId) {
        this.firstNewProductId = firstNewProductId;
    }

    @Column(name = "secondNewProductId", length = 50)
    public String getSecondNewProductId() {
        return this.secondNewProductId;
    }

    public void setSecondNewProductId(String secondNewProductId) {
        this.secondNewProductId = secondNewProductId;
    }

    @Column(name = "firstBrandId", length = 50)
    public String getFirstBrandId() {
        return this.firstBrandId;
    }

    public void setFirstBrandId(String firstBrandId) {
        this.firstBrandId = firstBrandId;
    }

    @Column(name = "firstBrandName", length = 100)
    public String getFirstBrandName() {
        return this.firstBrandName;
    }

    public void setFirstBrandName(String firstBrandName) {
        this.firstBrandName = firstBrandName;
    }

    @Column(name = "firstBrandProductId", length = 50)
    public String getFirstBrandProductId() {
        return this.firstBrandProductId;
    }

    public void setFirstBrandProductId(String firstBrandProductId) {
        this.firstBrandProductId = firstBrandProductId;
    }

    @Column(name = "secondBrandProductId", length = 50)
    public String getSecondBrandProductId() {
        return this.secondBrandProductId;
    }

    public void setSecondBrandProductId(String secondBrandProductId) {
        this.secondBrandProductId = secondBrandProductId;
    }

    @Column(name = "threeBrandProductId", length = 50)
    public String getThreeBrandProductId() {
        return this.threeBrandProductId;
    }

    public void setThreeBrandProductId(String threeBrandProductId) {
        this.threeBrandProductId = threeBrandProductId;
    }

    @Column(name = "fourthBrandProductId", length = 50)
    public String getFourthBrandProductId() {
        return this.fourthBrandProductId;
    }

    public void setFourthBrandProductId(String fourthBrandProductId) {
        this.fourthBrandProductId = fourthBrandProductId;
    }

    @Column(name = "fifthBrandProductId", length = 50)
    public String getFifthBrandProductId() {
        return this.fifthBrandProductId;
    }

    public void setFifthBrandProductId(String fifthBrandProductId) {
        this.fifthBrandProductId = fifthBrandProductId;
    }

    @Column(name = "sixthBrandProductId", length = 50)
    public String getSixthBrandProductId() {
        return this.sixthBrandProductId;
    }

    public void setSixthBrandProductId(String sixthBrandProductId) {
        this.sixthBrandProductId = sixthBrandProductId;
    }

    @Column(name = "seventhBrandProductId", length = 50)
    public String getSeventhBrandProductId() {
        return this.seventhBrandProductId;
    }

    public void setSeventhBrandProductId(String seventhBrandProductId) {
        this.seventhBrandProductId = seventhBrandProductId;
    }

    @Column(name = "secondBrandId", length = 50)
    public String getSecondBrandId() {
        return this.secondBrandId;
    }

    public void setSecondBrandId(String secondBrandId) {
        this.secondBrandId = secondBrandId;
    }

    @Column(name = "secondBrandName", length = 100)
    public String getSecondBrandName() {
        return this.secondBrandName;
    }

    public void setSecondBrandName(String secondBrandName) {
        this.secondBrandName = secondBrandName;
    }

    @Column(name = "threeBrandId", length = 50)
    public String getThreeBrandId() {
        return this.threeBrandId;
    }

    public void setThreeBrandId(String threeBrandId) {
        this.threeBrandId = threeBrandId;
    }

    @Column(name = "threeBrandName", length = 100)
    public String getThreeBrandName() {
        return this.threeBrandName;
    }

    public void setThreeBrandName(String threeBrandName) {
        this.threeBrandName = threeBrandName;
    }

    @Column(name = "fourthBrandId", length = 50)
    public String getFourthBrandId() {
        return this.fourthBrandId;
    }

    public void setFourthBrandId(String fourthBrandId) {
        this.fourthBrandId = fourthBrandId;
    }

    @Column(name = "fourthBrandName", length = 100)
    public String getFourthBrandName() {
        return this.fourthBrandName;
    }

    public void setFourthBrandName(String fourthBrandName) {
        this.fourthBrandName = fourthBrandName;
    }

    @Column(name = "fifthBrandId", length = 50)
    public String getFifthBrandId() {
        return this.fifthBrandId;
    }

    public void setFifthBrandId(String fifthBrandId) {
        this.fifthBrandId = fifthBrandId;
    }

    @Column(name = "fifthBrandName", length = 100)
    public String getFifthBrandName() {
        return this.fifthBrandName;
    }

    public void setFifthBrandName(String fifthBrandName) {
        this.fifthBrandName = fifthBrandName;
    }

    @Column(name = "sixthBrandId", length = 50)
    public String getSixthBrandId() {
        return this.sixthBrandId;
    }

    public void setSixthBrandId(String sixthBrandId) {
        this.sixthBrandId = sixthBrandId;
    }

    @Column(name = "sixthBrandName", length = 100)
    public String getSixthBrandName() {
        return this.sixthBrandName;
    }

    public void setSixthBrandName(String sixthBrandName) {
        this.sixthBrandName = sixthBrandName;
    }

    @Column(name = "seventhBrandId", length = 50)
    public String getSeventhBrandId() {
        return this.seventhBrandId;
    }

    public void setSeventhBrandId(String seventhBrandId) {
        this.seventhBrandId = seventhBrandId;
    }

    @Column(name = "seventhBrandName", length = 100)
    public String getSeventhBrandName() {
        return this.seventhBrandName;
    }

    public void setSeventhBrandName(String seventhBrandName) {
        this.seventhBrandName = seventhBrandName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createTime", length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "brandProductId", length = 50)
    public String getBrandProductId() {
        return this.brandProductId;
    }

    public void setBrandProductId(String brandProductId) {
        this.brandProductId = brandProductId;
    }

}
