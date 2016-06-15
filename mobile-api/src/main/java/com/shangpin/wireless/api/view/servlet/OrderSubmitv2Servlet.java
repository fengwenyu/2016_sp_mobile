package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.OrderSubmitAPIResponse;
import com.shangpin.wireless.api.api2server.domain.OrderSubmitServerResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.PayConfigCacheUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 提交订单接口(新架构)
 * 
 *  * @CreatDate: 2013-08-01
 */
public class OrderSubmitv2Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(OrderSubmitv2Servlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String product = request.getHeader("p");
		final String channel = request.getHeader("ch");
		final String version = request.getHeader("ver");
		final String userid = request.getParameter("userid");
		final String addrid = request.getParameter("addrid");
		final String invoiceflag = request.getParameter("invoiceflag");
		final String express = request.getParameter("express");
		final String coupon = request.getParameter("coupon");
		final String invoiceaddrid = request.getParameter("invoiceaddrid");
		final String invoicetitle = request.getParameter("invoicetitle");
		final String invoicecontent = request.getParameter("invoicecontent");
		final String invoicetype = request.getParameter("invoicetype");
		final String sessionid = request.getParameter("sessionid");
		final String errorskus = request.getParameter("errorskus");// 有问题的sku编号：123|223|333
		final String imei = request.getHeader("imei");
		final String couponflag = request.getParameter("couponflag");
		String buysids = request.getParameter("buysids");
		if (StringUtil.isNotEmpty(product, channel, version, userid, imei, addrid, invoiceflag, express, sessionid, buysids)) {

			buysids = buysids.replace(',', '|');
			
			String orderorigin = "9";
			if("101".equals(product) ||"102".equals(product) ){
				orderorigin="18";//安卓订单渠道
			}
			String ordertype;
			String url = Constants.BASE_URL + "confirmorder/";
			if ("1".equals(product) || "101".equals(product)) {	//	奥莱客户端
				ordertype = "2";// (表示订单来自尚品1，奥莱2)
			} else if ("2".equals(product) || "102".equals(product)) {	//	尚品客户端
				ordertype = "1";// (表示订单来自尚品1，奥莱2)
			} else {
				ordertype = "";
			}
			
			url = Constants.BASE_TRADE_URL + "order/ConfirmOrder/"; 
			
			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
                map.put("userid", userid);
                map.put("addrid", addrid);
                map.put("invoiceflag", invoiceflag);
                map.put("invoiceaddrid", invoiceaddrid);
                map.put("express", express);
                map.put("coupon", null == coupon ? "0" : coupon);
                map.put("couponflag", couponflag);
                map.put("invoiceaddrid", invoiceaddrid);
                map.put("invoicetitle", invoicetitle);
                map.put("invoicecontent", invoicecontent);
                map.put("invoicetype", invoicetype);
                map.put("buysIds", buysids);
                map.put("orderfrom", orderorigin);// (订单来源) 
                map.put("ordertype", ordertype);// (表示订单来自尚品1，奥莱2)
                
				OrderSubmitServerResponse orderSubmitServerResponse = new OrderSubmitServerResponse();
				OrderSubmitAPIResponse apiResponse = new OrderSubmitAPIResponse();
				try {
					String data = WebUtil.readContentFromGet(url, map);
					orderSubmitServerResponse.jsonV2Obj(data);
					BeanUtils.copyProperties(apiResponse, orderSubmitServerResponse);
					if (Constants.SUCCESS.equals(orderSubmitServerResponse.getCode())) {
						// 获取的在线支付方式
						final boolean supportCod = "1".equals(orderSubmitServerResponse.getCod());
						JSONArray onlinePayment = PayConfigCacheUtil.getPayConfig(product, channel, version, supportCod);
						if (onlinePayment == null) {
							onlinePayment = PayConfigCacheUtil.getPayConfig(product, "0", version, supportCod);
						}
						apiResponse.setMainpaymode(onlinePayment);
						response.getWriter().print(apiResponse.obj2Json());
					} else {
						if ("54c2b76f".equals(apiResponse.getCode()) || //
								"6194cca8".equals(apiResponse.getCode()) || //
								"f6390f2e".equals(apiResponse.getCode()) || //
								"b2c267e0".equals(apiResponse.getCode()) || //
								"a00b3c5a".equals(apiResponse.getCode()) || //
								"2bc1c5c3".equals(apiResponse.getCode()) || //
								"ac46383e".equals(apiResponse.getCode()) || //
								"9912c033".equals(apiResponse.getCode()) || //
								"9f98c417".equals(apiResponse.getCode())) {
							apiResponse.setCode(Constants.ERROR_SUBMITORDER);
							apiResponse.setMsg("购物袋中有商品的状态发生变化，请重新确认");
						}

						response.getWriter().print(apiResponse.obj2Json());
					}
					// 记录是否下单成功
					FileUtil.addLog(request, "orderStatistics",channel,
							"code", "0".equals(orderSubmitServerResponse.getCode()) ? "success" : "fail",//
							"orderid", orderSubmitServerResponse.getOrderid(),// 
							"date", orderSubmitServerResponse.getDate(),// 
							"amount", orderSubmitServerResponse.getAmount(),//
							"skucounts", orderSubmitServerResponse.getSkucounts(),//
							"objectcounts", orderSubmitServerResponse.getObjectcounts(),//
							"userid", userid,//
							"orderorign", orderorigin,//
							"ordertype", ordertype,//
							"code", orderSubmitServerResponse.getCode(),
							"giftcard",orderSubmitServerResponse.getGiftcard()
							);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("OrderManageServlet：" + e);
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					// 记录下单失败
					FileUtil.addLog(request, "orderStatistics", channel, 
							"code", "fail",// 
							"orderid", orderSubmitServerResponse.getOrderid(),// 
							"date", orderSubmitServerResponse.getDate(),// 
							"amount", orderSubmitServerResponse.getAmount(),//
							"skucounts", orderSubmitServerResponse.getSkucounts(),//
							"objectcounts", orderSubmitServerResponse.getObjectcounts(),//
							"userid", userid,//
							"orderorign", orderorigin,//
							"ordertype", ordertype,//
							"giftcard",orderSubmitServerResponse.getGiftcard()
							);
				}
			} else {
				try {
					WebUtil.sendErrorNoSessionid(response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "order", channel,
					"userid", userid,//
					"addrid", addrid,// 
					"invoiceflag", invoiceflag,// 
					"express", express,//
					"coupon", coupon,// 
					"invoiceaddrid", invoiceaddrid,//
					"invoicetitle", invoicetitle,//
					"invoicecontent", invoicecontent,//
					"invoicetype", invoicetype,//
					"orderorign", orderorigin,//
					"sessionid", sessionid,//
					"errorskus", errorskus,//
					"ordertype", ordertype//
					);
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
	/*public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String product = request.getHeader("p");
		final String channel = request.getHeader("ch");
		final String version = request.getHeader("ver");
		final String userid = request.getParameter("userid");
		final String addrid = request.getParameter("addrid");
		final String invoiceflag = request.getParameter("invoiceflag");
		final String express = request.getParameter("express");
		final String coupon = request.getParameter("coupon");
		final String invoiceaddrid = request.getParameter("invoiceaddrid");
		final String invoicetitle = request.getParameter("invoicetitle");
		final String invoicecontent = request.getParameter("invoicecontent");
		final String invoicetype = request.getParameter("invoicetype");
		final String sessionid = request.getParameter("sessionid");
		final String errorskus = request.getParameter("errorskus");// 有问题的sku编号：123|223|333
		final String imei = request.getHeader("imei");
		final String couponflag = request.getParameter("couponflag");
		String buysids = request.getParameter("buysids");
		if (StringUtil.isNotEmpty(product, channel, version, userid, imei, addrid, invoiceflag, express, sessionid, buysids)) {

			buysids = buysids.replace(',', '|');
			
			final String orderorigin = "9";
			String ordertype;
			String url = Constants.BASE_URL + "confirmorder/";
			if ("1".equals(product) || "101".equals(product)) {	//	奥莱客户端
				ordertype = "2";// (表示订单来自尚品1，奥莱2)
			} else if ("2".equals(product) || "102".equals(product)) {	//	尚品客户端
				ordertype = "1";// (表示订单来自尚品1，奥莱2)
				url = Constants.BASE_URL + "confirmorder/";
			} else {
				ordertype = "";
			}
			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("addrid", addrid);
				map.put("invoiceflag", invoiceflag);
				map.put("express", express);
				map.put("coupon", null == coupon?"0":coupon);
				map.put("invoiceaddrid", invoiceaddrid);
				map.put("invoicetitle", invoicetitle);
				map.put("invoicecontent", invoicecontent);
				map.put("invoicetype", invoicetype);
				map.put("orderfrom", orderorigin);// (订单来源) （此参数值传入9）
				map.put("ordertype", ordertype);// (表示订单来自尚品1，奥莱2)
				map.put("errorskus", errorskus);
				map.put("couponflag", couponflag);
				map.put("buysids", buysids);
				OrderSubmitServerResponse orderSubmitServerResponse = new OrderSubmitServerResponse();
				OrderSubmitAPIResponse apiResponse = new OrderSubmitAPIResponse();
				try {
					String data = WebUtil.readContentFromGet(url, map);
					orderSubmitServerResponse.json2Obj(data);
					BeanUtils.copyProperties(apiResponse, orderSubmitServerResponse);
					if (Constants.SUCCESS.equals(orderSubmitServerResponse.getCode())) {
						// 获取的在线支付方式
						final boolean supportCod = "1".equals(orderSubmitServerResponse.getCod());
						JSONArray onlinePayment = PayConfigCacheUtil.getPayConfig(product, channel, version, supportCod);
						if (onlinePayment == null) {
							onlinePayment = PayConfigCacheUtil.getPayConfig(product, "0", version, supportCod);
						}
						apiResponse.setMainpaymode(onlinePayment);
						response.getWriter().print(apiResponse.obj2Json());
					} else {
						if ("54c2b76f".equals(apiResponse.getCode()) || //
								"6194cca8".equals(apiResponse.getCode()) || //
								"f6390f2e".equals(apiResponse.getCode()) || //
								"b2c267e0".equals(apiResponse.getCode()) || //
								"a00b3c5a".equals(apiResponse.getCode()) || //
								"2bc1c5c3".equals(apiResponse.getCode()) || //
								"ac46383e".equals(apiResponse.getCode()) || //
								"9912c033".equals(apiResponse.getCode()) || //
								"9f98c417".equals(apiResponse.getCode())) {
							apiResponse.setCode(Constants.ERROR_SUBMITORDER);
							apiResponse.setMsg("购物袋中有商品的状态发生变化，请重新确认");
						}

						response.getWriter().print(apiResponse.obj2Json());
					}
					// 记录是否下单成功
					FileUtil.addLog(request, "orderStatistics",// 
							"isOK", "0".equals(orderSubmitServerResponse.getCode()) ? "1" : "0",//
							"orderid", orderSubmitServerResponse.getOrderid(),// 
							"date", orderSubmitServerResponse.getDate(),// 
							"amount", orderSubmitServerResponse.getAmount(),//
							"skucounts", orderSubmitServerResponse.getSkucounts(),//
							"objectcounts", orderSubmitServerResponse.getObjectcounts(),//
							"userid", userid,//
							"orderorign", orderorigin,//
							"ordertype", ordertype,//
							"code", orderSubmitServerResponse.getCode(),
							"giftcard",orderSubmitServerResponse.getGiftcard()
							);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("OrderManageServlet：" + e);
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					// 记录下单失败
					FileUtil.addLog(request, "orderStatistics",// 
							"isOK", "0",// 1成功0失败
							"orderid", orderSubmitServerResponse.getOrderid(),// 
							"date", orderSubmitServerResponse.getDate(),// 
							"amount", orderSubmitServerResponse.getAmount(),//
							"skucounts", orderSubmitServerResponse.getSkucounts(),//
							"objectcounts", orderSubmitServerResponse.getObjectcounts(),//
							"userid", userid,//
							"orderorign", orderorigin,//
							"ordertype", ordertype,//
							"giftcard",orderSubmitServerResponse.getGiftcard()
							);
				}
			} else {
				try {
					WebUtil.sendErrorNoSessionid(response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "order",// 
					"userid", userid,//
					"addrid", addrid,// 
					"invoiceflag", invoiceflag,// 
					"express", express,//
					"coupon", coupon,// 
					"invoiceaddrid", invoiceaddrid,//
					"invoicetitle", invoicetitle,//
					"invoicecontent", invoicecontent,//
					"invoicetype", invoicetype,//
					"orderorign", orderorigin,//
					"sessionid", sessionid,//
					"errorskus", errorskus,//
					"ordertype", ordertype//
					);
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}*/

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}