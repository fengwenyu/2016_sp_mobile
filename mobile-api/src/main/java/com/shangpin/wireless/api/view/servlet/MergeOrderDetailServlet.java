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
import com.shangpin.wireless.api.util.OutPayConfigCacheUtil;
import com.shangpin.wireless.api.util.PayConfigCacheUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 合并订单详情接口
 * @author xupengcheng
 *
 */
public class MergeOrderDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(MergeOrderDetailServlet.class);


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String userId = request.getParameter("userId");
		final String orderId = request.getParameter("orderId");
		final String product = request.getHeader("p");
		final String channel = request.getHeader("ch");
		final String version = request.getHeader("ver");
		final String postArea=request.getParameter("postArea");
		if (StringUtil.isNotEmpty(userId, orderId)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userId);
			map.put("mainOrderNo", orderId);
			String url = Constants.BASE_TRADE_URL + "order/getorder";
			try {
				String data = WebUtil.readContentFromGet(url, map);
				if(data == null || "".equals(data)){
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e1) {
						e1.printStackTrace();
						log.error("MergeOrderDetailServlet doGet() :"+e1);
					}
				}else{
				    JSONArray onlinePayment = null;
                    if ("2".equals(postArea)) {
                        onlinePayment = OutPayConfigCacheUtil.getPayConfig(product, channel, version, false);
                        if (onlinePayment == null) {
                            onlinePayment = OutPayConfigCacheUtil.getPayConfig(product, "0", version, false);
                        }
                    } else {
                        onlinePayment = PayConfigCacheUtil.getPayConfig(product, channel, version, false);
                        if (onlinePayment == null) {
                            onlinePayment = PayConfigCacheUtil.getPayConfig(product, "0", version, false);
                        }
                    }
					MergeOrderDetailResponse memgeOrderDetailResponse = new MergeOrderDetailResponse();
					memgeOrderDetailResponse.setMainPayMode(onlinePayment);
					memgeOrderDetailResponse.setUserId(userId);
					response.getWriter().print(memgeOrderDetailResponse.obj2Json("orderDetail", data,product,version));
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
