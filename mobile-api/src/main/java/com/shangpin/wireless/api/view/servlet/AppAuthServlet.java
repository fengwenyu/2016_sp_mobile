package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.AppAuthAPIResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * APP是否登录
 * @author mengqiang@shangpin.com
 *
 */
public class AppAuthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(AppAuthServlet.class);

	// @Override
	// public void init() throws ServletException {
	// ServletContext sc = this.getServletContext();
	// WebApplicationContext ctx =
	// WebApplicationContextUtils.getWebApplicationContext(sc);
	// accountService = (AccountService)
	// ctx.getBean(AccountService.SERVICE_NAME);
	// }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String imei = request.getParameter("imei");
		String token = request.getParameter("token");
		
		if (StringUtil.isNotEmpty(userId, imei, token)) {
			boolean isLogin = SessionUtil.validate(userId, imei, token);
			AppAuthAPIResponse apiResponse = new AppAuthAPIResponse();
			apiResponse.setCode(Constants.SUCCESS);
			apiResponse.setLogin(isLogin);
			response.getWriter().print(apiResponse.obj2Json());// obj2json
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
