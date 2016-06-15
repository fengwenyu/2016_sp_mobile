package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;

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
 * 优惠券列表接口，返回按状态分组的列表
 * 
 */
public class CouponGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(CouponGroupServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String pagesize = request.getParameter("pagesize");
		String pageindex = request.getParameter("pageindex");
		String shoptype = request.getParameter("shoptype");
		String sessionid = request.getParameter("sessionid");
		String imei = request.getHeader("imei");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userid, shoptype, imei, sessionid)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				// 组装参数
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("shoptype", shoptype);// 尚品1，奥莱2
				map.put("coupontype", "0");// 0未使用；1已使用； 3已过期；-1全部
				map.put("pagesize", StringUtils.isEmpty(pagesize) ? "100" : pagesize);
				map.put("pageindex", StringUtils.isEmpty(pageindex) ? "1" : pageindex);
				try {
					String available = getcoupon(map, shoptype);
					map.put("coupontype", "1");
					String used = getcoupon(map, shoptype);
					map.put("coupontype", "3");
					String expire = getcoupon(map, shoptype);
					StringBuffer buffer = new StringBuffer();
					buffer.append("{\"code\":\"0\",\"msg\":\"\",\"content\":{");
					buffer.append("\"available\":");
					if (StringUtils.isEmpty(available)) {
						buffer.append("[],");
					} else {
						buffer.append(available + ",");
					}
					buffer.append("\"used\":");
					if (StringUtils.isEmpty(used)) {
						buffer.append("[],");
					} else {
						buffer.append(used + ",");
					}
					buffer.append("\"expire\":");
					if (StringUtils.isEmpty(expire)) {
						buffer.append("[]");
					} else {
						buffer.append(expire);
					}
					buffer.append("}}");
					response.getWriter().print(buffer.toString());
				} catch (Exception e) {
					e.printStackTrace();
					log.error("CouponGroupServlet：" + e);
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				// 记录访问日志
				FileUtil.addLog(request, "couponGroup", channelNo,
						"userid", userid, 
						"shoptype", shoptype);
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

	/**
	 * 获取优惠券列表数据
	 * 
	 * @param map
	 *            参数集合
	 * @return 优惠券列表字符串
	 * @throws Exception
	 */
	private String getcoupon(HashMap<String, String> map, String shoptype) throws Exception {
		String url = Constants.BASE_URL_SP_SP + "coupons/";
		if ("2".equals(shoptype)) {
			url = Constants.BASE_URL_AL_SP + "coupons/";
		}
		String couponJSON = null;
		couponJSON = WebUtil.readContentFromGet(url, map);
		if (StringUtils.isNotEmpty(couponJSON)) {
			JSONObject jsonObj = JSONObject.fromObject(couponJSON);
			if (null != jsonObj && Constants.SUCCESS.equals(jsonObj.get("code"))) {
				JSONObject contentObj = (JSONObject) jsonObj.get("content");
				if (!"[]".equals(contentObj.get("list").toString())) {
					return contentObj.get("list").toString();
				}
			}
		}
		return null;
	}
}
