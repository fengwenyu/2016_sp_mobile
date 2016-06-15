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
 * 删除收货地址
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-08-29
 */
public class CouponCodeInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(CouponCodeInfoServlet.class);

//	@Override
//	public void init() throws ServletException {
//		super.init();
//	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String shoptype = request.getParameter("shoptype");
		String couponcode = request.getParameter("couponcode");
		String buysIds = request.getParameter("buysIds");
		String sessionid = request.getParameter("sessionid");
		String imei = request.getHeader("imei");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userid, couponcode, shoptype, buysIds, sessionid, imei)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				buysIds = buysIds.replace(',', '|');
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("shoptype", shoptype);
				map.put("couponcode", couponcode);
				map.put("buysIds", buysIds);
				String url = Constants.BASE_URL_AL_AL + "getCouponCodeInfo/";
				if ("1".equals(shoptype)) {
					url = Constants.BASE_URL_SP_AL + "getCouponCodeInfo/";
				}
				try {
					String data = WebUtil.readContentFromGet(url, map);
					response.getWriter().print(data);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("CouponCodeInfoServlet：" + e);
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
			FileUtil.addLog(request, "couponcodeinfo", channelNo,
					"userid", userid, 
					"shoptype", shoptype, 
					"couponcode", couponcode, 
					"buysIds", buysIds);
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
