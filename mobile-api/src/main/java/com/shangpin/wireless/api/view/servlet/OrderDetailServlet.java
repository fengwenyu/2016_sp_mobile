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

import com.shangpin.wireless.api.api2client.domain.MergeOrderDetailResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.PayConfigCacheUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 订单详情接口
 * 
 * @Author: wangwenguan
 * @CreatDate: 2014-01-23
 */
public class OrderDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(OrderDetailServlet.class);


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String product = request.getHeader("p");
		final String channel = request.getHeader("ch");
		final String version = request.getHeader("ver");
		final String userid = request.getParameter("userid");
		final String orderid = request.getParameter("orderid");
		final String sessionid = request.getParameter("sessionid");
		final String picw = request.getParameter("picw");
		final String pich = request.getParameter("pich");
		final String imei = request.getHeader("imei");
		if (StringUtil.isNotEmpty(userid, orderid, sessionid,imei)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("mainOrderNo", orderid);
				String url = Constants.BASE_TRADE_URL + "order/getorder";
				try {
					String data = WebUtil.readContentFromGet(url, map);
					if(data == null || "".equals(data)){
						try {
							WebUtil.sendApiException(response);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}else{
						final boolean supportCod = false;	//	默认为不支持，由客户端处理是否支持
						JSONArray onlinePayment = PayConfigCacheUtil.getPayConfig(product, channel, version, supportCod);
						if (onlinePayment == null) {
							onlinePayment = PayConfigCacheUtil.getPayConfig(product, "0", version, supportCod);
						}
						MergeOrderDetailResponse memgeOrderDetailResponse = new MergeOrderDetailResponse();
						memgeOrderDetailResponse.setMainPayMode(onlinePayment);
						memgeOrderDetailResponse.setUserId(userid);
						response.getWriter().print(memgeOrderDetailResponse.getNewOrderToOldOrderDetail(data));
					}
				} catch (Exception e) {
					e.printStackTrace();
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
			FileUtil.addLog(request, "orderdetail", channel,
					"userid", userid, 
					"orderid", orderid,
					"product", product,
					"version",version,
					"picw", picw,
					"pich", pich,
					"imei", imei,
					"sessionid", sessionid);
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

