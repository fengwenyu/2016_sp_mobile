package com.shangpin.pay.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.shangpin.pay.base.ErrType;
import com.shangpin.pay.base.WxPayHelper;
import com.shangpin.pay.bo.CommonBackBo;
import com.shangpin.pay.bo.WXPayBo;
import com.shangpin.pay.bo.WXPayDataBo;
import com.shangpin.pay.bo.WeChatPayBo;
import com.shangpin.pay.exception.ServiceException;
import com.shangpin.pay.service.WeixinpayService;
import com.shangpin.pay.utils.common.CommonPropertyUtil;
import com.shangpin.pay.utils.common.MD5Util;
import com.shangpin.pay.utils.common.SortedValueUtil;
import com.shangpin.pay.utils.common.StringUtil;
import com.shangpin.pay.utils.weixin.SHA1Util;
import com.shangpin.pay.utils.weixin.WeixinpayPropertyUtil;
import com.shangpin.pay.utils.weixin.WeixinpayUtil;
import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.JsonUtil;
import com.shangpin.wechat.constants.OpenPlatformConstants;
import com.shangpin.wechat.utils.common.Sha1Util;
import com.shangpin.wechat.utils.openplatform.WeChatOpenUtil;

@Service
public class WeixinpayServiceImpl extends BaseServiceImpl implements WeixinpayService {
	public static Logger logger = LoggerFactory.getLogger(WeixinpayServiceImpl.class);
	
	@Autowired
	com.shangpin.wechat.service.WeChatOpenService wechatOpenService;

	@Override
	public WXPayDataBo jsApiPay(WXPayBo wxpayBo) throws ServiceException {
		if (wxpayBo == null || StringUtil.isEmpty(wxpayBo.getOrderId(), wxpayBo.getProductName(), wxpayBo.getTotalFee(), wxpayBo.getIp(), wxpayBo.getNotifyUrl())) {
			throw new ServiceException(ErrType.MISS_PARAMETER, "Missing parameters，please check!");
		}
		WxPayHelper wxPayHelper = getWxPayHelper(wxpayBo);
		WXPayDataBo wxPayDataBo;
		try {
			wxPayDataBo = wxPayHelper.CreateBizjsPackage();
		} catch (Exception e) {
			logger.error("WXPay jsApiPay:e={}", e);
			throw new ServiceException(ErrType.ILLEGAL_REQDATA, "生成jsapi支付请求json异常");
		}
		logger.info("WXPay jsApiPay:appId={},package={},timeStamp={},noncestr={},paySign={},signType={}", wxPayDataBo.getAppId(), wxPayDataBo.getPackageStr(),
				wxPayDataBo.getTimeStamp(), wxPayDataBo.getNonceStr(), wxPayDataBo.getPaySign(), wxPayDataBo.getSignType());
		return wxPayDataBo;
	}

	@Override
	public CommonBackBo notify(HttpServletRequest request) throws ServiceException {
		CommonBackBo commonBackBo = new CommonBackBo();
		// 判断签名
		if (!isWeixinSign(request)) {
			logger.warn("Micro payment notification letter signature verification failed");
			throw new ServiceException(ErrType.NOTIFYSIFNCHECK_ERROR, "Notify signature verification failed");

		}
		// 确保通知来自微信
		// 后台调用
		Map<String, String> resMap = new HashMap<String, String>();
		resMap = isWeixinSignature(request);
		if (!resMap.get("isWeixinSignature").equals("true")) {
			logger.warn("Micro letter pay query failed to verify the signature or business errors");
			throw new ServiceException(ErrType.NOTIFYSIFNCHECK_ERROR, "Notify signature verification failed");
		}
		// 获取返回参数
		String tradeState = request.getParameter("trade_state");
		String tradeMode = request.getParameter("trade_mode");
		logger.info("WXPay notify trade_state={},trade_mode={} ", tradeState, tradeMode);
		final String orderId = request.getParameter("out_trade_no");
		final String totalFee = request.getParameter("total_fee");
		final String tradeNo = request.getParameter("transaction_id");
		// 判断签名及结果
		if (WeixinpayUtil.TRADESTATE_SUCCESS.equals(tradeState) && WeixinpayUtil.TRADEMODE_SUCCESS.equals(tradeMode)) {
			// TODO 是否通知微信待定
			// sendToWeixin("success", response);
			commonBackBo = getCommonBackBo(true, orderId, totalFee,tradeNo);
			logger.info("WXpay Notify pay successed：orderId={},totalFee={}", orderId, totalFee);
			return commonBackBo;
		} else {
			commonBackBo = getCommonBackBo(false, orderId, totalFee,tradeNo);
			return commonBackBo;
		}

	}

	@Override
	public WeChatPayBo WeChatPay(Map<String, String> inParams) throws Exception {
		WeChatPayBo wechatPayBo = new WeChatPayBo();
		// 商户固定信息
		// final String gateUrl = "";

		// 商户传递的参数
		String body = inParams.get("body");
		String outTradeNo = inParams.get("outTradeNo");
		String spbillCreateIp = inParams.get("spbillCreateIp");
		String totalFee = inParams.get("totalFee");
		String notifyUrl = inParams.get("notifyUrl");

		// 获取token值
		String token = wechatOpenService.getToken();
		if (!"".equals(token)) {
			// =========================
			// 生成预支付单
			// =========================
			// 设置package订单参数
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("bank_type", "WX"); // 商品描述
			packageParams.put("body", body); // 商品描述
			packageParams.put("notify_url", notifyUrl); // 接收财付通通知的URL
			packageParams.put("partner", OpenPlatformConstants.OPEN_PARTNER); // 商户号
			packageParams.put("out_trade_no", outTradeNo); // 商家订单号
			if (WeixinpayPropertyUtil.devModule && !"0".equals(totalFee)) {
				totalFee = "1";
			}
			packageParams.put("total_fee", totalFee); // 商品金额,以分为单位
			packageParams.put("spbill_create_ip", spbillCreateIp); // 订单生成的机器IP，指用户浏览器端IP
			packageParams.put("fee_type", "1"); // 币种，1人民币 66
			packageParams.put("input_charset", OpenPlatformConstants.CHARSET); // 字符编码

			// 获取package包
			String packageValue = WeChatOpenUtil.getPackage(packageParams);

			String noncestr = Sha1Util.getNonceStr();
			String timestamp = Sha1Util.getTimeStamp();
			String traceid = inParams.get("userId");

			// 设置支付参数
			SortedMap<String, String> signParams = new TreeMap<String, String>();
			signParams.put("appid", OpenPlatformConstants.OPEN_APP_ID);
			signParams.put("appkey", OpenPlatformConstants.OPEN_APP_KEY);
			signParams.put("noncestr", noncestr);
			signParams.put("package", packageValue);
			signParams.put("timestamp", timestamp);
			signParams.put("traceid", traceid);

			// 生成支付签名，要采用URLENCODER的原始值进行SHA1算法！
			String sign = Sha1Util.createSHA1Sign(signParams);
			// 增加非参与签名的额外参数
			signParams.put("app_signature", sign);
			signParams.put("sign_method", "sha1");

			// 获取prepayId
			String prepayid = wechatOpenService.getPrepayid(token, signParams);

			if (null != prepayid && !"".equals(prepayid)) {
				// 签名参数列表
				SortedMap<String, String> prePayParams = new TreeMap<String, String>();
				prePayParams.put("appid", OpenPlatformConstants.OPEN_APP_ID);
				prePayParams.put("appkey", OpenPlatformConstants.OPEN_APP_KEY);
				prePayParams.put("noncestr", noncestr);
				prePayParams.put("package", "Sign=WXPay");
				prePayParams.put("partnerid", OpenPlatformConstants.OPEN_PARTNER);
				prePayParams.put("prepayid", prepayid);
				prePayParams.put("timestamp", timestamp);
				// 生成签名
				sign = Sha1Util.createSHA1Sign(prePayParams);

				// 输出参数
				wechatPayBo.setPayCode("0");
				wechatPayBo.setPayMsg("OK");
				wechatPayBo.setAppId(OpenPlatformConstants.OPEN_APP_ID);
				wechatPayBo.setPartnerId(OpenPlatformConstants.OPEN_PARTNER);
				wechatPayBo.setNonceStr(noncestr);
				wechatPayBo.setDataPackage("Sign=WXPay");
				wechatPayBo.setPrepayId(prepayid);
				wechatPayBo.setTimestamp(timestamp);
				wechatPayBo.setSign(sign);

			} else {
				wechatPayBo.setPayCode("-2");
				wechatPayBo.setPayMsg("Error: failed to get prepayId !");
			}
		} else {
			wechatPayBo.setPayCode("-1");
			wechatPayBo.setPayMsg("Error: can't get Token !");
		}
		return wechatPayBo;
	}
	
	/**
	 * 返回处理结果给财付通服务器。
	 * 
	 * @param msg
	 *            : Success or fail。
	 * @throws IOException
	 */
	void sendToWeixin(String msg, HttpServletResponse response) throws IOException {
		String strHtml = msg;
		PrintWriter out = response.getWriter();
		out.println(strHtml);
		out.flush();
		out.close();

	}

	/**
	 * 微信异步验签
	 * 
	 * @param request
	 *            return boolean验签是否成功
	 */
	@SuppressWarnings("rawtypes")
	boolean isWeixinSign(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer sb = new StringBuffer();
		Set es = request.getParameterMap().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			if (entry.getValue() != null) {
				String v = request.getParameter(k);// ((String[])entry.getValue())[0];
				if (!"sign".equals(k) && null != v && !"".equals(v)) {
					sb.append(k + "=" + v + "&");
					map.put(k, v);
				}
			}
		}

		String signEncode = WeixinpayUtil.attachParams(map, true, request.getParameter("input_charset") != null ? request.getParameter("input_charset") : "GBK");
		// 1.1 urlencode
		logger.info("Micro asynchronous notification letter before signmd5 string sb={}", sb.toString());
		signEncode = signEncode + "&key=" + WeixinpayPropertyUtil.PARTNER_KEY;
		logger.info("Micro asynchronous notification letter before signmd5 signEncode={}", signEncode);
		// 2 md5
		String sign = MD5Util.MD5(signEncode).toUpperCase();
		// 3 final string
		// String result = signEncode + sign;
		String tenpaySign = request.getParameter("sign").toUpperCase();
		logger.info("Micro asynchronous notification letter: tenpaySign={}", tenpaySign);
		return tenpaySign.equals(sign);
	}

	/**
	 * 1、appid、appkey、timestamp、noncestr、openid、issubscribe 按照ascii码排序 2、sha编码
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	Map<String, String> isWeixinSignature(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		Map<String, String> resMap = new HashMap<String, String>();
		TreeMap map = new TreeMap();
		TreeMap map1 = getPostData(request);
		String timeStamp = String.valueOf(map1.get("timestamp"));
		String nonceStr = String.valueOf(map1.get("noncestr"));
		String openId = String.valueOf(map1.get("openid"));
		String issubscribe = String.valueOf(map1.get("issubscribe"));
		map.put("appid", WeixinpayPropertyUtil.APP_ID);
		map.put("appkey", WeixinpayPropertyUtil.PAYSIGN_KEY);
		if (StringUtil.isNotEmpty(timeStamp)) {
			map.put("timestamp", timeStamp);
		}

		if (StringUtil.isNotEmpty(nonceStr)) {
			map.put("noncestr", nonceStr);
		}
		if (StringUtil.isNotEmpty(openId)) {
			map.put("openid", openId);
		}
		if (StringUtil.isNotEmpty(issubscribe)) {
			map.put("issubscribe", issubscribe);
		}
		String[] keys = (String[]) map.keySet().toArray(new String[0]);
		for (int i = 0; i < keys.length; i++) {
			if (i == keys.length - 1) {
				sb.append(keys[i] + "=" + map.get(keys[i]));
			} else {
				sb.append(keys[i] + "=" + map.get(keys[i]) + "&");
			}
		}
		// appSignature
		String appSignature = (String) map1.get("appsignature");
		// return appSignature.equals(SHA1Util.Sha1(sb.toString()));
		if (appSignature.equals(SHA1Util.Sha1(sb.toString()))) {
			resMap.put("isWeixinSignature", "true");
			resMap.put("openId", openId);
		} else {
			resMap.put("isWeixinSignature", "false");
		}
		return resMap;
	}

	/**
	 * 取得postData数据
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	TreeMap getPostData(HttpServletRequest request) {
		TreeMap map = new TreeMap();
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(request.getInputStream());
			Element root = doc.getRootElement();
			Iterator<?> rootIter = root.elementIterator();
			while (rootIter.hasNext()) {
				Element rootElement = (Element) rootIter.next();
				map.put(rootElement.getName().toLowerCase(), rootElement.getText());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;

	}

	/**
	 * 处理支付请求数据
	 * 
	 * @param WxPayHelper
	 * @return
	 */
	WxPayHelper getWxPayHelper(WXPayBo wxpayBo) {
		WxPayHelper wxPayHelper = new WxPayHelper();
		// 先设置基本信息
		wxPayHelper.SetAppId(WeixinpayPropertyUtil.APP_ID);
		wxPayHelper.SetAppKey(WeixinpayPropertyUtil.PAYSIGN_KEY);
		wxPayHelper.SetPartnerKey(WeixinpayPropertyUtil.PARTNER_KEY);
		String signType = wxpayBo.getSignType();
		signType = signType != null ? signType : "sha1";
		wxPayHelper.SetSignType(signType);
		String bankType = wxpayBo.getBankType();
		String feeType = wxpayBo.getFeeType();
		String orderTimeStart = wxpayBo.getOrderTimeStart();
		String orderTimeout = wxpayBo.getOrderTimeout();
		String transportFee = wxpayBo.getTransportFee();
		String productFee = wxpayBo.getProductFee();
		String attach = wxpayBo.getAttach();
		// 设置请求package信息
		wxPayHelper.SetParameter("bank_type", bankType != null ? bankType : "WX");
		wxPayHelper.SetParameter("body", wxpayBo.getProductName());
		wxPayHelper.SetParameter("partner", WeixinpayPropertyUtil.PARTNER_ID);
		wxPayHelper.SetParameter("out_trade_no", wxpayBo.getOrderId().trim());
		if (WeixinpayPropertyUtil.devModule == false) {// 判断是否为测试订单
			wxPayHelper.SetParameter("total_fee", wxpayBo.getTotalFee());
		} else {
			wxPayHelper.SetParameter("total_fee", "1");
		}
		wxPayHelper.SetParameter("fee_type", feeType != null ? feeType : "CNY");
		wxPayHelper.SetParameter("notify_url", wxpayBo.getNotifyUrl().trim());
		wxPayHelper.SetParameter("spbill_create_ip", wxpayBo.getIp());
		wxPayHelper.SetParameter("input_charset", WeixinpayPropertyUtil.CHARSET);
		if (StringUtils.isNotEmpty(orderTimeout)) {
			wxPayHelper.SetParameter("time_expire", orderTimeout);
		}
		if (StringUtils.isNotEmpty(orderTimeStart)) {
			wxPayHelper.SetParameter("time_start", orderTimeStart);
		}
		if (StringUtils.isNotEmpty(transportFee)) {
			wxPayHelper.SetParameter("transport_fee", transportFee);
		}
		if (StringUtils.isNotEmpty(productFee)) {
			wxPayHelper.SetParameter("product_fee", productFee);
		}
		if (StringUtils.isNotEmpty(attach)) {
			wxPayHelper.SetParameter("attach", attach);
		}
		return wxPayHelper;
	}

	@SuppressWarnings("unchecked")
    @Override
	public Map<String, Object> pubPay(WXPayBo wxpayBo) throws ServiceException {
		if (wxpayBo == null || StringUtil.isEmpty(wxpayBo.getOrderId(), wxpayBo.getProductName(), wxpayBo.getTotalFee(), wxpayBo.getIp(), wxpayBo.getOpenId())) {
			throw new ServiceException(ErrType.MISS_PARAMETER, "Missing parameters，please check!");
		}
		Map<String, String> requestParams = new TreeMap<String, String>();
		requestParams.put("subject", wxpayBo.getProductName());
		requestParams.put("gateWay", wxpayBo.getGateway());
		requestParams.put("orderNo", wxpayBo.getOrderId());
		requestParams.put("openId", wxpayBo.getOpenId());
		String totalFee=wxpayBo.getTotalFee();
		if (WeixinpayPropertyUtil.devModule) {// 判断是否为测试订单
			totalFee="0.01";
		} 
		requestParams.put("totalFee", new BigDecimal(totalFee).setScale(2).toString());
		logger.info("weixin pubpay sign str:{}",SortedValueUtil.sortedValue(requestParams));
		logger.info("weixin pubpay sign str:{}",SortedValueUtil.sortedValue(requestParams)+CommonPropertyUtil.encryptKey);
		
		String sign=MD5Util.md5(SortedValueUtil.sortedValue(requestParams)+CommonPropertyUtil.encryptKey,null);
		logger.info("weixin pubpay sign sign:{}",sign);
		requestParams.put("sign",sign);
		String result = HttpClientUtil.doPost(CommonPropertyUtil.payRequestUrl, requestParams,WeixinpayPropertyUtil.CHARSET,wxpayBo.getReferer());
		logger.info("WXPUBPay pubPay result={}",result);
		Map<String, Object> map  = JsonUtil.fromJson(result,new TypeToken<HashMap<String, Object>>() {});
	
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("requestParams", (Map<String, String>) map.get("requestParams"));
		return params;
	}

	@Override
	public Map<String, Object> wapPay(WXPayBo wxpayBo) throws ServiceException {
		if (wxpayBo == null || StringUtil.isEmpty(wxpayBo.getOrderId(), wxpayBo.getProductName(), wxpayBo.getTotalFee())) {
			throw new ServiceException(ErrType.MISS_PARAMETER, "Missing parameters，please check!");
		}
		Map<String, String> requestParams = new TreeMap<String, String>();
		requestParams.put("subject", wxpayBo.getProductName());
		requestParams.put("gateWay", wxpayBo.getGateway());
		requestParams.put("orderNo", wxpayBo.getOrderId());
		String totalFee=wxpayBo.getTotalFee();
		if (WeixinpayPropertyUtil.devModule) {// 判断是否为测试订单
			totalFee="0.01";
		} 
		requestParams.put("totalFee",new BigDecimal(totalFee).setScale(2).toString() );
		logger.info("weixin wappay sign str:{}",SortedValueUtil.sortedValue(requestParams));
		logger.info("weixin wappay sign str:{}",SortedValueUtil.sortedValue(requestParams)+CommonPropertyUtil.encryptKey);
		
		String sign=MD5Util.MD5(SortedValueUtil.sortedValue(requestParams)+CommonPropertyUtil.encryptKey);
		
		logger.info("weixin wappay sign sign:{}",sign);
		requestParams.put("sign",sign);
	/*	String result = HttpClientUtil.doPost(WeixinpayPropertyUtil.payFacadeUrl.toString(), requestParams,WeixinpayPropertyUtil.CHARSET);
		logger.info("WXPUBPay pubPay result={}",result);
		Map<String, Object> map = new HashMap<String, Object>();*/
	
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("requestParams", requestParams);
		params.put("gatewayUrl", CommonPropertyUtil.payRequestUrl);
		return params;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> pubSeaPay(WXPayBo wxpayBo) throws ServiceException {
		if (wxpayBo == null || StringUtil.isEmpty(wxpayBo.getOrderId(), wxpayBo.getProductName(), wxpayBo.getTotalFee(), wxpayBo.getIp())) {
			throw new ServiceException(ErrType.MISS_PARAMETER, "Missing parameters，please check!");
		}
		Map<String, String> requestParams = new TreeMap<String, String>();
		requestParams.put("body", wxpayBo.getProductName());
		requestParams.put("subject", wxpayBo.getProductName());
		requestParams.put("gateway", wxpayBo.getGateway());
		requestParams.put("orderNo", wxpayBo.getOrderId());
		requestParams.put("customerIp", wxpayBo.getIp());
		String totalFee=wxpayBo.getTotalFee();
		if (WeixinpayPropertyUtil.devModule) {// 判断是否为测试订单
			totalFee="0.01";
		} 
		requestParams.put("totalFee", new BigDecimal(totalFee).setScale(2).toString());
		requestParams.put("ext", wxpayBo.getExt());
		logger.info("weixin pubpay sign str:{}",SortedValueUtil.sortedValue(requestParams));
		logger.info("weixin pubpay sign str:{}",SortedValueUtil.sortedValue(requestParams)+CommonPropertyUtil.encryptKey);
		try{
			Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
					.create();
			String reqStr = gson.toJson(requestParams);

			logger.info("pub Pay {} request={}",wxpayBo.getGateway(),reqStr);
			String result = HttpClientUtil.doPostWithJSON(CommonPropertyUtil.payFacadeUrl.toString(), reqStr,CommonPropertyUtil.CHARSET);
			logger.info("pub Pay {} result={}",wxpayBo.getGateway(),result);
			Map<String, Object> map = new HashMap<String, Object>();

			map = JsonUtil.fromJson(result,new TypeToken<HashMap<String, Object>>() {});
			if (map.get("err") != null) {
				throw new ServiceException(ErrType.RESRESULT_ERROR,"result error，please check!");
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("requestParams", (Map<String, String>) map.get("requestParams"));
			return params;
		}catch(Exception e){
			throw new ServiceException(ErrType.RESRESULT_ERROR,"result error，please check!");
		}
		
		
	}

}
