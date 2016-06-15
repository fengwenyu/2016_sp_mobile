package com.shangpin.biz.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.OrderService;
import com.shangpin.base.service.ShoppingCartService;
import com.shangpin.base.vo.CartOrder;
import com.shangpin.base.vo.ConsigneeAddress;
import com.shangpin.biz.bo.Address;
import com.shangpin.biz.bo.CartContent;
import com.shangpin.biz.bo.CartList;
import com.shangpin.biz.bo.CouponReturn;
import com.shangpin.biz.bo.OrderReturn;
import com.shangpin.biz.bo.Settlement;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ALBizCartService;
import com.shangpin.biz.service.abstraction.AbstractBizCartService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.ParseJsonUtil;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.utils.JsonUtil;

/**
 * @ClassName: BizCartServiceImpl
 * @Description:购物车接口的实现类
 * @author qinyingchun
 * @date 2014年11月5日
 * @version 1.0
 */
@Service
public class ALBizCartServiceImpl extends AbstractBizCartService implements ALBizCartService {

	private static final Logger logger = LoggerFactory.getLogger(ALBizCartServiceImpl.class);

	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OrderService orderService;

	@Override
	public ResultBase addToCart(String... params) {
		try {
			String json = shoppingCartService.addToCart(params);
			logger.debug("base interface add to car return data:" + json);
			if (StringUtils.isNotEmpty(json)) {
				ResultBase resultBase = JsonUtil.fromJson(json, ResultBase.class);
				return resultBase;
			}
		} catch (Exception e) {
			logger.error("base interface add to car return data occur error！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 购物车列表
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public Map<String, Object> listCart(String userId, String pich, String picw, String shopType, String isPromotion) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 调用主站返回购物车列表
		String json = shoppingCartService.listCart(userId, pich, picw, shopType, isPromotion);
		logger.debug("ListCart's json is:" + json);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;
		try {
			rootNode = mapper.readTree(json);
			String code = rootNode.path("code").asText();
			String content = rootNode.path("content").asText();
			String jsonList = "";
			if (Constants.SUCCESS.equals(code)) {
				// mapper.readValue(content, valueType);
				if (Constants.SHOP_TYPE_AOLAI.equals(shopType)) {// 奥莱
					jsonList = rootNode.path("content").path("AlList").toString();
				} else {// 尚品
					jsonList = rootNode.path("content").path("SpList").toString();
				}
				List<CartList> listCart = null;
				if (StringUtil.isNotEmpty(jsonList)) {
					JavaType javaType = ParseJsonUtil.getCollectionType(ArrayList.class, CartList.class);
					listCart = mapper.readValue(jsonList, javaType);
				}

				map.put("listCart", listCart);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 加入购物车
	 * 
	 */
	@Override
	public Map<String, Object> addToCart(String userId, String productNo, String quantity, String sku, String categoryNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		String json = shoppingCartService.addToCart(userId, productNo, quantity, sku, categoryNo);
		logger.debug("AddtoCart's json is:" + json);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;
		try {
			rootNode = mapper.readTree(json);
			String code = rootNode.path("code").asText();
			String msg = rootNode.path("msg").asText();
			map.put("code", code);
			map.put("msg", msg);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 删除购物车商品
	 */
	@Override
	public Map<String, Object> delCart(String userId, String shopId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String json = shoppingCartService.delCart(userId, shopId);
		logger.debug("DelCart's json is:" + json);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;
		try {
			rootNode = mapper.readTree(json);
			String code = rootNode.path("code").asText();
			String msg = rootNode.path("msg").asText();
			map.put("code", code);
			map.put("msg", msg);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 修改购物车商品数量
	 * 
	 */
	@Override
	public Map<String, Object> updateCart(String userId, String shopDetailId, String quantity) {
		Map<String, Object> map = new HashMap<String, Object>();
		String json = shoppingCartService.updateCart(userId, shopDetailId, quantity);
		logger.debug("UpdateCart's json is:" + json);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;
		try {
			rootNode = mapper.readTree(json);
			String code = rootNode.path("code").asText();
			String msg = rootNode.path("msg").asText();
			String conInven = rootNode.path("content").path("InventoryQuantity").asText();
			String conCode = rootNode.path("content").path("code").asText();
			map.put("code", code);
			map.put("msg", msg);
			map.put("conInven", conInven);
			map.put("conCode", conCode);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	@Override
	public CartContent cartList(String userId, String isPromotion, String width, String height) {
		try {
			String json = shoppingCartService.cartList(userId, isPromotion, width, height);
			logger.debug("调用base购物车列表接口返回数据:" + json);
			if (StringUtils.isNotEmpty(json)) {
				ResultObjOne<CartContent> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<CartContent>>() {
				});
				return obj.getObj();
			}

		} catch (Exception e) {
			logger.error("调用base购物车列表接口返回数据错误！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 提交购物车选中的商品
	 */
	@Override
	public CartContent submitCart(String userId, String shopId, String picW, String picH) {
		try {
			String json = shoppingCartService.submitCart(userId, shopId, picW, picH);
			logger.debug("SubmitCart's return json:" + json);
			ResultObjOne<CartContent> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<CartContent>>() {
			});
			if (obj != null) {
				return obj.getObj();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取生成订单的信息
	 */
	@Override
	public Settlement getCart(String userId, String isPromotion, String picW, String picH, String postArea) {
		try {
			String json = shoppingCartService.getCart(userId, isPromotion, picW, picH, postArea);
			logger.debug("GetCart's return json:" + json);
			ResultObjOne<Settlement> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<Settlement>>() {
			});
			if (obj != null) {
				return obj.getObj();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加发票地址
	 */
	@Override
	public List<Address> addInvoice(ConsigneeAddress consigneeAddress) {
		String json = commonService.addInvoiceAddress(consigneeAddress);
		logger.debug("AddInvoice's json is:" + json);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;
		try {
			rootNode = mapper.readTree(json);
			String code = rootNode.path("code").asText();
			String jsonList = rootNode.path("content").path("list").toString();
			if (Constants.SUCCESS.equals(code)) {
				JavaType javaType = ParseJsonUtil.getCollectionType(ArrayList.class, Address.class);
				List<Address> addrList = mapper.readValue(jsonList, javaType);
				return addrList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isSubmitCart(String userId, String shopId, String picW, String picH) {
		try {
			String json = shoppingCartService.submitCart(userId, shopId, picW, picH);
			logger.debug("SubmitCart's return json:" + json);
			ResultObjOne<CartContent> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<CartContent>>() {
			});
			if (Constants.SUCCESS.equals(obj.getCode())) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 提交订单
	 */
	@Override
	public OrderReturn submitOrder(CartOrder orderVO) {
		String json = orderService.submitOrder(orderVO);
		logger.debug("SubmitOrder's json is:" + json);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;//
		try {
			rootNode = mapper.readTree(json);
			String code = rootNode.path("code").asText();
			String content = rootNode.path("content").toString();
			if (Constants.SUCCESS.equals(code)) {
				OrderReturn order = mapper.readValue(content, OrderReturn.class);
				return order;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 确认订单信息时更改地址和优惠码/券内容
	 * orderSource 1：从购物车 ; 2：立即购买
	 */
	@Override
	public Map<String, Object> updateOrder(String userId, String couponFlag, String coupon, String buyGids,
			String addressId, String topicNo, String orderSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		String json = orderService.modifyConfirmOrderInfo(userId, couponFlag, coupon, buyGids, addressId, topicNo, orderSource);
		logger.debug("UpdateOrder's json is:" + json);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;
		String msgcode = "";
		try {
			rootNode = mapper.readTree(json);
			String code = rootNode.path("code").asText();
			String msg = rootNode.path("msg").asText();
			String content = rootNode.path("content").toString();
			if (Constants.SUCCESS.equals(code)) {
				CouponReturn couponVO = mapper.readValue(content, CouponReturn.class);
				msgcode = "0";// 成功
				map.put("couponVO", couponVO);
			} else {
				msgcode = "1";// 失败
				map.put("msg", msg);
			}
			map.put("msgcode", msgcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 支付方式变更
	 */
	@Override
	public Map<String, Object> changePay(String userId, String orderId, String mainPay, String subPay) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 调用主站改变支付方式
		String json = shoppingCartService.changePay(userId, orderId, mainPay, subPay);
		logger.debug("ChangePayType's json is:" + json);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;
		String msgcode = "";
		try {
			rootNode = mapper.readTree(json);
			String code = rootNode.path("code").asText();
			String msg = rootNode.path("msg").asText();
			if (Constants.SUCCESS.equals(code)) {
				msgcode = "success";// 成功
			} else {
				msgcode = "fail";// 失败
				map.put("msg", msg);
			}
			map.put("msgcode", msgcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 更新订单状态
	 */
	@Override
	public Map<String, Object> updateStatus(String mainPay, String subPay, String orderId, String payMoney) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 调主站更新订单状态
		String json = shoppingCartService.updateStatus(mainPay, subPay, orderId, payMoney);
		logger.debug("UpdateOrderStatus's json is:" + json);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;
		String msgcode = "";
		try {
			rootNode = mapper.readTree(json);
			String code = rootNode.path("code").asText();
			String msg = rootNode.path("msg").asText();
			if (Constants.SUCCESS.equals(code)) {
				msgcode = "0";// 成功
			} else {
				msgcode = "1";// 失败
				map.put("msg", msg);
			}
			map.put("msgcode", msgcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
