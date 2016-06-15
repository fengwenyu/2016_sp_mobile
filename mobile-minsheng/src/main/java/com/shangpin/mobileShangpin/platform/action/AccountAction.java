package com.shangpin.mobileShangpin.platform.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import weibo4sina.util.URLEncodeUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.mobileShangpin.base.model.Account;
import com.shangpin.mobileShangpin.businessbean.TopicConfig;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.DataContainerPool;
import com.shangpin.mobileShangpin.common.util.FileUtil;
import com.shangpin.mobileShangpin.common.util.ParseLogUtil;
import com.shangpin.mobileShangpin.common.util.StringUtil;
import com.shangpin.mobileShangpin.common.util.SysContent;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.AccountService;
import com.shangpin.mobileShangpin.platform.service.CaptchaService;
import com.shangpin.mobileShangpin.platform.service.CouponService;
import com.shangpin.mobileShangpin.platform.service.SysParametersService;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;
import com.shangpin.mobileShangpin.platform.vo.ConsigneeAddressVO;
import com.shangpin.mobileShangpin.platform.vo.ProvinceVO;

/**
 * 帐号管理Action
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-29
 */
@Controller
@ParentPackage("mobileShangpin")
@Scope("prototype")
@SuppressWarnings("unchecked")
@Actions({ @Action(value = ("/accountaction"), results = {//
@Result(name = "registerUI", location = "/WEB-INF/pages/account/register.jsp"),//
		@Result(name = "loginUI", location = "/WEB-INF/pages/account/login.jsp"),//
		@Result(name = "index", type = "redirect", location = "spindex!index?gender=0&ch=${ch}"), //
		@Result(name = "showCart", type = "redirect", location = "cartaction!showcart?msg=${errorMsg}&ch=${ch}"), //
		@Result(name = "toInfo", type = "redirect", location = "accountaction!info?ch=${ch}"), //
		@Result(name = "toAddressList", type = "redirect", location = "accountaction!addresslist?ch=${ch}"), //
		@Result(name = "toOrderList", type = "redirect", location = "orderaction!orderlist?ch=${ch}"), //		
		@Result(name = "toLoginUI", type = "redirect", location = "accountaction!loginui?loginFrom=3&ch=${ch}"), //
		@Result(name = "coupon", location = "/WEB-INF/pages/coupon/coupon.jsp"),//
		@Result(name = "toCoupon", type = "redirect", location = "accountaction!coupon?ch=${ch}"), //
		@Result(name = "info", location = "/WEB-INF/pages/account/index.jsp"), //
		@Result(name = "addressList", location = "/WEB-INF/pages/account/address_list.jsp"),//
		@Result(name = "newAddress", location = "/WEB-INF/pages/account/address_add.jsp"), //
		@Result(name = "ajaxSaveAddress", type = "json", params = { "root", "entityJson" }), //
		@Result(name = "ajaxSaveInvoiceAddress", type = "json", params = { "root", "entityJson" }), //
		@Result(name = "deleteAddress", type = "json", params = { "root", "entityJson" }), //
		@Result(name = "agreement", location = "/WEB-INF/pages/account/agreement.jsp"),//
		@Result(name = "bindPhone", location = "/WEB-INF/pages/account/bindmobile.jsp"), //
		@Result(name = "sendPhoneCode", location = "/WEB-INF/pages/account/mobilecode.jsp"), //
		@Result(name = "verifyPhoneCode", location = "/WEB-INF/pages/account/mobilesuccess.jsp"), //
		@Result(name = "city", type = "json", params = { "root", "entityJson" }), //
		@Result(name = "detail", location = "/WEB-INF/pages/market/merchandiseDetail.jsp"),//
		@Result(name = "editPasswordui", location = "/WEB-INF/pages/account/editPassword.jsp"),//
		@Result(name = "editPassword", location = "/WEB-INF/pages/account/editPassword.jsp"),//
		@Result(name = "minshenglogin", location = "/WEB-INF/pages/account/minshenglogin.jsp"),
		@Result(name = "getCoupon",  type = "redirect", location = "couponaction!couponlist?ch=${ch}&msg=${msg}"),
		@Result(name = "area", type = "json", params = { "root", "entityJson" }) }) })
public class AccountAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6241749617103279391L;
	private final Log log = LogFactory.getLog(AccountAction.class);
	@Autowired
	private AccountService accountService;
	@Autowired
	private SysParametersService sysParametersService;
	@Autowired
	CouponService couponService;
	@Autowired
	CaptchaService captchaService;
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
	/** 登录校验码返回标记 **/
	private JSONObject captchaFlag;
	/** 老密码**/
	private String oldPassword;
	/** 新密码**/
	private String newPassword;
	private  String couponcode;
	/**
	 * 跳转注册成功优惠券介绍页面
	 * 
	 * @Return
	 */
	public String coupon() {
		return "coupon";
	}

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
					account.setChannel(StringUtils.isEmpty(channelNo)?BigInteger.valueOf(4):BigInteger.valueOf(Integer.valueOf(channelNo)));
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
					} else {
						log.info("register : " + ua);
					}
					accountService.addAccount(account);
				}
				// 记录访问日志
				FileUtil.addLog(SysContent.getRequest(), "accountaction!register",//
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
				// loginFrom 1：点击加入购物车；2：查看订单；3：我的账户；4：我的购物袋
				if ("1".equals(loginFrom)) {
					Object obj = ServletActionContext.getRequest().getSession().getAttribute("map");
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
	@SuppressWarnings("unused")
	public String loginui() {
		SysContent.getRequest().getSession().setAttribute("loginFrom", loginFrom);
		/*String path=null;
		if(Constants.MINSHENG_PAYMENT){
			path="http://123.124.211.164:9999/mobileshangpin";
		}else{
			path="m.shangpin.com";
		}
		if(true){
			String toLogin=path+"/minshengbindaction!minshengLogin?loginFrom="+loginFrom;
			System.out.print("1111"+toLogin);
			PrintWriter out;
			try {
				out = SysContent.getResponse().getWriter();
				 out.flush();
				    out.println("<script>");
				    out.println("window.SysClientJs.toLoginShangPin(\""+toLogin+"\")");
				    System.out.println("111111window.SysClientJs.toLoginShangPin(\""+toLogin+"\")");
				    out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}*/
		
		/*ScriptEngineManager manager = new ScriptEngineManager();

		ScriptEngine engine = manager.getEngineByName("JavaScript");

		String titlescript = "function sayTitle(){alert('2222');}";



		try { 

			// 转换为Invocable

			Invocable invocableEngine = (Invocable) engine;



			// 调用外部脚本执行------------------------------------------

			// 创建JS文件的File对象，并读入流
			String configFile = AccountAction.class.getResource("/resources/cmbclogin.js").getPath();
			File functionscript = new File(configFile);

			Reader reader = new FileReader(functionscript);

			// 开始执行ajava.js里的程序

			engine.eval(reader);

			// 不带参数调用sayHello方法

			invocableEngine.invokeFunction("submit");

			// 带参数调用sayHello方法

			//invocableEngine.invokeFunction("sayHello", "mark");

	

		} catch (Exception e) {

		e.printStackTrace();

		}*/
		return "minshenglogin";

	}

	/**
	 * 跳转至修改密码页
	 * 
	 * @Author wangfeng
	 * @CreatDate 2013-08-01
	 * @param
	 * @Return
	 */
	public String editPasswordui() {
		AccountVO user = WebUtil.getSessionUser();
		String userId = "";
		if (null != user && null != user.getUserid()) {
			userId = user.getUserid();
		}else{
			return "loginUI";
		}
		return "editPasswordui";
	}
	/**
	 * 修改密码
	 * 
	 * @Author wangfeng
	 * @CreatDate 2013-08-01
	 * @param
	 * @Return
	 */
	public String editPassword() {
		AccountVO user = WebUtil.getSessionUser();
		String userId = "";
		if (null != user && null != user.getUserid()) {
			userId = user.getUserid();
		}else{
			return "loginUI";
		}
		try {
			entityJson =accountService.editPassword(oldPassword, newPassword, userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "editPassword";
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
		ActionContext.getContext().getValueStack().set(Constants.SP_TOPIC_ID, ActionContext.getContext().getSession().get(Constants.SP_TOPIC_ID));
		String channelNo = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		ActionContext.getContext().getValueStack().set(Constants.CHANNEL_PARAM_NAME, channelNo);
		ServletActionContext.getRequest().getSession().removeAttribute(WebUtil.SESSION_USER_PARAM);
		String referer = SysContent.getRequest().getHeader("referer");//记录登录前的地址
		
		try {
			if(referer!=null&&"".equals(referer)){				
				SysContent.getResponse().sendRedirect(referer);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public String login() throws Exception {
		String channelNo = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		String ip = SysContent.getRequest().getRemoteAddr();
		if(accountService.ipBlacklist(loginName, ip)){
			msg = "您操作过于频繁，请稍后重试";
			return "loginUI";
		}
		
		if (StringUtil.isNotEmpty(channelNo)) {
			SysContent.getRequest().getSession().setAttribute(Constants.CHANNEL_PARAM_NAME, channelNo);
			ActionContext.getContext().getValueStack().set(Constants.CHANNEL_PARAM_NAME, channelNo);
		} else {
			channelNo = Constants.SP_WAP_DEFAULT_CHANNELNO;
			SysContent.getRequest().getSession().removeAttribute(Constants.CHANNEL_PARAM_NAME);
		}
		// loginFrom 1：点击加入购物车；2：查看订单；3：我的账户；4：我的购物袋
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
					System.out.println("login:" + loginName + "; " + (account != null?(account.getId() + "," + sdf.format(date)):"null"));
					// 登录需要记录到account表的信息
					if (account != null && account.getId() > 0) {
						if (uaMap != null) {
							account.setPlatform(uaMap.get("platform"));
							account.setPhoneModel(uaMap.get("phoneModel"));
						} else {
							log.info("login : " + ua);
						}
						account.setLoginTime(date);
						account.setReserve0("shangpin login modify account");
						accountService.modifyAccount(account);
					} else {
						account = new Account();
						account.setChannel(StringUtils.isEmpty(channelNo)?BigInteger.valueOf(4):BigInteger.valueOf(Integer.valueOf(channelNo)));
						account.setProduct(BigInteger.valueOf(20000));
						account.setGender(user.getGender());
						account.setLoginName(user.getEmail());
						account.setCreateTime(date);
						account.setLoginTime(date);
						account.setRegTime(sdf.parse(user.getRegTime()));
						account.setRegOrigin(user.getRegOrigin());
						account.setUserId(user.getUserid());
						if (uaMap != null) {
							account.setPlatform(uaMap.get("platform"));
							account.setPhoneModel(uaMap.get("phoneModel"));
						} else {
							log.info("login : " + ua);
						}
						account.setReserve0("shangpin login add account");
						accountService.addAccount(account);
					}
				}
				// 记录访问日志
				FileUtil.addLog(SysContent.getRequest(), "accountaction!login",//
						channelNo,//
						"username", loginName,//
						"code", user.getCode(),//
						"userId", user.getUserid(),//
						"regOrigin", user.getRegOrigin(),//
						"gender", user.getGender(),//
						"regTime", user.getRegTime(),//
						"createTime", sdf.format(date),//
						"loginTime", sdf.format(date));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if ("1".equals(loginFrom)) {
				Object obj = ServletActionContext.getRequest().getSession().getAttribute("map");
				AccountVO user = WebUtil.getSessionUser();
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
			}
		} else {
			return "loginUI";
		}
		return "index";
	}

	/**
	 * 用户信息首页
	 * 
	 * @return
	 */
	public String info() {
		System.out.print("minsheng111111111111");
		System.out.println("infosession"+ActionContext.getContext().getSession());		
		AccountVO user = WebUtil.getSessionUser();
		System.out.println("user"+user);
		String ch = (String) ServletActionContext.getRequest().getSession().getAttribute(Constants.CHANNEL_PARAM_NAME);
		if (ch == null) {
			ch = "";
		}
		ActionContext.getContext().getValueStack().set(Constants.CHANNEL_PARAM_NAME, ch);
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "toLoginUI";
		}
		entityJson = accountService.getUserBuyInfo(user.getUserid(), "1");
		
		return "info";
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
		return "newAddress";
	}

	/**
	 * 保存收货人地址
	 * 
	 * @return
	 */
	public String saveaddress() {
		String channelNo = (String) SysContent.getRequest().getSession().getAttribute(Constants.CHANNEL_PARAM_NAME);
		if (!StringUtil.isNotEmpty(channelNo)) {
			channelNo = "";
		}
		ActionContext.getContext().getValueStack().set(Constants.CHANNEL_PARAM_NAME, channelNo);
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
		consigneeAddressVO.setName(URLEncodeUtils.decodeURL(consigneeAddressVO.getName()));
		consigneeAddressVO.setAddr(URLEncodeUtils.decodeURL(consigneeAddressVO.getAddr()));
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
		consigneeAddressVO.setName(URLEncodeUtils.decodeURL(consigneeAddressVO.getName()));
		consigneeAddressVO.setAddr(URLEncodeUtils.decodeURL(consigneeAddressVO.getAddr()));
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
	 * 生成验证码
	 * 
	 * @return
	 */
	public String captcha() {
		try {
			captchaService.writeImage(ServletActionContext.getRequest().getSession(), ServletActionContext.getResponse());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String activateCouponCode() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "loginUI";
		}
		try {
			if (StringUtils.isNotEmpty(couponcode)) {
				System.out.println("222222222"+user.getMobileNumber());
				if(user.getUserid()!=null&&!"".equals(user.getUserid())&&user.getMobileNumber()!=null&&!"".equals(user.getMobileNumber())){
					entityJson = accountService.activateCouponCode(user.getUserid(), user.getMobileNumber(), couponcode);
					ActionContext.getContext().getValueStack().set("msg", URLEncoder.encode(entityJson.getString("msg"), "UTF-8"));
				}else{
					msg = "信息有误！";
					ActionContext.getContext().getValueStack().set("msg", URLEncoder.encode(msg, "UTF-8"));
				}
				
			} else {
				msg = "请输入优惠券充值码！";
				ActionContext.getContext().getValueStack().set("msg", URLEncoder.encode(msg, "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "getCoupon";
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

	public JSONObject getCaptchaFlag() {
		return captchaFlag;
	}

	public void setCaptchaFlag(JSONObject captchaFlag) {
		this.captchaFlag = captchaFlag;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getCouponcode() {
		return couponcode;
	}

	public void setCouponcode(String couponcode) {
		this.couponcode = couponcode;
	}
	
	
}
