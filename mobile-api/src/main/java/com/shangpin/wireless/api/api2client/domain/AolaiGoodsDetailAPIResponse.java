package com.shangpin.wireless.api.api2client.domain;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.StringUtil;

public class AolaiGoodsDetailAPIResponse {

	private static final String HEAD = "<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=0.6,minimum-scale=0.6,maximum-scale=0.6\"></head>";

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
	private String systime; // 服务器时间
	private String shareurl; // 分享链接(网站的商品详情页)
	private String firstpropname; // 第一动态属性
	private String secondpropname; // 第二动态属性
	private String specialinfo; // 特殊说明
	private String aftersale; // 售后服务
	private JSONArray pics; // 图片地址
	private JSONArray info; // 商品基本信息
	private JSONArray firstprops; // 商品属性对应的sku数组
	private String name;// 商品名称
	private String brand;// 品牌名称
	private String preheattime;// 预热时间（如果没有预热返回""）
	private String infourl; // 商品信息对应url
	private String subjectno;// 活动编号

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
			content.put("type", type);
			content.put("categoryno", categoryno);
			content.put("cate", cate);
			content.put("hassize", hassize);
			content.put("now", now);
			content.put("past", past);
			content.put("rebate", rebate);

			if (StringUtil.isNotEmpty(rebate)) {
				String x = new StringBuffer().append(rebate).append("折").toString();
				content.put("rebateNew", x);
			} else {
				content.put("rebateNew", rebate);
			}

			content.put("totalcount", totalcount);
			content.put("buyer", buyer == null ? "" : buyer.trim());
			content.put("cod", cod);
			content.put("starttime", starttime);
			content.put("endtime", endtime);
			content.put("systime", systime);
			content.put("shareurl", shareurl);
			content.put("infourl", infourl);
			content.put("name", name);
			content.put("brand", brand);
			content.put("firstpropname", firstpropname);
			content.put("secondpropname", secondpropname);
			content.put("specialinfo", specialinfo);
			content.put("aftersale", aftersale.length() == 0 ? aftersale : (HEAD + aftersale));
			content.put("pics", pics == null ? "[]" : pics.toString());
			content.put("info", info == null ? "[]" : info.toString());
			content.put("firstprops", firstprops == null ? "[]" : firstprops.toString());
			content.put("subjectno", subjectno);
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

	public String getInfourl() {
		return infourl;
	}

	public void setInfourl(String infourl) {
		this.infourl = infourl;
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

	public String getSystime() {
		return systime;
	}

	public void setSystime(String systime) {
		this.systime = systime;
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

	public String getSubjectno() {
		return subjectno;
	}

	public void setSubjectno(String subjectno) {
		this.subjectno = subjectno;
	}

	/**
	 * 设置颜色缩略图宽高
	 * 
	 * @param url
	 *            图片url
	 * @return 图片url
	 */
	private String setThumbnail(String url, String size) {
		String res = "";
		if (StringUtils.isNotEmpty(url)) {
			StringBuffer buffer = new StringBuffer();
			try {
				String suffix = url.substring(url.lastIndexOf('.'));
				String[] array = url.split("-");
				buffer.append(array[0]).append(size).append(suffix);
				res = buffer.toString();
			} catch (Exception e) {
				e.printStackTrace();
				res = "";
			}
		}
		return res;
	}

	public String obj2Json(String size) {
		JSONObject obj = new JSONObject();
		obj.put("code", code);
		obj.put("msg", msg);
		JSONObject content = new JSONObject();
		if (Constants.SUCCESS.equals(code)) {
			content.put("type", type);
			content.put("categoryno", categoryno);
			content.put("cate", cate);
			content.put("hassize", hassize);
			content.put("now", now);
			content.put("past", past);
			String x = new StringBuffer().append(rebate).append("折").toString();
			content.put("rebate", rebate);
			content.put("rebateNew", x);
			content.put("totalcount", totalcount);
			content.put("buyer", (buyer == null || buyer.length() < 5) ? "" : buyer.trim());
			content.put("cod", cod);
			content.put("starttime", starttime);
			content.put("endtime", endtime);
			content.put("systime", systime);
			content.put("shareurl", shareurl);
			content.put("infourl", infourl);
			content.put("name", name);
			content.put("brand", brand);
			content.put("firstpropname", firstpropname);
			content.put("secondpropname", secondpropname);
			content.put("specialinfo", specialinfo);
			content.put("aftersale", aftersale.length() == 0 ? aftersale : (HEAD + aftersale));
			content.put("pics", pics == null ? "[]" : pics.toString());
			content.put("info", info == null ? "[]" : info.toString());
			content.put("subjectno", subjectno);
			// 添加颜色缩略图
			String firstpropsTmp = "[]";
			if (firstprops != null) {
				JSONArray propsJArray = new JSONArray();
				JSONArray picArray=new JSONArray();
				for (int i = 0; i < firstprops.size(); i++) {
					JSONObject propsJObj = firstprops.getJSONObject(i);
					picArray=propsJObj.getJSONArray("pics");
					if(picArray!=null){
					    String url = picArray.getString(0);
					    propsJObj.put("thumbnail", setThumbnail(url, size));
					}					
					propsJArray.add(propsJObj);
				}
				firstpropsTmp = propsJArray.toString();
			}
			content.put("firstprops", firstpropsTmp);
		}
		obj.put("content", content);
		return obj.toString();
	}

}
