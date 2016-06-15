package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.OrderMergeManageResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 订单合并管理接口
 * 
 * @author xupengcheng
 * @CreatDate: 2014-04-25
 *
 */
public class OrderMergeManageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(OrderMergeManageServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String userId = request.getParameter("userId");
		final String pageIndex = request.getParameter("pageIndex");
		final String pageSize = request.getParameter("pageSize");
		final String status = request.getParameter("status");// 订单状态
		final String isShowOverseas = request.getParameter("isShowOverseas");// 是否显示海外订单 ，不传为国内，0为全部
		final String product = request.getHeader("p");
		final String channel = request.getHeader("ch");
		final String version = request.getHeader("ver");
		if (StringUtil.isNotEmpty(userId, pageIndex, pageSize, status, version)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userId);
			map.put("page", pageIndex);
			map.put("pagesize", pageSize);
			if (StringUtil.compareVer(version, "2.7.0") < 0 && "3".equals(status)) {
				map.put("status", "old3");// 兼容老版的待收货的业务
			} else {
				map.put("status", status);
			}
			map.put("isShowOverseas", isShowOverseas);
			String url = Constants.BASE_TRADE_URL + "order/getorderlist";
			try {
				String data = WebUtil.readContentFromGet(url, map);
				if (data == null || "".equals(data)) {
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					OrderMergeManageResponse orderMergeManageResponse = new OrderMergeManageResponse();
					orderMergeManageResponse.setUserId(userId);
					data = data.replace("-132-", "-{w}-").replace("-176", "-{h}");
					String resJson = orderMergeManageResponse.obj2Json(data, product, version);
					response.getWriter().print(resJson);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("OrderMergeManageServlet:" + e);
			}
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
