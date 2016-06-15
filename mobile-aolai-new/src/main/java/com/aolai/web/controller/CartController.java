package com.aolai.web.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.aolai.web.utils.BizUtil;
import com.aolai.web.utils.Constants;
import com.shangpin.base.vo.CartOrder;
import com.shangpin.biz.bo.Address;
import com.shangpin.biz.bo.Area;
import com.shangpin.biz.bo.CartContent;
import com.shangpin.biz.bo.CartItems;
import com.shangpin.biz.bo.CartList;
import com.shangpin.biz.bo.City;
import com.shangpin.biz.bo.ConsigneeAddress;
import com.shangpin.biz.bo.Coupon;
import com.shangpin.biz.bo.CouponReturn;
import com.shangpin.biz.bo.Delivery;
import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.bo.OrderReturn;
import com.shangpin.biz.bo.Pay;
import com.shangpin.biz.bo.Province;
import com.shangpin.biz.bo.Settlement;
import com.shangpin.biz.bo.Town;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.ALBizAddressService;
import com.shangpin.biz.service.ALBizCartService;
import com.shangpin.biz.service.ALBizOrderService;

/**
 * 购物相关的Controller
 * 
 * @author cuibinqiang
 * @date 2014-11-4
 *
 */
@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(CartController.class);
    /** 购物车列表页 */
    public static final String CARTLIST = "cart/cart_list";
    /** 被拦截后：购物车列表*/
    public static final String RECARTLIST = "redirect:/cart/list";
    /**购物车为空时页面*/
    public static final String NOCART = "cart/cart_empty";
    /**提交订单页*/
    public static final String SUBMIT = "order/submit_order";
    /**提交订单成功页*/
    public static final String SUCCESS = "order/submit_success";
    /**零元购订单支付成功页*/
    public static final String PAYSUCCESS = "order/pay_success";
    /**选择支付方式页*/
    public static final String PAYMENT = "order/submit_pay";
   
    @Autowired
    private ALBizCartService cartService;
    @Autowired
    private ALBizAddressService addressService;
    @Autowired
    private ALBizOrderService aLBizOrderService;// 订单服务
    

    /**
     * 加入购物车（Ajax）
     * 
     * @author cuibinqiang
     */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> add(HttpServletRequest request,String productNo,String quantity,String skuNo,
    		String categoryNo,String typeFlag,String pageType) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		String userId = getUserId(request);
		map1 = cartService.addToCart(userId, productNo, quantity, skuNo, categoryNo);
		String code = (String) map1.get("code");
		String msg = (String) map1.get("msg");
		
		if(Constants.SUCCESS.equals(code)){//主站处理成功
			map.put("code", "3");// 跳转至购物车列表页面
			//跳转详情页所需参数
			map.put("typeFlag", typeFlag);
			map.put("pageType", pageType);
		}else{//处理失败，前台弹出错误提示信息
			map.put("code", "2");
			map.put("msg", msg);
		}
		
        return map;
    }
	
	/**
	 * 加入购物车（拦截器拦截，登录成功后执行）
	 * 
	 * @return
	 *
	 * @author cuibinqiang
	 */
	@RequestMapping(value = "/add/redirect", method = RequestMethod.GET)
	public String addToCart(HttpServletRequest request,String productNo,String quantity,String skuNo,
    		String categoryNo,String typeFlag,String pageType) {
		String userId = getUserId(request);
		Map<String, Object> map = new HashMap<String, Object>();
		map = cartService.addToCart(userId, productNo, quantity, skuNo, categoryNo);
		String code = (String) map.get("code");
		
		if(Constants.SUCCESS.equals(code)){
			//跳转详情页所需参数
			request.getSession().setAttribute("typeFlag", typeFlag);
			request.getSession().setAttribute("pageType", pageType);
			return RECARTLIST;// 跳转至购物车列表页面
		}
		return null;
	}
	
	/**
	 * 购物车列表
	 * 
	 * @author cuibinqiang
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request,Map<String, Object> map,String pageType,String typeFlag,
			String pich,String picw,String shopType,String isPromotion){
		if(StringUtils.isEmpty(typeFlag)&&StringUtils.isEmpty(pageType)){//未登录拦截跳转过来
			pageType = (String) request.getSession().getAttribute("pageType");
			typeFlag = (String) request.getSession().getAttribute("typeFlag");
		}
		User user= getSessionUser(request);
		String userId = "";
		if(user != null){
			userId = user.getUserid();
		}
		
		//判断请求来源：客户端/M站
		if (pich==null||"".equals(pich)) {//图片高
			pich = Constants.CART_LIST_HEIGHT;
        }
        if(picw==null||"".equals(picw)){//图片宽
        	picw = Constants.CART_LIST_WIDTH;
        }
        if(isPromotion==null||"".equals(isPromotion)){//是否促销:0为促销，1为不参加促销
        	isPromotion = Constants.IS_PROMOTION_NO;
        }if(shopType==null||"".equals(shopType)){//购物车类型：1：尚品   2：奥莱
        	shopType = Constants.SHOP_TYPE_AOLAI;
        }
		
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1 = cartService.listCart(userId, pich, picw, shopType, isPromotion);
		List<CartList> listCart = (List<CartList>) map1.get("listCart");
		map.put("listCart", listCart);
		map.put("pageType", pageType);
		map.put("typeFlag", typeFlag);
		if(listCart == null || listCart.size()<=0){
			return NOCART;
		}
		return CARTLIST;
	}
	
	/**
	 * 删除购物车商品（Ajax）
	 * 
	 * @param map
	 * @return
	 *
	 * @author cuibinqiang
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,String shopDetailId){
		Map<String, Object> map = new HashMap<String, Object>();
		User user= getSessionUser(request);
		String userId = "";
		if(user != null){
			userId = user.getUserid();
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1 = cartService.delCart(userId, shopDetailId);
		String code = (String) map1.get("code");
		String msg = (String) map1.get("msg");
		
		map.put("code", code);
		map.put("msg", msg);
		
		return map;
	}
	
	/**
	 * 修改购物车商品数量（Ajax）
	 * 
	 * @return
	 *
	 * @author cuibinqiang
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> update(HttpServletRequest request,String shopDetailId,String quantity){
		Map<String, Object> map = new HashMap<String, Object>();
		User user= getSessionUser(request);
		String userId = "";
		if(user != null){
			userId = user.getUserid();
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1 = cartService.updateCart(userId, shopDetailId, quantity);
		String code = (String) map1.get("code");
		String msg = (String) map1.get("msg");
		String conInven = (String) map1.get("conInven");
		String conCode = (String) map1.get("conCode");
		
		map.put("code", code);
		map.put("msg", msg);
		map.put("conInven", conInven);
		map.put("conCode", conCode);
		
		return map;
	}
	
	/**
	 * 跳转到提交订单页
	 * 
	 * @param request
	 * @param map
	 * @param shopDetailId
	 * @return
	 *
	 * @author cuibinqiang
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String toSubmit(HttpServletRequest request,Map<String, Object> map,String shopDetailId){
		User user= getSessionUser(request);
		String userId = "";
		if(user != null){
			userId = user.getUserid();
		}
		String picH = Constants.CART_LIST_HEIGHT;
		String picW = Constants.CART_LIST_WIDTH;
		String isPromotion  = Constants.IS_PROMOTION_NO;
		//提交购物车商品
		CartContent result = cartService.submitCart(userId, shopDetailId, picW, picH);
		//获取生成订单的详细信息
		if(result != null){
			Settlement settle = cartService.getCart(userId, isPromotion, picW, picH,"1");
			
			if(settle != null){
				//收货人地址列表
				List<ConsigneeAddress> consigneeAddrList =  settle.getReceive();
				//发票地址列表
				List<ConsigneeAddress> invoiceAddrList = settle.getInvoiceaddrs();
				//优惠劵列表
				List<Coupon> couponList = settle.getCoupon();
				//配送方式列表
				List<Delivery> deliveryList = BizUtil.getDelivery();
				//省级列表
				List<Province> provinceList = addressService.findProvinceListObj();
				
				CartOrder orderVO = new CartOrder();
				orderVO.setAmount(settle.getAmount());
				orderVO.setCoupon(settle.getCoupon().toString());
				orderVO.setCarriage(settle.getCarriage().getAmount());
				orderVO.setCodincart(settle.getCodincart());//是否支持货到付款
				
				List<CartItems> productList  = settle.getList().getAlList();
				
				map.put("consigneeAddrList", consigneeAddrList);
				map.put("invoiceAddrList", invoiceAddrList);
				map.put("couponList", couponList);
				map.put("deliveryList", deliveryList);
				map.put("provinceList", provinceList);
				map.put("orderVO", orderVO);
				map.put("productList", productList);
			}
			
		}
		return SUBMIT;
	}
	
	/**
	 * 提交订单（Ajax）
	 * 
	 * @param orderVO
	 * @return
	 *
	 * @author cuibinqiang
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/submit/order", method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> submit(HttpServletRequest request,CartOrder orderVO){
		Map<String,Object> map = new HashMap<String, Object>();
		//获取用户ID
		User user= getSessionUser(request);
		String userId = "";
		if(user != null){
			userId = user.getUserid();
		}
		orderVO.setUserid(userId);
		orderVO.setOrderfrom("19");//订单来源：19 M站订单渠道
		String coupon  = orderVO.getCoupon();//优惠券或折扣码编号:没有传"0"
		if(StringUtils.isEmpty(coupon)){
			coupon = "0";
		}
		orderVO.setCoupon(coupon);
		String buyIds = orderVO.getBuysids();//购物车商品id:多个用"|"分开
		String orderType = "2";//订单类型:尚品1，奥莱2
		orderVO.setOrdertype(orderType);
		
		String isUseGiftCardPay = "0";//是否使用礼品卡:0 为不使用；1 使用
		
		//提交订单
		OrderReturn order = cartService.submitOrder(orderVO);
		String code = "";
		String cod = "";
		String msgcode = "0";//零元购或跳转第三方标识：0 跳转第三方，1 零元购
		if(order != null){
			//零元购：使用优惠券等支付金额为0时的处理
			if(Integer.parseInt(order.getAmount())==0 && Integer.parseInt(order.getCarriage()) == 0){
				//更新订单状态
				String mainPay = "0";
				String subPay = "0";
				String orderId = order.getOrderid();
				String payMoney = Constants.PAY_AMOUNT;
				//调主站，更新订单状态
				Map<String, Object> result = cartService.updateStatus(mainPay, subPay, orderId, payMoney);
				String opt = (String) result.get("msgcode");
				String msg = (String) result.get("msg");
				if(Constants.SUCCESS.equals(opt)){
					logger.debug("***Success to update the order status!***0RMB");
				}
				if(Constants.FAILURE.equals(opt)){
					logger.debug("***Fail to update the order status：***0RMB"+msg);
				}
				msgcode  = "1";//标识为零元购
			}
			code = Constants.SUCCESS;//成功标识
			cod = order.getCod();//是否支持货到付款：1 支持，0不支持
			map.put("order", order);
			map.put("code", code);
			map.put("cod", cod);
			map.put("msgcode", msgcode);
		}
		return map;
	}
	
	
	/**
	 * 跳转到提交订单成功页
	 * 
	 * @param orderId 订单号
	 * @param amount 订单金额
	 * @param cod 是否支持货到付款
	 * @param msgcode 零元购或跳转第三方标识：0 跳转第三方，1 零元购
	 * @return
	 *
	 * @author cuibinqiang
	 */
	@RequestMapping(value = "/submit/success", method = RequestMethod.GET)
	public String success(Map<String, Object> map,String orderId,String msgcode){
		map.put("orderId", orderId);
		if("1".equals(msgcode)){//零元购订单支付成功页
			return PAYSUCCESS;
		}
		return SUCCESS;//跳转到第三方提交订单成功页
	}
	
	/**
	 * 跳转到选择支付方式页
	 * 
	 * @return
	 *
	 * @author cuibinqiang
	 */
	@RequestMapping(value = "/submit/pay", method = RequestMethod.GET)
	public String pay(HttpServletRequest request,Map<String, Object> map,String orderId){
		//获取渠道号
		//String channelNo = getChannelNo(request);
		//获取accept用于js判断是否是UC浏览器
		String accept=request.getHeader("Accept");
		//获取支付方式
		List<Pay> payList = BizUtil.getPay();
		
		// 获取用户ID
        String userId = getUserId(request);
		// 获取订单详细信息
        OrderItem order = findOrderDetail(userId, orderId);
        if (order == null) {
            logger.debug("OrderDetail is null");
            return null;
        }
        
        //判断是否支持货到付款
        String cod = order.getIscod();
		map.put("payList", payList);
		map.put("accept", accept);
		map.put("orderId", orderId); 
		map.put("cod", cod);
		
		return PAYMENT;
	}
	
	/**
	 * 获取订单详情
	 * 
	 * @param userId 用户ID
	 * @param orderId 订单ID
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
     * 确认订单信息时更改地址和优惠码/券内容（Ajax）
     * 
     * @param userId
     *            用户ID
     * @param couponFlag
     *            1使用优惠券； 2使用优惠码；未使用传空字符串；
     * @param coupon
     *            flag为优惠券时，coupon为优惠券id；flag为优惠码时，coupon为优惠码串
     * @param buyGids
     *            购买商品的gid串，多个用“|”分割
     * @param addressId
     *            使用的收货地址id（兼容以后运费与地址有关的情况）
     * @param topicNo
     *            专题编号
     * @author cuibinqiang
     */
	@RequestMapping(value = "/submit/update", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> updateOrder(String addressId, String couponFlag, String coupon, String buyGids, 
			HttpServletRequest request){
		//获取用户ID
		User user= getSessionUser(request);
		String userId = "";
		if(user != null){
			userId = user.getUserid();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		String topicNo = "";
		Map<String, Object> result = cartService.updateOrder(userId, couponFlag, coupon, buyGids, addressId, topicNo,"1");
		String msgcode = (String) result.get("msgcode");
		if(Constants.SUCCESS.equals(msgcode)){//成功
			CouponReturn couponVO = (CouponReturn) result.get("couponVO");
			map.put("couponVO", couponVO);
		}else{
			String msg = (String) result.get("msg");
			map.put("msg", msg);
		}
		map.put("msgcode", msgcode);
		
		return map;
	}
	
	/**
	 * 获取市级（Ajax）
	 * 
	 * @param provinceId
	 * @return
	 *
	 * @author cuibinqiang
	 */
	@RequestMapping(value = "/city", method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> city(String provinceId){
		Map<String,Object> map = new HashMap<String, Object>();
		List<City> cityList  = addressService.findCityListObj(provinceId);
		map.put("cityList", cityList);
		return map;
	}
	
	/**
	 * 获取区级（Ajax）
	 * 
	 * @param provinceId
	 * @return
	 *
	 * @author cuibinqiang
	 */
	@RequestMapping(value = "/area", method = RequestMethod.GET)
    @ResponseBody
	public Map<String,Object> area(String cityId){
		 Map<String,Object> map = new HashMap<String, Object>();
		 List<Area> areaList = addressService.findAreaListObj(cityId);
		 map.put("areaList", areaList);
		return map;
	}
	
	/**
	 * 获取街道级（Ajax）
	 * 
	 * @param provinceId
	 * @return
	 *
	 * @author cuibinqiang
	 */
	@RequestMapping(value = "/town", method = RequestMethod.GET)
    @ResponseBody
	public Map<String,Object> town(String areaId){
		Map<String,Object> map = new HashMap<String, Object>();
		List<Town> townList = addressService.findTownList(areaId);
		map.put("townList", townList);
		return map;
	}
	
	/**
	 * 添加收货人地址（Ajax）
	 * 
	 * @param address
	 * @param request
	 * @return
	 *
	 * @author cuibinqiang
	 */
	@RequestMapping(value = "/add/address", method = RequestMethod.GET)
    @ResponseBody
	public Map<String,Object> addAddr(Address address, HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		String userId = getUserId(request);
		List<Address>  addrList = addressService.addAddr(address, userId);
		boolean result = false;
		Address addr = new Address();
		if(addrList != null){//新增收货地址成功
			result = true;
		    addr = addrList.get(0);
		}
		map.put("size", addrList.size());
		map.put("result", result);
		map.put("addr", addr);
		return map;
	}
	
	/**
	 * 添加发票地址（Ajax）
	 * 
	 * @param address
	 * @param request
	 * @return
	 *
	 * @author cuibinqiang
	 */
	@RequestMapping(value = "/add/invoice", method = RequestMethod.GET)
    @ResponseBody
	public Map<String,Object> addInvoice(com.shangpin.base.vo.ConsigneeAddress consigneeAddress, HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		String userId = getUserId(request);
		consigneeAddress.setUserId(userId);
		List<Address> addrList = cartService.addInvoice(consigneeAddress);
		boolean result = false;
		Address addr = new Address();
		if(addrList != null){//新增收货地址成功
			result = true;
		    addr = addrList.get(0);
		}
		map.put("size", addrList.size());
		map.put("result", result);
		map.put("addr", addr);
		return map;
	}

}
