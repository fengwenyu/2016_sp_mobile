package com.shangpin.mobileShangpin.platform.action;

import java.io.IOException;
import java.util.ArrayList;
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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.mobileShangpin.base.model.FindManage;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.DateTimeUtil;
import com.shangpin.mobileShangpin.common.util.FileUtil;
import com.shangpin.mobileShangpin.common.util.ProReader;
import com.shangpin.mobileShangpin.common.util.StringUtil;
import com.shangpin.mobileShangpin.common.util.SysContent;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.ActivitiesService;
import com.shangpin.mobileShangpin.platform.service.ActivityManageService;
import com.shangpin.mobileShangpin.platform.service.BrandService;
import com.shangpin.mobileShangpin.platform.service.MerchandiseService;
import com.shangpin.mobileShangpin.platform.service.SedCategoryService;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;
import com.shangpin.mobileShangpin.platform.vo.ActivitiesVO;

/**
 * 手机wap端首页面
 * 
 * @author:yumeng
 * @date:2012-10-29
 */
@Controller
@ParentPackage("mobileShangpin")
@Scope("prototype")
@Actions({ @Action(value = ("/spindex"), results = { @Result(name = "index", location = "/WEB-INF/pages/cmbc_women.jsp"),@Result(name = "manIndex", location = "/WEB-INF/pages/cmbc_man.jsp"), 
	@Result(name = "manindex", type = "redirect", location = "spindex!manIndex?gender=1&ch=${ch}"),//
	@Result(name = "minshenglogin", type = "redirect", location = "minshengbindaction!minshengLogin?phoneNumber=${phoneNumber}&ch=${ch}&loginFrom=5"),//
	@Result(name = "newActivities", location = "/WEB-INF/pages/index/activities.jsp"), @Result(name = "limitActivities", location = "/WEB-INF/pages/index/activities.jsp") }) })
//@Actions({ @Action(value = ("/shangpinindex"), results = { @Result(name = "index", location = "/WEB-INF/pages/index.jsp"), @Result(name = "manIndex", location = "/WEB-INF/pages/man.jsp"),@Result(name = "newActivities", location = "/WEB-INF/pages/index/activities.jsp"), @Result(name = "limitActivities", location = "/WEB-INF/pages/index/activities.jsp") }) })
public class MobileAolaiIndexAction extends ActionSupport {
	/*** 序列化ID */
	private static final long serialVersionUID = 8500004787932994994L;
	@Autowired
	ActivitiesService activitiesService;
	@Autowired
	MerchandiseService merchandistService;
	@Autowired
	SedCategoryService sedCategoryServiece;
	@Autowired
	BrandService brandServiece;
	@Autowired
	ActivityManageService activityManageService;
	/*** 性别，0为女 */
	private String gender;
	private String pageType;
	/*** 最新热卖列表 */
	private List<ActivitiesVO> newActivitiesList = new ArrayList<ActivitiesVO>();
	/** 活动时间 */
	private Map<String, String> map;
	// 分类导航内容
	private String typeContent;
	private List<FindManage> activityList= new ArrayList<FindManage>();
	/**
	 * 女士首页
	 * 
	 * @return
	 */
	public String index() {
		String channelNo = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(channelNo)) {
			channelNo = Constants.SP_WAP_DEFAULT_CHANNELNO;// wap尚品渠道号
			SysContent.getRequest().getSession().removeAttribute(Constants.CHANNEL_PARAM_NAME);
		} else {
			SysContent.getRequest().getSession().setAttribute(Constants.CHANNEL_PARAM_NAME, channelNo);
			ActionContext.getContext().getValueStack().set(Constants.CHANNEL_PARAM_NAME, channelNo);
		}
		
		gender = ServletActionContext.getRequest().getParameter("gender");
		AccountVO user = WebUtil.getSessionUser();
		String phoneNumber=SysContent.getRequest().getParameter("phoneNumber");
		SysContent.getRequest().getSession().setAttribute("phoneNumber", phoneNumber);
		ActionContext.getContext().getValueStack().set("phoneNumber", phoneNumber);		
		if(user==null){
			if(!"".equals(phoneNumber)&&phoneNumber!=null){
					return "minshenglogin";
				}
		}
		if (null == gender||"".equals(gender)) {
			if ((user != null && "1".equals(user.getGender())) || "1".equals(gender)) {				
				gender = "1";
				return "manindex";
			} else {
				gender = "0";
			}
		}
		if (null != user && null != user.getUserid()) {
		}
		try {
			activityList = activityManageService.findByActivityManage(DateTimeUtil.getDateTime(), "0");
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		return "index";
	}
	/**
	 * 男士首页
	 * 
	 * @return
	 */
	public String manIndex() {
		gender = ServletActionContext.getRequest().getParameter("gender");
		if (null == gender) {
			AccountVO user = WebUtil.getSessionUser();
			if ((user != null && "1".equals(user.getGender())) || "1".equals(gender)) {
				gender = "1";
			} else {
				gender = "0";
			}
		}
		// 获取专题轮播广告列表
		try {
			activityList = activityManageService.findByActivityManage(DateTimeUtil.getDateTime(), "0");
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		return "manIndex";
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
			if ((user != null && "1".equals(user.getGender())) || "1".equals(gender)) {
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
			String channelNo = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME); // 发起请求的渠道
			String productNo = SysContent.getRequest().getParameter("p"); // 发起请求的产品
			String store = SysContent.getRequest().getParameter("store");
			// String downloadProduct = SysContent.getRequest().getParameter("dp"); // 下载的哪个产品
			String downloadProduct=null;
			if("iphone".equals(store)){
				downloadProduct="2";
			}else if("android".equals(store)){
				downloadProduct="102";
			}
			//final String downloadProduct = "1"; // 下载的哪个产品
			if (productNo == null || productNo.length() == 0) {
				productNo = "";
			}
			if (!StringUtil.isNotEmpty(channelNo)) {
				channelNo = Constants.SP_WAP_DEFAULT_CHANNELNO;
				SysContent.getRequest().getSession().removeAttribute(Constants.CHANNEL_PARAM_NAME);
			} else {
				SysContent.getRequest().getSession().setAttribute(Constants.CHANNEL_PARAM_NAME, channelNo);
			}
			// 记录访问日志
			FileUtil.addLog(SysContent.getRequest(), "shangpinindex!toAppStore", channelNo, "referer", referer, productNo, downloadProduct);
			if (StringUtils.isNotEmpty(store)) {
				if ("iphone".equals(store.trim())) {
					SysContent.getResponse().sendRedirect("https://itunes.apple.com/cn/app/id598127498?mt=8");
				} else if ("android".equals(store.trim())) {
					SysContent.getResponse().sendRedirect("http://m.shangpin.com/appdownloadaction!download?p=102&ch=4&fileName=shangpin_mshangpin.apk");
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
	 * @deprecated 此功能已经实现，经合适暂时不需要此功能 将pages/index.jsp中第170行注释去掉，即可开启此功能
	 * 
	 * @return
	 */
	@Deprecated
	public String limitactivities() {
		gender = ServletActionContext.getRequest().getParameter("gender");
		if (null == gender) {
			AccountVO user = WebUtil.getSessionUser();
			if ((user != null && "1".equals(user.getGender())) || "1".equals(gender)) {
				gender = "1";
			} else {
				gender = "0";
			}
		}
		newActivitiesList = activitiesService.getAllEndingActivitiesList(gender);
		return "limitActivities";
	}


	public String getTypeContent() {
		if ("2".equals(pageType)) {
			typeContent = "预售商品";
		} else if ("01".equals(pageType) || "11".equals(pageType)) {
			typeContent = "最新特卖";
		} else if ("02".equals(pageType) || "12".equals(pageType)) {
			typeContent = "限时特卖";
		} else if ("03".equals(pageType) || "13".equals(pageType)) {
			typeContent = "活动专区";
		}
		return typeContent;
	}

	/**
	 * 重新读取渠道不同获取不同支付方式
	 */
	public static void loadPayment() {
		try {
			ProReader.load("paymentch");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setTypeContent(String typeContent) {
		this.typeContent = typeContent;
	}

	public void setActivitiesService(ActivitiesService activitiesService) {
		this.activitiesService = activitiesService;
	}

	public void setMerchandistService(MerchandiseService merchandistService) {
		this.merchandistService = merchandistService;
	}
	public List<FindManage> getActivityList() {
		return activityList;
	}
	public void setActivityList(List<FindManage> activityList) {
		this.activityList = activityList;
	}

	public List<ActivitiesVO> getNewActivitiesList() {
		return newActivitiesList;
	}

	public void setNewActivitiesList(List<ActivitiesVO> newActivitiesList) {
		this.newActivitiesList = newActivitiesList;
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

	

	
	
}
