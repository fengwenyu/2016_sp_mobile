package com.shangpin.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.google.gson.reflect.TypeToken;
import com.shangpin.web.vo.GiftCardSubmit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.GiftCard;
import com.shangpin.biz.bo.GiftCardBuy;
import com.shangpin.biz.bo.GiftCardKeyt;
import com.shangpin.biz.bo.GiftCardKeytContent;
import com.shangpin.biz.bo.GiftCardProductList;
import com.shangpin.biz.bo.GiftCardProductListItem;
import com.shangpin.biz.bo.GiftCardRecordList;
import com.shangpin.biz.bo.GiftCardStatus;
import com.shangpin.biz.bo.ProductDetail;
import com.shangpin.biz.bo.Province;
import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.RecProduct;
import com.shangpin.biz.bo.Receive;
import com.shangpin.biz.bo.ScanCodeRecharge;
import com.shangpin.biz.bo.SubmitOrder;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultBaseNew;
import com.shangpin.biz.bo.base.ResultObjMap;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizAddressService;
import com.shangpin.biz.service.SPBizGiftCardService;
import com.shangpin.biz.service.SPBizOrderService;
import com.shangpin.biz.service.SPBizProductService;
import com.shangpin.biz.service.SPBizRecProductService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.utils.JSONUtils;
import com.shangpin.utils.JsonUtil;
import com.shangpin.web.utils.Constants;

/**
 * 礼品卡控制层
 * 
 * @author zghw
 */
@Controller
public class GiftCardController extends BaseController {
	/** 礼品卡列表 */
	private static final String GIFTCARD_PRODUCT_LIST = "giftCard/product_list";
	/** 礼品卡列表部分 */
	private static final String GIFTCARD_PRODUCT_LIST_CELL = "giftCard/product_list_cell";
	/** 礼品卡记录列表 */
	private static final String GIFTCARD_RECORDLIST = "giftCard/record_list";
	/** 查询充值密钥成功 */
	private static final String GIFTCARD_RECHARGEPASSWD_SUCCESS = "giftCard/recharge_passwd_success";
	/** 查询充值密钥失败 */
	private static final String GIFTCARD_RECHARGEPASSWD_FAIL = "giftCard/recharge_passwd_fail";
	/** 充值于本账户 */
	private static final String GIFTCARD_RECHARGE_SELF = "giftCard/recharge_self";
	/** 礼品卡充值 */
	private static final String GIFTCARD_RECHARGE = "giftCard/recharge";
	/** 礼品卡电子卡 */
	private static final String GIFTCARD_RECHARGE_E = "giftCard/recharge_e";
	/** 礼品卡实物卡 */
	private static final String GIFTCARD_RECHARGE_ENTITY = "giftCard/recharge_entity";
	/** 礼品卡绑定电话号码 */
	private static final String GIFTCARD_STEP_TEL = "giftCard/step_tel";
	/** 礼品卡绑定支付密码 */
	private static final String GIFTCARD_STEP_PAY_PASSWD = "giftCard/step_pay_passwd";
	/** 电子卡充值成功 */
	private static final String GIFTCARD_ELECTRONIC_RECHARGE_SUCCESS = "giftCard/recharge_e_success";
	/** 电子卡充值失败 */
	private static final String GIFTCARD_ELECTRONIC_RECHARGE_FAIL = "giftCard/recharge_e_fail";
	/** 实物卡充值失败 */
	/*
	 * private static final String GIFTCARD_ENTITY_RECHARGE_FAIL =
	 * "giftCard/recharge_entity_fail";
	 */
	/** 实物卡展示页 */
	private static final String GIFTCARD_ENTITY_PAGE = "giftCard/recharge_entity_info";
	/** 详情页 */
	private static final String GIFT_E_CARD_DETAIL = "giftCard/gift_E_Card_detail";
	private static final String GIFTCARD_DETAIL = "giftCard/giftCard_detail";
	/** 校验短信 */
	private static final String VERIFY_PASSWORD = "giftCard/verify_password";
	/** 礼品卡错误页面 */
	private static final String GIFT_ERROR = "giftCard/404";
	/** 立即购买 */
	private static final String GIFT_PROCESS = "giftCard/gift_process";
	private static final String GIFTCARD_KEYT = "giftCard/giftcard_keyt";
	private static final String GIFTCARD_PREVIEW = "giftCard/giftCard_preview";
	private static final String GIFTCARD_LOGIN = "giftCard/giftCard_login";
	private static final String GIFTCARD_SEND = "giftCard/giftCard_send";

	// 礼品卡一键登录token
	private static final String ONE_KEY_LOGIN_TOKEN = "100";
	// 实物卡一键登录key
	private static final String ENTITY_KEY_RECHARGE = "101";
	@Autowired
	private SPBizGiftCardService giftCardService;
	@Autowired
	private SPBizProductService spBizProductService;
	@Autowired
	private SPBizRecProductService spBizRecProductService;
	@Autowired
	private SPBizAddressService bizAddressService;
	@Autowired
	private SPBizOrderService bizOrderService;
	@Autowired
	private SPBizUserService userService;

	private static final Logger logger = LoggerFactory
			.getLogger(GiftCardController.class);

	/**
	 * 礼品卡列表
	 * 
	 * @author zghw
	 */
	@RequestMapping("giftCard/productList")
	public String giftCardProductList(HttpServletRequest request, ModelMap model) {

		return GIFTCARD_PRODUCT_LIST;
	}
	 
	/**
	 * 礼品卡列表
	 * 
	 * @author zghw
	 */
	@RequestMapping("giftCard/productListCell")
	public String giftCardProductList(
			@RequestParam(value = "type", defaultValue = Constants.GIFTCARD_TYPE_E, required = false) String type,
			HttpServletRequest request, ModelMap model) {
		GiftCardProductList giftCardProductList = giftCardService
				.getGiftCardProductList(null, type, "0", "200");
		if (giftCardProductList != null
				&& giftCardProductList.getList() != null) {
			List<GiftCardProductListItem> giftCardList = giftCardProductList
					.getList();
			model.addAttribute("giftCardList", giftCardList);
		}
		model.addAttribute("giftCardProductList", giftCardProductList);
		return GIFTCARD_PRODUCT_LIST_CELL;
	}

	/**
	 * 礼品卡绑定手机号码
	 * 
	 * @author zghw
	 */
	@RequestMapping("giftCard/stepTel")
	public String giftCardStepTel(String oneKeyToken, ModelMap model) {
	    logger.info("skip to stepTel==>oneKeyToken:"+oneKeyToken);
		model.addAttribute("oneKeyToken", oneKeyToken);
		return GIFTCARD_STEP_TEL;
	}

	/**
	 * 发送验证码
	 * 
	 * @author zghw
	 */
	@RequestMapping("giftCard/sendVerifyCode")
	@ResponseBody
	public String sendVerifyCode(String phone, HttpServletRequest request) {
		// String userId = getUserId(request);
		String json = userService.fromSendVerifyCode(phone, phone,
				"您的验证码是：{$verifyCode$}，请及时输入验证。【尚品网】");
		return json;
	}

	/**
	 * 礼品卡绑定手机
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "giftCard/bindTel")
	public String bindTel(String phone, String verifyCode, String oneKeyToken,
			HttpServletRequest request, ModelMap model) {
	    logger.info("bindTel:oneKeyToken1--"+oneKeyToken);
		String userId = getUserId(request);
		model.put("phone", phone);
		ResultBase result = userService.beVerifyPhoneCode(phone, phone,
				verifyCode);
		if (!result.isSuccess()) {
			// 验证失败，返回重新输入手机号码
			model.put("msg", result.getMsg());
			return GIFTCARD_STEP_TEL;
		}
		// 判断手机号码已经使用过
		ResultObjOne<User> obj = userService.beFindUserInfo(phone);
		if (obj != null && obj.isSuccess() && obj.getContent() != null
				&& obj.getContent().getUserid() != null) {
			model.put("msg", "手机号码已绑定其他用户！");
			return GIFTCARD_STEP_TEL;
		}
		// 绑定手机号
		ResultBase bindObj = userService.beBindToUser(userId, "bind:" + phone);
		if (bindObj != null && bindObj.isSuccess()) {
		    logger.info("bindTel:oneKeyToken2--"+oneKeyToken);
			// 去第二步设置支付密码
			return giftCardStepPayPasswd(oneKeyToken, model);
		} else if (bindObj != null) {
			model.put("msg", bindObj.getMsg());
		} else {
			model.put("msg", "绑定手机失败！");
		}
		return GIFTCARD_STEP_TEL;
	}

	/*
	 * @RequestMapping(value = "giftCard/bindTel", method = RequestMethod.POST)
	 * public String bindTel(String phone, String verifyCode, HttpServletRequest
	 * request, ModelMap model) { String userId = getUserId(request);
	 * model.put("phone", phone); ResultBase result =
	 * userService.beVerifyPhoneCode(phone, phone, verifyCode); if
	 * (!result.isSuccess()) { // 验证失败，返回重新输入手机号码 model.put("msg",
	 * result.getMsg()); return GIFTCARD_STEP_TEL; } // 判断手机号码已经使用过
	 * ResultObjOne<User> obj = userService.beFindUserInfo(phone); if (obj !=
	 * null && obj.isSuccess() && obj.getContent() != null &&
	 * obj.getContent().getUserid() != null) { model.put("msg", "手机号码已绑定其他用户！");
	 * return GIFTCARD_STEP_TEL; } // 绑定手机号 ResultBase bindObj =
	 * userService.beBindToUser(userId, "bind:" + phone); if (bindObj != null &&
	 * bindObj.isSuccess()) { // 去第二步设置支付密码 return giftCardStepPayPasswd(); }
	 * else if (bindObj != null) { model.put("msg", bindObj.getMsg()); } else {
	 * model.put("msg", "绑定手机失败！"); } return GIFTCARD_STEP_TEL; }
	 */

	/***
	 * 礼品卡绑定支付密码
	 * 
	 * @author zghw
	 */
	@RequestMapping("giftCard/stepPayPasswd")
	public String giftCardStepPayPasswd(String oneKeyToken, ModelMap model) {
	    logger.info("skip to steppass==>oneKeyToken:"+oneKeyToken);
		model.addAttribute("oneKeyToken", oneKeyToken);
		return GIFTCARD_STEP_PAY_PASSWD;
	}

	/*
	 * @RequestMapping("giftCard/stepPayPasswd") public String
	 * giftCardStepPayPasswd() { return GIFTCARD_STEP_PAY_PASSWD; }
	 */

	/**
	 * 礼品卡绑定支付密码
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "giftCard/bindPasswd", method = RequestMethod.POST)
	public String bindPasswd(String passwd, String oneKeyToken,
			HttpServletRequest request, ModelMap model) throws Exception {
		String userId = getUserId(request);
		ResultBase obj = giftCardService.beSetGiftCardPassword(userId, passwd);
		logger.info("bindPasswd:oneKeyToken--"+oneKeyToken+"passwd:"+passwd);
		if (obj != null && obj.isSuccess()) {
		    logger.info("Thread.sleep====start");
		    Thread.sleep(5000);
		    logger.info("Thread.sleep====end");
			if (org.apache.commons.lang3.StringUtils.isNotBlank(oneKeyToken)
					&& ONE_KEY_LOGIN_TOKEN.equals(oneKeyToken.trim())) {
			    logger.info("bindPasswd:entityRecharge");
				return checkOneKeyToken(oneKeyToken, request);
			} else if (!StringUtils.isEmpty(oneKeyToken)
					&& ENTITY_KEY_RECHARGE.equals(oneKeyToken.trim())) {
			    logger.info("bindPasswd:entityRecharge");
				return "redirect:/giftCard/entityRecharge?type=101";
			} else {
			    logger.info("bindPasswd:recharge");
				return recharge(request, model);
			}
		} else if (obj != null) {
			model.put("msg", obj.getMsg());
		} else {
			model.put("msg", "设置支付密码失败!");
		}
		return GIFTCARD_STEP_PAY_PASSWD;
	}

	/**
	 * 判断是否是一键登录进来的充值
	 * 
	 * @param oneKeyToken
	 *            一键充值token
	 * @param request
	 * @return
	 */
	private String checkOneKeyToken(String oneKeyToken,
			HttpServletRequest request) {
		// 判断是否是一键登录进来的充值
		// 有页面传递的token
		GiftCard giftCard = (GiftCard) request.getSession().getAttribute(
				"giftToken");
		if (giftCard == null) {
			logger.info("session中无礼品卡token信息");
			return null;
		}
		return "forward:/giftCard/oneKeyRecharge.action?backFlag=erecharge";
	}

	/**
	 * 礼品卡记录列表
	 * 
	 * @author zghw
	 */
	@RequestMapping("giftCard/recordList")
	public String giftCardRecordList(
			HttpServletRequest request,
			ModelMap model,
			@RequestParam(value = "recordType", defaultValue = Constants.RECORD_TYPE_BUY, required = false) String recordType,
			@RequestParam(value = "pageNo", defaultValue = Constants.PAGE_NO_1_STR, required = false) String pageNo,
			String callbackUrl) {
		String userId = getUserId(request);
		String step = statusToStep(null, userId, model);
		if (step != null) {
			// 转到用户绑定礼品卡信息
			return step;
		}
		GiftCardRecordList giftCardRecord = giftCardService.getRecordList(
				userId, recordType, pageNo, Constants.PAGE_SIZE_20_STR);
		// 是否存在加载更多
		boolean isHaveMore = false;
		if (giftCardRecord != null
				&& (giftCardRecord.getList().size() >= Constants.PAGE_SIZE_20)) {
			isHaveMore = true;
		}
		model.addAttribute("giftCardRecord", giftCardRecord);
		model.addAttribute("recordType", recordType);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("isHaveMore", isHaveMore);

		if (!StringUtils.isEmpty(callbackUrl)) {
			return "giftCard/" + callbackUrl;
		}
		return GIFTCARD_RECORDLIST;
	}

	/**
	 * 获取电子卡充值密码
	 * 
	 * @author zghw
	 */
	@RequestMapping("giftCard/getRechargePasswd")
	public String getRechargePasswd(String orderId, HttpServletRequest request,
			ModelMap model) {
		String userId = getUserId(request);
		String step = statusToStep(null, userId, model);
		if (step != null) {
			// 转到用户绑定礼品卡信息
			return step;
		}
		String cardPasswd = giftCardService.getGiftCardRechargePasswd(userId,
				orderId);

		model.addAttribute("orderId", orderId);
		if (StringUtils.isEmpty(cardPasswd)) {
			return GIFTCARD_RECHARGEPASSWD_FAIL;
		}
		model.addAttribute("cardPasswd", cardPasswd);
		return GIFTCARD_RECHARGEPASSWD_SUCCESS;
	}

	/**
	 * 充值于本账户
	 * 
	 * @author zghw
	 */
	@RequestMapping("giftCard/toRecharge")
	public String toRecharge(String orderId, String giftCardId, String pic,
			String from, HttpServletRequest request, ModelMap model) {
		String userId = getUserId(request);
		if (!StringUtils.isEmpty(from) && from.equals("0")) {
			GiftCardKeyt giftCardKeyt = giftCardService.queryGiftCardSecretKey(
					orderId, userId);
			if (giftCardKeyt.getList() != null
					&& giftCardKeyt.getList().size() > 1) {
				return "redirect:/giftCard/recordList";
			}
			giftCardId = giftCardKeyt.getList() != null ? giftCardKeyt
					.getList().get(0).getId() : "";
		}

		String step = statusToStep(null, userId, model);
		if (step != null) {
			// 转到用户绑定礼品卡信息
			return step;
		}

		model.addAttribute("giftCardId", giftCardId);
		model.addAttribute("orderId", orderId);
		model.addAttribute("pic", pic);
		return GIFTCARD_RECHARGE_SELF;
	}

	/**
	 * 去电子卡充值页面
	 * 
	 * @author zghw
	 */
	@RequestMapping("giftCard/recharge")
	public String recharge(HttpServletRequest request, ModelMap model) {
		String userId = getUserId(request);
		String step = statusToStep(null, userId, model);
		if (step != null) {
			// 转到用户绑定礼品卡信息
			return step;
		}
		logger.info("bindPasswd:recharge go to:"+GIFTCARD_RECHARGE);
		return GIFTCARD_RECHARGE;
	}

	/**
	 * 电子卡充值页面
	 * 
	 * @author zghw
	 */
	@RequestMapping("giftCard/rechargeE")
	public String rechargeE() {
		return GIFTCARD_RECHARGE_E;
	}

	/**
	 * 实物卡充值页面
	 * 
	 * @author zghw
	 */
	@RequestMapping("giftCard/rechargeEntity")
	public String rechargeEntity() {
		return GIFTCARD_RECHARGE_ENTITY;
	}

	/**
	 * 电子卡充值
	 * 
	 * @author zghw
	 */
	@RequestMapping("giftCard/electronicRecharge")
	public String giftCardElectronicRecharge(String orderId, String giftCardId,
			String backFlag, String passwd, HttpServletRequest request,
			ModelMap model) {
		String userId = getUserId(request);
		String step = statusToStep(null, userId, model);
		if (step != null) {
			// 转到用户绑定礼品卡信息
			return step;
		}
		String cardPasswd = null;
		if (StringUtils.isEmpty(orderId) && !StringUtils.isEmpty(passwd)) {
			// 密码充值
			cardPasswd = passwd;
		} else if (!StringUtils.isEmpty(giftCardId)) {
			// 根据订单号查询密钥充值
			//cardPasswd = giftCardService.getGiftCardRechargePasswd(userId, orderId);
       		cardPasswd = giftCardService.getGiftCardRechargePasswdByCardId(userId, giftCardId);
			logger.info("card electronicRecharge:"+cardPasswd);
		}
		logger.info("orderId：" + orderId + " =passwd:" + passwd
				+ " =giftCardId:" + giftCardId + "=cardPasswd:" + cardPasswd);
		model.addAttribute("passwd", passwd);
		model.addAttribute("orderId", orderId);
		if (StringUtils.isEmpty(cardPasswd)) {
			model.addAttribute("msg", "充值失败！");
			if ("erecharge".equals(backFlag)) {
				return GIFTCARD_RECHARGE_E;
			}
			// 转向充值失败页面
			return GIFTCARD_ELECTRONIC_RECHARGE_FAIL;
		}
	   ResultObjMap<String> obj = giftCardService.beElectronicRecharge(userId,
	                orderId, cardPasswd);
		logger.info("充值本账户："+JsonUtil.toJson(obj));
		logger.info("electronicRecharge to own account："+JsonUtil.toJson(obj));
		if (obj == null || !obj.isSuccess()) {
			if (obj != null) {
				model.addAttribute("msg", obj.getMsg());
			} else {
				model.addAttribute("msg", "充值失败！");
			}
			if ("erecharge".equals(backFlag)) {
				return GIFTCARD_RECHARGE_E;
			}
			return GIFTCARD_ELECTRONIC_RECHARGE_FAIL;
		}
		String rechargeMoney = obj.getObj("rechargeMoney");
		model.addAttribute("rechargeMoney", rechargeMoney);
		// 转向充值成功页面
		return GIFTCARD_ELECTRONIC_RECHARGE_SUCCESS;
	}

	/**
	 * 实物卡充值
	 * 
	 * @author zghw
	 */
	@RequestMapping("giftCard/entityRecharge")
	public String giftCardEntityRecharge(String cardno, String passwd,
			String type, HttpServletRequest request, ModelMap model) {
		String userId = getUserId(request);
		String step = statusToStep(null, userId, model);
		if (step != null) {
			// 转到用户绑定礼品卡信息
		    logger.info("step:error"+step);
			return step;
		}
		 logger.info("step is isNull"+step+"cardno:"+cardno);
		// 一键充值实物卡的处理
		if (!StringUtils.isEmpty(type) && ENTITY_KEY_RECHARGE.equals(type)) {
			ScanCodeRecharge scanCodeRecharge = getParameter(request);
			if (scanCodeRecharge == null) {
			    model.put("value", scanCodeRecharge.getValue());
				model.put("msg", "请重新扫描二维码信息！");
				return GIFTCARD_ENTITY_PAGE;
			}
			model.put("value", scanCodeRecharge.getValue());
			//实物卡是否是激活状态。
	        String msg=statusToMsg(scanCodeRecharge.getId());
	        if(msg!=null){
	            model.put("msg", msg);
	            return GIFTCARD_ENTITY_PAGE;
	        }
			cardno = scanCodeRecharge.getId();
			passwd = scanCodeRecharge.getPwd();
		}
		ResultObjMap<String> obj = giftCardService.beGiftCardEntityRecharge(
				userId, cardno, passwd);
		if (obj != null && obj.isSuccess()) {
			String rechargeMoney = obj.getObj("rechargeMoney");
			model.put("rechargeMoney", rechargeMoney);
			// 转向充值成功页面
			return GIFTCARD_ELECTRONIC_RECHARGE_SUCCESS;
		} else {
			if (obj != null) {
				model.addAttribute("msg", obj.getMsg());
			} else {
				model.addAttribute("msg", "充值失败！");
			}
			// 转向充值失败页面
			return GIFTCARD_RECHARGE_ENTITY;
		}
	}

	/***
	 * @author wh
	 *  实物卡充值一键充值
	 * @param model
	 * @param request
	 * @return page
	 */
    @RequestMapping(value="giftCard/quickEntityRecharge")
    public String giftCardQuickEntityRecharge(ModelMap model,HttpServletRequest request) {
        String userId = getUserId(request);
        ScanCodeRecharge sRecharge = getParameter(request);
        if (sRecharge == null) {
            model.put("value", sRecharge.getValue());
            model.put("msg", "请重新扫描二维码信息！");
            return GIFTCARD_ENTITY_PAGE;
        }
         model.put("value", sRecharge.getValue());
        //实物卡是否是激活状态。
        String msg=statusToMsg(sRecharge.getId());
        if(msg!=null){
            model.put("msg", msg);
            return GIFTCARD_ENTITY_PAGE;
        }
        String step = statusToStep(ENTITY_KEY_RECHARGE,userId, model);
        if (step != null) {
            // 转到用户绑定礼品卡信息
            return step;
        }
        ResultObjMap<String> obj = giftCardService.beGiftCardEntityRecharge(userId, sRecharge.getId(), sRecharge.getPwd());
        if(obj!=null && obj.isSuccess()){
            String rechargeMoney=obj.getObj("rechargeMoney");
            model.put("rechargeMoney", rechargeMoney);
            // 转向充值成功页面
            return GIFTCARD_ELECTRONIC_RECHARGE_SUCCESS;
        }else{
            if(obj!=null){
                model.addAttribute("msg",obj.getMsg());
            }else{
                model.addAttribute("msg","充值失败！" );
            }
            
            // 转向充值失败页面
            return GIFTCARD_ENTITY_PAGE;
        }
    }


    /**
     * @author wh
     * 获取会话中的参数
     * @param request
     * @return ScanCodeRecharge
     */
    private ScanCodeRecharge getParameter(HttpServletRequest request) {
        ScanCodeRecharge sCharge =(ScanCodeRecharge)request.getSession().getAttribute("scRecharge");
        if (sCharge!=null) {
             Boolean isParametEmpty=!StringUtils.isEmpty(sCharge.getId())&&!StringUtils.isEmpty(sCharge.getPwd())&&!StringUtils.isEmpty(sCharge.getT())&&!StringUtils.isEmpty(sCharge.getValue());
             if (isParametEmpty) {
                return sCharge;
             }
             return null;
        }
        return null;
    }


    /**
     * 根据礼品卡状态决定是否充值
     * 
     * @author wh
     * @param cardNo
     * @return Boolean
     */
    private String statusToMsg(String cardNo) {
        // 实物卡是否是激活状态。
        ResultObjOne<GiftCardStatus> obj = giftCardService.findGetGiftCardStatus(cardNo);
        if (obj != null && obj.isSuccess()) {
            GiftCardStatus giftCardStatus = obj.getObj();
            String status = giftCardStatus.getStatusCode();
            if (!StringUtils.isEmpty(status)) {
                if (status.equals("3")) {
                    return null;
                } else if (status.equals("6") || status.equals("7")) {
                    return "该礼品卡已经被充值，如有问题，请联系尚品网客服4006-900-900";
                } 
            } 
        }
        return "该礼品卡的状态异常，请联系尚品网客服4006-900-900";
    }

	/**
	 * 电子卡、实物卡详情
	 * 
	 * @param productNo
	 *            商品编号
	 * @param topicId
	 *            活动编号
	 * @param picNo
	 *            列表页的图片编号
	 * @param ch
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("giftCard/cardDetail")
	public String giftcardDetail(@RequestParam String productNo,
			String topicId, String picNo, String ch, String type, Model model,
			HttpServletRequest request) {
		model.addAttribute("productNo", productNo);
		model.addAttribute("topicId", topicId);
		// 解决cookie刷新不及时的问题，只有微信浦发银行时用到
		model.addAttribute("ch", ch);
		User user = getSessionUser(request);
		String userId = user != null ? user.getUserid() : "";
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		ProductDetail productDetail = spBizProductService.findProductDetail(
				topicId, productNo, userId, picNo);
		List<RecProduct> guessLikeList = spBizRecProductService
				.findRecProductObj(userId, Constants.GUESSLIKE_RECPRODUCT_TYPE,
						Constants.SHANGPIN_SHOPTYPE, productNo,
						Constants.GUESSLIKE_RECPRODUCT_START,
						Constants.GUESSLIKE_RECPRODUCT_END);

		model.addAttribute("productDetail", productDetail);
		model.addAttribute("guessLikeList", guessLikeList);
		model.addAttribute("userLv", userLv);

		String retunJsp = GIFTCARD_DETAIL;
		if ("2".equals(type)) {
			retunJsp = GIFTCARD_DETAIL;
		} else if ("1".equals(type)) {
			retunJsp = GIFT_E_CARD_DETAIL;
		}
		return retunJsp;
	}

	/**
	 * 礼品卡购买
	 * 
	 * @param skuId
	 * @param productId
	 * @param amount
	 * @param type
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "giftCard/giftProcess")
	public String buy(String skuId, String productId, String amount,
			String type, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String userId = getUserId(request);

		ResultObjOne<GiftCardBuy> giftCardBuys = giftCardService.doGiftCardBuy(
				userId, "", productId, amount, "", type, "0");
		GiftCardBuy giftCardBuy = giftCardBuys.getContent();
		if ("1".equals(type)) {
			model.addAttribute("haveAddress", false);
			List<Province> provinces = bizAddressService.findProvinceListObj();
			model.addAttribute("provinces", provinces);
		} else if ("2".equals(type)) {
			List<Receive> addresses = giftCardBuy.getReceive();
			if (addresses.size() > 0) {
				model.addAttribute("address", address(addresses, giftCardBuy.getLastReceiveId()));
				model.addAttribute("haveAddress", true);
			}
			List<Province> provinces = bizAddressService.findProvinceListObj();
			model.addAttribute("provinces", provinces);
		}
		double realPay = Double.parseDouble(giftCardBuy.getTotalAmount())
				+ Double.parseDouble(giftCardBuy.getCarriage().getAmount() == null ? "0"
						: giftCardBuy.getCarriage().getAmount());
		model.addAttribute("real_pay", realPay);
		model.addAttribute("type", type);
			model.addAttribute("buyNow", giftCardBuy);
		return GIFT_PROCESS;
	}

	@RequestMapping(value = "/giftCard/submit", method = RequestMethod.POST)
	@ResponseBody
	public String submitOrder(SubmitOrder order, HttpServletRequest request) {
		String userid = getUserId(request);
		order.setUserid(userid);
		Map<String, Object> map = bizOrderService.submitGiftOrder(order);
		return JsonUtil.toJson(map);
	}

	/**
	 * 520修改发票信息购买礼品卡链接
	 * @param order
	 * @param request
     * @return
     */
	@RequestMapping(value = "/giftCard/submitV2", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> submitOrderV2(GiftCardSubmit giftCardSubmit, HttpServletRequest request) {
		String userid = getUserId(request);
		giftCardSubmit.setUserid(userid);
		Map<String,Object> map =new HashMap<>();
		//校验参数
		if(giftCardSubmit.validate()){
			//userId, addressId, invoiceFlag, invoiceAddressId, invoiceType, invoiceTitle, invoiceContent, express, orderFrom, buysIds,
			//		payTypeId, payTypeChildId, orderType, type,invoiceEmail, invoiceTel
			final String json = giftCardService.fromGiftCardCommit(userid, giftCardSubmit.getAddrid(), giftCardSubmit.getInvoiceflag()
					, giftCardSubmit.getInvoiceaddrid(), giftCardSubmit.getInvoicetype(), giftCardSubmit.getInvoicetitle()
					, giftCardSubmit.getInvoicecontent(), giftCardSubmit.getExpress(), giftCardSubmit.getOrderfrom()
					, giftCardSubmit.getBuysIds(), giftCardSubmit.getPaytypeid(), giftCardSubmit.getPaytypechildid()
					, giftCardSubmit.getOrdertype(), giftCardSubmit.getType(), giftCardSubmit.getInvoiceEmail()
					, giftCardSubmit.getInvoiceTel());

			if(StringUtil.isNotEmpty(json)){
				ResultObjOne<Map<String,String>> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<Map<String,String>>>() {});
				if(result.isSuccess()){
					map.put("code",result.getCode());
					map.put("orderInfo",result.getContent());
				}else{
					map.put("code",result.getCode());
					map.put("msg",result.getMsg());
				}
			}else{
				map.put("code","error");
				map.put("msg","接口数据错误！");
			}

		}else{
			map.put("code","error");
			map.put("msg","接口数据错误！");
		}
		return map;
	}

	@RequestMapping(value = "/giftCard/sendFriend", method = RequestMethod.GET)
	public String giftCardSendFriend(String orderId, Model model,
			HttpServletRequest request) {

		String userid = getUserId(request);
		GiftCardKeyt giftCardKeyt = giftCardService.queryGiftCardSecretKey(
				orderId, userid);

		if (giftCardKeyt.getList() != null && giftCardKeyt.getList().size() > 1) {
			return "redirect:/giftCard/recordList";
		}
		model.addAttribute("giftCardKeyt", giftCardKeyt);
		GiftCardKeytContent giftCardKeytContent = giftCardKeyt.getList().get(0);
		String sendUrl = "/giftCard/skipToGiftCardSend?giftCardId="
				+ giftCardKeytContent.getId() + "&giftOrder="
				+ giftCardKeytContent.getOrderId() + "&faceValue="
				+ giftCardKeytContent.getFaceValue();
		return "redirect:" + sendUrl;

	}

	@RequestMapping(value = "/giftCard/queryGiftCardSecretKey", method = RequestMethod.GET)
	public String queryGiftCardSecretKey(String orderId, Model model,
			HttpServletRequest request) {

		String userid = getUserId(request);
		GiftCardKeyt giftCardKeyt = giftCardService.queryGiftCardSecretKey(
				orderId, userid);

		model.addAttribute("giftCardKeyt", giftCardKeyt);
		return GIFTCARD_KEYT;
	}

	private Receive address(List<Receive> addresses, String lastAddressId) {
		if (StringUtils.isEmpty(lastAddressId)) {
			return defaultAddress(addresses);
		} else {
			for (Receive address : addresses) {
				String addressId = address.getId();
				if (addressId.equals(lastAddressId)) {
					return address;
				}
			}
			return defaultAddress(addresses);
		}
	}

	private Receive defaultAddress(List<Receive> addresses) {
		for (Receive address : addresses) {
			if ("1".equals(address.getIsd())) {
				return address;
			}
		}
		return addresses.get(0);
	}

	/**
	 * 根据不同的礼品卡绑定状态转向到不同的页面
	 * 
	 * @author zghw
	 */
	private String statusToStep(String type, String userId, ModelMap model) {
		ResultObjOne<GiftCardStatus> obj = giftCardService
				.beGiftCardStatus(userId);
		if (obj != null && obj.isSuccess()) {
			GiftCardStatus giftCardStatus = obj.getObj();
			String status = giftCardStatus.getStatusCode();
			logger.info("giftCardStatus:--"+status);
			if (!StringUtils.isEmpty(status)) {
				if ("000".equals(status)) {
					// 未绑定手机
				    logger.info("statusToStep phone:type--"+type);
					return giftCardStepTel(type, model);
				} else if ("100".equals(status)) {
					// 未设置支付密码
				    logger.info("statusToStep pass:type--"+type);
					return giftCardStepPayPasswd(type, model);
				} else if ("110".equals(status)) {
					// 礼品卡未绑定
					return null;
				} else if ("111".equals(status)) {
					// 礼品卡已绑定
					return null;
				}
			}
		}
		return null;
	}

	/**
     * 赠送礼品卡给小伙伴，存储对方手机号、祝福语、祝福图片
     * @param  phoneNum 对方手机号
     * @param  picName 祝福图片的本地路径
     * @param  wishMsg 祝福语
     * @param  giftOrderId 礼品卡订单号
     * @Description: 
     * @author By fengwenyu
     * @Create Date 2015年12月23日
     */
    @RequestMapping(value = "/giftCard/sendGiftCard")
    @ResponseBody
    public ResultBaseNew sendGiftCard(GiftCard giftCard,String userid,String requestHead,HttpServletRequest request){
    	User user = null;
    	String ua = request.getHeader("User-Agent").toLowerCase();
    	if(ClientUtil.CheckApp(ua)){
    		user = userService.findUserByUserId(userid);
    	}else{
    		user = getSessionUser(request);
    	}
    	if(user==null){
            return ResultBaseNew.build("1", "用户未登陆");
    	}
    	String giftOrder = giftCard.getGiftOrder();
    	String giftCardId = giftCard.getGiftCardId();
    	String faceValue = giftCard.getFaceValue();
        String sendPhoneNum = giftCard.getSendPhoneNum();
        String wishMsg = giftCard.getWishMsg();
        String wishPic = giftCard.getWishPic();
        
    	String nickName = user.getNickName();
    	String name = user.getName();
    	if(org.apache.commons.lang3.StringUtils.isBlank(name)&&org.apache.commons.lang3.StringUtils.isBlank(nickName)){
    		return ResultBaseNew.build("1", "系统有误，请重试！");
    	}
    	String buyerName =org.apache.commons.lang3.StringUtils.isBlank(nickName)?name : nickName;
    	
    	if(org.apache.commons.lang3.StringUtils.isBlank(wishMsg)){
            return ResultBaseNew.build("1", "祝福信息不能为空！");
        }
        if(org.apache.commons.lang3.StringUtils.isBlank(wishPic)){
        	return ResultBaseNew.build("1", "祝福图片不能为空！");
        }
        if(org.apache.commons.lang3.StringUtils.isBlank(sendPhoneNum)){
        	return ResultBaseNew.build("1", "手机号码不能为空！");
        }
        // 验证手机号码是否正确
        boolean matches = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[^1^4,\\D]))\\d{8}").matcher(sendPhoneNum).matches();
        if(!matches){
        	return ResultBaseNew.build("1", "手机号码格式不正确！");
        }
        //校验礼品卡是否充值
        boolean canRecharge = canBeActiveByCardId(giftCardId);
        if(!canRecharge){
        	return ResultBaseNew.build("1", "该礼品卡已被充值！");
        }
        //将信息存放到redis
        long millis = System.currentTimeMillis();
        giftCard.setBuyerName(buyerName);
        giftCard.setSendTime(millis+"");
        giftCard.setUserId(userid);
        String result = giftCardService.saveGiftCardToDb(giftCard);
        logger.info("result==:"+result);
        ResultBaseNew resultBaseNew = ResultBaseNew.format(result);
        String code = resultBaseNew.getCode();
        if(code ==null || !code.equals("0")){
        	return resultBaseNew;
        }
        String msg = "giftCardId="+giftCardId+"8uuuuu8sendPhoneNum="+sendPhoneNum;
        String msg2 = "giftCardId="+giftCardId+"&sendPhoneNum="+sendPhoneNum;
        // String serverName = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        //int serverPort = request.getServerPort();
        giftCard.setSendTime(requestHead);
        giftCard.setFaceValue(msg2);
        return ResultBaseNew.build("0", msg, giftCard);
    }
    
    /**
     * 跳转到发送验证码页面
     * @param giftOrderId 礼品卡ID
     * @param phoneNum 领取人手机号
     * @param request
     * @return
     */
    @RequestMapping(value = "/giftCard/skipToVerifyCode")
    public String skipToVerifyCode(GiftCard giftCard,Model model,HttpServletRequest request){
    	if(giftCard==null){
    		return null;
    	}
    	String giftCardId = giftCard.getGiftCardId();
        String sendPhoneNum = giftCard.getSendPhoneNum();
        if(!StringUtil.isNotEmpty(giftCardId,sendPhoneNum)){
        	return null;
        }
        String result = giftCardService.getGiftCardPicAndMsg(giftCard);
        logger.info("result:"+result);
        ResultBaseNew resultBaseNew = ResultBaseNew.formatToPojo(result, GiftCard.class);
        if(resultBaseNew==null){
        	return null;
        }
        String code = resultBaseNew.getCode();
        if("1".equals(code)){
            return "giftCard/payment_cancel";
        }else if("2".equals(code)){
        	return "giftCard/payment_success";
        }else if("0".equals(code)){
    	//校验礼品卡是否充值
        boolean canRecharge = canBeActiveByCardId(giftCardId);
        if(!canRecharge){
        	return "giftCard/payment_success";
        }
        GiftCard giftCard2 = (GiftCard) resultBaseNew.getContent();
    	request.getSession().setAttribute("giftToken", giftCard2);//有问题的代码
    	model.addAttribute("giftCard", giftCard2);
    	String str1 = sendPhoneNum.substring(0, 3);
		String str2 = "****";
		String str3 = sendPhoneNum.substring(7);
		String phone = str1+str2+str3;
    	model.addAttribute("sendPhoneNum",phone);
    	return VERIFY_PASSWORD; 
        }else{
        return null;
        }
    } 
    
    /**
     * 发送验证码只用于礼品卡赠送逻辑
     * @param request
     * @return
     */
    @RequestMapping(value = "/giftCard/sendVerifyCodeToFriend")
    @ResponseBody
    public String sendVerifyCodeToFriend(HttpServletRequest request){
    	GiftCard  giftCard =(GiftCard) request.getSession().getAttribute("giftToken");
    	if(giftCard==null){
    	    return JsonUtil.toJson(ResultBaseNew.build("1", "请求不合法！"));
    	}
    	String phoneNum = giftCard.getSendPhoneNum();
    	// 验证手机号码是否正确
        boolean matches = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[^1^4,\\D]))\\d{8}").matcher(phoneNum).matches();
        if (!matches) {
        	return JsonUtil.toJson(ResultBaseNew.build("1", "手机号码格式不正确！"));
        }
        String msgTemplate = "礼品卡短信验证码：{$verifyCode$}，请勿向任何人提供您收到的短信验证码。";
        // 发送手机号码
        ResultBase obj = userService.beSendVerifyCode(phoneNum, phoneNum,msgTemplate);
        logger.info("短信obj："+JsonUtil.toJson(obj));
        if (obj == null || !obj.isSuccess()){
                return JsonUtil.toJson(ResultBaseNew.build("1", "发送短信失败。"));
            }
    	return JsonUtil.toJson(ResultBaseNew.build("0", "发送短信成功。")); 
    } 
    
    /**
     * 验证短信验证码
     * @param request
     * @return
     */
    @RequestMapping(value = "/giftCard/checkVerifyPassword")
    @ResponseBody
    public String checkVerifyPassword(@RequestParam(required=true)String smscode,HttpServletRequest request){
    	GiftCard  giftCard =(GiftCard) request.getSession().getAttribute("giftToken");
    	if(giftCard==null){
    	    return JsonUtil.toJson(ResultBaseNew.build("1", "请求不合法！"));
    	}
    	String phoneNum = giftCard.getSendPhoneNum();
        // 验证手机号码是否正确
        boolean matches = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[^1^4,\\D]))\\d{8}").matcher(phoneNum).matches();
        if (!matches) {
            return JsonUtil.toJson(ResultBaseNew.build("1", "手机号码格式不正确！"));
        }
        ResultBase obj = userService.beVerifyPhoneCode(phoneNum, phoneNum,smscode);
        if (obj == null || (!obj.isSuccess())) {
            return JsonUtil.toJson(ResultBaseNew.build("1", "验证码输入错误！"));
        }
        return JsonUtil.toJson(ResultBaseNew.build("0", "验证码验证成功！"));
    } 
    
    /**
     * 礼品卡页面一键登录（仅适用于礼品卡）
     * @param request
     * @return
     * @author fengwenyu
     */
    @RequestMapping(value = "/giftCard/checkUser")
    @ResponseBody
    public Map<String, String> checkUser(HttpServletRequest request){
    	Map<String, String> map =new HashMap<String, String>();
    	GiftCard giftCard = (GiftCard) request.getSession().getAttribute("giftToken");
        if(giftCard==null){
        	map.put("code", "1");
        	map.put("msg","请求不合法！");
            return map;
        }
        String sendPhoneNum = giftCard.getSendPhoneNum();
        logger.info("sendPhoneNum:"+sendPhoneNum);
        String channelNo = "42";//需要修改
        QuickUser quickUser = userService.checkPhoneUser(sendPhoneNum, channelNo);
        logger.info("quickUser:"+JsonUtil.toJson(quickUser));
        
        if (quickUser != null) {
        	String str1 = sendPhoneNum.substring(0, 3);
    		String str2 = "****";
    		String str3 = sendPhoneNum.substring(7);
    		String phone = str1+str2+str3;
        	if ("1".equals(quickUser.getIsNewUser())) {
        		map.put("code", "00");
            	map.put("msg",phone);//新用户
            }else if("0".equals(quickUser.getIsNewUser())){
            	map.put("code", "01");
            	map.put("msg",phone);//旧用户
            }else{
            	map.put("code", "1");
            	map.put("msg","查询出错，请重试");
            }
        }else{
        	map.put("code", "01");
        	map.put("msg","系统出错，请重试");
        }
        return map;
    }
    
    /**
     * 礼品卡页面一键登录（仅适用于礼品卡）
     * @param request
     * @return
     * @author fengwenyu
     */
    @RequestMapping(value = "/giftCard/oneKeyLogin")
    public String oneKeyLogin(HttpServletRequest request,ModelMap model){
    	GiftCard giftCard = (GiftCard) request.getSession().getAttribute("giftToken");
        if(giftCard==null){
           // return JsonUtil.toJson(ResultBaseNew.build("1", "请求不合法！"));
        	return null;
        }
        String giftCardId = giftCard.getGiftCardId();
        String sendPhoneNum = giftCard.getSendPhoneNum();
        logger.info("regist phoneNum:"+sendPhoneNum);
        // 通过手机号判断是否是新用户
        
        String channelNo = "42";//需要修改
        QuickUser quickUser = userService.checkPhoneUser(sendPhoneNum, channelNo);
        if (quickUser != null) {
            if ("1".equals(quickUser.getIsNewUser())) {
                // 如果是新用户则下发短信。
                String msgTemplate = "您好，您已注册成为尚品网用户，将享受尚品网会员权益。您可使用本手机号登录，默认密码为手机号后6位，请及时修改密码。";
                userService.beSendVerifyCode(sendPhoneNum, sendPhoneNum, msgTemplate);
            }
            String userId = quickUser.getUserId();
            // 查询用户信息对象
            User findUser = userService.findUserByUserId(userId);
            String sessionId = generateSessionId();
            findUser.setSessionid(sessionId);
            
            //放入session
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Constants.SESSION_USER);
            if (!StringUtils.isEmpty(user)) {
                session.removeAttribute(Constants.SESSION_USER);
            }
            user = findUser;
            session.setAttribute(Constants.SESSION_USER, user);
            // 将userId存入session，便于日志查看
            session.setAttribute(Constants.SESSION_USERID, user.getUserid());
            logger.info("查询的user:"+user+"    ====>session中user:"+JsonUtil.toJson(session.getAttribute(Constants.SESSION_USER)));
            //判断跳转到哪个页面
            String bindPhone = user.getBindPhone();//0未绑定手机号   1绑定了手机号
            String bindGiftPassword = user.getBindGiftPassword();//0:未设置礼品卡密码 1：设置了礼品卡密码
            //一键充值的token证明
            String oneKeyToken= ONE_KEY_LOGIN_TOKEN;
            if("0".equals(bindPhone)){
            	//未设置手机号
            	return giftCardStepTel(oneKeyToken, model);
            }
            if("0".equals(bindGiftPassword)){
            	//未设置支付密码
            	return giftCardStepPayPasswd(oneKeyToken,model);
            }
            //调到一键充值
            return "forward:/giftCard/oneKeyRecharge.action?backFlag=erecharge";
        }
        return null;
    }
    
    /**
     * 一键充值页面
     * @param backFlag 标记
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/giftCard/oneKeyRecharge")
    public String oneKeyRecharge(String backFlag,HttpServletRequest request,ModelMap model){
    	GiftCard giftCard = (GiftCard) request.getSession().getAttribute("giftToken");
        if(giftCard==null){
        	logger.info("session中无礼品卡token信息");
        	return null;
        }
        String cardPasswd = giftCardService.oneKeyRechargeGetPass(giftCard);
        
        User user = getSessionUser(request);
        String userId = user.getUserid();
        String step = statusToStep(ONE_KEY_LOGIN_TOKEN,userId, model);

		if (step != null) {
			// 转到用户绑定礼品卡信息
			return step;
		}
		model.addAttribute("passwd", cardPasswd);
		model.addAttribute("orderId", "");

		if (StringUtils.isEmpty(cardPasswd)) {
			model.addAttribute("msg", "充值失败！");
			if ("erecharge".equals(backFlag)) {
				return GIFTCARD_RECHARGE_E;
			}
			// 转向充值失败页面
			return GIFTCARD_ELECTRONIC_RECHARGE_FAIL;
		}
		//校验礼品卡是否充值
        boolean canRecharge = canBeActiveByCardId(giftCard.getGiftCardId());
        if(!canRecharge){
        	model.addAttribute("msg", "该礼品卡已被充值！");
        	return GIFTCARD_ELECTRONIC_RECHARGE_FAIL;
        }
        ResultObjMap<String> obj = giftCardService.beElectronicRecharge(userId,"", cardPasswd);//主站接口需要将orderId传空
        if (obj == null || !obj.isSuccess()) {
			if (obj != null) {
				model.addAttribute("msg", obj.getMsg());
			} else {
				model.addAttribute("msg", "充值失败！");
			}
			if ("erecharge".equals(backFlag)) {
				return GIFTCARD_RECHARGE_E;
			}
			return GIFTCARD_ELECTRONIC_RECHARGE_FAIL;
		}
		String rechargeMoney = obj.getObj("rechargeMoney");
		model.addAttribute("rechargeMoney", rechargeMoney);
		String recgargeTime = System.currentTimeMillis() + "";
		String data = giftCardService.oneKeyRechargeUpdateDb(userId,giftCard.getSendPhoneNum(), giftCard.getGiftCardId(),recgargeTime);
		ResultBaseNew resultBaseNew = ResultBaseNew.format(data);
		if (!resultBaseNew.isSuccess()) {
			logger.info("保存数据失败");
			return null;
		}
		//您赠送给手机号码xxxx的礼品卡已经被充值。
		 String msgTemplate = "您赠送给手机号码"+giftCard.getSendPhoneNum()+"的礼品卡已经被充值。";
		 String userid= giftCard.getUserId();
		 User user2 = userService.findUserByUserId(userid);
		 //logger.info("user2:====>"+JsonUtil.toJson(user2));
		 if(user2==null||!org.apache.commons.lang3.StringUtils.isBlank(user2.getMobile())){
			 logger.info("user2.getMobile()"+user2.getMobile());
			 ResultBase beSendVerifyCode = userService.beSendVerifyCode(user2.getMobile(), user2.getMobile(), msgTemplate);
		 }		// 转向充值成功页面
		return GIFTCARD_ELECTRONIC_RECHARGE_SUCCESS;
	}

	private static final String generateSessionId() {
		Random ran = new Random(System.currentTimeMillis());

		final int bits = 24;
		StringBuilder strBuff = new StringBuilder();
		for (int i = bits - 1; i >= 0; i--) {
			final int value = ran.nextInt(10 + 26);
			if (value < 10) {
				strBuff.append(value);
			} else if (value < 10 + 26) {
				strBuff.append((char) ('A' + (value - 10)));
			}
		}

		return strBuff.toString();
	}
/**
     * 跳转到赠送给小伙伴页面
     * @return
     */
    @RequestMapping("/giftCard/skipToGiftCardSend")
    public String skipToGiftCardSend(GiftCard giftCard,HttpServletRequest request,Model model){
    	String ua1 = request.getHeader("User-Agent");//iso
    	String ua2 = request.getHeader("origin");//安卓
    	String userId = "";
    	if(org.apache.commons.lang3.StringUtils.isNotBlank(ua2)){
    		ua2 = ua2.toLowerCase();
    	}
    	logger.info("ua1:"+ua1+"   ua2:"+ua2);
    	if(ClientUtil.CheckApp(ua1)||ClientUtil.CheckOrigin(ua2)){
    		userId = request.getHeader("userid");
    		if(userId==null){
    			userId=request.getParameter("userId");
    		}
    	}else{
    		userId = (String)request.getSession().getAttribute(Constants.SESSION_USERID);
    	}
    	logger.info("userid:"+userId);
    	String giftOrder = giftCard.getGiftOrder();
    	String giftCardId = giftCard.getGiftCardId();
    	String faceValue = giftCard.getFaceValue();
    	if(com.shangpin.pay.utils.common.StringUtil.isEmpty(giftOrder,giftCardId,faceValue)){
    		logger.info("参数传递为空 giftOrder:"+giftOrder+"=>giftCardId:"+giftCardId+"+=>faceValue:"+faceValue);
    		return null;
    	}
    	model.addAttribute("giftCard", giftCard);
    	model.addAttribute("userid", userId);
    	return GIFTCARD_SEND;
    }
    
    /**
     * 跳转到查看礼品卡页面
     */
    @RequestMapping(value = "giftCard/giftCardPreview")
    public String skipToPreview(Model model,HttpServletRequest request){
    	GiftCard  giftCard =(GiftCard) request.getSession().getAttribute("giftToken");
    	if(giftCard==null){
    	    return null;
    	}
    	String wishPic = giftCard.getWishPic();
    	int index = wishPic.lastIndexOf(".jpg");
    	String wishPicNew = wishPic.substring(0, index)+"_big.jpg";
    	logger.debug("wishPic:"+wishPic+"  wishPicNew:"+wishPicNew);
    	model.addAttribute("wishPic", wishPicNew);
        model.addAttribute("giftCard", giftCard);
    	return GIFTCARD_PREVIEW;
    }
    
    /**
     * 跳转到礼品卡登录页面
     */
    @RequestMapping(value = "giftCard/skipToCardLogin")
    public String skipToCardLogin(HttpServletRequest request){
    	GiftCard  giftCard =(GiftCard) request.getSession().getAttribute("giftToken");
    	if(giftCard==null){
    	    return null;
    	}
    	return GIFTCARD_LOGIN;
    }    
    /**
     * 判断礼品卡是否可以激活   true为可激活 false为不可激活
     * @param giftCardId
     * @return
     */
    private boolean canBeActiveByCardId(String giftCardId){
    	String result = giftCardService.getGiftCardStatusByGiftCardId(giftCardId);
    	ResultBaseNew baseNew = ResultBaseNew.format(result);
    	if(!baseNew.isSuccess()){
    		return false;
    	}
    	Map<String, String> map = (Map<String, String>) baseNew.getContent();
    	String status = map.get("status");
    	if(status==null||!"0".equals(status)){
    		return false;
    	}
    	return true;
    }
    
}
