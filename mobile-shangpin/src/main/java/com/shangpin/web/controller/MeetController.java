package com.shangpin.web.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.ActivityLottery;
import com.shangpin.biz.bo.Meet;
import com.shangpin.biz.bo.SiteType;
import com.shangpin.biz.bo.TimeLimitBuy;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.Winners;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizCouponService;
import com.shangpin.biz.service.SPBizMeetPlaceService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.PropertyUtil;
import com.shangpin.web.utils.UserUtil;

/**
 * @ClassName: MeetController
 * @Description: 会场
 * @author liling
 * @date 2014年1月6日
 * @version 1.0
 */
@Controller
public class MeetController extends BaseController {
	public static final String MEET_JS_PATH = "/styles/shangpin/js/meet/";
	public static final String MEET_CSS_PATH = "/styles/shangpin/css/page/meet/";

	public static final Logger logger = LoggerFactory.getLogger(MeetController.class);
	private static final String INDEX = "meet/common/index";
	/** 防欺诈 */
	private static final String DECEIVE_NOTICE = "meet/deceive_notice";
	/** 抽奖活动520 */
	private static final String ACTIVITY_LOTTERY_520 = "meet/activity_lottery_520";
	/** 限时抢页面 */
	private static final String TIME_LIMIT = "meet/time_limit";
	/** 520礼券包页面 */
	private static final String GIFT_PACKAGE = "meet/gift_package";
	/** 520乐分享页面 */
	private static final String HAPPY_SHARE = "meet/happy_share";
	/** 九宫格抽奖页面 */
	private static final String MARQUEE_LUCK = "meet/marqueeLuck";
	/** 免运费 */
	private static final String CARRIAGE_FREE = "meet/carriage_free";
	/** 单页面前缀 */
	private static final String SINGLE_PREFIX = "meet/single/";
	/** 天天领福利 */
	private static final String RECEIVE_COUPONS = "meet/coupo_list";
	/** 快来抢现金 */
	private static final String FLIP_DRAW = "meet/flip_draw";
	/** 天天领福利规则页面 */
	private static final String COUPON_RULE = "/meet/coupo_rule";
	/** 抢现金规则页面 */
	private static final String FLIP_DRAW_RULE = "/meet/flip_draw_rule";
	
	/** 2015/11/27加的静态页面*/
    private static final String INDEX_STATIC = "/meet/static_index";

	@Autowired
	SPBizMeetPlaceService meetPlaceService;
	@Autowired
	private SPBizCouponService bizCouponService;
	@Autowired
	private SPBizUserService userService;

	/**
	 * 
	 * @Title: list
	 * @Description:会场页
	 * @param id
	 *            会场id
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年1月8日
	 */
	@RequestMapping(value = "/meet/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, Model model, String id, String type, String isChange) {
		try {
			if (StringUtils.isNotEmpty(id)) {
				SiteType siteType =ClientUtil.getSiteType(request);
                Meet meet = meetPlaceService.getMeetPlaceList(id, siteType, true, isChange,type);
				// 测试数据
				/*
				 * if("428".equals(id)){ id="259"; meet.setId(id); }
				 */
				model.addAttribute("meet", meet);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				model.addAttribute("nowTime", sdf.format(new Date()));
				/***
				 * 如果数据出错或者为空则不缓存
				 */
				if (meet == null || (meet != null && StringUtils.isEmpty(meet.getHtml()))) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				} else {
					// 1. 如果为空或者为0或者没有这个input隐藏域，则代表有M站加载css或js
					// 2. 如果为1代表主站加载css，M站加载js
					// 3. 如果为2代表主站加载css,主站加载js
					String html = meet.getHtml();
					int pos = html.indexOf("|");
					String internal = html.substring(0, pos);
					if (StringUtils.isNotBlank(internal)) {
						model.addAttribute("internal", internal);
					} else {
						// 根据会场目录下是否有文件来判断是否需要加载css js
						internal = getInternalVal(request, id, meet.getStatus());
						model.addAttribute("internal", internal);
					}
					meet.setHtml(html.substring(pos + 1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("MeetController index e={}", e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return INDEX;

	}
	
	/**
	 * 静态页面
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value = "/static/index", method = RequestMethod.GET)
    public String staticPage(HttpServletRequest request, HttpServletResponse response) {
         return INDEX_STATIC;
    }

	/**
	 * 查询会场下的文件目录支持css jss 标识
	 * 
	 * @return 1. 如果为0 M站加载css和js <br/>
	 *         2. 如果为1 M站加载js <br/>
	 *         3. 如果为2 什么都不加载
	 * @author zghw
	 */
	private String getInternalVal(HttpServletRequest request, String id, String status) {
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String jsPath = realPath + MEET_JS_PATH + id + "/main.js";
		String cssPath = realPath + MEET_CSS_PATH + id + "/main.css";
		if ("0".equals(status)) {
			//预热会场处理
			jsPath = realPath + MEET_JS_PATH + id + "/pre.js";
			cssPath = realPath + MEET_CSS_PATH + id + "/pre.css";
		}
		File jsFile = new File(jsPath);
		File cssFile = new File(cssPath);
		String internal = "0";
		if (jsFile.exists()) {
			// 加载js 
			internal = "1";
			if (cssFile.exists()) {
				// 加载js 和css 
				internal = "0";
			}
		} else {
			if (cssFile.exists()) {
				// 只存在css
				internal = "3";
			} else {
				// 什么都不加载
				internal = "2";
			}
		}
		return internal;
	}

	/***
	 * 天天领福利 优惠劵规则
	 * 
	 * @param request
	 * @return page
	 */
	@RequestMapping(value = "/couponRule")
	public String couponRule(HttpServletRequest request, Model model) {
		return COUPON_RULE;
	}

	/**
	 * 天天领福利 判断是否登录 0时未登录 1是登录
	 * 
	 * @return coupo_list page
	 */
	@RequestMapping(value = "/findCoupo")
	public String findCoupo(HttpServletRequest request, Model model) {
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			model.addAttribute("isLogin", "0");
		} else {
			model.addAttribute("isLogin", "1");
		}
		return RECEIVE_COUPONS;
	}

	/**
	 * 快来抢现金 判断是否登录 0时未登录 1是登录
	 * 
	 * @return flip_draw page
	 */
	@RequestMapping(value = "/flipDraw")
	public String flipDraw(HttpServletRequest request, Model model) {
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			model.addAttribute("isLogin", "0");
		} else {
			model.addAttribute("isLogin", "1");
		}
		return FLIP_DRAW;
	}

	/**
	 * 快来抢现金规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/flipDrawRule")
	public String flipDrawRule(Model model) {
		/*
		 * List<Winners> obj = meetPlaceService.doWinners("520", "1", "30"); if
		 * (obj != null) { model.addAttribute("winList", obj); }
		 */
		return FLIP_DRAW_RULE;
	}

	/**
	 * 单页面通用类
	 * 
	 * @param request
	 * @param response
	 * @param mode
	 * @return
	 * @author zghw
	 */
	@RequestMapping("/single/{pageName}")
	public String singlePage(@PathVariable("pageName") String pageName) {
		if (pageName.startsWith(SINGLE_PREFIX)) {
			return pageName;
		}
		return SINGLE_PREFIX + pageName;
	}

	/**
	 * 加了权限的会场
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "/meet/redirect/app", method = RequestMethod.GET)
	public String redirectApp(HttpServletRequest request, HttpServletResponse response, Model model, String id, String type, String isChange) {
		// 如果不是会场是特殊页面做特殊处理也走此方法
		if ("marqueeLuck".equals(id)) {
			return marqueeLuck(model);
		}
		if (StringUtils.isNotBlank(id) && id.startsWith(SINGLE_PREFIX)) {
			return singlePage(id);
		}
		HttpSession session = request.getSession();
		session.setAttribute("isLogin", request.getParameter("isLogin"));
		return index(request, response, model, id, type, isChange);
	}

	/**
	 * 领取优惠券
	 * 
	 * @return
	 * @author zghw
	 */
	@RequestMapping(value = "/meet/coupon/get", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getCoupon(String coupon, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "";
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			code = Constants.UNLOGIN;
			map.put("code", code);
			return map;
		}
		String userId = this.getUserId(request);
		map = bizCouponService.sendCoupon(userId, "1", "coupon:" + coupon, "9");
		return map;
	}

	/**
	 * 激活优惠券
	 * 
	 * @return
	 * @author zghw
	 */
	@RequestMapping(value = "/meet/coupon/activation", method = RequestMethod.GET)
	@ResponseBody
	public ResultBase activationCoupon(String coupon, HttpServletRequest request) {
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			ResultBase resultBase = new ResultBase();
			resultBase.setCode(Constants.UNLOGIN);
			resultBase.setMsg("请先登录！");
			return resultBase;
		}
		// 从配置文件中读取优惠券标识对应的优惠券
		String dynamicCoupon = PropertyUtil.getString(coupon);
		if (StringUtils.isNotBlank(dynamicCoupon)) {
			coupon = dynamicCoupon;
		}
		String userId = this.getUserId(request);
		ResultBase resultBase = bizCouponService.beSendActivation(userId, "1", "coupon:" + coupon);
		return resultBase;
	}

	/**
	 * 通用中奖列表
	 * 
	 * @param activityId
	 * @return
	 * @author zghw
	 */
	@RequestMapping(value = "/winList")
	@ResponseBody
	public Object winList(String activityId) {
		Map<String, List<Winners>> map = new HashMap<String, List<Winners>>();
		List<Winners> obj = meetPlaceService.doWinners(activityId, "1", "10");
		if (obj != null) {
			map.put("winList", obj);
		}
		return map;
	}

	/**
	 * 通用抽奖
	 * 
	 * @param request
	 * @param activityId
	 * @return
	 * @author zghw
	 */
	@RequestMapping(value = "/activityLottery")
	@ResponseBody
	public Object activityLottery(HttpServletRequest request, String activityId) {
		Map<String, String> map = new HashMap<String, String>();
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			map.put("status", "2");
			return map;
		}
		ResultObjOne<ActivityLottery> obj = meetPlaceService.beActivityLottery(activityId, user.getUserid());
		if (obj != null && obj.isSuccess()) {
			ActivityLottery activityLottery = obj.getContent();
			if (activityLottery != null) {
				System.out.println(activityLottery.getIsDraw());
				System.out.println(activityLottery.getRemainCount());
				map.put("status", activityLottery.getIsDraw());
				map.put("remainCount", activityLottery.getRemainCount());
				map.put("message", activityLottery.getPrizeLevel());
				map.put("times", activityLottery.getRemainCount());
				if ("0".equals(activityLottery.getIsDraw())) {
					return map;
				}

			}
		} else {
			map.put("times", "0");
			return map;
		}
		return map;
	}

	/**
	 * 登录判断 返回值 0时未登录 1是登录
	 * 
	 * @param request
	 * @return
	 * @author zghw
	 */
	@RequestMapping(value = "/isLogin", method = RequestMethod.GET)
	@ResponseBody
	public String isLogin(HttpServletRequest request) {
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			// 未登陆
			return "0";
		}
		// 已经登录
		return "1";
		
		/*String userId = UserUtil.getUserId(request);
		if(userId==null||"".equals(userId)){
			// 未登陆
			return "0";
		}
		// 已经登录
		return "1";*/
	}
	

	/**
	 * 登录判断 返回值 0时未登录 1是登录
	 * 
	 * @param request
	 * @return
	 * @author zghw
	 */
	@RequestMapping(value = "/checkIsLogin", method = RequestMethod.GET)
	@ResponseBody
	public String checkIsLogin(HttpServletRequest request) {
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			// 未登陆
			return "0";
		}
		// 已经登录
		return "1";
	}

	// ===========================================以上为通用请求，以下为特殊开发========
	/**
	 * 防欺诈单页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/noticeDeceive")
	public String noticeDeceive() {
		return DECEIVE_NOTICE;
	}

	/**
	 * /** 进入520抽奖页面
	 * 
	 * @return
	 * @author zghw
	 */
	@RequestMapping(value = "/goodLuck520")
	public String goodLuck520() {
		return ACTIVITY_LOTTERY_520;
	}

	/**
	 * 520抽奖
	 * 
	 * @param request
	 * @return
	 * @author zghw
	 */
	@RequestMapping(value = "/activityLottery520")
	@ResponseBody
	public Object activityLottery520(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			map.put("status", "2");
			return map;
		}
		ResultObjOne<ActivityLottery> obj = meetPlaceService.beActivityLottery("520", user.getUserid());
		if (obj != null && obj.isSuccess()) {
			ActivityLottery activityLottery = obj.getContent();
			if (activityLottery != null) {
				map.put("status", activityLottery.getIsDraw());
				if ("0".equals(activityLottery.getIsDraw())) {
					return map;
				}
				map.put("message", activityLottery.getPrizeLevel());
				map.put("times", activityLottery.getRemainCount());
			}
		} else {
			map.put("status", "0");
			return map;
		}
		return map;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/happyShare")
	public String happyShare() {
		return HAPPY_SHARE;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/giftPackage")
	public String giftPackage() {
		return GIFT_PACKAGE;
	}

	/**
	 * 免运费页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/carriageFree")
	public String carriageFree() {
		return CARRIAGE_FREE;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/meet/timeLimit")
	public String timeLimit(HttpServletRequest request, String meetId, Model model, HttpServletResponse response) {

		ResultObjOne<TimeLimitBuy> timeLimits = meetPlaceService.beTimeLimitBuy(meetId);
		if (timeLimits != null && timeLimits.getObj() != null) {
			TimeLimitBuy timeLimit = timeLimits.getObj();
			model.addAttribute("timeLimit", timeLimit);
			model.addAttribute("meetId", meetId);
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return TIME_LIMIT;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/marqueeLuck")
	public String marqueeLuck(Model model) {
		List<Winners> obj = meetPlaceService.doWinners("9", "1", "10");
		if (obj != null) {
			model.addAttribute("winList", obj);
		}
		return MARQUEE_LUCK;
	}

	/**
	 * 9宫格抽奖
	 * 
	 * @param request
	 * @return
	 * @author zghw
	 */
	@RequestMapping(value = "/activityLottery9")
	@ResponseBody
	public Object activityLottery9(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			map.put("status", "2");
			return map;
		}
		ResultObjOne<ActivityLottery> obj = meetPlaceService.beActivityLottery("9", user.getUserid());
		if (obj != null && obj.isSuccess()) {
			ActivityLottery activityLottery = obj.getContent();
			if (activityLottery != null) {
				map.put("status", activityLottery.getIsDraw());
				map.put("message", activityLottery.getPrizeLevel());
				map.put("times", activityLottery.getRemainCount());
				if ("0".equals(activityLottery.getIsDraw())) {
					return map;
				}

			}
		} else {
			map.put("times", "0");
			return map;
		}
		return map;
	}

	/**
	 * 查询9宫格抽奖列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findWinList")
	@ResponseBody
	public Object findWinList(HttpServletRequest request) {
		Map<String, List<Winners>> map = new HashMap<String, List<Winners>>();
		List<Winners> obj = meetPlaceService.doWinners("9", "1", "10");
		if (obj != null) {
			map.put("winList", obj);
		}
		return map;
	}

	/**
	 * 获取短信验证码
	 * 
	 * @param mobi
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getsmscode")
	public String forgetPasswordPhone(@RequestParam("mobi") String mobi, HttpServletRequest request) {
		String jsonString = userService.fromSendVerifyCode(mobi, mobi, "您的验证码是：{$verifyCode$}，请及时输入验证。");
		return jsonString;
	}

	@RequestMapping(value = "/activityFationRun")
	@ResponseBody
	public Object activityFationRun(HttpServletRequest request, String mobi, String activityId, String verifyCode) {
		Map<String, String> map = new HashMap<String, String>();

		ResultObjOne<ActivityLottery> obj = meetPlaceService.beActivityLottery(activityId, mobi, verifyCode);
		if (obj != null && obj.isSuccess()) {
			ActivityLottery activityLottery = obj.getContent();
			if (activityLottery != null) {
				map.put("status", activityLottery.getIsDraw());
				map.put("message", activityLottery.getPrizeLevel());
				map.put("times", activityLottery.getRemainCount());
				if ("0".equals(activityLottery.getIsDraw())) {
					return map;
				}

			}
		} else {
			map.put("times", "0");
			map.put("code", obj.getCode());
			return map;
		}
		return map;
	}

	@RequestMapping(value = "/fashionrun/lucky")
	public String fashionrun() {
		return "meet/single/fashionRun";
	}
}
