package com.shangpin.pay.base;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.pay.bo.UnionPayBo;
import com.shangpin.pay.utils.common.StringUtil;
import com.shangpin.pay.utils.union.UnionpayPropertyUtil;
import com.shangpin.pay.utils.union.UnionpayUtil;
import com.shangpin.utils.HttpClientUtil;

public class UnionPayModelV2 {

    public static Logger logger = LoggerFactory.getLogger(UnionPayModelV2.class);
    private String data;
    private String respCode;
    private String respMsg;

    public String getData() {
        return data;
    }

    public String getResponseCode() {
        return respCode;
    }

    public String getResponseMessage() {
        return respMsg;
    }

    /**
     * 版本号 version M 1.0.0 字符编码 charset M 全大写 签名方法 signMethod M 可参考 6.4签名方法 签名信息
     * signature M 交易类型 transType M 例如 01 消费； 02 预授权 商户代码 merId M 定长15位数字 通知URL
     * backEndUrl M 交易成功后，异步通知商户后台的URL 前台通知URL frontEndUrl O 交易成功后，客户端前台通知商户的URL
     * 收单机构代码 acqCode C 当商户直接与银联移动支付系统相连时，该域可不出现。当商户通过其他系统间接与银联移动支付系统相连时，该域必须出现
     * 交易开始日期时间 orderTime M yyyyMMddHHmmss, 交易发生时的时间日期 订单超时时间 orderTimeout O
     * yyyyMMddHHmmss,订单超时时间 商户订单号 orderNumber M 最大40个字母、数字 交易金额 orderAmount M
     * 交易币种 orderCurrency O 订单描述 orderDescription O 商户保留域 merReserved O 请求方保留域
     * reqReserved O 系统保留域 sysReserved O
     */
    public String unionPayModel(UnionPayBo unionPayBo) {

        String transType = unionPayBo.getTransType();
        String orderTimeout = unionPayBo.getOrderTimeout();
        //String backEndUrl = unionPayBo.getCallbackUrl();
        String notifyUrl = unionPayBo.getNotifyUrl();
        String orderCurrency =unionPayBo.getOrderCurrency();
        String orderDescription= unionPayBo.getOrderDescription();
        Map<String, String> map = new TreeMap<String, String>();
        map.put("version", "1.0.0");
        map.put("charset", UnionpayUtil.CHARSET);//
        map.put("transType", transType != null ? transType : "01"); // 01 消费; 02
        map.put("merId", UnionpayPropertyUtil.MER_ID);
        map.put("backEndUrl", notifyUrl);//TODO 这俩为啥一样？
        map.put("frontEndUrl", notifyUrl);// 接受发送的支付结果通知。用于提高通知成功率，其实可与商户后台url一致
        map.put("orderTime", unionPayBo.getOrderTimeStart());
        map.put("orderNumber", unionPayBo.getOrderNumber());
        map.put("orderCurrency", orderCurrency != null ? orderCurrency : "156");
        if (StringUtil.isNotEmpty(orderTimeout)) {
            map.put("orderTimeout", orderTimeout);
        }
        
        if (StringUtil.isNotEmpty(orderDescription)) {
            map.put("orderDescription", orderDescription);
        }

        if (UnionpayPropertyUtil.devModule) {
            map.put("orderAmount", "1");
        } else {
            map.put("orderAmount", unionPayBo.getTotalFee());
        }
        
        String signature = UnionpayUtil.buildSignature(map, UnionpayPropertyUtil.SECRET);
        logger.debug("Unionpay unionPayModel buildSignature: signature={}", signature);

        map.put(UnionpayUtil.KEY_SIGNATURE_METHOD, UnionpayUtil.SIGNATURE_METHOD);
        map.put(UnionpayUtil.KEY_SIGNATURE, signature);
        String content = HttpClientUtil.doPost(UnionpayPropertyUtil.URL_BUY, map, UnionpayUtil.CHARSET);
        HashMap<String, String> resp = new HashMap<String, String>();
        boolean valid = UnionpayUtil.verifyResponse(content, UnionpayPropertyUtil.SECRET, resp);

        if (!valid) {
            logger.debug("UnionPayModelV2: Signature verification failed");
            return null; 
        } 
        respCode = resp.get(UnionpayUtil.KEY_CODE);
        if (!"00".equals(respCode)) {
            respMsg = resp.get(UnionpayUtil.KEY_MESSAGE);
            logger.debug("UnionPayModelV2:Signature verification failed:respMsg={}", respMsg);
            return null;
        } 
        data = resp.get(UnionpayUtil.KEY_TN);
        logger.debug("UnionPayModelV2: Signature verification successed:tn={}", data);
        return data;
    }

}
