package com.shangpin.base.service.impl;

import com.shangpin.base.service.ShoppingCartService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	/**加入购物车请求URL*/
	private StringBuilder addCartURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("trade/addproducttocart/");
	/**购物车列表请求URL*/
	private StringBuilder cartListURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("trade/cartlist/");
	/**删除购物车商品URL*/
	private StringBuilder delCartURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("trade/deletecartbydetailId/");
	/**修改购物车商品数量URL*/
	private StringBuilder updateCartURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("trade/updatecartbyquantity/");
	/**加入购物车*/
	private StringBuilder addToCartURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("trade/addproducttocart/");
	/**提交购物车商品URL*/
	private StringBuilder submitCartURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("trade/getordershoppingcartdetail/");
	/**获取生成订单的信息URL*/
	private StringBuilder getCartURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/SettlementOrder/");
	/**更新支付方式URL*/
	private StringBuilder changePayURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/UpdatePayType");
	/**更新订单状态URL*/
	private StringBuilder updateOrderStatusURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/UpdateOrderStatus");
	/**选中单品购物车URL*/
	private StringBuilder showCartURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("trade/showCart");
	/**修改购物车URL*/
	private StringBuilder modifyCartURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("trade/modifyCart");
	/**删除购物车URL*/
	private StringBuilder deleteCartURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("trade/deleteCart");

///////520
	/**选中单品购物车URL*/
	private StringBuilder showCartV2URL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("trade/showCartV2");
	/**修改购物车URL*/
	private StringBuilder modifyCartV2URL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("trade/modifyCartV2");
	/**删除购物车URL*/
	private StringBuilder deleteCartV2URL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("trade/deleteCartV2");

	/**
	 * 加入购物车（奥莱）
	 */
	@Override
	public String addToCart(String userId, String productNo, String quantity,
			String sku, String categoryNo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("productNo", productNo);
        map.put("quantity", quantity);
        map.put("skuNo", sku);
        map.put("dynamicattributetext", "");
        map.put("categoryNo", categoryNo);
        map.put("topicSubjectFlag", "1");
        map.put("skuFrom", "3");
        map.put("vipNo", "0");
        map.put("userId", userId);
        map.put("siteNo", "2");
        String result = "";
        try {
        	result = HttpClientUtil.doPost(addCartURL.toString(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return result;
	}

	/**
	 * 购物车列表（奥莱）
	 */
	@Override
	public String listCart(String userId, String pich, String picw,
			String shopType, String isPromotion) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userId);
        map.put("width", picw);
        map.put("height", pich);
        map.put("isPromotion", isPromotion);
        String result = HttpClientUtil.doGet(cartListURL.toString(), map);
        
		return result;
	}

	/**
	 * 删除购物车商品
	 */
	@Override
	public String delCart(String userId, String shopId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
        map.put("shopCartDetailIds", shopId);
        String result = "";
        try {
        	result = HttpClientUtil.doPost(delCartURL.toString(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return result;
	}

	/**
	 * 修改购物车商品数量
	 */
	@Override
	public String updateCart(String userId, String shopId, String count) {
		Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("shopCartDetailId", shopId);
        map.put("quantity", count);
        String result = "";
        try {
        	result = HttpClientUtil.doPost(updateCartURL.toString(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return result;
	}

	/**
	 *加入购物车 
	*/
	@Override
	public String addToCart(String... params) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", params[0]);
		map.put("productNo", params[1]);
        map.put("quantity", params[2]);
        map.put("skuNo", params[3]);
        map.put("categoryNo", params[4]);
        map.put("dynamicattributetext", params[5]);
        map.put("topicSubjectFlag", params[6]);
        map.put("skuFrom", params[7]);
        map.put("vipNo", params[8]);
        map.put("siteNo", params[9]);
        map.put("limitOverseaProductQuantity","0");
        if(params.length>10){
        	map.put("channelNo", params[10]);
        	map.put("channelId", params[11]);
        }
		return HttpClientUtil.doPost(addToCartURL.toString(), map);
	}

	@Override
	public String cartList(String userId, String isPromotion, String width,
			String height) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("isPromotion", isPromotion);
		params.put("width", width);
		params.put("height", height);
		return HttpClientUtil.doGet(cartListURL.toString(), params);
	}

	/**
	 * 提交购物车选中的商品
	 */
	@Override
	public String submitCart(String userId, String shopId, String picW,
			String picH) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
        map.put("shopCartDetailIds", shopId);
        map.put("height", picH);
        map.put("width", picW);
        String result = "";
        try {
        	result = HttpClientUtil.doGet(submitCartURL.toString(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return result;
	}

	/**
	 * 获取生成订单的信息
	 */
	@Override
	public String getCart(String userId, String isPromotion, String picW,String picH, String postArea) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userId);
        map.put("height", picH);
        map.put("width", picW);
        map.put("isPromotion",isPromotion);
        map.put("postArea",postArea);
        String result = "";
        try {
        	result = HttpClientUtil.doGet(getCartURL.toString(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return result;
	}

	/**
	 * 支付方式变更
	 */
	@Override
	public String changePay(String userId, String orderId, String mainPay,
			String subPay) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userId);
		map.put("mainorderno", orderId);
		if (GlobalConstants.MAIN_PAY.equals(mainPay)) {
			map.put("paytype", "4_" + mainPay + "_" + subPay);
		} else {
			map.put("paytype", "0_" + mainPay + "_" + subPay);
		}
        String result = "";
        try {
        	result = HttpClientUtil.doGet(changePayURL.toString(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return result;
	}

	/**
	 * 更新订单状态
	 */
	@Override
	public String updateStatus(String mainPay, String subPay, String orderId,
			String payMoney) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("payTypeId", mainPay);
		map.put("childPayTypeId", subPay);
		map.put("mainorderNo", orderId);
		map.put("orderAmount", payMoney);
        String result = "";
        try {
        	result = HttpClientUtil.doGet(updateOrderStatusURL.toString(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return result;
	}

	@Override
	public String addToCart(String userId, String productNo, String quantity, String sku, String categoryNo,
			String dynamicattributetext, String topicSubjectFlag, String skuFrom, String vipNo, String siteNo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("productNo", productNo);
        map.put("quantity", quantity);
        map.put("skuNo", sku);
        map.put("dynamicattributetext", dynamicattributetext);
        map.put("categoryNo", categoryNo);
        map.put("topicSubjectFlag", topicSubjectFlag);
        map.put("skuFrom", skuFrom);
        map.put("vipNo", vipNo);
        map.put("userId", userId);
        map.put("siteNo", siteNo);
        String result = HttpClientUtil.doPost(addCartURL.toString(), map);
		return result;
	}

	@Override
	public String showCart(String userId, String checked) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
        map.put("isChecked", checked);
		return HttpClientUtil.doGet(showCartURL.toString(), map);
	}

	@Override
	public String modifyCart(String userId, String cartItem, String checked,String region) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
        map.put("cartItem", cartItem);
        map.put("isChecked", checked);
        map.put("region", region);
		return HttpClientUtil.doGet(modifyCartURL.toString(), map);
	}
	@Override
	public String modifyCart(String userId, String cartItem, String checked,String region,String channelNo,String channelId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("cartItem", cartItem);
		map.put("isChecked", checked);
		map.put("region", region);
		map.put("channelNo", channelNo);
		map.put("channelId", channelId);
		return HttpClientUtil.doGet(modifyCartURL.toString(), map);
	}

	@Override
	public String deleteCart(String userId, String cartDetailId, String checked) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("cartDetailId", cartDetailId);
        map.put("isChecked", checked);
		return HttpClientUtil.doGet(deleteCartURL.toString(), map);
	}

/////////////////// 520 ////////////////
	@Override
	public String showCartV2(String userId, String checked) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("isChecked", checked);
		return HttpClientUtil.doGet(showCartV2URL.toString(), map);
	}


	@Override
	public String modifyCartV2(String userId, String cartItem, String checked,String channelNo, String channelId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("cartItem", cartItem);
		map.put("isChecked", checked);
		map.put("channelNo", channelNo);
		map.put("channelId", channelId);
		return HttpClientUtil.doGet(modifyCartV2URL.toString(), map);
	}


	@Override
	public String deleteCartV2(String userId, String cartDetailId, String checked) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("cartDetailId", cartDetailId);
		map.put("isChecked", checked);
		return HttpClientUtil.doGet(deleteCartV2URL.toString(), map);
	}



}
