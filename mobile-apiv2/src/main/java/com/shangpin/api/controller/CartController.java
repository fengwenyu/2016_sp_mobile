package com.shangpin.api.controller;

import com.shangpin.biz.service.ASPBizCartService;
import com.shangpin.utils.StringUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户端购物车Controller
 *
 * @author huangxiaoliang
 *
 */
@Controller
@RequestMapping("/")
public class CartController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ASPBizCartService aspBizCartService;

	/**
	 * 用于选中、取消选中显示购物车商品的接口
	 *
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/showCart", method = { RequestMethod.POST, RequestMethod.GET })
	public String showCart(@RequestParam String isChecked) {
		final String imei = request.getHeader("imei");
		final String userId = request.getHeader("userid");
		if (!StringUtil.isNotEmpty(imei, userId)) {
			return returnParamError();
		}

		try {
			return aspBizCartService.doShowCart(userId, isChecked);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}

	/**
	 * 用于修改购物车商品数量后展示的接口
	 *
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyCart", method = { RequestMethod.POST, RequestMethod.GET })
	public String modifyCart(@RequestParam String cartItem, @RequestParam String isChecked, @RequestParam(defaultValue = "1", required = false) String region) {
		final String imei = request.getHeader("imei");
		final String userId = request.getHeader("userid");
		if (!StringUtil.isNotEmpty(imei, userId, cartItem)) {
			return returnParamError();
		}

		try {
			return aspBizCartService.doModifyCart(userId, cartItem, isChecked,region);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}

	/**
	 * 用于删除购物车商品后展示的接口
	 *
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCart", method = { RequestMethod.POST, RequestMethod.GET })
	public String deleteCart(@RequestParam String cartDetailId, @RequestParam String isChecked) {
		final String imei = request.getHeader("imei");
		final String userId = request.getHeader("userid");
		if (!StringUtil.isNotEmpty(imei, userId, cartDetailId)) {
			return returnParamError();
		}

		try {
			return aspBizCartService.doDeleteCart(userId, cartDetailId, isChecked);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}


	///////////////////////////////////////////////////////////////////////////////////////////////////////
	////
	////									520版本接口
	////
	///////////////////////////////////////////////////////////////////////////////////////////////////////


	/**
	 * 用于选中、取消选中显示购物车商品的接口
	 *
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/showCartV2", method = { RequestMethod.POST, RequestMethod.GET })
	public String showCartV2(
			@RequestHeader("userid") String userId,
			@RequestHeader("imei") String imei,
			@RequestParam("isChecked") String isChecked) {
		if (!StringUtil.isNotEmpty(imei, userId)) {
			return returnParamError();
		}

		try {
			return aspBizCartService.doShowCartV2(userId, isChecked);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}

	/**
	 * 用于修改购物车商品数量后展示的接口
	 *
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyCartV2", method = { RequestMethod.POST, RequestMethod.GET })
	public String modifyCartV2(
			@RequestHeader("userid") String userId,
			@RequestHeader("imei") String imei,
			@RequestParam String cartItem,
			@RequestParam String isChecked) {
		if (!StringUtil.isNotEmpty(imei, userId, cartItem)) {
			return returnParamError();
		}

		try {
			return  aspBizCartService.doModifyCartV2(userId, cartItem, isChecked);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}

	/**
	 * 用于删除购物车商品后展示的接口
	 *
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCartV2", method = { RequestMethod.POST, RequestMethod.GET })
	public String deleteCartV2(
			@RequestHeader("userid") String userId,
			@RequestHeader("imei") String imei,
			@RequestParam String cartDetailId,
			@RequestParam String isChecked) {
		if (!StringUtil.isNotEmpty(imei, userId, cartDetailId)) {
			return returnParamError();
		}

		try {
			return aspBizCartService.doDeleteCartV2(userId, cartDetailId, isChecked);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}

/*	*//**
	 * 添加商品到购物车的接口  在之前的接口修改
	 *
	 * @param
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = "/addCartV2", method = { RequestMethod.POST, RequestMethod.GET })
	public String addCartV2(
			@RequestHeader("userid") String userId,
			@RequestHeader("imei") String imei,
			@RequestParam("productNo") String productNo,
			@RequestParam("skuNo") String skuNo,
			@RequestParam("quantity") String quantity,
			@RequestParam("categoryNo") String categoryNo
	) {

		if (!StringUtil.isNotEmpty(imei, userId, productNo, skuNo, quantity, categoryNo)) {
			return returnParamError();
		}

		//TODO
		String shopType = "1"; ///来源 1尚品


		Map<String, String> map = new HashMap<>();
		map.put("productNo", productNo);
		map.put("quantity", quantity);
		map.put("skuNo", skuNo);
		map.put("dynamicattributetext", "");
		if(StringUtils.isBlank(categoryNo)){
			map.put("categoryNo", "0");
			map.put("topicSubjectFlag", "1");
		}else{
			map.put("categoryNo", categoryNo);
			if("1".equals(shopType)){
				map.put("topicSubjectFlag", "2");
			}else{
				map.put("topicSubjectFlag", "1");
			}
		}
		map.put("skuFrom", "3");
		map.put("vipNo", "0");
		map.put("userId", userId);
		map.put("siteNo", shopType);

		try {
			return aspBizCartService.doAddCartV2(map);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}*/


}
