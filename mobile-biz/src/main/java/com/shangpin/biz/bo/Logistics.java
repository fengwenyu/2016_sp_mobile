package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/** 
* @ClassName: Logistics 
* @Description: 新版物流
* @author 李灵
* @date 2014年11月29日 
* @version 1.0 
*/
public class Logistics  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String logo;
	private String pkgname;
	private String express;// 物流公司
	private String ticketno;// 订单号
	private String date;//订单时间
	private String status;// 订单状态
	private	String warehouse;//仓库信息
	private String statusdesc;// 订单状态描述
	private List<ProductLogistics> products;//订单商品
	private List<LogisticsStatus> logistics;//物流信息
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getPkgname() {
		return pkgname;
	}
	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public String getTicketno() {
		return ticketno;
	}
	public void setTicketno(String ticketno) {
		this.ticketno = ticketno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	 
	public String getStatusdesc() {
		return statusdesc;
	}
	public void setStatusdesc(String statusdesc) {
		this.statusdesc = statusdesc;
	}
	public List<ProductLogistics> getProducts() {
		return products;
	}
	public void setProducts(List<ProductLogistics> products) {
		this.products = products;
	}
	public List<LogisticsStatus> getLogistics() {
		return logistics;
	}
	public void setLogistics(List<LogisticsStatus> logistics) {
		this.logistics = logistics;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	
}
