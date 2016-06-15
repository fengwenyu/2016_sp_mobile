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

//import com.shangpin.wireless.api.domain.ProductTypeEnum;

/**
 * AppPictures
 */
@Entity
@Table(name = "appPictures")
public class AppPictures implements java.io.Serializable {

    private static final long serialVersionUID = 7899735503139505036L;
    private Long id;
    private String imgUrl;
    private String imgWidth;
    private String imgHeight;
    private String shareUrl;
    private String description;
    private String osType;
    private Date createDate;
    private String firstIphone4guideImg;
    private String secondIphone4guideImg;
    private String thirdIphone4guideImg;
    private String fifthIphone4guideImg;
    private String fourthIphone4guideImg;
    private String firstIphone5guideImg;
    private String secondIphone5guideImg;
    private String thirdIphone5guideImg;
    private String fifthIphone5guideImg;
    private String fourthIphone5guideImg;
    private String guideImgWidth;
    private String guideImgHeight;
    private String firstIphone4startImg;
    private String secondIphone4startImg;
    private String thirdIphone4startImg;
    private String fourthIphone4startImg;
    private String firstIphone5startImg;
    private String secondIphone5startImg;
    private String thirdIphone5startImg;
    private String fourthIphone5startImg;
    private String fifthIphone5startImg;
    private String startImgWidth;
    private String startImgHeight;
    private String findIphone4shareImg;
    private String findIphone5shareImg;
    private String findShareUrl;
    private String findDesc;
    private String findShareWidth;
    private String findShareHeight;
    private String fifthIphone4startImg;
    private String title;
    private String shareTitle;
    // 产品类型，奥莱或者尚品
    private Integer productType;

    public AppPictures() {
    }

    public AppPictures(String imgUrl, String imgWidth, String imgHeight, String shareUrl, String description, String osType, Date createDate, String firstIphone4guideImg,
            String secondIphone4guideImg, String thirdIphone4guideImg, String fifthIphone4guideImg, String fourthIphone4guideImg, String firstIphone5guideImg,
            String secondIphone5guideImg, String thirdIphone5guideImg, String fifthIphone5guideImg, String fourthIphone5guideImg, String guideImgWidth, String guideImgHeight,
            String firstIphone4startImg, String secondIphone4startImg, String thirdIphone4startImg, String fourthIphone4startImg, String firstIphone5startImg,
            String secondIphone5startImg, String thirdIphone5startImg, String fourthIphone5startImg, String fifthIphone5startImg, String startImgWidth, String startImgHeight,
            String findIphone4shareImg, String findIphone5shareImg, String findShareUrl, String findDesc, String findShareWidth, String findShareHeight,
            String fifthIphone4startImg, String title, String shareTitle, Integer productType) {
        this.imgUrl = imgUrl;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.shareUrl = shareUrl;
        this.description = description;
        this.osType = osType;
        this.createDate = createDate;
        this.firstIphone4guideImg = firstIphone4guideImg;
        this.secondIphone4guideImg = secondIphone4guideImg;
        this.thirdIphone4guideImg = thirdIphone4guideImg;
        this.fifthIphone4guideImg = fifthIphone4guideImg;
        this.fourthIphone4guideImg = fourthIphone4guideImg;
        this.firstIphone5guideImg = firstIphone5guideImg;
        this.secondIphone5guideImg = secondIphone5guideImg;
        this.thirdIphone5guideImg = thirdIphone5guideImg;
        this.fifthIphone5guideImg = fifthIphone5guideImg;
        this.fourthIphone5guideImg = fourthIphone5guideImg;
        this.guideImgWidth = guideImgWidth;
        this.guideImgHeight = guideImgHeight;
        this.firstIphone4startImg = firstIphone4startImg;
        this.secondIphone4startImg = secondIphone4startImg;
        this.thirdIphone4startImg = thirdIphone4startImg;
        this.fourthIphone4startImg = fourthIphone4startImg;
        this.firstIphone5startImg = firstIphone5startImg;
        this.secondIphone5startImg = secondIphone5startImg;
        this.thirdIphone5startImg = thirdIphone5startImg;
        this.fourthIphone5startImg = fourthIphone5startImg;
        this.fifthIphone5startImg = fifthIphone5startImg;
        this.startImgWidth = startImgWidth;
        this.startImgHeight = startImgHeight;
        this.findIphone4shareImg = findIphone4shareImg;
        this.findIphone5shareImg = findIphone5shareImg;
        this.findShareUrl = findShareUrl;
        this.findDesc = findDesc;
        this.findShareWidth = findShareWidth;
        this.findShareHeight = findShareHeight;
        this.fifthIphone4startImg = fifthIphone4startImg;
        this.title = title;
        this.shareTitle = shareTitle;
        this.productType = productType;
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

    @Column(name = "imgUrl", length = 200)
    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Column(name = "imgWidth", length = 20)
    public String getImgWidth() {
        return this.imgWidth;
    }

    public void setImgWidth(String imgWidth) {
        this.imgWidth = imgWidth;
    }

    @Column(name = "imgHeight", length = 20)
    public String getImgHeight() {
        return this.imgHeight;
    }

    public void setImgHeight(String imgHeight) {
        this.imgHeight = imgHeight;
    }

    @Column(name = "shareUrl", length = 200)
    public String getShareUrl() {
        return this.shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "osType", length = 100)
    public String getOsType() {
        return this.osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate", length = 19)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "firstIphone4GuideImg", length = 200)
    public String getFirstIphone4guideImg() {
        return this.firstIphone4guideImg;
    }

    public void setFirstIphone4guideImg(String firstIphone4guideImg) {
        this.firstIphone4guideImg = firstIphone4guideImg;
    }

    @Column(name = "secondIphone4GuideImg", length = 200)
    public String getSecondIphone4guideImg() {
        return this.secondIphone4guideImg;
    }

    public void setSecondIphone4guideImg(String secondIphone4guideImg) {
        this.secondIphone4guideImg = secondIphone4guideImg;
    }

    @Column(name = "thirdIphone4GuideImg", length = 200)
    public String getThirdIphone4guideImg() {
        return this.thirdIphone4guideImg;
    }

    public void setThirdIphone4guideImg(String thirdIphone4guideImg) {
        this.thirdIphone4guideImg = thirdIphone4guideImg;
    }

    @Column(name = "fifthIphone4GuideImg", length = 200)
    public String getFifthIphone4guideImg() {
        return this.fifthIphone4guideImg;
    }

    public void setFifthIphone4guideImg(String fifthIphone4guideImg) {
        this.fifthIphone4guideImg = fifthIphone4guideImg;
    }

    @Column(name = "fourthIphone4GuideImg", length = 200)
    public String getFourthIphone4guideImg() {
        return this.fourthIphone4guideImg;
    }

    public void setFourthIphone4guideImg(String fourthIphone4guideImg) {
        this.fourthIphone4guideImg = fourthIphone4guideImg;
    }

    @Column(name = "firstIphone5GuideImg", length = 200)
    public String getFirstIphone5guideImg() {
        return this.firstIphone5guideImg;
    }

    public void setFirstIphone5guideImg(String firstIphone5guideImg) {
        this.firstIphone5guideImg = firstIphone5guideImg;
    }

    @Column(name = "secondIphone5GuideImg", length = 200)
    public String getSecondIphone5guideImg() {
        return this.secondIphone5guideImg;
    }

    public void setSecondIphone5guideImg(String secondIphone5guideImg) {
        this.secondIphone5guideImg = secondIphone5guideImg;
    }

    @Column(name = "thirdIphone5GuideImg", length = 200)
    public String getThirdIphone5guideImg() {
        return this.thirdIphone5guideImg;
    }

    public void setThirdIphone5guideImg(String thirdIphone5guideImg) {
        this.thirdIphone5guideImg = thirdIphone5guideImg;
    }

    @Column(name = "fifthIphone5GuideImg", length = 200)
    public String getFifthIphone5guideImg() {
        return this.fifthIphone5guideImg;
    }

    public void setFifthIphone5guideImg(String fifthIphone5guideImg) {
        this.fifthIphone5guideImg = fifthIphone5guideImg;
    }

    @Column(name = "fourthIphone5GuideImg", length = 200)
    public String getFourthIphone5guideImg() {
        return this.fourthIphone5guideImg;
    }

    public void setFourthIphone5guideImg(String fourthIphone5guideImg) {
        this.fourthIphone5guideImg = fourthIphone5guideImg;
    }

    @Column(name = "guideImgWidth", length = 20)
    public String getGuideImgWidth() {
        return this.guideImgWidth;
    }

    public void setGuideImgWidth(String guideImgWidth) {
        this.guideImgWidth = guideImgWidth;
    }

    @Column(name = "guideImgHeight", length = 20)
    public String getGuideImgHeight() {
        return this.guideImgHeight;
    }

    public void setGuideImgHeight(String guideImgHeight) {
        this.guideImgHeight = guideImgHeight;
    }

    @Column(name = "firstIphone4StartImg", length = 200)
    public String getFirstIphone4startImg() {
        return this.firstIphone4startImg;
    }

    public void setFirstIphone4startImg(String firstIphone4startImg) {
        this.firstIphone4startImg = firstIphone4startImg;
    }

    @Column(name = "secondIphone4StartImg", length = 200)
    public String getSecondIphone4startImg() {
        return this.secondIphone4startImg;
    }

    public void setSecondIphone4startImg(String secondIphone4startImg) {
        this.secondIphone4startImg = secondIphone4startImg;
    }

    @Column(name = "thirdIphone4StartImg", length = 200)
    public String getThirdIphone4startImg() {
        return this.thirdIphone4startImg;
    }

    public void setThirdIphone4startImg(String thirdIphone4startImg) {
        this.thirdIphone4startImg = thirdIphone4startImg;
    }

    @Column(name = "fourthIphone4StartImg", length = 200)
    public String getFourthIphone4startImg() {
        return this.fourthIphone4startImg;
    }

    public void setFourthIphone4startImg(String fourthIphone4startImg) {
        this.fourthIphone4startImg = fourthIphone4startImg;
    }

    @Column(name = "firstIphone5StartImg", length = 200)
    public String getFirstIphone5startImg() {
        return this.firstIphone5startImg;
    }

    public void setFirstIphone5startImg(String firstIphone5startImg) {
        this.firstIphone5startImg = firstIphone5startImg;
    }

    @Column(name = "secondIphone5StartImg", length = 200)
    public String getSecondIphone5startImg() {
        return this.secondIphone5startImg;
    }

    public void setSecondIphone5startImg(String secondIphone5startImg) {
        this.secondIphone5startImg = secondIphone5startImg;
    }

    @Column(name = "thirdIphone5StartImg", length = 200)
    public String getThirdIphone5startImg() {
        return this.thirdIphone5startImg;
    }

    public void setThirdIphone5startImg(String thirdIphone5startImg) {
        this.thirdIphone5startImg = thirdIphone5startImg;
    }

    @Column(name = "fourthIphone5StartImg", length = 200)
    public String getFourthIphone5startImg() {
        return this.fourthIphone5startImg;
    }

    public void setFourthIphone5startImg(String fourthIphone5startImg) {
        this.fourthIphone5startImg = fourthIphone5startImg;
    }

    @Column(name = "fifthIphone5StartImg", length = 200)
    public String getFifthIphone5startImg() {
        return this.fifthIphone5startImg;
    }

    public void setFifthIphone5startImg(String fifthIphone5startImg) {
        this.fifthIphone5startImg = fifthIphone5startImg;
    }

    @Column(name = "startImgWidth", length = 20)
    public String getStartImgWidth() {
        return this.startImgWidth;
    }

    public void setStartImgWidth(String startImgWidth) {
        this.startImgWidth = startImgWidth;
    }

    @Column(name = "startImgHeight", length = 20)
    public String getStartImgHeight() {
        return this.startImgHeight;
    }

    public void setStartImgHeight(String startImgHeight) {
        this.startImgHeight = startImgHeight;
    }

    @Column(name = "findIphone4ShareImg", length = 200)
    public String getFindIphone4shareImg() {
        return this.findIphone4shareImg;
    }

    public void setFindIphone4shareImg(String findIphone4shareImg) {
        this.findIphone4shareImg = findIphone4shareImg;
    }

    @Column(name = "findIphone5ShareImg", length = 200)
    public String getFindIphone5shareImg() {
        return this.findIphone5shareImg;
    }

    public void setFindIphone5shareImg(String findIphone5shareImg) {
        this.findIphone5shareImg = findIphone5shareImg;
    }

    @Column(name = "findShareUrl", length = 200)
    public String getFindShareUrl() {
        return this.findShareUrl;
    }

    public void setFindShareUrl(String findShareUrl) {
        this.findShareUrl = findShareUrl;
    }

    @Column(name = "findDesc")
    public String getFindDesc() {
        return this.findDesc;
    }

    public void setFindDesc(String findDesc) {
        this.findDesc = findDesc;
    }

    @Column(name = "findShareWidth", length = 20)
    public String getFindShareWidth() {
        return this.findShareWidth;
    }

    public void setFindShareWidth(String findShareWidth) {
        this.findShareWidth = findShareWidth;
    }

    @Column(name = "findShareHeight", length = 20)
    public String getFindShareHeight() {
        return this.findShareHeight;
    }

    public void setFindShareHeight(String findShareHeight) {
        this.findShareHeight = findShareHeight;
    }

    @Column(name = "fifthIphone4StartImg", length = 200)
    public String getFifthIphone4startImg() {
        return this.fifthIphone4startImg;
    }

    public void setFifthIphone4startImg(String fifthIphone4startImg) {
        this.fifthIphone4startImg = fifthIphone4startImg;
    }

    @Column(name = "title", length = 100)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "shareTitle", length = 100)
    public String getShareTitle() {
        return this.shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public Integer getProductType() {
        return productType;
    }

    @Column(name = "productType", length = 11)
    public void setProductType(Integer productType) {
        this.productType = productType;
    }

}
