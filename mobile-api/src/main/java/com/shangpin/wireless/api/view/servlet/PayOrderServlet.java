package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.action.PayOrderAction;

/**
 * 支付订单接口
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-09-12
 */
public class PayOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(PayOrderServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String productNum = request.getHeader("p");
		final String ver = request.getHeader("ver");
		final String userid = request.getParameter("userid");
		final String mainpaymode = request.getParameter("mainpaymode");
		String subpaymode = request.getParameter("subpaymode");
		final String orderid = request.getParameter("orderid");
		final String source = request.getParameter("source"); // 支付宝钱包标识
		final String token = request.getParameter("token"); // 支付宝钱包token
		final String productno = request.getParameter("productno"); // 翼支付用户手机号
		final String sig = request.getParameter("sig"); // 翼支付签名
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(productNum, ver, userid, mainpaymode, subpaymode, orderid)) {
			 PayOrderAction payOrderAction = new PayOrderAction();
			 if(mainpaymode.equals("30")){
					subpaymode="120";
			 }
			 String data =  payOrderAction.payInfo(request, null,null, productNum, ver, userid, mainpaymode, subpaymode, orderid, source, token, productno, sig, this.getServletContext());
			 response.getWriter().write(data);
			// 记录是否下单成功
             FileUtil.addLog(request, "payorder", channelNo, 
                     "orderid", orderid,// 
                     "mainpaymode", mainpaymode,// 
                     "subpaymode", subpaymode,//
                     "userid", userid,
                     "productNum", productNum,
                     "code", "success");
		} else {
			 FileUtil.addLog(request, "payorder", channelNo, 
                     "orderid", orderid,// 
                     "mainpaymode", mainpaymode,// 
                     "subpaymode", subpaymode,//
                     "userid", userid,
                     "productNum", productNum,
                     "code", "fail");
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
