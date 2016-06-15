package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.wireless.api.api2client.domain.LogicsAPIResponse;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

/**
 * 获取某订单的物流信息
 * 
 * @Author:yumeng
 * @CreatDate: 2012-12-25
 */
public class OrderLogisticServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(OrderLogisticServlet.class);
	
	private ShangPinService shangPinService;
	@Override
	public void init() throws ServletException{
		shangPinService = (ShangPinService) getBean(ShangPinService.class);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String productNo = request.getHeader("p");
		final String userid = request.getParameter("userid");
		final String orderid = request.getParameter("orderid");
		final String sessionid = request.getParameter("sessionid");
		final String flag = request.getParameter("flag");
		final String imei = request.getHeader("imei");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userid, sessionid, imei, productNo, orderid)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				try {
					String data = shangPinService.findOrderMoreLogistic(userid, orderid,"1","0");
					if (data != null) {
					} else {
						try {
							WebUtil.sendApiException(response);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					LogicsAPIResponse apiResponse = new LogicsAPIResponse();
					response.getWriter().print(apiResponse.obj2Json(data));
				} catch (Exception e) {
					e.printStackTrace();
					log.error("OrderLogisticServlet：" + e);
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
			FileUtil.addLog(request, "orderlogistic", channelNo,
					"userid", userid, 
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
