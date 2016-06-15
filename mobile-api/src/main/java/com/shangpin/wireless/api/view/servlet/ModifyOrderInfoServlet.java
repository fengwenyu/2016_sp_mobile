package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 修改订单的信息
 *     收货地址 发票地址  收货方式
 * @author xupengcheng
 *
 */
public class ModifyOrderInfoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String orderNo = req.getParameter("orderId");
		String addrId = req.getParameter("addrId");//收货地址ID
		String express = req.getParameter("express");// 收货时间
		String invoiceAddrId = req.getParameter("invoiceAddrId");//发票地址ID
		String invoiceFlag = req.getParameter("invoiceFlag");//是否开发票
		String invoiceType = req.getParameter("invoiceType");//发票类型
		String invoiceTitle = req.getParameter("invoiceTitle"); //发票抬头
		String invoiceContent = req.getParameter("invoiceContent"); //发票内容
		String isModifyInvoice = req.getParameter("isModifyInvoice");//是否修改发票信息
		if (StringUtil.isNotEmpty(userId, orderNo, addrId, express, invoiceAddrId, invoiceFlag, invoiceType, isModifyInvoice)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("userId", userId);
			map.put("orderNo", orderNo);
			map.put("addrid", addrId);
			map.put("timeRequest", express);
			map.put("invoiceaddrid", invoiceAddrId);
			map.put("invoiceflag", invoiceFlag);
			map.put("invoicetype", invoiceType);
			map.put("invoicetitle", invoiceTitle);
			map.put("invoicecontent", invoiceContent);
			if(isModifyInvoice.equals("0") || isModifyInvoice.equals("-1")){
				map.put("isModifyInvoice", "0");
			}else{
				map.put("isModifyInvoice", "1");
			}
			String url = Constants.BASE_TRADE_URL + "order/ModifyOrderAddress";
			try {
				String data = WebUtil.readContentFromGet(url, map );
				resp.getWriter().print(data);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					WebUtil.sendApiException(resp);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}else {
			try {
				WebUtil.sendErrorInvalidParams(resp);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
