package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;


/** 
* @ClassName: OrderLogistics 
* @Description: 订单跟踪
* @author 李灵
* @date 2014年11月29日 
* @version 1.0 
*/
public class OrderLogistics  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String date;// 订单时间
	private String orderId;// 订单编号
	private List<Logistics> list;// 包裹信息
	private Receive receive;
	private Logistics logistics;// 页面应该显示的物流
	private Integer index;// 当前应该选中的包裹
	private String noticeInfo;
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<Logistics> getList() {
		return list;
	}

	public void setList(List<Logistics> list) {
		this.list = list;
	}

	public Receive getReceive() {
		return receive;
	}

	public void setReceive(Receive receive) {
		this.receive = receive;
	}

	public Logistics getLogistics() {
		return logistics;
	}

	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}

	public Integer getIndex() {
		return index;
	}

	

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getNoticeInfo() {
		return noticeInfo;
	}

	public void setNoticeInfo(String noticeInfo) {
		this.noticeInfo = noticeInfo;
	}

}
