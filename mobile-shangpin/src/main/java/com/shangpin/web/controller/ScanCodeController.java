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

import com.shangpin.biz.bo.Coupon;
import com.shangpin.biz.bo.CouponInfo;
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
import com.shangpin.biz.service.SPBizCouponService;
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
 * 扫码二维码业务
 * 
 * @author wh
 */
@Controller
@RequestMapping("/QRcode")
public class ScanCodeController extends BaseController {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(ScanCodeController.class);
    
    /** 实物卡展示页 */
    private static final String GIFTCARD_ENTITY_PAGE = "giftCard/recharge_entity_info";
    
    /** 充现金卷展示页 */
    private static final String CASH_COUPON_RECHARGE = "coupon/recharge";
    
    
    @Autowired
    private SPBizCouponService bizCouponService;
    /***
     * 实物卡展示页
     * 
     * @author wanghua
     * @param scanCodeRecharge
     * @param model
     * @param request
     * @return page
     */
    @RequestMapping(value = "/recharge", method = RequestMethod.GET)
    public String cardRecharge(@Valid final ScanCodeRecharge scanCodeRecharge,
            ModelMap model, HttpServletRequest request) {
        request.getSession().setAttribute("scRecharge", scanCodeRecharge);
        model.put("value", scanCodeRecharge.getValue());
        return GIFTCARD_ENTITY_PAGE;
    }
    
    /***
     * 现金卷充值
     * 
     * @author wanghua
     * @param conponRecharge
     * @param model
     * @param request
     * @return page
     */
    @RequestMapping(value = "/conponRecharge", method = RequestMethod.GET)
    public String cardRecharge(@RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "bId", required = true) String bId,
            ModelMap model, HttpServletRequest request) throws Exception {
        Coupon coupon = bizCouponService.findCouponsInfo(bId);
        if (coupon == null) {
            model.addAttribute("msg", "页面加载失败");
        }else {
            model.addAttribute("coupon", coupon);
            //掉接口查询卡信息
            model.put("value", id);
            model.put("bId", bId);
        }
        return CASH_COUPON_RECHARGE;
    }
}
