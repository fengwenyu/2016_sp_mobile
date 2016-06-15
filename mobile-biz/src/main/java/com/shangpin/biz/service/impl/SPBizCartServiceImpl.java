package com.shangpin.biz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.CartContent;
import com.shangpin.biz.bo.CartQuantity;
import com.shangpin.biz.bo.RecProduct;
import com.shangpin.biz.bo.Settlement;
import com.shangpin.biz.bo.ShopCartItem;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.bo.cart.Cart;
import com.shangpin.biz.service.SPBizCartService;
import com.shangpin.biz.service.abstraction.AbstractBizCartService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JSONUtils;

/**
 * @ClassName: BizCartServiceImpl
 * @Description:购物车接口的实现类
 * @author qinyingchun
 * @date 2014年11月5日
 * @version 1.0
 */
@Service
public class SPBizCartServiceImpl extends AbstractBizCartService implements SPBizCartService {

	private static final Logger logger = LoggerFactory.getLogger(SPBizCartServiceImpl.class);

	@Override
	public Map<String, Object> updateCart(String userId, String shopDetailId, String quantity) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ResultObjOne<CartQuantity> obj = this.beUpdateCart(userId, shopDetailId, quantity);
			if (obj != null) {
				map.put("code", obj.getCode());
				map.put("msg", obj.getMsg());
				CartQuantity cartQuantity = obj.getContent();
				if (cartQuantity != null) {
					map.put("conInven", cartQuantity.getInventoryQuantity());
					map.put("conCode", cartQuantity.getCode());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	@Override
	public CartContent cartList(String userId, String isPromotion, String width, String height, String postArea) {
		try {
			ResultObjOne<CartContent> obj = beCartList(userId, isPromotion, width, height, postArea);
			if (obj != null && obj.isSuccess()) {
				return obj.getObj();
			}
		} catch (Exception e) {
			logger.error("调用base购物车列表接口返回数据错误！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取生成订单的信息
	 */
	@Override
	public Settlement getCart(String userId, String isPromotion, String picW, String picH, String postAresa) {
		try {
			ResultObjOne<Settlement> obj = this.beGetSettlement(userId, isPromotion, picW, picH, postAresa);
			if (obj != null && obj.isSuccess()) {
				return obj.getObj();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isSubmitCart(String userId, String shopId, String picW, String picH) {
		try {
			ResultObjOne<CartContent> obj = beSubmitCart(userId, shopId, picW, picH);
			if (obj != null) {
				return obj.isSuccess();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<RecProduct> findRecProductObj(String userId, String type, String pageIndex, String pageSize) {
		try {
			ResultObjMapList<RecProduct> obj = beRecProduct(userId, type, Constants.SHOP_TYPE_SHANGPIN, pageIndex,
					pageSize);
			if (obj != null && obj.isSuccess()) {
				return obj.getList("productList");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ShopCartItem showCart(String userId, String isChecked) {
		ShopCartItem shopCartItem=fromShowCart(userId,isChecked);
		if(shopCartItem!=null){
			return shopCartItem;
		}
		return null;
	}

	
	
	@Override
	public Map<String,Object> delCart(String userId, String cartDetailId,
			String isChecked) {
		Map<String,Object> map=doDelCart(userId,cartDetailId,isChecked);
		return map;
	}

	@Override
	public Map<String, Object> modifyCart(String userId, String cartItem,
			String isChecked,String region) {
		Map<String,Object> map=fromModifyCart(userId,cartItem,isChecked,region);
		return map;
	}
	@Override
	public Map<String, Object> modifyCart(String userId, String cartItem,String isChecked,String region,String channelNo,String channelId) {
		Map<String,Object> map=fromModifyCart(userId,cartItem,isChecked,region,channelNo,channelId);
		return map;
	}
	
	@Override
	public Cart doShowCartV2(String userId, String isChecked){
		try{
			String json=doBaseShowCartV2(userId,isChecked);
		logger.debug("调用base接口返回数据:" + json);
		ResultObjOne<Cart> obj = JSONUtils
				.toGenericsCollection(json, ResultObjOne.class,
						Cart.class);
		if(null ==obj)
			return null;
		
		String code = obj.getCode();
		if (!Constants.SUCCESS.equals(code)) {
			return null;
		}
		return obj.getObj();
		}catch (Exception e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		}
		return null;
			
	}
	
	@Override
	public Map<String, Object> doModifyCartV2(String userId, String cartItem, String isChecked,String channelNo, String channelId) {
		Map<String, Object> map=new HashMap<String, Object>();
		try{
			String json= doBaseModifyCartV2(userId, cartItem, isChecked, channelNo, channelId);
			logger.debug("调用base接口返回数据:" + json);
			ResultObjOne<Cart> obj = JSONUtils
					.toGenericsCollection(json, ResultObjOne.class,
							Cart.class);
			if(null ==obj)
				return null;
			String code = obj.getCode();
			String msg=obj.getMsg();
			map.put("code", code);
			map.put("msg", msg);
			if (!Constants.SUCCESS.equals(code)) {
				return map;
			}
			map.put("cart", obj.getObj());
			return map;
		}catch(Exception e){
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Map<String, Object> doDeleteCartV2(String userId, String cartDetailId, String isChecked) {
		Map<String, Object> map=new HashMap<String, Object>();
		try{
			String json= doBaseDeleteCartV2(userId, cartDetailId, isChecked);
			logger.debug("调用base接口返回数据:" + json);
			ResultObjOne<Cart> obj = JSONUtils
					.toGenericsCollection(json, ResultObjOne.class,
							Cart.class);
			if(null ==obj)
				return null;
			String code = obj.getCode();
			String msg=obj.getMsg();
			map.put("code", code);
			map.put("msg", msg);
			if (!Constants.SUCCESS.equals(code)) {
				return map;
			}
			map.put("cart", obj.getObj());
			return map;
		}catch(Exception e){
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		}
		return null;
		
	}
	
}
