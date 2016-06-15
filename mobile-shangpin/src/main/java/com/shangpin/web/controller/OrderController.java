package com.shangpin.web.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shangpin.web.utils.ActivifyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.Address;
import com.shangpin.biz.bo.GiftCardReturn;
import com.shangpin.biz.bo.MainOrder;
import com.shangpin.biz.bo.Order;
import com.shangpin.biz.bo.OrderDetail;
import com.shangpin.biz.bo.OrderDetailResult;
import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.bo.OrderListResult;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.ProductDetail;
import com.shangpin.biz.bo.Province;
import com.shangpin.biz.bo.QuickOrderParam;
import com.shangpin.biz.bo.QuickResult;
import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.Receive;
import com.shangpin.biz.bo.ReceiveRequest;
import com.shangpin.biz.bo.SubmitOrder;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBiz520SellService;
import com.shangpin.biz.service.SPBizAddPayLogService;
import com.shangpin.biz.service.SPBizAddressService;
import com.shangpin.biz.service.SPBizLogisticeService;
import com.shangpin.biz.service.SPBizOrderService;
import com.shangpin.biz.service.SPBizReceiveService;
import com.shangpin.biz.service.SPBizSendInfoService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.biz.service.impl.FreebieService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.biz.utils.FreeBie520Util;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.pay.service.AlipayService;
import com.shangpin.pay.service.SPDBPayService;
import com.shangpin.pay.service.UnionpayService;
import com.shangpin.pay.service.WeixinpayService;
import com.shangpin.utils.AESUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.CookieUtil;
import com.shangpin.web.utils.UserUtil;

/**
 * @ClassName: OrderController
 * @Description:订单管理相关方法控制层
 * @author zhongchao
 * @date 2014年10月30日
 * @version 1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	public static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	/** 跳转到订单列表页面 */
//	private static final String ORDER_LIST = "order/order_all";
//	private static final String ORDER_LIST = "order/list";
	private static final String ORDER_LIST = "order/order_list";
	
	/** 跳转到订单详情页面 */
	private static final String ORDER_DETAIL = "order/detail";
	/** 跳转到海外订单详情页面 */
	//private static final String ORDER_OVERSEAS_DETAIL = "order/overseas_detail";
	/** 立即购买订单页 */
	private static final String ORDER_NOW = "order/payment_now";
	/** 支付成功页面 */
	private static final String PAY_SUCCESS = "order/payment_success";
	/** 礼品卡支付找回密码 */
	private static final String GIFT_CARD_FORGET_PASSWORD = "order/forget_giftcard";
	/** 订单管理支付页面 礼品卡支付页面 */
	private static final String ORDER_PAY = "order/payment_normal";
	/** 银联安装控件页面 */
	private static final String INSTALL_UNIONPAY = "order/install_unionpay";
	/**客服虚拟系统代客下的礼品卡订单详情页*/
	private static final String GIFT_CARD_DETAIL = "order/giftcard_detail";
	/**客服虚拟系统代客下的礼品卡订单待支付除外的其他订单状态跳转的页面*/
	private static final String GIFT_CARD_INFO = "order/giftcard_info";
	
	/**2016-04-29买赠分享页面*/
    private static final String FREEBIE_SHARE = "order/freebie_share";
    
    /**2016-04-29买赠分享页面*/
    private static final String FREEBIE= "order/freebie";
	
	@Autowired
	private SPBizOrderService bizOrderService;
	@Autowired
	private SPBizUserService userService;
	@Autowired
	private SPBizAddressService bizAddressService;
	@Autowired
	private SPBizSendInfoService bizSendInfoService;
	@Autowired
	private SPBizLogisticeService logisticeService;
	@Autowired
	private AlipayService alipayService;
	@Autowired
	private UnionpayService unionpayService;
	@Autowired
	private WeixinpayService weixinpayService;
	@Autowired
	private SPDBPayService spdbPayService;
	@Autowired
	private SPBizAddPayLogService addPayLogService;
	@Autowired
	private SPBiz520SellService freebieService;
	
	@Autowired
	private SPBizOrderService spBizOrderService;
	@Autowired
	private SPBizReceiveService sPBizReceiveService;
	@Autowired
	private SPBiz520SellService sPBiz520SellService;
	
	@RequestMapping(value = "/update/type", method = RequestMethod.GET)
	@ResponseBody
	public ResultBase updatePayType(String mainOrderNo, String payType, HttpServletRequest request){
		String userid = getUserId(request);
		ResultBase resultBase = bizOrderService.updatePayType(userid, mainOrderNo, payType);
		return resultBase;
	}

	/**
	 * @Title: order_list
	 * @Description: 跳转到订单列表页面
	 * @param statusType
	 *            1 全部订单 2 待支付 3 待收货
	 * @Create By zhongchao
	 * @Create Date 2014年10月30日
	 */
/*	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String toOrderList(@RequestParam("statusType") String statusType, HttpServletRequest request,
			Map<String, Object> map) {
		User user = getSessionUser(request);

		if (StringUtils.isEmpty(statusType)) {
			statusType = "2";
		}
		// 调用主站接口,获取订单OrderResult
		OrderResult result = bizOrderService.getOrderlist(user.getUserid(), statusType);

		map.put("statusType", statusType);
		
		if (StringUtils.isEmpty(result)||result.getOrderItem()==null ||result.getOrderItem().size()<=0) {
			map.put("orderlist", null);
			return ORDER_LIST;
		}
		

		List<OrderItem> orderItemList =result.getOrderItem();
		if (orderItemList==null) {
			map.put("orderlist", null);
			return ORDER_LIST;
		}
		for(OrderItem mainOrderItem : orderItemList) {

			if(judgeCanConfirm(mainOrderItem)) {
				mainOrderItem.setCanconfirmgoods("1");
			}else {
				mainOrderItem.setCanconfirmgoods("0");
			}
		}

		map.put("currentTimes", System.currentTimeMillis());
		map.put("statusType", statusType);
		map.put("pageIndex", result.getPageIndex());
		map.put("orderlist", result.getOrderItem());
		map.put("haveMore", result.getHaveMore());
		return ORDER_LIST;
	}
	*/
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String toOrderList(@RequestParam("statusType") String statusType,
			HttpServletRequest request,Map<String, Object> map) {
		User user = getSessionUser(request);

		if (StringUtils.isEmpty(statusType)) {
			statusType = "2";
		}
		map.put("statusType", statusType);
		
		OrderListResult result = bizOrderService.orderlist(user.getUserid(), statusType);
		if (result == null ) {
			map.put("mainOrderlist", null);
			return ORDER_LIST;
		}		

		List<MainOrder> mainOrderList =result.getList();
		if (mainOrderList==null) {
			map.put("mainOrderlist", null);
			return ORDER_LIST;
		}

		map.put("currentTimes", System.currentTimeMillis());
		map.put("statusType", statusType);
		map.put("pageIndex", result.getPageIndex());
		map.put("result", result);
		return ORDER_LIST;
	}
	
	@SuppressWarnings("unused")
    private boolean judgeCanConfirm(OrderItem mainOrderItem) {
		
		List<Order> subOrderList = mainOrderItem.getOrder();
		for(Order subOrder : subOrderList) {
			String status = subOrder.getStatus();
			if (!("2".equals(mainOrderItem.getPaytypeid()) ) && "16".equals(status)) {
				continue;
			} else {
				return  false;   
			}
		}
		
		return true;
	}

	/**
	 * 
	 * @Title: getMore
	 * @Description: style搭配列表获取更多
	 * @param
	 * @return Map<String, Object>
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月17日
	 */
	@ResponseBody
	@RequestMapping(value = "/getMore", method = RequestMethod.POST)
	public Map<String, Object> getMore(@RequestParam("statusType") String statusType,
			@RequestParam("pageIndex") String pageIndex, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		/*User user = getSessionUser(request);
		if (null == user || StringUtils.isEmpty(user.getName())) {
			map.put("code", "2");
			return map;
		}
		map = bizOrderService.getMoreOrderlist(user.getUserid(), statusType,
				String.valueOf(Integer.valueOf(pageIndex) + 1));
		if(map!=null){
			map.put("statusType", statusType);
		}
		
		return map;*/
		
		User user = getSessionUser(request);
		if (StringUtils.isEmpty(statusType)) {
			statusType = "2";
		}
		map.put("statusType", statusType);
		pageIndex=String.valueOf(Integer.valueOf(pageIndex)+1);
		OrderListResult result = bizOrderService.orderlist(user.getUserid(), statusType,pageIndex);
		if (result == null ) {
			return map;
		}		
		List<MainOrder> mainOrderList =result.getList();
		if (mainOrderList==null) {
			return map;
		}
		map.put("code", "0");
		map.put("statusType", statusType);
		map.put("pageIndex", result.getPageIndex());
		map.put("result", result);
		return map;
	}

	/**
	 * @Title: order_detail
	 * @Description: 跳转到订单详情页面
	 * @param orderid
	 *            订单号
	 * @Create By zhongchao
	 * @Create Date 2014年10月31日
	 *//*
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String toOrderDetail(@RequestParam(value = "isConfirm") String isConfirm,@RequestParam(value = "mainOrderNo") String mainOrderNo,
			@RequestParam(value = "statusType") String statusType, HttpServletRequest request,
			Map<String, Object> map) {
		User user = getSessionUser(request);
		map.put("statusType", statusType);
		//是否能确认收货
		map.put("isConfirm", isConfirm);
		// 调用主站接口获取订单详细信息
		OrderItem orderItem = bizOrderService.findOrderDetail(user.getUserid(), mainOrderNo);
		if (StringUtils.isEmpty(orderItem)) {
			// 判断从主站获取的详细订单信息为空返回订单列表页
			return ORDER_LIST;
		}
		// 调用主站接口获取物流
		Track track = logisticeService.getNewLogistice(mainOrderNo, user.getUserid());
		map.put("orderItem", orderItem);
		map.put("order", orderItem.getOrder().get(0));
		map.put("track", track);
		if (orderItem != null && Constants.REGION_OVERSEAS.equals(orderItem.getPostArea())) {
			String cardID = orderItem.getOrder().get(0).getCardID();
			if (!StringUtils.isEmpty(cardID)) { // 身份证号解密
				try {
					cardID = AESUtil.decrypt(cardID, AESUtil.IDCARD_KEY);
					// 显示身份证号部分信息
					cardID = cardID.substring(0, 6) + "****" + cardID.substring(cardID.length() - 4);
					map.put("cardID", cardID);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return ORDER_OVERSEAS_DETAIL;
		}
		return ORDER_DETAIL;
	}*/
	/**
	 * @Title: order_detail
	 * @Description: 跳转到订单详情页面
	 * @param orderid
	 *            订单号
	 * @Create By liling
	 * @Create Date 2015年9月17日
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String toOrderDetail( String mainOrderId,String orderId,String isSplitOrder, Model model,HttpServletRequest request) {
		User user = getSessionUser(request);
		String userId=user.getUserid();
		OrderDetailResult orderDetail=spBizOrderService.findOrderDetail(userId, mainOrderId, orderId, isSplitOrder);
		model.addAttribute("orderDetail", orderDetail);
		model.addAttribute("mainOrderId", mainOrderId);
		model.addAttribute("orderId", orderId);
		model.addAttribute("isSplitOrder", isSplitOrder);
		model.addAttribute("currentTimes", System.currentTimeMillis());
		//修改orderDetail 解密身份证信息
		if(orderDetail != null){			
			
			Receive receive = orderDetail.getReceive();
			if(receive != null){
				String cardId = receive.getCardID();					
				receive.setCardID(decrypt(cardId));
			}
			List<Receive> receiveList = orderDetail.getReceiveList();
			
			for(Receive rec : receiveList){
				
				if(rec != null){
					String cardId = rec.getCardID();
					rec.setCardID(decrypt(cardId));
				}
			}			
		}	
		
		return ORDER_DETAIL;
	}
	
	/**
	 * 客服虚拟系统客服代客下礼品卡够买的订单，订单中可以包含多张礼品卡
	 * @param userId
	 * @param mainOrderId
	 * @param orderId
	 * @param isSplitOrder
	 * @param model
	 * @param request
	 * @return
	 * @author qinyingchun
	 */
	@RequestMapping(value = "/giftcard/detail", method = RequestMethod.GET)
	public String giftCareDetail(String userId, String mainOrderId,String orderId,String isSplitOrder, Model model,HttpServletRequest request){
		OrderDetailResult orderResult=spBizOrderService.findOrderDetail(userId, mainOrderId, orderId, isSplitOrder);
		if(null == orderResult){
			return GIFT_CARD_DETAIL;
		}
		String status = orderResult.getStatus();
		if(!Constants.ORDER_STATUS_PAY_WAIT.equals(status)){
			model.addAttribute("status", status);
			return GIFT_CARD_INFO;
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.SESSION_USER);
	    if (!StringUtils.isEmpty(user)) {
	         session.removeAttribute(Constants.SESSION_USER);
	         session.removeAttribute(Constants.SESSION_USERID);
	    }
		User giftcardUser = userService.findUserByUserId(userId);
		request.getSession().setAttribute(Constants.SESSION_USERID, userId);
		request.getSession().setAttribute(Constants.SESSION_USER, giftcardUser);
		List<Province> provinces = bizAddressService.findProvinceListObj();
		model.addAttribute("provinces", provinces);
		model.addAttribute("orderResult", orderResult);
		return GIFT_CARD_DETAIL;
	}
	/**
	 * 修改订单信息
	 * @param orderId
	 * @param addrId
	 * @param express
	 * @param invoiceAddrId
	 * @param invoiceFlag
	 * @param invoiceType
	 * @param invoiceTitle
	 * @param invoiceContent
	 * @param isModifyInvoice
	 * @param paytypeid
	 * @param paytypechildid
	 * @param request
	 * @return
	 * @author qinyingchun
	 */
	@RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultBase modifyOrder(String orderId, String addrId, String express, String invoiceAddrId, String invoiceFlag, String invoiceType, String invoiceTitle, String invoiceContent, String isModifyInvoice, HttpServletRequest request){
		String userId = (String)request.getSession().getAttribute(Constants.SESSION_USERID);
		ResultBase resultBase = spBizOrderService.modifyOrder(userId, orderId, addrId, express, invoiceAddrId, invoiceFlag, invoiceType, invoiceTitle, invoiceContent, isModifyInvoice);
		return resultBase;
	}
	
	/**
	 * 解密身份证信息
	 * @param cardId
	 * @return
	 */
	private String decrypt(String cardId) {
	    
	    try {
			return AESUtil.decrypt(cardId, AESUtil.IDCARD_KEY);
		} catch (Exception e) {
			logger.error("订单详情解密身份证["+cardId+"]异常", e);
		}
	    return null;
	}

	/**
	 * 确认收货
	 * @param order
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/finish", method = RequestMethod.GET)
	public Map<String, Object> finishOrder(@RequestParam(value = "mainOrderNo") String mainOrderNo,
			HttpServletRequest request) {
		User user = getSessionUser(request);
		String userId=user.getUserid();
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == user || StringUtils.isEmpty(user.getName())) {
			map.put("code", "2");
			return map;
		}
		map = bizOrderService.finishedOrder(userId, mainOrderNo);
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public Map<String, Object> cancelOrder(@RequestParam(value = "mainOrderNo") String mainOrderNo,String postArea,
			HttpServletRequest request) {
		User user = getSessionUser(request);
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == user || StringUtils.isEmpty(user.getName())) {
			map.put("code", "2");
			return map;
		}
		map = bizOrderService.cancelOrder(user.getUserid(), mainOrderNo,postArea);
		return map;
	}

	/**
	 * 
	 * @Title: orderPay
	 * @Description:订单管理页面点击支付跳转到支付页面
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月4日
	 */
	@RequestMapping(value = "/pay/normal", method = RequestMethod.GET)
	public String orderPay(String totalamount, String payamount, String onlineamount, String paycard,
			String giftcardbalance, String orderId, String cod, String isusablegiftcardpay, String paytypeid,
			String paytypechildid, String date, Model model, HttpServletRequest request) {
		User user = getSessionUser(request);
		String userId = getUserId(request);
		OrderItem orderItem = bizOrderService.findOrderDetail(userId, orderId);
		model.addAttribute("phoneNum", user.getMobile());
		model.addAttribute("totalamount", orderItem.getTotalamount());// 订单总金额
		model.addAttribute("payamount", orderItem.getPayamount());// 还需要支付的金额
		model.addAttribute("onlineamount", orderItem.getOnlineamount());// 线上支付金额（还需要支付的金额）
		model.addAttribute("paycard", orderItem.getGiftcardamount());// 礼品卡支付金额
		model.addAttribute("giftcardbalance", orderItem.getGiftbardbalance());// giftbardbalance
		model.addAttribute("orderId", orderId);
		model.addAttribute("cod", orderItem.getIscod());
		model.addAttribute("isusegiftcard", orderItem.getIsusegiftcard());
		   // 用户来自微信
		String useragent = request.getHeader("User-Agent");
        if (ClientUtil.CheckMircro(useragent)) {
        	String cookieChannel = null;
    		if (CookieUtil.getCookieByName(request, Constants.CHANNEL_PARAM_NAME) != null) {
    			cookieChannel = CookieUtil.getCookieByName(request, Constants.CHANNEL_PARAM_NAME).getValue();
    		}
    		if(cookieChannel.equals("102")){
    			model.addAttribute("paytypeid", Constants.SPDB_MAIN_PAY);
        		model.addAttribute("paytypechildid",  Constants.SPDB_SUB_PAY);
    		}else{
    			model.addAttribute("paytypeid", Constants.WEIXIN_MAIN_PAY);
        		model.addAttribute("paytypechildid",  Constants.WEIXIN_SUB_PAY);
    		}
        	
        }else{
        	if(orderItem.getPaytypeid().equals(Constants.WEIXIN_MAIN_PAY)&&orderItem.getPaytypechildid().equals( Constants.WEIXIN_SUB_PAY)){
        		model.addAttribute("paytypeid", Constants.AL_MAIN_PAY);
        		model.addAttribute("paytypechildid",  Constants.AL_SUB_PAY);
        	}else{
        		model.addAttribute("paytypeid", orderItem.getPaytypeid());
        		model.addAttribute("paytypechildid", orderItem.getPaytypechildid());
        	}
        	
        }
	
		model.addAttribute("date", orderItem.getDate());
		return ORDER_PAY;
	}

	/**
	 * 
	 * @Title: buyNow
	 * @Description:立即购买，调转到立即购买提交订单页
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月20日
	 */
	@RequestMapping(value = "/now", method = RequestMethod.GET)
	public String buyNow(String sku, String productNo, String amount, Model model, HttpServletRequest request) {
		Cookie cookie = CookieUtil.getCookieByName(request, "quickPayUser");
		if (cookie != null) {
			String value;
			try {
				value = URLDecoder.decode(cookie.getValue(), Constants.DEFAULT_ENCODE);
				model.addAttribute("params", this.strToObj(value));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		List<Province> provinces = bizAddressService.findProvinceListObj();
		model.addAttribute("provinces", provinces);
		model.addAttribute("sku", sku);
		model.addAttribute("productNo", productNo);
		model.addAttribute("amount", amount);
		return ORDER_NOW;
	}

	/**
	 * 
	 * @Title: submit
	 * @Description: 立即购买提交订单
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月20日
	 */
	@SuppressWarnings("unused")
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submit(QuickOrderParam params, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String tel = params.getTel();
		Cookie cookie = CookieUtil.getCookieByName(request, params.getTel());
		if (cookie == null) {
			StringBuffer sb = this.objToStr(params);
			CookieUtil.addCookie(response, "quickPayUser", sb.toString(), Constants.COOKIE_TIME);
		}
		QuickUser user = userService.checkUser(tel, Constants.CREATE_NEW_USER);
		String userId = user.getUserId();
		User user2 = userService.findUserByUserId(userId);
		request.getSession().setAttribute("quickSubmitUID", userId);
		request.getSession().setAttribute(Constants.SESSION_USER, user2);
		params.setUserId(userId);
		params.setShopType(Constants.SKU_SP);
		String phone = params.getTel();
		// 13621 10 1904
		String lastNum = phone.substring(7);
		String pwd = phone.substring(5);
		String isNewUser = user.getIsNewUser();
		String msgTemplate = "";
		ResultObjOne<QuickResult> obj = bizOrderService.quickOrder(params);
		if (Constants.FAILE.equals(obj.getCode())) {
			map.put("code", Constants.FAILE);
			map.put("msg", obj.getMsg());
		} else {
			map.put("code", Constants.SUCCESS);
			map.put("orderId", obj.getObj().getOrderId());
			if (Constants.IS_NEW_USER.equals(isNewUser)) {
				msgTemplate = "太棒啦！你抢到了1元女神新装，使用你的手机号，登录密码：手机号后六位。点击链接t.cn/RvweXV1 下载尚品网APP查看订单";
			} else {
				msgTemplate = "太棒啦！你抢到了1元女神新装，使用你的手机号打开尚品网APP查看订单，若忘记密码，打开APP登录页面，点击‘手机快捷登录’。点击链接立刻查看t.cn/RvweXV1";
			}
			boolean flag = bizSendInfoService.sendInfo(userId, phone, msgTemplate);
			logger.debug("send info=========={}", flag);
		}
		return map;
	}

	/**
	 * 
	 * @Title: submitOrder
	 * @Description:常规购物提交订单
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月3日
	 */
	@RequestMapping(value = "/common/submit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submitOrder(SubmitOrder order, HttpServletRequest request) {
		String userid = getUserId(request);
		Map<String, String> cookieMap = UserUtil.getThridCookie(request);
    	Map<String, Object> map  = bizOrderService.submitCommonOrderMap(cookieMap,userid, order.getAddrid(),
    				order.getInvoiceaddrid(), order.getInvoiceflag(), order.getInvoicecontent(), order.getInvoicetype(),
    				order.getInvoicetitle(), order.getCouponflag(), order.getCoupon(), order.getExpress(),
    				order.getOrderfrom(), order.getBuysIds(), order.getPaytypeid(), order.getPaytypechildid(),
    				order.getOrdertype(), order.getIsUseGiftCardPay(), order.getOrderSource(), order.getPostArea());
		
		return map;
	}

	/**
	 * 
	 * @Title: giftcard
	 * @Description:礼品卡支付页
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月3日
	 */
	@RequestMapping(value = "/gifgcard", method = RequestMethod.GET)
	public String giftcard(String payTypeId, String payTypeChildId, String date, String amount, String paycard,
			String orderId, String cod, String realpay, String giftcardbalance, Model model, HttpServletRequest request) {
		User user = getSessionUser(request);
		String userId = getUserId(request);
		String mobile = user.getMobile();
		OrderItem orderItem = bizOrderService.findOrderDetail(userId, orderId);
		model.addAttribute("orderId", orderId);
		model.addAttribute("cod", orderItem.getIscod());
		// model.addAttribute("realpay", realpay);
		model.addAttribute("giftcardbalance", orderItem.getGiftbardbalance());
		// model.addAttribute("paycard", paycard);
		// model.addAttribute("amount", amount);
		model.addAttribute("phoneNum", mobile);
		model.addAttribute("date", orderItem.getDate());
		model.addAttribute("isusegiftcard", orderItem.getIsusegiftcard());
		model.addAttribute("onlineamount", orderItem.getOnlineamount());
		model.addAttribute("paytypeid", orderItem.getPaytypeid());
		model.addAttribute("paytypechildid", orderItem.getPaytypechildid());
		return ORDER_PAY;
	}

	/**
	 * 
	 * @Title: cardPay
	 * @Description:礼品卡支付
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月3日
	 */
	@RequestMapping(value = "/card/pay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cardPay(String orderId, String password, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = getUserId(request);
		GiftCardReturn giftCardReturn = bizOrderService.payGiftCards(userId, Constants.SITE_NO_SP, "", orderId,
				password);
		if (!StringUtils.isEmpty(giftCardReturn)) {
			map.put("code", Constants.SUCCESS);
			map.put("returnInfo", giftCardReturn);
		} else {
			map.put("code", Constants.FAILE);
		}
		return map;
	}

	/**
	 * 
	 * @Title: cardSuccess
	 * @Description:礼品卡全额支付成功跳转页面
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月5日
	 */
	@RequestMapping(value = "/card/success", method = RequestMethod.GET)
	public String cardSuccess(String orderId, String date, String payamount, Model model, HttpServletRequest request) {
		String userid = getUserId(request);
		OrderItem orderDetail = bizOrderService.findOrderDetail(userid, orderId);
		model.addAttribute("totalPrice", orderDetail.getGiftcardamount());
		model.addAttribute("payType", Constants.LP_PAY);
		model.addAttribute("orderDetail",orderDetail);
		model.addAttribute("orderId",orderId);
		return PAY_SUCCESS;
	}

	/**
	 * 
	 * @Title: cardSuccess
	 * @Description:折扣码、优惠券全额支付成功跳转页面
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月5日
	 */
	@RequestMapping(value = "/coupon/success", method = RequestMethod.GET)
	public String yhSuccess(String orderId, String date, String payamount, Model model, HttpServletRequest request) {
		String userid = getUserId(request);
		OrderItem orderDetail = bizOrderService.findOrderDetail(userid, orderId);
		model.addAttribute("orderId", orderId);
		model.addAttribute("date", orderDetail.getDate());
		model.addAttribute("totalPrice", orderDetail.getDiscountamount());
		model.addAttribute("payType", Constants.YH_PAY);
		return PAY_SUCCESS;
	}

	/**
	 * 
	 * @Title: forgetCardPassword
	 * @Description:礼品卡找回密码页
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月4日
	 */
	@RequestMapping(value = "/card/password", method = RequestMethod.GET)
	public String forgetCardPassword(String mobile, Model model, HttpServletRequest request) {
		String userid = getUserId(request);
		model.addAttribute("mobile", mobile);
		String msgTempl = "您的验证码是：{$verifyCode$}，请及时输入验证!";
		bizSendInfoService.sendInfo(userid, mobile, msgTempl);
		return GIFT_CARD_FORGET_PASSWORD;
	}

	/**
	 * 
	 * @Title: sendPhoneCode
	 * @Description: 发送手机验证码
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月4日
	 */
	@RequestMapping(value = "/send/code", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendPhoneCode(String mobi, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userid = getUserId(request);
		String msgTempl = "您的验证码是：{$verifyCode$}，请及时输入验证!";
		boolean flag = bizSendInfoService.sendInfo(userid, mobi, msgTempl);
		if (flag) {
			map.put("code", Constants.SUCCESS);
		} else {
			map.put("code", Constants.FAILE);
			map.put("msg", "短信下发失败！");
		}
		return map;
	}

	/**
	 * 
	 * @Title: verifycode
	 * @Description: 验证手机发送的验证码
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月4日
	 */
	@RequestMapping(value = "/code/verify", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> verifycode(String mobi, String verifycode, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userid = getUserId(request);
		ResultBase result = bizSendInfoService.verifyPhoneCode(userid, mobi, verifycode);
		String code = result.getCode();
		if (code.equals(Constants.SUCCESS)) {
			map.put("code", Constants.SUCCESS);
		} else {
			map.put("code", Constants.FAILE);
			map.put("msg", result.getMsg());
		}
		return map;
	}

	@RequestMapping(value = "/set/password", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setPassword(String password, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userid = getUserId(request);
		ResultBase result = bizOrderService.beSetGiftCardPassword(userid, password);
		String code = result.getCode();
		if (code.equals(Constants.SUCCESS)) {
			map.put("code", Constants.SUCCESS);
		} else {
			map.put("code", Constants.FAILE);
			map.put("msg", result.getMsg());
		}
		return map;
	}

	
	/**
	 * 
	 * @Title: install
	 * @Description:跳转银联控件安装界面
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月26日
	 */
	@RequestMapping(value = "/install", method = RequestMethod.GET)
	public String install(String orderId, String quickSubmitFlag, Model model, HttpServletRequest request) {
		if (!StringUtils.isEmpty(quickSubmitFlag) && quickSubmitFlag.equals("0")) {// 如果是一键购物
			model.addAttribute("quickSubmitFlag", "0");
		}

		String accept = request.getHeader("Accept");
		model.addAttribute("accept", accept);
		model.addAttribute("orderId", orderId);

		return INSTALL_UNIONPAY;
	}

	

	private StringBuffer objToStr(QuickOrderParam params) {
		StringBuffer sb = new StringBuffer();
		String name = params.getConsigneeName();
		String provinceId = params.getProvince();
		String provinceName = params.getProvinceName();
		String cityId = params.getCity();
		String cityName = params.getCityName();
		String areaId = params.getArea();
		String areaName = params.getAreaName();
		String townId = params.getTown();
		String townName = params.getTownName();
		String addr = params.getAddress();
		String tel = params.getTel();
		String postcode = params.getZip();
		String payTypeId = params.getPayTypeId();
		String payTypeChildId = params.getPayTypeChildId();
		sb.append(name);
		sb.append(":" + provinceId);
		sb.append(":" + provinceName);
		sb.append(":" + cityId);
		sb.append(":" + cityName);
		sb.append(":" + areaId);
		sb.append(":" + areaName);
		sb.append(":" + townId);
		sb.append(":" + townName);
		sb.append(":" + addr);
		sb.append(":" + tel);
		sb.append(":" + postcode);
		sb.append(":" + payTypeId);
		sb.append(":" + payTypeChildId);
		return sb;
	}

	private QuickOrderParam strToObj(String sb) {
		QuickOrderParam param = new QuickOrderParam();
		String[] strs = sb.split(":");
		param.setConsigneeName(strs[0]);
		param.setProvince(strs[1]);
		param.setProvinceName(strs[2]);
		param.setCity(strs[3]);
		param.setCityName(strs[4]);
		param.setArea(strs[5]);
		param.setAreaName(strs[6]);
		param.setTown(strs[7]);
		param.setTownName(strs[8]);
		param.setAddress(strs[9]);
		param.setTel(strs[10]);
		param.setZip(strs[11]);
		param.setPayTypeId(strs[12]);
		param.setPayTypeChildId(strs[13]);
		return param;
	}

	/**
	 * 
	 * @Title: findOrderDetail
	 * @Description:获取订单详情
	 * @param
	 * @return String
	 * @throws
	 * @Create By lilig
	 * @Create Date 2014年1月12日
	 */
	public Map<String, String> findOrderDetail(String userId, String orderId) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtil.isNotEmpty(userId, orderId)) {

			try {
				OrderItem order = bizOrderService.findOrderDetail(userId, orderId);
				String totalFee = order.getOnlineamount();
				String giftcardamount = order.getGiftcardamount();
				if (StringUtil.isNotEmpty(giftcardamount)) {
					giftcardamount = giftcardamount.split("[.]")[0];
				}

				BigDecimal totalFee2 = new BigDecimal(totalFee);
				BigDecimal totalFee4 = new BigDecimal(100);
				totalFee = String.valueOf(totalFee2.multiply(totalFee4).toString().split("[.]")[0]);

				SimpleDateFormat formatDate1 = new SimpleDateFormat("yyyyMMddHHmmss");

				SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// 订单时间
				Date orderDate = formatDate.parse(order.getDate());
				long endtime = orderDate.getTime() + 60 * 1000 * 60;
				// 订单超时时间
				String timeExpire = formatDate1.format(new Date(endtime));
				String timeStart = formatDate1.format(new Date(orderDate.getTime()));

				map.put("timeExpire", timeExpire);
				map.put("timeStart", timeStart);
				map.put("totalFee", totalFee);
				map.put("giftcardamount", giftcardamount);
				List<Order> list = order.getOrder();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < list.size(); i++) {
					List<OrderDetail> listDetail = list.get(i).getOrderdetail();

					for (int j = 0; j < listDetail.size(); j++) {
						OrderDetail orderDetail = listDetail.get(j);
						if (orderDetail != null) {
							if (j == 0 && i == 0) {
								sb.append(StringUtil.checkNull(orderDetail.getProductname()));
							} else {
								sb.append("," + StringUtil.checkNull(orderDetail.getProductname()));
							}
						}
					}
				}

				if (sb.toString().length() > 40) {
					map.put("productName", sb.toString().substring(0, 40) + "...");
				} else {
					map.put("productName", sb.toString());
				}

				return map;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

	/***
	 *#2016-05-20 买赠分享列表页面
	 * @param order
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/freebie/share")
    public String freebieShare(HttpServletRequest request, Model model){
		if(ActivifyUtil.isfreeActivify()){
			String userId = UserUtil.getUserId(request);
			if (StringUtils.isEmpty(userId)) {
				return null;
			}
			List<Product>  oderFreeBieList=freebieService.initFreebieList(userId);
			model.addAttribute("orderList", oderFreeBieList);
			model.addAttribute("shareTitle",com.shangpin.biz.utils.Constants.FREEBIE_520_SHARE_M_TITLE);
			model.addAttribute("shareDesc",com.shangpin.biz.utils.Constants.FREEBIE_520_SHARE_DESC);
			model.addAttribute("sharePic",com.shangpin.biz.utils.Constants.FREEBIE_520_SHARE_PIC);
			return FREEBIE_SHARE;
		}else{
			return "redirect:/index";
		}
    }
	/***
	 * #2016-05-20买赠分享页面
	 * @param sku
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/freebie")
    public String freebie(@RequestParam String p, String sharePlat,Model model,HttpServletRequest request){

			//解密参数获得页面去向;
			Map<String,String> parameter= FreeBie520Util.decodeFreebieParam(p);
			if (parameter==null) {
				return null;
			}
			model.addAttribute("p", p);
			if ((!StringUtils.isEmpty(sharePlat)) && ("2".equals(sharePlat))) {
				return "redirect:/product/freebieDetail";
			}
			String productNo = parameter.get("spu");
			ProductDetail productDetail = sPBiz520SellService.getProductDetail(productNo);
			model.addAttribute("productDetail", productDetail);
			//老虎机游戏
			return "activity/slot/index";
    }
    /**
     * 520买赠获取领取
     * 
     * @param request
     * @return
     * @author wh
     */
    @RequestMapping(value = "/receive")
    @ResponseBody
    public Map<String, Object> receive(ReceiveRequest receiveRequest, @RequestParam String p, HttpServletRequest request) {

    	Map<String, Object> map = new HashMap<String, Object>();
		if(!ActivifyUtil.isfreeActivify()){
			  map.put("code", "4");
              map.put("msg", "活动已结束!");
              return map;
		}
        User user = getSessionUser(request);
        if (user != null && !StringUtils.isEmpty(user.getUserid())) {
            // 解密参数获得页面去向;
            Map<String, String> parameter = FreeBie520Util.decodeFreebieParam(p);
            if (parameter == null) {
                map.put("code", "1");
                map.put("msg", "参数有误!");
                return map;
            }
            receiveRequest.setFspuSend(parameter.get("spu"));
            receiveRequest.setFuserId(parameter.get("userId"));
            receiveRequest.setForderId(parameter.get("orderId"));
            receiveRequest.setFspuId(parameter.get("sku"));
            receiveRequest.setFspuNo(parameter.get("sortNo"));
            receiveRequest.setPhone(user.getMobile());
            receiveRequest.setLuserId(user.getUserid());
            try {
				map = sPBizReceiveService.pickFreebie(receiveRequest);
			} catch (Exception e) {
				logger.error("调用SPBizReceiveService.pickFreebie方法发生异常.......");
				e.printStackTrace();
				throw new RuntimeException("调用SPBizReceiveService.pickFreebie方法发生异常....... ", e);
			}
        }
        return map;
    }
}
