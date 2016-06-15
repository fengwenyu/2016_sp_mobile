/**
 * Alipay.com Inc.
 * Copyright (c) 2005-2008 All Rights Reserved.
 */
package com.shangpin.wireless.api.pay.unionpay;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.pay.bestpay.server.impl.NoticefyResult;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 接收通知并处理
 * 
 */
public class NotifyReceiverUnipay extends HttpServlet {
	
	protected final Log log = LogFactory.getLog(NotifyReceiverUnipay.class.getSimpleName());

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		("unipay通知!");
		log.debug("NotifyReceiverUnipay : unipay advice start");
		
		try {
			//获得通知参数
			String notifyData = WebUtil.read(request.getInputStream());
			
			UnionUtil.handleNotify(notifyData, UnionUtil.SECRET);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		("unipay通知结束!");
		log.debug("NotifyReceiverUnipay : unipay advice end");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

}
