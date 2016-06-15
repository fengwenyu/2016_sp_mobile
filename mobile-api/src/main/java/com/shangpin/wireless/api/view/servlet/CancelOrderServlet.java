package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.MergeOrderDetailResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.PayConfigCacheUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 取消订单
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-08-29
 */
public class CancelOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(CancelOrderServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String orderId = request.getParameter("orderid");
		String sessionid = request.getParameter("sessionid");
		String imei = request.getHeader("imei");
		String product = request.getHeader("p");
		String channel = request.getHeader("ch");
		String version = request.getHeader("ver");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userId, orderId, sessionid, imei)) {
			if (SessionUtil.validate(userId, imei, sessionid)) {
				String url = Constants.BASE_TRADE_URL + "order/CancelOrder";
				String data = ""; 
				Map<String, String> map = new HashMap<String, String>();
				map.put("userId", userId);
				map.put("mainOrderNo", orderId);
				try{
					data = WebUtil.readContentFromGet(url, map );
					JSONObject result = JSONObject.fromObject(data);
					if(!(result.isEmpty() || result.isNullObject())){
						String code = result.getString("code");
						if(code != null && "0".equals(code)){
							url = Constants.BASE_TRADE_URL + "order/getorder";
							try {
								data = WebUtil.readContentFromGet(url, map);
								if(data == null || "".equals(data)){
									try {
										WebUtil.sendApiException(response);
									} catch (Exception e2) {
										e2.printStackTrace();
									}
								}else{
									JSONArray onlinePayment = PayConfigCacheUtil.getPayConfig(product, channel, version, false);
									MergeOrderDetailResponse memgeOrderDetailResponse = new MergeOrderDetailResponse();
									memgeOrderDetailResponse.setMainPayMode(onlinePayment);
									memgeOrderDetailResponse.setUserId(userId);
									response.getWriter().print(memgeOrderDetailResponse.getCancelOrderInfo(data));
								}
							} catch (Exception e) {
								e.printStackTrace();
								log.error("MergeOrderDetailServlet：" + e);
								try {
									WebUtil.sendApiException(response);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
						}
					}else{
						response.getWriter().print(data);
					}
					
				}catch(Exception e){
					e.printStackTrace();
					log.error("CancelOrderServlet：" + e);
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e2) {
						e2.printStackTrace();
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
			FileUtil.addLog(request, "cancelorder", channelNo,
					"userid", userId, 
					"orderid", orderId);
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
