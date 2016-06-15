package com.shangpin.web.controller;

import com.shangpin.biz.bo.*;
import com.shangpin.biz.bo.cart.Cart;
import com.shangpin.biz.bo.orderUnion.*;
import com.shangpin.biz.bo.orderUnion.Coupon;
import com.shangpin.biz.domain.settlement.BuyNowDo;
import com.shangpin.biz.domain.settlement.RefreshDo;
import com.shangpin.biz.service.SPBizAddressService;
import com.shangpin.biz.service.SPBizCartService;
import com.shangpin.biz.service.SPBizSettlementService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.AESUtil;
import com.shangpin.utils.StringUtil;
import com.shangpin.web.enums.PaymentEnum;
import com.shangpin.web.utils.IDCard;
import com.shangpin.web.utils.UserUtil;
import com.shangpin.web.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:520结算controller
 * @author liushaoqing
 * @date 2016年04月23日
 * @version 1.0
 */
@Controller
@RequestMapping("settlement")
public class SettlementController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(SettlementController.class);

    private static final String PAY = "settlement/cart_pay";

    private static final String BUYNOW = "settlement/buy_now";

    private static final String LIST = "settlement/new_list";

    @Autowired
    private SPBizCartService bizCartService;
    @Autowired
    private SPBizAddressService bizAddressService;
    @Autowired
    private SPBizSettlementService bizSettlementService;

    @RequestMapping(value = "/topay", method = RequestMethod.GET)
    public String toPay(String shopCartDetailIds, Model model, HttpServletRequest request) {

        String userId = getUserId(request);
        //购物车提交
        Cart cart = bizCartService.doShowCartV2(userId, shopCartDetailIds);
        if (cart==null) {
            model.addAttribute("msg", "");
            return "redirect:/shopcart/list";
        } else {

            //获取结算结果,第三个参数，是否默认使用优惠券 0是不使用
            SettlementUnion cartUnion = bizSettlementService.cartSettlement(userId, shopCartDetailIds, "1");
            //接口返回错误跳转购物车列表
            if (cartUnion == null) {
                return "redirect:/shopcart/list";
            }else{

                /*// 检测用户s是否来自微信
                String useragent = request.getHeader("User-Agent");
                if (ClientUtil.CheckMircro(useragent)) {

                }*/
                List<com.shangpin.web.vo.Payment> payments = getPayments(cartUnion, request);
                model.addAttribute("cartUnion", cartUnion);
                List<Province> provinces = bizAddressService.findProvinceListObj();
                //获取可送达接口地址 // 个人中心0 购物车为1 立即购买为2
                List<Receive> addresses = bizSettlementService.getAccessAddress(userId,shopCartDetailIds,"","1","0");
                setCardId(addresses);
                model.addAttribute("addresses",addresses);
                model.addAttribute("provinces", provinces);
                model.addAttribute("payments",payments);
                model.addAttribute("orderSource","1"); //1 购物车 2，立即购买
                model.addAttribute("cardId",getCardId(cartUnion));
                return PAY;
            }
        }
    }

    /**
     * 用于选择地址，优惠券，礼品卡等，结算页面数据动态刷新
     * @param shopCartDetailIds
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/fresh", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> toPayFresh(
            String buyId,// 购物车id String必须 多个用竖线分割
            String orderSource,//	1：购物车，2：立即购买	String	必须
            String receivedId,// 收货地址ID	String	可选
            String isUseGiftCard,//	用户礼品卡使用金额	String	可选
            String domesticCouponflag,
            String domesticCoupon,
            String abroadCouponflag,
            String abroadCoupon,
            HttpServletRequest request) {

        String userId = getUserId(request);
        RefreshDo refreshDo = new RefreshDo();
        refreshDo.setBuyId(buyId);
        refreshDo.setUserId(userId);
        refreshDo.setOrderSource(orderSource);
        refreshDo.setReceivedId(receivedId);
        if("1".equals(isUseGiftCard)){
            refreshDo.setIsUseGiftCard("1");
        }else{
            refreshDo.setIsUseGiftCard("0");
        }
        refreshDo.setDomesticCoupon(domesticCoupon);
        refreshDo.setDomesticCouponflag(domesticCouponflag);
        refreshDo.setAbroadCoupon(abroadCoupon);
        refreshDo.setAbroadCouponflag(abroadCouponflag);
        refreshDo.setType("0");//兼容接口实现类
        //获取结算结果
        Map<String,Object> map = bizSettlementService.refreshSettlement(refreshDo);
        if(!map.containsKey("cartUnion")){
            return map;
        }else{
            SettlementUnion cartUnion = (SettlementUnion) map.get("cartUnion");
            List<com.shangpin.web.vo.Payment> payments = getPayments(cartUnion, request);
            map.put("payments",payments);
            return map;
        }
    }

    /**
     * 用于结算页面订单提交
     */
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> toPaySubmit(OrderSubmitParam orderSubmitParam, Model model, HttpServletRequest request) throws Exception {

        orderSubmitParam.setUserid(getUserId(request));
        boolean validate = orderSubmitParam.validate();
        //调用主站订单结算
        if(validate){
            Map<String, String> cookieMap = UserUtil.getThridCookie(request);
            //增加推广链接的参数
            if(cookieMap!=null){
                orderSubmitParam.setChannelNo(cookieMap.get(Constants.THRID_COOKIE_SOURCE));
                orderSubmitParam.setChannelId(cookieMap.get(Constants.THRID_COOKIE_CAMPAIGN));
                orderSubmitParam.setParam(cookieMap.get(Constants.THRID_COOKIE_PARAM));
                orderSubmitParam.setChannelType(cookieMap.get(Constants.THRID_COOKIE_CHANNEL_TYPE));
            }else{
                orderSubmitParam.setChannelNo("");
                orderSubmitParam.setChannelId("");
                orderSubmitParam.setParam("");
                orderSubmitParam.setChannelType("0");
            }
            Map<String,Object> orderResult = bizSettlementService.submitOrder(orderSubmitParam);
            return orderResult;
        }else{
            Map<String,Object> obj = new HashMap<>();
            obj.put("code","error");
            obj.put("msg","参数错误！");
            return obj;
        }
    }

    /**
     * 用于立即购买结算页面
     * @param productId 产品id
     * @param skuId
     * @param amount 数量
     * @param region 1：国内商品 2: 海外商品
     * @param activityId 活动id
     */
    @RequestMapping(value = "/now",  method = {RequestMethod.GET, RequestMethod.POST})
    public String toPayFresh(Model model,@RequestParam String productId,@RequestParam String skuId,
                             @RequestParam String amount, @RequestParam String activityId,HttpServletRequest request) {

        String userId = getUserId(request);
        BuyNowDo buyNowDo = new BuyNowDo();
        buyNowDo.setUserId(userId);
        buyNowDo.setSkuId(skuId);
        buyNowDo.setActivityId(activityId);
        buyNowDo.setAmount(amount);
        buyNowDo.setIsDefaultUseCoupon("1");//默认使用优惠券
        buyNowDo.setProductId(productId);

        //增加商品客的参数chanelNo chanelId
        HttpSession session = request.getSession();
        String channelNo = (String) session.getAttribute(com.shangpin.web.utils.Constants.THRID_TOKEN_ChannelNo);
        String channelId = (String) session.getAttribute(com.shangpin.web.utils.Constants.THRID_TOKEN_ChannelId);
        String spuNo = (String) session.getAttribute(com.shangpin.web.utils.Constants.THRID_TOKEN_SpuNo);
        String topicId = (String) session.getAttribute(com.shangpin.web.utils.Constants.THRID_TOKEN_TopicId);
        boolean isTopic = false;
        boolean isSpu = false;
        //校验活动id
        if(com.shangpin.biz.utils.StringUtil.isNotEmpty(topicId,channelNo,channelId)){
            if(topicId.equals(activityId)){
                isTopic = true;
            }
        }
        //验证商品是否是那件spu商品
        if(com.shangpin.biz.utils.StringUtil.isNotEmpty(spuNo,channelNo,channelId)){
            logger.info("productId:"+productId+"===>spuNo"+spuNo);
            if(spuNo.equals(productId)){
                isSpu = true;
            }
        }
        if(isTopic||isSpu){
            buyNowDo.setChannelId(channelId);
            buyNowDo.setChannelNo(channelNo);
        }

        SettlementUnion cartUnion = bizSettlementService.buyNowSettlement(buyNowDo);
        if(cartUnion==null){
            return "redirect:/shopcart/list";
        }
        List<com.shangpin.web.vo.Payment> payments = getPayments(cartUnion, request);
        model.addAttribute("cartUnion", cartUnion);
        List<Province> provinces = bizAddressService.findProvinceListObj();
        //获取可送达接口地址 // 个人中心0 购物车为1 立即购买为2
        List<Receive> addresses = bizSettlementService.getAccessAddress(userId,cartUnion.getBuyId(),"","2","0");
        //解密身份证号
        setCardId(addresses);
        model.addAttribute("addresses",addresses);
        model.addAttribute("provinces", provinces);
        model.addAttribute("payments",payments);
        model.addAttribute("orderSource","2"); //1 购物车 2，立即购买
        model.addAttribute("cardId",getCardId(cartUnion));
        return PAY;
    }

    /**
     * 提交订单页点击可选优惠券列表
     * @param buyId 购买ID
     * @param orderSource 订单来源：1表示购物车，2表示立即购买
     * @param pageIndex 页码
     * @param pageSize 大小
     * @param couponNo 本次已选中的优惠券编号，可选
     * @param useCouponNo 国内海外一起结算时,已被选中的优惠券,在此次请求时不能再选
     * @param request
     * @return
     */
    @RequestMapping(value = "/canUseCoupons", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public CouponsWraper canUseCoupons(String buyId, String orderSource, String pageIndex, String pageSize, String couponNo, String useCouponNo, HttpServletRequest request){
        String userId = getUserId(request);
        //获取可用的优惠券 ordersource 1，购物车2，立即购买 useCoupon 已使用的coupon号
        CouponsWraper couponsWraper = bizSettlementService.accessCoupons(userId, pageIndex, pageSize, buyId, orderSource, couponNo, useCouponNo);
        return couponsWraper;
    }

    /**
     *
     * @Title: orderAddress
     * @Description: 原来方式不能获取是否可用所以先添加再获取
     * @param
     * @return String
     * @throws
     * @Create By 刘少卿
     * @Create
     */
    @RequestMapping(value = "/address/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addAddress(Address address,String buyId,String orderSource, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = getSessionUser(request);
        List<Address> addresses = bizAddressService.addAddr(address, user.getUserid());
        if (null != addresses) {
            List<Receive> addresses_result = bizSettlementService.getAccessAddress(user.getUserid(),buyId,"",orderSource,"0");
            //解密身份证号
            setCardId(addresses_result);
            map.put("code", Constants.SUCCESS);
            map.put("addresses", addresses_result);
        } else {
            map.put("code", Constants.FAILE);
        }
        return map;
    }


    /**
     * 获取可用支付方式
     * @param cartUnion
     * @param request
     * @return
     */
    private List<com.shangpin.web.vo.Payment> getPayments(SettlementUnion cartUnion,HttpServletRequest request){

        //支持线下支付方式 (货到付款) 1是 2否
        String isCod = cartUnion.getIsCod();
        //线上支付方式: 1:国内，2：国外，3：支付宝分帐
        String payCategory = cartUnion.getPayCategory();
        // 检测用户s是否来自微信
        String useragent = request.getHeader("User-Agent");

        boolean iscod = isCod.equals("1");
        List<com.shangpin.web.vo.Payment> payments = null;
        if (ClientUtil.CheckMircro(useragent)) {
            payments = PaymentEnum.getPayment(payCategory, PaymentEnum.MWX_CHANNEL, iscod);
        }else{
            payments = PaymentEnum.getPayment(payCategory, PaymentEnum.M_CHANNEL, iscod);
        }
        return payments;
    }

    /**
     * 获取解密后的身份证号码
     * @param cartUnion
     * @return
     */
    private String getCardId(SettlementUnion cartUnion){

        String result = "";
        if(cartUnion.getReceived()!=null && cartUnion.getReceived().getLastReceived()!=null){
            if(cartUnion.getReceived().getLastReceived().getCardID()!=null){
                result = cartUnion.getReceived().getLastReceived().getCardID();
                if(!"".equals(result)){
                    try {
                        result = AESUtil.decrypt(result, AESUtil.IDCARD_KEY);
                        if(IDCard.IDCardValidate(result)){
                            result = result.substring(0,6)+"***"+result.substring(result.length()-4,result.length());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    /**
     * 设置解密身份证号码
     * @param addresses
     */
    private void setCardId(List<Receive> addresses){

        if(addresses!=null){
            for (Receive address : addresses) {
                if(address.getCardID()!=null && !"".equals(address.getCardID())){
                    String temp= address.getCardID();
                    try {
                        if(IDCard.IDCardValidate(temp)){
                            temp = temp.substring(0,6)+"***"+temp.substring(temp.length()-4,temp.length());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    address.setCardID(temp);
                }
            }
        }

    }

}
