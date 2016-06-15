package com.shangpin.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.google.gson.reflect.TypeToken;
import com.shangpin.biz.bo.BuyNow;
import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.bo.OrderReturn;
import com.shangpin.biz.bo.OverseasOrderParam;
import com.shangpin.biz.bo.PriceShowVo;
import com.shangpin.biz.bo.Province;
import com.shangpin.biz.bo.Receive;
import com.shangpin.biz.bo.SupplierSkuNoInfo;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizAddressService;
import com.shangpin.biz.service.SPBizBuyNowService;
import com.shangpin.biz.service.SPBizLockSkuCommonService;
import com.shangpin.biz.service.SPBizOrderService;
import com.shangpin.pay.bo.CommonBackBo;
import com.shangpin.pay.bo.OutsideAlipayBo;
import com.shangpin.pay.exception.ServiceException;
import com.shangpin.pay.service.AlipayService;
import com.shangpin.utils.AESUtil;
import com.shangpin.utils.JsonUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.PropertyUtil;
import com.shangpin.web.utils.UserUtil;

/**
 * 海外产品功能控制层
 * 
 * @author zghw
 *
 */
@Controller
public class OverseasController extends BaseController {
	public static final Logger logger = LoggerFactory.getLogger(OverseasController.class);
	/** 海外订单提交页 */
	public static final String ORDER_FILL = "overseas/order_fill";
	/** 订单支付成功页 */
	public static final String PAY_SUCCESS = "overseas/pay_success";
	/** 订单支付失败页 */
	public static final String PAY_FAIL = "overseas/pay_fail";
	/** 无订单错误页 */
	public static final String ORDER_FILL_FIAL = "overseas/order_fill_fail";
	/** 订单支付状态页 */
	public static final String PAY_STATUS = "overseas/pay_status";
	/** 继续支付 */
	public static final String PAY_CONTINUE = "overseas/pay_continue";
	/**520海外购页面*/
	private static final String ABROAD_BUY = "meet/abroadBuy";
	
	private static final String BUY_NOW = "/buy/buy_now";
	@Autowired
	private SPBizBuyNowService buyNowService;
	@Autowired
	private SPBizOrderService orderService;
	@Autowired
	private SPBizOrderService bizOrderService;
	@Autowired
	private AlipayService alipayService;
	@Autowired
	private SPBizAddressService bizAddressService;
	@Autowired
	private SPBizLockSkuCommonService spBizLockSkuCommonService;

	/**
	 * 进入提交订单
	 * 
	 * @param skuId
	 *            商品sku
	 * @param productId
	 *            商品编号
	 * @param activityId
	 *            活动ID
	 * @param amount
	 *            商品数量
	 * @param msg
	 *            提交失败转向过来带来的信息 可为空
	 * @author zghw
	 */
	@RequestMapping("/overseas/order/fill")
	public String fillOrder(String skuId, String productId, String activityId, String amount, String msg,
			HttpServletRequest request, ModelMap model) {
		String userId = getUserId(request);
		BuyNow fillData = buyNowService.buyNowObj(userId, skuId, productId, activityId, amount,
				Constants.REGION_OVERSEAS);

		if (fillData == null) {
			model.addAttribute("msg", "无对应的商品购物结算信息！");
			return ORDER_FILL_FIAL;
		}
		// 解码
		decryptCardID(fillData);
		// 得到默认收货地址
		Receive defAddress = getDefAddress(fillData);
		// 得到省份列表
		List<Province> provinces = bizAddressService.findProvinceListObj();
		String productAmount = fillData.getProductAmount();
		String isHaveDirect = fillData.getIsHaveDirect();
		boolean isDir = "1".equals(isHaveDirect);//是直发商品
		boolean isProAmount = productAmount!=null && Double.parseDouble(productAmount) >= Double.parseDouble(com.shangpin.biz.utils.Constants.PAY_OVERSEA_PRICE_LINE);
		 if (defAddress==null) {
             model.addAttribute("haveAddress", false);
         } else {
             model.addAttribute("haveAddress", true);
             model.addAttribute("address",defAddress);
         }
		 model.addAttribute("provinces", provinces);
		 model.addAttribute("skuId", skuId);
		 model.addAttribute("productId", productId);
		 model.addAttribute("activityId", activityId);
		 model.addAttribute("amount", amount);
		 model.addAttribute("fillData", fillData);
		 model.addAttribute("defAddress", defAddress);
		 model.addAttribute("msg", msg);
		//判断跳转海外支付还是国内支付
		if(isDir||!isProAmount){
			model.addAttribute("isPayOut","1");
		}
		return ORDER_FILL;
		/*else{
			List<PriceShowVo> priceShowVos = fillData.getPriceShow();
	        for(PriceShowVo priceShowVo : priceShowVos){
				if("5".equals(priceShowVo.getType())){
					model.addAttribute("ori_carriage", priceShowVo);
				}else if("6".equals(priceShowVo.getType())){
					model.addAttribute("ori_nocarriage", priceShowVo);
				}
			}
	        double realPay = Double.parseDouble(fillData.getPayAmount());
	        model.addAttribute("provinces", provinces);
	        model.addAttribute("buyNow", fillData);
	        model.addAttribute("real_pay", realPay);
	        return BUY_NOW;
		}*/
	}

	/**
	 * 提交订单
	 * 
	 * @param skuId商品sku
	 * @param activityId活动号
	 * @param addressId收货地址id
	 * @param invoiceAddressId发票地址id
	 * @param invoiceType发票类型
	 * @param invoiceTitle发票抬头
	 * @param invoiceContent发票内容
	 * @param express配送发送
	 * @param buysIds购物车商品id
	 * @param productId商品编号
	 * @param amount商品个数
	 * @param invoiceFlag是否开发票
	 * @param payId主支付方式
	 * @param payChildId次支付方式
	 * @author zghw
	 */
	@RequestMapping(value = "/overseas/order/submit", method = RequestMethod.POST)
	public String submitOrder(String skuId, String productId, String activityId, String amount, String addressId,
			String invoiceAddressId, String invoiceFlag, String invoiceType, String invoiceTitle,
			String invoiceContent, String express, String buysIds, String payId, String payChildId,
			String couponflag, String coupon, String orderSource,
			HttpServletRequest request, HttpServletResponse response, ModelMap model,
			RedirectAttributesModelMap redirect) {
		String userId = getUserId(request);
		// 转向提交订单页面的参数
		Map<String, String> fillMapParam = new HashMap<String, String>();
		fillMapParam.put("skuId", skuId);
		fillMapParam.put("productId", productId);
		fillMapParam.put("activityId", activityId);
		fillMapParam.put("amount", amount);
		// 验证参数
		boolean hasError = validateParam(skuId, productId, amount, addressId, invoiceFlag, express, buysIds, payId,
				payChildId);
		if (hasError) {
			fillMapParam.put("msg", "订单提交参数不合法!");
			redirect.addAllAttributes(fillMapParam);
			return "redirect:/overseas/order/fill";
		}
		// 组装提交订单参数
		OverseasOrderParam overseasOrderParam = new OverseasOrderParam(userId, skuId, addressId, invoiceAddressId,
				invoiceFlag, invoiceType, invoiceTitle, invoiceContent, express, buysIds, payId, payChildId, couponflag, coupon, orderSource);
		Map<String, String> cookieMap = UserUtil.getThridCookie(request);
		// 提交订单
		ResultObjOne<OrderReturn> result = orderService.beSubmitCommonOrder(cookieMap,overseasOrderParam);
		// 根据提交订单情况转向相应的界面
		if (result != null && result.isSuccess()) {
			OrderReturn orderReturn = result.getObj();
			String orderId = orderReturn.getOrderid();
			//String postArea = orderReturn.getPostarea();//国超说接口返回数据没给。改为拿本地的，如下
			String postArea = overseasOrderParam.getPostArea();
			//
			if(!StringUtils.isEmpty(postArea)&&postArea.equals("2")){//海外
				String supplierskuno=orderReturn.getSupplierskuno();//获取供应商的id
				Map<String,String> map=new HashMap<String,String>();
				  if(!StringUtils.isEmpty(supplierskuno)){
		            	List<SupplierSkuNoInfo> list = JsonUtil.fromJson(supplierskuno, new TypeToken<List<SupplierSkuNoInfo>>() {
			            }.getType());
		            	map=spBizLockSkuCommonService.orderLockSku(orderReturn.getOrderno(), userId, orderId,list);
					
		            }else{
		            	map=spBizLockSkuCommonService.orderLockSku(orderReturn.getOrderno(), userId, orderId);
		            }
					if(!map.get("code").equals("0")){
						model.addAttribute("msg", map.get("msg"));
						return ORDER_FILL_FIAL;
					}
				
			}
		
			
			return overseasPayPayment(orderId, payId, payChildId);
		} else {
			if (result == null) {
				fillMapParam.put("msg", "订单提交失败!");
			} else {
				fillMapParam.put("msg", result.getMsg());
			}
			redirect.addAllAttributes(fillMapParam);
			return "redirect:/overseas/order/fill";
		}
	}

	/**
	 * 去继续支付页面
	 * 
	 * @return
	 * @author zghw
	 */
	@RequestMapping(value = "/overseas/pay/continue")
	public String overseasPayContinue(String orderId, HttpServletRequest request, ModelMap model) {
		String userId = getUserId(request);
		OrderItem orderItem = bizOrderService.findOrderDetail(userId, orderId);
		model.put("orderItem", orderItem);
		String paytypeid = orderItem.getPaytypeid();
		if("20".equals(paytypeid)||"27".equals(paytypeid)||"19".equals(paytypeid)){
			model.put("isPayIn", "1");
		}
		return PAY_CONTINUE;
	}

	/**
	 * 付款方式
	 * 
	 * @return
	 * @author zghw
	 */
	@RequestMapping(value = "/overseas/pay/payment")
	public String overseasPayPayment(String orderId, String payId, String payChildId) {
		
		if (Constants.OVERSEAS_AL_MAIN_PAY.equals(payId) && Constants.OVERSEAS_AL_SUB_PAY.equals(payChildId)) {
			// 转向海外支付宝
			return "redirect:/pay/alipay/ALIFZWAP?orderId=" + orderId;
		} else if (Constants.WEIXIN_PUB_SEA_MAIN_PAY.equals(payId) && Constants.WEIXIN_PUB_SEA_SUB_PAY.equals(payChildId)) {
			// 微信
			return "redirect:/pay/weixin/WEIXINPUBSEA?orderId=" + orderId;
		} else if (Constants.WEIXINWAP_MAIN_PAY.equals(payId) && Constants.WEIXINWAP_SUB_PAY.equals(payChildId)) {
			// 微信
			return "redirect:/pay/weixin/WEIXINWAP?orderId=" + orderId;
		}else if (Constants.CMBCWAP_MAIN_PAY.equals(payId) && Constants.CMBCWAP_SUB_PAY.equals(payChildId)) {
			// 微信
			return "redirect:/pay/cmbc/CMBWAP?orderId=" + orderId;
		}else if("20".equals(payId) && "37".equals(payChildId)){
			//支付宝国内wap
			return "redirect:/pay/alipay/ALIWAP?orderId=" + orderId;
		}else if("19".equals(payId) && "49".equals(payChildId)){
			//银联国内wap
			return "redirect:/pay/unpay/UNWAP?orderId=" + orderId;
		}else if("27".equals(payId) && "58".equals(payChildId)){
			//微信公众号支付
			return "redirect:/pay/weixin/WEIXINPUB?orderId=" + orderId;
		}else {
			// 去继续支付页面
			return "redirect:/overseas/pay/continue?orderId=" + orderId;
		}
	}
	@RequestMapping(value = "/overseas/orderLockSku", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> orderLockSku(String orderId, HttpServletRequest request) {
		Map<String,String> map=new HashMap<String,String>();
		String userId = getUserId(request);
		OrderItem orderDetail = bizOrderService.findOrderDetail(userId, orderId);
	 	if(orderDetail.getCanpay().equals("false")){
	 		map.put("code", "-3");
	 		map.put("code", "订单不是待支付状态");
	 	}
	 	map=spBizLockSkuCommonService.orderLockSku(orderDetail.getOrder().get(0).getOrderno(), userId, orderId);
		return map;
		
	}
	/**
	 * 调起海外支付进行支付
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "/overseas/pay", method = { RequestMethod.GET, RequestMethod.POST })
	public String overseasPay(String orderId, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("=====overseas pay start!=====");
		String userId = getUserId(request);
		OrderItem orderDetail = bizOrderService.findOrderDetail(userId, orderId);
		if (orderDetail != null) {
			OutsideAlipayBo alipayBo = new OutsideAlipayBo(orderId, orderId, orderDetail.getOnlineamount(),
					PropertyUtil.getString("outsideAlipay.merchant.url"),
					PropertyUtil.getString("outsideAlipay.callback.url"),
					PropertyUtil.getString("outsideAlipay.notify.url"));
			try {
				String url = alipayService.outsideWapPay(alipayBo);
				if (!StringUtils.isEmpty(url)) {
					logger.debug("==========redirect pay url : " + url);
					response.sendRedirect(url);
				}
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 海外支付完成后返回的页面
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "/overseas/pay/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String overseasPayCallback(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("=====overseas callback start!=====");

		try {
			CommonBackBo cBo = alipayService.outsideWapCallBack(request);
			boolean flag = cBo.isVerifySign();
			logger.debug("=====Verify Sign value  {} =====", flag);
			if (flag) {
				String orderId = cBo.getOrderId();
				return "redirect:/overseas/pay/success?orderId=" + orderId;
			}
			if (StringUtils.isBlank(cBo.getOrderId())) {
				return "redirect:/index";
			}
			return "redirect:/overseas/pay/fail?orderId=" + cBo.getOrderId();
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return "redirect:/index";
	}

	/**
	 * 海外支付宝回调函数异步调取的url更新订单状态
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "/overseas/pay/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String overseasPayUpdate(HttpServletRequest request, HttpServletResponse response) {
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

	/**
	 * 支付订单失败
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "/overseas/pay/fail", method = RequestMethod.GET)
	public String payFail(String orderId, HttpServletRequest request, ModelMap model) {
		String userId = getUserId(request);
		OrderItem orderItem = bizOrderService.findOrderDetail(userId, orderId);
		model.put("orderItem", orderItem);
		String paytypeid = orderItem.getPaytypeid();
		if("20".equals(paytypeid)||"27".equals(paytypeid)||"19".equals(paytypeid)){
			model.put("isPayIn", "1");
		}
//		return PAY_FAIL;
		model.addAttribute("statusType","2");
		 return "redirect:/order/list";
	}

	/**
	 * 支付订单成功
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "/overseas/pay/success", method = RequestMethod.GET)
	public String paySuccess(String orderId, HttpServletRequest request, ModelMap model) {
		String userId = getUserId(request);
		OrderItem orderItem = bizOrderService.findOrderDetail(userId, orderId);
		model.put("orderItem", orderItem);
		return PAY_SUCCESS;
	}

	/**
	 * 验证提交订单参数是否有错误 如果为true参数有误
	 * 
	 * @author zghw
	 */
	private boolean validateParam(String... params) {
		for (String param : params) {
			if (StringUtils.isEmpty(param)) {
				return true;
			}
		}
		return false;
	}

	// 设置第一显示的收货地址
	private Receive getDefAddress(BuyNow fillData) {
		if (fillData != null) {
			String lastAddressId = fillData.getLastReceiveId();
			List<Receive> addressList = fillData.getReceive();
			if (StringUtils.isNotBlank(lastAddressId)) {
				// 如果有上次的使用的收货地址则使用
				for (Receive address : addressList) {
					if (lastAddressId.equals(address.getId())) {
						return address;
					}
				}
			}
			// 则使用第一个
			if (addressList != null && addressList.size() > 0) {
				return addressList.get(0);
			}
		}
		return null;
	}

	/**
	 * 身份证号码解码
	 * 
	 * @param fillData
	 * @author zghw
	 */
	private void decryptCardID(BuyNow fillData) {
		if (fillData == null) {
			return;
		}
		if (fillData.getReceive() == null) {
			return;
		}
		try {
			for (Receive address : fillData.getReceive()) {
				String cardID = address.getCardID();
				if (!StringUtils.isEmpty(cardID)) { // 身份证号解密
					String originalCardID = AESUtil.decrypt(cardID, AESUtil.IDCARD_KEY);
					address.setCardID(originalCardID);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/overseas/abroadBuy")
	public String abroadBuy() {
		return ABROAD_BUY;
	}
}
