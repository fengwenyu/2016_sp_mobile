/**
 * <pre>
 * Copyright:		Copyright(C) 2010-2014, shangpin.com
 * Filename:		com.shangpin.controller.UserController.java
 * Class:			UserController
 * Date:			2014-07-11
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          2.0
 * Description:		
 *
 * </pre>
 **/

package com.aolai.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aolai.web.utils.BizUtil;
import com.aolai.web.utils.Constants;
import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.service.ALBizCartService;
import com.shangpin.biz.service.ALBizOrderService;
import com.shangpin.biz.service.SPBizOrderService;
import com.shangpin.pay.bo.AlipayBo;
import com.shangpin.pay.bo.CommonBackBo;
import com.shangpin.pay.bo.CommonPayBackBo;
import com.shangpin.pay.bo.CommonQueryBackBo;
import com.shangpin.pay.bo.UnionPayBo;
import com.shangpin.pay.exception.ServiceException;
import com.shangpin.pay.service.AlipayService;
import com.shangpin.pay.service.CMBCPayService;
import com.shangpin.pay.service.CommonPayService;
import com.shangpin.pay.service.UnionpayService;
import com.shangpin.pay.service.WeixinpayService;
import com.shangpin.wechat.service.WeChatPublicService;

/**
 * 支付相关的controller
 * 
 * @author cuibinqiang
 * 
 */
@Controller
@RequestMapping("/pay")
public class PaymentController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    /** 支付成功页 */
    public static final String PAY_SUCCESS = "order/pay_success";
    /** 支付失败页 */
    public static final String PAY_FAILURE = "order/pay_failure";
    /**安装银联安全控件页*/
    public static final String INSTALL = "order/install_unionpay";
    private static final String PAY_INDEX="payment/index";
    @Autowired
    private ALBizCartService cartService;
    @Autowired
    private ALBizOrderService aLBizOrderService;// 订单服务
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

    /**
     * 支付方式变更（Ajax）
     * 
     * @param request
     * @param orderId
     *            订单ID
     * @param mainPay
     *            主支付方式
     * @param subPay
     *            子支付方式
     * @return
     * 
     * @author cuibinqiang
     */
    @RequestMapping(value = "/change", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> changePay(HttpServletRequest request, String orderId, String mainPay, String subPay) {
        Map<String, Object> map = new HashMap<String, Object>();
        String userId = getUserId(request);
        if (BizUtil.isNotEmpty(userId, orderId, mainPay, subPay)) {
            // 调主站变更支付方式
            Map<String, Object> response = cartService.changePay(userId, orderId, mainPay, subPay);
            if (response != null) {
                String msgcode = (String) response.get("msgcode");
                String msg = (String) response.get("msg");
                map.put("msgcode", msgcode);
                map.put("msg", msg);
            }
        }
        return map;
    }

    /**
     * 货到付款
     * 
     * @param request
     * @param orderId
     *            订单ID
     * @return
     * 
     * @author cuibinqiang
     */
    @RequestMapping(value = "/cod", method = RequestMethod.GET)
    public String cod(HttpServletRequest request, String orderId, Map<String, Object> map) {
        map.put("orderId", orderId);
        // 跳转到支付成功页
        return PAY_SUCCESS;
    }

    /**
     * 支付宝支付
     * 
     * @param request
     * @param orderId
     *            订单ID
     * @param totalFee
     *            订单金额
     * @return
     * 
     * @author cuibinqiang
     */
    @RequestMapping(value = "/alipay", method = RequestMethod.GET)
    public String aliPay(HttpServletRequest request, HttpServletResponse response, String orderId) {
        AlipayBo alipayBo = new AlipayBo();
        // 动态配置以下三个URL
        String callbackUrl = Constants.ALIPAY_CALLBACK_URL;// 同步回调地址
        String notifyUrl = Constants.ALIPAY_NOTIFY_URL;// 异步回调地址
        String merchantUrl = Constants.ALIPAY_MERCHANT_URL;// 返回商户首页
        logger.debug("******GIVE TO PAY MODEL URL callback*******>>>:" + callbackUrl);
        logger.debug("******GIVE TO PAY MODEL URL notify*******>>>:" + notifyUrl);
        logger.debug("******GIVE TO PAY MODEL URL merchant*******>>>:" + merchantUrl);

        // 获取用户ID
        String userId = getUserId(request);
        // 获取订单详细信息
        OrderItem order = findOrderDetail(userId, orderId);
        if (order == null) {
            logger.debug("OrderDetail is null");
            return null;
        }
        // 订单金额
        String totalFee = order.getOnlineamount();
        logger.debug("******GIVE TO PAY MODEL URL totalFee*******>>>:" + totalFee);

        // 准备参数
        alipayBo.setOrderId(orderId);
        alipayBo.setTotalFee(totalFee);
        alipayBo.setCallbackUrl(callbackUrl);
        alipayBo.setNotifyUrl(notifyUrl);
        alipayBo.setMerchantUrl(merchantUrl);
        try {
            String redirectURL = alipayService.wapPay(alipayBo);
            if (StringUtils.isNotEmpty(redirectURL)) {
                // 跳转到第三方支付宝
                response.sendRedirect(redirectURL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 支付宝同步回调
     * 
     * @param request
     * @param map
     * @return
     * 
     * @author cuibinqiang
     */
    @RequestMapping(value = "/alipay/callback", method = { RequestMethod.GET, RequestMethod.POST })
    public String AlipayCallBack(HttpServletRequest request, Map<String, Object> map) {
        logger.debug("Alipay callback come in ");
        try {
            // 调支付接口校验验签是否成功
            CommonBackBo commonBackBo = alipayService.callBack(request);
            if (commonBackBo != null) {
                String orderId = commonBackBo.getOrderId();
                String totalFee = commonBackBo.getTotalFee();// 为空：初步断定支付宝不会返回此参数
                map.put("orderId", orderId);
                map.put("totalFee", totalFee);
                logger.debug("Callback return param:orderId,money>alipay " + orderId, totalFee);
                boolean verified = commonBackBo.isVerifySign();
                if (verified) {
                    // 支付成功，跳转到支付成功页
                    return PAY_SUCCESS;
                } else {
                    // 跳转到支付失败页
                    String userId = getUserId(request);
                    OrderItem order = findOrderDetail(userId, orderId);
                    String amount = order.getOnlineamount();
                    String cod = order.getIscod();

                    map.put("amount", amount);
                    map.put("cod", cod);
                    return PAY_FAILURE;
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 支付宝异步通知，更新订单状态
     * 
     * @param request
     * @return
     * 
     * @author cuibinqiang
     */
    @RequestMapping(value = "/alipay/notify", method = { RequestMethod.GET, RequestMethod.POST })
    public String AlipayNotify(HttpServletRequest request, HttpServletResponse response) {
        try {
        	logger.debug("*****Alipay notify come in *****************");
            PrintWriter out = response.getWriter();
            // 调支付接口校验验签是否成功
            CommonBackBo commonBackBo = alipayService.notify(request);
            if (commonBackBo == null) {
                return null;
            }
            boolean verified = commonBackBo.isVerifySign();
            if (verified) {// 验证签名通过
                String mainPay = Constants.ALIPAY_MAIN_WAY;
                String subPay = Constants.ALIPAY_SUB_WAY;
                String orderId = commonBackBo.getOrderId();
                //String payMoney = commonBackBo.getTotalFee();
                String payMoney = Constants.PAY_AMOUNT;
                logger.debug("***Notify return verify:verify,orderId,money>alipay " + verified+","+orderId+","+payMoney);
                //取实际订单金额更新订单状态
                if(StringUtils.isEmpty(payMoney)){
                	String userId = getUserId(request);
                    OrderItem order = findOrderDetail(userId, orderId);
                    payMoney = order.getOnlineamount();
                }

                logger.debug("Update params*alipay:orderId,money>" + orderId+","+payMoney);
                // 调主站：更新订单状态
                Map<String, Object> result = cartService.updateStatus(mainPay, subPay, orderId, payMoney);
                String code = (String) result.get("msgcode");
                String msg = (String) result.get("msg");
                if (Constants.SUCCESS.equals(code)) {
                    // 通知支付宝接收成功
                    out.print("success");
                    logger.debug("***Success to update the order status!***Alipay");
                }
                if (Constants.FAILURE.equals(code)) {
                    logger.debug("***Fail to update the order status：***Alipay" + msg);
                }
            } else {
                out.print("fail");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ==========================================银联支付start==========================================

    /**
     * 安装银联安全控件页
     * 
     * @param request
     * @param orderId
     * @return
     *
     * @author cuibinqiang
     */
    @RequestMapping(value = "/install", method = RequestMethod.GET)
    public String install(HttpServletRequest request,String orderId,Map<String, Object> map){
    	String accept=request.getHeader("Accept");
    	map.put("accept", accept);
    	map.put("orderId", orderId);
    	return INSTALL;
    }
    
    /**
     * 银联支付
     * 
     * @param request
     * @param response
     * @param orderId
     *            订单ID
     * @return
     * 
     * @author cuibinqiang
     */
    @RequestMapping(value = "/unionpay", method = RequestMethod.GET)
    public String unionPay(HttpServletRequest request, HttpServletResponse response, String orderId) {
        UnionPayBo unionPayBo = new UnionPayBo();
        String callbackUrl = Constants.UNIONPAY_CALLBACK_URL;// 同步回调URL
        String notifyUrl = Constants.UNIONPAY_NOTIFY_URL;// 异步回调URL
        logger.debug("******GIVE TO PAY MODEL URL callback(unionpay)*******>>>:" + callbackUrl);
        logger.debug("******GIVE TO PAY MODEL URL notify(unionpay)*******>>>:" + notifyUrl);
        // 获取用户ID
        String userId = getUserId(request);
        // 获取订单详细信息
        OrderItem order = findOrderDetail(userId, orderId);
        if (order == null) {
            logger.debug("OrderDetail is null");
            return null;
        }
        try {
            String orderTimeStart = order.getDate();// 交易开始时间
            // 将交易时间处理成银联需要的格式
            String tradeTime = BizUtil.formatDate(orderTimeStart, "yyyyMMddHHmmss");
            String totalFee = order.getOnlineamount();// 交易金额：银联单位是分，没有小数点
            // 将订单金额处理为银联需要的格式
            String payMoney = BizUtil.formatMoney(totalFee);
            // 订单超时时间（非必传，建议传）
            String expireTime = BizUtil.expireTime(orderTimeStart, "yyyyMMddHHmmss");

            // 准备参数
            unionPayBo.setOrderTimeStart(tradeTime);
            unionPayBo.setOrderNumber(orderId);
            unionPayBo.setTotalFee(payMoney);
            unionPayBo.setCallbackUrl(callbackUrl);
            unionPayBo.setNotifyUrl(notifyUrl);
            unionPayBo.setOrderTimeout(expireTime);
            unionPayBo.setOrderDescription("尚品网-轻奢的选择");//订单描述：用于第三方银联支付后显示

            String redirectURL = unionpayService.wapPay(unionPayBo);
            if (StringUtils.isNotEmpty(redirectURL)) {
                // 跳转到第三方银联
                response.sendRedirect(redirectURL);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 银联同步回调
     * 
     * @param request
     * @param map
     * @return
     * 
     * @author cuibinqiang
     */
    @RequestMapping(value = "/unionpay/callback", method = RequestMethod.GET)
    public String UnionPayCallBack(HttpServletRequest request, Map<String, Object> map) {
        try {
        	 logger.debug("***Unionpay callback come in-local***");
            // 调支付接口校验验签是否成功
            CommonBackBo commonBackBo = unionpayService.callBack(request);
            if (commonBackBo != null) {
                String orderId = commonBackBo.getOrderId();
                String totalFee = commonBackBo.getTotalFee();
                map.put("orderId", orderId);
                map.put("totalFee", totalFee);
                boolean verified = commonBackBo.isVerifySign();
                if (verified) {
                    // 支付成功，跳转到支付成功页

                    return PAY_SUCCESS;
                } else {
                    // 跳转到支付失败页
                    String userId = getUserId(request);
                    OrderItem order = findOrderDetail(userId, orderId);
                    String amount = order.getOnlineamount();
                    String cod = order.getIscod();

                    map.put("amount", amount);
                    map.put("cod", cod);
                    return PAY_FAILURE;
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 银联异步通知，更新订单状态
     * 
     * @param request
     * @param response
     * @return
     * 
     * @author cuibinqiang
     */
    @RequestMapping(value = "/unionpay/notify", method = { RequestMethod.GET, RequestMethod.POST })
    public String UnionPayNotify(HttpServletRequest request, HttpServletResponse response) {
        try {
        	logger.debug("***Unionpay nofity come in-local***");
            PrintWriter out = response.getWriter();
            // 调支付接口校验验签是否成功
            CommonBackBo commonBackBo = unionpayService.notify(request);
            if (commonBackBo == null) {
                return null;
            }
            boolean verified = commonBackBo.isVerifySign();
            if (verified) {// 验证签名通过
                String mainPay = Constants.UNIONPAY_MAIN_WAY;
                String subPay = Constants.UNIONPAY_SUB_WAY;
                String orderId = commonBackBo.getOrderId();
                //String payMoney = commonBackBo.getTotalFee();
                String payMoney = Constants.PAY_AMOUNT;
                logger.debug("***Notify return verify:verify,orderId,money>unionpay " + verified+","+orderId+","+payMoney);
                // 取实际订单金额更新订单状态
                if(StringUtils.isEmpty(payMoney)){
                	String userId = getUserId(request);
                    OrderItem order = findOrderDetail(userId, orderId);
                    payMoney = order.getOnlineamount();
                }
                
                logger.debug("***Update params*unionpay:mainPay,subPay,orderId,money>unionpay" + mainPay+","+subPay+","+orderId+","+payMoney);
                // 调主站：更新订单状态
                Map<String, Object> result = cartService.updateStatus(mainPay, subPay, orderId, payMoney);
                String code = (String) result.get("msgcode");
                String msg = (String) result.get("msg");
                if (Constants.SUCCESS.equals(code)) {
                    // 通知银联接收成功
                    out.print("success");
                    logger.debug("***Success to update the order status!***Unionpay");
                }
                if (Constants.FAILURE.equals(code)) {
                    logger.debug("***Fail to update the order status：***Unionpay" + msg);
                }
            } else {
                out.print("fail");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取订单详情
     * 
     * @param userId
     *            用户ID
     * @param orderId
     *            订单ID
     * @return
     * 
     * @author cuibinqiang
     */
    public OrderItem findOrderDetail(String userId, String orderId) {
        if (BizUtil.isNotEmpty(userId, orderId)) {
            OrderItem order = aLBizOrderService.findOrderDetailObj(userId, orderId);
            return order;
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
		logger.debug("alipay start.............");
		logger.debug("orderId:" + orderId);
		String userId;
		if (!StringUtils.isEmpty(quickSubmitFlag) && quickSubmitFlag.equals("0")) {// 如果是一键购物
			userId = (String) request.getSession().getAttribute("quickSubmitUID");
		} else {
			userId = getUserId(request);
		}

		OrderItem orderDetail = bizOrderService.findOrderDetail(userId, orderId);
		AlipayBo alipayBo = new AlipayBo();
		alipayBo.setOrderId(orderId);
		alipayBo.setTotalFee(orderDetail.getOnlineamount());
		alipayBo.setGateWay(gateway);
		alipayBo.setCallbackUrl(getCallBackUrl(Constants.COMMON_CALLBACK_URL,gateway));
		alipayBo.setProductName("订单号:"+orderId);
		try {
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
		logger.debug("unpay start.............");
		logger.debug("orderId:" + orderId);
		String userId;
		if (!StringUtils.isEmpty(quickSubmitFlag) && quickSubmitFlag.equals("0")) {// 如果是一键购物
			userId = (String) request.getSession().getAttribute("quickSubmitUID");
		} else {
			userId = getUserId(request);
		}

		OrderItem orderDetail = bizOrderService.findOrderDetail(userId, orderId);
		UnionPayBo unionPayBo=new UnionPayBo();
		unionPayBo.setOrderNumber(orderId);
		unionPayBo.setTotalFee(orderDetail.getOnlineamount());
		unionPayBo.setCallbackUrl(getCallBackUrl(Constants.COMMON_CALLBACK_URL,gateway,orderId));
		unionPayBo.setGateWay(gateway);
		unionPayBo.setProductName("订单号:"+orderId);
		try {
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
			String userid = getUserId(request);
			String mainPay = null;	String subPay = null;
			if(gateway.startsWith("ALI")){
				CommonPayBackBo commonPayBackBo=commonPayService.payCallBack(request.getParameterMap(), gateway);
				model.addAttribute("totalPrice", commonPayBackBo.getTotalFee());
				model.addAttribute("orderId", commonPayBackBo.getOrderNo());
				model.addAttribute("date", commonPayBackBo.getTradeDate());
				if(commonPayBackBo.getPayStatus().equals(Constants.SUCCESS_MSG)){
					if(gateway.equals("ALIWAP")){
						mainPay=Constants.AL_MAIN_PAY;
						subPay= Constants.AL_SUB_PAY;
					}
					if(gateway.equals("ALIWAPSEA")){
						mainPay=Constants.OVERSEAS_AL_MAIN_PAY;
						subPay= Constants.OVERSEAS_AL_SUB_PAY;
					}
					OrderItem orderDetail = bizOrderService.findOrderDetail(userid, commonPayBackBo.getOrderNo());
					if(orderDetail.getCanpay().equals("true")){
						bizOrderService.updateOrderStatus(mainPay, subPay,commonPayBackBo.getOrderNo(), Constants.PAY_AMOUNT);
					}
					model.addAttribute("date", orderDetail.getDate());
					return PAY_SUCCESS;
				}
			}
			String orderId=request.getParameter("orderId");
			CommonQueryBackBo commonQueryBackBo=commonPayService.queryTradeStatus(orderId);
			OrderItem orderDetail = bizOrderService.findOrderDetail(userid, orderId);
			model.addAttribute("totalPrice", orderDetail.getOnlineamount());
			model.addAttribute("orderId", orderId);
			model.addAttribute("date", orderDetail.getDate());
			if(commonQueryBackBo==null||commonQueryBackBo.getPayStatus()==null){
				return PAY_FAILURE;
			}
			if(commonQueryBackBo.getPayStatus().equals(Constants.SUCCESS_MSG)){
		
				if(gateway.equals("UNWAP")){
					mainPay=Constants.YINLIAN_MAIN_PAY;
					subPay= Constants.YINLIAN_SUB_PAY;
				}
			
				if(orderDetail.getCanpay().equals("true")){
					bizOrderService.updateOrderStatus(mainPay, subPay,orderId, Constants.PAY_AMOUNT);
				}
				model.addAttribute("date", orderDetail.getDate());
				
				return PAY_SUCCESS;
			}
			return PAY_FAILURE;
		} catch (ServiceException e) {
			logger.info("callback e code={}", e.getErrType().getErrorCode());
			e.printStackTrace();
		}
		return null;
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
}
