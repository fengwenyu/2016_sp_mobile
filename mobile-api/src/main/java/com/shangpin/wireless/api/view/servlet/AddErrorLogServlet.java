package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shangpin.wireless.api.api2client.domain.CommonAPIResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.ErrorLog;
import com.shangpin.wireless.api.service.ErrorLogService;
import com.shangpin.wireless.api.util.DBType;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 添加错误地址接口
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-09-06a
 */
public class AddErrorLogServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(AddErrorLogServlet.class);
	private ErrorLogService errorLogService;

	@Override
	public void init() throws ServletException {
		ServletContext sc = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		errorLogService = (ErrorLogService) ctx.getBean(ErrorLogService.SERVICE_NAME);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String platform = request.getHeader("os");
		String systemVersoin = request.getHeader("osv");
		String imei = request.getHeader("imei");
		String apn = request.getHeader("apn");
		String productNum = request.getHeader("p");
		String channelNum = request.getHeader("ch");
		String productVersion = request.getHeader("ver");
		String shortmsg = request.getParameter("short");
		String longmsg = request.getParameter("long");
		String phoneType = request.getParameter("model");
		// String ip = request.getParameter("ip");
		// String city = request.getParameter("city");
		if (StringUtil.isNotEmpty(platform, systemVersoin, imei, apn, productNum, channelNum, productVersion, longmsg, phoneType)) {

			try {
				ErrorLog errorLog = new ErrorLog();
				errorLog.setPlatform(platform);
				errorLog.setSystemVersoin(systemVersoin);
				errorLog.setPhoneType(phoneType);
				errorLog.setImei(imei);
				errorLog.setApn(apn);
				errorLog.setProductNum(Long.parseLong(productNum.trim()));
				errorLog.setChannelNum(Long.parseLong(channelNum.trim()));
				errorLog.setProductVersion(productVersion);
				errorLog.setShortmsg(shortmsg);
				errorLog.setLongmsg(longmsg);
				errorLog.setCreateTime(new Date());// 操作时间
				errorLogService.save(errorLog, DBType.dataSourceAPI.toString());
				CommonAPIResponse apiResponse = new CommonAPIResponse();
				apiResponse.setCode(Constants.SUCCESS);
				apiResponse.setMsg("");
				response.getWriter().print(apiResponse.obj2Json());
			} catch (Exception e) {
				e.printStackTrace();
				log.error("AddErrorLogServlet：" + e);
			}
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
