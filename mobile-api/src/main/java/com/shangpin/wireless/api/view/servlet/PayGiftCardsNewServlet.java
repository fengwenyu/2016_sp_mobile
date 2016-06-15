package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.service.ASPBizLockSkuCommonService;
import com.shangpin.biz.service.ASPBizLockSkuService;
import com.shangpin.biz.service.ASPBizOrderService;
import com.shangpin.pay.service.CommonPayService;
import com.shangpin.wireless.api.api2client.domain.CommonAPIResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.action.PayOrderAction;

/**
 * 礼品卡支付接口
 * 
 * @CreatDate: 2014-07-14
 */
public class PayGiftCardsNewServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(PayGiftCardsNewServlet.class);

	
	CommonPayService commonPayService;
	ASPBizLockSkuCommonService aspBizLockSkuCommonService;
	private ASPBizOrderService aspBizOrderService;
	@Override
	public void init() throws ServletException {
		commonPayService= (CommonPayService) getBean(CommonPayService.class);
		aspBizLockSkuCommonService=(ASPBizLockSkuCommonService) getBean(ASPBizLockSkuCommonService.class);
		aspBizOrderService=(ASPBizOrderService) getBean(ASPBizOrderService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String productNo = request.getHeader("p");
		final String userid = request.getParameter("userid");
		final String password = request.getParameter("password");// 礼品卡密码
		final String orderid = request.getParameter("orderid");
		final String paytype = request.getParameter("paytype");
		final String mainpaymode = request.getParameter("mainpaymode");
		String subpaymode = request.getParameter("subpaymode");
		final String sessionid = request.getParameter("sessionid");
		final String source = request.getParameter("source"); // 支付宝钱包标识
        final String token = request.getParameter("token"); // 支付宝钱包token
        final String productno = request.getParameter("productno"); // 翼支付用户手机号
        final String sig = request.getParameter("sig"); // 翼支付签名
		final String imei = request.getHeader("imei");
		final String version = request.getHeader("ver");
		String shoptype = "";
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userid, password,sessionid, imei, productNo)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("password", password);
				map.put("orderid", orderid);
				map.put("paytype", paytype);
				if ("1".equals(productNo) || "101".equals(productNo)) {// 奥莱
					shoptype = "2";
				}
				if ("2".equals(productNo) || "102".equals(productNo)) {// 尚品
					shoptype = "1";
				}
				map.put("shoptype", shoptype);
				
				OrderItem orderItem=aspBizOrderService.beOrderDetail(userid, orderid).getObj();
				String orderstatusdesc = orderItem.getStatusname();
				CommonAPIResponse apiResponse = new CommonAPIResponse();
				String canPay = orderItem.getCanpay();
				
				if (canPay.equals("false")) {
					apiResponse.setCode(Constants.ERROR_CANPAY);
					apiResponse.setMsg("订单状态已经为" + orderstatusdesc + "，无法支付");
					response.getWriter().print(apiResponse.toString());
					return;
				} 
				if(orderItem.getPostArea().equals("2")){
					Map<String,String> lockSkuMap=aspBizLockSkuCommonService.orderLockSku(orderItem.getOrder().get(0).getOrderno(), userid, orderid);
					String lockCode=lockSkuMap.get("code");
					if (Constants.ORDER_LOCKSKU_ERROR.equals(lockCode)||Constants.ORDER_LOCKSKU_FAIL.equals(lockCode)) {
						apiResponse.setCode(lockCode);
						apiResponse.setMsg(lockSkuMap.get("msg"));
						response.getWriter().print(apiResponse.toString());
						return;
					}
				}
				
				String url = Constants.BASE_TRADE_URL + "order/payGiftCards/";			
				try {
					String data = WebUtil.readContentFromGet(url, map);					
					JSONObject apiResult=JSONObject.fromObject(data);	
					JSONObject contentObj=JSONObject.fromObject(apiResult.getString("content"));
					JSONObject payinfoJson=new JSONObject();
					if("0".equals(apiResult.getString("code"))){
						if(mainpaymode.equals("30")){
							subpaymode="120";
						}
						String apiPayResult=new PayOrderAction().payInfo(request, null,commonPayService, productNo,version,userid,mainpaymode,subpaymode,orderid,source,token,productno,sig,this.getServletContext());                        					    
						JSONObject payObj=new JSONObject();
						payObj=JSONObject.fromObject(apiPayResult);
						if (Constants.ERROR_CANPAY.equals(payObj.getString("code"))) {
							response.getWriter().print(apiPayResult.toString());
							return;
						}
						if("0".equals(payObj.getString("code"))){
							payinfoJson=JSONObject.fromObject(payObj.getString("content"));
						}
						if ("-3".equals(payObj.getString("code"))) {
							response.getWriter().print(payinfoJson.toString());
							return;
						}
					}
					payinfoJson.put("prompt", Constants.PROMPT);
					contentObj.put("payinfo", payinfoJson);
					contentObj.put("isEverUsedGiftCard", orderItem.getIsEverUsedGiftCard()==null?"":orderItem.getIsEverUsedGiftCard());
					apiResult.put("content", contentObj);
					response.getWriter().print(apiResult.toString());
					
				} catch (Exception e) {
					e.printStackTrace();
					log.error("PayGiftCardsNewServlet：" + e);
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} else {
				try {
					WebUtil.sendErrorNoSessionid(response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "order/payGiftCards", channelNo,
					"userid", userid, 
					"imei", imei, 
					"shoptype", shoptype,
					"subpaymode", subpaymode,
					"source", source,
					"orderid", orderid);
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
