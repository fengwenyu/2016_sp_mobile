package com.shangpin.mobileAolai.platform.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.CookieUtil;
import com.shangpin.mobileAolai.common.util.DateUtil;
import com.shangpin.mobileAolai.common.util.StringUtil;
import com.shangpin.mobileAolai.common.util.SysContent;
import com.shangpin.mobileAolai.platform.service.PresaleService;
import com.shangpin.mobileAolai.platform.vo.ActivitiesVO;
import com.shangpin.mobileAolai.platform.vo.PresaleVO;

/**
 * 预售模块
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-30
 */
@Controller
@ParentPackage("mobileAolai")
@Scope("prototype")
@Actions({ @Action(value = ("/presaleaction"), results = {//
@Result(name = "presaleList", location = "/WEB-INF/pages/market/calendar.jsp") }) })
public class PresaleAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1134211385464668299L;
	@Autowired
	private PresaleService presaleService;
	private String id;
	private String startTime;
	private String endTime;
	private String showContent;
	private String ch;
	@SuppressWarnings("unused")
	private String platform;

	/**
	 * 进入预售列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-30
	 * @param
	 * @Return
	 */
	public String presalelist() {
		// 活动列表
		List<ActivitiesVO> activityList = new ArrayList<ActivitiesVO>();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// 首次进入预售页面的时候默认显示当前日期次日的数据
			if (StringUtil.isNotEmpty(id, startTime, endTime)) {
				if (id.matches("^[0-9]*$")) {
					// 拼接页面展示数据：“11月02日 星期五 预售商品”
					showContent = sdf.format(DateUtil.getAfterAmountDay(date, Integer.parseInt(id))) + "&nbsp" + //
							DateUtil.dateToWeek(DateUtil.getAfterAmountDay(date, Integer.parseInt(id))) + "&nbsp" + //
							"预售商品";
					// 获取日期对应的活动列表
					activityList = presaleService.activityList(startTime, endTime);
				}
			} else {
				// 拼接页面展示数据：“11月02日 星期五 预售商品”
				showContent = sdf.format(DateUtil.getAfterAmountDay(date, 1)) + "&nbsp" + //
						DateUtil.dateToWeek(DateUtil.getAfterAmountDay(date, 1)) + "&nbsp" + //
						"预售商品";
				// 获取日期对应的活动列表
				activityList = presaleService.activityList(sdf1.format(DateUtil.getAfterAmountDay(date, 1)), //
						sdf1.format(DateUtil.getAfterAmountDay(date, 2)));
			}
			// 日期列表
			List<PresaleVO> presaleList = new ArrayList<PresaleVO>();
			presaleList = presaleService.dateList(date, id, startTime, endTime);
			// 将数据放入ValueStack中传给页面
			ActionContext.getContext().getValueStack().set("presaleList", presaleList);
			ActionContext.getContext().getValueStack().set("activityList", activityList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "presaleList";
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShowContent() {
		return showContent;
	}

	public void setShowContent(String showContent) {
		this.showContent = showContent;
	}

	public String getCh() {
		ch = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(ch)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
			}else{
				ch=StringUtils.isEmpty(ch)?Constants.AOLAI_WAP_DEFAULT_CHANNELNO:ch;
			}
			
		}
		return ch;
	}

	public void setCh(String ch) {
		ch = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(ch)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
			}else{
				ch=StringUtils.isEmpty(ch)?Constants.AOLAI_WAP_DEFAULT_CHANNELNO:ch;
			}
			
		}
		this.ch = ch;
	}

	public String getPlatform() {
		Object platform = SysContent.getSession().getAttribute("platform");
		if(platform != null) {
			return (String) platform;
		}
		return "";
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
}
