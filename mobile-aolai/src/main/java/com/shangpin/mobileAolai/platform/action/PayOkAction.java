package com.shangpin.mobileAolai.platform.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.mobileAolai.common.unionPay.UnionUtil;
import com.shangpin.mobileAolai.common.util.WebUtil;

/**
 * 银联支付Action
 * 
 * @Author liling
 * @CreatDate 2013-07-20
 */
@Controller
@ParentPackage("mobileAolai")
@Scope("prototype")
@Actions({ @Action(value = ("/payokaction"), results = {
		@Result(name = "success", location = "/WEB-INF/pages/order/paysuccess.jsp"),
		@Result(name = "android", location = "/WEB-INF/pages/app/openandroidapp.html"),
		@Result(name = "ios", location = "/WEB-INF/pages/app/openiosapp.html"),
		@Result(name = "fail", location = "/WEB-INF/pages/order/payfail.jsp"),
		@Result(name = "cancel", location = "/WEB-INF/pages/order/payfail.jsp") }) })
public class PayOkAction extends ActionSupport {
	private static final long serialVersionUID = -1860110234612931273L;
	private static Log logInfo = LogFactory.getLog(PayOkAction.class);

	private HttpServletRequest request = WebUtil.getRequest();
	/** 订单金额 */
	private String amount;

	/** 订单ID */
	private String orderid;

	/**
	 * 原生浏览器跳转到下载控件页面
	 * 
	 * @param style
	 *            判断是andorid还是ios的原生浏览器(目前可认定：安卓系统除了UC QQ Opera 360都算其原生系统，ios
	 *            除了UC QQ都可认为是其原生浏览器)
	 * @Author liling
	 * @CreatDate 2013-08-01
	 */
	public String getClinet() {
		// 通过前台js判断
		String style = request.getParameter("style");
		return style;
	}

	/**
	 * 支付成功后跳转返回
	 * 
	 * @param argName
	 *            0代表成功，1代表失败，-1代表取消
	 * @Author liling
	 * @CreatDate 2013-08-01
	 */
	public String result() {
		// 获得通知参数
		String argName = request.getParameter("argname");
		String parmresult = request.getParameter("parmresult"); // 支付宝返回参数 格式为
																// “成功标志位-订单号-金额”

		amount = request.getParameter("amount");
		orderid = request.getParameter("orderid");
		if (argName != null) {
			if (argName.endsWith("0")) {

				// 银联支付成功返回页
				return "success";
			}
			// 银联支付失败
			if (argName.endsWith("1")) {
				return "fail";
			}
			// 银联支付取消
			if (argName.endsWith("-1")) {
				return "cancel";
			}
		} else if (parmresult != null) {// 支付宝支付
			String parms[] = parmresult.split("-");
			if ("0".equals(parms[0])) {
				orderid = parms[1];
				amount = parms[2];
				return "fail";
			} else if ("1".equals(parms[0])) {
				orderid = parms[1];
				amount = parms[2];
				return "success";
			}
		} else {
			// 此处用于货到付款跳转到支付成功页
			orderid = request.getParameter("orderid");
			return "success";
		}
		return null;
	}

	/**
	 * 接受银联异步支付通知
	 * 
	 * @Author liling
	 * @CreatDate 2013-08-01
	 */
	public void notifyReceiverUnipay() {
		logInfo.debug("unipay notify start!");

		try {

			@SuppressWarnings("unchecked")
			Map<String, String[]> notifyData = request.getParameterMap();
			// 验证签名，并更新支付状态
			UnionUtil.handleNotify(notifyData, UnionUtil.getSecret());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logInfo.debug("unipay notify end!");
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
}