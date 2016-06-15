package com.shangpin.mobileShangpin.platform.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangpin.mobileShangpin.common.page.Page;
import com.shangpin.mobileShangpin.common.util.BaseAction;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.LogisticeService;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;
import com.shangpin.mobileShangpin.platform.vo.LogisticeVO;

/**
 * 物流相关模块
 * 
 * @Author wangfeng
 * @CreatDate 2013-7-31
 */
@Controller
@ParentPackage("mobileShangpin")
@Scope("prototype")
@Actions({ @Action(value = ("/logisticeaction"), results = {//
@Result(name = "detail", location = "/WEB-INF/pages/account/logistics_detail.jsp"),
@Result(name = "info", location = "/WEB-INF/pages/account/logistics.jsp"),
@Result(name = "toLoginUI", type = "redirect", location = "accountaction!loginui?loginFrom=3&ch=${ch}"), 
@Result(name = "getMoreList", type = "json", params = { "includeProperties", "logisticeList.*,pageIndex,haveMore" })
}) })
public class LogisticeAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private LogisticeService logisticeService;

	private List<LogisticeVO> logisticeList=new ArrayList<LogisticeVO>();//物流详情集合
	private String orderId;//订单号
	private String express;//物流
	private String  pageIndex;
	// 判断是否有加载更多
	private Integer haveMore;
	public String logisticeDetail(){
		AccountVO user = WebUtil.getSessionUser();
		String userId = "";
		if (null != user && null != user.getUserid()) {
			userId = user.getUserid();
		}
		else{
			return "toLoginUI";
		}
		orderId = ServletActionContext.getRequest().getParameter("orderId");
		String ticketNo = ServletActionContext.getRequest().getParameter("ticketNo");
		try {
			logisticeList=logisticeService.getLogisticeList(orderId, userId, ticketNo);
			if(logisticeList!=null){
				express=logisticeList.get(0).getExpress();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "detail";
	}
	public String logisticeInfo(){
		System.out.print("start---logistice");
		AccountVO user = WebUtil.getSessionUser();
		System.out.println(WebUtil.getSessionUser());
		String userId = "";
		if (null != user && null != user.getUserid()) {
			System.out.print("logi....");
			userId = user.getUserid();
		}else{
			return "toLoginUI";
		}
		try {
			logisticeList=logisticeService.getLogisticeOrderList(userId,"1");	
			pageIndex="2";
			if(!"null".equals(logisticeList)){
				if (logisticeList.size() < Page.DEFAULT_PAGE_SIZE) {
					haveMore = 0;
				} else {
					haveMore = 1;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "info";
	}
	
	public String logisticeByOrderDetail(){
		AccountVO user = WebUtil.getSessionUser();
		String userId = "";
		if (null != user && null != user.getUserid()) {
			userId = user.getUserid();
		}
		else{
			return "toLoginUI";
		}
		orderId = ServletActionContext.getRequest().getParameter("orderid");
		String ticketNo=logisticeService.getOrderLogistic(userId,orderId);
		try {
			logisticeList=logisticeService.getLogisticeList(orderId, userId, ticketNo);
			if(logisticeList!=null){
				express=logisticeList.get(0).getExpress();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "detail";
	}
	/**
	 * 获取Json格式的订单数据列表
	 * 
	 * @return
	 */
	public String getMoreList() {
		AccountVO user = WebUtil.getSessionUser();
		String userId = "";
		if (null != user && null != user.getUserid()) {
			userId = user.getUserid();
		}
		try {
			logisticeList=logisticeService.getLogisticeOrderList(userId,pageIndex);
			if (logisticeList.size() < Page.DEFAULT_PAGE_SIZE) {
				haveMore = 0;
			} else {
				haveMore = 1;
			}
			pageIndex=String.valueOf((Integer.parseInt(pageIndex)+1));
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return "getMoreList";
	}

	public void setLogisticeList(List<LogisticeVO> logisticeList) {
		this.logisticeList = logisticeList;
	}

	public void setLogisticeService(LogisticeService logisticeService) {
		this.logisticeService = logisticeService;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}
	public String getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}
	public List<LogisticeVO> getLogisticeList() {
		return logisticeList;
	}
	public Integer getHaveMore() {
		return haveMore;
	}
	public void setHaveMore(Integer haveMore) {
		this.haveMore = haveMore;
	}


	
}
