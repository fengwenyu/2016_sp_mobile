
package com.shangpin.wireless.api.api2client.domain;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.api2server.domain.SettlementServerOrderVO;
import com.shangpin.wireless.api.domain.AlPromotionVO;
import com.shangpin.wireless.api.domain.ProductAllCartVO;
import com.shangpin.wireless.api.domain.ProductCartVO;
import com.shangpin.wireless.api.domain.ProductPropVO;
import com.shangpin.wireless.api.domain.SpPromotionVO;
import com.shangpin.wireless.api.util.StringUtil;

/**
 * 返给客户端的json数据
 * 
 * @Author: wangfeng
 * @CreatDate: 2014-04-24
 * @Return
 */

public class SettlementOrderAPIResponse {

	/**
	 * 返给客户端的json数据新版本
	 * 
	 * @Author: wangfeng
	 * @CreatDate: 2014-04-24
	 * @Return
	 */
	public String objJson(SettlementServerOrderVO settlementServerOrderVO, JSONArray onlinePaymentArray) {
		JSONObject obj = new JSONObject();
		obj.put("code", settlementServerOrderVO.getCode());
		obj.put("msg", settlementServerOrderVO.getMsg());
		JSONObject content = new JSONObject();
		if ("0".equals(settlementServerOrderVO.getCode())) {
			Double totalAmount = 0.0;
			SettlmentOrderVO settlmentOrderVO = settlementServerOrderVO.getSettlmentOrderVO();
			totalAmount += Double.parseDouble(settlmentOrderVO.getAmount());
			content.put("codincart", settlmentOrderVO.getCodincart());
			content.put("lastDeliveryType", settlmentOrderVO.getLastDeliveryType());
			content.put("lastInvoiceTitle", settlmentOrderVO.getLastInvoiceTitle());
			content.put("lastReceiveId", settlmentOrderVO.getLastReceiveId());
			content.put("giftCardBalance", settlmentOrderVO.getGiftCardBalance());
			content.put("payment", onlinePaymentArray);
			CarriageVO carriageVO = settlmentOrderVO.getCarriage();
			JSONObject carriageObj = new JSONObject();
			if (carriageVO != null) {
				totalAmount += Double.parseDouble(carriageVO.getAmount());
				carriageObj.put("amount", "20");
				carriageObj.put("reduction", carriageVO.getReductionAmount());
				carriageObj.put("desc", carriageVO.getDesc());
			}
			content.put("carriage", carriageObj);
			LastPaymentVO lastPaymentVO = settlmentOrderVO.getLastPayment();
			JSONObject lastPayMentObj = new JSONObject();
			if (lastPaymentVO != null) {
				lastPayMentObj.put("mainPayCode", lastPaymentVO.getMainPayCode());
				lastPayMentObj.put("subPayCode", lastPaymentVO.getSubPayCode());
			}
			content.put("lastPayMent", lastPayMentObj);
			List<AddressVO> receive = settlmentOrderVO.getReceive();
			JSONArray receiveArray = new JSONArray();
			if (receive != null & receive.size() > 0) {
				receiveArray = JSONArray.fromObject(receive);
			}
			content.put("receive", receiveArray);

			List<AddressVO> invoiceaddrs = settlmentOrderVO.getInvoiceaddrs();
			JSONArray invoiceaddrsArray = new JSONArray();
			if (invoiceaddrs != null & invoiceaddrs.size() > 0) {
				invoiceaddrsArray = JSONArray.fromObject(invoiceaddrs);
			}
			content.put("invoice", invoiceaddrsArray);

			List<CouponVO> couponList = settlmentOrderVO.getCoupon();
			JSONArray couponArray = new JSONArray();
			if (couponList != null & couponList.size() > 0) {
				couponArray = JSONArray.fromObject(couponList);
			}
			content.put("coupon", couponArray);

			JSONArray list = new JSONArray();
			JSONObject productJsonObject;
			ProductAllCartVO productAllCartVO = settlmentOrderVO.getProductAllCartVO();
			if (productAllCartVO != null) {
				List<AlPromotionVO> alPromotionVOList = productAllCartVO.getAlPromotionVO();
				List<SpPromotionVO> spPromotionVOList = productAllCartVO.getSpPromotionVO();
				List<ProductCartVO> productList = null;
				AlPromotionVO alPromotionVO = null;
				SpPromotionVO spPromotionVO = null;
				ProductCartVO productCartVO = null;
				List<ProductPropVO> propList;
				if (alPromotionVOList != null && alPromotionVOList.size() > 0) {
					for (int i = 0; i < alPromotionVOList.size(); i++) {
						alPromotionVO = alPromotionVOList.get(i);
						productList = alPromotionVO.getProductCartVO();
						if (productList != null && productList.size() > 0) {
							for (int j = 0; j < productList.size(); j++) {
								productJsonObject = new JSONObject();
								productCartVO = productList.get(j);
								productJsonObject.put("sku", productCartVO.getSkuNo());
								productJsonObject.put("productNo", productCartVO.getProductNo());
								productJsonObject.put("brandName", productCartVO.getBrandName());
								productJsonObject.put("productName", productCartVO.getProductName());
								productJsonObject.put("count", productCartVO.getQuantity());
								productJsonObject.put("price", productCartVO.getPrice());
								productJsonObject.put("amount", productCartVO.getTotalQuantityAmount());
								productJsonObject.put("shopDetailId", productCartVO.getShopDetailId());
								productJsonObject.put("pic", productCartVO.getImg());
								propList = productCartVO.getSkuAttrText();
								JSONArray prop = new JSONArray();
								if (propList != null && propList.size() > 0) {
									for (int z = 0; z < propList.size(); z++) {
										JSONObject propObj = new JSONObject();
										propObj.put("name", propList.get(z).getName());
										propObj.put("value", propList.get(z).getValue());
										prop.add(propObj);
									}
								}
								productJsonObject.put("prop", prop);
								list.add(productJsonObject);
							}
						}
					}
				} else if (spPromotionVOList != null && spPromotionVOList.size() > 0) {
					for (int i = 0; i < spPromotionVOList.size(); i++) {
						spPromotionVO = spPromotionVOList.get(i);
						productList = spPromotionVO.getProductCartVO();
						if (productList != null && productList.size() > 0) {
							for (int j = 0; j < productList.size(); j++) {
								productJsonObject = new JSONObject();
								productCartVO = productList.get(j);
								productJsonObject.put("sku", productCartVO.getSkuNo());
								productJsonObject.put("brandName", productCartVO.getBrandName());
								productJsonObject.put("productNo", productCartVO.getProductNo());
								productJsonObject.put("productName", productCartVO.getProductName());
								productJsonObject.put("count", productCartVO.getQuantity());
								productJsonObject.put("price", productCartVO.getPrice());
								productJsonObject.put("amount", productCartVO.getTotalQuantityAmount());
								productJsonObject.put("shopDetailId", productCartVO.getShopDetailId());
								productJsonObject.put("pic", productCartVO.getImg());
								productJsonObject.put("countryPic", productCartVO.getCountryPic());
								propList = productCartVO.getSkuAttrText();
								JSONArray prop = new JSONArray();
								if (propList != null && propList.size() > 0) {
									for (int z = 0; z < propList.size(); z++) {
										JSONObject propObj = new JSONObject();
										propObj.put("name", propList.get(z).getName());
										propObj.put("value", propList.get(z).getValue());
										prop.add(propObj);
									}
								}
								productJsonObject.put("prop", prop);
								list.add(productJsonObject);
							}
						}
					}
				}

			}
			String promoAmount = settlmentOrderVO.getPromoAmount();
			content.put("list", list);
			content.put("promoAmount", promoAmount);
			content.put("totalAmount", settlmentOrderVO.getAmount());
			if (StringUtils.isNotBlank(promoAmount)) {
				BigDecimal t = new BigDecimal(Double.toString(totalAmount));
				BigDecimal p = new BigDecimal(promoAmount);
				totalAmount = t.subtract(p).doubleValue();
			}
			content.put("payAmount", StringUtil.cutOffDecimal(String.valueOf(totalAmount)));
			obj.put("content", content);
		}
		return obj.toString();
	}

	/**
	 * 返给客户端的json数据新版本(2.6.5版本以上)
	 * 
	 * @Author: wangfeng
	 * @CreatDate: 2015-03-20
	 * @Return
	 */
	public String objJsonNew(SettlementServerOrderVO settlementServerOrderVO, JSONArray onlinePaymentArray, boolean isLastPay, String postArea) {
		JSONObject obj = new JSONObject();
		obj.put("code", settlementServerOrderVO.getCode());
		obj.put("msg", settlementServerOrderVO.getMsg());
		JSONObject content = new JSONObject();
		if ("0".equals(settlementServerOrderVO.getCode())) {
			Double totalAmount = 0.0;
			SettlmentOrderVO settlmentOrderVO = settlementServerOrderVO.getSettlmentOrderVO();
			totalAmount += Double.parseDouble(settlmentOrderVO.getAmount());
			content.put("codFlag", settlmentOrderVO.getCodincart());
			content.put("lastDeliveryTyp", settlmentOrderVO.getLastDeliveryType());
			content.put("lastInvoiceTitle", settlmentOrderVO.getLastInvoiceTitle());
			content.put("lastInvoiceFlag", settlmentOrderVO.getLastInvoiceFlag());
			content.put("lastReceiveId", settlmentOrderVO.getLastReceiveId());
			content.put("giftCardBalance", settlmentOrderVO.getGiftCardBalance());
			content.put("totalSettlement", settlmentOrderVO.getTotalSettlement());
			content.put("lastInvoiceContent", "");
			content.put("payment", onlinePaymentArray);
			CarriageVO carriageVO = settlmentOrderVO.getCarriage();
			JSONObject carriageObj = new JSONObject();
			if (carriageVO != null) {
				totalAmount += Double.parseDouble(carriageVO.getAmount());
				carriageObj.put("amount", carriageVO.getAmount());
				carriageObj.put("reductionAmount", carriageVO.getReductionAmount());
				carriageObj.put("desc", carriageVO.getDesc());
			}
			content.put("carriage", carriageObj);
			LastPaymentVO lastPaymentVO = settlmentOrderVO.getLastPayment();
			JSONObject lastPayMentObj = new JSONObject();
			if (isLastPay) {
				if ("2".equals(postArea)) {
					lastPayMentObj.put("mainPayCode", "30");
					lastPayMentObj.put("subPayCode", "107");
				} else {
					lastPayMentObj.put("mainPayCode", "27");
					lastPayMentObj.put("subPayCode", "57");
				}
			} else {
				if (lastPaymentVO != null) {
					lastPayMentObj.put("mainPayCode", lastPaymentVO.getMainPayCode());
					lastPayMentObj.put("subPayCode", lastPaymentVO.getSubPayCode());
				}
			}
			content.put("lastPayType", lastPayMentObj);
			
			List<DeliveryVo> delivery = settlmentOrderVO.getDelivery();
			JSONArray deliveryArray = new JSONArray();
			if (delivery != null & delivery.size() > 0) {
				deliveryArray = JSONArray.fromObject(delivery);
			}
			content.put("delivery", deliveryArray);
			List<PriceShowVo> priceShow = settlmentOrderVO.getPriceShow();
			JSONArray priceShowArray = new JSONArray();
			if (priceShow != null & priceShow.size() > 0) {
				priceShowArray = JSONArray.fromObject(priceShow);
			}
			content.put("priceShow", priceShowArray);
			List<AddressVO> receive = settlmentOrderVO.getReceive();
			JSONArray receiveArray = new JSONArray();
			if (receive != null & receive.size() > 0) {
				receiveArray = JSONArray.fromObject(receive);
			}
			content.put("receive", receiveArray);

			List<AddressVO> invoiceaddrs = settlmentOrderVO.getInvoiceaddrs();
			JSONArray invoiceaddrsArray = new JSONArray();
			if (invoiceaddrs != null & invoiceaddrs.size() > 0) {
				invoiceaddrsArray = JSONArray.fromObject(invoiceaddrs);
			}
			content.put("invoice", invoiceaddrsArray);

			List<CouponVO> couponList = settlmentOrderVO.getCoupon();
			JSONArray couponArray = new JSONArray();
			if (couponList != null & couponList.size() > 0) {
				couponArray = JSONArray.fromObject(couponList);
			}
			content.put("coupon", couponArray);

			JSONArray list = new JSONArray();
			JSONObject productJsonObject;
			ProductAllCartVO productAllCartVO = settlmentOrderVO.getProductAllCartVO();
			if (productAllCartVO != null) {
				List<AlPromotionVO> alPromotionVOList = productAllCartVO.getAlPromotionVO();
				List<SpPromotionVO> spPromotionVOList = productAllCartVO.getSpPromotionVO();
				List<ProductCartVO> productList = null;
				AlPromotionVO alPromotionVO = null;
				SpPromotionVO spPromotionVO = null;
				ProductCartVO productCartVO = null;
				List<ProductPropVO> propList;
				if (alPromotionVOList != null && alPromotionVOList.size() > 0) {
					for (int i = 0; i < alPromotionVOList.size(); i++) {
						alPromotionVO = alPromotionVOList.get(i);
						productList = alPromotionVO.getProductCartVO();
						if (productList != null && productList.size() > 0) {
							for (int j = 0; j < productList.size(); j++) {
								productJsonObject = new JSONObject();
								productCartVO = productList.get(j);
								productJsonObject.put("sku", productCartVO.getSkuNo());
								productJsonObject.put("id", productCartVO.getProductNo());
								productJsonObject.put("brandNameEN", productCartVO.getBrandNameEN());
								productJsonObject.put("brandNameCN", productCartVO.getBrandNameCN());
								productJsonObject.put("name", productCartVO.getProductName());
								productJsonObject.put("quantity", productCartVO.getQuantity());
								productJsonObject.put("price", productCartVO.getPrice());
								productJsonObject.put("buyId", productCartVO.getShopDetailId());
								productJsonObject.put("pic", productCartVO.getImg());
								productJsonObject.put("countryPic", productCartVO.getCountryPic());
								propList = productCartVO.getSkuAttrText();
								JSONArray prop = new JSONArray();
								if (propList != null && propList.size() > 0) {
									for (int z = 0; z < propList.size(); z++) {
										JSONObject propObj = new JSONObject();
										propObj.put("name", propList.get(z).getName());
										propObj.put("value", propList.get(z).getValue());
										prop.add(propObj);
									}
								}
								productJsonObject.put("attribute", prop);
								list.add(productJsonObject);
							}
						}
					}
				} else if (spPromotionVOList != null && spPromotionVOList.size() > 0) {
					for (int i = 0; i < spPromotionVOList.size(); i++) {
						spPromotionVO = spPromotionVOList.get(i);
						productList = spPromotionVO.getProductCartVO();
						if (productList != null && productList.size() > 0) {
							for (int j = 0; j < productList.size(); j++) {
								productJsonObject = new JSONObject();
								productCartVO = productList.get(j);
								productJsonObject.put("sku", productCartVO.getSkuNo());
								productJsonObject.put("brandNameEN", productCartVO.getBrandNameEN());
								productJsonObject.put("brandNameCN", productCartVO.getBrandNameCN());
								productJsonObject.put("id", productCartVO.getProductNo());
								productJsonObject.put("name", productCartVO.getProductName());
								productJsonObject.put("quantity", productCartVO.getQuantity());
								productJsonObject.put("price", productCartVO.getPrice());
								productJsonObject.put("buyId", productCartVO.getShopDetailId());
								productJsonObject.put("pic", productCartVO.getImg());
								productJsonObject.put("priceTitle", productCartVO.getPriceTitle());
								productJsonObject.put("quantityTitle", "数量");
								productJsonObject.put("countryPic", productCartVO.getCountryPic());
								propList = productCartVO.getSkuAttrText();
								JSONArray prop = new JSONArray();
								if (propList != null && propList.size() > 0) {
									for (int z = 0; z < propList.size(); z++) {
										JSONObject propObj = new JSONObject();
										propObj.put("name", propList.get(z).getName());
										propObj.put("value", propList.get(z).getValue());
										prop.add(propObj);
									}
								}
								productJsonObject.put("attribute", prop);
								list.add(productJsonObject);
							}
						}
					}
				}

			}
			String promoAmount = settlmentOrderVO.getPromoAmount();
			content.put("list", list);
			content.put("promoAmount", promoAmount);
			content.put("totalAmount", settlmentOrderVO.getAmount());
			content.put("carriageAmount", settlmentOrderVO.getCarriageAmount());
			content.put("postArea", settlmentOrderVO.getPostArea());
			if (StringUtils.isNotBlank(promoAmount)) {
				BigDecimal t = new BigDecimal(Double.toString(totalAmount));
				BigDecimal p = new BigDecimal(promoAmount);
				totalAmount = t.subtract(p).doubleValue();
			}
			content.put("payAmount", StringUtil.cutOffDecimal(String.valueOf(totalAmount)));
			/**
			 * 2.9.5新加开始
			 * 
			 */
			content.put("couponAmount", settlmentOrderVO.getCouponAmount());
			content.put("couponIndex", settlmentOrderVO.getCouponIndex());
			content.put("couponIsUsed", settlmentOrderVO.getCouponIsUsed());
			if(settlmentOrderVO.getPayAmount()!=null&&!"".equals(settlmentOrderVO.getPayAmount())){
				content.put("payAmount", StringUtil.cutOffDecimal(String.valueOf(settlmentOrderVO.getPayAmount())));
			}
			/**
			 * 2.9.5新加结束
			 */
			obj.put("content", content);
		}
		return obj.toString();
	}

}

