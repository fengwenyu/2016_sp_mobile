package com.shangpin.mobileAolai.platform.action;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import weibo4sina.util.URLEncodeUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.mobileAolai.base.model.Account;
import com.shangpin.mobileAolai.common.annotation.AppAuthAnnotation;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.CookieUtil;
import com.shangpin.mobileAolai.common.util.DataContainerPool;
import com.shangpin.mobileAolai.common.util.FileUtil;
import com.shangpin.mobileAolai.common.util.ParseLogUtil;
import com.shangpin.mobileAolai.common.util.SendCoupon;
import com.shangpin.mobileAolai.common.util.StringUtil;
import com.shangpin.mobileAolai.common.util.SysContent;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.service.AccountService;
import com.shangpin.mobileAolai.platform.service.CaptchaService;
import com.shangpin.mobileAolai.platform.service.CouponService;
import com.shangpin.mobileAolai.platform.service.SysParametersService;
import com.shangpin.mobileAolai.platform.vo.AccountVO;
import com.shangpin.mobileAolai.platform.vo.ConsigneeAddressVO;
import com.shangpin.mobileAolai.platform.vo.ProvinceVO;

/**
 * 帐号管理Action
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-29
 */
@Controller
@ParentPackage("mobileAolai")
@Scope("prototype")
@SuppressWarnings("unchecked")
@Actions({ @Action(value = ("/accountaction"), results = {//
		@Result(name = "registerUI", location = "/WEB-INF/pages/account/register.jsp"),//
		@Result(name = "suject", type = "redirect", location = "allcartaction!listCart"),
		@Result(name = "sendcoupon", location = "/WEB-INF/pages/coupon/sendCoupon.jsp"), //
		@Result(name = "logings", type = "redirect", location = "accountaction!loginui?loginFrom=${loginFrom}&couponcode=${couponcode}&callback=${callback}"), //
		@Result(name = "loginUI", location = "/WEB-INF/pages/account/login.jsp"),//
		@Result(name = "index", type = "redirect", location = "aolaiindex!index"), @Result(name = "showCart", type = "redirect", location = "allcartaction!listCart?msg=${errorMsg}"),
		@Result(name = "toInfo", type = "redirect", location = "accountaction!info"), @Result(name = "toAddressList", type = "redirect", location = "accountaction!addresslist?ch=${ch}"),
		@Result(name = "toOrderList", type = "redirect", location = "orderaction!orderlist"), @Result(name = "toLoginUI", type = "redirect", location = "accountaction!loginui?loginFrom=3&ch=${ch}"),
		@Result(name = "info", location = "/WEB-INF/pages/account/index.jsp"),
		@Result(name = "addressList", location = "/WEB-INF/pages/account/address_list.jsp"),
		@Result(name = "newAddress", location = "/WEB-INF/pages/account/address_add.jsp"),
		@Result(name = "ajaxSaveAddress", type = "json", params = { "root", "entityJson" }),
		@Result(name = "ajaxSaveInvoiceAddress", type = "json", params = { "root", "entityJson" }),
		@Result(name = "deleteAddress", type = "json", params = { "root", "entityJson" }),
		@Result(name = "agreement", location = "/WEB-INF/pages/account/agreement.jsp"),//
		@Result(name = "index", type = "redirect", location = "aolaiindex!index}"), @Result(name = "bindPhone", location = "/WEB-INF/pages/account/bindmobile.jsp"),
		@Result(name = "sendPhoneCode", location = "/WEB-INF/pages/account/mobilecode.jsp"), @Result(name = "verifyPhoneCode", location = "/WEB-INF/pages/account/mobilesuccess.jsp"),
		@Result(name = "city", type = "json", params = { "root", "entityJson" }),
		@Result(name = "detail", location = "/WEB-INF/pages/market/merchandiseDetail.jsp"),//
		@Result(name = "getSpecialCoupon", type = "redirect", location = "accountaction!getSpecialCoupon?couponcode=${couponcode}"), //
		@Result(name = "toLottery", type = "redirect", location = "actlotaction!lotteryIndex?activeId=20140805"), //
		@Result(name = "getCouponSuccess", location = "/WEB-INF/pages/coupon/coupon_success.jsp"),//
		@Result(name = "isBind", type = "json", params = { "root", "entityJson" }), @Result(name = "area", type = "json", params = { "root", "entityJson" }),
		@Result(name = "town", type = "json", params = { "root", "entityJson" }) }) })
public class AccountAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6241749617103279391L;
	private final Log log = LogFactory.getLog(AccountAction.class);
	@Autowired
	@Qualifier("accountService")
	AccountService accountService;
	@Autowired
	SysParametersService sysParametersService;
	@Autowired
	CaptchaService captchaService;
	@Autowired
	CouponService couponService;
	private String loginName;
	private String password;
	private String gender;
	private String loginFrom;
	private List<ConsigneeAddressVO> consigneeAddressList;
	/** 省级数据列表 */
	private List<ProvinceVO> provinceList;
	/** 市级数据列表 */
	private List<ProvinceVO> cityList;
	/** 区级数据列表 */
	private List<ProvinceVO> areaList;
	/** 乡镇数据列表 */
	private List<ProvinceVO> townList;
	/** 实体数据转json */
	private JSONObject entityJson;
	private String addrid;
	private ConsigneeAddressVO consigneeAddressVO;
	private String msg;
	/** 省、市id ，用于ajax级联参数传递 */
	private String id;
	/** 待绑定手机号码 */
	private String phonenum;
	/** 绑定校验码 */
	private String verifycode;
	/** 登录校验码 */
	private String captcha;
	private String ch;
	@SuppressWarnings("unused")
	private String platform;
	private String couponcode;
	private String callback;
	private String isyr;

	/**
	 * 跳转至注册页面
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-29
	 * @param
	 * @Return
	 */
	public String registerui() {

		return "registerUI";
	}

	/**
	 * 跳转至注册条款页面
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-31
	 * @param
	 * @Return
	 */
	public String agreement() {
		return "agreement";
	}

	/**
	 * 注册校验
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-29
	 * @param
	 * @Return
	 */
	public String register() {
		String ch = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(ch)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
			} else {
				ch = StringUtils.isEmpty(ch) ? Constants.AOLAI_WAP_DEFAULT_CHANNELNO : ch;
			}

		}
		String ua = SysContent.getRequest().getHeader("User-Agent");
		if (StringUtil.isNotEmpty(loginName, password, gender)) {
			try {
				if (!captchaService.verifyCaptcha(ServletActionContext.getRequest().getSession(), this.getCaptcha())) {
					msg = "验证码错误，请重新输入";
					return "registerUI";
				}
				if (!accountService.register(loginName, password, gender))
					return "registerUI";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				AccountVO user = WebUtil.getSessionUser();
				synchronized (user.getUserid().intern()) {
					Account account = new Account();
					account.setChannel(StringUtils.isEmpty(ch) ? BigInteger.valueOf(3) : BigInteger.valueOf(Integer.valueOf(ch)));
					account.setProduct(BigInteger.valueOf(10000));
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
					} else {
						log.debug("register : " + ua);
					}
					accountService.addAccount(account);
				}

				// 记录访问日志
				FileUtil.addLog(SysContent.getRequest(), "accountaction!register", ch, "username", loginName, "code", user.getCode(), "userId", user.getUserid(), "regOrigin", user.getRegOrigin(),
						"gender", user.getGender(), "regTime", user.getRegTime(), "createTime", sdf.format(date), "loginTime", sdf.format(date));
				Map<String, String> mapCoupon = new HashMap<String, String>();
				mapCoupon = SendCoupon.isSendCouponAndGetCoupon();
				if (mapCoupon != null) {
					if (mapCoupon.get("isSendCoupon").equals("true")) {
						couponService.sendCoupon(user.getUserid(), "2", "coupon:" + mapCoupon.get("coupon"));
						ActionContext.getContext().getValueStack().set("sendcoupondesc", mapCoupon.get("sendcoupondesc"));
						return "sendcoupon";
					}
				}
				// loginFrom 1：点击加入购物车；2：查看订单；3：我的账户；4：我的购物袋
				if ("1".equals(loginFrom)) {
					Object obj = ServletActionContext.getRequest().getSession().getAttribute("map");
					if (obj != null) {
						Map<String, String> map = (Map<String, String>) obj;
						map.put("userid", user.getUserid());
						String url = Constants.BASE_NEWAPI_URL_SP + "trade/addproducttocart/";
						String data = WebUtil.readContentFromPost(url, map);
						JSONObject jsonObj = JSONObject.fromObject(data);
						String code = jsonObj.getString("code");
						ServletActionContext.getRequest().getSession().removeAttribute("map");
						if (!"0".equals(code)) {
							ActionContext.getContext().getValueStack().set("errorMsg", URLEncoder.encode(jsonObj.getString("msg"), "UTF-8"));
						}
						return "showCart";
					}
				} else if ("2".equals(loginFrom)) {
					return "toOrderList";
				} else if ("3".equals(loginFrom)) {
					return "toInfo";
				} else if ("4".equals(loginFrom)) {
					return "showCart";
				} else if ("7".equals(loginFrom)) {
					return "toLottery";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return "registerUI";
		}
		return "index";
	}

	/**
	 * 跳转至登录页面
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-29
	 * @param
	 * @Return
	 */
	public String loginui() {
		return "loginUI";
	}

	/**
	 * 注销当前帐号
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-27
	 * @param
	 * @Return
	 */
	public String logout() {
		// String channelNo =
		// SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		// ActionContext.getContext().getValueStack().set(Constants.CHANNEL_PARAM_NAME,
		// channelNo);
		ServletActionContext.getRequest().getSession().removeAttribute(WebUtil.SESSION_USER_PARAM);
		return "index";
	}

	/**
	 * 登录校验
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-29
	 * @param
	 * @throws Exception
	 * @Return
	 */
	public String login() throws Exception {
		String ch = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(ch)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
			} else {
				ch = StringUtils.isEmpty(ch) ? Constants.AOLAI_WAP_DEFAULT_CHANNELNO : ch;
			}

		}
		String ip = SysContent.getRequest().getRemoteAddr();
		if (accountService.ipBlacklist(loginName, ip)) {
			msg = "您操作过于频繁，请稍后重试";
			return "loginUI";
		}
		// if (StringUtil.isNotEmpty(channelNo)) {
		// SysContent.getRequest().getSession().setAttribute(Constants.CHANNEL_PARAM_NAME,
		// channelNo);
		// ActionContext.getContext().getValueStack().set(Constants.CHANNEL_PARAM_NAME,
		// channelNo);
		// } else {
		// channelNo = "3";
		// SysContent.getRequest().getSession().removeAttribute(Constants.CHANNEL_PARAM_NAME);
		// }
		// loginFrom 1：点击加入购物车；2：查看订单；3：我的账户；4：我的购物袋
		String ua = SysContent.getRequest().getHeader("User-Agent");
		if (StringUtil.isNotEmpty(loginName, password)) {
			try {
				if (!captchaService.verifyCaptcha(ServletActionContext.getRequest().getSession(), this.getCaptcha())) {
					msg = "验证码错误，请重新输入";
					return "loginUI";
				}
				if (!accountService.login(loginName, password))
					return "loginUI";
				AccountVO user = WebUtil.getSessionUser();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				loginName = loginName.toLowerCase();
				log.debug("loginName:" + loginName + ";threadName: " + Thread.currentThread().getName());
				synchronized (user.getUserid().intern()) {
					Account account = accountService.getAccountByParams(loginName);
					Map<String, String> uaMap = ParseLogUtil.parseUA(ua);
					// 登录需要记录到account表的信息
					if (account != null && account.getId() > 0) {
						if (uaMap != null) {
							account.setPlatform(uaMap.get("platform"));
							account.setPhoneModel(uaMap.get("phoneModel"));
						} else {
							log.debug("login : " + ua);
						}
						account.setLoginTime(date);
						account.setReserve0("aolai login modify account");
						accountService.modifyAccount(account);
					} else {
						account = new Account();
						account.setChannel(StringUtils.isEmpty(ch) ? BigInteger.valueOf(3) : BigInteger.valueOf(Integer.valueOf(ch)));
						account.setProduct(BigInteger.valueOf(10000));
						account.setGender(user.getGender());
						account.setLoginName(loginName);
						account.setCreateTime(date);
						account.setLoginTime(date);
						account.setRegTime(sdf.parse(user.getRegTime()));
						account.setRegOrigin(user.getRegOrigin());
						account.setUserId(user.getUserid());
						if (uaMap != null) {
							account.setPlatform(uaMap.get("platform"));
							account.setPhoneModel(uaMap.get("phoneModel"));
						} else {
							log.debug("login : " + ua);
						}
						account.setReserve0("aolai login add account");
						accountService.addAccount(account);
					}
				}

				// 记录访问日志
				FileUtil.addLog(SysContent.getRequest(), "accountaction!login", ch, "username", loginName, "code", user.getCode(), "userId", user.getUserid(), "regOrigin", user.getRegOrigin(),
						"gender", user.getGender(), "regTime", user.getRegTime(), "createTime", sdf.format(date), "loginTime", sdf.format(date));
			} catch (Exception e) {
				log.debug("aolai login Exception:" + loginName + "; " + Thread.currentThread().getName());
				e.printStackTrace();
			}
			if ("1".equals(loginFrom)) {
				Object obj = ServletActionContext.getRequest().getSession().getAttribute("map");
				AccountVO user = WebUtil.getSessionUser();
				if (obj != null) {
					Map<String, String> map = (Map<String, String>) obj;
					map.put("userid", user.getUserid());
					String url = Constants.BASE_NEWAPI_URL_SP + "trade/addproducttocart/";
					String data = WebUtil.readContentFromPost(url, map);
					JSONObject jsonObj = JSONObject.fromObject(data);
					String code = jsonObj.getString("code");
					ServletActionContext.getRequest().getSession().removeAttribute("map");
					if (!"0".equals(code)) {
						ActionContext.getContext().getValueStack().set("errorMsg", URLEncoder.encode(jsonObj.getString("msg"), "UTF-8"));
					}
					return "showCart";
				}
			} else if ("2".equals(loginFrom)) {
				return "toOrderList";
			} else if ("3".equals(loginFrom)) {
				return "toInfo";
			} else if ("4".equals(loginFrom)) {
				return "showCart";
			} else if ("7".equals(loginFrom)) {
				return "toLottery";
			} else if ("8".equals(loginFrom)) {
				return "getSpecialCoupon";
			}
		} else {
			return "loginUI";
		}
		return "index";
	}

	/**
	 * 登录校验
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-29
	 * @param
	 * @throws Exception
	 * @Return
	 */
	@AppAuthAnnotation
	public String applogin() throws Exception {
		String ch = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(ch)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
			} else {
				ch = StringUtils.isEmpty(ch) ? Constants.AOLAI_WAP_DEFAULT_CHANNELNO : ch;
			}

		}
		// loginFrom 1：点击加入购物车；2：查看订单；3：我的账户；4：我的购物袋 5:抢优惠券6:微信抽奖 7活动抽奖
		String ua = SysContent.getRequest().getHeader("User-Agent");
		String micromessenger = "micromessenger";
		if (WebUtil.getSessionUser() != null) {
			AccountVO user = WebUtil.getSessionUser();
			if ("1".equals(loginFrom)) {
				Object obj = ServletActionContext.getRequest().getSession().getAttribute("map");
				user = WebUtil.getSessionUser();
				if (obj != null) {
					Map<String, String> map = (Map<String, String>) obj;
					map.put("userid", user.getUserid());
					String url = Constants.BASE_URL + "AddProductToShopping/";
					String data = WebUtil.readContentFromGet(url, map);
					JSONObject jsonObj = JSONObject.fromObject(data);
					String code = jsonObj.getString("code");
					ServletActionContext.getRequest().getSession().removeAttribute("map");
					if (!"0".equals(code)) {
						ActionContext.getContext().getValueStack().set("errorMsg", URLEncoder.encode(jsonObj.getString("msg"), "UTF-8"));
					}
					return "showCart";
				}
			} else if ("2".equals(loginFrom)) {
				return "toOrderList";
			} else if ("3".equals(loginFrom)) {
				return "toInfo";
			} else if ("4".equals(loginFrom)) {
				return "showCart";
			} else if ("5".equals(loginFrom)) {
				return "toGetCoupon";
			} else if ("7".equals(loginFrom)) {
				return "toLottery";
			} else if ("8".equals(loginFrom)) {
				return "getSpecialCoupon";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			// 记录访问日志
			FileUtil.addLog(SysContent.getRequest(), "accountaction!login", ch, "username", loginName, "code", user.getCode(), "userId", user.getUserid(), "regOrigin", user.getRegOrigin(), "gender",
					user.getGender(), "regTime", user.getRegTime(), "createTime", sdf.format(date), "loginTime", sdf.format(date));
		} else {
			return "loginUI";
		}

		if (ua.toLowerCase().indexOf(micromessenger) > -1) {
			return "toCategory";// 跳到品类页
		} else {
			return "index";
		}
	}

	/**
	 * 用户信息首页
	 * 
	 * @return
	 */
	public String info() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "toLoginUI";
		}
		entityJson = accountService.getUserBuyInfo(user.getUserid());
		return "info";
	}

	/**
	 * 是否绑定手机
	 * 
	 * @return
	 */
	@AppAuthAnnotation
	public String checkIsBind() {
		AccountVO user = WebUtil.getSessionUser();
		entityJson = new JSONObject();
		if (user != null) {
			user = accountService.findAccountByPhone(user.getEmail());
			if (user.getMobileNumber() != null && !"".equals(user.getMobileNumber()) || !"".equals(user.getName())) {
				entityJson.put("num", "0");
				entityJson.put("message", "");
			} else {
				entityJson.put("num", "1");
				entityJson.put("message", "你没有绑定手机！");
				entityJson.put("loginFrom", "7");
			}
		} else {
			entityJson.put("num", "-1");
			entityJson.put("message", "你还没有登录！");
			entityJson.put("loginFrom", "7");
		}
		return "isBind";
	}

	/**
	 * 跳转绑定手机页面
	 * 
	 * @return
	 */
	public String bindphone() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "loginUI";
		}
		return "bindPhone";
	}

	/**
	 * 发送手机验证码
	 * 
	 * @return
	 */
	public String sendphonecode() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "loginUI";
		}
		entityJson = accountService.sendPhoneCode(user.getUserid(), phonenum);
		if (Constants.SUCCESS.equals(entityJson.get("code")))
			return "sendPhoneCode";
		return "bindPhone";
	}

	/**
	 * 校验手机验证码
	 * 
	 * @return
	 */
	public String verifyphonecode() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "loginUI";
		}
		entityJson = accountService.verifyPhoneCode(user.getUserid(), phonenum, verifycode);
		if (Constants.SUCCESS.equals(entityJson.get("code")))
			return "verifyPhoneCode";
		return "sendPhoneCode";
	}

	/**
	 * 获取收货人地址列表
	 * 
	 * @return
	 */
	public String addresslist() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "loginUI";
		}
		// 获取地址列表
		consigneeAddressList = accountService.getConsigneeAddressList(user.getUserid());
		return "addressList";
	}

	/**
	 * 删除收货人地址
	 * 
	 * @return
	 */
	public String deleteaddress() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "loginUI";
		}
		String res = accountService.removeConsigneeAddress(user.getUserid(), addrid);
		entityJson = new JSONObject();
		if (null != res && Constants.SUCCESS.equals(res)) {
			entityJson.put("success", true);
		} else {
			entityJson.put("success", false);
			entityJson.put("msg", "删除失败！");
		}
		return "deleteAddress";
	}

	/**
	 * 跳转到新增收货人地址页面
	 * 
	 * @return
	 */
	public String newaddress() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "loginUI";
		}
		// 获取省级数据
		provinceList = (List<ProvinceVO>) DataContainerPool.sysContainer.get("provinceList");
		return "newAddress";
	}

	/**
	 * 跳转到修改收货人地址页面
	 * 
	 * @return
	 */
	public String modifyaddress() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "loginUI";
		}
		consigneeAddressVO = accountService.getConsigneeAddress(user.getUserid(), addrid);
		// 获取省级数据
		provinceList = (List<ProvinceVO>) DataContainerPool.sysContainer.get("provinceList");
		// 获取市级数据
		cityList = sysParametersService.getCityList(consigneeAddressVO.getProvince());
		// 获取区级数据
		areaList = sysParametersService.getAreaList(consigneeAddressVO.getCity());
		townList = sysParametersService.getTownList(consigneeAddressVO.getArea());
		return "newAddress";
	}

	/**
	 * 保存收货人地址
	 * 
	 * @return
	 */
	public String saveaddress() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "loginUI";
		}
		JSONObject jobj = accountService.addOrUpdateConsigneeAddress(user.getUserid(), consigneeAddressVO);
		if (null != jobj && Constants.SUCCESS.equals(jobj.get("code"))) {
			return "toAddressList";
		} else {
			msg = "保存收货人地址失败";
		}
		// 获取省级数据
		provinceList = (List<ProvinceVO>) DataContainerPool.sysContainer.get("provinceList");
		return "newAddress";
	}

	/**
	 * 保存收货人地址(异步操作)
	 * 
	 * @return
	 */
	public String ajaxsaveaddress() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "loginUI";
		}
		entityJson = accountService.addOrUpdateConsigneeAddress(user.getUserid(), consigneeAddressVO);
		return "ajaxSaveAddress";
	}

	/**
	 * 保存发票收货地址(异步操作)
	 * 
	 * @return
	 */
	public String ajaxsaveinvoiceaddress() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "loginUI";
		}
		entityJson = accountService.addInvoiceAddress(user.getUserid(), consigneeAddressVO);
		return "ajaxSaveInvoiceAddress";
	}

	/**
	 * 通过省级ID，获取市级json数据
	 * 
	 * @return
	 */
	public String city() {
		entityJson = sysParametersService.getCityJson(id);
		return "city";
	}

	/**
	 * 通过市级ID，获取区级json数据
	 * 
	 * @return
	 */
	public String area() {
		entityJson = sysParametersService.getAreaJson(id);
		return "area";
	}

	/**
	 * 通过地区ID，获取区级json数据
	 * 
	 * @return
	 */
	public String town() {
		entityJson = sysParametersService.getTownJson(id);
		return "town";
	}

	/**
	 * 生成验证码
	 * 
	 * @return
	 */
	public String captcha() {
		try {
			captchaService.writeImage(ServletActionContext.getRequest().getSession(), ServletActionContext.getResponse());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@AppAuthAnnotation
	public String getSpecialCoupon() {
		AccountVO user = WebUtil.getSessionUser();
		if (loginFrom == null) {
			loginFrom = "";
		}
		if (couponcode == null) {
			couponcode = "";
		}

		if (isyr == null) {
			isyr = "";
		}
		if (user == null || !StringUtil.isNotEmpty(user.getName())) {
			loginFrom = "8";
			callback = URLEncodeUtils.encodeURL(Constants.AOLAI_URL + "accountaction!login?loginFrom=" + loginFrom + "&couponcode=" + couponcode + "&isyr=" + isyr);
			SysContent.getRequest().getSession().setAttribute("loginFrom", loginFrom);
			SysContent.getRequest().getSession().setAttribute("couponcode", couponcode);
			SysContent.getRequest().getSession().setAttribute("isyr", isyr);
			SysContent.getRequest().getSession().setAttribute("callback", callback);
			return "logings";
		}
		// user=accountService.findAccountByPhone(user.getEmail());
		// if(user.getMobileNumber()!=null&&!"".equals(user.getMobileNumber())){
		JSONObject o = couponService.sendCoupon(user.getUserid(), "2", "coupon:" + couponcode, "8");
		if (o.getString("code") != null && !"".equals(o.getString("code"))) {
			msg = o.getString("msg");
		}

		return "getCouponSuccess";

		// }else{
		// System.out.println("ddddddddddddd");
		// return "getCoupon";
		// }
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<ConsigneeAddressVO> getConsigneeAddressList() {
		return consigneeAddressList;
	}

	public void setConsigneeAddressList(List<ConsigneeAddressVO> consigneeAddressList) {
		this.consigneeAddressList = consigneeAddressList;
	}

	public JSONObject getEntityJson() {
		return entityJson;
	}

	public void setEntityJson(JSONObject entityJson) {
		this.entityJson = entityJson;
	}

	public String getAddrid() {
		return addrid;
	}

	public void setAddrid(String addrid) {
		this.addrid = addrid;
	}

	public ConsigneeAddressVO getConsigneeAddressVO() {
		return consigneeAddressVO;
	}

	public void setConsigneeAddressVO(ConsigneeAddressVO consigneeAddressVO) {
		this.consigneeAddressVO = consigneeAddressVO;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<ProvinceVO> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<ProvinceVO> provinceList) {
		this.provinceList = provinceList;
	}

	public List<ProvinceVO> getCityList() {
		return cityList;
	}

	public void setCityList(List<ProvinceVO> cityList) {
		this.cityList = cityList;
	}

	public List<ProvinceVO> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<ProvinceVO> areaList) {
		this.areaList = areaList;
	}

	public List<ProvinceVO> getTownList() {
		return this.townList;
	}

	public void setTownList(List<ProvinceVO> townList) {
		this.townList = townList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String getLoginFrom() {
		return loginFrom;
	}

	public void setLoginFrom(String loginFrom) {
		this.loginFrom = loginFrom;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getCh() {
		ch = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(ch)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
			} else {
				ch = StringUtils.isEmpty(ch) ? Constants.AOLAI_WAP_DEFAULT_CHANNELNO : ch;
			}

		}
		return ch;
	}

	public void setCh(String ch) {
		ch = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(ch)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
			} else {
				ch = StringUtils.isEmpty(ch) ? Constants.AOLAI_WAP_DEFAULT_CHANNELNO : ch;
			}

		}
		this.ch = ch;
	}

	public String getPlatform() {
		Object platform = SysContent.getSession().getAttribute("platform");
		if (platform != null) {
			return (String) platform;
		}
		return "";
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getCouponcode() {
		return couponcode;
	}

	public void setCouponcode(String couponcode) {
		this.couponcode = couponcode;
	}

	public void setCaptchaService(CaptchaService captchaService) {
		this.captchaService = captchaService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public void setSysParametersService(SysParametersService sysParametersService) {
		this.sysParametersService = sysParametersService;
	}

	public void setCouponService(CouponService couponService) {
		this.couponService = couponService;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getIsyr() {
		return isyr;
	}

	public void setIsyr(String isyr) {
		this.isyr = isyr;
	}

}
