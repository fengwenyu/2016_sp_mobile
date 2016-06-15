package com.shangpin.wechat.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shangpin.utils.DateUtils;
import com.shangpin.utils.HttpsUtil;
import com.shangpin.utils.StringUtil;
import com.shangpin.utils.XmlUtil;
import com.shangpin.wechat.bo.base.CashBonusResult;
import com.shangpin.wechat.bo.base.CashCouponResult;
import com.shangpin.wechat.constants.MerchantPlatformConstants;
import com.shangpin.wechat.service.WeChatMerchantService;
import com.shangpin.wechat.utils.common.SignUtil;

@Service
public class WeChatMerchantServiceImpl implements WeChatMerchantService {
	
	private static final Logger logger = LoggerFactory.getLogger(WeChatMerchantServiceImpl.class);

	@Override
	public CashBonusResult cashBonus(String re_openid, String total_amount, String total_num, String wishing,
			String client_ip, String act_name, String remark) {
		
		String nonce_str = StringUtil.getRandomString(32);
		String mch_billno = MerchantPlatformConstants.MERCHANT_ID + DateUtils.dateToStr(new Date(), "yyyyMMddHHmmssSSS") + StringUtil.getRandomNumber(1);
		String mch_id = MerchantPlatformConstants.MERCHANT_ID;
		String wxappid = MerchantPlatformConstants.APP_ID;
		String send_name = MerchantPlatformConstants.MERCHANT_NAME;
		
		logger.info("["+mch_billno+"]微信现金红包 re_openid＝{},total_amount={}, nonce_str ={},mch_billno ={}, mch_id ={},wxappid ={},send_name ={}",
				re_openid,total_amount,nonce_str,mch_billno,mch_id,wxappid,send_name);
		
		SortedMap<String, String> map = new TreeMap<String, String>();
		map.put("nonce_str", nonce_str);
		map.put("mch_billno", mch_billno);
		map.put("mch_id", mch_id);
		map.put("wxappid", wxappid);
		map.put("send_name", send_name);
		map.put("re_openid", re_openid);
		map.put("total_amount", total_amount);
		map.put("total_num", total_num);
		map.put("wishing", wishing);//祝福语
		map.put("client_ip", client_ip);
		map.put("act_name", act_name);
		map.put("remark", remark);
		
		String sign = SignUtil.createSign(map, MerchantPlatformConstants.PARTNER_KEY);
		
		map.put("sign", sign);
		logger.info("["+mch_billno+"]微信现金红包签名值 sign ={}",sign);		
		
		XmlUtil obj = new XmlUtil("xml");		
		for(Entry<String,String> entry : map.entrySet()){
			obj.addSubXmlObject(new XmlUtil(entry.getKey(), "<![CDATA["+entry.getValue()+"]]>"));
		}

		String xml = obj.toCompactXml();
		logger.info("["+mch_billno+"]微信现金红包post数据 xml ={}",xml);
		
		//获取证书
		InputStream instream = this.getClass().getClassLoader().getResourceAsStream(MerchantPlatformConstants.CERT_PATH);
			
		try {
			String resultXml = HttpsUtil.doPost(instream, MerchantPlatformConstants.CERT_PASSWORD, 
					MerchantPlatformConstants.MERCHANT_CASH_BONUS_URI, xml);
			
			logger.info("["+mch_billno+"]微信现金红包返回数据 resultXml={}",resultXml);
			
			CashBonusResult cashBonusResult = (CashBonusResult) XmlUtil.xmlStrToBean(resultXml, CashBonusResult.class);
			
			return cashBonusResult;
		} catch (Exception e) {			
			logger.error("["+mch_billno+"]微信现金红包程序异常 e={}",e);
		}		
		return null;
	}


	@Override
	public CashCouponResult cashCoupon(String coupon_stock_id,String openid,String userId) {
		
		String openid_count = "1";//openid记录数
		String partner_trade_no = MerchantPlatformConstants.MERCHANT_ID 
				+ DateUtils.dateToStr(new Date(), "yyyyMMddHHmmssSSS") + StringUtil.getRandomNumber(3);//商户单据号
		String appid = MerchantPlatformConstants.APP_ID;//公众账号ID
		String mch_id = MerchantPlatformConstants.MERCHANT_ID;//商户号
		String nonce_str = StringUtil.getRandomString(32);//随机字符串
		
		SortedMap<String, String> map = new TreeMap<String, String>();
		map.put("coupon_stock_id", coupon_stock_id);
		map.put("openid", openid);
		map.put("openid_count", openid_count);
		map.put("partner_trade_no", partner_trade_no);
		map.put("appid", appid);
		map.put("mch_id", mch_id);
		map.put("nonce_str", nonce_str);
		
		String sign = SignUtil.createSign(map, MerchantPlatformConstants.PARTNER_KEY);		
		map.put("sign", sign);		
		
		XmlUtil obj = new XmlUtil("xml");		
		for(Entry<String,String> entry : map.entrySet()){
			obj.addSubXmlObject(new XmlUtil(entry.getKey(), "<![CDATA["+entry.getValue()+"]]>"));
		}

		String xml = obj.toCompactXml();
		logger.info("微信代金券post数据 userId={},openid={},xml={}",userId,openid,xml);
		
		//获取证书
		InputStream instream = this.getClass().getClassLoader().getResourceAsStream(MerchantPlatformConstants.CERT_PATH);
			
		try {
			String resultXml = HttpsUtil.doPost(instream, MerchantPlatformConstants.CERT_PASSWORD, 
					MerchantPlatformConstants.MERCHANT_COUPON_URI, xml);
			
			logger.info("微信代金券返回数据 userId={},openid={},resultXml={}",userId,openid,resultXml);
			
			CashCouponResult cashCouponResult = (CashCouponResult) XmlUtil.xmlStrToBean(resultXml, CashCouponResult.class);
			
			return cashCouponResult;
		} catch (Exception e) {			
			logger.error("微信代金券程序异常 userId={},openid={},e={}",userId,openid,e);
			e.printStackTrace();
		}		
		return null;
	}



}
