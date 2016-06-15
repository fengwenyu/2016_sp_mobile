/**
 * Alipay.com Inc.
 * Copyright (c) 2005-2008 All Rights Reserved.
 */
package com.shangpin.wireless.api.pay.alipay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.pay.bo.CommonBackBo;
import com.shangpin.pay.service.AlipayService;
import com.shangpin.utils.HttpClientUtil;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

/**
 * 接收通知并处理
 * 
 */
public class OutsideNotifyServlet extends BaseServlet {

    private static final long serialVersionUID = 4477378689585602797L;

    private final Log logger = LogFactory.getLog(OutsideNotifyServlet.class);

    private String ORDER_UPDATE_URL = Constants.BASE_TRADE_URL + "order/UpdateOrderStatus";

    private String orderId = null;

    private String totalFee = null;

    private Boolean status = null;

    AlipayService alipayService = null;

    @Override
    public void init() throws ServletException {
        alipayService = (AlipayService) getBean(AlipayService.class);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String channelNo = ChannelNoUtil.getChannelNo(request);// 获取渠道号
        PrintWriter out = response.getWriter();

        try {

            CommonBackBo backBo = alipayService.outsideAppNotify(request);
            this.orderId = backBo.getOrderId();
            this.totalFee = backBo.getTotalFee();
            this.status = backBo.getVerifySign();

            if (!status) {
                FileUtil.addLog(request, "alipay_notify", "channelNo", channelNo, "status", status.toString(), "orderId", orderId, "totalFee", totalFee, "code", "fail");
                return;
            }

            if (!StringUtil.isNotEmpty(orderId, totalFee)) {
                FileUtil.addLog(request, "alipay_notify", "channelNo", channelNo, "status", status.toString(), "orderId", orderId, "totalFee", totalFee, "code", "fail");
                return;
            }

            HashMap<String, String> params = new HashMap<String, String>();
            params.put("mainorderNo", orderId);
            params.put("payTypeId", Constants.PAYTYPE_ID);
            params.put("childPayTypeId", Constants.PAYTYPE_CHILD_ID);
            params.put("orderAmount", Constants.PAY_AMOUNT);

            String data = HttpClientUtil.doGet(ORDER_UPDATE_URL, params);
            JSONObject content = JSONObject.fromObject(data);
            String code = content.getString("code");
            
            if (!Constants.SUCESS_CODE.equalsIgnoreCase(code)) {
                FileUtil.addLog(request, "alipay_notify", "channelNo", channelNo, "orderId", orderId, "payId", Constants.PAYTYPE_ID, "payChildId", Constants.PAYTYPE_CHILD_ID,
                        "code", "fail", "totalFee", totalFee);
                return;
            }
            
            FileUtil.addLog(request, "alipay_notify", "channelNo", channelNo, "orderId", orderId, "payId", Constants.PAYTYPE_ID, "payChildId", Constants.PAYTYPE_CHILD_ID, "code",
                    "success", "totalFee", totalFee);
            out.print("success");
        } catch (Exception e) {
            FileUtil.addLog(request, "alipay_notify", "channelNo", channelNo, "orderId", orderId, "payId", Constants.PAYTYPE_ID, "payChildId", Constants.PAYTYPE_CHILD_ID, "code",
                    "fail", "totalFee", totalFee, "msg", "订单数据同步出错");
            logger.error("ERROR:", e);
        } finally {
            out.close();
        }
    }

}
