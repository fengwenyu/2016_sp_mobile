package com.shangpin.biz.service.abstraction;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.ShoppingCartService;
import com.shangpin.biz.bo.*;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JSONUtils;
import com.shangpin.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBizCartService {
	public static Logger logger = LoggerFactory
			.getLogger(AbstractBizCartService.class);
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private CommonService commonService;

	public String fromAddToCart(String userId, String productNo, String quantity, String sku, String categoryNo,
			String dynamicattributetext, String topicSubjectFlag, String skuFrom, String vipNo, String siteNo) {
		String json = shoppingCartService.addToCart(userId, productNo, quantity, sku, categoryNo, dynamicattributetext,
				topicSubjectFlag, skuFrom, vipNo, siteNo);
		return json;
	}
	//重载 加入channelNo,channelId
	public String fromAddToCart(String userId, String productNo, String quantity, String sku, String categoryNo,
			String dynamicattributetext, String topicSubjectFlag, String skuFrom, String vipNo, String siteNo,String channelNo,String channelId) {
		String json = shoppingCartService.addToCart(userId, productNo, quantity, sku, categoryNo, dynamicattributetext,
				topicSubjectFlag, skuFrom, vipNo, siteNo,channelNo,channelId);
		return json;
	}

	public ResultBase beAddToCart(String userId, String productNo, String quantity, String sku, String categoryNo,
			String dynamicattributetext, String topicSubjectFlag, String skuFrom, String vipNo, String siteNo) {
		String json = fromAddToCart(userId, productNo, quantity, sku, categoryNo, dynamicattributetext,
				topicSubjectFlag, skuFrom, vipNo, siteNo);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}
	//重载 加入channelNo,channelId
	public ResultBase beAddToCart(String userId, String productNo, String quantity, String sku, String categoryNo,
			String dynamicattributetext, String topicSubjectFlag, String skuFrom, String vipNo, String siteNo,String channelNo,String channelId) {
		String json = fromAddToCart(userId, productNo, quantity, sku, categoryNo, dynamicattributetext,
				topicSubjectFlag, skuFrom, vipNo, siteNo,channelNo,channelId);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}
	public String fromCartList(String userId, String isPromotion, String width, String height, String postArea) {
		String json = shoppingCartService.cartList(userId, isPromotion, width, height);
		return json;
	}

	public ResultObjOne<CartContent> beCartList(String userId, String isPromotion, String width, String height, String postArea) {
		String json = fromCartList(userId, isPromotion, width, height, postArea);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<CartContent> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<CartContent>>() {
			});
			return result;
		}
		return null;
	}

	public String fromDelCart(String userId, String shopId) {
		String json = shoppingCartService.delCart(userId, shopId);
		return json;
	}

	public ResultBase beDelCart(String userId, String shopId) {
		String json = fromDelCart(userId, shopId);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}

	public String fromUpdateCart(String userId, String shopId, String count) {
		String json = shoppingCartService.updateCart(userId, shopId, count);
		return json;
	}

	public ResultObjOne<CartQuantity> beUpdateCart(String userId, String shopId, String count) {
		String json = fromUpdateCart(userId, shopId, count);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<CartQuantity> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<CartQuantity>>() {
			});
			return result;
		}
		return null;
	}

	public String fromSubmitCart(String userId, String shopId, String picW, String picH) {
		String json = shoppingCartService.submitCart(userId, shopId, picW, picH);
		return json;
	}

	public ResultObjOne<CartContent> beSubmitCart(String userId, String shopId, String picW, String picH) {
		String json = fromSubmitCart(userId, shopId, picW, picH);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<CartContent> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<CartContent>>() {
			});
			return result;
		}
		return null;
	}

	public String fromGetSettlement(String userId, String isPromotion, String picW, String picH, String postAresa) {
		String json = shoppingCartService.getCart(userId, isPromotion, picW, picH, postAresa);
		return json;
	}

	public ResultObjOne<Settlement> beGetSettlement(String userId, String isPromotion, String picW, String picH, String postAresa) {
		String json = fromGetSettlement(userId, isPromotion, picW, picH, postAresa);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<Settlement> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<Settlement>>() {
			});
			return result;
		}
		return null;
	}

	public String fromRecProduct(String userId, String type, String shopType, String pageIndex, String pageSize) {
		String json = commonService.recProduct(userId, type, shopType, pageIndex, pageSize);
		return json;
	}

	public ResultObjMapList<RecProduct> beRecProduct(String userId, String type, String shopType, String pageIndex,
			String pageSize) {
		String json = fromRecProduct(userId, type, shopType, pageIndex, pageSize);
		if (!StringUtils.isEmpty(json)) {
			ResultObjMapList<RecProduct> result = JsonUtil.fromJson(json,
					new TypeToken<ResultObjMapList<RecProduct>>() {
					});
			return result;
		}
		return null;
	}
	public ResultObjMapList<RecProduct> fromRecProductList(String userId, String type, String shopType,String productId, String pageIndex,
			String pageSize) {
		String json = commonService.recProduct(userId, type, shopType,productId, pageIndex, pageSize);
		if (!StringUtils.isEmpty(json)) {
			ResultObjMapList<RecProduct> result = JsonUtil.fromJson(json,
					new TypeToken<ResultObjMapList<RecProduct>>() {
					});
			return result;
		}
		return null;
	}
	
	protected String doBaseShowCart(String userId, String isChecked) {
		return shoppingCartService.showCart(userId, isChecked);
	}
	
	protected String doBaseModifyCart(String userId, String cartItem, String isChecked, String region) {
		return shoppingCartService.modifyCart(userId, cartItem, isChecked,region);
	}
	
	protected String doBaseDeleteCart(String userId, String cartDetailId, String isChecked) {
		return shoppingCartService.deleteCart(userId, cartDetailId, isChecked);
	}

	protected String doBaseShowCartV2(String userId, String isChecked) {
			return shoppingCartService.showCartV2(userId, isChecked);

	}

	protected String doBaseModifyCartV2(String userId, String cartItem, String isChecked,String channelNo, String channelId) {
			return shoppingCartService.modifyCartV2(userId, cartItem, isChecked,channelNo, channelId);

	}

	protected String doBaseDeleteCartV2(String userId, String cartDetailId, String isChecked) {
			return shoppingCartService.deleteCartV2(userId, cartDetailId, isChecked);
		
	}

	public  Map<String, Object> doDelCart(String userId, String cartDetailId, String isChecked){
		 Map<String, Object> map=new HashMap<String, Object>();
		try {
			String json = shoppingCartService.deleteCart(userId, cartDetailId,isChecked);
			logger.debug("调用base接口返回数据:" + json);
			ResultObjOne<ShopCartItem> obj = JSONUtils
					.toGenericsCollection(json, ResultObjOne.class,
							ShopCartItem.class);
			String code = obj.getCode();
			map.put("code", code);
			map.put("msg", obj.getMsg());
			if (!Constants.SUCCESS.equals(code) || null == obj) {
				return map;
			}
			map.put("shopCartItem", obj.getObj());
			return map;
		} catch (Exception e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		}
		return null;
	}
	public  Map<String, Object> fromModifyCart(String userId, String cartItem, String isChecked,String region){
		 Map<String, Object> map=new HashMap<String, Object>();
		try {
			String json = shoppingCartService.modifyCart(userId, cartItem, isChecked,region);
			logger.debug("调用base接口返回数据:" + json);
			ResultObjOne<ShopCartItem> obj = JSONUtils
					.toGenericsCollection(json, ResultObjOne.class,
							ShopCartItem.class);
			String code = obj.getCode();
			map.put("code", code);
			map.put("msg", obj.getMsg());
			if (!Constants.SUCCESS.equals(code) || null == obj) {
				return map;
			}
			map.put("shopCartItem", obj.getObj());
			return map;
		} catch (Exception e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		}
		return null;
	}
	public  Map<String, Object> fromModifyCart(String userId, String cartItem, String isChecked,String region,String channelNo,String channelId){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			String json = shoppingCartService.modifyCart(userId, cartItem, isChecked,region, channelNo, channelId);
			logger.debug("调用base接口返回数据:" + json);
			ResultObjOne<ShopCartItem> obj = JSONUtils
					.toGenericsCollection(json, ResultObjOne.class,
							ShopCartItem.class);
			String code = obj.getCode();
			map.put("code", code);
			map.put("msg", obj.getMsg());
			if (!Constants.SUCCESS.equals(code) || null == obj) {
				return map;
			}
			map.put("shopCartItem", obj.getObj());
			return map;
		} catch (Exception e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		}
		return null;
	}
	
	public ShopCartItem fromShowCart(String userId, String isChecked){
		try {
			String json = shoppingCartService.showCart(userId, isChecked);
			logger.debug("调用base接口返回数据:" + json);
			ResultObjOne<ShopCartItem> obj = JSONUtils
					.toGenericsCollection(json, ResultObjOne.class,
							ShopCartItem.class);
			String code = obj.getCode();
			if (!Constants.SUCCESS.equals(code) || null == obj) {
				return null;
			}
			return obj.getObj();
		} catch (Exception e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		}
		return null;
	}
}
