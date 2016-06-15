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
 * 根据用户名或手机号查找用户 : 需要单独提供接口， 内部逻辑, 用户手机号与验证码匹配成功后的操作: 手机号码和优惠券做绑定。需要单独提供接口。
 * json,领取优惠券的接口，错误代码需要，0成功，1失败，2未绑定手机号码
 * <p/>
 * 绑定优惠券的Servlet 需要提供，用户名，用户id,手机号码，激活码
 * 
 * @author sunweiwei
 * 
 */
public class BindCouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(BindCouponServlet.class);

	// ShangPinService shangPinService;

	@Override
	public void init() throws ServletException {
		super.init();
		// ServletContext sc = this.getServletContext();
		// WebApplicationContext ctx =
		// WebApplicationContextUtils.getWebApplicationContext(sc);
		// shangPinService = (ShangPinService)
		// ctx.getBean(ShangPinService.class);
	}

	/**
	 * 1.根据用户id查看是否绑定了手机号码，如果未绑定，需要返回2错误码（未绑定手机号码）。
	 * <p/>
	 * 2.如果绑定了手机号码，没问题，继续绑定优惠券。
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// String userName = request.getParameter("username");
		String userId = request.getParameter("userid");
		String coupon = request.getParameter("coupon");
		String channelNo = request.getHeader("ch");// 获取渠道号
		final String sessionId = request.getParameter("sessionid");
		final String imei = request.getHeader("imei");

		PrintWriter writer = response.getWriter();

		if (!SessionUtil.validate(userId, imei, sessionId)) {
			JSONObject respobj = new JSONObject();
			respobj.put("code", Constants.ERROR);
			respobj.put("msg", Constants.ERROR_INVALIDPARAMS_PROMPT);
			respobj.put("content", "");
			writer.print(respobj.toString());
			writer.close();
			return;
		}
		// // 验证用户是否绑定了手机
		// String checkUrl = Constants.BASE_URL_SP_SP + "checkUsername/";
		// String phoneNum = checkUser(checkUrl, userName);
		// if (!StringUtil.isNotEmpty(phoneNum)) {
		// JSONObject respobj = new JSONObject();
		// respobj.put("code", Constants.NO_BIND_PHONE);
		// respobj.put("msg", "");
		// respobj.put("content","");
		// writer.print(respobj.toString());
		// writer.close();
		// return;
		// }
		// 开始绑定激活码
		String url = Constants.BASE_URL_SP_AL + "codemactchedphone/";
		if (StringUtil.isNotEmpty(userId, coupon)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userId);
			map.put("type", "coupon:" + coupon);
			map.put("coupontype", "9");// 无限制
			try {
				String data = WebUtil.readContentFromGet(url, map);
				JSONObject obj = JSONObject.fromObject(data);
				writer.print(obj.toString());
				// 打印日志
				FileUtil.addLog(request, "codemactchedphone", channelNo, "userid", userId, "coupon", coupon);
			} catch (Exception e) {
				log.error("BindCouponServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					log.error("BindCouponServlet：" + e);
				}
			}
		}

	}

	/**
	 * 验证用户id是否绑定了手机号码
	 * 
	 * @param checkUrl
	 * @param userName
	 *            or phoneNum
	 * @return
	 */
	@SuppressWarnings("unused")
	private String checkUser(String checkUrl, String userName) {
		if (StringUtil.isNotEmpty(userName)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", userName);
			try {
				String data = WebUtil.readContentFromGet(checkUrl, map);
				JSONObject obj = JSONObject.fromObject(data);
				JSONObject content = null;
				String phone = null;
				String contentStr = obj.getString("content");
				if (contentStr != null && contentStr.length() > 2) {
					content = obj.getJSONObject("content");
					phone = content.getString("mobile");
				}

				if (StringUtil.isNotEmpty(phone)) {
					return phone;
				}
			} catch (Exception e) {
				log.error("sendsmscode：" + e);
			}
		}
		return null;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
