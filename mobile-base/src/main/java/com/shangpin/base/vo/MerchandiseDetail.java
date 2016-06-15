package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MerchandiseDetail implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /*** 库存数量 */
    private String totalcount;
    /*** 商品来自活动还是专题 0：专题；1：活动 */
    private String type;
    /*** 折扣 */
    private String rebate;
    /*** 买手说明 */
    private String buyer;
    /*** 可变属性名称 */
    private String firstpropname;
    /*** 可变属性名称 */
    private String secondpropname;
    /*** 分享超链接 */
    private String shareurl;
    /*** 商品编号 */
    private String goodscode;
    /*** 商品名称 */
    private String name;
    /*** 是否支持货到付款 */
    private String cod;
    /*** 品类 */
    private String cate;
    /*** 是否显示尺码说明 */
    private String hassize;
    /*** 系统时间 */
    private String systime;
    /*** 特殊说明 */
    private String specialinfo;
    /*** 售后服务 */
    private String aftersale;
    /**
     * 温馨提示
     */
    private String returnChangeRemind;
    private String brandinfo;
    /*** 品牌 */
    private String brand;
    private String priceindex;
    /**
     * 是否支持会员价格，支持会员价格为1，不支持为0
     */
    private String issupportmember;
    /**
     * 是否支持退换货，1为支持，如："00"不支持；"11"支持；"01"不支持退，支持换
     */
    private String exchange;
    private String topicno;
    private String sizeinfo;
    /**
     * 编辑推荐
     */
    private String recommend;
    /**
     * 商品状态码,共6位，依次为普通、新品、特价、促销、预售、预留（次位暂时为0），只
     * 能单选，即只能有一位是1；按位存储，0表示否，1表示是；如‘100000
     * ’表示普通，‘010000’表示新品，‘000100’表示促销，‘000010’表示预售
     */
    private String status;
    /**
     * 商品是否已经收藏 1为已经收藏 0 为未收藏
     */
    private String ismarked;
    private String favoriteProductid;
    /*** 商品详情图片 */
    private List<String> pics = new ArrayList<String>();
    /*** 基本信息 */
    private List<String> info = new ArrayList<String>();
    /**
     * 商品价格
     */
    private List<String> prices = new ArrayList<String>();
    /**
     * 特殊价格数组，0位为促销价格，当商品状态码为000100促销时，显示此数组中0位的价格；
     */
    private List<String> specialprice = new ArrayList<String>();
    
    private List<MerchandiseFirstprops> firstprops = new ArrayList<MerchandiseFirstprops>();

    /**
     * 商品尺码信息的原始数据
     */
    private SizeInfoII sizeinfoii;
    

    /**
     * @return the totalcount
     */
    public String getTotalcount() {
        return totalcount;
    }

    /**
     * @param totalcount
     *            the totalcount to set
     */
    public void setTotalcount(String totalcount) {
        this.totalcount = totalcount;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the rebate
     */
    public String getRebate() {
        return rebate;
    }

    /**
     * @param rebate
     *            the rebate to set
     */
    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    /**
     * @return the buyer
     */
    public String getBuyer() {
        return buyer;
    }

    /**
     * @param buyer
     *            the buyer to set
     */
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    /**
     * @return the firstpropname
     */
    public String getFirstpropname() {
        return firstpropname;
    }

    /**
     * @param firstpropname
     *            the firstpropname to set
     */
    public void setFirstpropname(String firstpropname) {
        this.firstpropname = firstpropname;
    }

    /**
     * @return the secondpropname
     */
    public String getSecondpropname() {
        return secondpropname;
    }

    /**
     * @param secondpropname
     *            the secondpropname to set
     */
    public void setSecondpropname(String secondpropname) {
        this.secondpropname = secondpropname;
    }

    /**
     * @return the shareurl
     */
    public String getShareurl() {
        return shareurl;
    }

    /**
     * @param shareurl
     *            the shareurl to set
     */
    public void setShareurl(String shareurl) {
        this.shareurl = shareurl;
    }

    /**
     * @return the goodscode
     */
    public String getGoodscode() {
        return goodscode;
    }

    /**
     * @param goodscode
     *            the goodscode to set
     */
    public void setGoodscode(String goodscode) {
        this.goodscode = goodscode;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * @param cod
     *            the cod to set
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     * @return the cate
     */
    public String getCate() {
        return cate;
    }

    /**
     * @param cate
     *            the cate to set
     */
    public void setCate(String cate) {
        this.cate = cate;
    }

    /**
     * @return the hassize
     */
    public String getHassize() {
        return hassize;
    }

    /**
     * @param hassize
     *            the hassize to set
     */
    public void setHassize(String hassize) {
        this.hassize = hassize;
    }

    /**
     * @return the systime
     */
    public String getSystime() {
        return systime;
    }

    /**
     * @param systime
     *            the systime to set
     */
    public void setSystime(String systime) {
        this.systime = systime;
    }

    /**
     * @return the specialinfo
     */
    public String getSpecialinfo() {
        return specialinfo;
    }

    /**
     * @param specialinfo
     *            the specialinfo to set
     */
    public void setSpecialinfo(String specialinfo) {
        this.specialinfo = specialinfo;
    }

    /**
     * @return the aftersale
     */
    public String getAftersale() {
        return aftersale;
    }

    /**
     * @param aftersale
     *            the aftersale to set
     */
    public void setAftersale(String aftersale) {
        this.aftersale = aftersale;
    }

    /**
     * @return the returnChangeRemind
     */
    public String getReturnChangeRemind() {
        return returnChangeRemind;
    }

    /**
     * @param returnChangeRemind
     *            the returnChangeRemind to set
     */
    public void setReturnChangeRemind(String returnChangeRemind) {
        this.returnChangeRemind = returnChangeRemind;
    }

    /**
     * @return the brandinfo
     */
    public String getBrandinfo() {
        return brandinfo;
    }

    /**
     * @param brandinfo
     *            the brandinfo to set
     */
    public void setBrandinfo(String brandinfo) {
        this.brandinfo = brandinfo;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand
     *            the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the priceindex
     */
    public String getPriceindex() {
        return priceindex;
    }

    /**
     * @param priceindex
     *            the priceindex to set
     */
    public void setPriceindex(String priceindex) {
        this.priceindex = priceindex;
    }

    /**
     * @return the issupportmember
     */
    public String getIssupportmember() {
        return issupportmember;
    }

    /**
     * @param issupportmember
     *            the issupportmember to set
     */
    public void setIssupportmember(String issupportmember) {
        this.issupportmember = issupportmember;
    }

    /**
     * @return the exchange
     */
    public String getExchange() {
        return exchange;
    }

    /**
     * @param exchange
     *            the exchange to set
     */
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    /**
     * @return the topicno
     */
    public String getTopicno() {
        return topicno;
    }

    /**
     * @param topicno
     *            the topicno to set
     */
    public void setTopicno(String topicno) {
        this.topicno = topicno;
    }

    /**
     * @return the sizeinfo
     */
    public String getSizeinfo() {
        return sizeinfo;
    }

    /**
     * @param sizeinfo
     *            the sizeinfo to set
     */
    public void setSizeinfo(String sizeinfo) {
        this.sizeinfo = sizeinfo;
    }

    /**
     * @return the recommend
     */
    public String getRecommend() {
        return recommend;
    }

    /**
     * @param recommend
     *            the recommend to set
     */
    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the ismarked
     */
    public String getIsmarked() {
        return ismarked;
    }

    /**
     * @param ismarked
     *            the ismarked to set
     */
    public void setIsmarked(String ismarked) {
        this.ismarked = ismarked;
    }

    /**
     * @return the favoriteProductid
     */
    public String getFavoriteProductid() {
        return favoriteProductid;
    }

    /**
     * @param favoriteProductid
     *            the favoriteProductid to set
     */
    public void setFavoriteProductid(String favoriteProductid) {
        this.favoriteProductid = favoriteProductid;
    }

    /**
     * @return the pics
     */
    public List<String> getPics() {
        return pics;
    }

    /**
     * @param pics
     *            the pics to set
     */
    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    /**
     * @return the info
     */
    public List<String> getInfo() {
        return info;
    }

    /**
     * @param info
     *            the info to set
     */
    public void setInfo(List<String> info) {
        this.info = info;
    }

    /**
     * @return the prices
     */
    public List<String> getPrices() {
        return prices;
    }

    /**
     * @param prices
     *            the prices to set
     */
    public void setPrices(List<String> prices) {
        this.prices = prices;
    }

    /**
     * @return the specialprice
     */
    public List<String> getSpecialprice() {
        return specialprice;
    }

    /**
     * @param specialprice
     *            the specialprice to set
     */
    public void setSpecialprice(List<String> specialprice) {
        this.specialprice = specialprice;
    }

    /**
     * @return the sizeinfoii
     */
    public SizeInfoII getSizeinfoii() {
        return sizeinfoii;
    }

    /**
     * @param sizeinfoii
     *            the sizeinfoii to set
     */
    public void setSizeinfoii(SizeInfoII sizeinfoii) {
        this.sizeinfoii = sizeinfoii;
    }
    
    

    /**
     * @return the firstprops
     */
    public List<MerchandiseFirstprops> getFirstprops() {
        return firstprops;
    }

    /**
     * @param firstprops the firstprops to set
     */
    public void setFirstprops(List<MerchandiseFirstprops> firstprops) {
        this.firstprops = firstprops;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MerchandiseVO [totalcount=" + totalcount + ", type=" + type + ", rebate=" + rebate + ", buyer=" + buyer + ", firstpropname=" + firstpropname + ", secondpropname="
                + secondpropname + ", shareurl=" + shareurl + ", goodscode=" + goodscode + ", name=" + name + ", cod=" + cod + ", cate=" + cate + ", hassize=" + hassize
                + ", systime=" + systime + ", specialinfo=" + specialinfo + ", aftersale=" + aftersale + ", returnChangeRemind=" + returnChangeRemind + ", brandinfo=" + brandinfo
                + ", brand=" + brand + ", priceindex=" + priceindex + ", issupportmember=" + issupportmember + ", exchange=" + exchange + ", topicno=" + topicno + ", sizeinfo="
                + sizeinfo + ", recommend=" + recommend + ", status=" + status + ", ismarked=" + ismarked + ", favoriteProductid=" + favoriteProductid + ", pics=" + pics
                + ", info=" + info + ", prices=" + prices + ", specialprice=" + specialprice + ", firstprops=" + firstprops + ", sizeinfoii=" + sizeinfoii + "]";
    }

    


}
