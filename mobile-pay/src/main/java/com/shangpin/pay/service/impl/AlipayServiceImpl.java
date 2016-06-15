package com.shangpin.pay.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.shangpin.pay.bo.PayOrderDetailBo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shangpin.pay.base.DirectTradeCreateRes;
import com.shangpin.pay.base.ErrType;
import com.shangpin.pay.base.ErrorCode;
import com.shangpin.pay.base.ResponseResult;
import com.shangpin.pay.bo.AlipayBo;
import com.shangpin.pay.bo.CommonBackBo;
import com.shangpin.pay.bo.OutsideAlipayBo;
import com.shangpin.pay.exception.ServiceException;
import com.shangpin.pay.service.AlipayService;
import com.shangpin.pay.utils.alipay.AlipayPropertyUtil;
import com.shangpin.pay.utils.alipay.AlipayUtil;
import com.shangpin.pay.utils.alipay.RSA;
import com.shangpin.pay.utils.alipay.RSASignature;
import com.shangpin.pay.utils.alipay.XMapUtil;
import com.shangpin.pay.utils.common.CommonPropertyUtil;
import com.shangpin.pay.utils.common.SortedValueUtil;
import com.shangpin.pay.utils.common.StringUtil;
import com.shangpin.utils.Encodes;
import com.shangpin.utils.MD5Util;
import com.shangpin.utils.RequestUtils;

@Service
public class AlipayServiceImpl extends BaseServiceImpl implements AlipayService {

    public static Logger logger = LoggerFactory.getLogger(AlipayServiceImpl.class);

    @Override
    public String wapPay(AlipayBo alipayBo) throws ServiceException {
        logger.debug("*******PAY MODEL:come in alipay's method wapPay*****");

        if (alipayBo == null || StringUtil.isEmpty(alipayBo.getOrderId(), alipayBo.getTotalFee(), alipayBo.getCallbackUrl(), alipayBo.getNotifyUrl(), alipayBo.getMerchantUrl())) {
            throw new ServiceException(ErrType.MISS_PARAMETER, "Missing parameters，please check!");
        }
        String callbackUrl = alipayBo.getCallbackUrl();
        logger.debug("*****PAY MODEL:orderId,money,callback,notify,merchant*****>>:" + alipayBo.getOrderId());
        logger.debug("*****PAY MODEL:money*****>>:" + alipayBo.getTotalFee());
        logger.debug("*****PAY MODEL:callback*****>>:" + alipayBo.getCallbackUrl());
        logger.debug("*****PAY MODEL:notify*****>>:" + alipayBo.getNotifyUrl());
        logger.debug("*****PAY MODEL:merchant*****>>:" + alipayBo.getMerchantUrl());

        Map<String, String> reqParams = prepareTradeRequestParamsMap(alipayBo, alipayBo.getNotifyUrl(), alipayBo.getMerchantUrl(), callbackUrl);
        String error = reqParams.get("error");
        if (AlipayUtil.ERROR.equals(error)) {
            throw new ServiceException(ErrType.ILLEGAL_DIRECTPARAMETER, "Direct service parameters error，please check!");
        }
        // 签名类型
        String signAlgo = AlipayPropertyUtil.SEC_ID;
        String reqUrl = AlipayPropertyUtil.REQ_URL;
        // 签名
        String sign = getSign(reqParams, signAlgo, AlipayPropertyUtil.RSA_PRIVATE);
        if (StringUtil.isEmpty(sign)) {
            throw new ServiceException(ErrType.ILLEGAL_SIGN, "Invalid  Signature, please create sign with alipay signature specification!");
        }
        reqParams.put("sign", sign);
        logger.info("Alipy wappay:sign={},reqUrl={}", sign, reqUrl);
        ResponseResult resResult = new ResponseResult();
        String businessResult = "";
        resResult = doSend(reqParams, reqUrl, signAlgo);
        if (resResult == null) {
            throw new ServiceException(ErrType.SERVICE_ERROR, "Request service error，please check service!");
        }
        if (resResult.isSuccess()) {
            businessResult = resResult.getBusinessResult();
        } else {
            throw new ServiceException(ErrType.RESRESULT_ERROR, "alipay result parse error,please check!");
        }
        DirectTradeCreateRes directTradeCreateRes = null;
        XMapUtil.register(DirectTradeCreateRes.class);
        try {
            directTradeCreateRes = (DirectTradeCreateRes) XMapUtil.load(new ByteArrayInputStream(businessResult.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            logger.error("Alipy wappay XMapUtil load :e={}", e);
            throw new ServiceException(ErrType.ILLEGAL_XML, "XML file is not valid ,please confirm!");
        } catch (Exception e) {
            logger.error("Alipy wappay XMapUtil load :e={}", e);
            throw new ServiceException(ErrType.ILLEGAL_XML, "XML file is not valid ,please confirm! ");
        }
        // 开放平台返回的内容中取出request_token
        String requestToken = directTradeCreateRes.getRequestToken();
        logger.info("Alipy wappay:requestToken={}", requestToken);
        Map<String, String> authParams = prepareAuthParamsMap(requestToken, callbackUrl);
        // 对调用授权请求数据签名
        String authSign = getSign(authParams, signAlgo, AlipayPropertyUtil.RSA_PRIVATE);
        if (StringUtil.isEmpty(authSign)) {
            throw new ServiceException(ErrType.ILLEGAL_AUTHSIGN, "Invalid auth Signature,please create sign with alipay signature specification!");
        }
        authParams.put("sign", authSign);
        String redirectURL = getRedirectUrl(authParams, reqUrl);
        if (StringUtil.isEmpty(redirectURL)) {
            throw new ServiceException(ErrType.ILLEGAL_REQURL, "Invalid rquest url,please confirm! ");
        }
        logger.info("Alipy wappay authAndExecute: URL={}", redirectURL);

        return redirectURL;

    }

    @Override
    public  Map<String, Object>  wapPayV2(AlipayBo alipayBo) throws ServiceException {
        logger.debug("*******PAY MODEL:come in alipay's method wapPay*****");

        if (alipayBo == null || StringUtil.isEmpty(alipayBo.getOrderId(), alipayBo.getTotalFee(), alipayBo.getCallbackUrl())) {
            throw new ServiceException(ErrType.MISS_PARAMETER, "Missing parameters，please check!");
        }
        logger.debug("*****PAY MODEL:orderId,money,callback,notify,merchant*****>>:" + alipayBo.getOrderId());
        logger.debug("*****PAY MODEL:money*****>>:" + alipayBo.getTotalFee());
        logger.debug("*****PAY MODEL:callback*****>>:" + alipayBo.getCallbackUrl());
        Map<String, Object> aliParams = new HashMap<String, Object>();
    	Map<String, String> params = new TreeMap<String, String>();
		params.put("gateWay", alipayBo.getGateWay());
		params.put("orderNo", alipayBo.getOrderId());
		params.put("returnUrl", alipayBo.getCallbackUrl());
		params.put("subject", alipayBo.getProductName());

        //不再管什么支付方式 只要返回分账金额 就让他分账
        if(!StringUtil.isBlank(alipayBo.getInternalAmount())){
            BigDecimal goodFees = new BigDecimal(0);
            BigDecimal transportFee = new BigDecimal(0);
            BigDecimal duty = new BigDecimal(0);
            List<PayOrderDetailBo> bos = alipayBo.getPayOrderDetailBo();
            for (PayOrderDetailBo spgood : bos) {
                goodFees=goodFees.add(new BigDecimal(spgood.getGoodFees()));
                transportFee=transportFee.add(new BigDecimal(spgood.getTransportFee()));
                duty=duty.add(new BigDecimal(spgood.getDuty()));
            }
            String ext = getExt(goodFees,transportFee,duty);
           // if (AlipayPropertyUtil.devModule) {
           //     ext = ext + ",splitAmount:" + "0.01";
           // }else{
                ext = ext + ",splitAmount:" + alipayBo.getInternalAmount();
            //}
            params.put("extend",ext);
        }

		String totalFee=alipayBo.getTotalFee();
	    // 判断是否是测试环境
        if (AlipayPropertyUtil.devModule) {
            totalFee = "0.01";
            if(alipayBo.getGateWay().equals("ALIFZWAP")){
            	totalFee = "0.06";
            }
        }
		params.put("totalFee",new BigDecimal(totalFee).setScale(2).toString() );
		logger.info("alipay sign str:{}",SortedValueUtil.sortedValue(params)+CommonPropertyUtil.encryptKey);
		logger.info("alipay sign sign:{}",SortedValueUtil.sortedValue(params)+CommonPropertyUtil.encryptKey);
		String sign=MD5Util.md5(SortedValueUtil.sortedValue(params)+CommonPropertyUtil.encryptKey,null);
		params.put("sign",sign);
		aliParams.put("gatewayUrl", CommonPropertyUtil.payRequestUrl);
		aliParams.put("requestParams",params);

        return aliParams;

    }
    private String getExt(BigDecimal goodFees, BigDecimal transportFee,
                          BigDecimal duty) {
        StringBuffer str=new StringBuffer("duty:");
        str.append(duty).append(",").append("goodFees:").append(goodFees).append(",").append("transportFee:").append(transportFee);
        return str.toString();
    }

    @Override
    public CommonBackBo callBack(HttpServletRequest request) throws ServiceException {
        logger.debug("********PAY MODEL:come in alipay's method callBack*****");
        CommonBackBo commonBackBo = new CommonBackBo();
        // 取得支付宝返回参数
        // 获得通知签名
        String sign = request.getParameter("sign");
        String result = request.getParameter("result");
        String requestToken = request.getParameter("request_token");
        String outTradeNo = request.getParameter("out_trade_no");
        String tradeNo = request.getParameter("trade_no");
        String totalFee = request.getParameter("total_fee");
		logger.debug("alipay callBack out_trade_no={}", outTradeNo);
        if (StringUtil.isEmpty(sign)) {
            throw new ServiceException(ErrType.MISS_CALLBACKRAMETER, "Invalid callback sign");
        }
        Map<String, String> resMap = new HashMap<String, String>();
        resMap.put("result", result);
        resMap.put("request_token", requestToken);
        resMap.put("out_trade_no", outTradeNo);
        resMap.put("trade_no", tradeNo);
        String verifyData = AlipayUtil.getSignData(resMap);
        boolean verified = false;

        try {
            verified = RSASignature.doCheck(verifyData, sign, AlipayPropertyUtil.RSA_ALIPAY_PUBLIC, "");
        } catch (Exception e) {
            throw new ServiceException(ErrType.CALLBACKSIFNCHECK_ERROR, "Callback signature verification failed");
        }
        if (!verified || !AlipayUtil.SUCCESS.equals(result)) {
            commonBackBo = getCommonBackBo(false, outTradeNo, totalFee);
            logger.info("alipay callBack vertify sign fail and the parameters for：result={},request_token={},out_trade_no={},trade_no={},sign={}", result, requestToken,
                    outTradeNo, tradeNo, sign);
            return commonBackBo;
        } else {
            commonBackBo = getCommonBackBo(true, outTradeNo, totalFee);
            logger.info("alipay callBack vertify sign success and the parameters for：result={},request_token={},out_trade_no={},trade_no={},sign={}", result, requestToken,
                    outTradeNo, tradeNo, sign);
            return commonBackBo;
        }

    }

    @Override
    public CommonBackBo notify(HttpServletRequest request) throws ServiceException {
        logger.debug("*********PAY MODEL:come in alipay's method notify********");
        CommonBackBo commonBackBo = new CommonBackBo();
        // 获得通知参数，支付宝系统通知待签名数据构造规则比较特殊。为固定顺序
        @SuppressWarnings("unchecked")
        Map<String, String[]> map = request.getParameterMap();
        // 获得通知签名
        String sign = (String) ((Object[]) map.get("sign"))[0];
        // String
        // sign="zW+tFYLa1fVd2ppu3Boueyg7ROS/8zXmbnvAhrLw6KBi+P0i6TJ+PfkeoAbFROLgscdAesAhZw1alZVP45Dipbo6BcpdTbKT/j8CswnSzQsqNWbx4JR7xIWdUh5el/E353ewVpZ8Nb3pXU8SHUWFZ742VlkfNqW9pmfdcGcl6vw=";
        logger.info("支付宝支付 notify: sign={}", sign);
        // 获得待验签名的数据
        String verifyData = null;
        try {
            verifyData = getVerifyData(map);
        } catch (Exception e1) {
            throw new ServiceException(ErrType.NOTIFYVERIFYDATA_EEROR, "Notify result parse failed");
        }
        logger.info("支付宝支付 notify:verifyData={}", verifyData);
        boolean verified = false;
        // 使用支付宝公钥验签名
        try {
            verified = RSASignature.doCheck(verifyData, sign, AlipayPropertyUtil.RSA_ALIPAY_PUBLIC, "");
        } catch (Exception e) {
            throw new ServiceException(ErrType.NOTIFYSIFNCHECK_ERROR, "Notify signature verification failed");
        }
        // 验证签名通过
        if (!verified) {
            throw new ServiceException(ErrType.NOTIFYSIFNCHECK_ERROR, "Notify signature verification failed");
        }
        // 当交易状态成功，处理业务逻辑成功。回写success
        final String tradestatus = StringUtil.getTagContent(verifyData, "<trade_status>", "</trade_status>");
        final String orderId = StringUtil.getTagContent(verifyData, "<out_trade_no>", "</out_trade_no>");
        final String totalFee = StringUtil.getTagContent(verifyData, "<total_fee>", "</total_fee>");
        final String tradeNo = StringUtil.getTagContent(verifyData, "<trade_no>", "</trade_no>");
        if (AlipayUtil.TRADE_FINISHED.equals(tradestatus) || AlipayUtil.TRADE_SUCCESS.equals(tradestatus)) {
            logger.info("Alipay system notify verify signature successed, and tradestatus ={} ", tradestatus);
            commonBackBo = getCommonBackBo(true, orderId, totalFee,tradeNo);
            return commonBackBo;
        } else {
            commonBackBo = getCommonBackBo(true, orderId, totalFee,tradeNo);
            logger.info("Alipay system notify verify signature failed, please check！");
            return commonBackBo;
        }
    }

    @Override
    public String outsideAppPay(OutsideAlipayBo alipayBo) throws ServiceException {
        try {
            if (alipayBo == null) {
                logger.error("Alipy outsideAppPay : Param Error");
                return null;
            }
            String orderId = alipayBo.getOrderId();
            String amount = alipayBo.getTotalFee();
            // 判断是否是测试环境
            if (AlipayPropertyUtil.devModule) {
                amount = "1.00";
            }
            String subject = alipayBo.getProductName();
            String body = alipayBo.getProductDetail();
            String notifyURL = alipayBo.getNotifyUrl();
            if (StringUtil.isEmpty(orderId, amount, subject, body, notifyURL)) {
                logger.error("Alipy outsideAppPay : Param Error");
                return null;
            }

            return getOutsideAppPayStr(alipayBo);
        } catch (Exception e) {
            logger.error("Alipy outsideAppPay : Build Error");
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.shangpin.pay.service.AlipayService#outsideAppNotify(javax.servlet
     * .http.HttpServletRequest)
     */
    @Override
    public CommonBackBo outsideAppNotify(HttpServletRequest request) throws ServiceException {
        Map<String, String> params = RequestUtils.getQueryParamMap(request);
        String notifyId = params.get("notify_id");
        String sign = params.get("sign");
        String orderId = params.get("out_trade_no");
        String status = params.get("trade_status");
        String totalFee = params.get("total_fee");
        String rmbFee = params.get("rmb_fee");
        // 如果rmb_fee不为空，需要把rmb的金额赋值给totalFee
        if (!StringUtil.isBlank(rmbFee)) {
            totalFee = rmbFee;
        }

        CommonBackBo backBo = new CommonBackBo(false, orderId, totalFee);

        // 验证notify_id是否合法
        if (!AlipayUtil.verifyNotifyId(notifyId)) {
            logger.error("AlipayService outsideAppNotify : Verify NotifyId Error, orderId={} , notifyId={}", orderId, notifyId);
            backBo.setVerifySign(false);
            return backBo;
        }
        // 验证签名的合法性
        if (!AlipayUtil.verifySignRSA(params, sign)) {
            logger.error("AlipayService outsideAppNotify : Verify Sign Error, orderId={} ,sign={}", orderId, sign);
            backBo.setVerifySign(false);
            return backBo;
        }

        // 验证订单的支付结果状态
        if (!AlipayUtil.verifyOrderStatus(status)) {
            logger.error("AlipayService outsideAppNotify : Order pay fail, orderId={} , status={}", orderId, status);
            backBo.setVerifySign(false);
            return backBo;
        }

        backBo.setVerifySign(true);
        return backBo;
    }

    @Override
    public String outsideWapPay(OutsideAlipayBo alipayBo) throws ServiceException {

        try {
            if (alipayBo == null) {
                logger.error("Alipy outsideWapPay : Param Error");
                return null;
            }
            String orderId = alipayBo.getOrderId();
            String amount = alipayBo.getTotalFee();
            // 判断是否是测试环境
            if (AlipayPropertyUtil.devModule) {
                amount = "1.00";
            }
            String subject = alipayBo.getProductName();
            String merchantUrl = alipayBo.getMerchantUrl();
            String callbackUrl = alipayBo.getCallbackUrl();
            String notifyURL = alipayBo.getNotifyUrl();
            if (StringUtil.isEmpty(orderId, amount, subject, merchantUrl, callbackUrl, notifyURL)) {
                logger.error("Alipy outsideWapPay : Param Error");
                return null;
            }

            return getOutsideWapPayStr(alipayBo);
        } catch (Exception e) {
            logger.error("Alipy outsideWapPay : Build Error");
            return null;
        }
    }

    @Override
    public CommonBackBo outsideWapCallBack(HttpServletRequest request) throws ServiceException {
        Map<String, String> params = RequestUtils.getQueryParamMap(request);
        String orderId = params.get("out_trade_no");
        String sign = params.get("sign");
        String status = params.get("trade_status");
        String totalFee = params.get("total_fee");
        String rmbFee = params.get("rmb_fee");
        // 如果rmb_fee不为空，需要把rmb的金额赋值给totalFee
        if (!StringUtil.isBlank(rmbFee)) {
            totalFee = rmbFee;
        }

        CommonBackBo backBo = new CommonBackBo(false, orderId, totalFee);

        // 验证request是否有值，没有值返回错误信息
        if (StringUtil.isEmpty(orderId, totalFee, status, sign)) {
            logger.error("AlipayService outsideWapCallBack : Params has empty value");
            backBo.setVerifySign(false);
            return backBo;
        }

        // 验证签名
        if (!verifySign(params)) {
            backBo.setVerifySign(false);
            return backBo;
        }

        // 验证订单的支付结果状态
        if (!AlipayUtil.verifyOrderStatus(status)) {
            logger.error("AlipayService outsideWapCallBack : Order pay fail, orderId={} , status={}", orderId, status);
            backBo.setVerifySign(false);
            return backBo;
        }

        backBo.setVerifySign(true);
        return backBo;
    }

    @Override
    public CommonBackBo outsideWapNotify(HttpServletRequest request) throws ServiceException {
        Map<String, String> params = RequestUtils.getQueryParamMap(request);
        String notifyId = params.get("notify_id");
        String sign = params.get("sign");
        String orderId = params.get("out_trade_no");
        String status = params.get("trade_status");
        String totalFee = params.get("total_fee");
        String rmbFee = params.get("rmb_fee");
        // 如果rmb_fee不为空，需要把rmb的金额赋值给totalFee
        if (!StringUtil.isBlank(rmbFee)) {
            totalFee = rmbFee;
        }

        CommonBackBo backBo = new CommonBackBo(false, orderId, totalFee);

        // 验证request是否有值，没有值返回错误信息
        if (StringUtil.isEmpty(orderId, totalFee, status, sign, notifyId)) {
            logger.error("AlipayService outsideWapNotify : Params has empty value");
            backBo.setVerifySign(false);
            return backBo;
        }

        // 验证notify_id是否合法
        if (!AlipayUtil.verifyNotifyId(notifyId)) {
            logger.error("AlipayService outsideAppNotify : Verify NotifyId Error, orderId={} , notifyId={}", orderId, notifyId);
            backBo.setVerifySign(false);
            return backBo;
        }

        // 验证签名
        if (!verifySign(params)) {
            backBo.setVerifySign(false);
            return backBo;
        }

        // 验证订单的支付结果状态
        if (!AlipayUtil.verifyOrderStatus(status)) {
            logger.error("AlipayService outsideAppNotify : Order pay fail, orderId={} , status={}", orderId, status);
            backBo.setVerifySign(false);
            return backBo;
        }

        backBo.setVerifySign(true);
        return backBo;
    }

    /**
     * 验证签名的合法性
     * 
     * @param params
     * @param orderId
     * @param sign
     * @param signType
     * @return
     */
    private boolean verifySign(Map<String, String> params) {
        String orderId = params.get("out_trade_no");
        String sign = params.get("sign");
        String signType = params.get("sign_type");

        // 验证签名的合法性
        if (AlipayPropertyUtil.OUTSIDE_SIGN_TYPE_MD5.equals(signType) && !AlipayUtil.verifySignMD5(params, sign)) {
            logger.error("AlipayService verifySign : Verify Sign Error, orderId={} ,sign={},signType={}", orderId, sign, signType);
            return false;
        }

        // 验证签名的合法性
        if (AlipayPropertyUtil.OUTSIDE_SIGN_TYPE_RSA.equals(signType) && !AlipayUtil.verifySignRSA(params, sign)) {
            logger.error("AlipayService verifySign : Verify Sign Error, orderId={} ,sign={},signType={}", orderId, sign, signType);
            return false;
        }

        return true;
    }

    /**
     * 准备alipay.wap.trade.create.direct服务的参数
     * 
     * @param request
     * @return
     * @throws IOException
     */
    private Map<String, String> prepareTradeRequestParamsMap(AlipayBo alipayBo, String notifyUrl, String merchantUrl, String callbackUrl) {
        Map<String, String> requestParams = new HashMap<String, String>();

        // 卖家帐号
        String sellerAccountName = AlipayPropertyUtil.SELLER_EMAIL;
        String outTradeNo = alipayBo.getOrderId();
        String totalFee = alipayBo.getTotalFee();
        String subject = alipayBo.getProductName();
        String outUser = alipayBo.getUserId();
        String payExpire = alipayBo.getOrderTimeout();
        // 判断是否是测试环境
        if (AlipayPropertyUtil.devModule) {
            totalFee = "0.01";
        }
        // req_data的内容
        StringBuilder reqData = new StringBuilder("<direct_trade_create_req><subject>");
        if (StringUtil.isEmpty(subject)) {
            subject = "订单号：" + alipayBo.getOrderId();
        }
        reqData.append(subject).append("</subject><out_trade_no>");
        reqData.append(outTradeNo);
        reqData.append("</out_trade_no><total_fee>");
        reqData.append(totalFee);
        reqData.append("</total_fee><seller_account_name>");
        reqData.append(sellerAccountName);
        reqData.append("</seller_account_name><notify_url>");
        reqData.append(notifyUrl);
        reqData.append("</notify_url><call_back_url>");
        reqData.append(callbackUrl);
        reqData.append("</call_back_url><merchant_url>");
        reqData.append(merchantUrl);
        reqData.append("</merchant_url>");
        if (StringUtil.isNotEmpty(outUser)) {
            reqData.append("<out_user>");
            reqData.append(outUser);
            reqData.append("</out_user>");
        }
        if (StringUtil.isNotEmpty(payExpire)) {
            reqData.append("<pay_expire>");
            reqData.append(payExpire);
            reqData.append("</pay_expire>");
        }
        reqData.append("</direct_trade_create_req>");

        requestParams.put("req_data", reqData.toString());
        logger.info("reqData={}", reqData.toString());
        requestParams.put("req_id", System.currentTimeMillis() + "");
        requestParams.putAll(prepareCommonParams(callbackUrl));
        return requestParams;
    }

    /**
     * 准备alipay.wap.auth.authAndExecute服务的参数
     * 
     * @param request
     * @param requestToken
     * @return
     */
    private Map<String, String> prepareAuthParamsMap(String requestToken, String callbackUrl) {
        Map<String, String> requestParams = new HashMap<String, String>();
        String reqData = "<auth_and_execute_req><request_token>" + requestToken + "</request_token></auth_and_execute_req>";
        requestParams.put("req_data", reqData);
        requestParams.putAll(prepareCommonParams(callbackUrl));
        // 支付成功跳转链接
        requestParams.put("call_back_url", callbackUrl);
        requestParams.put("service", "alipay.wap.auth.authAndExecute");
        return requestParams;
    }

    /**
     * 准备通用参数
     * 
     * @param request
     * @return
     */
    private Map<String, String> prepareCommonParams(String callbackUrl) {
        Map<String, String> commonParams = new HashMap<String, String>();
        commonParams.put("service", AlipayUtil.SERVICE);
        commonParams.put("sec_id", AlipayPropertyUtil.SEC_ID);
        commonParams.put("partner", AlipayPropertyUtil.PARTNER);
        commonParams.put("call_back_url", callbackUrl);
        commonParams.put("format", AlipayUtil.FORMAT);
        commonParams.put("v", AlipayUtil.VERSION);
        return commonParams;
    }

    /**
     * 对参数进行签名
     * 
     * @param reqParams
     * @return
     * @throws Exception
     */
    private String sign(Map<String, String> reqParams, String signAlgo, String key) throws Exception {
        String signData = AlipayUtil.getSignData(reqParams);
        logger.info("Unsigned Data={}", signData);
        String sign = "";

        sign = RSASignature.sign(signData, key, "");

        return sign;
    }

    /**
     * 调用alipay.wap.auth.authAndExecute服务的时候需要跳转到支付宝的页面，组装跳转url
     * 
     * @param reqParams
     * @return
     * @throws Exception
     */
    private String getRedirectUrl(Map<String, String> reqParams, String reqUrl) {
        String redirectUrl = reqUrl + "?";
        try {
            redirectUrl = redirectUrl + AlipayUtil.mapToUrl(reqParams);
        } catch (UnsupportedEncodingException e) {
            logger.error("alipy getRedirectUrl:e={}", e);
            return null;
        }
        return redirectUrl;
    }

    /**
     * 调用支付宝开放平台的服务
     * 
     * @param reqParams
     *            请求参数
     * @return
     * @throws Exception
     */
    private ResponseResult send(Map<String, String> reqParams, String reqUrl, String secId) throws Exception {
        String response = "";
        String invokeUrl = reqUrl + "?";
        URL serverUrl = new URL(invokeUrl);
        HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.connect();
        String params = AlipayUtil.mapToUrl(reqParams);
        logger.info("Alipay request Token:Token={}", URLDecoder.decode(params, AlipayUtil.CHARSET));
        conn.getOutputStream().write(params.getBytes());
        InputStream is = conn.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        response = URLDecoder.decode(buffer.toString(), AlipayUtil.CHARSET);
        logger.info("Alipay send response:response={}", response);
        conn.disconnect();
        return praseResult(response, secId);
    }

    /**
     * 解析支付宝返回的结果
     * 
     * @param response
     * @return
     * @throws Exception
     */
    private ResponseResult praseResult(String response, String secId) throws Exception {
        // 调用成功
        HashMap<String, String> resMap = new HashMap<String, String>();
        String v = AlipayUtil.getParameter(response, "v");
        String service = AlipayUtil.getParameter(response, "service");
        String partner = AlipayUtil.getParameter(response, "partner");
        String sign = AlipayUtil.getParameter(response, "sign");
        String reqId = AlipayUtil.getParameter(response, "req_id");
        resMap.put("v", v);
        resMap.put("service", service);
        resMap.put("partner", partner);
        resMap.put("sec_id", secId);
        resMap.put("req_id", reqId);
        String businessResult = "";
        ResponseResult result = new ResponseResult();
        logger.info("Alipay token Result:Result={}", response);
        if (response.contains("<err>")) {
            result.setSuccess(false);
            businessResult = AlipayUtil.getParameter(response, "res_error");
            // 转换错误信息
            XMapUtil.register(ErrorCode.class);
            ErrorCode errorCode = (ErrorCode) XMapUtil.load(new ByteArrayInputStream(businessResult.getBytes("UTF-8")));
            result.setErrorMessage(errorCode);
            resMap.put("res_error", AlipayUtil.getParameter(response, "res_error"));
        } else {
            businessResult = AlipayUtil.getParameter(response, "res_data");
            result.setSuccess(true);
            // 对返回的res_data数据先用商户私钥解密
            String resData = RSASignature.decrypt(businessResult, AlipayPropertyUtil.RSA_PRIVATE);
            result.setBusinessResult(resData);
            resMap.put("res_data", resData);
        }
        // 获取待签名数据
        String verifyData = AlipayUtil.getSignData(resMap);
        logger.info("Alipay verifyData:verifyData={}", verifyData);
        // 对待签名数据使用支付宝公钥验签名
        boolean verified = RSASignature.doCheck(verifyData, sign, AlipayPropertyUtil.RSA_ALIPAY_PUBLIC, "");
        if (!verified) {
            throw new Exception("验证签名失败");
        }
        return result;
    }

    /**
     * 获得验签名的数据
     * 
     * @param map
     * @return
     * @throws Exception
     */
    private String getVerifyData(Map<String, String[]> map) throws Exception {
        String service = (String) ((Object[]) map.get("service"))[0];
        String v = (String) ((Object[]) map.get("v"))[0];
        String sec_id = (String) ((Object[]) map.get("sec_id"))[0];
        String notify_data = (String) ((Object[]) map.get("notify_data"))[0];
        logger.info(" Alipay notify notify_data before decrypt：notify_data={}", notify_data);
        // 对返回的notify_data数据用商户私钥解密
        notify_data = RSASignature.decrypt(notify_data, AlipayPropertyUtil.RSA_PRIVATE);
        logger.info("Notify the parameters for：service={},v={},sec_id={},notify_data={}", service, v, sec_id, notify_data);
        StringBuilder verifyData = new StringBuilder("service=");
        verifyData.append(service);
        verifyData.append("&v=");
        verifyData.append(v);
        verifyData.append("&sec_id=");
        verifyData.append(sec_id);
        verifyData.append("&notify_data=");
        verifyData.append(notify_data);
        return verifyData.toString();
    }

    private String getSign(Map<String, String> reqParams, String signAlgo, String key) {
        String sign;
        try {
            sign = sign(reqParams, signAlgo, key);
        } catch (Exception e) {
            logger.error("alipy sign e={}", e);
            return null;
        }
        return sign;
    }

    private ResponseResult doSend(Map<String, String> reqParams, String reqUrl, String signAlgo) {
        ResponseResult resResult = null;
        try {
            resResult = send(reqParams, reqUrl, signAlgo);
        } catch (Exception e) {
            logger.error("alipy send e={}", e);
            return null;
        }
        return resResult;

    }

    /**
     * 构造海外支付宝跟业务无关的基础数据
     * 
     * @return
     */
    private String getOutsideAppPayStr(OutsideAlipayBo alipayBo) {

        if (!checkAppAlipayBo(alipayBo)) {
            return null;
        }

        Map<String, String> params = new HashMap<String, String>();
        params.put("_input_charset", "\"" + AlipayPropertyUtil.CHARSET + "\"");
        params.put("service", "\"" + AlipayPropertyUtil.OUTSIDE_APP_PAY_SERVICE + "\"");
        params.put("partner", "\"" + AlipayPropertyUtil.OUTSIDE_PARTNER + "\"");
        params.put("seller_id", "\"" + AlipayPropertyUtil.OUTSIDE_SELLER_ID + "\"");
        params.put("payment_type", "\"" + AlipayPropertyUtil.OUTSIDE_PAYMENT_TYPE + "\"");
        params.put("currency", "\"" + AlipayPropertyUtil.OUTSIDE_CURRENCY + "\"");
        params.put("forex_biz", "\"" + AlipayPropertyUtil.OUTSIDE_FOREX_BIZ + "\"");
        params.put("it_b_pay", "\"" + AlipayPropertyUtil.OUTSIDE_IT_B_PAY + "\"");
        // 业务数据
        params.put("out_trade_no", "\"" + alipayBo.getOrderId() + "\"");
        params.put("rmb_fee", "\"" + alipayBo.getTotalFee() + "\"");
        params.put("subject", "\"" + Encodes.urlEncode(alipayBo.getProductName()) + "\"");
        params.put("body", "\"" + Encodes.urlEncode(alipayBo.getProductDetail()) + "\"");
        params.put("notify_url", "\"" + Encodes.urlEncode(alipayBo.getNotifyUrl()) + "\"");
        params.put("forex_param", "\"" + buildExParam("app") + "\"");
        // 去除Map中value为空的key
        params = AlipayUtil.paraFilter(params);

        String sign = RSA.sign(AlipayUtil.createLinkString(params), AlipayPropertyUtil.OUTSIDE_RSA_PRIVATE_KEY, AlipayPropertyUtil.CHARSET);
        params.put("sign", "\"" + Encodes.urlEncode(sign) + "\"");
        params.put("sign_type", "\"" + AlipayPropertyUtil.OUTSIDE_SIGN_TYPE_RSA + "\"");

        String linkString = AlipayUtil.createLinkString(params);
        
        return linkString;
    }

    /**
     * 构造海外支付宝跟业务无关的基础数据
     * 
     * @return
     */
    private String getOutsideWapPayStr(OutsideAlipayBo alipayBo) {

        if (!checkWapAlipayBo(alipayBo)) {
            return null;
        }
        String amount = alipayBo.getTotalFee();
        // 判断是否是测试环境
        if (AlipayPropertyUtil.devModule) {
            amount = "0.05";
        }
        StringBuilder url = new StringBuilder(AlipayPropertyUtil.OUTSIDE_URL);
        Map<String, String> params = new HashMap<String, String>();
        params.put("_input_charset", AlipayPropertyUtil.CHARSET);
        params.put("service", AlipayPropertyUtil.OUTSIDE_WAP_TRADE_SERVICE);
        params.put("partner", AlipayPropertyUtil.OUTSIDE_PARTNER);
        params.put("currency", AlipayPropertyUtil.OUTSIDE_CURRENCY);
        // 业务数据
        params.put("out_trade_no", alipayBo.getOrderId());
        params.put("rmb_fee", amount);
        params.put("subject", alipayBo.getProductName());
        params.put("merchant_url", alipayBo.getMerchantUrl());
        params.put("return_url", alipayBo.getCallbackUrl());
        params.put("notify_url", alipayBo.getNotifyUrl());
        params.put("ext_params", buildExParam("wap"));
        // 去除Map中value为空的key
        params = AlipayUtil.paraFilter(params);

        String sign = RSA.sign(AlipayUtil.createLinkString(params), AlipayPropertyUtil.OUTSIDE_RSA_PRIVATE_KEY, AlipayPropertyUtil.CHARSET);
        params.put("sign", Encodes.urlEncode(sign));
        params.put("sign_type", AlipayPropertyUtil.OUTSIDE_SIGN_TYPE_RSA);

        // 对可能是中文或者特殊字符的参数做urlencode
        params.put("subject", Encodes.urlEncode(alipayBo.getProductName()));
        params.put("merchant_url", Encodes.urlEncode(alipayBo.getMerchantUrl()));
        params.put("return_url", Encodes.urlEncode(alipayBo.getCallbackUrl()));
        params.put("notify_url", Encodes.urlEncode(alipayBo.getNotifyUrl()));
        params.put("ext_params", Encodes.urlEncode(buildExParam("wap")));

        url.append("?").append(AlipayUtil.createLinkString(params));
        
        return url.toString();
    }

    /**
     * 检查app的bo对象是否符合要求
     * 
     * @param alipayBo
     * @return
     */
    private boolean checkAppAlipayBo(OutsideAlipayBo alipayBo) {
        if (alipayBo == null) {
            return false;
        }

        String orderId = alipayBo.getOrderId();
        String productName = alipayBo.getProductName();
        String productDetail = alipayBo.getProductDetail();
        String totalFee = alipayBo.getTotalFee();
        String notifyUrl = alipayBo.getNotifyUrl();

        if (StringUtil.isEmpty(orderId, productName, productDetail, totalFee, notifyUrl)) {
            return false;
        }
        return true;
    }

    /**
     * 检查wap的bo对象是否符合要求
     * 
     * @param alipayBo
     * @return
     */
    private boolean checkWapAlipayBo(OutsideAlipayBo alipayBo) {
        if (alipayBo == null) {
            return false;
        }

        String orderId = alipayBo.getOrderId();
        String productName = alipayBo.getProductName();
        String totalFee = alipayBo.getTotalFee();
        String merchantUrl = alipayBo.getMerchantUrl();
        String callbackUrl = alipayBo.getCallbackUrl();
        String notifyUrl = alipayBo.getNotifyUrl();

        if (StringUtil.isEmpty(orderId, productName, totalFee, merchantUrl, callbackUrl, notifyUrl)) {
            return false;
        }
        return true;
    }
    
	/**
	 * 组装报关参数
	 * @param flag
	 * 			app;wap
	 * @return
	 */
	private String buildExParam(String flag) {
		if ("app".equals(flag)) {
			return "customs_place=" + AlipayPropertyUtil.OUTSIDE_CUSTOMS_NAME + "^" + "merchant_customs_code=" +AlipayPropertyUtil.OUTSIDE_FILING_NUMBER;
		} else if ("wap".equals(flag)) {
			return "customs_place|" + AlipayPropertyUtil.OUTSIDE_CUSTOMS_NAME + "#" + "merchant_customs_code|" +AlipayPropertyUtil.OUTSIDE_FILING_NUMBER;
		} else {
			return "";
		}

	}

    public static void main(String[] args) {
        try {
            AlipayServiceImpl al = new AlipayServiceImpl();
            OutsideAlipayBo alipayBo = new OutsideAlipayBo("fz_ydz_101", "2015032352525254", "750.00", "fz_ydz_101", "http://www.baidu.com");
            System.out.println(al.outsideAppPay(alipayBo));
            OutsideAlipayBo alipayBo1 = new OutsideAlipayBo("中国  你好", "2015031952525254", "230", "http://www.baidu.com", "http://www.baidu.com", "http://www.baidu.com");
            System.out.println(al.outsideWapPay(alipayBo1));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
