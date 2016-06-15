package com.shangpin.mobileAolai.platform.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.shangpin.mobileAolai.common.page.Page;
import com.shangpin.mobileAolai.common.page.PageContext;
import com.shangpin.mobileAolai.common.util.BaseAction;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.CookieUtil;
import com.shangpin.mobileAolai.common.util.StringUtil;
import com.shangpin.mobileAolai.common.util.SysContent;
import com.shangpin.mobileAolai.platform.service.MerchandiseService;
import com.shangpin.mobileAolai.platform.vo.ActivityVO;

/**
 * 商品模块
 * 
 * @Author zhouyu
 * @CreatDate 2012-11-01
 */
@Controller
@ParentPackage("mobileAolai")
@Scope("prototype")

@Actions({ @Action(value = ("/merchandiseaction"), results = {//
@Result(name = "list", location = "/WEB-INF/pages/market/merchandiseList.jsp"),//
		@Result(name = "detail", location = "/WEB-INF/pages/market/merchandiseDetail.jsp"),//
		@Result(name = "aolaiDetail", location = "/WEB-INF/pages/market/aolaiMerchandiseDetail.jsp"),
		@Result(name = "A01B01", location = "/WEB-INF/pages/market/size_A01B01.jsp"),//
		@Result(name = "A01B02", location = "/WEB-INF/pages/market/size_A01B02.jsp"),//
		@Result(name = "A02B01", location = "/WEB-INF/pages/market/size_A02B01.jsp"),//
		@Result(name = "getMore", type = "json", params = { "root", "data" }),
		@Result(name = "toIndex", type = "redirect", location = "aolaiindex!index"),
		@Result(name = "A02B02", location = "/WEB-INF/pages/market/size_A02B02.jsp") }) })
public class MerchandiseAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	MerchandiseService merchandiseService;
	private String pageType;
	// 分类导航内容
	private String typeContent;
	// 活动id
	private String activityId;
	// 活动名称
	private String activityName;
	// 活动起始时间
	private String startTime;
	// 活动结束时间
	private String endTime;
	// 当前系统时间
	@SuppressWarnings("unused")
	private String systemTime;
	// 活动是否开启 0：关闭，1：开启
	private String openFlag;
	// 进入商品详情页面的时候区分来自活动还是专题 1：活动，0：专题
	private String typeFlag;
	// 加载更多的时候传输对象
	private JSONObject data;
	// 判断是否有加载更多
	private Integer haveMore;
	private String ch;
	@SuppressWarnings("unused")
	private String platform;
	
	/**
	 * 进入商品列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-01
	 * @param
	 * @Return
	 */
	public String list() {
		try {
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// System.out.println(sdf.sformat(new Date(Long.valueOf(startTime))));
			ActivityVO activityVO = merchandiseService.activityVO(activityId,typeFlag, 1, Page.DEFAULT_PAGE_SIZE);
			if (activityVO.getMerchandiseList().size() < Page.DEFAULT_PAGE_SIZE) {
				haveMore = 0;
			} else {
				haveMore = 1;
			}
			openFlag = activityVO.getOpenFlag();
			startTime = activityVO.getStartTime();
			endTime = activityVO.getEndTime();
			if("3".equals(openFlag)){
				return "toIndex";
			}
			this.setActivityName(activityVO.getName());
			ActionContext.getContext().getValueStack().set("merchandiseList", activityVO.getMerchandiseList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	/**
	 * 商品详情页面
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-02
	 * @param
	 * @Return
	 */
	public String detail() {
		String categoryno = ServletActionContext.getRequest().getParameter("categoryno");
		String goodsid = ServletActionContext.getRequest().getParameter("goodsid");
		if (StringUtil.isNotEmpty(goodsid, typeFlag, categoryno)) {
			try {
				merchandiseService.merchandiseDetail(goodsid, typeFlag, categoryno);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "detail";
	}
	
	/**
	 * 奥莱商品详情页面
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-02
	 * @param
	 * @Return
	 */
	public String aolaiDetail() {
		String categoryno = ServletActionContext.getRequest().getParameter("categoryno");
		String goodsid = ServletActionContext.getRequest().getParameter("goodsid");
		String type = ServletActionContext.getRequest().getParameter("type");
		if (StringUtil.isNotEmpty(goodsid, type, categoryno)) {
			try {
				merchandiseService.merchandiseDetail(goodsid, type, categoryno);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "aolaiDetail";
	}

	/**
	 * 尺码说明
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-02
	 * @param
	 * @Return
	 */
	public String sizedesc() {
		String cate = ServletActionContext.getRequest().getParameter("cate");
		return cate;
	}
	/**
	 * 加载更多
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-22
	 * @param
	 * @Return
	 */
	public String getmore() {
		try {
			ActivityVO activityVO = merchandiseService.activityVO(activityId,
					typeFlag,
					PageContext.getOffset() == 0 ? 1 : PageContext.getOffset(),
					Page.DEFAULT_PAGE_SIZE);
			data = new JSONObject();
			data.put("merchandiseList",JSONArray.fromObject(activityVO.getMerchandiseList()));
			
			
			
			data.put("haveMore",activityVO.getMerchandiseList().size() < Page.DEFAULT_PAGE_SIZE ? "0" : "1");// 1:有，0:没有
			data.put("pageIndex", PageContext.getOffset() == 0 ? 1 : PageContext.getOffset());
			data.put("code", 1);
			data.put("openFlag", activityVO.getOpenFlag());
			data.put("typeFlag", typeFlag);
			data.put("pageType", pageType);
			data.put("activityName", activityName);
			data.put("activityId", activityId);
			ServletActionContext.getResponse().setContentType("text/html");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

	public void setTypeContent(String typeContent) {
		this.typeContent = typeContent;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		String decode = "";
		try {
			decode = URLDecoder.decode(activityName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decode;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
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

	public String getSystemTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(System.currentTimeMillis()));
	}

	public String getOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public Integer getHaveMore() {
		return haveMore;
	}

	public void setHaveMore(Integer haveMore) {
		this.haveMore = haveMore;
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
