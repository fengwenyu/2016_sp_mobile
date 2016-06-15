package com.shangpin.wireless.api.api2server.domain;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

public class AolaiGoodsDetailServerResponse {

	private String code;
	private String msg;
	private String type; // 活动or专题
	private String categoryno; // 活动编号
	private String goodscode; // 商品编号
	private String cate; // 所属类别
	private String hassize; // 是否需要尺码对照表
	private String now; // 限时价格
	private String past; // 商品原价
	private String rebate; // 折扣
	private String totalcount; // 剩余库存
	private String buyer; // 买手推荐
	private String cod; // 是否支持货到付款
	private String starttime; // 活动or专题的开始时间
	private String endtime; // 活动or专题的结束时间
	private String shareurl; // 分享链接(网站的商品详情页)
	private String firstpropname; // 第一动态属性
	private String secondpropname; // 第二动态属性
	private String specialinfo; // 特殊说明
	private String aftersale; // 售后服务
	private String brandinfo; // 品牌介绍
	private JSONArray pics; // 图片地址
	private JSONArray info; // 商品基本信息
	private JSONArray firstprops; // 商品属性对应的sku数组
	private String name;// 商品名称
	private String brand;// 品牌名称
	private String preheattime;//预热时间（如果没有预热返回""）
	private String systime;

	/**
	 * 解析主站返回的json数据
	 * 
	 * @Author: wangwenguan
	 * @CreatDate: 2012-11-2
	 * @param
	 * @Return
	 */
	public AolaiGoodsDetailServerResponse json2Obj(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(code)) {
			obj = JSONObject.fromObject(obj.getJSONObject("content"));
			setType(obj.getString("type"));
			setCategoryno(obj.getString("categoryno"));
			setGoodscode(obj.getString("goodscode"));
			setCate(obj.getString("cate"));
			setHassize(obj.getString("hassize"));
			setNow(obj.getString("now"));
			setPast(obj.getString("past"));
			setRebate(obj.getString("rebate"));
			setTotalcount(obj.getString("totalcount"));
			setBuyer(obj.getString("buyer"));
			setCod(obj.getString("cod"));
			setStarttime(obj.getString("starttime"));
//			Object toplistObj=DataContainerPool.topicNosContainer.get("toplist");
//			
//			if(toplistObj!=null){
//				long now = System.currentTimeMillis()/1000;
//				List<String> toplist=(List<String>)toplistObj;
//				for(int i=0;i<toplist.size();i++){
//					if((obj.getString("subjectno").trim()).equals(toplist.get(i))){
//						setStarttime(String.valueOf(now));	
//						break;
//					}
//				}
//			}
			setSystime(String.valueOf(System.currentTimeMillis()));
			setEndtime(obj.getString("endtime"));
			setShareurl(obj.getString("shareurl"));
			setFirstpropname(obj.getString("firstpropname"));
			setSecondpropname(obj.getString("secondpropname"));
			setSpecialinfo(obj.getString("specialinfo"));
			setAftersale(obj.getString("aftersale"));
			setBrandinfo(obj.getString("brandinfo"));
			setPics(obj.getJSONArray("pics"));
			setInfo(obj.getJSONArray("info"));
			setFirstprops(obj.getJSONArray("firstprops"));
			setName(obj.getString("name"));
			setBrand(obj.getString("brand"));
		}
		return this;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategoryno() {
		return categoryno;
	}

	public void setCategoryno(String categoryno) {
		this.categoryno = categoryno;
	}

	public String getGoodscode() {
		return goodscode;
	}

	public void setGoodscode(String goodscode) {
		this.goodscode = goodscode;
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

	public String getRebate() {
		return rebate;
	}

	public void setRebate(String rebate) {
		this.rebate = rebate;
	}

	public String getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
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

	public String getShareurl() {
		return shareurl;
	}

	public void setShareurl(String shareurl) {
		this.shareurl = shareurl;
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

	public JSONArray getPics() {
		return pics;
	}

	public void setPics(JSONArray pics) {
		this.pics = pics;
	}

	public JSONArray getInfo() {
		return info;
	}

	public void setInfo(JSONArray info) {
		this.info = info;
	}

	public JSONArray getFirstprops() {
		return firstprops;
	}

	public void setFirstprops(JSONArray firstprops) {
		this.firstprops = firstprops;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPreheattime() {
		return preheattime;
	}

	public void setPreheattime(String preheattime) {
		this.preheattime = preheattime;
	}

	public String getSystime() {
		return systime;
	}

	public void setSystime(String systime) {
		this.systime = systime;
	}
	
}
