/**
 * Alipay.com Inc.
 * Copyright (c) 2005-2008 All Rights Reserved.
 */
package com.shangpin.wireless.api.pay.alipay;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shangpin.wireless.api.api2client.domain.GetAlipayTokenAPIResponse;
import com.shangpin.wireless.api.api2client.domain.GetAlipayUserInfoAPIResponse;
import com.shangpin.wireless.api.api2server.domain.GetAddressServerResponse;
import com.shangpin.wireless.api.businessbean.CityBean;
import com.shangpin.wireless.api.domain.AccountAlipay;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.service.AccountAlipayService;
import com.shangpin.wireless.api.util.AddressUtil;
import com.shangpin.wireless.api.util.HqlHelper;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 接收通知并处理
 * 
 */
public class RSANotifyReceiverAlipayLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(RSANotifyReceiverAlipayLogin.class.getSimpleName());
	private AccountAlipayService accountAlipayService;

	@Override
	public void init() throws ServletException {
		ServletContext sc = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		accountAlipayService = (AccountAlipayService) ctx.getBean(AccountAlipayService.SERVICE_NAME);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.debug("RSANotifyReceiverAlipayLogin : alipay login advice");
        //获得通知参数
    	final Map map = request.getParameterMap();
        //获得通知签名
        final String sign = (String) ((Object[]) map.get("sign"))[0];

        final String userType = (String) ((Object[]) map.get("userType"))[0];
        final String alipay_user_id = (String) ((Object[]) map.get("alipay_user_id"))[0];
        final String auth_code = (String) ((Object[]) map.get("token"))[0];
        final String sign_type = (String) ((Object[]) map.get("sign_type"))[0];
        //获得待验签名的数据
        final String verifyData = "token=" + auth_code + "&user_id=" + alipay_user_id + "&userType=" + userType;
        boolean verified = false;
        final String channelNo = request.getHeader("ch");// 渠道号
        //使用支付宝公钥验签名
        
        try {
        	String mysign = RSASignatureAlipay.sign(verifyData);
            verified = RSASignatureAlipay.doCheck(verifyData, sign, PartnerConfigAlipay.RSA_ALIPAY_PUBLIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //验证签名通过
        if (true) {
        	//当交易状态成功，处理业务逻辑成功。回写success
        	
//    		String verifyData = "notify_data=<notify><partner>2088101954925026</partner><discount>0.00</discount><payment_type>1</payment_type><subject>订单号：2012092505139</subject><trade_no>2012092564594636</trade_no><buyer_email>qiufengfeilu@hotmail.com</buyer_email><gmt_create>2012-09-25 16:24:14</gmt_create><quantity>1</quantity><out_trade_no>2012092505139</out_trade_no><seller_id>2088101954925026</seller_id><trade_status>TRADE_FINISHED</trade_status><is_total_fee_adjust>N</is_total_fee_adjust><total_fee>1.00</total_fee><gmt_payment>2012-09-25 16:24:16</gmt_payment><seller_email>zhifubao@vansky.cn</seller_email><gmt_close>2012-09-25 16:24:16</gmt_close><price>1.00</price><buyer_id>2088002912023364</buyer_id><use_coupon>N</use_coupon></notify>";
        	out.print("success");
        	log.debug("RSANotifyReceiverAlipayLogin : alipaylogin_notify success");

			new GetAlipayUserInfo(accountAlipayService, alipay_user_id, auth_code).start();
        } else {
            out.print("fail");
        }
        out.close();
        log.debug("RSANotifyReceiverAlipayLogin : alipay login finish");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
    
	private static final String ALI_OPEN_URL = "https://mapi.alipay.com/gateway.do";

	private static final String getAlipayToken (String authcode, String refreshtoken) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("service", "alipay.open.auth.token.exchange");
		map.put("timestamp", sdf.format(new Date()));
//		map.put("format", "json");
		map.put("_input_charset", "utf-8");
		map.put("partner", PartnerConfigAlipay.PARTNER);

		map.put("grant_type", "authorization_code");
		map.put("code", authcode);

//		final String digest = StringUtil.buildParams(map, true, false);
//		String signature = RSASignatureAlipay.sign(digest);
		final String digest = StringUtil.buildParams(map, true, false) + PartnerConfigAlipay.MD5KEY;
		String signature = DigestUtils.md5Hex(digest.getBytes("UTF-8"));
		map.put("sign_type", "MD5");
		map.put("sign", signature);
		final String postStr = StringUtil.buildParams(map, false, true);
		HashMap<String, String> header = new HashMap<String, String>();
		header.put("Accept", "text/xml,text/javascript,text/html");
		header.put("User-Agent", "aop-sdk-java");
		header.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		String xmlStr = WebUtil.readContentFromPost(ALI_OPEN_URL, postStr, header);
		
		return xmlStr;
	}
	private static final String getAlipayUserInfo (String accesstoken) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("service", "alipay.user.userinfo.share");
		map.put("timestamp", sdf.format(new Date()));
		map.put("format", "json");
		map.put("_input_charset", "utf-8");
		map.put("partner", PartnerConfigAlipay.PARTNER);
		
		map.put("query_token", accesstoken);

		final String digest = StringUtil.buildParams(map, true, false) + PartnerConfigAlipay.MD5KEY;
		String signature = DigestUtils.md5Hex(digest.getBytes("UTF-8"));
		map.put("sign_type", "MD5");
		map.put("sign", signature);
		final String postStr = StringUtil.buildParams(map, false, true);
		HashMap<String, String> header = new HashMap<String, String>();
		header.put("Accept", "text/xml,text/javascript,text/html");
		header.put("User-Agent", "aop-sdk-java");
		header.put("Content-Type", "application/x-www-form-urlencoded;charset=GBK");
		String xmlStr = WebUtil.readContentFromPost(ALI_OPEN_URL, postStr, header);
		
		return xmlStr;
	}
	private static final void updateUserInfo (GetAlipayUserInfoAPIResponse userinfoResponse, AccountAlipayService accountAlipayService) throws Exception {
		HqlHelper hqlHelper = new HqlHelper(AccountAlipay.class, "aa");
		hqlHelper.addWhereCondition("aa.aliUserId=?", userinfoResponse.getAliUserId());
		AccountAlipay aliaccount = accountAlipayService.getByCondition(hqlHelper);
		// 登录需要记录到account表的信息
		if (aliaccount != null) {
			if (userinfoResponse.getEmail() == null && userinfoResponse.getMobile() == null) return;
			aliaccount.setGender(userinfoResponse.getGender());
			aliaccount.setNickname(userinfoResponse.getName());
			aliaccount.setCity(userinfoResponse.getCity());
			aliaccount.setMobile(userinfoResponse.getMobile());
			if (userinfoResponse.getEmail() != null && userinfoResponse.getEmail().indexOf("@") > 0) {
				aliaccount.setEmail(userinfoResponse.getEmail());
			}
			accountAlipayService.update(aliaccount);
		} else {
			return;
		}
		
		//	同步收货地址
		if (StringUtil.isNotEmpty(aliaccount.getUserId())
//				&& StringUtil.isNotEmpty(apiResponse.getProvince(), apiResponse.getCity(), apiResponse.getArea())
				&& StringUtil.isNotEmpty(userinfoResponse.getAddress(), userinfoResponse.getZip())
				&& (StringUtil.isNotEmpty(userinfoResponse.getName()) || StringUtil.isNotEmpty(userinfoResponse.getDeliverFullname()))
				&& (StringUtil.isNotEmpty(userinfoResponse.getMobile()) || StringUtil.isNotEmpty(userinfoResponse.getDeliverMobile()))) {

			final String userid = aliaccount.getUserId();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("userid", userid);
			String url = Constants.BASE_URL + "getconsigneeaddress/";
			try {
				String data = WebUtil.readContentFromGet(url, map);
				GetAddressServerResponse serverResponse = new GetAddressServerResponse();
				serverResponse.json2Obj(data);
				
				if (serverResponse.getSize() == 0) {
//					查找省市区
					String address = userinfoResponse.getAddress();
					CityBean province = null;
					if (StringUtil.isNotEmpty(userinfoResponse.getProvince())) {
						province = AddressUtil.findBean(userinfoResponse.getProvince(), null, CityBean.TYPE_PROVINCE);
					} else {
						province = AddressUtil.findBean(address, null, CityBean.TYPE_PROVINCE);
						if (province != null) {
							address = address.substring(province.getName().length());
							if (address.startsWith("省")) address = address.substring(1);
						}
					}
					if (province == null) return;

					CityBean city = null;
					if (StringUtil.isNotEmpty(userinfoResponse.getCity())) {
						city = AddressUtil.findBean(userinfoResponse.getCity(), province.getId(), CityBean.TYPE_CITY);
					} else {
						city = AddressUtil.findBean(address, province.getId(), CityBean.TYPE_CITY);
						if (city != null) {
							address = address.substring(city.getName().length());
						}
					}
					if (city == null) return;

					CityBean area = null;
					if (StringUtil.isNotEmpty(userinfoResponse.getCity())) {
						area = AddressUtil.findBean(userinfoResponse.getCity(), city.getId(), CityBean.TYPE_AREA);
					} else {
						area = AddressUtil.findBean(address, city.getId(), CityBean.TYPE_AREA);
						if (area != null) {
							address = address.substring(area.getName().length());
						}
					}
					if (area == null) return;

					hqlHelper = new HqlHelper(AccountAlipay.class, "aa");
					hqlHelper.addWhereCondition("aa.aliUserId=?", userinfoResponse.getAliUserId());
					aliaccount = accountAlipayService.getByCondition(hqlHelper);
					// 登录需要记录到account表的信息
					if (aliaccount != null && aliaccount.getCity() == null) {
						aliaccount.setCity(city.getName());
						accountAlipayService.update(aliaccount);
					}
					
					map = new HashMap<String, String>();
					map.put("userid", userid);
					final String consigneename = StringUtil.isNotEmpty(userinfoResponse.getDeliverFullname())?userinfoResponse.getDeliverFullname():userinfoResponse.getName();
					map.put("consigneename", consigneename);
					map.put("address", address);
					map.put("province", province.getId());
					map.put("city", city.getId());
					map.put("area", area.getId());
					map.put("postcode", userinfoResponse.getZip());
					final String tel = StringUtil.isNotEmpty(userinfoResponse.getDeliverMobile())?userinfoResponse.getDeliverMobile():userinfoResponse.getMobile();
					map.put("tel", tel);

					url = Constants.BASE_URL + "AddConsigneeAddress/";
					try {
						data = WebUtil.readContentFromGet(url, map);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	class GetAlipayUserInfo extends Thread {
		private AccountAlipayService accountAlipayService;
		private String alipayuserid;
		private String authcode;
		public GetAlipayUserInfo (AccountAlipayService accountAlipayService, String alipayuserid, String authcode) {
			this.accountAlipayService = accountAlipayService;
			this.alipayuserid = alipayuserid;
			this.authcode = authcode;
		}
		@Override
		public void run () {
			int count = 0;
			try {
//				AlipayClient client = new DefaultAlipayClient(ALI_OPEN_URL, PartnerConfigAlipay.APP_ID, PartnerConfigAlipay.RSA_PRIVATE, "json");
//				AlipayUserUserinfoShareRequest req = new AlipayUserUserinfoShareRequest();
//				AlipayUserUserinfoShareResponse res = client.execute(req, accesstoken); 
//				String jsonStr = res.getBody();
				while (true) {
					HqlHelper hqlHelper = new HqlHelper(AccountAlipay.class, "aa");
					hqlHelper.addWhereCondition("aa.aliUserId=?", alipayuserid);
					AccountAlipay aliaccount = accountAlipayService.getByCondition(hqlHelper);
					if (aliaccount != null) {
						GetAlipayTokenAPIResponse tokenResponse = new GetAlipayTokenAPIResponse();
						String xmlStr = getAlipayToken(authcode, null);
						tokenResponse.xml2Obj(xmlStr);
						
						xmlStr = getAlipayUserInfo (tokenResponse.getAccesstoken());
						
						GetAlipayUserInfoAPIResponse userinfoResponse = new GetAlipayUserInfoAPIResponse();
						userinfoResponse.xml2Obj(xmlStr);
						
						updateUserInfo (userinfoResponse, accountAlipayService);
						
						break;
					} else {
						count ++;
						if (count > 50) return;
						try {
							wait(20000);
						} catch (Exception e) {}
					}
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
