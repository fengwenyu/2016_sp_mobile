package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 根据用户名和手机号绑定用户 绑定手机号码的Servlet 需要提供手机号码，用户id
 * 
 * @author sunweiwei
 * 
 */
public class BindPhoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(BindPhoneServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String phoneNum = request.getParameter("phonenum");
		String verifycode = request.getParameter("smscode");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		String type = request.getParameter("type")==null?"":request.getParameter("type");//客户端传值，并且为1时，代表的是更换绑定的手机号
		final String sessionId = request.getParameter("sessionid");
		final String imei = request.getHeader("imei");
		PrintWriter writer = response.getWriter();
		if(!SessionUtil.validate(userId, imei, sessionId)){
			JSONObject respobj = new JSONObject();
			respobj.put("code", Constants.ERROR);
			respobj.put("msg", Constants.ERROR_INVALIDPARAMS_PROMPT);
			respobj.put("content","");
			writer.print(respobj.toString());
			writer.close();
			return;
		}
		// 验证用户是否绑定了手机
		if("".equals(type)){
			String checkUrl = Constants.BASE_URL_SP_SP + "checkUsername/";
			String phone = checkUser(checkUrl, phoneNum);
			if (StringUtil.isNotEmpty(phone)) {
				JSONObject respobj = new JSONObject();
				respobj.put("code", Constants.ERROR_PHONE_HAS_BIND);
				respobj.put("msg", Constants.PHONE_HAS_BIND_PROMPT);
				respobj.put("content","");
				writer.print(respobj.toString());
				writer.close();
				return;
			}
		}
		//判断验证码是否正确，如果错误，返回错误代码
		if(!verifySMSCode(userId, phoneNum, verifycode)){
			JSONObject respobj = new JSONObject();
			respobj.put("code", Constants.SMS_CODE_ERROR);
			respobj.put("msg", Constants.SMS_CODE_PROMPT);
			respobj.put("content","");
			writer.print(respobj.toString());
			writer.close();
			return;
		}
		//用户绑定手机号码
		String url = Constants.BASE_URL_SP_AL + "codemactchedphone/";
		if (StringUtil.isNotEmpty(userId, phoneNum)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userId);
			map.put("type", "bind:" + phoneNum);
			try {
				String data = WebUtil.readContentFromGet(url, map);
				JSONObject obj = JSONObject.fromObject(data);
				writer.print(obj.toString());
				writer.close();

				// 打印日志
				FileUtil.addLog(request, "codemactchedphone", channelNo,
						"userid", userId,
						"phonenum", phoneNum);
			} catch (Exception e) {
				log.error("codemactchedphone：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					log.error("codemactchedphone：" + e);
				}
			}
		}

	}

	private boolean verifySMSCode(String userId, String phoneNum,
			String verifycode) {
		String url = Constants.BASE_URL_SP_AL + "verifyphoneandcode/";
		if (StringUtil.isNotEmpty(userId, phoneNum, verifycode)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userId);
			map.put("phonenum", phoneNum);
			map.put("verifycode", verifycode);
			try {
				String data = WebUtil.readContentFromGet(url, map);
				JSONObject obj = JSONObject.fromObject(data);
				String code = obj.getString("code");
				if (Constants.SUCCESS.equals(code)) {
					return true;
				}
			} catch (Exception e) {
				log.error("verifyphoneandcode：" + e);
			}
		}
		return false;

	}
	
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

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
