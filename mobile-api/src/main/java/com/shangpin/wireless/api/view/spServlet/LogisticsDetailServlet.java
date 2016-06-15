package com.shangpin.wireless.api.view.spServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 获取物流信息
 * 
 * @Author:yumeng
 * @CreatDate: 2012-12-25
 */
public class LogisticsDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(LogisticsDetailServlet.class);
	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// final String product = request.getHeader("p");
		// final String channel = request.getHeader("ch");
		// final String version = request.getHeader("ver");
		final String userid = request.getParameter("userid");
		final String orderid = request.getParameter("orderid");
		final String ticketno = request.getParameter("ticketno");
		final String sessionid = request.getParameter("sessionid");
		final String imei = request.getHeader("imei");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userid, orderid, sessionid, imei)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("orderid", orderid);
				map.put("ticketno", null == ticketno ? "" : ticketno);
				String url = com.shangpin.wireless.api.domain.Constants .SP_BASE_URL + "SPLogisticsDetail/";
				try {
					String data = WebUtil.readContentFromGet(url, map);
					response.getWriter().print(data);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("LogisticsDetailServlet：" + e);
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
			FileUtil.addLog(request, "shangpinlogisticsdetail", channelNo,
					"userid", userid, 
					"orderid", orderid, 
					"ticketno", ticketno);
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
