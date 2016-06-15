package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.SettlementOrderAPIResponse;
import com.shangpin.wireless.api.api2server.domain.ProductServerAllCartVO;
import com.shangpin.wireless.api.api2server.domain.SettlementServerOrderVO;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.OutPayConfigCacheUtil;
import com.shangpin.wireless.api.util.PayConfigCacheUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 去结算（确认订单信息）新
 * 
 * @Author:wangfeng
 * @CreatDate: 2015-03-20
 */
public class ConfirmSettlementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ConfirmSettlementServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}
	
   

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String product = request.getHeader("p");
		final String channel = request.getHeader("ch");
		final String version = request.getHeader("ver");
		String postArea = request.getParameter("postArea");
		String userid = request.getHeader("userid");
		String sessionid = request.getParameter("sessionId");
		String imei = request.getHeader("imei");
		String pich=request.getParameter("pich");
		String picw=request.getParameter("picw");
		String isPromotion="1";//（是否参加促销）参数，（0为促销，1为不参加促销）
		String buyId=request.getParameter("buyId");
		final boolean isLastPay = true;
		if (StringUtil.isNotEmpty(userid, imei, sessionid)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
			    Map<String, String> map = new HashMap<String, String>();
			    map.put("userId", userid);			  
			    map.put("width", picw);
			    map.put("height", pich);
			    map.put("shopCartDetailIds", buyId);
			    String url = Constants.BASE_TRADE_URL + "trade/getordershoppingcartdetail/";
				try {
				        String apiResult="";
						String data = WebUtil.readContentFromGet(url, map);
                        ProductServerAllCartVO productServerAllCartVO=new ProductServerAllCartVO().jsonObj(data);
                        if("0".equals(productServerAllCartVO.getCode())){
                            Map<String, String> orderMap = new HashMap<String, String>();
                            orderMap.put("userid", userid);              
                            orderMap.put("width", picw);
                            orderMap.put("height", pich);
                            orderMap.put("isPromotion", isPromotion);
                            orderMap.put("postArea", postArea);
                            //版本2.9.6，默认使用优惠券功能(开始)
                    		String isDefaultUseCoupon = "0";
                    		if(com.shangpin.utils.StringUtil.compareVersion("", "2.9.6", version) == 1){
                    			isDefaultUseCoupon = "1";
                    		}
                    		orderMap.put("isDefaultUseCoupon", isDefaultUseCoupon);
                    		//版本2.9.6，默认使用优惠券功能(结束)
                            String orderUrl = Constants.BASE_TRADE_URL + "order/SettlementOrder/";
                            String orderData = WebUtil.readContentFromGet(orderUrl, orderMap);  
                            SettlementServerOrderVO settlementServerOrderVO=new SettlementServerOrderVO().jsonObj(orderData);
                            SettlementOrderAPIResponse apiResponse=new SettlementOrderAPIResponse();
                         // 获取的在线支付方式
                            final boolean supportCod = "1".equals(settlementServerOrderVO.getSettlmentOrderVO().getCodincart());
                            JSONArray onlinePayment = null;
                    		//判断跳转海外支付还是国内支付
                    		if ("2".equals(postArea)) {
                    			String isHaveDirect = settlementServerOrderVO.getSettlmentOrderVO().getIsHaveDirect();
                    			String productAmount = settlementServerOrderVO.getSettlmentOrderVO().getProductAmount();
                    			boolean isDir = "1".equals(isHaveDirect);//是直发商品
                    			boolean isProAmount = productAmount!=null && Double.parseDouble(productAmount) >= Double.parseDouble(com.shangpin.biz.utils.Constants.PAY_OVERSEA_PRICE_LINE);
                    			if(!isDir&&isProAmount){
                    				postArea="1";
                    			}
                    		}
            				if ("2".equals(postArea)) {
        						onlinePayment = OutPayConfigCacheUtil.getPayConfig(product, channel, version, supportCod);
        						if (onlinePayment == null) {
        							onlinePayment = OutPayConfigCacheUtil.getPayConfig(product, "0", version, supportCod);
        						}
            				} else {
            					onlinePayment = PayConfigCacheUtil.getPayConfig(product, channel, version, supportCod);
            					if (onlinePayment == null) {
            						onlinePayment = PayConfigCacheUtil.getPayConfig(product, "0", version, supportCod);
            					}
            				}
                            
                            apiResult=apiResponse.objJsonNew(settlementServerOrderVO,onlinePayment, isLastPay, postArea);
                            
                        }else{
                        	apiResult=data;
                        }
					response.getWriter().print(apiResult);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("SettlementServlet：" + e);
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
			FileUtil.addLog(request, "confirmSettlement", channel,
					"userid", userid,
					"buyId",buyId);
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
