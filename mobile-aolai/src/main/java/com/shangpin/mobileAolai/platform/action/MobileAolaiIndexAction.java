package com.shangpin.mobileAolai.platform.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.CookieUtil;
import com.shangpin.mobileAolai.common.util.DateTimeUtil;
import com.shangpin.mobileAolai.common.util.FileUtil;
import com.shangpin.mobileAolai.common.util.ProReader;
import com.shangpin.mobileAolai.common.util.SysContent;
import com.shangpin.mobileAolai.common.util.VenueEntrance;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.service.ActivitiesService;
import com.shangpin.mobileAolai.platform.vo.AccountVO;
import com.shangpin.mobileAolai.platform.vo.ActivitiesVO;
import com.shangpin.mobileAolai.platform.vo.TopicVO;
import com.shangpin.mobileAolai.platform.vo.VenueEntranceVO;

/**
 * 手机wap端首页面
 * 
 * @author:yumeng
 * @date:2012-10-29
 */
@Controller
@ParentPackage("mobileAolai")
@Scope("prototype")
@Actions({ @Action(value = ("/aolaiindex"), results = {
	    @Result(name = "index", location = "/WEB-INF/pages/index.jsp"),
//        @Result(name = "index", location = "/download.html"),
		@Result(name = "newActivities", location = "/WEB-INF/pages/index/activities.jsp"),
		@Result(name = "limitActivities", location = "/WEB-INF/pages/index/activities.jsp") }) })
public class MobileAolaiIndexAction extends ActionSupport {

	/*** 序列化ID */
	private static final long serialVersionUID = 8500004787932994994L;

	@Autowired
	ActivitiesService activitiesService;

	/*** 性别，0为女 */
	private String gender;
	private String pageType;

	/*** 轮播广告列表 */
	private List<TopicVO> topicCarouselList = new ArrayList<TopicVO>();

	/*** 最新热卖列表 */
	private List<ActivitiesVO> newActivitiesList = new ArrayList<ActivitiesVO>();

	/*** 限时特卖列表 */
	private List<ActivitiesVO> limitActivitiesList = new ArrayList<ActivitiesVO>();
	/** 活动时间 */
	private Map<String,String> map;
	// 分类导航内容
	private String typeContent;
	private String ch;
	@SuppressWarnings("unused")
	private String platform;
	private VenueEntranceVO venueEntranceVO;
	public String index() {
		gender = ServletActionContext.getRequest().getParameter("gender");
		if (null == gender) {
			AccountVO user = WebUtil.getSessionUser();
			if ((user != null && "1".equals(user.getGender()))
					|| "1".equals(gender)) {
				gender = "1";
			} else {
				gender = "0";
			}
		}
		//获取会场入口内容
		venueEntranceVO = VenueEntrance.getVenueEntranceContent();
		// 获取专题轮播广告列表
		topicCarouselList = activitiesService.getTopicCarouselListt(gender);//临时修改把参数gender换成1

		// 获取最新热卖列表，即当日最新活动列表
		Date date = new Date();
		newActivitiesList = activitiesService.getNewActivitiesList(gender,
				DateTimeUtil.shortFmt(date), DateTimeUtil.shortFmt(DateTimeUtil
						.getAppointDayFromToday(date, 1)));

		map = new HashMap<String,String>();
		map.put("flag", "");
		map.put("_flag", "");
		if(null != newActivitiesList && newActivitiesList.size()>0){
			long start = Long.parseLong( newActivitiesList.get(0).getStarttime());
			long cur = date.getTime();
			if(start > cur){
				map.put("startTime",DateTimeUtil.getTime(new Date(Long.parseLong(newActivitiesList.get(0).getStarttime()))));
				map.put("flag", "true");//true距活动开始、false距活动结束
			}else{
				map.put("flag", "false");
			}
		}
		// 获取限时特卖列表
		limitActivitiesList = activitiesService.getAllEndingActivitiesList(gender);
		if(null != limitActivitiesList && limitActivitiesList.size()>0){
			map.put("_endTime",DateTimeUtil.getTime(new Date(Long.parseLong(limitActivitiesList.get(0).getEndtime()))));
			map.put("_flag", "true");
		}
		map.put("nowTime", DateTimeUtil.getTime(date));
		return "index";
	}

	/**
	 * 跳转更多最新热卖活动列表页面
	 * 
	 * @return
	 */
	public String newactivities() {
		gender = ServletActionContext.getRequest().getParameter("gender");
		if (null == gender) {
			AccountVO user = WebUtil.getSessionUser();
			if ((user != null && "1".equals(user.getGender()))
					|| "1".equals(gender)) {
				gender = "1";
			} else {
				gender = "0";
			}
		}
		newActivitiesList = activitiesService.getAllActivitiesList(gender);
		return "newActivities";
	}
	/**
	 * 跳转至appstore
	 * 
	 * @return
	 */
	public String toAppStore() {
		try {
			String referer = SysContent.getRequest().getHeader("referer");
			String channelNo = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
			if (StringUtils.isEmpty(channelNo)) {
				if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
					channelNo = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
				}else{
					channelNo=StringUtils.isEmpty(channelNo)?Constants.AOLAI_WAP_DEFAULT_CHANNELNO:ch;
				}
				
			}	//	发起请求的渠道
			String productNo = SysContent.getRequest().getParameter("p");	//	发起请求的产品
			String store = SysContent.getRequest().getParameter("store");
//			String downloadProduct = SysContent.getRequest().getParameter("dp");	//	下载的哪个产品
			String downloadProduct=null;
			if("iphone".equals(store)){
				downloadProduct="1";
			}else if("android".equals(store)){
				downloadProduct="101";
			}
			//final String downloadProduct = "1";	//	下载的哪个产品
			if (productNo == null || productNo.length() == 0) {
				productNo = "";
			}
			// 记录访问日志
			FileUtil.addLog(SysContent.getRequest(), "aolaiindex!toAppStore", channelNo, "referer", referer, "p", downloadProduct);
			if (StringUtils.isNotEmpty(store)) {
				if ("iphone".equals(store.trim())) {
					SysContent.getResponse().sendRedirect("https://itunes.apple.com/cn/app/id432489082?mt=8");
				} else if ("android".equals(store.trim())) {
					SysContent.getResponse().sendRedirect(Constants.SHANGPIN_URL+"download.action?p=101&ch="+channelNo+"&fileName=aolai_"+channelNo+".apk");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 跳转更多限时特卖活动列表页面
	 * 
	 * @deprecated 此功能已经实现，经合适暂时不需要此功能
	 * 将pages/index.jsp中第170行注释去掉，即可开启此功能
	 * 
	 * @return
	 */
	@Deprecated
	public String limitactivities() {
		gender = ServletActionContext.getRequest().getParameter("gender");
		if (null == gender) {
			AccountVO user = WebUtil.getSessionUser();
			if ((user != null && "1".equals(user.getGender()))
					|| "1".equals(gender)) {
				gender = "1";
			} else {
				gender = "0";
			}
		}
		newActivitiesList = activitiesService
				.getAllEndingActivitiesList(gender);
		return "limitActivities";
	}

	public List<TopicVO> getTopicCarouselList() {
		return topicCarouselList;
	}

	public void setTopicCarouselList(List<TopicVO> topicCarouselList) {
		this.topicCarouselList = topicCarouselList;
	}

	public List<ActivitiesVO> getNewActivitiesList() {
		return newActivitiesList;
	}

	public void setNewActivitiesList(List<ActivitiesVO> newActivitiesList) {
		this.newActivitiesList = newActivitiesList;
	}

	public List<ActivitiesVO> getLimitActivitiesList() {
		return limitActivitiesList;
	}

	public void setLimitActivitiesList(List<ActivitiesVO> limitActivitiesList) {
		this.limitActivitiesList = limitActivitiesList;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public String getTypeContent() {
		if ("2".equals(pageType)) {
			typeContent = "预售商品";
		} else if ("01".equals(pageType)||"11".equals(pageType)) {
			typeContent = "最新特卖";
		} else if ("02".equals(pageType)||"12".equals(pageType)) {
			typeContent = "限时特卖";
		} else if ("03".equals(pageType)||"13".equals(pageType)) {
			typeContent = "活动专区";
		}
		return typeContent;
	}
	/**
	 * 重新读取不同渠道获取不同支付方式
	 */
	public static void loadIndex() {
		try {
			ProReader.load("paymentch");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setTypeContent(String typeContent) {
		this.typeContent = typeContent;
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

	public VenueEntranceVO getVenueEntranceVO() {
		return venueEntranceVO;
	}

	public void setVenueEntranceVO(VenueEntranceVO venueEntranceVO) {
		this.venueEntranceVO = venueEntranceVO;
	}
	
}
