package com.shangpin.wireless.api.api2client.domain;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.StringUtil;

public class SPGoodsDetailAPIResponse {
	private static final String HEAD = "<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=0.6,minimum-scale=0.6,maximum-scale=0.6\"><link href=\"http://spscc.shangpincdn.com/ResourceHandler.ashx?f=~/shangpin/Content/css/core/css&amp;v=B2468645201AD7509445F8C3DC0412C4\" rel=\"stylesheet\" type=\"text/css\" /><link href=\"http://spscc.shangpincdn.com/ResourceHandler.ashx?f=~/shangpin/Content/css/page/new_detail/css&amp;v=818953B8BF21808D57F972DA8EDF290C\" rel=\"stylesheet\" type=\"text/css\" /></head><body>";
	private static final String SIZEINFOHEAD_OLD = "<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=0.8,minimum-scale=0.4,maximum-scale=1.0\"><link href=\"http://spscc.shangpincdn.com/ResourceHandler.ashx?f=~/shangpin/Content/css/core/css&amp;v=B2468645201AD7509445F8C3DC0412C4\" rel=\"stylesheet\" type=\"text/css\" /><link href=\"http://spscc.shangpincdn.com/ResourceHandler.ashx?f=~/shangpin/Content/css/page/new_detail/css&amp;v=818953B8BF21808D57F972DA8EDF290C\" rel=\"stylesheet\" type=\"text/css\" /></head><body>";
	private static final String SIZEINFOHEAD ="<html lang=\"zh-CN\"><head><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0\"><meta name=\"apple-mobile-web-app-capable\" content=\"yes\"><meta name=\"apple-touch-fullscreen\" content=\"yes\"><meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\"><meta name=\"format-detection\" content=\"telephone=no\"><link href=\""+Constants.BASE_M_SHANGPIN_URL +"styles/shangpin/css/base.css\" rel=\"stylesheet\" /><link href=\""+Constants.BASE_M_SHANGPIN_URL +"styles/shangpin/css/page/size.css\" rel=\"stylesheet\" /><script src=\""+Constants.BASE_M_SHANGPIN_URL +"styles/shangpin/js/core.js\" type=\"text/javascript\" charset=\"utf-8\"></script><script type=\"text/javascript\" charset=\"utf-8\">loader = SP.core.install(\""+Constants.BASE_M_SHANGPIN_URL +"styles/shangpin/js/zepto.min.js\").using(\""+Constants.BASE_M_SHANGPIN_URL +"styles/shangpin/js/lazyload.js\").excute().using(\""+Constants.BASE_M_SHANGPIN_URL +"styles/shangpin/js/config.sim.js\").using(\""+Constants.BASE_M_SHANGPIN_URL +"styles/shangpin/js/iscroll.js\" ).using(\""+Constants.BASE_M_SHANGPIN_URL +"styles/shangpin/js/size.js\").excute(function(){loaded();});</script></head><body>";
	private String code;
	private String msg;
	private String totalcount; // 剩余库存
	private String type; // 活动or专题
	// private String rebate; // 折扣
	private String buyer; // 买手推荐
	private String firstpropname; // 第一动态属性
	private String secondpropname; // 第二动态属性
	private String shareurl; // 分享链接(网站的商品详情页)
	private String goodscode; // 商品编号
	private String cod; // 是否支持货到付款
	private String cate; // 所属类别
	private String specialinfo; // 特殊说明
	private String aftersale; // 售后服务
	private String name;// 商品名称
	private String brand;// 品牌名称
	private String priceindex;// 商品价格索引
	private String issupportmember;// 是否支持会员
	private String exchange;// 是否支持退换货
	private String topicno;// 专题编号
	private String sizeinfo;// 尺码描述
	private String infourl; //尚品信息对应url
	private String sizeinfoii;// 尺码描述
	private String status;// 商品属性
	private JSONArray prices;// 商品价格
	private JSONArray pics; // 图片地址
	private JSONArray info; // 商品基本信息
	private JSONArray firstprops; // 商品属性对应的sku数组
	private JSONArray specialprice;// 特殊价格
	private String isMarked;
	private String favoriteProductid;//商品id
	private String hasSize;
	private String returnChangeRemind;//温馨提示
	/**
	 * 返给客户端的json数据
	 * 
	 * @Author: wangwenguan
	 * @CreatDate: 2012-11-2
	 * @param
	 * @Return
	 */
	public String obj2Json() {
		JSONObject obj = new JSONObject();
		obj.put("code", code);
		obj.put("msg", msg);
		JSONObject content = new JSONObject();
		if (Constants.SUCCESS.equals(code)) {
			content.put("totalcount", totalcount);
			content.put("type", type);
			// content.put("rebate", rebate);
			if (buyer == null) {
				buyer = "";
			} else {
				buyer = buyer.trim();
				if (buyer.length() <= 5) {
					buyer = "";
				}
			}
			content.put("hassize", hasSize);
			content.put("ismarked",  isMarked);
			content.put("favoriteproductid", favoriteProductid);
			content.put("buyer", buyer);
			content.put("firstpropname", firstpropname);
			content.put("secondpropname", secondpropname);
			if(cate != null){
				if(cate.startsWith("A01")){
					content.put("shareurl", shareurl.replace("/men/product", "/women/product"));
				}else if(cate.startsWith("A02")){
					content.put("shareurl", shareurl.replace("/women/product", "/men/product"));
				}
			}
			content.put("goodscode", goodscode);
			content.put("cod", cod);
			content.put("cate", cate);
			content.put("specialinfo",  specialinfo== null ? "" : specialinfo.trim());
			content.put("aftersale", aftersale.length() == 0 ? aftersale : (HEAD + aftersale));
			content.put("name", name);
			content.put("brand", brand);
			content.put("priceindex", priceindex);
			content.put("issupportmember", issupportmember);
			content.put("exchange", exchange);
			content.put("topicno", topicno);
			if (!sizeinfo.equals(sizeinfoii)) {
				content.put("sizeinfo", sizeinfo.length() == 0 ? "" : (SIZEINFOHEAD_OLD + sizeinfo + "</body></html>"));
			} else {
				content.put("sizeinfo", sizeinfo.length() == 0 ? "" : (SIZEINFOHEAD + sizeinfo + "</body></html>"));
			}
			content.put("infourl", infourl);
			content.put("status", status);
			content.put("prices", prices == null ? "[]" : prices.toString());
			content.put("pics", pics == null ? "[]" : pics.toString());
			if(StringUtil.isNotEmpty(returnChangeRemind)){
				info.add(returnChangeRemind.replace("<br/>", ""));
			}
			content.put("info", info == null ? "[]" : info.toString());
			content.put("firstprops", firstprops == null ? "[]" : firstprops.toString());
			content.put("specialprice", specialprice == null ? "[]" : specialprice.toString());
		}
		obj.put("content", content);
		return obj.toString();
	}

	/**
	 * 返回简要商品的json数据(购物袋快照)
	 */
	public String obj2SummaryJson() {
		JSONObject obj = new JSONObject();
		obj.put("code", code);
		obj.put("msg", msg);
		JSONObject content = new JSONObject();
		if (Constants.SUCCESS.equals(code)) {
			content.put("totalcount", totalcount);
			content.put("type", type);
			// content.put("rebate", rebate);
			content.put("buyer", buyer == null ? "" : buyer.trim());
			content.put("firstpropname", firstpropname);
			content.put("secondpropname", secondpropname);
			content.put("shareurl", shareurl);
			content.put("goodscode", goodscode);
			content.put("cod", cod);
			content.put("cate", cate);
			content.put("specialinfo", specialinfo);
			content.put("aftersale", aftersale.length() == 0 ? aftersale : (HEAD + aftersale));
			content.put("name", name);
			content.put("brand", brand);
			content.put("priceindex", priceindex);
			content.put("issupportmember", issupportmember);
			content.put("exchange", exchange);
			content.put("topicno", topicno);
			content.put("sizeinfo", sizeinfo.length() == 0 ? sizeinfo : (SIZEINFOHEAD + sizeinfo + "</body></html>"));
			content.put("infourl", infourl);
			content.put("status", status);
			content.put("prices", prices == null ? "[]" : prices.toString());
			content.put("pics", pics == null ? "[]" : pics.toString());
			content.put("info", info == null ? "[]" : info.toString());
			content.put("firstprops", firstprops == null ? "[]" : firstprops.toString());
			content.put("specialprice", specialprice == null ? "[]" : specialprice.toString());
		}
		obj.put("content", content);
		return obj.toString();
	}
	/**
	 * 返回简要商品的json数据
	 */
	public String obj2InfoJson() {
		JSONObject obj = new JSONObject();
		obj.put("code", code);
		obj.put("msg", msg);
		JSONObject content = new JSONObject();
		if (Constants.SUCCESS.equals(code)) {
			content.put("totalcount", totalcount);
			content.put("firstpropname", firstpropname);
			content.put("secondpropname", secondpropname);
			content.put("cod", cod);
			content.put("priceindex", priceindex);
			content.put("issupportmember", issupportmember);
			content.put("exchange", exchange);
			content.put("infourl", infourl);
			content.put("status", status);
			content.put("prices", prices == null ? "[]" : prices.toString());
			content.put("firstprops", firstprops == null ? "[]" : firstprops.toString());
		}
		obj.put("content", content);
		return obj.toString();
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

	public String getGoodscode() {
		return goodscode;
	}

	public void setGoodscode(String goodscode) {
		this.goodscode = goodscode;
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

	public String getInfourl() {
		return infourl;
	}

	public void setInfourl(String infourl) {
		this.infourl = infourl;
	}

	public String getAftersale() {
		return aftersale;
	}

	public void setAftersale(String aftersale) {
		if (aftersale == null)
			aftersale = "";
		aftersale = aftersale.trim();
		this.aftersale = aftersale;
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

	public String getPriceindex() {
		return priceindex;
	}

	public void setPriceindex(String priceindex) {
		this.priceindex = priceindex;
	}

	public String getIssupportmember() {
		return issupportmember;
	}

	public void setIssupportmember(String issupportmember) {
		this.issupportmember = issupportmember;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getTopicno() {
		return topicno;
	}

	public void setTopicno(String topicno) {
		this.topicno = topicno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JSONArray getPrices() {
		return prices;
	}

	public void setPrices(JSONArray prices) {
		this.prices = prices;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSizeinfo() {
		return sizeinfo;
	}

	public void setSizeinfo(String sizeinfo) {
		this.sizeinfo = sizeinfo;
	}

	public String getSizeinfoii() {
		return sizeinfoii;
	}

	public void setSizeinfoii(String sizeinfoii) {
		this.sizeinfoii = sizeinfoii;
	}

	public JSONArray getSpecialprice() {
		return specialprice;
	}

	public void setSpecialprice(JSONArray specialprice) {
		this.specialprice = specialprice;
	}


	public String getIsMarked() {
		return isMarked;
	}

	public void setIsMarked(String isMarked) {
		this.isMarked = isMarked;
	}

	public String getFavoriteProductid() {
		return favoriteProductid;
	}

	public void setFavoriteProductid(String favoriteProductid) {
		this.favoriteProductid = favoriteProductid;
	}

	public String getHasSize() {
		return hasSize;
	}

	public void setHasSize(String hasSize) {
		this.hasSize = hasSize;
	}

	public String getReturnChangeRemind() {
		return returnChangeRemind;
	}

	public void setReturnChangeRemind(String returnChangeRemind) {
		this.returnChangeRemind = returnChangeRemind;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}
	
	
	
	
}
