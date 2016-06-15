package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.GiftCardConsumptionAPIResponse;
import com.shangpin.wireless.api.api2server.domain.GiftCardConsumptionServerResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 订单管理接口
 * 
 * @Author: wangwenguan
 * @CreatDate: 2014-01-24
 */
public class GiftCardConsumptionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(GiftCardConsumptionServlet.class);


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String product = request.getHeader("p");
		final String userid = request.getParameter("userid");
		final String sessionid = request.getParameter("sessionid");
		final String imei = request.getHeader("imei");
		final String cardno = request.getParameter("cardno");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userid, cardno/*, sessionid, imei*/)) {
//			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("cardno", cardno);
				String url;
				if ("101".equals(product) || "1".equals(product)) {
					url = Constants.BASE_URL_AL_SP + "giftcardconsumption/";
				} else {
					url = Constants.BASE_URL_SP_SP + "giftcardconsumption/";
				}
				try {
					String data = WebUtil.readContentFromGet(url, map);
					GiftCardConsumptionServerResponse serverResponse = new GiftCardConsumptionServerResponse();
					serverResponse.json2Obj(data);
					GiftCardConsumptionAPIResponse apiResponse = new GiftCardConsumptionAPIResponse();
					BeanUtils.copyProperties(apiResponse, serverResponse);
					response.getWriter().print(apiResponse.obj2Json());
				} catch (Exception e) {
					e.printStackTrace();
					log.error("GiftCardConsumptionServlet：" + e);
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
//			} else {
//				try {
//					WebUtil.sendErrorNoSessionid(response);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
			// 记录访问日志
			FileUtil.addLog(request, "giftcardconsumption", channelNo,
					"userid", userid, 
					"cardno", cardno,
					"product", product,
					"sessionid", sessionid,
					"imei", imei);
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
