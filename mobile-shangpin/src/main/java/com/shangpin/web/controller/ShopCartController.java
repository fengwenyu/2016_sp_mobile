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

import com.shangpin.biz.bo.RecProduct;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.cart.Cart;
import com.shangpin.biz.bo.cart.CartList;
import com.shangpin.biz.bo.cart.CartProductList;
import com.shangpin.biz.service.SPBizCartService;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.utils.ArithmeticUtil;
import com.shangpin.web.utils.Constants;

/**
 * 520购物车控制器
 * @author zkj
 *
 */
@Controller
@RequestMapping("/shopcart")
public class ShopCartController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(ShopCartController.class);

	private static final String CART_LIST_NULL = "cart/no_product";
	private static final String CART_PAY = "cart/cart_pay";

	private static final String NEW_CART_LIST = "cart520/cartList";
	private static final String MODIFY = "cart520/modify";
	@Autowired
	private SPBizCartService bizCartService;
	
	
	/**
	 * 加入购物车
	 * @param productNo
	 * @param quantity
	 * @param sku
	 * @param topicNo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(String productNo, String quantity, String sku, String topicNo, HttpServletRequest request) {
		User user=getSessionUser(request);
		String userId=user.getUserid();
		String code="";
		String msg="";
		if(user==null||StringUtils.isEmpty(userId)){
			code=Constants.UNLOGIN;
		}else{
			String categoryNo;
			String topicSubjectFlag;
			if(!StringUtils.isEmpty(topicNo)){
				categoryNo=topicNo;
				topicSubjectFlag=Constants.BUY_TOPIC;
			}else{
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
			code=result.getCode().equals(Constants.SUCCESS)?Constants.SUCCESS:Constants.FAILE;
			msg=result.getMsg();
		}
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 进入购物车页面(查询购物车)
	 * @param model
	 * @param request
	 * @param isChecked
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request, String isChecked) throws Exception{
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
	 * 修改购物车商品数量
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
	 * 删除购物车商品
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
	 * 计算购物车商品数量
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public int count(HttpServletRequest request){
		User user=getSessionUser(request);
		String userId=user.getUserid();
		if(StringUtils.isEmpty(user)){
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
	 * 购物车页面单选中商品时访问
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
	
	/**
	 * 购物车中没有商品
	 * @return
	 */
	@RequestMapping(value = "/no", method = RequestMethod.GET)
	public String noProduct(){
		return CART_LIST_NULL;
	}
	
	

}

