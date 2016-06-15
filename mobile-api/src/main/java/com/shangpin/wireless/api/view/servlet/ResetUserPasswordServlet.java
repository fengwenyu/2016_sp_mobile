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
 * 新版客户端重置登录密码
 * 
 * @author liling
 * 
 */
public class ResetUserPasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 6345447459447582412L;

	protected final Log log = LogFactory.getLog(ResetUserPasswordServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String phoneNum = request.getParameter("phoneNum");
		final String password = request.getParameter("password");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号

		if (StringUtil.isNotEmpty(password, phoneNum)) {
			// 检查用户
			String url = Constants.BASE_URL_SP_SP + "checkUsername/";
			String userId = checkUser(url, phoneNum);
			// 校验手机验证码
			try {
				Map<String, String> map = new HashMap<String, String>();
				url = Constants.SP_BASE_URL + "resetLoginPasswrod/";
				map.put("userid", userId);
				map.put("password", password);
				String data = WebUtil.readContentFromGet(url, map);
				response.getWriter().print(data);

			} catch (Exception e) {
				e.printStackTrace();
				log.error("ResetLoginPasswrod：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "resetUserPassword", channelNo,
					"phoneNum",phoneNum);
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}



	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 检查是否存在该用户、返回userid
	 * 
	 * @param checkUrl
	 * @param userName
	 *            or phoneNum
	 * @return
	 */
	private String checkUser(String checkUrl, String userName) {
		if (StringUtil.isNotEmpty(userName)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", userName);
			try {
				String data = WebUtil.readContentFromGet(checkUrl, map);
				JSONObject obj = JSONObject.fromObject(data);
				JSONObject content = null;
				String userid = null;
				String contentStr = obj.getString("content");
				if (contentStr != null && contentStr.length() > 2) {
					content = obj.getJSONObject("content");
					userid = content.getString("userid");
				}

				if (StringUtil.isNotEmpty(userid)) {
					return userid;
				}
			} catch (Exception e) {
				log.error("checkUsername：" + e);
			}
		}
		return null;
	}
}
