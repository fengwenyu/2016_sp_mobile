package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;

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
 * 优惠券列表接口
 * 
 */
public class CouponsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(CouponsServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String pagesize = request.getParameter("pagesize");
		String pageindex = request.getParameter("pageindex");
		String shoptype = request.getParameter("shoptype");
		String coupontype = request.getParameter("coupontype");
		String sessionid = request.getParameter("sessionid");
		String imei = request.getHeader("imei");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userid, shoptype, imei, sessionid)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				// 组装参数
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("shoptype", shoptype);// 尚品1，奥莱2
				map.put("coupontype", StringUtils.isEmpty(coupontype) ? "0" : coupontype);// 0未使用；1已使用； 3已过期；-1全部
				map.put("pagesize", StringUtils.isEmpty(pagesize) ? "20" : pagesize);
				map.put("pageindex", StringUtils.isEmpty(pageindex) ? "1" : pageindex);
				String url = Constants.BASE_URL_SP_SP + "coupons/";
				if ("2".equals(shoptype)) {
					url = Constants.BASE_URL_AL_SP + "coupons/";
				}
				try {
					String data = WebUtil.readContentFromGet(url, map);
					response.getWriter().print(data);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("CouponsServlet：" + e);
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				// 记录访问日志
				FileUtil.addLog(request, "coupons", channelNo,
						"userid", userid, 
						"shoptype", shoptype, 
						"coupontype", coupontype, 
						"pageindex", pageindex, 
						"pagesize", pagesize);
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
