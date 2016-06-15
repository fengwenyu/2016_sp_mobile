package com.shangpin.mobileAolai.common.unionPay.trade;

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

import net.sf.json.JSONObject;

import com.shangpin.mobileAolai.common.unionPay.UnionPayModelV2;
import com.shangpin.mobileAolai.common.unionPay.UnionUtil;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.FileUtil;
import com.shangpin.mobileAolai.common.util.StringUtil;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.vo.OrderVO;

/**
 * ipad银联支付接口
 */

public class Trade extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7146291767574671486L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
			doPost(request, response);
		}
		
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
			Map<String, String> returnMap = new HashMap<String, String>();
			String userid = request.getParameter("userId");
			String orderId = request.getParameter("orderId");

			if(StringUtil.isNotEmpty(userid)&&StringUtil.isNotEmpty(orderId)){
				try {
					returnMap = getUnionPayTradePaydata(orderId, userid);
				} catch (SignatureException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String resultUrl=request.getParameter("resultUrl");
				// 生成paydata参数用于银联支付(有交易流水号tn和 resultUrl以及usetestmode三个参数组成)
				String paydata = UnionUtil.returnPayDate(returnMap.get("tn"), resultUrl);
				request.setAttribute("paydata", paydata);
				String redirectURL = "";
				redirectURL = "uppay://uppayservice/?style=token&paydata=" + paydata;
				try {
					response.sendRedirect(redirectURL);
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 记录访问日志
				FileUtil.addLog(request, "unionpay/trade",//
						"42",//
						"userid", userid,//
						"orderid", orderId);
			}else{
				response.getWriter().print("请求参数不能为空");
			}
	}

		/**
		 * 生成银联支付交易流水号tn
		 * 
		 * @param userId
		 *            用户ID
		 * @param orderId
		 *            订单ID
		 * @return 交易流水号tn
		 * 
		 */
		private Map<String, String> getUnionPayTradePaydata(String orderId, String userId) throws Exception {
			Map<String, String> returnMap = new HashMap<String, String>();
			Map<String, String> totalFeeAndDateMap = new HashMap<String, String>();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date orderDate;
			totalFeeAndDateMap = getTotalFeeAndDate(userId, orderId);
			String totalFee = totalFeeAndDateMap.get("totalFee");
			String giftcardamount=totalFeeAndDateMap.get("giftcardamount");
			BigDecimal totalFee2 = new BigDecimal(totalFee);
			BigDecimal totalFee4 = new BigDecimal(100);
			if(StringUtil.isNotEmpty(giftcardamount)){
				BigDecimal giftcardamounttemp = new BigDecimal(giftcardamount);
				if(giftcardamounttemp.compareTo(new BigDecimal(0))==1){
					orderId=orderId+"gift"+giftcardamount;
					if(orderId.length()>40){
						orderId=orderId.substring(0, 40);
					}
				}
			}
			// 订单金额
			totalFee = totalFee2.multiply(totalFee4).toString().split("[.]")[0];
			// 订单时间
			orderDate = format.parse(totalFeeAndDateMap.get("date"));
			long endtime = orderDate.getTime() + 60 * 1000 * 60;
			// 订单超时时间
			String expiretime = sdf.format(new Date(endtime));
			String ordertime = sdf.format(new Date(orderDate.getTime()));
			returnMap = new UnionPayModelV2().unionPayModel(orderId, ordertime, expiretime, totalFee, true);
			return returnMap;
		}

//		/**
//		 * 获取订单总金额
//		 * 
//		 * @param userId
//		 *            用户ID
//		 * @param orderId
//		 *            订单ID
//		 * 
//		 * @return 返回订单总金额
//		 */
//		private Map<String, String> getTotalFeeAndDate(String userId, String orderId) {
//			Map<String, String> totalFeeAndDateMap = new HashMap<String, String>();
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("userid", userId);
//			map.put("orderid", orderId);
//			map.put("picw", Constants.SAMLL_PICTURE_WIDTH);
//			map.put("pich", Constants.SAMLL_PICTURE_HEIGHT);
//			String url = Constants.BASE_URL + "getorderdetail/";
//			String totalFee = "-1";
//			String date = "";
//			String giftcardamount = "";
//			try {
//				String data = WebUtil.readContentFromGet(url, map);
//				System.out.println("getorderdetail: " + data);
//				JSONObject obj = JSONObject.fromObject(data);
//				String code = obj.getString("code");
//				if (Constants.SUCCESS.equals(code)) {
//					obj = JSONObject.fromObject(obj.getJSONObject("content"));
//					totalFee = obj.getString("onlineamount");
//					giftcardamount=obj.getString("giftcardamount");
//					date = obj.getString("date");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			totalFeeAndDateMap.put("totalFee", totalFee);
//			totalFeeAndDateMap.put("date", date);
//			totalFeeAndDateMap.put("giftcardamount", giftcardamount);
//			return totalFeeAndDateMap;
//		}
//		
		
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
		private Map<String, String> getTotalFeeAndDate(String userId, String orderId) {
			Map<String, String> totalFeeAndDateMap = new HashMap<String, String>();
			String totalFee = "-1";
			String date = "";
			String giftcardamount = "";
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userId);
			map.put("mainOrderNo", orderId);
			String url = Constants.BASE_NEWAPI_URL_SP + "order/getorder";
			OrderVO orderVO = null;
			String json = null;
			try {
				// 获取订单详情json格式字符串
				json = WebUtil.readContentFromGet(url, map);
			} catch (Exception e) {
				e.printStackTrace();
				json = null;
			}
			if (null != json && !"".equals(json)) {
				JSONObject jsonObj = JSONObject.fromObject(json);
				if (!(jsonObj.isEmpty() || jsonObj.isNullObject()) && Constants.SUCCESS.equals(jsonObj.get("code"))) {
					orderVO = OrderVO.json2NewBean(jsonObj.getJSONObject("result"));
					totalFee = orderVO.getOnlineamount();
					giftcardamount = orderVO.getGiftcardamount();
					date = orderVO.getDate();
				}
			}
			totalFeeAndDateMap.put("totalFee", totalFee);
			totalFeeAndDateMap.put("date", date);
			totalFeeAndDateMap.put("giftcardamount", giftcardamount);
			return totalFeeAndDateMap;
		}
		
		
	}