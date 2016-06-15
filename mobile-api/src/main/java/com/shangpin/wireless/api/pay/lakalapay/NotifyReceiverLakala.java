/**
 * Alipay.com Inc.
 * Copyright (c) 2005-2008 All Rights Reserved.
 */
package com.shangpin.wireless.api.pay.lakalapay;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 接收通知并处理
 * 
 */
public class NotifyReceiverLakala extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(NotifyReceiverLakala.class.getSimpleName());

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
        //版本号
        String VER = request.getParameter("VER");
        if(!LakalaPayModel.VERSION.equals(VER)){
            throw new IOException("版本不一致");
        }
        final String MERID = request.getParameter("MERID");	//商户号
        final String ORDERID = request.getParameter("ORDERID");	//订单号
        final String AMOUNT = request.getParameter("AMOUNT");	//付款金额
        String RANDNUM = request.getParameter("RANDNUM");//随机数
        RANDNUM = (RANDNUM==null?"":RANDNUM);
        String ACCOUNTDATE = request.getParameter("ACCOUNTDATE");	//账务日期
        ACCOUNTDATE=(ACCOUNTDATE==null?"":ACCOUNTDATE);
        String PLATSEQ = request.getParameter("PLATSEQ");	//支付平台流水号
        PLATSEQ = (PLATSEQ==null?"":PLATSEQ);
        String PAYMENTSERI = request.getParameter("PAYMENTSERI");	//支付流水
        PAYMENTSERI = (PAYMENTSERI==null?"":PAYMENTSERI);
        String TERMINALNO = request.getParameter("TERMINALNO");	//终端号
        TERMINALNO = (TERMINALNO==null?"":TERMINALNO);
        String ACCOUNTNAME = request.getParameter("ACCOUNTNAME");	//用户account
        ACCOUNTNAME = (ACCOUNTNAME==null?"":ACCOUNTNAME);
        String MACTYPE = request.getParameter("MACTYPE");	//校验类型
        if(!LakalaPayModel.MAC_TYPE.equals(MACTYPE)){
            throw new IOException("校验类型不一致");
        }
        final String MAC = request.getParameter("MAC");	//校验码
        //校签
        if("null".equals(ACCOUNTNAME)){
        	ACCOUNTNAME="";
        }
        String dataStr = new StringBuffer().append(VER)
     				      .append(MERID)
     				      .append(LakalaPayModel.MERCHANT_PASSWORD)
     				      .append(ORDERID)
     				      .append(AMOUNT)
     				      .append(RANDNUM)
     				      .append(ACCOUNTDATE)
     				      .append(PLATSEQ)
     				      .append(LakalaPayModel.MAC_TYPE)
     				      .append(PAYMENTSERI)
     				      .append(TERMINALNO)
     				      .append(ACCOUNTNAME)
     				.toString();
        final String macMd5= DigestUtils.md5Hex(dataStr.toString().getBytes("UTF-8"));
        if(!macMd5.equals(MAC)) {
            throw new IOException("商户校签不通过");
        }else{
        	//处理商户自己的订单；
        	final String payTypeId = "21";
        	final String payTypeChildId = "43";
        	
//        	HashMap<String, String> reqmap = new HashMap<String, String>();
//			reqmap.put("orderno", ORDERID);
//			reqmap.put("paytypeId", payTypeId);
//			reqmap.put("paytypechildId", payTypeChildId);
//			
//			String url = Constants.BASE_URL + "payconfirmpayment/";
			
			HashMap<String, String> reqmap = new HashMap<String, String>();
			reqmap.put("mainorderNo", ORDERID);
			reqmap.put("payTypeId", payTypeId);
			reqmap.put("childPayTypeId", payTypeChildId);
			reqmap.put("orderAmount", AMOUNT);
			String url = Constants.BASE_TRADE_URL + "order/UpdateOrderStatus";
			try {
				log.debug("NotifyReceiverLakala::updateOrderStatus " + Thread.currentThread().getName());
				String data = WebUtil.readContentFromGet(url, reqmap);
				JSONObject content = JSONObject.fromObject(data);
				final String code = content.getString("code");
				String returnStr;
				if ("0".equals(code)) {
					// 记录访问日志
					FileUtil.addLog(request, "lakala_notify", channelNo,
							"orderid", ORDERID, 
							"payid", payTypeId, 
							"paychildid", payTypeChildId, 
							"code", "success");
					returnStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<merpay><retcode>0</retcode></merpay>";
				    log.debug("NotifyReceiverLakala::updateOrderStatus " + Thread.currentThread().getName());
				} else {
//					记录访问日志
					FileUtil.addLog(request, "paysuccess", channelNo, 
							"orderid", ORDERID, 
							"payid", payTypeId, 
							"paychildid", payTypeChildId, 
							"code", "fail", 
							"msg", content.getString("msg"));
					
					log.warn("LakalaPay failed orderid = " + ORDERID + " (" + code + ")(" + content.getString("msg") + ")" + Thread.currentThread().getName());
					returnStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<merpay><retcode>-1</retcode></merpay>";
				}
				response.getWriter().write(returnStr);
			} catch (Exception e) {
				e.printStackTrace();
//				记录访问日志
				FileUtil.addLog(request, "paysuccess", channelNo, 
						"orderid", ORDERID, 
						"payid", payTypeId, 
						"paychildid", payTypeChildId, 
						"code", "fail");
				log.error("NotifyReceiverLakala：" + e);
				throw new IOException("订单数据同步出错");
			}
        }
    	
    }

//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        doPost(request, response);
//        throw new IOException("不支付的方法");
//    }

}
