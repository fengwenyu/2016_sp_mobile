package com.shangpin.mobileAolai.platform.action;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangpin.mobileAolai.common.util.BaseAction;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.CookieUtil;
import com.shangpin.mobileAolai.common.util.StringUtil;
import com.shangpin.mobileAolai.common.util.SysContent;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.service.CouponService;
import com.shangpin.mobileAolai.platform.vo.AccountVO;

/**
 * 优惠券Action
 * 
 * @Author yumeng
 */
@Controller
@ParentPackage("mobileAolai")
@Scope("prototype")
@Actions({ @Action(value = ("/couponaction"), results = { //
@Result(name = "couponlist", location = "/WEB-INF/pages/coupon/couponList.jsp"), //
		@Result(name = "ajaxCouponList", type = "json", params = { "root", "entityJson" }), //
		@Result(name = "login", type = "redirect", location = "accountaction!loginui?loginFrom=2&ch=${ch}"), //
}) })
public class CouponAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4332355066780062232L;
	@Autowired
	private CouponService couponService;
	/** 优惠券类型 */
	private String couponType;
	/** 实体数据转json */
	private JSONObject entityJson;
	/** 信息提示 */
	private String msg;
	private String ch;
	@SuppressWarnings("unused")
	private String platform;

	/**
	 * 跳转到优惠券列表页面
	 * 
	 * @return
	 */
	public String couponlist() {
//		String ch = (String) ServletActionContext.getRequest().getSession().getAttribute(Constants.CHANNEL_PARAM_NAME);
//		ActionContext.getContext().getValueStack().set("ch", null ==ch?"":ch);
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		if (StringUtils.isEmpty(couponType))
			couponType = "0";
		entityJson = couponService.getCouponJson(user.getUserid(), couponType, "2");
		return "couponlist";
	}

	/**
	 * 获取Json格式的优惠券数据列表
	 * 
	 * @return
	 */
	public String ajaxcouponlist() {
//		String ch = (String) ServletActionContext.getRequest().getSession().getAttribute(Constants.CHANNEL_PARAM_NAME);
//		if (ch == null) {
//			ch = "";
//		}
//		ActionContext.getContext().getValueStack().set("ch", ch);
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		entityJson = couponService.getCouponJson(user.getUserid(), couponType, "2");
		return "ajaxCouponList";
	}

	public JSONObject getEntityJson() {
		return entityJson;
	}

	public void setEntityJson(JSONObject entityJson) {
		this.entityJson = entityJson;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
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
