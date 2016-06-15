package com.shangpin.mobileShangpin.common.unionPay.trade;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.SignatureException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shangpin.mobileShangpin.common.unionPay.UnionPayModelV2;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.WebUtil;

import net.sf.json.JSONObject;


public class Trade extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7146291767574671486L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		Map<String, String> returnMap = new HashMap<String, String>();
//		String userid = request.getParameter("userId");
//		String orderId = request.getParameter("orderId");
//		try {
//			returnMap = getUnionPayTradePaydata(orderId, userid);
//		} catch (SignatureException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		String callflag = "ipad";
//	//	String resultUrl = UnionUtil.montageResultUrl(orderId, returnMap.get("orderTime"), returnMap.get("signature"), callflag);
//		String paydata = UnionUtil.returnPayDate(returnMap.get("tn"), resultUrl);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF/pages//unionpay.jsp");
//		try {
//			request.setAttribute("paydata", paydata);
//			dispatcher.forward(request, response);
//		} catch (ServletException e1) {
//			e1.printStackTrace();
//		}
//		String redirectURL = "";
//		redirectURL = "uppay://uppayService/?style=token&paydata=" + paydata;
//		try {
//			response.sendRedirect(redirectURL);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		// 记录访问日志
//		FileUtil.addLog(request, "unionpay/trade",//
//				"",//
//				"userid", userid,//
//				"orderid", orderId);
	}

	public Map<String, String> getUnionPayTradePaydata(String orderId, String userId) throws SignatureException, Exception {
		Map<String, String> returnMap = new HashMap<String, String>();
		Map<String, String> totalFeeAndDateMap = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date orderDate;
		totalFeeAndDateMap = getTotalFeeAndDate(userId, orderId);
		String totalFee = totalFeeAndDateMap.get("totalFee");
		BigDecimal totalFee2 = new BigDecimal(totalFee);
		BigDecimal totalFee4 = new BigDecimal(100);
		totalFee = totalFee2.multiply(totalFee4).toString().split("[.]")[0];
		orderDate = sdf.parse(totalFeeAndDateMap.get("orderDate"));
		long endtime = orderDate.getTime() + 60 * 1000 * 60;
		String expiretime = sdf.format(new Date(endtime));
		String ordertime = sdf.format(new Date(orderDate.getTime()));
		returnMap = new UnionPayModelV2().unionPayModel(orderId, ordertime, expiretime, totalFee, true);
		returnMap.put("orderTime", ordertime);
		return returnMap;
	}

	/**
	 * 获取订单总金额
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderId
	 *            订单ID
	 * 
	 * @return 返回订单总金额
	 */
	public Map<String, String> getTotalFeeAndDate(String userId, String orderId) {
		Map<String, String> totalFeeAndDateMap = new HashMap<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userId);
		map.put("orderid", orderId);
		String url = Constants.BASE_URL + "getorder/";
		String totalFee = "-1";
		String date = "";
		try {
			String data = WebUtil.readContentFromGet(url, map);
			JSONObject obj = JSONObject.fromObject(data);
			String code = obj.getString("code");
			if (Constants.SUCCESS.equals(code)) {
				obj = JSONObject.fromObject(obj.getJSONObject("content"));
				totalFee = obj.getString("onlineamount");
				date = obj.getString("date");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		totalFeeAndDateMap.put("totalFee", totalFee);
		totalFeeAndDateMap.put("date", date);
		return totalFeeAndDateMap;
	}
}