package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.GiftCardListAPIResponse;
import com.shangpin.wireless.api.api2server.domain.GiftCardListServerResponse;
import com.shangpin.wireless.api.api2server.domain.GiftCardListServerResponse.GiftCard;
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
public class GiftCardListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(GiftCardListServlet.class);


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String product = request.getHeader("p");
		final String userid = request.getParameter("userid");
		final String sessionid = request.getParameter("sessionid");
		final String imei = request.getHeader("imei");
		final String statustype = request.getParameter("statustype");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userid, sessionid, imei)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				String url;
				if ("101".equals(product) || "1".equals(product)) {
					url = Constants.BASE_URL_AL_SP + "giftcardlist/";
				} else {
					url = Constants.BASE_URL_SP_SP + "giftcardlist/";
				}
				map.put("status", "-1");
				try {
					String data = WebUtil.readContentFromGet(url, map);
					GiftCardListServerResponse serverResponse = new GiftCardListServerResponse();
					serverResponse.json2Obj(data);
					ArrayList<GiftCard> list = serverResponse.getList();
					int balance = 0;
					if (list != null) {	//	计算总账户余额
						for (int i=0;i<list.size();i++) {
							GiftCard giftcard = list.get(i);
							balance += giftcard.getCurrentAmount();
						}
					}
					if (statustype != null && statustype.length() > 0) {
						if ("used".equals(statustype)) {
							map.put("status", "7");
						} else if ("actived".equals(statustype)) {
							map.put("status", "6");
						} else if ("frozen".equals(statustype)) {
							map.put("status", "8");
						} else {
							map.put("status", "-1");
						}
					} else {
						map.put("status", "-1");
					}
					if (!"-1".equals(map.get("status"))) {
						data = WebUtil.readContentFromGet(url, map);
						serverResponse = new GiftCardListServerResponse();
						serverResponse.json2Obj(data);
					}
					GiftCardListAPIResponse apiResponse = new GiftCardListAPIResponse();
					BeanUtils.copyProperties(apiResponse, serverResponse);
					apiResponse.setBalance(String.valueOf(balance));
					response.getWriter().print(apiResponse.obj2Json());
				} catch (Exception e) {
					e.printStackTrace();
					log.error("GiftCardListServlet：" + e);
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
			FileUtil.addLog(request, "giftcardlist", channelNo,
					"userid", userid, 
					"statustype", statustype,
					"product", product,
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
