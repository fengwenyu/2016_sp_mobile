package com.shangpin.wireless.api.util;

import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.api2client.domain.Express;
import com.shangpin.wireless.api.domain.Constants;

public class OrderDetailUtil {
	private final static String REDUCTION = "20";

	public static JSONObject getManagerOrderInfo(JSONObject sourceOrder, String userId, JSONArray payModeList) {
		JSONObject targetOrder = new JSONObject();
		JSONArray orderArray = sourceOrder.getJSONArray("order");
		JSONObject detail = new JSONObject();
		JSONArray list = new JSONArray();
		if (orderArray != null && orderArray.size() > 0) {
			boolean flag = false;
			for (int i = 0; i < orderArray.size(); i++) {
				JSONObject orderDetail = orderArray.getJSONObject(i);
				list.addAll(getManageOrder(orderDetail));
				if (!flag) {
					// 收货人信息 两个子单信息一样 所以使用其中一个就可以了
					detail.put("receive", getOrderReceive(orderDetail));
					// 收货方式
					String expressDesc = checkNull(orderDetail.getString("express"));
					detail.put("express", expressDesc); // 送货
					detail.put("title", checkNull(orderDetail.getString("title")));
					detail.put("invoice", checkNull(orderDetail.getString("invoice")));
					detail.put("paymode", checkNull(sourceOrder.getString("paytypename")));
					flag = true;
				}
			}
		}
		detail.put("list", list);
		targetOrder.put("detail", detail);
		targetOrder.put("orderid", checkNull(sourceOrder.getString("mainorderno")));
		targetOrder.put("date", checkNull(sourceOrder.getString("date")));
		targetOrder.put("amount", String.valueOf(sourceOrder.getInt("totalamount"))); // 总金额
		targetOrder.put("status", checkNull(sourceOrder.getString("status")));
		targetOrder.put("statusdesc", checkNull(sourceOrder.getString("statusname")));
		targetOrder.put("cancancel", checkTrueOrFalse(sourceOrder.getString("cancancel")));
		targetOrder.put("canpay", checkTrueOrFalse(sourceOrder.getString("canpay")));
		targetOrder.put("canconfirm", checkTrueOrFalse(sourceOrder.getString("canconfirmgoods")));
		String cod = "0";
		try {
			cod = checkTrueOrFalse(sourceOrder.getString("iscod"));
		} catch (Exception e) {
		}
		targetOrder.put("cod", cod); // 是否支出货到付款
		if (cod != null && cod.equals("0")) {
			targetOrder.put("codMsg", "无法货到付款"); // 无法货到付款的原因
		} else {
			targetOrder.put("codMsg", "");
		}

		String status = checkNull(sourceOrder.getString("status"));// 订单状态
		if (status.equals("13") || status.equals("99")) {// 收款已经确认
			targetOrder.put("canLogistics", "1");
		} else {
			targetOrder.put("canLogistics", "0");
		}
		targetOrder.put("onlineamount", String.valueOf(sourceOrder.getInt("onlineamount"))); // 线上支付金额
		targetOrder.put("payamount", String.valueOf(sourceOrder.getInt("onlineamount")));// 老版本数据可能有问题
																							// 故用这个字段适配

		String giftCardAmount = checkNull(sourceOrder.getString("giftcardamount"));
		if (giftCardAmount.indexOf(".") > 0) {
			giftCardAmount = giftCardAmount.substring(0, giftCardAmount.indexOf("."));
		}
		targetOrder.put("discount", String.valueOf(sourceOrder.getInt("discountamount"))); // 优惠折扣金额
		targetOrder.put("giftcardamount", giftCardAmount);

		JSONObject payMode = new JSONObject();// 支付方式
		payMode.put("id", checkNull(sourceOrder.getString("paytypeid")));
		payMode.put("subId", checkNull(sourceOrder.getString("paytypechildid")));
		payMode.put("name", checkNull(sourceOrder.getString("paytypename")));
		targetOrder.put("payMode", payMode);

		targetOrder.put("carriage", String.valueOf(sourceOrder.getInt("freight"))); // 运费
		return targetOrder;

	}

//	订单列表返回的数据
	public static JSONObject getManagerOldInfo(JSONObject sourceOrder, String userId, JSONArray payModeList) {
		JSONObject targetOrder = new JSONObject();
		JSONArray orderArray = sourceOrder.getJSONArray("order");
		JSONObject detail = new JSONObject();
		JSONArray list = new JSONArray();
		if (orderArray != null && orderArray.size() > 0) {
			boolean flag = false;
			for (int i = 0; i < orderArray.size(); i++) {
				JSONObject orderDetail = orderArray.getJSONObject(i);
				list.addAll(getManageOrder(orderDetail));
				if (!flag) {
					// 收货人信息 两个子单信息一样 所以使用其中一个就可以了
					detail.put("receive", getOrderReceive(orderDetail));
					// 收货方式
					String expressDesc = checkNull(orderDetail.getString("express"));
					detail.put("express", expressDesc); // 送货
					detail.put("title", checkNull(orderDetail.getString("title")));
					detail.put("invoice", checkNull(orderDetail.getString("invoice")));
					detail.put("paymode", checkNull(sourceOrder.getString("paytypename")));
					detail.put("carriage", String.valueOf(sourceOrder.getInt("freight"))); // 运费
					targetOrder.put("carriage", String.valueOf(sourceOrder.getInt("freight"))); // 运费
					flag = true;
				}
			}
		}
		detail.put("list", list);
		targetOrder.put("detail", detail);
		targetOrder.put("orderid", checkNull(sourceOrder.getString("mainorderno")));
		targetOrder.put("date", checkNull(sourceOrder.getString("date")));
		targetOrder.put("amount", String.valueOf(sourceOrder.getInt("totalamount"))); // 总金额
		targetOrder.put("status", checkNull(sourceOrder.getString("status")));
		targetOrder.put("statusdesc", checkNull(sourceOrder.getString("statusname")));
		targetOrder.put("cancancel", checkTrueOrFalse(sourceOrder.getString("cancancel")));
		targetOrder.put("canpay", checkTrueOrFalse(sourceOrder.getString("canpay")));
		targetOrder.put("postArea", sourceOrder.getString("postArea"));
		
		String cod = "0";
		try {
			cod = checkTrueOrFalse(sourceOrder.getString("iscod"));
		} catch (Exception e) {
		}
		targetOrder.put("cod", cod); // 是否支出货到付款
		if (cod != null && cod.equals("0")) {
			targetOrder.put("codMsg", "无法货到付款"); // 无法货到付款的原因
		} else {
			targetOrder.put("codMsg", "");
		}

		String status = checkNull(sourceOrder.getString("status"));// 订单状态
		if (status.equals("13") || status.equals("99")) {// 收款已经确认
			targetOrder.put("canLogistics", "1");
		} else {
			targetOrder.put("canLogistics", "0");
		}
		targetOrder.put("onlineamount", String.valueOf(sourceOrder.getInt("onlineamount"))); // 线上支付金额
		targetOrder.put("payamount", String.valueOf(sourceOrder.getInt("onlineamount")));

		String giftCardAmount = checkNull(sourceOrder.getString("giftcardamount"));
		if (giftCardAmount.indexOf(".") > 0) {
			giftCardAmount = giftCardAmount.substring(0, giftCardAmount.indexOf("."));
		}
		targetOrder.put("discount", String.valueOf(sourceOrder.getInt("discountamount"))); // 优惠折扣金额
		targetOrder.put("giftcardamount", giftCardAmount);

		JSONObject payMode = new JSONObject();// 支付方式
		payMode.put("id", checkNull(sourceOrder.getString("paytypeid")));
		payMode.put("subId", checkNull(sourceOrder.getString("paytypechildid")));
		payMode.put("name", checkNull(sourceOrder.getString("paytypename")));
		targetOrder.put("payMode", payMode);

		return targetOrder;

	}
	
	/**
	 * 收货人信息
	 * 
	 * @param orderDetail
	 * @return
	 */
	private static JSONObject getOrderReceive(JSONObject orderDetail) {
		// 收货人信息
		JSONObject receive = new JSONObject();
		receive.put("name", checkNull(orderDetail.getString("name")));
		receive.put("addr", checkNull(orderDetail.getString("addr")));
		receive.put("province", checkNull(orderDetail.getString("province")));
		receive.put("city", checkNull(orderDetail.getString("city")));
		receive.put("area", checkNull(orderDetail.getString("area")));
		receive.put("postcode", checkNull(orderDetail.getString("postcode")));
		receive.put("tel", checkNull(orderDetail.getString("tel")));
		return receive;
	}

	/**
	 * 返回订单取消信息
	 * 
	 * @param sourceOrder
	 * @return
	 */
	public static JSONObject getCancelOrderInfo(JSONObject sourceOrder) {
		JSONObject targetOrder = new JSONObject();
		targetOrder.put("amount", String.valueOf(sourceOrder.getInt("totalamount"))); // 总金额
		targetOrder.put("cancancel", checkTrueOrFalse(sourceOrder.getString("cancancel")));
		targetOrder.put("canconfirm", checkTrueOrFalse(sourceOrder.getString("canconfirmgoods")));
		targetOrder.put("canpay", checkTrueOrFalse(sourceOrder.getString("canpay")));
		targetOrder.put("carriage", String.valueOf(sourceOrder.getInt("freight"))); // 运费
		targetOrder.put("discount", String.valueOf(sourceOrder.getInt("discountamount"))); // 优惠折扣金额
		String giftCardAmount = checkNull(sourceOrder.getString("giftcardamount"));
		if (giftCardAmount.indexOf(".") > 0) {
			giftCardAmount = giftCardAmount.substring(0, giftCardAmount.indexOf("."));
		}
		targetOrder.put("giftCardAmount", giftCardAmount);
		targetOrder.put("onlineamount", String.valueOf(sourceOrder.getInt("onlineamount"))); // 线上支付金额
		targetOrder.put("orderid", checkNull(sourceOrder.getString("mainorderno")));
		targetOrder.put("payamount", String.valueOf(sourceOrder.getInt("payamount")));
		targetOrder.put("status", checkNull(sourceOrder.getString("status")));
		targetOrder.put("statusdesc", checkNull(sourceOrder.getString("statusname")));
		return targetOrder;
	}

	public static JSONObject getOrderInfo(JSONObject sourceOrder, String userId, JSONArray payModeList, String product, String version, String fromMethod) {
		boolean isEcard=false;
		JSONObject targetOrder = new JSONObject();
		targetOrder.put("canCancel", checkTrueOrFalse(sourceOrder.getString("cancancel")));
		targetOrder.put("canPay", checkTrueOrFalse(sourceOrder.getString("canpay")));
		targetOrder.put("date", checkNull(sourceOrder.getString("date")));
//		targetOrder.put("sysTime", System.currentTimeMillis());
//		targetOrder.put("expiryDate", DateUtil.getDateNum("yyyy-MM-dd HH:mm:ss", checkNull(sourceOrder.getString("expirydate"))));
		targetOrder.put("orderId", checkNull(sourceOrder.getString("mainorderno")));
		String status = checkNull(sourceOrder.getString("status"));// 订单状态
		targetOrder.put("status", status);
		targetOrder.put("statusDesc", sourceOrder.getString("statusname"));
		targetOrder.put("isusablegiftcardpay",sourceOrder.get("isusablegiftcardpay")==null?"":sourceOrder.getString("isusablegiftcardpay"));
		targetOrder.put("isEverUsedGiftCard", sourceOrder.get("isEverUsedGiftCard")==null?"":sourceOrder.getString("isEverUsedGiftCard"));

		//2.9.12添加开始
		targetOrder.put("payCategory", sourceOrder.get("payCategory")==null?"":sourceOrder.getString("payCategory"));
		targetOrder.put("internalAmount", sourceOrder.get("internalAmount")==null?"":sourceOrder.getString("internalAmount"));
		//2.9.12添加结束

//		app3.0以后主单加国内/海外订单的描述
//		targetOrder.put("postArea", checkNull(sourceOrder.getString("postArea")));
//		targetOrder.put("postAreaDesc", checkNull(sourceOrder.getString("postAreaDesc")));
		
//		对系统时间的矫正，保证倒计时为一个小时
		long sysTime = System.currentTimeMillis();
		long expiryDate = DateUtil.getDateNum("yyyy-MM-dd HH:mm:ss", checkNull(sourceOrder.getString("expirydate")));
		if ((expiryDate != 0) && ((expiryDate-sysTime-3600000) > 0)){
			sysTime = expiryDate-3600000;
		}
		targetOrder.put("sysTime", sysTime);
		targetOrder.put("expiryDate", expiryDate);
		// 处理礼品卡--电子卡不让显示物流跟踪
		JSONArray orderList = sourceOrder.getJSONArray("order");
		JSONObject giftCardObj = orderList.getJSONObject(0);
		JSONArray productList = giftCardObj.getJSONArray("orderdetail");
		if (StringUtil.compareVer(version, "2.7.0") >= 0 && giftCardObj != null && !productList.isEmpty() && "1".equals(productList.getJSONObject(0).get("type"))) {
			// 处理礼品卡--电子卡不让显示物流跟踪
			targetOrder.put("canLogistics", "0");
			//用来标志是电子卡订单
			isEcard=true;
		} else {
			if ("12".equals(status) || "13".equals(status) || "14".equals(status) || "15".equals(status) || "16".equals(status) || "18".equals(status) || "98".equals(status) || "99".equals(status)) {
				targetOrder.put("canLogistics", "1");
			} else {
				targetOrder.put("canLogistics", "0");
			}
		}
		
//		2.7.0及以前版本的的ios,订单详情里不显示确认收货
		if("orderDetail".equals(fromMethod) && "2".equals(product) && (StringUtil.compareVer(version, "2.7.0") <= 0)){
			targetOrder.put("canConfirm", "0");
//		2.7.1以后的订单详情里，同时能够确认收货和订单跟踪时，优先显示确认收货
		} else if ("orderDetail".equals(fromMethod) && "1".equals(checkCanConfirm(sourceOrder, version)) && (StringUtil.compareVer(version, "2.7.1") > 0)) {
				targetOrder.put("canConfirm", "1");
				targetOrder.put("canLogistics", "0");
		} else {
			targetOrder.put("canConfirm", checkCanConfirm(sourceOrder, version));
		}

		String giftCardAmount = checkNull(sourceOrder.getString("giftcardamount"));
		if (giftCardAmount.indexOf(".") > 0) {
			giftCardAmount = giftCardAmount.substring(0, giftCardAmount.indexOf("."));
		}
		targetOrder.put("giftCardAmount", giftCardAmount);
		String postArea = sourceOrder.getString("postArea");
		targetOrder.put("postArea", sourceOrder.getString("postArea"));
		JSONObject carriage = new JSONObject();
		String freight = String.valueOf(sourceOrder.getInt("freight"));
		if ("102".equals(product) || "2".equals(product)) {
			if (StringUtil.compareVer(version, "2.6.5") >= 0) {
				if (freight != null && !"0".equals(freight)) {
					carriage.put("amount", String.valueOf(sourceOrder.getInt("freight")));
					carriage.put("reduction", "0");
					String desc = String.valueOf(sourceOrder.getString("freightdescription"));
					carriage.put("desc", desc);
				} else {
					carriage.put("amount", String.valueOf(sourceOrder.getInt("freight")));
					if ("2".equals(postArea)) {
						carriage.put("reduction", "0");// 由于海外取暂时取不到优惠的运费金额，所以做这种逻辑，国内的暂时写死20
					} else {
						carriage.put("reduction", REDUCTION);
					}
					String desc = String.valueOf(sourceOrder.getString("freightdescription"));
					carriage.put("desc", desc);
				}
			} else {
				if (freight != null && "0".equals(freight)) {
					carriage.put("amount", REDUCTION);
					carriage.put("reduction", REDUCTION);
					String desc = String.valueOf(sourceOrder.getString("freightdescription"));
					carriage.put("desc", desc);
				} else {
					carriage.put("amount", String.valueOf(sourceOrder.getInt("freight")));
					carriage.put("reduction", "0");
					carriage.put("desc", "");
				}
			}
		} else {
			if (freight != null && "0".equals(freight)) {
				carriage.put("amount", REDUCTION);
				carriage.put("reduction", REDUCTION);
				String desc = String.valueOf(sourceOrder.getString("freightdescription"));
				carriage.put("desc", desc);
			} else {
				carriage.put("amount", String.valueOf(sourceOrder.getInt("freight")));
				carriage.put("reduction", "0");
				carriage.put("desc", "");
			}
		}
		//电子礼品卡字段应该显示为 ：运费减免（电子卡免运费）
		if(isEcard){
			carriage.put("desc", "运费减免（电子卡免运费）");
		}
		
		targetOrder.put("carriage", carriage); // 运费
		JSONObject tarriffResult = sourceOrder.getJSONObject("tariff");
		targetOrder.put("tariff", tarriffResult);
		targetOrder.put("amount", String.valueOf(sourceOrder.getInt("totalamount"))); // 总金额
		targetOrder.put("onlineamount", String.valueOf(sourceOrder.getInt("onlineamount"))); // 线上支付金额
		targetOrder.put("payAmount", String.valueOf(sourceOrder.getInt("payamount")));
		JSONObject payMode = new JSONObject();// 支付方式
		payMode.put("id", checkNull(sourceOrder.getString("paytypeid")));
		payMode.put("subId", checkNull(sourceOrder.getString("paytypechildid")));
		payMode.put("name", checkNull(sourceOrder.getString("paytypename")));
		targetOrder.put("payMode", payMode);
		targetOrder.put("discount", String.valueOf(sourceOrder.getInt("discountamount"))); // 优惠折扣金额
		String cod = "0";
		try {
			cod = checkTrueOrFalse(sourceOrder.getString("iscod"));
		} catch (Exception e) {
		}
		targetOrder.put("cod", cod); // 是否支出货到付款
		if (payModeList != null && payModeList.size() > 0) {
			targetOrder.put("payments", getPayMentpattern(cod, payModeList));// 支付方式
		}
		if (cod != null && cod.equals("0")) {
			targetOrder.put("codMsg", "无法货到付款"); // 无法货到付款的原因
		} else {
			targetOrder.put("codMsg", "");
		}
		String giftCard = "0";
		String giftCardBalance = checkNull(sourceOrder.getString("giftbardbalance"));
		try {
			giftCard = checkTrueOrFalse(sourceOrder.getString("isusablegiftcardpay"));
			if (giftCardBalance != null && giftCardBalance.equals("0")) {
				giftCard = "0";
			}
		} catch (Exception e) {
		}
		targetOrder.put("giftCard", giftCard); // 是否可用礼品卡支付
		targetOrder.put("isUseGiftCard", checkNull(sourceOrder.getString("isusegiftcard")));// 是否使用礼品卡
		if (giftCardBalance.indexOf(".") > 0) {
			giftCardBalance = giftCardBalance.substring(0, giftCardBalance.indexOf("."));
		}
		targetOrder.put("giftCardBalance", giftCardBalance);// 礼品卡余额
		JSONArray orderArray = sourceOrder.getJSONArray("order");
		if (orderArray != null && orderArray.size() > 0) {
			JSONObject orderDetail = orderArray.getJSONObject(0);
			// 收货人信息
			JSONObject receive = new JSONObject();
			receive.put("name", checkNull(orderDetail.getString("name")));
			receive.put("addr", checkNull(orderDetail.getString("addr")));
			receive.put("provName", checkNull(orderDetail.getString("province")));
			receive.put("cityName", checkNull(orderDetail.getString("city")));
			receive.put("areaName", checkNull(orderDetail.getString("area")));
			receive.put("postcode", checkNull(orderDetail.getString("postcode")));
			receive.put("tel", checkNull(orderDetail.getString("tel")));
			receive.put("townName", checkNull(orderDetail.getString("town")));
			receive.put("cardID", checkNull(orderDetail.getString("cardID")));
			targetOrder.put("receive", receive);

			String expressDesc = checkNull(orderDetail.getString("express"));
			targetOrder.put("express", Express.getId(expressDesc)); // 送货
			targetOrder.put("expressDesc", expressDesc); // 送货
			// 发票信息
			JSONObject invoice = new JSONObject();
			invoice.put("title", checkNull(orderDetail.getString("title")));
			invoice.put("context", checkNull(orderDetail.getString("invoice")));
			invoice.put("provName", checkNull(orderDetail.getString("invoiceprovincename")));
			invoice.put("cityName", checkNull(orderDetail.getString("invoicecityname")));
			invoice.put("areaName", checkNull(orderDetail.getString("invoiceareaname")));
			invoice.put("townName", checkNull(orderDetail.getString("invoicetownname")));
			invoice.put("addr", checkNull(orderDetail.getString("invoiceaddress")));
			invoice.put("postcode", checkNull(orderDetail.getString("invoicepostcode")));
			invoice.put("tel", checkNull(orderDetail.getString("invoicemobile")));
			targetOrder.put("invoice", invoice);
			// targetOrder.put("discount",
			// String.valueOf(orderDetail.getInt("discountamount"))); //
			// 子单优惠折扣金额
			for (int i = 0; i < orderArray.size(); i++) {
				JSONObject order = orderArray.getJSONObject(i);
				if (order != null && !"{}".equals(order.toString())) {
					String orderType = order.getString("ordertype");
					if (orderType != null && !"".equals(orderType)) {
						if (orderType.equals("2")) {
							targetOrder.put("aolaiOrder", getOrder(order));
						} else if (orderType.equals("1")) {
							targetOrder.put("shangpinOrder", getOrder(order));
						}
					}
				}
			}
		}
		return targetOrder;
	}

	public static JSONArray getPayMentpattern(String cod, JSONArray mainPayMode) {
		JSONArray result = new JSONArray();
		if (mainPayMode != null && mainPayMode.size() > 0) {
			for (int i = 0; i < mainPayMode.size(); i++) {
				JSONObject obj = mainPayMode.getJSONObject(i);
				JSONObject temp = new JSONObject();
				String id = obj.getString("id");
				temp.put("id", id);
				temp.put("name", obj.getString("name"));
				if ((id != null && id.equals("2")) || "1".equals(cod)) {
					temp.put("enable", cod);
				} else {
					temp.put("enable", obj.getString("enable"));
				}
				result.add(temp);
			}
		}
		return result;
	}

	public static String checkNull(String value) {
		if (value != null && !"null".equals(value)) {
			return value;
		} else {
			return "";
		}
	}

	public static String checkTrueOrFalse(String value) {
		if (value != null && "true".equals(value)) {
			return "1";
		} else {
			return "0";
		}
	}

	/**
	 * 订单合并前的订单信息
	 * 
	 * @param order
	 * @return
	 */
	private static JSONArray getManageOrder(JSONObject order) {

		JSONArray productList = order.getJSONArray("orderdetail");
		JSONArray list = new JSONArray();
		if (productList != null && productList.size() > 0) {
			for (int i = 0; i < productList.size(); i++) {
				JSONObject product = productList.getJSONObject(i);
				JSONObject targetProduct = new JSONObject();
				targetProduct.put("sku", checkNull(product.getString("sku")));
				targetProduct.put("brand", checkNull(product.getString("brand")));
				// targetProduct.put("exchange", "00");//是否支持退换货
				targetProduct.put("productname", checkNull(product.getString("productname")));
				targetProduct.put("firstpropname", "颜色");
				targetProduct.put("firstpropvalue", checkNull(product.getString("firstpropvalue")));
				targetProduct.put("secondpropname", "尺码");
				targetProduct.put("secondpropvalue", checkNull(product.getString("secondpropvalue")));
				targetProduct.put("count", checkNull(product.getString("count")));
				targetProduct.put("amount", String.valueOf(product.getInt("amount")));
				targetProduct.put("pic", checkNull(product.getString("pic")));
				list.add(targetProduct);
			}
		}
		return list;

	}

	public static JSONObject getOrder(JSONObject order) {
		JSONObject reOrder = new JSONObject();
		reOrder.put("orderId", checkNull(order.getString("orderno")));
		reOrder.put("date", checkNull(order.getString("date")));
		Integer amount = 0;

		reOrder.put("status", checkNull(order.getString("status")));
		reOrder.put("statusDesc", checkNull(order.getString("statusname")));
		reOrder.put("canconfirm", checkTrueOrFalse(order.getString("canconfirmgoods")));

		String status = checkNull(order.getString("status"));
		
		JSONArray productList = order.getJSONArray("orderdetail");
		JSONArray list = new JSONArray();
		if (productList != null && productList.size() > 0) {
			for (int i = 0; i < productList.size(); i++) {
				JSONObject product = productList.getJSONObject(i);
				JSONObject targetProduct = new JSONObject();
				String type = checkNull(product.getString("type"));
				targetProduct.put("type", type);
				targetProduct.put("sku", checkNull(product.getString("sku")));
				if ("0".equals(type)) {
					targetProduct.put("brandName", checkNull(product.getString("brand")));
				} else {
					// 如果是礼品卡不显示品牌
					targetProduct.put("brandName", "");
				}

				// targetProduct.put("exchange", "00");//是否支持退换货
				targetProduct.put("productId", checkNull(product.getString("productno")));
				targetProduct.put("topicId", "");// 暂时扩展
				targetProduct.put("productName", checkNull(product.getString("productname")));

				if ("0".equals(type)) {
					JSONArray colorSize = new JSONArray();
					JSONObject colorObj = new JSONObject();
					String first = checkNull(product.getString("firstpropvalue"));
					if (first.equals("")) {
						colorObj.put("name", "");
					} else {
						colorObj.put("name", "颜色");
					}
					colorObj.put("code", "color");
					colorObj.put("value", first);
					colorSize.add(colorObj);
					JSONObject sizeObj = new JSONObject();
					sizeObj.put("code", "size");
					String second = checkNull(product.getString("secondpropvalue"));
					if (second.equals("")) {
						sizeObj.put("name", "");
					} else {
						sizeObj.put("name", "尺码");
					}
					sizeObj.put("value", second);
					colorSize.add(sizeObj);
					targetProduct.put("prop", colorSize);
				} else {
					JSONArray colorSize = new JSONArray();
					JSONObject colorObj = new JSONObject();
					colorObj.put("code", "color");
					colorObj.put("name", "");
					colorObj.put("value", "");
					colorSize.add(colorObj);
					JSONObject sizeObj = new JSONObject();
					sizeObj.put("code", "size");
					sizeObj.put("name", "");
					sizeObj.put("value", "");
					colorSize.add(sizeObj);
					// 如果是礼品卡不显示属性
					targetProduct.put("prop", colorSize);
				}

				targetProduct.put("count", checkNull(product.getString("count")));
				targetProduct.put("price", String.valueOf(product.getInt("amount")));
				targetProduct.put("pic", checkNull(product.getString("pic")));
				list.add(targetProduct);
				if (!"".equals(checkNull(product.getString("amount"))) && !"".equals(checkNull(product.getString("count")))) {
					try {
						int count = product.getInt("count");
						int amount1 = product.getInt("amount");
						amount += count * amount1;
					} catch (Exception e) {
					}
				}
			}
		}
		reOrder.put("amount", String.valueOf(amount));
		reOrder.put("list", list);
		return reOrder;
	}

	/**
	 * 查看物流信息
	 * 
	 * @param orderId
	 * @return
	 */
	public static JSONObject getSPLogisticsDetail(String orderId, String userId) {
		JSONObject result = null;
		String url = Constants.SP_BASE_URL_OLD + "SPLogisticsDetail";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userId);
		map.put("orderid", orderId);
		try {
			String data = WebUtil.readContentFromGet(url, map);
			if (data != null) {
				JSONObject dataObj = JSONObject.fromObject(data);
				if (dataObj != null) {
					String code = dataObj.getString("code");
					if (code != null && code.equals("0")) {
						JSONObject content = dataObj.getJSONObject("content");
						if (content != null && !"{}".equals(content.toString())) {
							JSONArray logistics = content.getJSONArray("logistics");
							if (logistics != null && !"[]".equals(logistics) && logistics.size() > 0) {
								result = logistics.getJSONObject(0);
								String date1 = result.getString("date");
								for (int i = 1; i < logistics.size(); i++) {
									JSONObject logistic = logistics.getJSONObject(i);
									String date = logistic.getString("date");
									if (date1.compareTo(date) < 0) {
										result = logistic;
									}
								}
							}
						}
					} else {

					}
				}
			}
		} catch (Exception e) {
		}

		return result;
	}
	/**
	 * 判断合单里的每个子订单，是否能够确认收货
	 * @param jsonArray
	 * @param version
	 * @param sourceOrder
	 * @return
	 */
	public static String checkCanConfirm(JSONObject sourceOrder, String version) {
		String isConfirm = "1";
		JSONArray jsonArray = sourceOrder.getJSONArray("order");
		if (jsonArray == null || jsonArray.size() == 0){
			isConfirm  = "0";
		}
		for(int i=0; i<jsonArray.size(); i++) {
			JSONObject order = jsonArray.getJSONObject(i);
			String status = order.getString("status");
			int paytypeid = sourceOrder.getInt("paytypeid");
			if (StringUtil.compareVer(version, "2.7.0") >= 0) {
				if (!(2 == paytypeid) && "16".equals(status)) {
					continue;
				} else {
					isConfirm = "0";
				}

			} else {
				isConfirm = checkTrueOrFalse(sourceOrder.getString("canconfirmgoods"));
			}
		}
		
		return isConfirm;
	}
}
