package com.shangpin.wireless.api.api2server.domain;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.shangpin.wireless.api.api2client.domain.AddressVO;
import com.shangpin.wireless.api.api2client.domain.CarriageVO;
import com.shangpin.wireless.api.api2client.domain.CouponVO;
import com.shangpin.wireless.api.api2client.domain.DeliveryVo;
import com.shangpin.wireless.api.api2client.domain.LastPaymentVO;
import com.shangpin.wireless.api.api2client.domain.PriceShowVo;
import com.shangpin.wireless.api.api2client.domain.SettlmentOrderVO;
import com.shangpin.wireless.api.domain.ProductAllCartVO;
import com.shangpin.wireless.api.util.StringUtil;

public class SettlementServerOrderVO {

	private String code;
	private String msg;
	private SettlmentOrderVO settlmentOrderVO;

	/**
	 * 返给客户端的json数据
	 * 
	 * @Author: wangfeng
	 * @CreatDate: 2014-04-24
	 * @Return
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public SettlementServerOrderVO jsonObj(String json) {
		JSONObject obj = JSONObject.fromObject(json);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if ("0".equals(code)) {
			obj = JSONObject.fromObject(obj.getJSONObject("content"));
			settlmentOrderVO = new SettlmentOrderVO();
			String amount = obj.getString("amount");
			settlmentOrderVO.setAmount(StringUtil.cutOffDecimal(amount));
			settlmentOrderVO.setPromoAmount(StringUtil.cutOffDecimal(obj.getString("promoAmount")));
			settlmentOrderVO.setCarriageAmount(obj.getString("carriageAmount"));
			settlmentOrderVO.setPostArea(obj.getString("postArea"));
			settlmentOrderVO.setTotalSettlement(obj.getString("totalSettlement"));
			settlmentOrderVO.setCodincart(obj.getString("codincart"));
			settlmentOrderVO.setLastInvoiceTitle(obj.getString("lastInvoiceTitle"));
			settlmentOrderVO.setLastReceiveId(obj.getString("lastReceiveId"));
			settlmentOrderVO.setLastInvoiceConsigneeID(obj.getString("lastInvoiceConsigneeID"));
			settlmentOrderVO.setLastDeliveryType(obj.getString("lastDeliveryType"));
			settlmentOrderVO.setGiftCardBalance(obj.getString("giftCardBalance"));
			settlmentOrderVO.setLastInvoiceFlag(obj.getString("lastInvoiceFlag"));
			//新增是否显示开发票显示地址2016-4-11
			settlmentOrderVO.setInvoiceDesc(obj.getString("invoiceDesc"));
			
			/**
			 * 2.9.5新加开始
			 */
			settlmentOrderVO.setCouponAmount(obj.getString("couponAmount")==null?"":obj.getString("couponAmount"));
			settlmentOrderVO.setCouponIndex(obj.getString("couponIndex")==null?"":obj.getString("couponIndex"));
			settlmentOrderVO.setCouponIsUsed(obj.getString("couponIsUsed")==null?"":obj.getString("couponIsUsed"));
			settlmentOrderVO.setPayAmount(obj.getString("payAmount")==null?"":obj.getString("payAmount"));
			/**
			 * 2.9.5新加结束
			 */
			//2.9.0新加 开始
			List<DeliveryVo> delivery = new ArrayList<DeliveryVo>();
			JSONArray deliveryArray = obj.getJSONArray("delivery");
			if (deliveryArray != null && deliveryArray.size() > 0) {
				delivery = deliveryArray.toList(deliveryArray, new DeliveryVo(), new JsonConfig());
			}
			settlmentOrderVO.setDelivery(delivery);
			//2.9.0新加 结束
			
			List<AddressVO> receive = new ArrayList<AddressVO>();
			JSONArray receiveArray = obj.getJSONArray("receive");
			if (receiveArray != null && receiveArray.size() > 0) {
				receive = receiveArray.toList(receiveArray, new AddressVO(), new JsonConfig());
			}
			settlmentOrderVO.setReceive(receive);
			List<AddressVO> invoiceaddrs = new ArrayList<AddressVO>();
			JSONArray invoiceArray = obj.getJSONArray("invoiceaddrs");
			if (invoiceArray != null && invoiceArray.size() > 0) {
				invoiceaddrs = invoiceArray.toList(invoiceArray, new AddressVO(), new JsonConfig());
			}
			settlmentOrderVO.setInvoiceaddrs(invoiceaddrs);
			JSONObject carriageObj = obj.getJSONObject("carriage");
			CarriageVO carriageVO;
			if (carriageObj != null&&!carriageObj.isEmpty()) {
				carriageVO = new CarriageVO();
				carriageVO.setAmount(carriageObj.getString("amount"));
				carriageVO.setReductionAmount(carriageObj.getString("reductionAmount"));
				carriageVO.setDesc(carriageObj.getString("desc"));
				settlmentOrderVO.setCarriage(carriageVO);
			}
			JSONObject lastPaymentObj = obj.getJSONObject("lastPayMent");
			LastPaymentVO lastPaymentVO;
			if (lastPaymentObj != null) {
				lastPaymentVO = new LastPaymentVO();
				lastPaymentVO.setMainPayCode(lastPaymentObj.getString("mainPayCode"));
				lastPaymentVO.setSubPayCode(lastPaymentObj.getString("subPayCode"));
				settlmentOrderVO.setLastPayment(lastPaymentVO);
			}
			List<CouponVO> couponList = new ArrayList<CouponVO>();
			JSONArray couponArray = obj.getJSONArray("coupon");
			if (couponArray != null && couponArray.size() > 0) {
				couponList = couponArray.toList(couponArray, new CouponVO(), new JsonConfig());
			}
			settlmentOrderVO.setCoupon(couponList);
			String listStr = obj.getString("list");
			ProductAllCartVO productAllCartVO;
			if (listStr != null) {
				productAllCartVO = new ProductAllCartVO().jsonObj(listStr);
				settlmentOrderVO.setProductAllCartVO(productAllCartVO);
			}
			//2.9.0新加 开始
			List<PriceShowVo> priceShow = new ArrayList<PriceShowVo>();
			JSONArray priceShowArray = obj.getJSONArray("priceShow");
			if (priceShowArray != null && priceShowArray.size() > 0) {
				priceShow = priceShowArray.toList(priceShowArray, new PriceShowVo(), new JsonConfig());
			}
			settlmentOrderVO.setPriceShow(priceShow);
			//2.9.0新加 结束
			
			//关税调整后新加商品裸价
			settlmentOrderVO.setProductAmount(obj.getString("productAmount")==null?"":obj.getString("productAmount"));
			settlmentOrderVO.setIsHaveDirect(obj.getString("isHaveDirect")==null?"":obj.getString("isHaveDirect"));
		}
		return this;
	}

	public SettlmentOrderVO getSettlmentOrderVO() {
		return settlmentOrderVO;
	}

	public void setSettlmentOrderVO(SettlmentOrderVO settlmentOrderVO) {
		this.settlmentOrderVO = settlmentOrderVO;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
