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
 * 优惠券激活、列表
 * 
 * @author huangxiaoliang
 * 
 */
public class ActivateCouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ActivateCouponServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String couponCode = request.getParameter("couponCode");
		String type = request.getParameter("type");
		String buyId=request.getParameter("buyId");
		String orderSource =request.getParameter("orderSource");
		String pagesize = request.getParameter("pagesize");
		String pageindex = request.getParameter("pageindex");
		String shoptype = request.getParameter("shoptype");
		String coupontype = request.getParameter("coupontype");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		String productNo = request.getHeader("p");// 产品号
		final String ver = request.getHeader("ver");
		final String sessionId = request.getParameter("sessionid");
		final String imei = request.getHeader("imei");

		PrintWriter writer = response.getWriter();
		
		if (type == null) {
			type = "0";
		}

		if (!SessionUtil.validate(userId, imei, sessionId)) {
			JSONObject respobj = new JSONObject();
			respobj.put("code", Constants.ERROR);
			respobj.put("msg", Constants.ERROR_INVALIDPARAMS_PROMPT);
			respobj.put("content", "");
			writer.print(respobj.toString());
			writer.close();
			return;
		}

		if (StringUtil.isNotEmpty(userId, couponCode)) {
			// 开始绑定激活码
			String url = Constants.BASE_URL_SP_AL + "codemactchedphone/";
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("userid", userId);
			map.put("type", "coupon:" + couponCode);
			map.put("buyId", buyId);
			map.put("orderSource", orderSource);
			try {
				String data = WebUtil.readContentFromGet(url, map);
				JSONObject obj = JSONObject.fromObject(data);
				
				if (Constants.SUCCESS.equals(obj.getString("code"))) {
					map.remove("type");
					if ("0".equals(type)) {
						url = Constants.BASE_TRADE_URL + "order/Ticketlist/";
						data = WebUtil.readContentFromGet(url, map);
						obj = JSONObject.fromObject(data);
						if (Constants.SUCCESS.equals(obj.getString("code"))) {
							data = "{\"code\":\"0\",\"msg\":\"\",\"content\":{\"list\":" + obj.get("content") + "}}";
							response.getWriter().print(data);
						}  else {
							response.getWriter().print(data);
						}
					} else {
					    if("2".equals(productNo)&&(StringUtil.compareVer(ver, "2.5.8")<=0)){
					        pagesize="200";
					    }
						map.put("shoptype", StringUtils.isEmpty(shoptype) ? "1" : shoptype);// 尚品1，奥莱2
						map.put("coupontype", StringUtils.isEmpty(coupontype) ? "0" : coupontype);// 0未使用；1已使用； 3已过期；-1全部
						map.put("pagesize", StringUtils.isEmpty(pagesize) ? "100" : pagesize);
						map.put("pageindex", StringUtils.isEmpty(pageindex) ? "1" : pageindex);
						url = Constants.BASE_URL_SP_SP + "coupons/";
						if ("2".equals(shoptype)) {
							url = Constants.BASE_URL_AL_SP + "coupons/";
						}
						data = WebUtil.readContentFromGet(url, map);
						obj = JSONObject.fromObject(data);
						if (Constants.SUCCESS.equals(obj.getString("code"))) {
							response.getWriter().print(data);
						} else {
							response.getWriter().print(data);
						}
					}
				} else {
					response.getWriter().print(data);
				}
				
				// 记录访问日志
				FileUtil.addLog(request, "ActivateCouponServlet", channelNo,
						"userid", userId, 
						"couponCode", couponCode ,
						"type", type,
						"couponCode", couponCode);

			} catch (Exception e) {
				log.error("ActivateCouponServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					log.error("ActivateCouponServlet：" + e);
				}
			}
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				log.error("ActivateCouponServlet：" + e1);
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

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
