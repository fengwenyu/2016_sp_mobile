package com.shangpin.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shangpin.pay.bo.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shangpin.biz.bo.Freebie;
import com.shangpin.biz.bo.Order;
import com.shangpin.biz.bo.OrderDetail;
import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.service.SPBizOrderService;
import com.shangpin.biz.service.impl.FreebieService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.pay.exception.ServiceException;
import com.shangpin.pay.service.AlipayService;
import com.shangpin.pay.service.CMBCPayService;
import com.shangpin.pay.service.CommonPayService;
import com.shangpin.pay.service.UnionpayService;
import com.shangpin.pay.service.WeixinpayService;
import com.shangpin.pay.utils.weixin.WeixinpayUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.PropertyUtil;
import com.shangpin.wechat.bo.base.AccessToken;
import com.shangpin.wechat.service.WeChatPublicService;

@Controller
@RequestMapping("/pay")
public class PayController  extends BaseController{
	public static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	private static final String PAY_INDEX="payment/index";
	/** 支付成功页面 */
	private static final String PAY_SUCCESS = "order/payment_success";
	/** 支付失败页面 */
	private static final String PAY_FAIL = "order/payment_fail";
	private static final String PAY_WX ="payment/pub_wx";
	/**电子卡支付成功页*/
	private static final String GIFTCARD_PAY_SUCCESS = "order/giftcard_pay_success";
	private static final String PAY_OVERSEAS_FAIL = "order/payment_overseas_fail";
	@Autowired
	private SPBizOrderService bizOrderService;
	@Autowired
	private AlipayService alipayService;
	@Autowired
	private UnionpayService unionpayService;
	@Autowired
	private WeixinpayService weixinpayService;
	@Autowired
	private CommonPayService commonPayService;
	@Autowired
	private WeChatPublicService weChatPublicService;
	@Autowired
	private CMBCPayService cmbcPayService;	
	@Autowired
	private FreebieService freebieSrv; 
	@RequestMapping(value = "/alipay", method = { RequestMethod.GET, RequestMethod.POST })
	public String alipay(HttpServletRequest request, HttpServletResponse response){
		String userId = request.getParameter("userId");
		String orderId = request.getParameter("orderId");
		String callbackUrl = PropertyUtil.getString("alipay.pad.callback");
		OrderItem orderDetail = bizOrderService.findOrderDetail(userId, orderId);
	
		try {
			if(orderDetail.getPostArea().equals("2")){
				OutsideAlipayBo OutsideAlipayBo = new OutsideAlipayBo(orderId, orderId, orderDetail.getOnlineamount(),
						PropertyUtil.getString("outsideAlipay.pad.merchant"),
						PropertyUtil.getString("outsideAlipay.pad.callback"),
						PropertyUtil.getString("outsideAlipay.pad.notify"));
				String url = alipayService.outsideWapPay(OutsideAlipayBo);
				logger.debug("alipay url:" + url);
				if (!StringUtils.isEmpty(url)) {
					response.sendRedirect(url);
				}
			}else{
				AlipayBo alipayBo = new AlipayBo();
				alipayBo.setOrderId(orderId);
				alipayBo.setTotalFee(orderDetail.getOnlineamount());
				alipayBo.setCallbackUrl(callbackUrl);
				alipayBo.setNotifyUrl(PropertyUtil.getString("alipay.pad.notify"));
				alipayBo.setMerchantUrl(PropertyUtil.getString("alipay.pad.merchant"));
				String url = alipayService.wapPay(alipayBo);
				logger.debug("alipay url:" + url);
				if (!StringUtils.isEmpty(url)) {
					response.sendRedirect(url);
				}
			}
		
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("alipay end.............");
		return null;
	}
	@RequestMapping(value = "/alipay/notify", method = { RequestMethod.GET, RequestMethod.POST })
	public String alipayNotify(HttpServletRequest request, HttpServletResponse response){
		// String userid = getUserId(request);
		logger.debug("alipay notify update order status start");
		try {
			PrintWriter out = response.getWriter();
			CommonBackBo cBo = alipayService.notify(request);
			boolean flag = cBo.isVerifySign();
			logger.debug("alipay verifySign:" + flag);
			if (flag) {
				String orderId = cBo.getOrderId();
				// OrderItem orderDetail =
				// bizOrderService.findOrderDetail(userid, orderId);
				// String payMoney = orderDetail.getOnlineamount();
				// logger.debug("alipay amount:" + payMoney);
				boolean f = bizOrderService.updateOrderStatus(Constants.AL_MAIN_PAY, Constants.AL_SUB_PAY, orderId,
						Constants.PAY_AMOUNT);
				logger.debug("alipay update order status return result:" + f);
				if (f) {
					logger.debug("alipay order update success!");
					out.print("success");
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("alipay notify update order status end");
		return null;
		
	}
	@RequestMapping(value = "/alipay/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String alipayCallBcak(HttpServletRequest request, HttpServletResponse response) {
		try {
			CommonBackBo cBo = alipayService.callBack(request);
			boolean flag = cBo.isVerifySign();
			logger.debug("alipay verifySign:" + flag);
			if (flag) {
				logger.debug("alipay verifySign success");
			} else {
				logger.debug("alipay verifySign fail to fail page");
			}
			//修改为主站提供的支付失败跳转地址
			response.sendRedirect(PropertyUtil.getString("alipay.pad.sp")+"/?OrderNo=" + cBo.getOrderId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("alipay success page end");
		return null;
	}
	
	/**
	 * 海外支付宝回调函数异步调取的url更新订单状态
	 * 
	 * @author liling
	 */
	@RequestMapping(value = "/aliOverseas/notify", method = { RequestMethod.GET, RequestMethod.POST })
	public String aliOverseasNotify(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("=====overseas update notify start!=====");
		try {
			PrintWriter out = response.getWriter();
			CommonBackBo cBo = alipayService.outsideWapNotify(request);
			boolean flag = cBo.getVerifySign();
			if (flag) {
				String orderId = cBo.getOrderId();
				logger.debug("=====overseas update  orderID   !!!" + orderId);
				boolean checkUpdate = bizOrderService.updateOrderStatus(Constants.OVERSEAS_AL_MAIN_PAY,
						Constants.OVERSEAS_AL_SUB_PAY, orderId, Constants.PAY_AMOUNT);
				if (checkUpdate) {
					logger.debug("=====overseas update  success   !!!");
					out.print("success");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/aliOverseas/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String aliOverseasCallBack(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("=====overseas callback start!=====");
		try {
			CommonBackBo cBo = alipayService.outsideWapCallBack(request);
			boolean flag = cBo.isVerifySign();
			logger.debug("=====Verify Sign value  {} =====", flag);
			response.sendRedirect(PropertyUtil.getString("alipay.pad.sp")+"/?OrderNo=" + cBo.getOrderId());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/unionpay", method = { RequestMethod.GET, RequestMethod.POST })
	public String unionpay(HttpServletRequest request, HttpServletResponse response){
		String userId = request.getParameter("userId");
		String orderId = request.getParameter("orderId");
		String resultUrl=request.getParameter("resultUrl");
		
		Map<String, String> orderMap = findOrderDetail(userId, orderId);
		String giftcardamount = orderMap.get("giftcardamount");
		if (StringUtil.isNotEmpty(giftcardamount)) {
			BigDecimal giftcardamounttemp = new BigDecimal(giftcardamount);
			if (giftcardamounttemp.compareTo(new BigDecimal(0)) == 1) {
				orderId = orderId + "gift" + giftcardamount;
				if (orderId.length() > 40) {
					orderId = orderId.substring(0, 40);
				}
			}
		}
		UnionPayBo unionPayBo = new UnionPayBo();
		unionPayBo.setOrderTimeStart(orderMap.get("timeStart"));
		unionPayBo.setOrderTimeout(orderMap.get("timeExpire"));
		unionPayBo.setOrderNumber(orderId);
		unionPayBo.setTotalFee(orderMap.get("totalFee"));
		unionPayBo.setOrderDescription(Constants.YL_PAY_DESCRIPTION);// 订单描述：用于第三方银联支付后显示
		unionPayBo.setCallbackUrl(resultUrl);
		unionPayBo.setNotifyUrl(PropertyUtil.getString("unionpay.pad.notify"));
		try {
			String url = unionpayService.wapPay(unionPayBo);
			logger.debug("yinlianpay to url:" + url);
			if (!StringUtils.isEmpty(url)) {
				response.sendRedirect(url);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("yinlianpay end....................");
		return null;
	}
	@RequestMapping(value = "/unionpay/notify", method = { RequestMethod.GET, RequestMethod.POST })
	public String unionpayNotify(HttpServletRequest request, HttpServletResponse response){
		logger.debug("yinlian pay update order status start...................");
		try {
			PrintWriter out = response.getWriter();
			CommonBackBo cBo = unionpayService.notify(request);
			boolean flag = cBo.isVerifySign();
			logger.debug("yinlian pay verifySign:" + flag);
			if (flag) {
				String orderId = cBo.getOrderId();
				boolean f = bizOrderService.updateOrderStatus(Constants.YINLIAN_MAIN_PAY, Constants.YINLIAN_SUB_PAY,
						orderId, Constants.PAY_AMOUNT);
				logger.debug("order status update return result:" + f);
				if (f) {
					logger.debug("order status update success");
					out.print("success");
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("yinlian pay update order status end...................");
		return null;
		
	}
	/** 
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
	
	/**
	 * 
	 * @Title: pay
	 * @Description: 支付宝支付
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年9月9日
	 */
	@RequestMapping(value = "/alipay/{gateway}", method = RequestMethod.GET)
	public String pay(@PathVariable("gateway")String gateway,String orderId, String quickSubmitFlag, HttpServletRequest request, Model model) {
		try {
			logger.debug("alipay start.............");
			logger.debug("orderId:" + orderId);
			String userId;
			if (!StringUtils.isEmpty(quickSubmitFlag) && quickSubmitFlag.equals("0")) {// 如果是一键购物
				userId = (String) request.getSession().getAttribute("quickSubmitUID");
			} else {
				userId = getUserId(request);
			}
	
			OrderItem orderDetail = bizOrderService.findOrderDetail(userId, orderId);
			if(orderDetail.getCanpay().equals("false")){
	    		model.addAttribute("orderId",orderId);
	    		model.addAttribute("totalPrice", orderDetail.getOnlineamount());
	    		model.addAttribute("orderDetail", orderDetail);
	    		model.addAttribute("payType", Constants.WX_PAY);
	    		
	    		if("2".equals(orderDetail.getPaystatus())){
	    			model.addAttribute("hasFreebie",hasFreebie(userId,orderId));
	    			return PAY_SUCCESS;
	    		}
	    		
	    		String paytypeid = orderDetail.getPaytypeid();
	    		model.addAttribute("statusType","2");
				if("32".equals(paytypeid)||"30".equals(paytypeid)){
//					return PAY_FAIL;
					 return "redirect:/order/list";
				}else{
					model.addAttribute("isPayIn", "1");
//					return PAY_FAIL;
					 return "redirect:/order/list";
				}
	       }
			AlipayBo alipayBo = new AlipayBo();
			if(orderDetail.getPostArea().equals("2")){
				gateway="ALIFZWAP";
			}
			alipayBo.setOrderId(orderId);
			alipayBo.setTotalFee(orderDetail.getOnlineamount());
			alipayBo.setGateWay(gateway);
			alipayBo.setCallbackUrl(getCallBackUrl(PropertyUtil.getString("common.callback.url"),gateway));
			alipayBo.setProductName("订单号:"+orderId);

			if("3".equals(orderDetail.getPayCategory())){
				/**设置每个商品的支付详情**/
				List<PayOrderDetailBo> list = new ArrayList<>();
				for (Order order : orderDetail.getOrder()) {
					for (OrderDetail detail : order.getOrderdetail()) {
						String duty = detail.getDuty();
						String goodFees = detail.getGoodFees();
						String count = detail.getCount();
						String transportFee = detail.getTransportFee();
						String productname = detail.getProductname();
						PayOrderDetailBo bo = new PayOrderDetailBo();
						bo.setCount(count);
						bo.setDuty(duty);
						bo.setGoodFees(goodFees);
						bo.setTransportFee(transportFee);
						bo.setProductname(productname);
						list.add(bo);
					}
				}
				alipayBo.setPayOrderDetailBo(list);
				alipayBo.setInternalAmount(orderDetail.getInternalAmount());
			}

			Map<String,Object> alipayParams = alipayService.wapPayV2(alipayBo);
			//alipayParams.get("gatewayUrl")
			model.addAttribute("gatewayUrl",alipayParams.get("gatewayUrl"));
			model.addAttribute("requestParams",alipayParams.get("requestParams"));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		logger.debug("alipay end.............");
		return PAY_INDEX;
	}
	/**
	 * 
	 * @Title: pay
	 * @Description: 支付宝支付
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年9月9日
	 */
	@RequestMapping(value = "/unpay/{gateway}", method = RequestMethod.GET)
	public String unpay(@PathVariable("gateway")String gateway,String orderId, String quickSubmitFlag, HttpServletRequest request, Model model) {
		try {
			logger.debug("unpay start.............");
			logger.debug("orderId:" + orderId);
			String userId;
			if (!StringUtils.isEmpty(quickSubmitFlag) && quickSubmitFlag.equals("0")) {// 如果是一键购物
				userId = (String) request.getSession().getAttribute("quickSubmitUID");
			} else {
				userId = getUserId(request);
			}
	
			OrderItem orderDetail = bizOrderService.findOrderDetail(userId, orderId);
			if(orderDetail.getCanpay().equals("false")){
	    		model.addAttribute("orderId",orderId);
	    		model.addAttribute("totalPrice", orderDetail.getOnlineamount());
	    		model.addAttribute("orderDetail", orderDetail);
	    		model.addAttribute("payType", Constants.WX_PAY);
	    		if("2".equals(orderDetail.getPaystatus())){
	    			model.addAttribute("hasFreebie",hasFreebie(userId,orderId));
	    			return PAY_SUCCESS;
	    		}
//				return PAY_FAIL;
	       }
			UnionPayBo unionPayBo=new UnionPayBo();
			if(orderDetail.getPostArea().equals("2")){
				gateway="UNWAP";
			}
			unionPayBo.setOrderNumber(orderId);
			unionPayBo.setTotalFee(orderDetail.getOnlineamount());
			unionPayBo.setCallbackUrl(getCallBackUrl(PropertyUtil.getString("common.callback.url"),gateway,orderId));
			unionPayBo.setGateWay(gateway);
			unionPayBo.setProductName("订单号:"+orderId);
	
			Map<String,Object> unParams = unionpayService.wapPayV2(unionPayBo);
			
			model.addAttribute("gatewayUrl", unParams.get("gatewayUrl"));
			model.addAttribute("requestParams",unParams.get("requestParams"));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		logger.debug("alipay end.............");
		return PAY_INDEX;
	}
	

	
	@RequestMapping(value = "/callback/{gateway}", method = RequestMethod.GET)
	public String payCallBack(@PathVariable("gateway")String gateway,Model model, HttpServletRequest request){
		try {
			String userId = getUserId(request);
			String orderId="";
			orderId=request.getParameter("orderId");
			Map<String,Object> map=new HashMap<String,Object>();
			if(gateway.startsWith("ALI")){
				if(request.getParameterMap().size()<=0){
					 return "redirect:/index";
				}
			}
			map=getPayReturn(gateway,userId,orderId,request);
			orderId = (String)map.get("orderId");
			model.addAttribute("orderId", map.get("orderId"));
			model.addAttribute("orderDetail", map.get("orderDetail"));
			model.addAttribute("payType", map.get("payType"));
							model.addAttribute("totalPrice", map.get("totalPrice"));
			String payStatus=(String) map.get("payStatus");
			if(payStatus.equals("fail")){
				
				model.addAttribute("statusType","2");
				if(map.get("postArea").equals("2")){
					if("ALIWAP".equals(gateway)||"UNWAP".equals(gateway)||"WEIXINPUB".equals(gateway)||"WEIXINWAP".equals(gateway)){
						model.addAttribute("isPayIn","1");
					}
//					return PAY_OVERSEAS_FAIL;
				}
				//return PAY_FAIL;
				 return "redirect:/order/list";
			}
			if(payStatus.equals("success")){
				model.addAttribute("hasFreebie",hasFreebie(userId,orderId));
				if((boolean) map.get("useGift")){
					model.addAttribute("pic", map.get("pic"));
					return GIFTCARD_PAY_SUCCESS;
				}
				return PAY_SUCCESS;
			}
			
		} catch (ServiceException e) {
			logger.info("callback e code={}", e.getErrType().getErrorCode());
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * callback回调，判断是否有赠品，有在前端弹出提示 
	 * @param userId 用户id
	 * @param orderId 订单id
	 * @return
	 */
	private String hasFreebie(String userId, String orderId) {

		try{
			Freebie freebie=freebieSrv.getOrderFreeBie(userId, orderId);
			if(freebie!=null){
				return "0";
			}
		}catch (Exception e){
			return "1";
		}
		return "1";
	}
	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/weixin/{gateway}", method = RequestMethod.GET)
	public String weixinPay(@PathVariable("gateway")String gateway,HttpServletRequest request, String quickSubmitFlag, String orderId, Model model) {
		try {
			logger.debug("weixinpay start....................");
			logger.debug("orderId:" + orderId);

			WXPayBo wxPayBo = new WXPayBo();
			String userId;
			if (!StringUtils.isEmpty(quickSubmitFlag) && quickSubmitFlag.equals("0")) {// 如果是一键购物
				// TODO quickSubmitUID 需要提出来
				userId = (String) request.getSession().getAttribute("quickSubmitUID");
			} else {
				userId = getUserId(request);
			}
			// 获取订单详细信息
			OrderItem orderDetail = bizOrderService.findOrderDetail(userId, orderId);
			if(orderDetail.getCanpay().equals("false")){
        		model.addAttribute("orderId",orderId);
        		model.addAttribute("totalPrice", orderDetail.getOnlineamount());
        		model.addAttribute("orderDetail", orderDetail);
        		model.addAttribute("payType", Constants.WX_PAY);
        		if("2".equals(orderDetail.getPaystatus())){
	    			model.addAttribute("hasFreebie",hasFreebie(userId,orderId));
	    			return PAY_SUCCESS;
	    		}
//    			return PAY_FAIL;
        		model.addAttribute("statusType","2");
        		 return "redirect:/order/list";
	       }
			wxPayBo.setOrderId(orderId);
			wxPayBo.setIp(getIp(request));
			wxPayBo.setTotalFee(orderDetail.getOnlineamount());
			wxPayBo.setProductName(getProductName(orderDetail));
			String referer = request.getRequestURL().toString();
			logger.info("weixin pub pay referer==============={}", referer);
			wxPayBo.setReferer(referer);
			Map<String,Object> payParamsMap=new HashMap<String,Object>();
			Map<String,String> requestParams=new HashMap<String,String>();
			String useragent = request.getHeader("User-Agent");
			
          // 用户来自微信
          if (ClientUtil.CheckMircro(useragent)) {

			    //改版后没有postArea
        	  	/*if(orderDetail.getPostArea().equals("2")){
        	  		gateway="WEIXINPUBSEA";
  				}else{
  					gateway="WEIXINPUB";
  				}*/
            	HttpSession session = request.getSession();
            	String openId = (String) session.getAttribute(Constants.WX_ID_NAME);
            	if(StringUtils.isEmpty(openId)){
            		String code = request.getParameter("code");
            		logger.info("weixin pub pay authroized code==============={}", code);
            		AccessToken accessToken = weChatPublicService.getAccessTokenObj(code);// 网页授权获得的token和公共账号获取的token不同
            		logger.info("weixin pub pay access token==============={}", accessToken.toString());
            		openId = accessToken.getOpenid();
            		session.setAttribute(Constants.WX_ID_NAME, openId);
            		request.getSession().setAttribute("weixin_user_openId", openId);
            	}
            	if(gateway.equals("WEIXINPUB")){
            		wxPayBo.setOpenId(openId);
            		logger.info("weixin pub pay openid:{}",openId );
        			wxPayBo.setGateway(gateway);
                	payParamsMap= weixinpayService.pubPay(wxPayBo);
        			requestParams=(Map<String, String>) payParamsMap.get("requestParams");
        			requestParams.put("orderId", orderId);
        			model.addAttribute("requestParams",requestParams);
        			return PAY_WX;
            	}else{
            		wxPayBo.setIp(getIp(request));
					wxPayBo.setExt(getExt(orderDetail));
            		wxPayBo.setGateway(gateway);
            		
                	payParamsMap= weixinpayService.pubSeaPay(wxPayBo);
        			requestParams=(Map<String, String>) payParamsMap.get("requestParams");
        			requestParams.put("orderId", orderId);
        			model.addAttribute("requestParams",requestParams);
        			return PAY_WX;
            	}
            	
          }else{
        	if(orderDetail.getPostArea().equals("2")&&!"27".equals(orderDetail.getPaytypeid())){
//        		return PAY_FAIL;
        		model.addAttribute("statusType","2");
        		 return "redirect:/order/list";
  			}else{
  				gateway="WEIXINWAP";
  			}
			wxPayBo.setGateway(gateway);
	        payParamsMap= weixinpayService.wapPay(wxPayBo);
			requestParams=(Map<String, String>) payParamsMap.get("requestParams");
			model.addAttribute("gatewayUrl", payParamsMap.get("gatewayUrl"));
			model.addAttribute("requestParams",requestParams);
			return PAY_INDEX;
          }
		} catch (ServiceException e) {
			logger.info("weixinpay e code={}", e.getErrType().getErrorCode());
			e.printStackTrace();
		}
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/cmbc/{gateway}", method = RequestMethod.GET)
	public String cmbwapPay(@PathVariable("gateway")String gateway,HttpServletRequest request, String quickSubmitFlag, String orderId, Model model) {
		try {
			logger.debug("weixinpay start....................");
			logger.debug("orderId:" + orderId);

			CommonPayBo commonPayBo = new CommonPayBo();
			String userId;
			if (!StringUtils.isEmpty(quickSubmitFlag) && quickSubmitFlag.equals("0")) {// 如果是一键购物
				// TODO quickSubmitUID 需要提出来
				userId = (String) request.getSession().getAttribute("quickSubmitUID");
			} else {
				userId = getUserId(request);
			}
			// 获取订单详细信息
			OrderItem orderDetail = bizOrderService.findOrderDetail(userId, orderId);
			if(orderDetail.getPostArea().equals("2")){
				 return "redirect:/index";
			}
			commonPayBo.setOrderNo(orderId);
			commonPayBo.setTotalFee(orderDetail.getOnlineamount());
			Map<String,Object> payParamsMap=new HashMap<String,Object>();
			Map<String,String> requestParams=new HashMap<String,String>();
        	if(orderDetail.getCanpay().equals("false")){
        		model.addAttribute("orderId",orderId);
        		model.addAttribute("totalPrice", orderDetail.getOnlineamount());
        		model.addAttribute("orderDetail", orderDetail);
        		model.addAttribute("payType", Constants.CMBC_PAY);
        		CommonQueryBackBo commonQueryBackBo=commonPayService.queryTradeStatusWithGateway(gateway,orderId);
        		if(commonQueryBackBo.getPayStatus().equals(Constants.SUCCESS_MSG)){
        			model.addAttribute("hasFreebie",hasFreebie(userId,orderId));
        			return PAY_SUCCESS;
        		}
//    			return PAY_FAIL;
        		model.addAttribute("statusType","2");
        		 return "redirect:/order/list";
        	}
        	commonPayBo.setGateWay(gateway);
        	commonPayBo.setSubject(getProductName(orderDetail));
	        payParamsMap= cmbcPayService.cmbcWapPay(commonPayBo);
			requestParams=(Map<String, String>) payParamsMap.get("requestParams");
			model.addAttribute("gatewayUrl", payParamsMap.get("gatewayUrl"));
			model.addAttribute("requestParams",requestParams);
			return PAY_INDEX;
		} catch (ServiceException e) {
			logger.info("cmbc wap pay e code={}", e.getErrType().getErrorCode());
			e.printStackTrace();
		}
		return null;
		
	}
	/**
	 * 
	 * @Title: pay
	 * @Description: 支付
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年9月9日
	 */
	@RequestMapping(value = "/wap/{gateway}", method = RequestMethod.GET)
	public String wapPay(@PathVariable("gateway")String gateway,String orderId, String quickSubmitFlag, HttpServletRequest request, Model model) {
		
		logger.debug("{} pay start.............",gateway);
		logger.debug("{} pay orderId:{}" , gateway,orderId);
		try {
			String userId;
			if (!StringUtils.isEmpty(quickSubmitFlag) && quickSubmitFlag.equals("0")) {// 如果是一键购物
				userId = (String) request.getSession().getAttribute("quickSubmitUID");
			} else {
				userId = getUserId(request);
			}
	
			OrderItem orderDetail = bizOrderService.findOrderDetail(userId, orderId);
			if(orderDetail.getPostArea().equals("2")){
				 return "redirect:/index";
			}
			CommonPayBo commonPayBo = new CommonPayBo();
			commonPayBo.setOrderNo(orderId);
			commonPayBo.setTotalFee(orderDetail.getOnlineamount());
	    	if(orderDetail.getCanpay().equals("false")){
	    		model.addAttribute("orderId",orderId);
	    		model.addAttribute("totalPrice", orderDetail.getOnlineamount());
	    		model.addAttribute("orderDetail", orderDetail);
	    		model.addAttribute("payType", Constants.SP_PAY);
	    		if("2".equals(orderDetail.getPaystatus())){
	    			model.addAttribute("hasFreebie",hasFreebie(userId,orderId));
	    			return PAY_SUCCESS;
	    		}
//				return PAY_FAIL;
	    		model.addAttribute("statusType","2");
	    		 return "redirect:/order/list";
	    	}
	    	commonPayBo.setGateWay(gateway);
	    	commonPayBo.setSubject(getProductName(orderDetail));
			Map<String,Object> payParams = commonPayService.wapPay(commonPayBo);
			model.addAttribute("gatewayUrl",payParams.get("gatewayUrl"));
			model.addAttribute("requestParams",payParams.get("requestParams"));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		logger.debug("alipay end.............");
		return PAY_INDEX;
	}
	
	@RequestMapping(value = "/hdpay/success", method = RequestMethod.GET)
	public String hdpay(String orderId, String totalFee, String date, Model model, HttpServletRequest request) {
		String userid = getUserId(request);
		OrderItem orderDetail = bizOrderService.findOrderDetail(userid, orderId);
		model.addAttribute("orderId", orderId);
		model.addAttribute("orderDetail",orderDetail);
		model.addAttribute("totalPrice",orderDetail.getOnlineamount());
		model.addAttribute("payType", Constants.HD_PAY);
		return PAY_SUCCESS;
	}
	
	private String getIp(HttpServletRequest request){
		String ip = WeixinpayUtil.getIpAddr(request);
		if (ip.indexOf(",") > -1) {
			ip = ip.split(",")[0].trim();
		}
		return ip;
	}
	private String getProductName(OrderItem orderDetail){
		List<Order> list = orderDetail.getOrder();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			List<OrderDetail> listDetail = list.get(i).getOrderdetail();
			for (int j = 0; j < listDetail.size(); j++) {
				OrderDetail detail = listDetail.get(j);
				if (orderDetail != null) {
					if (j == 0 && i == 0) {
						sb.append(StringUtil.checkNull(detail.getProductname()));
					} else {
						sb.append("," + StringUtil.checkNull(detail.getProductname()));
					}
				}
			}
		}
		return sb.toString();
	}
	private  String getCallBackUrl(String url,String gateway,String... str){
		StringBuilder sb = new StringBuilder(url);
		sb.append("/");
		sb.append(gateway);
		int length = str.length;
		for (int i = 0; i < length; i++) {
			sb.append("?orderId=");
			sb.append(str[i]);
			sb.append("&argname=");
		}
		return sb.toString();
	}
	private String getExt(OrderItem orderDetail) {
		BigDecimal goodFees = new BigDecimal(0);
		BigDecimal transportFee = new BigDecimal(0);
		BigDecimal duty = new BigDecimal(0);
		List<Order> orderList=orderDetail.getOrder();
		for(int i=0;i<orderList.size();i++){
			List<OrderDetail> orderDetailList=orderList.get(i).getOrderdetail();
			for(int j=0;j<orderDetailList.size();j++){
				OrderDetail detail=orderDetailList.get(j);
				goodFees=goodFees.add(new BigDecimal(detail.getGoodFees()));
				transportFee=transportFee.add(new BigDecimal(detail.getTransportFee()));
				duty=duty.add(new BigDecimal(detail.getDuty()));
			}
		}
		//duty（关税）,goodsFee（产品费用）,transportFee（运费）
		StringBuffer str=new StringBuffer("duty:");
		str.append(duty).append(",").append("goodFees:").append(goodFees).append(",").append("transportFee:").append(transportFee);
		return str.toString();
	}
	
	private Map<String,Object> getPayReturn(String gateway,String userId,String orderId,HttpServletRequest request) throws ServiceException{
		
		Map<String,Object> map =new HashMap<String,Object>();
		String postArea="1";
		if(gateway.startsWith("ALI")){
			map.put("payType", Constants.AL_PAY);
		}else if(gateway.startsWith("WEIXIN")){
			map.put("payType", Constants.WX_PAY);
		}else if(gateway.startsWith("UN")){
			map.put("payType", Constants.YL_PAY);
		}else if(gateway.startsWith("CMBC")){
			map.put("payType", Constants.CMBC_PAY);
		}else if(gateway.startsWith("SPDB")){
			map.put("payType", Constants.SPDB_SUB_PAY);
		}
		CommonPayBackBo commonPayBackBo=new CommonPayBackBo();
		CommonQueryBackBo commonQueryBackBo=new CommonQueryBackBo();
		String flag="0";
		if(gateway.startsWith("ALI")){
			flag="1";
			commonPayBackBo=commonPayService.payCallBack(request.getParameterMap(), gateway);
			orderId=commonPayBackBo.getOrderNo();
		}else{
			commonQueryBackBo=commonPayService.queryTradeStatus(orderId);
		}
		OrderItem orderDetail = bizOrderService.findOrderDetail(userId, orderId);
		postArea=orderDetail.getPostArea();
		List<Order> orderList = orderDetail.getOrder();
		boolean useGift=false;
		if(orderList.size()>0){
			List<OrderDetail> detailList = orderList.get(0).getOrderdetail();
			if(detailList.size()>0){
				String type = detailList.get(0).getType();
				if("1".equals(type)){
					map.put("pic", detailList.get(0).getPic());
					useGift=true;
					
				}
			}
		}
		map.put("orderId", orderId);
		map.put("useGift", useGift);
		map.put("postArea", postArea);
		String payStatus="fail";
		if(flag.equals("1")){
			if(commonPayBackBo!=null&&commonPayBackBo.getPayStatus()!=null&&commonPayBackBo.getPayStatus().equals(Constants.SUCCESS_MSG)){
				payStatus="success";
			}
		}
		if(flag.equals("0")){
			if(commonQueryBackBo!=null&&commonQueryBackBo.getPayStatus()!=null&&commonQueryBackBo.getPayStatus().equals(Constants.SUCCESS_MSG)){
				payStatus="success";
			}
		}
		
		map.put("payStatus", payStatus);
		map.put("orderDetail", orderDetail);
		map.put("totalPrice", orderDetail.getOnlineamount());
		return map;
	}
}
