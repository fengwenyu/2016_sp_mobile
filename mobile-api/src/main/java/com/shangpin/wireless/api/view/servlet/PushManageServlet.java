package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shangpin.wireless.api.service.PushManageService;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * push信息接口
 * 
 * @Author yumeng
 * @CreateDate: 2013-02-22
 */
public class PushManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(PushManageServlet.class);
	private PushManageService pushManageService;

	@Override
	public void init() throws ServletException {
		ServletContext sc = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		pushManageService = (PushManageService) ctx.getBean(PushManageService.SERVICE_NAME);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String platform = request.getHeader("os");
		String imei = request.getHeader("imei");
		String productNum = request.getHeader("p");
		String channelNum = request.getHeader("ch");
		String userId = request.getParameter("userid");
		String gender = request.getParameter("gender");
		if (StringUtils.isEmpty(gender)) {
			gender = "2";
		}
//		try {
//			productNum = "102";
//			response.getWriter().print(pushManageService.updateAndGetAndroidPushInfo(username, gender,productNum));
//			return;
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error("PushManageServlet：" + e);
//		}
		if (StringUtil.isNotEmpty(platform, imei, productNum, channelNum)) {
			try {
				response.getWriter().print(pushManageService.updateAndGetAndroidPushInfo(userId, gender,productNum));
			} catch (Exception e) {
				e.printStackTrace();
				log.error("PushManageServlet：" + e);
			}
			// 记录访问日志
			FileUtil.addLog(request, "pushManage", channelNum,
					"userId", userId);
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
