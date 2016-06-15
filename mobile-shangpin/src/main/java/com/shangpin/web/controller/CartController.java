package com.shangpin.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.CartContent;
import com.shangpin.biz.bo.ConsigneeAddress;
import com.shangpin.biz.bo.PriceShowVo;
import com.shangpin.biz.bo.Province;
import com.shangpin.biz.bo.RecProduct;
import com.shangpin.biz.bo.Settlement;
import com.shangpin.biz.bo.ShopCartItem;
import com.shangpin.biz.bo.ShopCartList;
import com.shangpin.biz.bo.ShopCartProductList;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.bo.cart.Cart;
import com.shangpin.biz.bo.cart.CartList;
import com.shangpin.biz.bo.cart.CartProductList;
import com.shangpin.biz.service.SPBizAddressService;
import com.shangpin.biz.service.SPBizCartService;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.utils.AESUtil;
import com.shangpin.utils.ArithmeticUtil;
import com.shangpin.web.utils.Constants;

/**
 * @ClassName: CartController
 * @Description:购物车控制层
 * @author qinyingchun
 * @date 2014年11月5日
 * @version 1.0
 */
@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	// private static final String CART_LIST = "cart/list";
	private static final String CART_LIST_NULL = "cart/no_product";
	private static final String CART_PAY = "cart/cart_pay";
	private static final String OVERSEA_PAY = "oversea_cart/order_fill";

	private static final String NEW_CART_LIST = "cart520/cartList";
	private static final String MODIFY = "cart520/modify";

	@Autowired
	private SPBizCartService bizCartService;
	@Autowired
	private SPBizAddressService bizAddressService;

	/**
	 * 
	 * @Title: add
	 * @Description: 加入购物车
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月6日
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(String productNo, String quantity, String sku, String topicNo, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			code = Constants.UNLOGIN;
		} else {
			String userId = user.getUserid();
			String categoryNo;
			String topicSubjectFlag;
			if (!StringUtils.isEmpty(topicNo)) {
				categoryNo = topicNo;
				topicSubjectFlag = Constants.BUY_TOPIC;
			} else {
				categoryNo = Constants.DEFAULT_CATEGORYNO;
				topicSubjectFlag = Constants.BUY_LIST;
			}
			ResultBase result =null;
			//增加商品客的参数channelNo channelId
			HttpSession session = request.getSession();
			String channelNo = (String) session.getAttribute(Constants.THRID_TOKEN_ChannelNo);
        	String channelId = (String) session.getAttribute(Constants.THRID_TOKEN_ChannelId);
        	String spuNo = (String) session.getAttribute(Constants.THRID_TOKEN_SpuNo);
        	String topicId = (String) session.getAttribute(Constants.THRID_TOKEN_TopicId);
        	boolean isTopic = false;
        	boolean isSpu = false;
        	//校验活动id
        	if(StringUtil.isNotEmpty(topicId,channelNo,channelId)){
        		if(topicId.equals(categoryNo)){
        			isTopic = true;
        			session.setAttribute(Constants.THRID_TOKEN+productNo, productNo);//将商品sku放入session
        		}
        	}
        	//验证商品是否是那件spu商品
        	if(StringUtil.isNotEmpty(spuNo,channelNo,channelId)){
        		if(spuNo.equals(productNo)){
        			isSpu = true;
        		}
        	}
        	if(isTopic||isSpu){
        		result = bizCartService.beAddToCart(userId, productNo, quantity, sku, categoryNo, Constants.SKU_DYNAMIC, topicSubjectFlag, Constants.SKU_MB,
    					Constants.VIP_NO, Constants.SITE_NO_SP,channelNo,channelId);
        	}else{
        		result = bizCartService.beAddToCart(userId, productNo, quantity, sku, categoryNo, Constants.SKU_DYNAMIC, topicSubjectFlag, Constants.SKU_MB,
        				Constants.VIP_NO, Constants.SITE_NO_SP,"","");
        	}
			
			code = result.getCode().equals(Constants.SUCCESS) ? Constants.SUCCESS : Constants.FAILE;
			msg = result.getMsg();
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addToList(String productNo, String quantity, String sku, String topicNo, HttpServletRequest request) {
		User user = getSessionUser(request);
		String userId = user.getUserid();
		String categoryNo;
		String topicSubjectFlag;
		if (!StringUtils.isEmpty(topicNo)) {
			categoryNo = topicNo;
			topicSubjectFlag = Constants.BUY_TOPIC;
		} else {
			categoryNo = Constants.DEFAULT_CATEGORYNO;
			topicSubjectFlag = Constants.BUY_LIST;
		}
		ResultBase result =null;
		//增加商品客的参数channelNo channelId
		HttpSession session = request.getSession();
		String channelNo = (String) session.getAttribute(Constants.THRID_TOKEN_ChannelNo);
    	String channelId = (String) session.getAttribute(Constants.THRID_TOKEN_ChannelId);
    	String spuNo = (String) session.getAttribute(Constants.THRID_TOKEN_SpuNo);
    	String topicId = (String) session.getAttribute(Constants.THRID_TOKEN_TopicId);
    	boolean isTopic = false;
    	boolean isSpu = false;
    	//校验活动id
    	if(StringUtil.isNotEmpty(topicId,channelNo,channelId)){
    		if(topicId.equals(categoryNo)){
    			isTopic = true;
    			session.setAttribute(Constants.THRID_TOKEN+productNo, productNo);//将商品sku放入session
    		}
    	}
    	//验证商品是否是那件spu商品
    	if(StringUtil.isNotEmpty(spuNo,channelNo,channelId)){
    		if(spuNo.equals(productNo)){
    			isSpu = true;
    		}
    	}
    	if(isTopic||isSpu){
    		result = bizCartService.beAddToCart(userId, productNo, quantity, sku, categoryNo, Constants.SKU_DYNAMIC, topicSubjectFlag, Constants.SKU_MB,Constants.VIP_NO, Constants.SITE_NO_SP,channelNo,channelId);
    	}else{
    		result = bizCartService.beAddToCart(userId, productNo, quantity, sku, categoryNo, Constants.SKU_DYNAMIC, topicSubjectFlag, Constants.SKU_MB, Constants.VIP_NO,Constants.SITE_NO_SP,"","");
    	}
		return "redirect:/cart/list";
	}
	
	/**
	 * 计算购物车商品数量(520)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public int count(HttpServletRequest request){
		User user=getSessionUser(request);
		if(StringUtils.isEmpty(user)){
            return -1;
        }
		String userId=user.getUserid();
		if(StringUtils.isEmpty(userId)){
			return -1;
		}
		try{
			Cart cart=bizCartService.doShowCartV2(userId, null);
			int count=0;
			List<CartList> cartLists=cart.getCartList();
			if(null!=cartLists && cartLists.size()>0){
				for(CartList cartList:cartLists){
					List<CartProductList> cartProductLists=cartList.getProductList();
					for(CartProductList cartProductList:cartProductLists){
						int quantity=Integer.parseInt(cartProductList.getQuantity());
						count=count+quantity;
					}
				}
			}
			return count;
		}catch (Exception e){
			return 0;
		}
	}

	/**
	 * 
	 * @Title: count
	 * @Description: 获取购物车列表商品数量
	 * @param
	 * @return int
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月6日
	 *//*
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public int count(HttpServletRequest request) {
		User user = getSessionUser(request);
		if (StringUtils.isEmpty(user)) {
			return -1;
		}
		try {
			ShopCartItem shopCartItem = bizCartService.showCart(user.getUserid(), null);
			int count1 = 0;
			List<ShopCartList> shopCartLists = shopCartItem.getCartList();
			if(null != shopCartLists && shopCartLists.size() > 0){
				for(ShopCartList shopCartList : shopCartLists){
					List<ShopCartProductList> productLists = shopCartList.getProductList();
					for(ShopCartProductList cartProductList : productLists){
						int quantity = Integer.parseInt(cartProductList.getQuantity());
						count1 += quantity;
					}
				}
			}
			int count2 = 0;
			List<ShopCartItem> abroadShopCartItems = shopCartItem.getAbroad();
			if(null != abroadShopCartItems && abroadShopCartItems.size() > 0){
				for(ShopCartItem abroadShopCartItem : abroadShopCartItems){
					List<ShopCartList> abroadShopCartLists = abroadShopCartItem.getCartList();
					for(ShopCartList abroadShopCartList : abroadShopCartLists){
						List<ShopCartProductList> abroadShopCartProductLists = abroadShopCartList.getProductList();
						for(ShopCartProductList abroadShCartProductList : abroadShopCartProductLists){
							int quantity = Integer.parseInt(abroadShCartProductList.getQuantity());
							count2 += quantity;
						}
					}
				}
			}
			return count1 + count2;
		} catch (Exception e) {
			return 0;
		}
	}*/


	/**
	 * 
	 * @Title: del
	 * @Description:删除购物车
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月7日
	 */
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> del(String userId, String shopDetailId, HttpServletRequest request) {
		User user = getSessionUser(request);
		logger.debug("User ID:" + user.getUserid());
		ResultBase obj = bizCartService.beDelCart(user.getUserid(), shopDetailId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", obj.getCode());
		map.put("msg", obj.getMsg());
		return map;
	}

	/**
	 * 
	 * @Title: update
	 * @Description: 更新购物车商品数量
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月7日
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> update(String userId, String shopDetailId, String quantity, HttpServletRequest request) {
		User user = getSessionUser(request);
		logger.debug("User ID:" + user.getUserid());
		Map<String, Object> map = bizCartService.updateCart(user.getUserid(), shopDetailId, quantity);
		return map;
	}

	@RequestMapping(value = "/topay", method = RequestMethod.GET)
	public String toPay(String shopCartDetailIds, String postArea, Model model, HttpServletRequest request) {
		String userId = getUserId(request);
		Settlement elements = null;
		ResultObjOne<CartContent> obj = bizCartService.beSubmitCart(userId, shopCartDetailIds, Constants.CART_PIC_WIDTH, Constants.CART_PIC_HEIGHT);
		boolean flag = obj != null ? obj.isSuccess() : false;
		if (flag) {
			elements = bizCartService.getCart(userId, Constants.PROMOTION_N, Constants.CART_PIC_WIDTH, Constants.CART_PIC_HEIGHT, postArea);
		} else {
			model.addAttribute("msg", obj.getMsg());
			return list(model, request, null);
		}
		if (elements == null) {
			return list(model, request, null);
		} 
		this.decryptCardID(elements);
		// 判断收货地址
		List<ConsigneeAddress> addresses = elements.getReceive();
		if (addresses.size() == 0) {
			model.addAttribute("haveAddress", false);
		} else {
			model.addAttribute("haveAddress", true);
			model.addAttribute("address", address(addresses, elements.getLastReceiveId()));
		}
		List<PriceShowVo> priceShowVos = elements.getPriceShow();
		for(PriceShowVo priceShowVo : priceShowVos){
			if("5".equals(priceShowVo.getType())){
				model.addAttribute("ori_carriage", priceShowVo);
			}else if("6".equals(priceShowVo.getType())){
				model.addAttribute("ori_nocarriage", priceShowVo);
			}
		}
		double amount = Double.parseDouble(elements.getPayAmount());
		List<Province> provinces = bizAddressService.findProvinceListObj();
		model.addAttribute("provinces", provinces);
		model.addAttribute("elements", elements);
		model.addAttribute("real_pay", amount);
		if("2".equals(postArea)){
			String isHaveDirect = elements.getIsHaveDirect();
			String productAmount = elements.getProductAmount();
			boolean isDir = "1".equals(isHaveDirect);//是直发商品 走海外
			boolean isProAmount = productAmount!=null && Double.parseDouble(productAmount) >= Double.parseDouble(com.shangpin.biz.utils.Constants.PAY_OVERSEA_PRICE_LINE);
			if(isDir||!isProAmount){
				model.addAttribute("isPayOut", "1");
			}
			return OVERSEA_PAY;
		}
		return CART_PAY;

	}
	
	/**
	 * 身份证号码解码
	 * 
	 * @param fillData
	 * @author zghw
	 */
	private void decryptCardID(Settlement elements) {
		if (elements == null) {
			return;
		}
		if (elements.getReceive() == null) {
			return;
		}
		try {
			for (ConsigneeAddress address : elements.getReceive()) {
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

	private ConsigneeAddress address(List<ConsigneeAddress> addresses, String lastAddressId) {
		if (StringUtils.isEmpty(lastAddressId)) {
			return defaultAddress(addresses);
		} else {
			for (ConsigneeAddress address : addresses) {
				String addressId = address.getId();
				if (addressId.equals(lastAddressId)) {
					return address;
				}
			}
			return defaultAddress(addresses);
		}
	}

	private ConsigneeAddress defaultAddress(List<ConsigneeAddress> addresses) {
		for (ConsigneeAddress address : addresses) {
			if ("1".equals(address.getIsd())) {
				return address;
			}
		}
		return addresses.get(0);
	}

	/**
	 * 
	 * @Title: list
	 * @Description:购物车列表
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年4月20日
	 */
	/*@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request, String isChecked) {
		User user = getSessionUser(request);
		String userId = user.getUserid();
		logger.debug("User ID:" + userId);
		ShopCartItem shopCartItem = bizCartService.showCart(userId, isChecked);
		if ((StringUtils.isEmpty(shopCartItem.getCartList()) || shopCartItem.getCartList().size() == 0) && (StringUtils.isEmpty(shopCartItem.getAbroad()) || shopCartItem.getAbroad().size() == 0)) {
			List<RecProduct> productList = bizCartService.findRecProductObj(user.getUserid(), Constants.STRING_1, Constants.STRING_1, "12");
			if (null != productList && productList.size() > 0) {
				List<List<RecProduct>> groupList = ArithmeticUtil.createList(productList, 3);
				model.addAttribute("groupList", groupList);
			}
			return CART_LIST_NULL;
		}
		model.addAttribute("shopCartItem", shopCartItem);
		return NEW_CART_LIST;
	}*/
	
	/**
	 * 进入购物车页面(查询购物车) 520
	 * @param model
	 * @param request
	 * @param isChecked
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request, String isChecked){
		User user = getSessionUser(request);
		String userId = user.getUserid();
		logger.debug("User ID:" + userId);
		Cart cart=bizCartService.doShowCartV2(userId, isChecked);
		//购物车里为空时返回推荐数据
		if ((StringUtils.isEmpty(cart.getCartList()) || cart.getCartList().size() == 0)) {
			List<RecProduct> productList = bizCartService.findRecProductObj(userId, Constants.STRING_1, Constants.STRING_1, "12");
			if (null != productList && productList.size() > 0) {
				List<List<RecProduct>> recList = ArithmeticUtil.createList(productList, 3);
				model.addAttribute("groupList", recList);
			}
			return CART_LIST_NULL;
		}
		model.addAttribute("cart",cart);
		return NEW_CART_LIST;
	}
	
	/**
	 * 修改购物车商品数量(520)
	 * @param cartItem
	 * @param isChecked
	 * @param cartSpuNo
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(String cartItem, String isChecked,String cartSpuNo,Model model, HttpServletRequest request) throws Exception{
		User user=getSessionUser(request);
		String userId=user.getUserid();
		logger.debug("User ID:" + user.getUserid());
		//增加尚品客的参数channelNo channelId(非必须)
		HttpSession session=request.getSession();
		String channelNo = (String) session.getAttribute(Constants.THRID_TOKEN_ChannelNo);
    	String channelId = (String) session.getAttribute(Constants.THRID_TOKEN_ChannelId);
    	String spuNo = (String) session.getAttribute(Constants.THRID_TOKEN_SpuNo);
    	//获取session存储的加入购物车的spu
    	String cartSpu = (String) session.getAttribute(Constants.THRID_TOKEN+cartSpuNo);
    	boolean isTopicSpu = false;
    	boolean isSpu = false;
    	//验证商品是否是那件spu商品
    	if(StringUtil.isNotEmpty(cartSpu,channelNo,channelId)){
    		isTopicSpu = true;
    	}
    	if(StringUtil.isNotEmpty(spuNo,channelNo,channelId)){
    		if(spuNo.equals(cartSpuNo)){
    			isSpu = true;
    		}
    	}
    	Map<String, Object> cartMap = null;
    	if(isTopicSpu||isSpu){
    		cartMap = bizCartService.doModifyCartV2(userId, cartItem, isChecked, channelNo, channelId);
    	}else{
    		cartMap = bizCartService.doModifyCartV2(userId, cartItem, isChecked, null, null);
    	}
    	
    	String code=(String)cartMap.get("code");
    	if(Constants.SUCCESS.equals(code)){
    		Cart cart=(Cart)cartMap.get("cart");
    		model.addAttribute("cart",cart);
    	}else{
    		model.addAttribute("cart",null);
    	}
    	return MODIFY;
    	
	}
	
	/**
	 * 删除购物车商品(520)
	 * @param cartDetailId
	 * @param isChecked
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(String cartDetailId,String isChecked,Model model, HttpServletRequest request) throws Exception{
		User user=getSessionUser(request);
		String userId=user.getUserid();
		logger.debug("User ID:" + user.getUserid());
		Map<String,Object> map=bizCartService.doDeleteCartV2(userId, cartDetailId, isChecked);
		
		String code=(String)map.get("code");
		if(Constants.SUCCESS.equals(code)){
			Cart cart=(Cart)map.get("cart");
			model.addAttribute("cart",cart);
		}else{
			model.addAttribute("cart",null);
		}
		return MODIFY;
	}

	/**
	 * 购物车页面单选中商品时访问(520)
	 * @param isChecked
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateShowCart", method = RequestMethod.GET)
	public String updateShowCart(String isChecked, Model model, HttpServletRequest request) throws Exception {
		User user=getSessionUser(request);
		String userId=user.getUserid();
		logger.debug("User ID:" + userId);
		Cart cart=bizCartService.doShowCartV2(userId, isChecked);
		model.addAttribute("cart",cart);
		return MODIFY;
	}
	
	/*@RequestMapping(value = "/updateShowCart", method = RequestMethod.GET)
	public String updateShowCart(String isChecked, Model model, HttpServletRequest request) {
		User user = getSessionUser(request);
		logger.debug("User ID:" + user.getUserid());
		String userId = user.getUserid();
		ShopCartItem shopCartItem = bizCartService.showCart(userId, isChecked);
		model.addAttribute("shopCartItem", shopCartItem);
		return MODIFY;
	}*/

	/**
	 * 
	 * @Title: delete
	 * @Description:新版删除购物车
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年4月25日
	 */
	/*@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(String userId, String cartDetailId, String isChecked, Model model, HttpServletRequest request) {
		User user = getSessionUser(request);
		logger.debug("User ID:" + user.getUserid());
		Map<String, Object> cartMap = bizCartService.delCart(user.getUserid(), cartDetailId, isChecked);
		String code = (String)cartMap.get("code");
		if(Constants.SUCCESS.equals(code)){
			model.addAttribute("shopCartItem", cartMap.get("shopCartItem"));
		}else {
			model.addAttribute("shopCartItem", null);
		}
		return MODIFY;
	}
*/
	/**
	 * 
	 * @Title: delete
	 * @Description:新版删除购物车
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年4月25日
	 */
	/*@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(String userId, String cartItem, String isChecked,String region, String cartSpuNo, Model model, HttpServletRequest request) {
		User user = getSessionUser(request);
		logger.debug("User ID:" + user.getUserid());
		//增加商品客的参数channelNo channelId
		HttpSession session = request.getSession();
		String channelNo = (String) session.getAttribute(Constants.THRID_TOKEN_ChannelNo);
    	String channelId = (String) session.getAttribute(Constants.THRID_TOKEN_ChannelId);
    	String spuNo = (String) session.getAttribute(Constants.THRID_TOKEN_SpuNo);
    	//获取session存储的加入购物车的spu
    	String cartSpu = (String) session.getAttribute(Constants.THRID_TOKEN+cartSpuNo);
    	boolean isTopicSpu = false;
    	boolean isSpu = false;
    	//验证商品是否是那件spu商品
    	if(StringUtil.isNotEmpty(cartSpu,channelNo,channelId)){
    		isTopicSpu = true;
    	}
    	if(StringUtil.isNotEmpty(spuNo,channelNo,channelId)){
    		if(spuNo.equals(cartSpuNo)){
    			isSpu = true;
    		}
    	}
    	Map<String, Object> cartMap = null;
    	if(isTopicSpu||isSpu){
    		cartMap = bizCartService.modifyCart(user.getUserid(), cartItem, isChecked,region,channelNo,channelId);
    	}else{
    		cartMap = bizCartService.modifyCart(user.getUserid(), cartItem, isChecked,region);
    	}
		String code = (String)cartMap.get("code");
		if(Constants.SUCCESS.equals(code)){
			ShopCartItem shopCartItem = (ShopCartItem)cartMap.get("shopCartItem");
			model.addAttribute("shopCartItem", shopCartItem);
		}else {
			model.addAttribute("shopCartItem", null);
		}
		return MODIFY;
	}*/
	
	/**
	 * 购物车为空
	 * @return
	 */
	@RequestMapping(value = "/no", method = RequestMethod.GET)
	public String noProduct(){
		return CART_LIST_NULL;
	}
}
