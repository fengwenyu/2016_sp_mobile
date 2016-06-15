package com.shangpin.mobileShangpin.platform.vo;

import java.util.ArrayList;
import java.util.List;
/**
 * 购物车列表接口返回的deteil商品详情属性
 * 
 * @Author wangfeng
 * @CreateDate 2013-10-01
 */
public class MerchandiseDetail {
	/*** 特殊说明 */
	private String specialinfo;
	/*** 商品详情图片 */
	private List<String> pics = new ArrayList<String>();
	/*** 现价 */
	private String now;
	/*** 往期价格 */
	private String past;
	/*** 折扣 */
	private String rebate;
	private String propicon;
	/*** 库存数量 */
	private String  count;
	/*** 名称 */
	private String  productname;
	/*** 买手说明 */
	private String  buyer;
	private List<String> info = new ArrayList<String>();
	private String exchange;
	public String getSpecialinfo() {
		return specialinfo;
	}
	public void setSpecialinfo(String specialinfo) {
		this.specialinfo = specialinfo;
	}
	public List<String> getPics() {
		return pics;
	}
	public void setPics(List<String> pics) {
		this.pics = pics;
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
	public String getPropicon() {
		return propicon;
	}
	public void setPropicon(String propicon) {
		this.propicon = propicon;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public List<String> getInfo() {
		return info;
	}
	public void setInfo(List<String> info) {
		this.info = info;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	
	@Override
	public String toString() {
		return "MerchandiseDetail [exchange=" + exchange + ",info=" + info + ",buyer=" + buyer + ",now=" + now + ",past=" + past + ",rebate=" + rebate + ",propicon=" + propicon + ",count=" + count + ",productname=" + productname + ",specialinfo=" + specialinfo + ", pics=" + pics + ", now=" + now + "]";
	}
}
