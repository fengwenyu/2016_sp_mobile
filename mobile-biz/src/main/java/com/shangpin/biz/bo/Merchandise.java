package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 商品对象
 * 
 * @author cuibinqiang
 * @date 2014-10-31
 *
 */
public class Merchandise implements Serializable {
	private static final long serialVersionUID = -7979736418646492961L;
	private String code;
	private String msg;

	/*** 品牌 */
	private String brand;
	private String brandname;
	/*** 名称 */
	private String productname;
	/*** 现价 */
	private String now;
	/*** 往期价格 */
	private String past;
	/*** 商品id */
	private String goodsid;
	/*** 品类id */
	private String categoryno;
	/*** 图片地址 */
	private String picurl;
	/*** 库存数量 */
	private String count;
	/*** */
	private String subjectno;
	/*** 商品来自活动还是专题 0：专题；1：活动 */
	private String type;
	/*** 产品名称 */
	private String name;
	/*** 商品所在活动或者专题的起始时间 */
	private String starttime;
	/*** 商品所在活动或者专题的结束时间 */
	private String endtime;
	/*** 是否支持货到付款 */
	private String cod;
	/*** 品类 */
	private String cate;
	/*** 是否显示尺码说明 */
	private String hassize;
	/*** 是否支持退换货 */
	private String exchange;
	/*** 商品详情图片 */
	private List<String> pics;
	/*** 库存数量 */
	private String totalcount;
	/*** 折扣 */
	private String rebate;
	/*** 买手说明 */
	private String buyer;
	/*** 分享超链接 */
	private String shareurl;
	/*** 分享图片 */
	private String sharepic;
	/*** 特殊说明 */
	private String specialinfo;
	/*** 售后服务 */
	private String aftersale;
	/*** 品牌介绍 */
	private String brandinfo;
	/*** 商品编号 */
	private String goodscode;
	/*** 基本信息 */
	private String[] info;
	/*** 可变属性名称 */
	private String firstpropname;
	/*** 可变属性名称 */
	private String secondpropname;
	/*** 可变属性实体 */
	private List<MerchandiseFirstprops> firstprops;
	/*** 默认购买sku */
	private String defaultSku;

	private String returnChangeRemind;
	private List<String> priceList;
	private String recommend;
	private List<String> prices;
	private String priceindex;
	private String issupportmember;
	private String topicno;
	private List<String> specialprice;
	private String ismarked;
	private String favoriteProductid;
	private SizeInfo sizeinfoii;

	private String systime;
	private String sizeinfo;
	private String preheattime;
	private String ispreheat;
	private String status;

	public void setPriceindex(String priceindex) {
		this.priceindex = priceindex;
	}

	public SizeInfo getSizeinfoii() {
		return sizeinfoii;
	}

	public void setSizeinfoii(SizeInfo sizeinfoii) {
		this.sizeinfoii = sizeinfoii;
	}

	public String getIsmarked() {
		return ismarked;
	}

	public void setIsmarked(String ismarked) {
		this.ismarked = ismarked;
	}

	public String getFavoriteProductid() {
		return favoriteProductid;
	}

	public void setFavoriteProductid(String favoriteProductid) {
		this.favoriteProductid = favoriteProductid;
	}

	public List<String> getSpecialprice() {
		return specialprice;
	}

	public void setSpecialprice(List<String> specialprice) {
		this.specialprice = specialprice;
	}

	public String getTopicno() {
		return topicno;
	}

	public void setTopicno(String topicno) {
		this.topicno = topicno;
	}

	public String getIssupportmember() {
		return issupportmember;
	}

	public void setIssupportmember(String issupportmember) {
		this.issupportmember = issupportmember;
	}

	public String getPriceindex() {
		return priceindex;
	}

	public List<String> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<String> priceList) {
		this.priceList = priceList;
	}

	public String getSystime() {
		return systime;
	}

	public void setSystime(String systime) {
		this.systime = systime;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getReturnChangeRemind() {
		return returnChangeRemind;
	}

	public void setReturnChangeRemind(String returnChangeRemind) {
		this.returnChangeRemind = returnChangeRemind;
	}

	public List<String> getPrices() {
		return prices;
	}

	public void setPrices(List<String> prices) {
		this.prices = prices;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}

	public String getIspreheat() {
		return ispreheat;
	}

	public void setIspreheat(String ispreheat) {
		this.ispreheat = ispreheat;
	}

	public String getPreheattime() {
		return preheattime;
	}

	public void setPreheattime(String preheattime) {
		this.preheattime = preheattime;
	}

	public String getSizeinfo() {
		return sizeinfo;
	}

	public void setSizeinfo(String sizeinfo) {
		this.sizeinfo = sizeinfo;
	}

	public String getDefaultSku() {
		return defaultSku;
	}

	public void setDefaultSku(String defaultSku) {
		this.defaultSku = defaultSku;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	public String getPast() {
		return past;
	}

	public void setPast(String past) {
		this.past = past;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getCategoryno() {
		return categoryno;
	}

	public void setCategoryno(String categoryno) {
		this.categoryno = categoryno;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getSubjectno() {
		return subjectno;
	}

	public void setSubjectno(String subjectno) {
		this.subjectno = subjectno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public String getHassize() {
		return hassize;
	}

	public void setHassize(String hassize) {
		this.hassize = hassize;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
	}

	public String getRebate() {
		return rebate;
	}

	public void setRebate(String rebate) {
		this.rebate = rebate;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getShareurl() {
		return shareurl;
	}

	public void setShareurl(String shareurl) {
		this.shareurl = shareurl;
	}

	public String getSharepic() {
		return sharepic;
	}

	public void setSharepic(String sharepic) {
		this.sharepic = sharepic;
	}

	public String getSpecialinfo() {
		return specialinfo;
	}

	public void setSpecialinfo(String specialinfo) {
		this.specialinfo = specialinfo;
	}

	public String getAftersale() {
		return aftersale;
	}

	public void setAftersale(String aftersale) {
		this.aftersale = aftersale;
	}

	public String getBrandinfo() {
		return brandinfo;
	}

	public void setBrandinfo(String brandinfo) {
		this.brandinfo = brandinfo;
	}

	public String getGoodscode() {
		return goodscode;
	}

	public void setGoodscode(String goodscode) {
		this.goodscode = goodscode;
	}

	public String[] getInfo() {
		return info;
	}

	public void setInfo(String[] info) {
		this.info = info;
	}

	public String getFirstpropname() {
		return firstpropname;
	}

	public void setFirstpropname(String firstpropname) {
		this.firstpropname = firstpropname;
	}

	public String getSecondpropname() {
		return secondpropname;
	}

	public void setSecondpropname(String secondpropname) {
		this.secondpropname = secondpropname;
	}

	public List<MerchandiseFirstprops> getFirstprops() {
		return firstprops;
	}

	public void setFirstprops(List<MerchandiseFirstprops> firstprops) {
		this.firstprops = firstprops;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
