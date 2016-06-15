package com.shangpin.mobileShangpin.common.minshengPay;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.shangpin.mobileShangpin.common.alipay.util.StringUtil;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.DateTimeUtil;
import com.shangpin.mobileShangpin.common.util.FileUtil;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;
import com.shangpin.mobileShangpin.platform.vo.BalanceServerResponse;

public class MsPay extends HttpServlet {
	private static final long serialVersionUID = -3035307235076650766L;
	//private static final String SHOP_ID = "06005";
	private static final String SHOP_ID = "01287";
	//民生支付回调地址修改为线上外网环境
	String basePath = "http://cmbc.m.shangpin.com";
	//String basePath = "http://123.124.211.164:9999/mobileshangpin";
	//民生支付回调地址修改为线上外网环境
	//String callbackUrl = "http://123.124.211.164:9999/mobileshangpin/minshengpay/notityCmbc";
	String callbackUrl = "http://cmbc.m.shangpin.com/minshengpay/notityCmbc";
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AccountVO user = WebUtil.getSessionUser();
		String channelNo = request.getParameter(Constants.CHANNEL_PARAM_NAME);
		if(StringUtil.isNotEmpty(channelNo))
			request.getSession().setAttribute(Constants.CHANNEL_PARAM_NAME, channelNo);
		else
			channelNo = "3";
		if(user == null)
			response.sendRedirect(basePath+"/accountaction!loginui?loginForm=2");
		String userid = user.getUserid();
		String orderId = request.getParameter("orderId");
		String payval=request.getParameter("payVal");
		System.out.println("payval"+payval);
		String mainpaymode = null;
		String subpaymode = null;
		if("19".equals(payval)){
			mainpaymode = "19";
			subpaymode = "49";
		}else if("25".equals(payval)){
			mainpaymode = "25";
			subpaymode = "53";
		}
		
		BalanceServerResponse balanceServerResponse = new BalanceServerResponse();
		//获取订单信息：订单编号，订单时间yyyyMMDDHHmmss，订单金额100.00，订单状态，订单状态描述
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("mainOrderNo", orderId);
		String url = Constants.BASE_TRADE_URL + "order/getorder";
		String data = "";
		try {
			data = WebUtil.readContentFromGet(url, map);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		balanceServerResponse.json2Obj(data);
		// 记录访问日志
		FileUtil.addLog(request, "minsheng/MsPay",// 
				channelNo,//  
				"userid", userid,//
				"orderid", orderId,
				"mainpaymode", mainpaymode,
				"subpaymode", subpaymode,
				"ordertime", balanceServerResponse.getDate(),
				"orderamount", balanceServerResponse.getAmount(),
				"orderstatus", balanceServerResponse.getStatus(),
				"orderstatusdesc", balanceServerResponse.getStatusdesc(),
				"orderOrigin", balanceServerResponse.getOrderOrigin(),
				"orderOriginDesc", balanceServerResponse.getOrderOriginDesc(),
				"cancancel", balanceServerResponse.getCancancel(),
				"canpay", balanceServerResponse.getCanpay(),
				"canconfirm", balanceServerResponse.getCanconfirm(),
				"onlineamount", balanceServerResponse.getOnlineamount()
				);		
		// 得到应用服务器地址
		//String path = request.getContextPath();
		//basePath = request.getScheme() + "://" + request.getLocalAddr() + ":" + request.getServerPort() + path + "/";
		String reqParams = mingshengRequestParams(balanceServerResponse);
		// 商户(ecshop)对订单明文进行加密
		String orderEncrypt = new MBECShopSecurity().encryptOrder(reqParams);
		System.out.println(orderEncrypt);
		request.getSession().setAttribute("orderinfo",orderEncrypt);
	
		try {
			if("25".equals(payval)){				
				request.getRequestDispatcher("/WEB-INF/pages/market/minsheng.jsp").forward(request, response);
			}else if("19".equals(payval)){
				request.getRequestDispatcher("/WEB-INF/pages/market/minshengyinlian.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*//调用民生提交订单接口
	    PrintWriter out = response.getWriter();
	    out.flush();
	    out.println("<script>");
	    out.println("window.SysClientJs.submitOrderShangPin("+orderEncrypt+")");
	    out.println("</script>");*/
	}
	/**
	 * 准备服务的参数
	 * 
	 * @param balanceServerResponse
	 * @return
	 * @throws IOException 
	 */
	private String mingshengRequestParams(BalanceServerResponse balanceServerResponse) throws IOException {
		StringBuffer buffer=new StringBuffer();
		buffer.append(SHOP_ID);
		buffer.append(balanceServerResponse.getOrderid());
		buffer.append("|");
		buffer.append(balanceServerResponse.getOnlineamount());
		buffer.append("|");
		buffer.append("01");
		buffer.append("|");
		Date date=DateTimeUtil.getDateFomate(balanceServerResponse.getDate());
		buffer.append(DateTimeUtil.strForDate(date));
		buffer.append("|");
		buffer.append(DateTimeUtil.timeForDate(date));
		buffer.append("|");
		buffer.append(SHOP_ID);
		buffer.append("|");
		buffer.append("spw");
		buffer.append("|");
		buffer.append("|");
		buffer.append("|");
		buffer.append("0");
		buffer.append("|");
		buffer.append(callbackUrl);
		buffer.append("|");
		System.out.println("mscs:"+buffer.toString());
		return buffer.toString();
	}
	
	
}
