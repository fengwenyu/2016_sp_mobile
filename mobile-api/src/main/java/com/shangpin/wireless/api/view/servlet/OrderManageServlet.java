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

import com.shangpin.wireless.api.api2client.domain.OrderManageResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.PayConfigCacheUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 订单管理接口
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-08-28
 */
public class OrderManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(OrderManageServlet.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String product = request.getHeader("p");
		final String channel = request.getHeader("ch");
		final String version = request.getHeader("ver");
		final String userId = request.getParameter("userid");
		final String page = request.getParameter("page");
		String orderType = request.getParameter("ordertype");
		final String pageSize = request.getParameter("pagesize");
		final String sessionId = request.getParameter("sessionid");
		final String imei = request.getHeader("imei");
		String status = getStatus(request.getParameter("statustype")); // 订单状态
		if (StringUtil.isNotEmpty(userId, page, sessionId,imei)) {
			if (SessionUtil.validate(userId, imei, sessionId)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userId);
				map.put("page", page);
				map.put("pagesize", pageSize);
				if(StringUtil.isNotEmpty(status) && !status.equals("0")) { //全部
					map.put("status", status);  
				}
				String url = Constants.BASE_TRADE_URL + "order/getorderlist";
				try {
					String data = WebUtil.readContentFromGet(url, map);
					if(data == null || "".equals(data)){
						try {
							WebUtil.sendApiException(response);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}else{
						// 获取的在线支付方式
						final boolean supportCod = false;	//	默认为不支持，由客户端处理是否支持
						JSONArray mainPayMode = PayConfigCacheUtil.getPayConfig(product, channel, version, supportCod);
						if (mainPayMode == null) {
							mainPayMode = PayConfigCacheUtil.getPayConfig(product, "0", version, supportCod);
						}
						OrderManageResponse orderManageResponse = new OrderManageResponse();
						orderManageResponse.setMainPayMode(mainPayMode);
						orderManageResponse.setUserId(userId);
						orderManageResponse.setOrderType(orderType);
						response.getWriter().print(orderManageResponse.obj2Json(data));
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.error("OrderManageServlet：" + e);
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
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * statustype订单状态(默认全部)：all全部，waitpay待支付，preparegoods配货中，delivergoods发货中
	 * @param parameter
	 * @return
	 */
	private String getStatus(String status) {
		String result = "";
		if(status == null){
		}else{
			if(status.equals("all")){
				result = "0";
			}else if(status.equals("waitpay")){
				result = "2";
			}else if(status.equals("preparegoods") || status.equals("delivergoods")){
				result = "5";
			}
		}
		return result;
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
