package com.shangpin.pay.service.impl;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shangpin.pay.base.ErrType;
import com.shangpin.pay.base.UnionPayModelV2;
import com.shangpin.pay.bo.CommonBackBo;
import com.shangpin.pay.bo.UnionPayBo;
import com.shangpin.pay.exception.ServiceException;
import com.shangpin.pay.service.UnionpayService;
import com.shangpin.pay.utils.alipay.AlipayPropertyUtil;
import com.shangpin.pay.utils.common.Base64;
import com.shangpin.pay.utils.common.CommonPropertyUtil;
import com.shangpin.pay.utils.common.SortedValueUtil;
import com.shangpin.pay.utils.common.StringUtil;
import com.shangpin.pay.utils.union.UnionpayPropertyUtil;
import com.shangpin.pay.utils.union.UnionpayUtil;
import com.shangpin.utils.MD5Util;

@Service
public class UnionpayServiceImpl extends BaseServiceImpl implements UnionpayService {
	
    public static Logger logger = LoggerFactory.getLogger(UnionpayServiceImpl.class);

	@Override
	public String wapPay(UnionPayBo unionPayBo)  throws ServiceException{
		logger.debug("*******PAY MODEL:come in unionpay's method wapPay*****");
		String baseResultUrl = unionPayBo.getCallbackUrl();
		if(unionPayBo==null||StringUtil.isEmpty(unionPayBo.getOrderTimeStart(),unionPayBo.getOrderNumber(),unionPayBo.getTotalFee(),baseResultUrl,unionPayBo.getNotifyUrl())){
			throw new ServiceException(ErrType.MISS_PARAMETER,"Missing parameters，please check!");
		}
		String tn = new UnionPayModelV2().unionPayModel(unionPayBo);
	
		// 拼接银联回调resultUrl
		String resultUrl = UnionpayUtil.montageResultUrl(unionPayBo.getOrderNumber(),unionPayBo.getTotalFee(),baseResultUrl);
		if(StringUtil.isEmpty(tn)){
			throw new ServiceException(ErrType.TN_ERROR,"Invalid tn，please check!");
		}
		logger.info("Unionpay:tn={}",tn);
		// 生成paydata参数用于银联支付(有交易流水号tn和 resultUrl以及usetestmode三个参数组成)
		String paydata = returnPayData(tn, resultUrl);
		if(StringUtil.isEmpty(paydata)){
			throw new ServiceException(ErrType.ILLEGAL_REQDATA,"Invalid paydata，please check!");
		}
		logger.info("Unionpay: paydata={}",paydata);
		StringBuilder reqData = new StringBuilder(UnionpayPropertyUtil.URL_REQUEST);
		reqData.append("/?style=token&paydata=");
		reqData.append(paydata);
		String requestUrl=reqData.toString();
		logger.info("Unionpay: requestUrl={}",requestUrl);
		return requestUrl;
	}
	@Override
	public CommonBackBo callBack(HttpServletRequest request) throws ServiceException{
		logger.debug("*******PAY MODEL:come in unionpay's method callBack*****");
		CommonBackBo commonBackBo=new CommonBackBo();
		// 获得通知参数
		String argName = request.getParameter("argname");
		String amount = request.getParameter("amount");// 支付金额
		String orderId = request.getParameter("orderId");// 订单号
		if(StringUtil.isEmpty(argName)){
			throw new ServiceException(ErrType.MISS_CALLBACKRAMETER,"Invalid callback sign");
		}
		if (orderId.indexOf(UnionpayUtil.GIFT_FLAG) > -1) {
			orderId = orderId.split("gift")[0];
		}
		// 银联支付成功
		if (UnionpayUtil.CALLBACK_SUCCESS.endsWith(argName)) {
			commonBackBo=getCommonBackBo(true,orderId,amount);
			logger.info("Unionpay callBack  pay successed：argName={}",argName);
			return commonBackBo;
		}else {// 银联支付失败
			commonBackBo=getCommonBackBo(false,orderId,amount);
			logger.info("Unionpay callBack  pay failed：argName={}",argName);
			return commonBackBo;
		}
	}
	
	@Override
	public CommonBackBo notify(HttpServletRequest request)throws ServiceException {
		logger.debug("*******PAY MODEL:come in unionpay's method notify*****");
		logger.info("Unionpay notice!");
		CommonBackBo commonBackBo = new CommonBackBo();
		@SuppressWarnings("unchecked")
		Map<String, String[]> notifyData = request.getParameterMap();
		HashMap<String, String> resp = new HashMap<String, String>();
		boolean verified = vertifyNotifySign(notifyData,UnionpayPropertyUtil.SECRET, resp);
		if (!verified) {
			throw new ServiceException(ErrType.NOTIFYVERIFYDATA_EEROR,"Notify signature verification failed");
		}

		String orderId = resp.get(UnionpayUtil.KEY_ORDER_ID);
		String totalFee = resp.get("settleAmount");
		final String respCode = resp.get(UnionpayUtil.KEY_CODE);
		final String transStatus = resp.get(UnionpayUtil.KEY_TRANS_STATUS);
		if (!UnionpayUtil.NOTIFY_SUCCESS.equals(respCode)||!UnionpayUtil.NOTIFY_SUCCESS.equals(transStatus)) {
			logger.info("Unionpay vertifyNotifySign failed: respCode={}",respCode);
			commonBackBo=getCommonBackBo(false,orderId,totalFee);
			return commonBackBo;
		}
		logger.info("Unionpay vertifyNotifySign successed: respCode={},transStatus={}",respCode, transStatus);
		commonBackBo=getCommonBackBo(true,orderId,totalFee);
		return commonBackBo;

	}

	/**
	 * 组装银联支付参数paydata
	 * 
	 * @param tn
	 *            交易流水号
	 * @param resultUrl
	 *            回调url
	 * @return paydata
	 * 
	 */
	private String returnPayData(String tn, String resultUrl) {
		// usetestmode 是否使用银联测试环境标识 ture使用银联测试环境测试 false使用银联生产环境
		String paydata=null;
		try {
			StringBuilder data = new StringBuilder("tn=");
			data.append(tn);
			data.append(",resultURL=");
			data.append( URLEncoder.encode(resultUrl.toString(), UnionpayUtil.CHARSET));
			data.append(",usetestmode=");
			data.append(UnionpayUtil.USETEST_MODE );
			data.append("");
			paydata =  URLEncoder.encode(Base64.getBASE64(data.toString()), UnionpayUtil.CHARSET);
		} catch (UnsupportedEncodingException e) {
			logger.error("Unionpay returnPayDate: e={}", e);
			e.printStackTrace();
			return null;
			
		}
		return paydata;
	}
	
//	private String getBaseResultUrl(SiteType siteType){
//		if(SiteType.AOLAI.equals(siteType)){
//			return UnionpayPropertyUtil.AOLAI_RESULT_URL;
//		}
//		if(SiteType.SHANGPIN.equals(siteType)){
//			return UnionpayPropertyUtil.SHANGPIN_RESULT_URL;
//		}
//		return null;
//
//	}
	/**
	 *银联支付异步通知验签
	 * 
	 * @param params
	 *            返回参数
	 * @param secret
	 *            secret
	 * @return 验签结果
	 * 
	 */
	private boolean vertifyNotifySign(Map<String, String[]> params,
			String secret, Map<String, String> resp) {
		if (params.size() > 0) {
			// String respSignature = params.get(KEY_SIGNATURE);
			// paravalue = ((String[])params.get(KEY_SIGNATURE))[0];
			String respSignature = ((String[]) params.get(UnionpayUtil.KEY_SIGNATURE))[0];
			// 除去数组中的空值和签名参数
			for (Object key : params.keySet()) {
				String value = params.get(key)[0].toString();
				if (value == null|| value.equals("")|| ((String) key).equalsIgnoreCase(UnionpayUtil.KEY_SIGNATURE)|| ((String) key).equalsIgnoreCase(UnionpayUtil.KEY_SIGNATURE_METHOD)) {
					continue;
				}
				resp.put((String) key, value);
			}
			String signature =UnionpayUtil. buildSignature(resp, secret);
			if (null != respSignature && respSignature.equals(signature)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		UnionpayServiceImpl al=new UnionpayServiceImpl();
		UnionPayBo bo=new UnionPayBo();
		bo.setOrderNumber("2012315641234");
		bo.setOrderTimeStart("20141030150123");
		bo.setTotalFee("1");
		bo.setCallbackUrl("http://m.shangpin.com");
		bo.setNotifyUrl("http://m.shangpin.com");
//		bo.setSiteType(SiteType.SHANGPIN);

		try {
			System.out.println(al.wapPay(bo));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Map<String, Object> wapPayV2(UnionPayBo unionPayBo) throws ServiceException {
		   logger.debug("*******PAY MODEL:come in alipay's method wapPay*****");

	        if (unionPayBo == null || StringUtil.isEmpty(unionPayBo.getOrderNumber(), unionPayBo.getTotalFee(), unionPayBo.getCallbackUrl())) {
	            throw new ServiceException(ErrType.MISS_PARAMETER, "Missing parameters，please check!");
	        }
	        logger.debug("*****PAY MODEL:orderId,money,callback,notify,merchant*****>>:" + unionPayBo.getOrderNumber());
	        logger.debug("*****PAY MODEL:money*****>>:" + unionPayBo.getTotalFee());
	        logger.debug("*****PAY MODEL:callback*****>>:" + unionPayBo.getCallbackUrl());
	        Map<String, Object> unParams = new HashMap<String, Object>();
	    	Map<String, String> params = new TreeMap<String, String>();
			params.put("gateWay", unionPayBo.getGateWay());
			params.put("orderNo", unionPayBo.getOrderNumber());
			params.put("returnUrl", unionPayBo.getCallbackUrl());
			params.put("subject", unionPayBo.getProductName());
			String totalFee=unionPayBo.getTotalFee();
		    // 判断是否是测试环境
	        if (AlipayPropertyUtil.devModule) {
	            totalFee = "0.01";
	        }
			params.put("totalFee",new BigDecimal(totalFee).setScale(2).toString() );
			logger.info("unpay sign str:{}",SortedValueUtil.sortedValue(params));
			logger.info("unpay sign str:{}",SortedValueUtil.sortedValue(params)+CommonPropertyUtil.encryptKey);
			
			String sign=MD5Util.md5(SortedValueUtil.sortedValue(params)+CommonPropertyUtil.encryptKey,null);
			logger.info("unpay sign sign:{}",sign);
			params.put("sign",sign);
			unParams.put("gatewayUrl", CommonPropertyUtil.payRequestUrl);
			unParams.put("requestParams",params);

	        return unParams;

	}
	
	
	
}
