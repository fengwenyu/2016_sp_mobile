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

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 确认收货
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-09-18
 */
public class ConfirmRecvOrderGoodsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ConfirmRecvOrderGoodsServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String userid = request.getParameter("userid");
		final String orderid = request.getParameter("orderid");
		final String sessionid = request.getParameter("sessionid");
		final String product = request.getHeader("p");
		final String imei = request.getHeader("imei");
		final String ver = request.getHeader("ver");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userid, orderid, imei, sessionid)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("orderno", orderid);
				String url = Constants.BASE_URL_OLD + "ConfirmOrderGoods/";
				// 奥莱Android版本大于1.0.0使用新架构；奥莱iPhone版本大于3.3.1使用新架构；
				if (("101".equals(product) && StringUtil.compareVer(ver, "1.0.0") == 1) || ("1".equals(product) && StringUtil.compareVer(ver, "3.3.1") > 0)) {
					url = Constants.BASE_URL + "ConfirmOrderGoods/";
				}
				// 尚品Android版本大于1.0.3使用新架构；尚品iPhone版本大于1.0.2使用新架构；
				if (("102".equals(product) && StringUtil.compareVer(ver, "1.0.3") == 1) || ("2".equals(product) && StringUtil.compareVer(ver, "1.0.2") == 1)) {
					url = Constants.BASE_URL_SP + "ConfirmOrderGoods/";
				}
				try {
					String data = WebUtil.readContentFromGet(url, map);
					response.getWriter().print(data);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("ConfirmRecvOrderGoodsServlet：" + e);
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
			FileUtil.addLog(request, "orderconfirm", channelNo,
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
