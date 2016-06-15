package com.shangpin.mobileAolai.platform.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.shangpin.mobileAolai.common.page.PageContext;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.CookieUtil;
import com.shangpin.mobileAolai.common.util.DataContainerPool;
import com.shangpin.mobileAolai.common.util.FileUtil;
import com.shangpin.mobileAolai.common.util.MD5Util;
import com.shangpin.mobileAolai.common.util.SysContent;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.service.OrderService;
import com.shangpin.mobileAolai.platform.vo.OrderSubmitServerResponse;
import com.shangpin.mobileAolai.platform.vo.OrderVO;
import com.shangpin.mobileAolai.platform.vo.PayVO;
import com.shangpin.mobileAolai.platform.vo.SettlementServerOrderVO;
import com.shangpin.mobileAolai.platform.vo.SettlmentOrderVO;

/**
 * 订单业务逻辑接口实现类，用于订单相关操作
 * 
 * @author yumeng
 * @date:2012-12-29
 */
@Service("orderService")
// @Transactional
@SuppressWarnings({ "unchecked" })
public class OrderServiceImpl implements OrderService {
	// 订单分页每页默认获取10条记录
	private final String ORDER_PAGE_SIZE = "10";
	protected final Log log = LogFactory.getLog(OrderServiceImpl.class.getSimpleName());
	@Override
	public OrderVO addOrder(OrderVO orderVO) {
	    String url = Constants.BASE_NEWAPI_URL_SP + "order/ConfirmOrder/";
		String orderFrom="19";//M站订单渠道
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", orderVO.getUserid());// 用户ID
		map.put("addrid", orderVO.getAddrid());// 　收货人地址ID
		map.put("express", orderVO.getExpress());// 快递信息 id
		map.put("coupon", orderVO.getCoupon());// 优惠券id，不用为0
	//	map.put("paytypeid", orderVO.getPaytypeid());// 主支付方式，支付宝(20)、货到付款(2)、银联
	//	map.put("paytypechildid", orderVO.getPaytypechildid());// 子支付方式，银联:招行、工行...
		boolean tag = null == orderVO.getInvoiceflag() || "0".equals(orderVO.getInvoiceflag());
		map.put("invoiceflag", tag ? "0" : orderVO.getInvoiceflag());// 是否开发票，0不开；1开
		map.put("invoicecontent", tag ? "" : orderVO.getInvoicecontent());// 发票内容：商品全称，箱包，饰品，钟表，化妆品，服装，鞋帽，眼镜
		map.put("invoicetype", tag ? "" : orderVO.getInvoicetype());// 发票类型，0单位；1个人
		map.put("invoicetitle", tag ? "" : orderVO.getInvoicetitle());// 发票抬头
		map.put("invoiceaddrid", tag ? "" : orderVO.getInvoiceaddrid());// 发票收货地址id
		map.put("orderfrom", orderFrom);// 订单来源，手机、网站
		map.put("ordertype", orderVO.getOrdertype());// 表示订单来自尚品1，奥莱2
		map.put("errorskus", orderVO.getErrorskus());
		map.put("couponflag", orderVO.getCouponflag());// 1使用优惠券； 2使用优惠码；未使用传空字符串
		map.put("buysids", orderVO.getBuysids());// 购买商品的gid串，多个用“|”分割
		String json = null;
		OrderSubmitServerResponse orderSubmitServerResponse = new OrderSubmitServerResponse();
		String channelNo=null;
		if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
			channelNo = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
		} else {
			channelNo = StringUtils.isEmpty(channelNo) ? Constants.AOLAI_WAP_DEFAULT_CHANNELNO : channelNo;
		}
		try {
			// 获取订单json格式字符串
			json = WebUtil.readContentFromGet(url, map);
			orderSubmitServerResponse.json2Obj(json);
			// 记录是否下单成功
			FileUtil.addLog(SysContent.getRequest(), "orderStatistics", channelNo,//
					"isOK", "0".equals(orderSubmitServerResponse.getCode()) ? "1" : "0",//
					"orderid", orderSubmitServerResponse.getOrderid(),//
					"date", orderSubmitServerResponse.getDate(),//
					"amount", orderSubmitServerResponse.getAmount(),//
					"skucounts", orderSubmitServerResponse.getSkucounts(),//
					"objectcounts", orderSubmitServerResponse.getObjectcounts(),//
					"userid", orderVO.getUserid() != null ? orderVO.getUserid() : "#",//
					"orderorign", "2",//
					"ordertype", "9",//
					"code", orderSubmitServerResponse.getCode());
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
			// 记录下单失败
			FileUtil.addLog(SysContent.getRequest(), "orderStatistics", channelNo,//
					"isOK", "0",// 1成功0失败
					"orderid", orderSubmitServerResponse.getOrderid(),//
					"date", orderSubmitServerResponse.getDate(),//
					"amount", orderSubmitServerResponse.getAmount(),//
					"skucounts", orderSubmitServerResponse.getSkucounts(),//
					"objectcounts", orderSubmitServerResponse.getObjectcounts(),//
					"userid", orderVO.getUserid() != null ? orderVO.getUserid() : "#",//
					"orderorign", "2",//
					"ordertype", "9"//
			);
		}
		// 记录访问日志
		FileUtil.addLog(SysContent.getRequest(), "orderaction!addorder", channelNo,//
				"userid", orderVO.getUserid() != null ? orderVO.getUserid() : "#",//
				"addrid", orderVO.getAddrid(),//
				"invoiceflag", orderVO.getInvoiceflag(),//
				"express", orderVO.getExpress(),//
				"coupon", orderVO.getCoupon(),//
				"invoiceaddrid", orderVO.getInvoiceaddrid(),//
				"invoicetitle", orderVO.getInvoicetitle(),//
				"invoicecontent", orderVO.getInvoicecontent(),//
				"invoicetype", orderVO.getInvoicetype(),//
				"orderorign", "2",//
				"errorskus", orderVO.getErrorskus(),//
				"ordertype", orderVO.getOrdertype()//
		);
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj) {
				if (Constants.SUCCESS.equals(jsonObj.getString("code"))) {
					// 订单统计
					sendOrderCount(orderVO, "2", CookieUtil.getCookieValue(SysContent.getRequest()));
				}
				if (null != jsonObj.get("content") && !"{}".equals(jsonObj.get("content").toString())) {
					JSONObject contentObj = (JSONObject) jsonObj.get("content");
					orderVO.setOrderid(contentObj.get("orderid").toString());
					orderVO.setAmount(contentObj.get("amount").toString());
					orderVO.setCarriage(contentObj.get("carriage").toString());
				}
				orderVO.setMsg(jsonObj.get("msg").toString());
				orderVO.setCode(jsonObj.get("code").toString());
				return orderVO;
			}
		}
		orderVO.setMsg("订单有误，请重新提交");
		orderVO.setCode("-1");
		return orderVO;
	}

	@Override
	public List<OrderVO> getOrderList(String userid, String statustype) {
		List<OrderVO> list = null;
		String url = Constants.BASE_URL + "ordermanage/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);// 用户ID
		map.put("statustype", statustype);
		map.put("pich", Constants.SAMLL_PICTURE_HEIGHT);
		map.put("picw", Constants.SAMLL_PICTURE_WIDTH);
		map.put("pageindex", "1");
		map.put("pagesize", "1000");
		String json = null;
		try {
			// 获取订单json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj && Constants.SUCCESS.equals(jsonObj.get("code"))) {
				if (null != jsonObj.get("content") && !"{}".equals(jsonObj.get("content").toString())) {
					JSONObject contentObj = (JSONObject) jsonObj.get("content");
					if (null != contentObj.get("list") && !"[]".equals(contentObj.get("list").toString())) {
						JSONArray array = (JSONArray) contentObj.get("list");
						list = JSONArray.toList(array, new OrderVO(), new JsonConfig());
					}
				}
			}
		}
		return list;
	}

	@Override
	public JSONObject getOrderJson(String userid, String statustype,String ordertype,String flag ) {
		String url = Constants.BASE_URL + "ordermanage/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);// 用户ID
		map.put("statustype", statustype);
		map.put("pich", Constants.SAMLL_PICTURE_HEIGHT);
		map.put("picw", Constants.SAMLL_PICTURE_WIDTH);
		map.put("pageindex", String.valueOf(PageContext.getOffset() + 1));
		map.put("pagesize", ORDER_PAGE_SIZE);
		map.put("ordertype", ordertype);
		map.put("flag", flag);
		String json = null;
		try {
			// 获取订单json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		JSONObject res = null;
		try {
			if (null != json && !"".equals(json)) {
				res = JSONObject.fromObject(json);
				if (null != res && Constants.SUCCESS.equals(res.get("code"))) {
					if (null != (JSONObject) res.get("content") && !"[]".equals(((JSONObject) res.get("content")).getString("list"))) {
						JSONObject contentJson = (JSONObject) res.get("content");
						List<OrderVO> list = JSONArray.toList((JSONArray) contentJson.get("list"), new OrderVO(), new JsonConfig());
						String haveMore = "1";
						if (list.size() < Integer.valueOf(ORDER_PAGE_SIZE)) {
							haveMore = "0";
						}
						res.put("haveMore", haveMore);// 0表示没有更多数据；1表示有更多
						res.put("pageindex", String.valueOf(PageContext.getOffset() + 1));
						// return res;
					}
				}
			}
			if (null == res) {
				res = new JSONObject();
				res.put("code", "-1");
				res.put("msg", "数据错误，请重试");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// String str =
		// "{\"code\":\"0\",\"msg\":\"\",\"content\":{\"list\":[{\"orderid\":\"2013031908439\",\"date\":\"2013/3/19 11:06:56\",\"amount\":\"8999\",\"status\":\"0\",\"statusdesc\":\"已取消\",\"cancancel\":\"0\",\"canpay\":\"0\",\"canconfirm\":\"0\",\"cod\":\"1\",\"codmsg\":\"\",\"detail\":{\"list\":[{\"sku\":\"03219928001\",\"brand\":\"PRADA\",\"productname\":\"专利SAFFIANO斜纹牛皮手拎斜挎两用包\",\"cate\":\"\",\"firstpropname\":\"颜色\",\"firstpropvalue\":\"黑色\",\"secondpropname\":\"\",\"secondpropvalue\":\"\",\"count\":\"1\",\"amount\":\"8999\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/12/27/20121227173137502376-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"sadfdsf\",\"province\":\"上海\",\"city\":\"上海\",\"area\":\"普陀区\",\"addr\":\"司法所飞洒\",\"postcode\":\"111111\",\"tel\":\"13401000111\"},\"express\":\"工作日收货\",\"paymode\":\"无线支付宝\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013020708066\",\"date\":\"2013/2/7 17:56:45\",\"amount\":\"9799\",\"status\":\"0\",\"statusdesc\":\"已取消\",\"cancancel\":\"0\",\"canpay\":\"0\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"03219928001\",\"brand\":\"PRADA\",\"productname\":\"专利SAFFIANO斜纹牛皮手拎斜挎两用包\",\"cate\":\"\",\"firstpropname\":\"颜色\",\"firstpropvalue\":\"黑色\",\"secondpropname\":\"\",\"secondpropvalue\":\"\",\"count\":\"1\",\"amount\":\"8999\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/12/27/20121227173137502376-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"0537890001\",\"brand\":\"FOLLI FOLLIE\",\"productname\":\"手镯\",\"cate\":\"\",\"firstpropname\":\"主石/配石\",\"firstpropvalue\":\"银\",\"secondpropname\":\"珠宝尺码\",\"secondpropvalue\":\"无\",\"count\":\"1\",\"amount\":\"800\",\"pic\":\"http://pic02.shangpincdn.com/f/p/11/05/12/20110512181238000082-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"斯蒂芬\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"撒旦法撒法是否萨芬萨芬\",\"postcode\":\"122222\",\"tel\":\"13333333323\"},\"express\":\"工作日收货\",\"paymode\":\"无线支付宝\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013013007868\",\"date\":\"2013/1/30 14:05:07\",\"amount\":\"599\",\"status\":\"10\",\"statusdesc\":\"等待客服确认\",\"cancancel\":\"1\",\"canpay\":\"1\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236077003\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"棕色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"L(国际码 L)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209094317305357-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"sadfdsf\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"司法所飞洒\",\"postcode\":\"111111\",\"tel\":\"13401000111\"},\"express\":\"工作日收货\",\"paymode\":\"货到付款\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013012907778\",\"date\":\"2013/1/29 15:48:49\",\"amount\":\"599\",\"status\":\"0\",\"statusdesc\":\"已取消\",\"cancancel\":\"0\",\"canpay\":\"0\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236077003\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"棕色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"L(国际码 L)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209094317305357-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"sadfdsf\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"司法所飞洒\",\"postcode\":\"111111\",\"tel\":\"13401000111\"},\"express\":\"工作日收货\",\"paymode\":\"无线支付宝\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013012907776\",\"date\":\"2013/1/29 15:45:49\",\"amount\":\"599\",\"status\":\"10\",\"statusdesc\":\"等待客服确认\",\"cancancel\":\"1\",\"canpay\":\"1\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"待客服确认状态订单不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236077003\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"棕色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"L(国际码 L)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209094317305357-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"roonery123\",\"province\":\"山西\",\"city\":\"大同市\",\"area\":\"大同县\",\"addr\":\"郎家园6号-Vintage郎园2号楼A座5层\",\"postcode\":\"100009\",\"tel\":\"12313123121\"},\"express\":\"所有日期均可收货\",\"paymode\":\"货到付款\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013012807688\",\"date\":\"2013/1/28 16:51:07\",\"amount\":\"599\",\"status\":\"10\",\"statusdesc\":\"等待客服确认\",\"cancancel\":\"1\",\"canpay\":\"1\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236079002\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"红色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"M(国际码 M)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103405570341-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"sadfdsf\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"司法所飞洒\",\"postcode\":\"111111\",\"tel\":\"13401000111\"},\"express\":\"工作日收货\",\"paymode\":\"货到付款\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013012807687\",\"date\":\"2013/1/28 16:40:57\",\"amount\":\"599\",\"status\":\"10\",\"statusdesc\":\"等待客服确认\",\"cancancel\":\"1\",\"canpay\":\"1\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236077003\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"棕色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"L(国际码 L)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209094317305357-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"sadfdsf\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"司法所飞洒\",\"postcode\":\"111111\",\"tel\":\"13401000111\"},\"express\":\"工作日收货\",\"paymode\":\"货到付款\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013012807682\",\"date\":\"2013/1/28 16:17:39\",\"amount\":\"2596\",\"status\":\"0\",\"statusdesc\":\"已取消\",\"cancancel\":\"0\",\"canpay\":\"0\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236077003\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"棕色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"L(国际码 L)\",\"count\":\"2\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209094317305357-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"01236079002\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"红色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"M(国际码 M)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103405570341-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"01236081003\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士加绒拉链帽衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"米色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"XL(国际码 XL)\",\"count\":\"1\",\"amount\":\"799\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103303648562-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"sadfdsf\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"司法所飞洒\",\"postcode\":\"111111\",\"tel\":\"13401000111\"},\"express\":\"工作日收货\",\"paymode\":\"无线支付宝\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013012307515\",\"date\":\"2013/1/23 20:49:35\",\"amount\":\"6191\",\"status\":\"0\",\"statusdesc\":\"已取消\",\"cancancel\":\"0\",\"canpay\":\"0\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236079002\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"红色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"M(国际码 M)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103405570341-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"01236079005\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"红色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"XXL(国际码 XXL)\",\"count\":\"3\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103405570341-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"01236081002\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士加绒拉链帽衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"米色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"L(国际码 L)\",\"count\":\"3\",\"amount\":\"799\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103303648562-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"01236081003\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士加绒拉链帽衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"米色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"XL(国际码 XL)\",\"count\":\"1\",\"amount\":\"799\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103303648562-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"01236085002\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"白色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"M(国际码 M)\",\"count\":\"1\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209102917867993-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"斯蒂芬\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"撒旦法撒法是否萨芬萨芬\",\"postcode\":\"122222\",\"tel\":\"13333333323\"},\"express\":\"工作日收货\",\"paymode\":\"无线支付宝\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"},{\"orderid\":\"2013012307508\",\"date\":\"2013/1/23 20:19:46\",\"amount\":\"2895\",\"status\":\"0\",\"statusdesc\":\"已取消\",\"cancancel\":\"0\",\"canpay\":\"0\",\"canconfirm\":\"0\",\"cod\":\"0\",\"codmsg\":\"您的收货地址不支持货到付款\",\"detail\":{\"list\":[{\"sku\":\"01236079004\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士长袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"红色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"XL(国际码 XL)\",\"count\":\"4\",\"amount\":\"599\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/09/20120209103405570341-120-160.jpg\",\"exchange\":\"11\"},{\"sku\":\"01236093001\",\"brand\":\"POLO RALPH LAUREN\",\"productname\":\"男士短袖Polo衫\",\"cate\":\"\",\"firstpropname\":\"产品颜色\",\"firstpropvalue\":\"红色\",\"secondpropname\":\"服装尺码\",\"secondpropvalue\":\"M(国际码 M)\",\"count\":\"1\",\"amount\":\"499\",\"pic\":\"http://pic02.shangpincdn.com/f/p/12/02/16/20120216163851488917-120-160.jpg\",\"exchange\":\"11\"}],\"receive\":{\"name\":\"斯蒂芬\",\"province\":\"天津\",\"city\":\"天津市\",\"area\":\"河东区\",\"addr\":\"撒旦法撒法是否萨芬萨芬\",\"postcode\":\"122222\",\"tel\":\"13333333323\"},\"express\":\"工作日收货\",\"paymode\":\"无线支付宝\",\"invoice\":\"\",\"title\":\"无\"},\"canlogistics\":\"0\",\"onlineamount\":\"\"}]}}";
		// res = new JSONObject().fromObject(str);
		return res;
	}

	@Override
	public OrderVO getOrderDetail(String userId, String orderId) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userId);
		map.put("mainOrderNo", orderId);
		String url = Constants.BASE_NEWAPI_URL_SP + "order/getorder";
		OrderVO orderVO = null;
		String json = null;
		try {
			// 获取订单详情json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (!(jsonObj.isEmpty() || jsonObj.isNullObject()) && Constants.SUCCESS.equals(jsonObj.get("code"))) {
				orderVO = OrderVO.json2Bean(jsonObj.getJSONObject("result"));
			}
		}
		return orderVO;
	}

	@Override
	public JSONObject cancelOrder(String userid, String orderid) {
		String url = Constants.BASE_NEWAPI_URL_SP + "order/CancelOrder";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userid);
		map.put("mainOrderNo", orderid);
		String json = null;
		try {
			// 获取订单json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		JSONObject jsonObj = null;
		if (null != json && !"".equals(json)) {
			json = json.replace("null", "{}");//主站接口有问题
			jsonObj = JSONObject.fromObject(json);
		} else {
			jsonObj = new JSONObject();
			jsonObj.put("code", "-1");
			jsonObj.put("msg", "数据错误，请重试");
		}
		return jsonObj;
	}

	@Override
	public Map<String, Object> getPreparedOrder(String userid,String shopType) {
	    Map<String, Object> res = new HashMap<String, Object>();
        // 组装参数
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid", userid);// 用户ID
        map.put("height", Constants.SAMLL_PICTURE_HEIGHT);
        map.put("width", Constants.SAMLL_PICTURE_WIDTH);
        map.put("isPromotion", "1");
        String url = Constants.BASE_NEWAPI_URL_SP + "order/SettlementOrder/";
        String json = null;
        try {
            // 获取订单详情json格式字符串
            json = WebUtil.readContentFromGet(url, map);
        } catch (Exception e) {
            e.printStackTrace();
            json = null;
        }
        if (null != json && !"".equals(json)) {
            SettlementServerOrderVO settlementServerOrderVO = new SettlementServerOrderVO().jsonObj(json);
            if (null != settlementServerOrderVO && Constants.SUCCESS.equals(settlementServerOrderVO.getCode())) {

                SettlmentOrderVO settlmentOrderVO = settlementServerOrderVO.getSettlmentOrderVO();
                OrderVO orderVO = new OrderVO();
                orderVO.setAmount(settlmentOrderVO.getAmount());
                orderVO.setCoupon(JSONArray.fromObject(settlmentOrderVO.getCoupon()).toString());
                orderVO.setCarriage(settlmentOrderVO.getCarriage().getAmount());
                orderVO.setCodincart(settlmentOrderVO.getCodincart());
                res.put("orderVO", orderVO);
                // 组装收货人地址列表
                if (null != settlmentOrderVO.getReceive() && settlmentOrderVO.getReceive().size() > 0)
                    res.put("consigneeAddressList", settlmentOrderVO.getReceive());
                // 组装商品列表
                if (null != settlmentOrderVO.getProductAllCartVO() && settlmentOrderVO.getProductAllCartVO().getProductsPromotionVOList().size() > 0)
                    res.put("merchandiseList", settlmentOrderVO.getProductAllCartVO().getProductsPromotionVOList());
                // 组装发票地址列表
                if (null != settlmentOrderVO.getInvoiceaddrs() && settlmentOrderVO.getInvoiceaddrs().size() > 0)
                    res.put("invoiceaddrsList", settlmentOrderVO.getInvoiceaddrs());
                // 组装優惠券列表
                if (null != settlmentOrderVO.getCoupon() && settlmentOrderVO.getCoupon().size() > 0)
                    res.put("couponList", settlmentOrderVO.getCoupon());
            } else {
                res.put("msg", settlementServerOrderVO.getMsg());
            }
            res.put("code", settlementServerOrderVO.getCode());
        } else {
			res.put("msg", "数据错误，请刷新重试");
			res.put("code", "-1");
		}
		return res;
	}

	/**
	 * 订单统计
	 * 
	 * @param orderVO
	 *            订单对象
	 * @param userForm
	 *            用户来源，1:尚品；2:奥莱
	 */
	private void sendOrderCount(final OrderVO orderVO, final String userForm, final Map<String, String> map) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					String url = Constants.SP_COUNT_URL + "mobile.aspx";
					String orderNo = orderVO.getOrderid();
					String payAmount = orderVO.getAmount();
					String uid = orderVO.getUserid();
					// 将参数MD5加密生成密钥，约定顺序：OrderNo+PayAmount+userFrom+UId+约定的公钥
					String tmp = userForm + orderNo + payAmount + uid + Constants.SP_BASE_MD5_KEY;
					String key = MD5Util.md5Digest(tmp.toLowerCase());
					// 组装参数
					map.put("MainOrderNo", orderNo);
					map.put("PayAmount", payAmount);
					map.put("userFrom", userForm);
					map.put("UId", uid);
					map.put("passCode", key);
					// 获取结果0失败，1成功
					WebUtil.readContentFromGet(url, map);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	@Override
	public  Map<String, Object> chagePayMode(String userid, String orderid, String mainpaymode, String subpaymode) {
		Map<String, Object> res = new HashMap<String, Object>();
//		String url = Constants.BASE_URL + "chagePayMode/";
//		// 组装参数
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("userid", userid);// 用户ID
//		map.put("orderid",orderid);// 　订单ID
//		map.put("mainpaymode", mainpaymode);// 主支付id
//		map.put("subpaymode", subpaymode);//  子支付id
		HashMap<String, String> map = new HashMap<String, String>();
		String url = Constants.BASE_NEWAPI_URL_SP + "order/UpdatePayType";
		map.put("userid", userid);
		map.put("mainorderno", orderid);
		if("2".equals(mainpaymode)){		    
		    map.put("paytype", "4_" + mainpaymode + "_" + subpaymode);
		}else{
		    map.put("paytype", "0_" + mainpaymode + "_" + subpaymode);
		}
		
		String channelNo=null;
		if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
			channelNo = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
		} else {
			channelNo = StringUtils.isEmpty(channelNo) ? Constants.AOLAI_WAP_DEFAULT_CHANNELNO : channelNo;
		}
		try {
			// 获取订单json格式字符串
			String data =  WebUtil.readContentFromGet(url, map);
			JSONObject content = JSONObject.fromObject(data);
			final String code = content.getString("code");
			// 记录是否更新支付方式成功
		
			if ("0".equals(code)) {
				
				res.put("msgcode", "success");
				// 记录访问日志
				FileUtil.addLog(SysContent.getRequest(), "/orderaction!chagePayMode", channelNo, "userid", userid, "orderid", orderid, "mainpaymode", mainpaymode, "success", "1");
			} else {
				
				res.put("msgcode", "fail");
				res.put("msg", content.getString("msg"));
				FileUtil.addLog(SysContent.getRequest(), "/orderaction!chagePayMode", channelNo,  "userid", userid, "orderid", orderid, "mainpaymode", mainpaymode, "success", "0");
				log.warn("chagePayMode failed orderid = " + orderid + " (" + code + ")(" + content.getString("msg") + ")");
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error("orderaction：" + e);
			
		}
		return res;
	}

	/**
	 * 根据渠道号获取支付方式
	 * 
	 * @param c 渠道号
	 *  @return 返回支付实体
	 */
	@Override
	public List<PayVO> getPayment(String ch) {
		Object obj=DataContainerPool.paymentConfigContainer.get("payment");
		JSONArray array=(JSONArray)obj;
		List<PayVO> payvoList=null;
		List<PayVO> payvodeList=null;
		if (null != obj && !"".equals(obj)) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject paymentVoJsonObj = array.getJSONObject(i);				
				if(ch.equals(paymentVoJsonObj.getString("ch"))){
					Object paymentlistobj=paymentVoJsonObj.get("paymentlist");
					if (null != paymentlistobj && !"".equals(paymentlistobj)) {
						JSONArray paylistarray=(JSONArray)paymentlistobj;
						payvoList=new ArrayList<PayVO>();
						for (int j = 0; j < paylistarray.size(); j++) {
							PayVO payvo=new PayVO();
							JSONObject payvoJsonObj = paylistarray.getJSONObject(j);
							payvo.setId(payvoJsonObj.getString("id"));
							payvo.setName(payvoJsonObj.getString("name"));
							payvoList.add(payvo);
						}
					}
					break;
				}else if(("-1").equals(paymentVoJsonObj.getString("ch"))){
					Object paymentlistobj=paymentVoJsonObj.get("paymentlist");
					if (null != paymentlistobj && !"".equals(paymentlistobj)) {
						JSONArray paylistarray=(JSONArray)paymentlistobj;
						payvodeList=new ArrayList<PayVO>();
						for (int j = 0; j < paylistarray.size(); j++) {
							PayVO payvo=new PayVO();
							JSONObject payvoJsonObj = paylistarray.getJSONObject(j);
							payvo.setId(payvoJsonObj.getString("id"));
							payvo.setName(payvoJsonObj.getString("name"));
							payvodeList.add(payvo);
						}
					}
				}			
			}
		}
		if(payvoList==null){
			return payvodeList;
		}else{
			return payvoList;
		}
	}
	@Override
	public JSONObject getOrderInfoOfJson(String userId, String status) {
		int pageIndex = PageContext.getOffset() + 1;
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userId);
		map.put("status", status);
		map.put("page", String.valueOf(pageIndex));
		map.put("pagesize", ORDER_PAGE_SIZE);
		String url = Constants.BASE_NEWAPI_URL_SP + "order/getorderlist";
		JSONObject result = new JSONObject();
		try {
			String data = WebUtil.readContentFromGet(url, map);
			if (data != null && !"".equals(data)) {
				JSONObject orderListObject = JSONObject.fromObject(data);
				result = getOrderInfo(orderListObject);
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	private JSONObject getOrderInfo(JSONObject sourceObj) {
		int pageIndex = PageContext.getOffset() + 1;
		JSONObject result = new JSONObject();
		if (!(sourceObj.isNullObject() || sourceObj.isEmpty())) {
			String code = sourceObj.getString("code");
			if (code.equals("0")) {
				JSONObject dataObject = sourceObj.getJSONObject("result");
				if (!(dataObject.isNullObject() || dataObject.isEmpty())) {
					int pageCount = dataObject.getInt("TotalPages");
					if (pageIndex < pageCount) {
						result.put("haveMore", "1");
					} else {
						result.put("haveMore", "0");
					}
					result.put("pageindex", String.valueOf(pageIndex));
					JSONArray array = dataObject.getJSONArray("Items");
					if (array != null && !"".equals(array.toString())) {
						JSONArray list = new JSONArray();
						for (int i = 0; i < array.size(); i++) {
							JSONObject mergeOrder = new JSONObject();
							JSONObject sourceOrder = array.getJSONObject(i);
							JSONArray prouuctList = new JSONArray();// 返回的json
							JSONArray orderList = sourceOrder.getJSONArray("order");
							if (orderList.isArray() && !orderList.isEmpty()) {
								for (int j = 0; j < orderList.size(); j++) {
									// 购买的商品
									JSONArray productList = orderList.getJSONObject(j).getJSONArray("orderdetail");
									if (productList.isArray() && !productList.isEmpty()) {
										for (int k = 0; k < productList.size(); k++) {
											JSONObject product = productList.getJSONObject(k);
											product.put("firstpropname", "颜色");
											product.put("secondpropname", "尺码");
											prouuctList.add(product);
										}
									}
								}
							}
							JSONObject detail = new JSONObject();
							detail.put("list", prouuctList);
							mergeOrder.put("detail", detail);
							mergeOrder.put("orderid", sourceOrder.getString("mainorderno"));
							String status = sourceOrder.getString("status").replace("null", "");
							mergeOrder.put("status", status);
							if(status.equals("13") || status.equals("99")){
								mergeOrder.put("canlogistics", "1");
							}else{
								mergeOrder.put("canlogistics", "0");
							}
							mergeOrder.put("statusdesc", sourceOrder.getString("statusname").replace("null", ""));
							list.add(mergeOrder);
						}

						JSONObject content = new JSONObject();
						content.put("list", list);
						result.put("content", content);
						result.put("code", "0");
						result.put("msg", "");
					}
				} else {
					result.put("code", "0");
					result.put("msg", "");
					result.put("haveMore", "0");
					JSONObject content = new JSONObject();
					content.put("list", new JSONArray());
					result.put("content", content);
				}
			}
		} else {
			result.put("code", "-1");
			result.put("haveMore", "0");
			result.put("msg", "数据错误，请重试");
		}
		return result;
	}

	@Override
    public boolean getCheckShopId(String userId, String shopId, String shopTpye) {
        // 组装参数
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("shopCartDetailIds", shopId);
        map.put("height", Constants.SAMLL_PICTURE_HEIGHT);
        map.put("width", Constants.SAMLL_PICTURE_WIDTH);
        String url = Constants.BASE_NEWAPI_URL_SP + "trade/getordershoppingcartdetail/";
        String json = null;
        try {
            json = WebUtil.readContentFromGet(url, map);
        } catch (Exception e) {
            e.printStackTrace();
            json = null;
        }
        if (null != json && !"".equals(json)) {
            JSONObject jsonObj = JSONObject.fromObject(json);
            if ("0".equals(jsonObj.getString("code"))) {
                return true;
            }

        }
        return false;
    }	
}