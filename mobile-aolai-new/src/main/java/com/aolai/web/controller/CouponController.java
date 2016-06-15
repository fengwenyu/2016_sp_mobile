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

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aolai.web.utils.Constants;
import com.shangpin.biz.bo.Coupon;
import com.shangpin.biz.service.ALBizCouponService;

/**
 * 优惠券控制层
 * 
 * @author zghw
 */
@Controller
public class CouponController extends BaseController {

    public static Logger logger = LoggerFactory.getLogger(CouponController.class);

    /** 优惠券列表 */
    public static final String COUPON_LIST = "coupon/list";

    @Autowired
    private ALBizCouponService couponService;

    /**
     * 优惠券列表
     * 
     * @author zhanghongwei
     */
    @RequestMapping(value = "/user/coupon/list", method = RequestMethod.GET)
    public String couponList(@RequestParam(value = "couponType", defaultValue = Constants.TYPE_UNUSED, required = false) String couponType, String callbackUrl,
            @RequestParam(value = "pageNo", defaultValue = Constants.PAGE_NO_1_STR, required = false) String pageNo, Map<String, Object> map, HttpServletRequest request) {
        String userId = getUserId(request);
        List<Coupon> couponList = couponService.findCouponsList(userId, couponType, pageNo, String.valueOf(Constants.PAGE_SIZE));
        // 是否存在优惠券
        boolean haveCoupon = false;
        if (couponList != null && (!couponList.isEmpty())) {
            haveCoupon = true;
        }
        // 是否存在加载更多
        boolean isHaveMore = false;
        if (haveCoupon && (couponList.size() >= Constants.PAGE_SIZE)) {
            isHaveMore = true;
        }
        map.put("couponList", couponList);
        map.put("couponType", couponType);
        map.put("haveCoupon", haveCoupon);
        map.put("isHaveMore", isHaveMore);
        map.put("pageNo", pageNo);
        if (!StringUtils.isEmpty(callbackUrl)) {
            return "coupon/" + callbackUrl;
        }
        return COUPON_LIST;
    }
}
