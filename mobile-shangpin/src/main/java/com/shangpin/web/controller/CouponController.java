package com.shangpin.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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



import com.shangpin.biz.bo.Coupon;
import com.shangpin.biz.bo.PriceShowVo;
import com.shangpin.biz.bo.RecProductFor;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.service.SPBizCartService;
import com.shangpin.biz.service.SPBizCouponService;
import com.shangpin.biz.service.SPBizOrderService;
import com.shangpin.utils.StringUtil;
import com.shangpin.web.utils.Constants;

/**
 * @ClassName: CouponController
 * @Description: 优惠券控制类
 * @author qinyingchun
 * @date 2014年11月11日
 * @version 1.0
 */
@Controller
@RequestMapping("/coupon")
public class CouponController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CouponController.class);

	@Autowired
	private SPBizCouponService bizCouponService;
	@Autowired
	private SPBizCartService bizCartService;
	@Autowired
	private SPBizOrderService bizOrderService;
	

	private static final String COUPON_LIST = "coupon/list";
	private static final String COUPON_UNUSED = "coupon/unused";
	private static final String COUPON_USED = "coupon/used";
	private static final String COUPON_PAST = "coupon/past";
	private static final String ORDER_COUPONS_LIST = "cart/coupons_list";
	private static final String PRICE_SHOW = "cart/price_show";

	private static final String GETCOUPON_SUCCESS = "coupon/getCoupon_success";

	private List<PriceShowVo> priceShowVos;

	private static final String COUPON_PRODUCT_LIST= "coupon/product_list";

	private static final String COUPON_PRODUCT = "order/coupon_product";
	/** 充现金卷展示页 */
    private static final String CASH_COUPON_RECHARGE = "coupon/recharge";
    /** 充现金卷成功页*/
    private static final String CASH_COUPON_SUCESS = "coupon/rechargeSucess";

	/**
	 * 
	 * @Title: list
	 * @Description: 优惠券列表页
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月11日
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		return COUPON_LIST;
	}

	/**
	 * 
	 * @Title: list
	 * @Description: 未使用优惠券列表页
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月11日
	 */
	@RequestMapping(value = "/unsed", method = RequestMethod.GET)
	public String unsed(String type, String start, String size, Model model, HttpServletRequest request) {
		logger.debug("type:" + type);
		String userId = getUserId(request);
		List<Coupon> coupons = this.list(userId, type, start, size);
		if (null == coupons) {
			return COUPON_UNUSED;
		}
		model.addAttribute("coupons", coupons);
		if (coupons.size() < 20) {
			model.addAttribute("start", Constants.COUPON_LIST_START);
			return COUPON_UNUSED;
		}
		if (StringUtils.isEmpty(start)) {
			start = String.valueOf(Integer.parseInt(Constants.COUPON_LIST_START) + 1);
		}
		if (StringUtils.isEmpty(size)) {
			size = Constants.COUPON_LIST_SIZE;
		}
		List<Coupon> nextList = this.list(userId, type, start, size);
		if (null != nextList) {
			model.addAttribute("start", Constants.COUPON_LIST_START);
			model.addAttribute("hasMore", true);
		}
		return COUPON_UNUSED;
	}

	/**
	 * 
	 * @Title: list
	 * @Description: 已使用优惠券列表页
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月11日
	 */
	@RequestMapping(value = "/used", method = RequestMethod.GET)
	public String used(String type, String start, String size, Model model, HttpServletRequest request) {
		logger.debug("type:" + type);
		String userId = getUserId(request);
		List<Coupon> coupons = this.list(userId, type, start, size);
		if (null == coupons) {
			return COUPON_USED;
		}
		model.addAttribute("coupons", coupons);
		if (coupons.size() < 20) {
			model.addAttribute("start", Constants.COUPON_LIST_START);
			return COUPON_USED;
		}
		if (StringUtils.isEmpty(start)) {
			start = String.valueOf(Integer.parseInt(Constants.COUPON_LIST_START) + 1);
		}
		if (StringUtils.isEmpty(size)) {
			size = Constants.COUPON_LIST_SIZE;
		}
		List<Coupon> nextList = this.list(userId, type, start, size);
		if (null != nextList) {
			model.addAttribute("start", Constants.COUPON_LIST_START);
			model.addAttribute("hasMore", true);
		}
		return COUPON_USED;
	}

	/**
	 * 
	 * @Title: list
	 * @Description: 过期优惠券列表页
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月11日
	 */
	@RequestMapping(value = "/past", method = RequestMethod.GET)
	public String past(String type, String start, String size, Model model, HttpServletRequest request) {
		logger.debug("type:" + type);
		String userId = getUserId(request);
		List<Coupon> coupons = this.list(userId, type, start, size);
		if (null == coupons) {
			return COUPON_PAST;
		}
		model.addAttribute("coupons", coupons);
		if (coupons.size() < 20) {
			model.addAttribute("start", Constants.COUPON_LIST_START);
			return COUPON_PAST;
		}
		if (StringUtils.isEmpty(start)) {
			start = String.valueOf(Integer.parseInt(Constants.COUPON_LIST_START) + 1);
		}
		if (StringUtils.isEmpty(size)) {
			size = Constants.COUPON_LIST_SIZE;
		}
		List<Coupon> nextList = this.list(userId, type, start, size);
		if (null != nextList) {
			model.addAttribute("start", Constants.COUPON_LIST_START);
			model.addAttribute("hasMore", true);
		}
		return COUPON_PAST;
	}

	/**
	 * 
	 * @Title: sendActivation
	 * @Description:优惠券激活
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月11日
	 */
	@RequestMapping(value = "/activation", method = RequestMethod.POST)
	@ResponseBody
	public ResultBase sendActivation(String code, HttpServletRequest request) {
		ResultBase resultBase = null;
		if (isUser(request)) {
			String userid = getUserId(request);
			resultBase = bizCouponService.beSendActivation(userid, Constants.SITE_NO_SP, code);
		}
		return resultBase;
	}

	@RequestMapping(value = "/more", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMore(String type, String start, String size, Model model, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = getUserId(request);
		List<Coupon> coupons = this.list(userId, type, start, size);
		map.put("coupons", coupons);
		map.put("start", start);
		if (coupons.size() < 20) {
			map.put("hasMore", false);
			return map;
		}
		start = String.valueOf(Integer.parseInt(start) + 1);
		if (StringUtils.isEmpty(size)) {
			size = Constants.COUPON_LIST_SIZE;
		}
		List<Coupon> nextList = this.list(userId, type, start, size);
		if (null != nextList) {
			map.put("hasMore", true);
		} else {
			map.put("hasMore", false);
		}
		return map;
	}

	/**
	 * 
	 * @Title:提交订单页激活优惠券
	 * @Description:
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月1日
	 */
	@RequestMapping(value = "order/active", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendActivation(String code, String[] coupons, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userid = getUserId(request);
		ResultBase resultBase = bizCouponService.beSendActivation(userid, Constants.SITE_NO_SP, code);
		if (Constants.SUCCESS.equals(resultBase.getCode())) {
			map.put("code", Constants.SUCCESS);
		} else {
			map.put("code", Constants.FAILE);
			map.put("msg", resultBase.getMsg());
		}
		return map;
	}
	
	/**
	 * 
	 * @Title:couponList
	 * @Description:优惠券激活后返回用户可用的优惠券列表
	 * @param model
	 * @param request
	 * @return
	 * @author qinyingchun
	 * @date 2015年3月28日
	 */
	@RequestMapping(value = "order/coupon/list", method = RequestMethod.GET)
	public String couponList(String orderSource, String buyId, Model model, HttpServletRequest request){
		String userid = getUserId(request);
		List<Coupon> list = bizCouponService.couponList(userid, orderSource, buyId);
		if(null != list && list.size() > 0){
			model.addAttribute("coupons", list);
		}
		return ORDER_COUPONS_LIST;
	}

	/**
	 * 
	 * @Title: returnCoupons
	 * @Description: 使用优惠券/折扣码是返回的信息
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月2日
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> returnCoupons(String couponFlag, String coupon, String buyGids, String addressId, String orderSource,
			HttpServletRequest request) {
		String userid = getUserId(request);
		Map<String, Object> map = bizOrderService.updateOrder(userid, couponFlag, coupon, buyGids, addressId, "", orderSource);
		return map;
	}
	
	/**
	 * 使用优惠券折扣码
	 * @param couponType
	 * @param couponCode
	 * @param buyIds
	 * @param orderSource
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/coupon", method = {RequestMethod.GET, RequestMethod.POST})
	public String userCoupon(String type, String totalAmount, String promoAmount, String ticketAmount, String carriageAmount, String discountCode, String giftCardAmount, String postArea, String couponCode , String buyIds, String orderSource , Model model, HttpServletRequest request, HttpServletResponse response){
		String userId = getUserId(request);
		priceShowVos = bizCouponService.useCouponObj(userId, type, totalAmount, promoAmount, ticketAmount, carriageAmount, discountCode, giftCardAmount, postArea, buyIds, orderSource);
		if(null == priceShowVos || priceShowVos.size() == 0){
			model.addAttribute("code", "1");
			response.setHeader("couon_return", "1");
		}else {
			response.setHeader("couon_return", "0");
			model.addAttribute("code", "0");
			model.addAttribute("prices", priceShowVos);
		}
		return PRICE_SHOW;
	}

	/**
	 * 
	 * @Title: getCoupon
	 * @Description: 领取优惠券
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月9日
	 */
	@RequestMapping(value = "/ajax/getCoupon", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> ajaxGetCoupon(HttpServletRequest request, @RequestParam("couponCode") String couponCode
	        ,  String couponType) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "";
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			code = Constants.UNLOGIN;
			map.put("code", code);
			return map;
		}
		if (StringUtil.isBlank(couponType)) {
		    couponType="9";
        }
		map = bizCouponService.sendCoupon(user.getUserid(), "1", "coupon:" + couponCode, couponType);
		return map;
	}
	/**
	 * 
	 * @Title: getCoupon
	 * @Description: 领取优惠券
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月9日
	 */
	@RequestMapping(value = "/app/getCoupon", method = { RequestMethod.GET, RequestMethod.POST })
	public String appGetCoupon(HttpServletRequest request, String back) {
//		User user = getSessionUser(request);
//		if (user == null || StringUtils.isEmpty(user.getUserid())) {
//			  return "redirect:" + back;
//		}
//		 bizCouponService.sendCoupon(user.getUserid(), "1", "coupon:" + couponCode, "9");
		 return "redirect:" + back;
	}
	
	
	/**
	 * 
	 * @Title: getCoupon
	 * @Description: 领取优惠券
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月9日
	 */
	@RequestMapping(value = "/getCoupon", method = { RequestMethod.GET, RequestMethod.POST })
	public String getCoupon(HttpServletRequest request, @RequestParam("couponCode") String couponCode,String topicId,Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = getSessionUser(request);
		map = bizCouponService.sendCoupon(user.getUserid(), "1", "coupon:" + couponCode, "8");
		if(!StringUtil.isEmpty(topicId)){
			String back="/subject/product/list?topicId="+topicId;
			return "redirect:" + back;
		}
		if (Constants.SUCCESS.equals(map.get("code"))) {
			model.addAttribute("code","0");
			return GETCOUPON_SUCCESS;
		}
		// 发送失败时没有业务逻辑
		model.addAttribute("code","1");
		return GETCOUPON_SUCCESS;
	}
	@RequestMapping(value = "/product/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String productList(HttpServletRequest request,Model model,String pageIndex,String payAmount,String postArea,String from) {
	
		User user = getSessionUser(request);
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		pageIndex = ( StringUtils.isEmpty(pageIndex)) ? "1" : pageIndex;
		Map<String,Object> map=bizCouponService.searchUseCouponProductList(payAmount, pageIndex, Constants.COUPON_PRODUCT_LIST_SIZE, null, null, "1", null, null, null, null, null, null,userLv);
		if(map==null){
			return null;
		}
		if(!map.get("code").equals("0")){
			return null;
		}
		model.addAttribute("recProductFor",map.get("recProductFor"));
		model.addAttribute("userLv", userLv);
		model.addAttribute("payAmount", payAmount);
		if(!StringUtils.isEmpty(from)&&from.equals("pay")){
			return COUPON_PRODUCT;
		}
		return COUPON_PRODUCT_LIST;
	}
	@RequestMapping(value = "/product/list/more", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> productListMore(HttpServletRequest request,Model model,String pageIndex,String payAmount,String postArea,String from) {
		User user = getSessionUser(request);
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		pageIndex = ( StringUtils.isEmpty(pageIndex)) ? "1" : pageIndex;
		String start=String.valueOf(Integer.valueOf(pageIndex)+1);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String,Object> mapCoupon=bizCouponService.searchUseCouponProductList(payAmount, start, Constants.COUPON_PRODUCT_LIST_SIZE, null, null, "1", null, null, null, null, null, null,userLv);
		if(mapCoupon==null){
			return null;
		}
		if(!mapCoupon.get("code").equals("0")){
			return null;
		}
		RecProductFor recProductItem =(RecProductFor) mapCoupon.get("recProductFor");
		map.put("recProductItem", recProductItem);
		map.put("userLv", userLv);
		map.put("pageIndex", start);
		map.put("hasMore", "0");
		if(recProductItem!=null&&recProductItem.getList()!=null){
			if(recProductItem.getList().size()>=Integer.valueOf(Constants.COUPON_PRODUCT_LIST_SIZE)){
				map.put("hasMore", "1");
			}
		}
		return map;
	}
	private List<Coupon> list(String userId, String type, String start, String size) {
		String startIndex = StringUtils.isEmpty(start) ? Constants.COUPON_LIST_START : start;
		String show_size = StringUtils.isEmpty(size) ? Constants.COUPON_LIST_SIZE : size;
		List<Coupon> list = bizCouponService.findCouponsList(userId, Constants.SITE_NO_SP, type, startIndex, show_size);
		return list;
	}

	private Coupon newAdd(String[] coupons, List<Coupon> list) {
		for (int j = 0; j < list.size(); j++) {
			for (int i = 0; i < coupons.length; i++) {
				String no = list.get(j).getCouponno();
				if (no.equals(coupons[i])) {
					list.remove(j);
				}
			}
		}
		return list.get(0);
	}

	public static void main(String[] args) {
		List<Coupon> list = new ArrayList<Coupon>();
		Coupon coupon = new Coupon();
		coupon.setCouponno("1");
		Coupon coupon2 = new Coupon();
		coupon2.setCouponno("2");
		Coupon coupon3 = new Coupon();
		coupon3.setCouponno("3");
		Coupon coupon4 = new Coupon();
		coupon4.setCouponno("4");
		list.add(coupon);
		list.add(coupon2);
		list.add(coupon3);
		list.add(coupon4);
		String[] arr = new String[] { "1", "2", "4" };
		CouponController controller = new CouponController();
		System.out.println(controller.newAdd(arr, list).getCouponno());
	}
	
    /**
     * 
     * @Title: recharge
     * @Description: 一键充值
     * @paramc ardNo
     * @return String
     * @throws
     * @Create By wh
     * @Create Date 2016年01月13日
     */
    @RequestMapping(value = "/recharge")
    public String recharge(@RequestParam("cardNo") String cardNo, String type,
            @RequestParam("bId") String bId,Model model,HttpServletRequest request)throws Exception  {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = getSessionUser(request);
        model.addAttribute("type", type);
        map = bizCouponService.sendCoupon(user.getUserid(), "1", "coupon:" + cardNo, "30");
        if (Constants.SUCCESS.equals(map.get("code"))) {
            return CASH_COUPON_SUCESS;
        }
        Coupon coupon = bizCouponService.findCouponsInfo(bId);
        model.addAttribute("coupon", coupon);
        model.addAttribute("bId", bId);
        model.addAttribute("value", cardNo);
        model.addAttribute("msg", map.get("msg"));
        // 发送失败时没有业务逻辑
        return CASH_COUPON_RECHARGE;
    }
}
