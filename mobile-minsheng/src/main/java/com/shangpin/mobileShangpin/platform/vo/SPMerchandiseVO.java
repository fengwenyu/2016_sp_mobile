package com.shangpin.mobileShangpin.platform.vo;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.shangpin.mobileShangpin.common.util.Constants;

/**
 * 商品传输数据对象，用于前台展示
 * 
 */
@SuppressWarnings("unchecked")
public class SPMerchandiseVO {
	private String code;
	private String msg;
	// -------------------商品列表---------------------
	/*** 商品id */
	private String productid;
	/*** 商品名称 */
	private String productname;
	/*** 商品列表图片 */
	private String pic;
	/*** 库存数量 */
	private String count;
	/*** 商品价格列表；依次是正式、黄金、白金、钻石、市场 */
	private String[] prices;
	/*** 前端展示的价格索引从0开始 */
	private String priceindex;
	private String status;
	/*** 品牌id */
	private String brandid;
	/*** 品牌名称 */
	private String brandname;
	/*** 是否支持会员价格，支持会员价格为1，不支持为0 */
	private String issupportmember;
	// TODO:
	// -------------------待定---------------------
	/*** 品类id */
	private String categoryno;
	/*** 购物袋中价格 */
	private String amount;
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
	// -------------------商品详情---------------------
	/*** 商品详情图片 */
	private List<String> pics = new ArrayList<String>();
	/*** 库存数量 */
	private String totalcount;
	/*** 商品来自活动还是专题 0：专题；1：活动 */
	private String type;
	/*** 折扣 */
	private String rebate;
	/*** 买手说明 */
	private String buyer;
	/*** 基本信息 */
	private List<String> info = new ArrayList<String>();
	/*** 可变属性实体 */
	private List<MerchandiseFirstprops> firstprops = new ArrayList<MerchandiseFirstprops>();
	/*** 可变属性名称 */
	private String firstpropname;
	/*** 可变属性名称 */
	private String secondpropname;
	/*** 分享超链接 */
	private String shareurl;
	/*** 商品编号 */
	private String goodscode;
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
	/*** 品牌信息 */
	private String brandinfo;
	/*** 是否支持退换货；1为支持，如："00"不支持；"11"支持；"01"不支持退支持换 */
	private String exchange;
	/*** 尺码信息 */
	private String sizeinfo;
	/*** 商品价格列表；依次是正式、黄金、白金、钻石、市场 */
	private List<String> priceList;
	/*** 专题id */
	private String topicno;
	/*** 默认购买sku */
	private String defaultSku;
	/*** 分享图片 */
	private String sharepic;
	/*** 特殊价格 */
	private Object[] specialprice;

	/**
	 * 解析主站返回的商品详情json数据
	 * 
	 * @param jsonStr
	 *            主站返回的商品详情json数据
	 * 
	 * @Return 商品传输数据对象
	 */
	public SPMerchandiseVO json2Bean(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(code)) {
			obj = JSONObject.fromObject(obj.getJSONObject("content"));
			this.setPics(JSONArray.toList(obj.getJSONArray("pics"), String.class, new JsonConfig()));
			this.setTotalcount(obj.getString("totalcount"));
			this.setType(obj.getString("type"));
			this.setRebate(obj.getString("rebate"));
			this.setBuyer(obj.getString("buyer"));
			List<String> info=JSONArray.toList(obj.getJSONArray("info"), String.class, new JsonConfig());
			for(int i=0;i<info.size();i++){
				if(info.get(i).indexOf("商品编号")!=-1){//不显示商品编号
					info.remove(i);
				}
			}
			this.setInfo(info);
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
						// if(obj.getString("secondpropname") == null || "".equals(obj.getString("secondpropname"))){
						if (j == 0) {
							this.setDefaultSku(secondpropsArr.getJSONObject(j).getString("sku"));
						}
						// }
						merchandiseSecondprops.add(secondprop);
					}
					firstprop.setSecondprops(merchandiseSecondprops);
					firstprops.add(firstprop);
				}
				this.setFirstprops(firstprops);
			}
			this.setFirstpropname(obj.getString("firstpropname"));
			this.setSecondpropname(obj.getString("secondpropname"));
			this.setShareurl(obj.getString("shareurl"));
			this.setGoodscode(obj.getString("goodscode"));
			this.setCod(obj.getString("cod"));
			this.setCate(obj.getString("cate"));
			this.setHassize(obj.getString("hassize"));
			this.setSystime(obj.getString("systime"));
			this.setSpecialinfo(obj.getString("specialinfo"));
			this.setAftersale(obj.getString("aftersale"));
			this.setBrandinfo(obj.getString("brandinfo"));
			this.setBrandname(obj.getString("brand"));
			this.setProductname(obj.getString("name"));
			this.setPriceList(JSONArray.toList(obj.getJSONArray("prices"), String.class, new JsonConfig()));
			this.setPriceindex(obj.getString("priceindex"));
			this.setIssupportmember(obj.getString("issupportmember"));
			this.setExchange(obj.getString("exchange"));
			this.setTopicno(obj.getString("topicno"));
			this.setSizeinfo(obj.getString("sizeinfo"));
			this.setStatus(obj.getString("status"));
			this.setSharepic(pics.size() > 0 ? pics.get(0) : "");
			this.setSpecialprice(obj.getJSONArray("specialprice").toArray ());
		}
		return this;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getCategoryno() {
		return categoryno;
	}

	public void setCategoryno(String categoryno) {
		this.categoryno = categoryno;
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

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getPriceindex() {
		return priceindex;
	}

	public void setPriceindex(String priceindex) {
		this.priceindex = priceindex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String[] getPrices() {
		return prices;
	}

	public void setPrices(String[] prices) {
		this.prices = prices;
	}

	public String getIssupportmember() {
		return issupportmember;
	}

	public void setIssupportmember(String issupportmember) {
		this.issupportmember = issupportmember;
	}

	public String getSystime() {
		return systime;
	}

	public void setSystime(String systime) {
		this.systime = systime;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
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

	public String getSharepic() {
		return sharepic;
	}

	public void setSharepic(String sharepic) {
		this.sharepic = sharepic;
	}

	public String getTopicno() {
		return topicno;
	}

	public void setTopicno(String topicno) {
		this.topicno = topicno;
	}

	public List<String> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<String> priceList) {
		this.priceList = priceList;
	}

	public Object[] getSpecialprice() {
		return specialprice;
	}

	public void setSpecialprice(Object[] specialprice) {
		this.specialprice = specialprice;
	}

	@Override
	public String toString() {
		return "MerchandiseVO [code=" + code + ", msg=" + msg + ", brandid=" + brandid + ", brandname=" + brandname + ", productname=" + productname + ", productid=" + productid + ", categoryno=" + categoryno + ", pic=" + pic + ", count=" + count + ", type=" + type + ", cod=" + cod + ", cate=" + cate + ", hassize=" + hassize + ", pics=" + pics + ", totalcount=" + totalcount + ", rebate=" + rebate + ", buyer=" + buyer + ", shareurl=" + shareurl + ", specialinfo=" + specialinfo + ", aftersale=" + aftersale + ", brandinfo=" + brandinfo + ", goodscode=" + goodscode + ", info=" + info + ", firstpropname=" + firstpropname + ", secondpropname=" + secondpropname + ", firstprops=" + firstprops + "]";
	}
}
