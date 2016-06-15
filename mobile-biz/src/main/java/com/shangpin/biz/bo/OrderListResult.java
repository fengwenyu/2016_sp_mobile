package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 订单列表返回
 * @author zrs
 *
 */
public class OrderListResult implements Serializable{
	private static final long serialVersionUID = 1L;
	//订单信息集合
	private List<MainOrder> list ;
	
	private String systime ;
    private String noticeInfo;
    
    //页数
    private Integer pageIndex; 
    //是否有更多
    private String haveMore;
    //页数量
    private Integer pageSize = 10;
    

	public String getHaveMore() {
		return haveMore;
	}
	public void setHaveMore(String haveMore) {
		this.haveMore = haveMore;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public List<MainOrder> getList() {
		return list;
	}
	public void setList(List<MainOrder> list) {
		this.list = list;
	}
	public String getSystime() {
		return systime;
	}
	public void setSystime(String systime) {
		this.systime = systime;
	}
	public String getNoticeInfo() {
		return noticeInfo;
	}
	public void setNoticeInfo(String noticeInfo) {
		this.noticeInfo = noticeInfo;
	}
  
    
}
