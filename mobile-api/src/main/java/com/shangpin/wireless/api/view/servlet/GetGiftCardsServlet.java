package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 获取用户礼品卡接口
 * 
 * @CreatDate: 2013-06-24
 */
public class GetGiftCardsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(GetGiftCardsServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String productNo = request.getHeader("p");
		final String pageindex = request.getParameter("pageindex");
		final String pagesize = request.getParameter("pagesize");
		final String type = request.getParameter("type");
		final String status = request.getParameter("status");
		final String userid = request.getParameter("userid");
		final String sessionid = request.getParameter("sessionid");
		final String imei = request.getHeader("imei");
		final String version = request.getHeader("ver");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userid, type,  sessionid, imei, productNo)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("pageindex", StringUtils.isEmpty(pageindex)?"0":pageindex);
				map.put("pagesize", pagesize);
				map.put("type", type);
				map.put("status", StringUtils.isEmpty(status)?"0":pageindex);
				String shoptype = null;
				if ("1".equals(productNo) || "101".equals(productNo)) {// 奥莱
					shoptype = "2";
				}
				if ("2".equals(productNo) || "102".equals(productNo)) {// 尚品
					shoptype = "1";
				}
				map.put("shoptype", shoptype);
				String url = Constants.BASE_URL_OLD + "giftCards/";
				// 奥莱Android版本大于1.0.0使用新架构；奥莱iPhone版本大于3.3.0使用新架构；
				if (("101".equals(productNo) && StringUtil.compareVer(version, "1.0.0") == 1) || ("1".equals(productNo) && StringUtil.compareVer(version, "3.3.1") > 0)) {
					url = Constants.BASE_URL_AL_AL + "giftCards/";
				}
				// 尚品Android版本大于1.0.3使用新架构；尚品iPhone版本大于1.0.2使用新架构；
				if (("102".equals(productNo) && StringUtil.compareVer(version, "1.0.3") == 1) || ("2".equals(productNo) && StringUtil.compareVer(version, "1.0.2") == 1)) {
					url = Constants.BASE_URL_SP_AL + "giftCards/";
				}
				try {
					String data = WebUtil.readContentFromGet(url, map);
					response.getWriter().print(data);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("GetGiftCardsServlet：" + e);
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				// 记录访问日志
				FileUtil.addLog(request, "giftCards", channelNo,
						"userid", userid, 
						"imei", imei, 
						"shoptype", shoptype);
			} else {
				try {
					WebUtil.sendErrorNoSessionid(response);
				} catch (Exception e) {
					e.printStackTrace();
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
