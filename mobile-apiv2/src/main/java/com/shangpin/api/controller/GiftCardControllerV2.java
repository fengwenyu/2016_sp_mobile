package com.shangpin.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.GiftCardBuy;
import com.shangpin.biz.bo.GiftCardProductList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ASPBizGiftCardService;
import com.shangpin.utils.AESUtil;
import com.shangpin.utils.JSONUtils;
import com.shangpin.utils.StringUtil;

/**
 * 礼品卡功能接口 新接口 为app的订单提交失败回滚特定创建
 * 
 * @author fenwgenyu
 *
 */
@Controller
public class GiftCardControllerV2 extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(GiftCardControllerV2.class);
	@Autowired
	private ASPBizGiftCardService giftCardService;

	/**
	 * 礼品卡立即购买2.9.12以后版本
	 * 
	 * @author fengwenyu
	 */
	@ResponseBody
	@RequestMapping(value = "/giftCardBuySettlement")
	public Object buy(String skuId, String productId, String amount,String type, HttpServletRequest request, HttpServletResponse response) {
		String userId = getAppUId(request);
		final String version = request.getHeader("ver");
		if (!StringUtil.isNotEmpty(userId,productId,amount,type)) {
			return returnParamError();
		}
		try {
			ResultObjOne<GiftCardBuy> giftCardBuy = giftCardService.doGiftCardBuy(userId, skuId, productId, amount, "", type, "0", version);
			String	result = giftCardService.doGiftCardBuyToSettlementUnion(giftCardBuy,version);
			return toResult(result);
		} catch (Exception e) {
			logger.error("error:",e);
			return returnSystemError();
		}
	}
}
