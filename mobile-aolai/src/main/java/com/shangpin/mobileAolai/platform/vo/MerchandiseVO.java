package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.shangpin.mobileAolai.common.util.Constants;

/**
 * 商品传输数据对象，用于前台展示
 * 
 * @Author zhouyu
 * @CreateDate 2012-11-01
 */
@SuppressWarnings("unchecked")
public class MerchandiseVO implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = -3759795171616027410L;
    private String code;
	private String msg;
	// -------------------商品列表---------------------
	/*** 品牌 */
	private String brand;
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
	/*** 购物袋中价格 */
	private String amount;
	/*** 购物袋中图片 */
	private String pic;
	/*** 可变属性名称对应的值 */
	private String firstpropvalue;
	/*** 可变属性名称对应的值 */
	private String secondpropvalue;
	/*** 购物袋中skuid */
	private String sku;
	/*** 购物袋中商品错误码 */
	private String errorcode;
	/*** 购物袋中商品错误码描述 */
	private String errormsg;
	
	// ------------------购物袋列表---------------------
	private String totalamount;
	/**商品名称*/
	private String goodsname;
	/**商品是否可以购买，1不可以购买(售罄或下架)*/
	private String hidden;
	/**商品的唯一标识、唯一标识串*/
	private String gid;
	/**组合商品的序号,同组的商品序号相同，如：1、2、3等，非组合商品此属性赋值为空字符串*/
	private String groupno;
	/**组合商品总价格*/
	private String groupprice;
	/**与原价比优惠的价格*/
	private String groupdiscount;
	/**组合商品的组id，非组合商品此属性赋值为空字符串*/
	private String  groupid;
	/**购物车详情id，删除时根据此属性及gid删除商品*/
	private String shoppingcartdetailid;
	/**购物车商品详情属性*/
	private MerchandiseDetail detail;
	
	

	// -------------------商品详情---------------------
	/*** 商品来自活动还是专题 0：专题；1：活动 */
	private String type;
	/*** 商品所在活动或者专题的起始时间 */
	private String starttime;
	/*** 商品所在活动或者专题的结束时间 */
	private String endtime;
	/*** 系统时间 */
	private String systime;
	/*** 是否支持货到付款 */
	private String cod;
	/*** 品类 */
	private String cate;
	/*** 是否显示尺码说明 */
	private String hassize;
	/*** 商品详情图片 */
	private List<String> pics = new ArrayList<String>();
	/*** 库存数量 */
	private String totalcount;
	/*** 折扣 */
	private String rebate;
	/*** 折扣 (带折字)*/
	private String rebateNew;
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
	/*** 库存数量 */
	private String brandinfo;
	/*** 商品编号 */
	private String goodscode;
	/*** 基本信息 */
	private List<String> info = new ArrayList<String>();
	/*** 可变属性名称 */
	private String firstpropname;
	/*** 可变属性名称 */
	private String secondpropname;
	/*** 可变属性实体 */
	private List<MerchandiseFirstprops> firstprops = new ArrayList<MerchandiseFirstprops>();
	/*** 默认购买sku */
	private String defaultSku;
	/***是否支持退换货 */
	private String exchange;
	//编辑推荐
	private String recommend;
	
	/**
	 * 解析主站返回的json数据
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-11-05
	 * @param jsonStr
	 *            主站返回的json数据
	 * @Return
	 */
	public MerchandiseVO json2Bean(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(code)) {
			obj = JSONObject.fromObject(obj.getJSONObject("content"));
			this.setRecommend(obj.getString("recommend"));
			this.setType(obj.getString("type"));
			this.setBrand(obj.getString("brand"));
			this.setProductname(obj.getString("name"));
			this.setStarttime(obj.getString("starttime"));
			this.setEndtime(obj.getString("endtime"));
			this.setSystime(obj.getString("systime"));
			if("-1".equals(obj.getString("starttime"))&&"-1".equals(obj.getString("endtime")))
				this.setMsg("该商品活动已结束或商品已下架！");
			this.setHassize(obj.getString("hassize"));
			this.setNow(obj.getString("now"));
			this.setPast(obj.getString("past"));
			this.setCod(obj.getString("cod"));
			this.setCate(obj.getString("cate"));
			this.setPics(JSONArray.toList(obj.getJSONArray("pics"),
					String.class, new JsonConfig()));
			this.setSharepic(pics.size() > 0 ? pics.get(0) : "");
			this.setTotalcount(obj.getString("totalcount"));
			this.setCategoryno(obj.getString("categoryno"));
			this.setRebate(obj.getString("rebate"));
			this.setBuyer(obj.getString("buyer"));
			this.setShareurl(obj.getString("shareurl"));
			this.setSpecialinfo(obj.getString("specialinfo"));
			this.setAftersale(obj.getString("aftersale"));
			this.setBrandinfo(obj.getString("brandinfo"));
			this.setGoodscode(obj.getString("goodscode"));
			this.setInfo(JSONArray.toList(obj.getJSONArray("info"),
					String.class, new JsonConfig()));
			this.setFirstpropname(obj.getString("firstpropname"));
			this.setSecondpropname(obj.getString("secondpropname"));
			JSONArray firstpropsArr = obj.getJSONArray("firstprops");
			if (firstpropsArr.size() > 0) {
				for (int i = 0; i < firstpropsArr.size(); i++) {
					MerchandiseFirstprops firstprop = new MerchandiseFirstprops();
					JSONObject firstpropsJsonObj = firstpropsArr.getJSONObject(i);
					firstprop.setFirstprop(firstpropsJsonObj.getString("firstprop"));
					firstprop.setIcon(firstpropsJsonObj.getString("icon"));
					JSONArray secondpropsArr = firstpropsJsonObj.getJSONArray("secondprops");
					List<MerchandiseSecondprops> merchandiseSecondprops = new ArrayList<MerchandiseSecondprops>();
					int size = secondpropsArr.size();
					for (int j = 0; j < size; j++) {
						MerchandiseSecondprops secondprop = new MerchandiseSecondprops();
						secondprop.setSku(secondpropsArr.getJSONObject(j).getString("sku"));
						secondprop.setSecondprop(secondpropsArr.getJSONObject(j).getString("secondprop"));
						secondprop.setCount(secondpropsArr.getJSONObject(j).getString("count"));
//						if(obj.getString("secondpropname") == null || "".equals(obj.getString("secondpropname"))){
						if(j==0&&i==0){
							this.setDefaultSku(secondpropsArr.getJSONObject(j).getString("sku"));
						}
//						}
						merchandiseSecondprops.add(secondprop);
					}
					firstprop.setSecondprops(merchandiseSecondprops);
					firstprops.add(firstprop);
				}
				this.setFirstprops(firstprops);
			}
		}
		return this;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getFirstpropvalue() {
		return firstpropvalue;
	}

	public void setFirstpropvalue(String firstpropvalue) {
		this.firstpropvalue = firstpropvalue;
	}

	public String getSecondpropvalue() {
		return secondpropvalue;
	}

	public void setSecondpropvalue(String secondpropvalue) {
		this.secondpropvalue = secondpropvalue;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getSystime() {
		return systime;
	}

	public void setSystime(String systime) {
		this.systime = systime;
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

	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
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

	public List<String> getInfo() {
		return info;
	}

	public void setInfo(List<String> info) {
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

	public List<MerchandiseFirstprops> getFirstprops() {
		return firstprops;
	}

	public void setFirstprops(List<MerchandiseFirstprops> firstprops) {
		this.firstprops = firstprops;
	}

	@Override
	public String toString() {
		return "MerchandiseVO [code=" + code + ", msg=" + msg + ", brand="
				+ brand + ", productname=" + productname + ", now=" + now
				+ ", past=" + past + ", goodsid=" + goodsid + ", categoryno="
				+ categoryno + ", picurl=" + picurl + ", count=" + count
				+ ", type=" + type + ", starttime=" + starttime + ", endtime="
				+ endtime + ", cod=" + cod + ", cate=" + cate + ", hassize="
				+ hassize + ", pics=" + pics + ", totalcount=" + totalcount
				+ ", rebate=" + rebate + ", buyer=" + buyer + ", shareurl="
				+ shareurl + ", specialinfo=" + specialinfo + ", aftersale="
				+ aftersale + ", brandinfo=" + brandinfo + ", goodscode="
				+ goodscode + ", info=" + info + ", firstpropname="
				+ firstpropname + ", secondpropname=" + secondpropname
				+ ", firstprops=" + firstprops + ", gid=" + gid + ", rebateNew=" + rebateNew
				+ ", systime=" + systime + "]";
	}

	public String getSharepic() {
		return sharepic;
	}

	public void setSharepic(String sharepic) {
		this.sharepic = sharepic;
	}

	public String getDefaultSku() {
		return defaultSku;
	}

	public void setDefaultSku(String defaultSku) {
		this.defaultSku = defaultSku;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGroupno() {
		return groupno;
	}

	public void setGroupno(String groupno) {
		this.groupno = groupno;
	}

	public String getGroupprice() {
		return groupprice;
	}

	public void setGroupprice(String groupprice) {
		this.groupprice = groupprice;
	}

	public String getGroupdiscount() {
		return groupdiscount;
	}

	public void setGroupdiscount(String groupdiscount) {
		this.groupdiscount = groupdiscount;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getShoppingcartdetailid() {
		return shoppingcartdetailid;
	}

	public void setShoppingcartdetailid(String shoppingcartdetailid) {
		this.shoppingcartdetailid = shoppingcartdetailid;
	}

	public MerchandiseDetail getDetail() {
		return detail;
	}

	public void setDetail(MerchandiseDetail detail) {
		this.detail = detail;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getRebateNew() {
		return rebateNew;
	}

	public void setRebateNew(String rebateNew) {
		this.rebateNew = rebateNew;
	}

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
	
}
