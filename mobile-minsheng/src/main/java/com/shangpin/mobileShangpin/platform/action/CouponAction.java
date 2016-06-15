package com.shangpin.mobileShangpin.platform.action;

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
import com.shangpin.mobileShangpin.common.util.BaseAction;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.StringUtil;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.CouponService;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;

/**
 * 优惠券Action
 * 
 * @Author yumeng
 */
@Controller
@ParentPackage("mobileShangpin")
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

	/**
	 * 跳转到优惠券列表页面
	 * 
	 * @return
	 */
	public String couponlist() {
		String ch = (String) ServletActionContext.getRequest().getSession().getAttribute(Constants.CHANNEL_PARAM_NAME);
		if (ch == null) {
			ch = "";
		}
		ActionContext.getContext().getValueStack().set("ch", ch);
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		if (StringUtils.isEmpty(couponType))
			couponType = "0";
		entityJson = couponService.getCouponJson(user.getUserid(), couponType, "1");
		return "couponlist";
	}

	/**
	 * 获取Json格式的优惠券数据列表
	 * 
	 * @return
	 */
	public String ajaxcouponlist() {
		String ch = (String) ServletActionContext.getRequest().getSession().getAttribute(Constants.CHANNEL_PARAM_NAME);
		if (ch == null) {
			ch = "";
		}
		ActionContext.getContext().getValueStack().set("ch", ch);
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		entityJson = couponService.getCouponJson(user.getUserid(), couponType, "1");
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
}
