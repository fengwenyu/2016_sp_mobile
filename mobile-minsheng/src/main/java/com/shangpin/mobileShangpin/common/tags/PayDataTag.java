package com.shangpin.mobileShangpin.common.tags;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.shangpin.mobileShangpin.common.unionPay.UnionPayModelV2;
import com.shangpin.mobileShangpin.common.unionPay.UnionUtil;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;

import net.sf.json.JSONObject;


public class PayDataTag extends TagSupport {
	/**
	 * 生成银联支付paydata参数
	 */
	private static final long serialVersionUID = 4042062931490405758L;

	@Override
	public int doEndTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doStartTag() {
		System.out.println("doStartTag...");
		AccountVO user = WebUtil.getSessionUser();
		// 下面三个参数是为了在支付成功或者支付失败页面显示
		String orderid = pageContext.getRequest().getParameter("orderid");
		String ch = pageContext.getRequest().getParameter("ch");
		String amount = pageContext.getRequest().getParameter("amount");
		Map<String, String> returnMap = new HashMap<String, String>();
		try {
			returnMap = getUnionPayTn(orderid, user.getUserid());
			// 拼接银联回调resultUrl
			String resultUrl = UnionUtil.montageResultUrl(orderid, ch, amount);
			// 生成paydata参数用于银联支付(有交易流水号tn和 resultUrl以及usetestmode三个参数组成)
			String paydata = UnionUtil.returnPayDate(returnMap.get("tn"), resultUrl);
			pageContext.getOut().write(paydata);// 标签的返回值
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return EVAL_PAGE;
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
	private Map<String, String> getUnionPayTn(String orderId, String userId) throws Exception {
		Map<String, String> returnMap = new HashMap<String, String>();
		Map<String, String> totalFeeAndDateMap = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date orderDate;
		totalFeeAndDateMap = getTotalFeeAndDate(userId, orderId);
		String totalFee = totalFeeAndDateMap.get("totalFee");
		BigDecimal totalFee2 = new BigDecimal(totalFee);
		BigDecimal totalFee4 = new BigDecimal(100);
		// 订单金额
		totalFee = totalFee2.multiply(totalFee4).toString().split("[.]")[0];
		// 订单时间
		orderDate = sdf.parse(totalFeeAndDateMap.get("date"));
		long endtime = orderDate.getTime() + 60 * 1000 * 60;
		// 订单超时时间
		String expiretime = sdf.format(new Date(endtime));
		String ordertime = sdf.format(new Date(orderDate.getTime()));
		returnMap = new UnionPayModelV2().unionPayModel(orderId, ordertime, expiretime, totalFee, true);
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
	private Map<String, String> getTotalFeeAndDate(String userId, String orderId) {
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
