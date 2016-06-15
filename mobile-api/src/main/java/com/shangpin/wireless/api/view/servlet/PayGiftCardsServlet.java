package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
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
 * 礼品卡支付接口
 * 
 * @CreatDate: 2013-06-24
 */
public class PayGiftCardsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(PayGiftCardsServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String productNo = request.getHeader("p");
		final String userid = request.getParameter("userid");
		final String email = request.getParameter("email");
		final String password = request.getParameter("password");// 礼品卡密码
		final String giftcardno = request.getParameter("giftcardno");// 礼品卡字符串（多个用"|"分割）
		final String orderid = request.getParameter("orderid");
		final String paytype = request.getParameter("paytype");
		final String sessionid = request.getParameter("sessionid");
		final String imei = request.getHeader("imei");
		final String version = request.getHeader("ver");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		String shoptype = "";
		if (StringUtil.isNotEmpty(userid, password, email, giftcardno, sessionid, imei, productNo)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				if ("1".equals(productNo) || "101".equals(productNo)) {// 奥莱
					shoptype = "2";
				}
				if ("2".equals(productNo) || "102".equals(productNo)) {// 尚品
					shoptype = "1";
				}
				try {
					JSONArray list = new JSONArray();
					String cardjson = null;
					Map<String, String> cardmap = new HashMap<String, String>();
					cardmap.put("userid", userid);
					cardmap.put("pageindex", "1");
					cardmap.put("pagesize", "100");
					cardmap.put("type", "2");
					cardmap.put("status", "0");
					cardmap.put("shoptype", shoptype);
					String cardurl = null;
					if (("101".equals(productNo) && StringUtil.compareVer(version, "1.0.0") == 1) || ("1".equals(productNo) && StringUtil.compareVer(version, "3.3.1") > 0)) {
						cardurl = Constants.BASE_URL_AL_AL + "giftCards/";
					}
					// 尚品Android版本大于1.0.3使用新架构；尚品iPhone版本大于1.0.2使用新架构；
					if (("102".equals(productNo) && StringUtil.compareVer(version, "1.0.3") == 1) || ("2".equals(productNo) && StringUtil.compareVer(version, "1.0.2") == 1)) {
						cardurl = Constants.BASE_URL_SP_AL + "giftCards/";
					}
					// 获取订单详情json格式字符串
					cardjson = WebUtil.readContentFromGet(cardurl,cardmap);
					if (null != cardjson && !"".equals(cardjson)) {
						JSONObject jsonObj = JSONObject.fromObject(cardjson);
						if (!(jsonObj.isEmpty() || jsonObj.isNullObject())&& Constants.SUCCESS.equals(jsonObj.get("code"))) {
							JSONObject object = jsonObj.getJSONObject("content");
							list = object.getJSONArray("list");

						}
					}
					Map<String, String> map = new HashMap<String, String>();
					map.put("userid", userid);
					map.put("password", password);
					map.put("orderid", orderid);
					map.put("paytype", paytype);
					if ("1".equals(productNo) || "101".equals(productNo)) {// 奥莱
						shoptype = "2";
					}
					if ("2".equals(productNo) || "102".equals(productNo)) {// 尚品
						shoptype = "1";
					}
					map.put("shoptype", shoptype);
					String url = Constants.BASE_TRADE_URL + "order/payGiftCards/";			
					String data = WebUtil.readContentFromGet(url, map);					
					JSONObject apiResult=JSONObject.fromObject(data);	
					JSONObject contentObj=JSONObject.fromObject(apiResult.getString("content"));
					JSONObject obj = new JSONObject();
					JSONObject  content = new JSONObject();
					if("0".equals(apiResult.getString("code"))){					    					    
						obj.put("code", apiResult.getString("code"));
						obj.put("msg", apiResult.getString("msg"));
						content.put("payresult", contentObj.get("payresult"));
						content.put("count", "0");
						content.put("amount", contentObj.get("amount"));
						content.put("onlineamount", contentObj.get("onlineamount"));
						content.put("payamount", contentObj.get("payamount"));
						content.put("discountamount", contentObj.get("discountamount"));
						content.put("giftcardamount", contentObj.get("giftcardamount"));
						for (int i = 0; i < list.size(); i++) {
							JSONObject object = (JSONObject) list.get(i);
							object.put("useamount", object.getString("amount"));
						}
						content.put("list", list);
						obj.put("content", content);
							
					}else{
						obj.put("code", apiResult.getString("code"));
						obj.put("msg", apiResult.getString("msg"));
						obj.put("content", content);
					}
					response.getWriter().print(obj.toString());
				} catch (Exception e) {
					e.printStackTrace();
					log.error("PayGiftCardsServlet：" + e);
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
			FileUtil.addLog(request, "payGiftCards", channelNo,
					"userid", userid, 
					"shoptype", shoptype,
					"orderid", orderid,
					"paytype", paytype,
					"imei", imei,
					"productNo", productNo);
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
