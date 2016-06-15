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
import com.shangpin.wireless.api.util.PayConfigCacheUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 去结算（确认订单信息）
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-06-23
 */
public class SettlementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(EditAddressServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}
	
   

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String product = request.getHeader("p");
		final String channel = request.getHeader("ch");
		final String version = request.getHeader("ver");
		String userid = request.getParameter("userId");
		String sessionid = request.getParameter("sessionId");
		String imei = request.getHeader("imei");
		String shoptype = request.getParameter("shopType");
		String pich=request.getParameter("pich");
		String picw=request.getParameter("picw");
		String isPromotion=request.getParameter("isPromotion");//（是否参加促销）参数，（0为促销，1为不参加促销）
		String shopdetailids=request.getParameter("shopDetailId");
		if (StringUtil.isNotEmpty(userid, imei, sessionid)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
			    Map<String, String> map = new HashMap<String, String>();
			    map.put("userId", userid);			  
			    map.put("width", picw);
			    map.put("height", pich);
			    map.put("shopCartDetailIds", shopdetailids);
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
                            String orderUrl = Constants.BASE_TRADE_URL + "order/SettlementOrder/";
                            String orderData = WebUtil.readContentFromGet(orderUrl, orderMap);    
                            SettlementServerOrderVO settlementServerOrderVO=new SettlementServerOrderVO().jsonObj(orderData);
                            SettlementOrderAPIResponse apiResponse=new SettlementOrderAPIResponse();
                         // 获取的在线支付方式
                            final boolean supportCod = "1".equals(settlementServerOrderVO.getSettlmentOrderVO().getCodincart());
                            JSONArray onlinePayment = PayConfigCacheUtil.getPayConfig(product, channel, version, supportCod);
                            if (onlinePayment == null) {
                                onlinePayment = PayConfigCacheUtil.getPayConfig(product, "0", version, supportCod);
                            }
                            apiResult=apiResponse.objJson(settlementServerOrderVO,onlinePayment);
                         
                            
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
			FileUtil.addLog(request, "submitSettlement", channel,
					"userid", userid,
					"shopType",shoptype,
					"shopdetailids",shopdetailids);
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
