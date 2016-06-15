package com.shangpin.wireless.api.view.spServlet;

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
 * 获取物流列表
 * 
 * @Author:yumeng
 * @CreatDate: 2012-12-25
 */
public class LogisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(LogisticsServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String productNo = request.getHeader("p");
		final String userid = request.getParameter("userid");
		final String pageindex = request.getParameter("pageindex");
		final String pagesize = request.getParameter("pagesize");
		final String sessionid = request.getParameter("sessionid");
		final String isall = request.getParameter("isall");
		final String imei = request.getHeader("imei");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		String ordertype = "";
		String picw = request.getParameter("picw");
		String pich = request.getParameter("pich");
		if (StringUtil.isNotEmpty(userid, pageindex, sessionid, imei, productNo)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				if (Constants.SP_IPHONE_PRODUCTNO.equals(productNo) || Constants.AOLAI_IPHONE_PRODUCTNO.equals(productNo)) {
					picw = "132";
					pich = "176";
				}
				String url = Constants.BASE_URL_SP_SP + "Logistics/";
				if ("1".equals(productNo) || "101".equals(productNo)) {
					ordertype = "2";
					url = Constants.BASE_URL_AL_SP + "Logistics/";
				}
				if ("2".equals(productNo) || "102".equals(productNo)) {
					ordertype = "1";
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("pageindex", pageindex);
				map.put("pagesize", pagesize);
				map.put("picw", picw);
				map.put("pich", pich);
				map.put("isall", StringUtils.isEmpty(isall)?"1":isall);
				map.put("ordertype", ordertype);
				try {
					String data = WebUtil.readContentFromGet(url, map);
					if(data != null){
						data = data.replace("null", "");
					}
					response.getWriter().print(data);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("LogisticsServlet：" + e);
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
			FileUtil.addLog(request, "shangpinlogistics", channelNo,
					"userid", userid, 
					"pageindex", pageindex, 
					"pagesize", pagesize);
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
