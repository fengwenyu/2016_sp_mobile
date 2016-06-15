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
 * 选择礼品卡接口
 * 
 * @CreatDate: 2013-06-24
 */
public class SelectGiftCardsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(SelectGiftCardsServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String productNo = request.getHeader("p");
		final String giftcardno = request.getParameter("giftcardno");
		final String orderid = request.getParameter("orderid");
		final String userid = request.getParameter("userid");
		final String sessionid = request.getParameter("sessionid");
		final String imei = request.getHeader("imei");
		final String version = request.getHeader("ver");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		String shoptype = "";
		if (StringUtil.isNotEmpty(userid, sessionid, imei, productNo)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("giftcardno", giftcardno);
				map.put("orderid", orderid);
				if ("1".equals(productNo) || "101".equals(productNo)) {// 奥莱
					shoptype = "2";
				}
				if ("2".equals(productNo) || "102".equals(productNo)) {// 尚品
					shoptype = "1";
				}
				map.put("shoptype", shoptype);
				try {
					JSONObject obj = new JSONObject();
					JSONObject content = new JSONObject();
					String[] count=giftcardno.split("[|]");
					content.put("count", String.valueOf(count.length));
					Map<String, String> ordermap = new HashMap<String, String>();
					ordermap.put("userid", userid);
					ordermap.put("mainOrderNo", orderid);
					String getorderurl = Constants.BASE_TRADE_URL + "order/getorder";
					String json = null;
					float giftbardbalance=0;
					float onlineamount=0;
					try {
						// 获取订单详情json格式字符串
						json = WebUtil.readContentFromGet(getorderurl, ordermap);
					} catch (Exception e) {
						e.printStackTrace();
						json = null;
					}
					if (null != json && !"".equals(json)) {
						JSONObject jsonObj = JSONObject.fromObject(json);
						if (!(jsonObj.isEmpty() || jsonObj.isNullObject()) && Constants.SUCCESS.equals(jsonObj.get("code"))) {
							JSONObject object=jsonObj.getJSONObject("result");
							content.put("amount",  object.get("totalamount").toString() );
							content.put("onlineamount",  object.get("onlineamount").toString()  );
							content.put("payamount",  object.get("payamount").toString() );
							content.put("discountamount", object.get("discountamount").toString() );
							onlineamount=Float.valueOf( object.get("onlineamount").toString());
							giftbardbalance=Float.valueOf(  object.get("giftbardbalance").toString());
						}
					}
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
					JSONArray list=new JSONArray();
					String cardjson = null;
					try {
						// 获取订单详情json格式字符串
						cardjson = WebUtil.readContentFromGet(cardurl, cardmap);
						if (null != cardjson && !"".equals(cardjson)) {
							JSONObject jsonObj = JSONObject.fromObject(cardjson);
							if (!(jsonObj.isEmpty() || jsonObj.isNullObject()) && Constants.SUCCESS.equals(jsonObj.get("code"))) {
								JSONObject object=jsonObj.getJSONObject("content");
								list=object.getJSONArray("list");
								
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						cardjson = null;
					}
					for(int i=0;i<list.size();i++){
						JSONObject object=(JSONObject) list.get(i);
						object.put("useamount", object.getString("amount"));
					
					}
					if(onlineamount>=giftbardbalance){
						content.put("giftcardamount", String.valueOf(giftbardbalance) );
					}else{
						content.put("giftcardamount", String.valueOf(onlineamount) );
					}
					
					content.put("list",  list );
					obj.put("code", "0");
					obj.put("msg",  "" );
					obj.put("content",  content );
					response.getWriter().print(obj.toString());
				} catch (Exception e) {
					e.printStackTrace();
					log.error("SelectGiftCardsServlet：" + e);
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
			FileUtil.addLog(request, "selectGiftCards", channelNo,
					"userid", userid, 
					"imei", imei, 
					"shoptype", shoptype);
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
