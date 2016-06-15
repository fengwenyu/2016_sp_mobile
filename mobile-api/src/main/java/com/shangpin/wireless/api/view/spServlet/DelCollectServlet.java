package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.util.HashMap;

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
 * 删除收藏商品
 * 
 * @Author:wangwenguan
 * @CreatDate: 2013-12-28
 */
public class DelCollectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(DelCollectServlet.class);


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String imei = request.getHeader("imei");
		final String sessionid = request.getParameter("sessionid");
		final String userid = request.getParameter("userid");
		final String favoriteproductid = request.getParameter("favoriteproductid");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		String url = Constants.SP_BASE_URL + "delCollect/";
		if (StringUtil.isNotEmpty(userid, sessionid, favoriteproductid)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				try {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("userid", userid);
					map.put("favoriteproductid", favoriteproductid);
					map.put("shoptyp", "1");
					String data = WebUtil.readContentFromGet(url, map);
					response.getWriter().print(data);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("DelCollectServlet：" + e);
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
			FileUtil.addLog(request, "delcollect", channelNo,
					"userid", userid, 
					"favoriteproductid", favoriteproductid);
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
