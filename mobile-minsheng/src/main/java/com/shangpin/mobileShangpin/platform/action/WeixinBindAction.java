package com.shangpin.mobileShangpin.platform.action;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.shangpin.mobileShangpin.base.model.Account;
import com.shangpin.mobileShangpin.base.model.AccountWeixinBind;
import com.shangpin.mobileShangpin.businessbean.TopicConfig;
import com.shangpin.mobileShangpin.common.util.BaseAction;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.DataContainerPool;
import com.shangpin.mobileShangpin.common.util.FileUtil;
import com.shangpin.mobileShangpin.common.util.ParseLogUtil;
import com.shangpin.mobileShangpin.common.util.ProReader;
import com.shangpin.mobileShangpin.common.util.StringUtil;
import com.shangpin.mobileShangpin.common.util.SysContent;
import com.shangpin.mobileShangpin.common.util.ThreeDES;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.AccountService;
import com.shangpin.mobileShangpin.platform.service.CaptchaService;
import com.shangpin.mobileShangpin.platform.service.CouponService;
import com.shangpin.mobileShangpin.platform.service.WeixinBindService;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;

/**
 * 微信绑定Action
 * 
 * @Author liling
 * @CreatDate 2013-08-01
 */
@Controller
@ParentPackage("mobileShangpin")
@Scope("prototype")
@Actions({ @Action(value = ("/weixinaction"), results = {//
@Result(name = "registerUI", location = "/WEB-INF/pages/account/weixinregisterbind.jsp"),//
		@Result(name = "loginUI", location = "/WEB-INF/pages/account/weixinloginbind.jsp"),//
		@Result(name = "binded", type = "redirect", location = "${responseUrl}"), //
		@Result(name = "index", type = "redirect", location = "spindex!index?ch=${ch}"), //
		@Result(name = "toCoupon", type = "redirect", location = "accountaction!coupon?ch=${ch}") }) })
public class WeixinBindAction extends BaseAction {
	private static final long serialVersionUID = -3550458496888919742L;
	@Autowired
	private AccountService accountService;
	@Autowired
	CouponService couponService;
	@Autowired
	CaptchaService captchaService;
	@Autowired
	WeixinBindService weixinBindService;
	/** 登录名 */
	private String loginName;
	/** 密码 */
	private String password;
	private String msg;
	/** 登录校验码 */
	private String captcha;
	/** 性别 */
	private String gender;
	private HttpServletRequest request = SysContent.getRequest();
	/** 微信用户所传参数 */
	private String wx;

	/**
	 * 判断是否已经绑定，如绑定则跳转到参数指定的跳转页面，若没绑定则跳转到登陆页
	 * 
	 * @Author liling
	 * @CreatDate 2013-08-01
	 */
	public String ok() throws Exception {
		wx = request.getParameter("wx");
		// System.out.println("1235    " + URLDecoder.decode(weixinParams));
		// 先解密
		wx = ThreeDES.decryptToString(wx);
		/** 微信id */
		String weixinid = wx.toString().split("[|]")[0];
		/** 微信用户想要访问的action的名称 */
		String responseUrl = wx.toString().split("[|]")[1];
		String strForCheck = "?";
		if (responseUrl.indexOf(strForCheck) > -1) {
			responseUrl = responseUrl + "&ch=" + Constants.SP_WEIXIN_CHANNELNO;
			System.out.println("0   " + responseUrl);
		} else {
			responseUrl = responseUrl + "?ch=" + Constants.SP_WEIXIN_CHANNELNO;
			System.out.println("1   " + responseUrl);
		}
		ActionContext.getContext().getValueStack().set("responseUrl", responseUrl);
		ServletActionContext.getRequest().getSession().setAttribute("weixinid", weixinid);
		ServletActionContext.getRequest().getSession().setAttribute("responseUrl", responseUrl);
		ActionContext.getContext().getValueStack().set("ch", Constants.SP_WEIXIN_CHANNELNO);
		// 判断是否已经绑定
		AccountWeixinBind accountWeixinBind = weixinBindService.findByWeixinId(weixinid);
		if (accountWeixinBind != null) {
			// 如果已经绑定，执行自动登录操作
			Account account = accountService.findByUserId(accountWeixinBind.getUserId());
			if (account != null) {
				// 自动登录成功后返回到responseUrl指定跳转页
				if (!weixinBindService.autologin(account)) {
					return "loginUI";
				} else {
					if (responseUrl.startsWith("weixinaction!loginbind")) {
						return "index";
					}
					return "binded";
				}
			}
		} else {
			// 跳转无访问限制的页面
			List<String> list = ProReader.getWxunlimitedurls();
			if (null != list && list.size() > 0) {
				for (String prefix : list) {
					if (responseUrl.startsWith(prefix)) {
						return "binded";
					}
				}
			}
		}
		return "loginUI";
	}

	/**
	 * 跳转至注册页面
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-29
	 * @param
	 * @Return
	 */
	public String weixinregisterui() {
		/** 微信id */
		String weixinid = (String) ServletActionContext.getRequest().getSession().getAttribute("weixinid");
		if (weixinid != null) {
			return "registerUI";
		}
		return "index";
	}

	/**
	 * 微信登录绑定
	 * 
	 * @Author liling
	 * @CreatDate 2013-08-01
	 */
	public String loginbind() throws Exception {
		/** 微信id */
		String weixinid = (String) ServletActionContext.getRequest().getSession().getAttribute("weixinid");
		if (weixinid != null) {
			String channelNo = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
			if (StringUtil.isNotEmpty(channelNo)) {
				SysContent.getRequest().getSession().setAttribute(Constants.CHANNEL_PARAM_NAME, channelNo);
				ActionContext.getContext().getValueStack().set(Constants.CHANNEL_PARAM_NAME, channelNo);
			} else {
				channelNo = Constants.SP_WAP_DEFAULT_CHANNELNO;
				SysContent.getRequest().getSession().removeAttribute(Constants.CHANNEL_PARAM_NAME);
			}
			String ip = SysContent.getRequest().getRemoteAddr();
			if (accountService.ipBlacklist(loginName, ip)) {
				msg = "您操作过于频繁，请稍后重试";
				return "loginUI";
			}
			ActionContext.getContext().getValueStack().set(Constants.SP_TOPIC_ID, ActionContext.getContext().getSession().get(Constants.SP_TOPIC_ID));
			String ua = SysContent.getRequest().getHeader("User-Agent");
			if (StringUtil.isNotEmpty(loginName, password)) {
				try {
					if (!captchaService.verifyCaptcha(ServletActionContext.getRequest().getSession(), this.getCaptcha())) {
						ActionContext.getContext().getValueStack().setValue("email", loginName);
						ActionContext.getContext().getValueStack().setValue("password", password);
						msg = "验证码错误，请重新输入";
						return "loginUI";
					}
					if (!accountService.login(loginName, password))
						return "loginUI";
					AccountVO user = WebUtil.getSessionUser();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = new Date();
					synchronized (user.getUserid().intern()) {
						Account account = accountService.getAccountByParams(loginName);
						Map<String, String> uaMap = ParseLogUtil.parseUA(ua);
						// 登录需要记录到account表的信息
						if (account != null) {
							if (uaMap != null) {
								account.setPlatform(uaMap.get("platform"));
								account.setPhoneModel(uaMap.get("phoneModel"));
							}
							account.setLoginTime(date);
							accountService.addAccount(account);
						} else {
							account = new Account();
							account.setChannel(StringUtils.isEmpty(channelNo) ? BigInteger.valueOf(4) : BigInteger.valueOf(Integer.valueOf(channelNo)));
							account.setProduct(BigInteger.valueOf(20000));
							account.setGender(user.getGender());
							account.setLoginName(user.getEmail());
							account.setCreateTime(date);
							account.setLoginTime(date);
							account.setRegTime(sdf.parse(user.getRegTime()));
							account.setRegOrigin(user.getRegOrigin());
							account.setUserId(user.getUserid());
							accountService.addAccount(account);
						}
						// 判断是否已经绑定，没有绑定则执行绑定操作
						AccountWeixinBind accountWeixinBind = weixinBindService.findByWeixinId(weixinid);
						if (accountWeixinBind == null) {
							// 绑定
							accountWeixinBind = new AccountWeixinBind();
							accountWeixinBind.setWeixinId(weixinid);
							accountWeixinBind.setCreateTime(new Date());
							accountWeixinBind.setLoginName(loginName);
							accountWeixinBind.setUserId(user.getUserid());
							accountWeixinBind.setNickname(user.getName());
							accountWeixinBind.setGender(user.getGender());
							accountWeixinBind.setRegTime(sdf.parse(user.getRegTime()));
							accountWeixinBind.setRegOrigin(user.getRegOrigin());
							accountWeixinBind.setBindTime(new Date());
							weixinBindService.addAccountWeixinBind(accountWeixinBind);
						} else {
						}
					}
					// 记录访问日志
					FileUtil.addLog(SysContent.getRequest(), "weixinaction!loginbind",//
							channelNo,//
							"username", loginName,//
							"code", user.getCode(),//
							"userId", user.getUserid(),//
							"regOrigin", user.getRegOrigin(),//
							"gender", user.getGender(),//
							"regTime", user.getRegTime(),//
							"createTime", sdf.format(date));//
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 跳转页面
				/** 微信用户想要访问的action的名称 */
				String responseUrl = (String) ServletActionContext.getRequest().getSession().getAttribute("responseUrl");
				ActionContext.getContext().getValueStack().set("responseUrl", responseUrl);
				if (responseUrl != null) {
					if (responseUrl.startsWith("weixinaction!loginbind")) {
						return "index";
					}
					return "binded";
				}
			} else {
				return "loginUI";
			}
		}
		return "index";
	}

	/**
	 * 微信绑定注册校验
	 * 
	 * @Author liling
	 * @CreatDate 2012-10-29
	 * 
	 */
	public String registerbind() {
		/** 微信id */
		String weixinid = (String) ServletActionContext.getRequest().getSession().getAttribute("weixinid");
		if (weixinid != null) {
			if (weixinBindService.isBind(weixinid)) {
				msg = "您已经绑定了尚品帐号，不能重复绑定操作！";
				return "registerUI";
			}
			// 只需要在注册完成后绑定微信号
			String channelNo = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
			String ua = SysContent.getRequest().getHeader("User-Agent");
			if (StringUtils.isEmpty(channelNo)) {
				channelNo = Constants.SP_WAP_DEFAULT_CHANNELNO;// wap尚品渠道号
				SysContent.getRequest().getSession().removeAttribute(Constants.CHANNEL_PARAM_NAME);
			} else {
				SysContent.getRequest().getSession().setAttribute(Constants.CHANNEL_PARAM_NAME, channelNo);
				ActionContext.getContext().getValueStack().set(Constants.CHANNEL_PARAM_NAME, channelNo);
			}
			if (StringUtil.isNotEmpty(loginName, password, gender)) {
				try {
					if (!captchaService.verifyCaptcha(ServletActionContext.getRequest().getSession(), this.getCaptcha())) {
						ActionContext.getContext().getValueStack().set("loginName",loginName);
						ActionContext.getContext().getValueStack().set("password",password);
						ActionContext.getContext().getValueStack().set("gender",gender);
						msg = "验证码错误，请重新输入";
						return "registerUI";
					}
					if (!accountService.register(loginName, password, gender, channelNo))
						return "registerUI";
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = new Date();
					AccountVO user = WebUtil.getSessionUser();
					synchronized (user.getUserid().intern()) {
						Account account = new Account();
						account.setChannel(BigInteger.valueOf(Long.valueOf(channelNo)));
						account.setProduct(BigInteger.valueOf(20000));// wap尚品产品号
						account.setGender(gender);
						account.setLoginName(user.getEmail());
						account.setCreateTime(date);
						account.setLoginTime(date);
						account.setRegTime(sdf.parse(user.getRegTime()));
						account.setRegOrigin(user.getRegOrigin());
						account.setUserId(user.getUserid());
						Map<String, String> uaMap = ParseLogUtil.parseUA(ua);
						if (uaMap != null) {
							account.setPlatform(uaMap.get("platform"));
							account.setPhoneModel(uaMap.get("phoneModel"));
						}
						accountService.addAccount(account);
						// 微信绑定
						AccountWeixinBind accountWeixinBind = new AccountWeixinBind();
						accountWeixinBind.setWeixinId(weixinid);
						accountWeixinBind.setCreateTime(new Date());
						accountWeixinBind.setLoginName(loginName);
						accountWeixinBind.setUserId(user.getUserid());
						accountWeixinBind.setNickname(user.getName());
						accountWeixinBind.setGender(user.getGender());
						accountWeixinBind.setRegTime(sdf.parse(user.getRegTime()));
						accountWeixinBind.setRegOrigin(user.getRegOrigin());
						accountWeixinBind.setBindTime(new Date());
						weixinBindService.addAccountWeixinBind(accountWeixinBind);
					}
					// 记录访问日志
					FileUtil.addLog(SysContent.getRequest(), "weixinaction!registerbind",//
							channelNo,//
							"username", loginName,//
							"code", user.getCode(),//
							"userId", user.getUserid(),//
							"regOrigin", user.getRegOrigin(),//
							"gender", user.getGender(),//
							"regTime", user.getRegTime(),//
							"createTime", sdf.format(date),//
							"loginTime", sdf.format(date));
					// 取缓存中的活动，如果此活动注册时发放优惠券，执行以下代码
					if (DataContainerPool.topicConfigContainer.getQueueSize() > 0) {
						// 通过渠道号在缓存中获取活动
						Object obj = DataContainerPool.topicConfigContainer.get(channelNo);
						if (null != obj) {
							TopicConfig topicConfig = (TopicConfig) obj;
							if (StringUtils.isNotEmpty(topicConfig.getCoupon())) {
								couponService.sendCoupon(user.getUserid(), "1", "coupon:" + topicConfig.getCoupon());
								return "toCoupon";
							}
						}
					}
					/** 微信用户想要访问的action的名称 */
					String responseUrl = (String) ServletActionContext.getRequest().getSession().getAttribute("responseUrl");
					ActionContext.getContext().getValueStack().set("responseUrl", responseUrl);
					if (responseUrl != null) {
						if (responseUrl.startsWith("weixinaction!loginbind")) {
							return "index";
						}
						return "binded";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				return "registerUI";
			}
		}
		return "index";
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getWx() {
		return wx;
	}

	public void setWx(String wx) {
		this.wx = wx;
	}
}
