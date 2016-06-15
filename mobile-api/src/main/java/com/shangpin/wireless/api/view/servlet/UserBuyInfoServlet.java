package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 获取用户购物车、物流、订单数量
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-08-29
 */
public class UserBuyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(UserBuyInfoServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String userid = request.getParameter("userid");
		final String checktime = request.getParameter("checktime");
		final String shoptype = request.getParameter("shoptype");
		String productNum = request.getHeader("p");
		final String ver = request.getHeader("ver");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
//		productNum = "102";
		if (StringUtil.isNotEmpty(userid)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userid);
			map.put("checktime", checktime == null ? "" : checktime);
			String url = null;
			if (StringUtil.isNotEmpty(shoptype)) {
				map.put("shoptype", shoptype);
				if ("1".equals(shoptype)) {
					url = Constants.BASE_URL_SP + "UserBuyInfo/";
				} else {
					url = Constants.BASE_URL + "UserBuyInfo/";
				}
			} else {
				// 奥莱Android版本大于1.0.0使用新架构；奥莱iPhone版本大于3.3.0使用新架构；
				if ("101".equals(productNum) || "1".equals(productNum)) {
					url = Constants.BASE_URL + "UserBuyInfo/";
					map.put("shoptype", "2");
				}
				// 尚品Android版本大于1.0.3使用新架构；尚品iPhone版本大于1.0.2使用新架构；
				if ("102".equals(productNum) || "2".equals(productNum)) {
					url = Constants.BASE_URL_SP + "UserBuyInfo/";
					map.put("shoptype", "1");
				}
			}
			
			try {
				String data = WebUtil.readContentFromGet(url, map);
				JSONObject obj = JSONObject.fromObject(data);
				JSONObject result = new JSONObject();
				JSONObject content= obj.getJSONObject("content");
				content.put("exchangeflag", "0");
				content.put("exchangeurl", "");
				//2.9.9新加开始
				Map<String, String> mapParams = new HashMap<String, String>();
				mapParams.put("readStatus", "0");
				mapParams.put("userId", userid);
				String urlStr = Constants.BASE_URL_NEWSP + "message/findUserMessageTotalCountByCondition";
				String dataStr = WebUtil.readContentFromGet(urlStr, mapParams);
				JSONObject msgObj = JSONObject.fromObject(dataStr);
				String msgCount="";
				if("0".equals(msgObj.getString("code"))){
					JSONObject msgTotal=msgObj.getJSONObject("content");
					msgCount = (String) msgTotal.get("total");
				}
				content.put("unreadMsgCount", msgCount);
				//2.9.9新加结束
				result.put("code", obj.getString("code"));
				result.put("msg", obj.getString("msg"));
				result.put("content", content);
				response.getWriter().print(result.toString());

			} catch (Exception e) {
				e.printStackTrace();
				log.error("UserBuyInfoServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "userbuyinfo", channelNo,
					"userid", userid);
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
