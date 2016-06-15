package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

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
import com.shangpin.wireless.api.service.PushconfigAolaiService;
import com.shangpin.wireless.api.service.PushconfigShangpinService;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 获取Iphone客户端的push标识
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-09-08
 */
public class GetIosPushFlagServlet extends HttpServlet {
	//  奥莱，尚品需要区分开
	private static final long serialVersionUID = 1L;
	private PushconfigAolaiService pushconfigAolaiService;
	private PushconfigShangpinService pushconfigShangpinService;
	protected final Log log = LogFactory.getLog(GetIosPushFlagServlet.class);

	@Override
	public void init() throws ServletException {
		ServletContext sc = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		pushconfigAolaiService = (PushconfigAolaiService) ctx.getBean(PushconfigAolaiService.SERVICE_NAME);
		pushconfigShangpinService = (PushconfigShangpinService) ctx.getBean(PushconfigShangpinService.SERVICE_NAME);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String gender = request.getParameter("gender");
		String token = request.getParameter("deviceToken");
		String isOpen = request.getParameter("isopen");
		String msgType = request.getParameter("msgtype");
		String imei = request.getParameter("imei");
		String productNum = request.getHeader("p");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userId, gender, token, isOpen, msgType)) {
			token = token.replaceAll(" ", "");
			token = token.substring(1, token.length() - 1);
			int isopen = Integer.parseInt(isOpen.trim());
			int msgtype = Integer.parseInt(msgType.trim());
			try {
				if ("1".equals(productNum)){
					pushconfigAolaiService.savePushConfig(userId, gender, token, imei, isopen, msgtype);
				}else if ("2".equals(productNum)){
					pushconfigShangpinService.savePushConfig(userId, gender, token, imei, isopen, msgtype);
				}
				CommonAPIResponse apiResponse = new CommonAPIResponse();
				apiResponse.setCode(Constants.SUCCESS);
				apiResponse.setMsg("");
				response.getWriter().print(apiResponse.obj2Json());
			} catch (Exception e) {
				log.error("GetIosPushFlagServlet" + e);
				e.printStackTrace();
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "pushconfig", channelNo, 
					"userid", userId, 
					"gender", gender, 
					"token", token, 
					"isopen", isOpen, 
					"msgType", msgType);
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
