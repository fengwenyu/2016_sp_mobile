
package com.shangpin.wireless.api.pay.alipay;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.HqlHelper;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 获取支付宝用户token
 * 
 * @Author:wangwenguan
 * @CreatDate: 2013-08-05
 */
public class GetAlipayTokenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(GetAlipayTokenServlet.class.getSimpleName());
	private AccountAlipayService accountAlipayService;

	@Override
	public void init() throws ServletException {
		ServletContext sc = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		accountAlipayService = (AccountAlipayService) ctx.getBean(AccountAlipayService.SERVICE_NAME);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String alipayuid = request.getParameter("alipayuid");
		final String authcode = request.getParameter("authcode");	//	支付宝授权码
		final String refreshtoken = request.getParameter("refreshtoken");	//	刷新token
		final String productNo = request.getHeader("p");// 产品号
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号

		if (StringUtil.isNotEmpty(alipayuid, authcode, productNo, channelNo)) {
			try {
				GetAlipayTokenAPIResponse apiResponse = new GetAlipayTokenAPIResponse();
				HqlHelper hqlHelper = new HqlHelper(AccountAlipay.class, "aa");
				hqlHelper.addWhereCondition("aa.aliUserId=?", alipayuid);
				AccountAlipay aliaccount = accountAlipayService.getByCondition(hqlHelper);
				if (aliaccount != null) {
					long ut = aliaccount.getUpdateTime().getTime();
					long now = new Date().getTime();
					long elapse = now - ut;
					if (StringUtil.isNotEmpty(refreshtoken)) {	//	已授权用户更新token
//						if (authcode.equals(aliaccount.getAuthcode())) {
//						} else {	//	不同授权码
//						}
						if (elapse > aliaccount.getAccessExpiresSec()*1000
								|| elapse > aliaccount.getRefreshExpiresSec()*1000) {
							//	用refreshtoken交换短token
							String jsonStr = getAlipayToken (authcode, refreshtoken);
							apiResponse.json2Obj_wallet(jsonStr);
							if ("0".equals(apiResponse.getCode())) {
								aliaccount.setAuthcode(authcode);
								aliaccount.setAccesstoken(apiResponse.getAccesstoken());
								aliaccount.setAccessExpiresSec(apiResponse.getAccessExpiresSec());
								aliaccount.setRefreshtoken(apiResponse.getRefreshtoken());
								aliaccount.setRefreshExpiresSec(apiResponse.getRefreshExpiresSec());
								aliaccount.setUpdateTime(new Date());
								accountAlipayService.update(aliaccount);
							}
						} else {	//	token未过期
							//	不用重新获取
							apiResponse.setAliUserId(aliaccount.getAliUserId());
							apiResponse.setAccesstoken(aliaccount.getAccesstoken());
							int accessExpiresSec = (int)((long)aliaccount.getAccessExpiresSec()*1000 - elapse) / 1000;
							apiResponse.setAccessExpiresSec(accessExpiresSec);
							apiResponse.setRefreshtoken(aliaccount.getRefreshtoken());
							int refreshExpiresSec = (int)((long)aliaccount.getRefreshExpiresSec()*1000 - elapse) / 1000;
							apiResponse.setRefreshExpiresSec(refreshExpiresSec);
							apiResponse.setCode("0");
							apiResponse.setMsg("");
						}
					} else {	//	用户授权
//						用授权码获取token
						if ("null".equals(authcode) || (authcode.equals(aliaccount.getAuthcode()) && StringUtil.isNotEmpty(aliaccount.getAccesstoken()))) {	//	处理客户端的错误，返回上次数据
							apiResponse.setAliUserId(aliaccount.getAliUserId());
							apiResponse.setAccesstoken(aliaccount.getAccesstoken());
							int accessExpiresSec = (int)((long)aliaccount.getAccessExpiresSec()*1000 - elapse) / 1000;
							apiResponse.setAccessExpiresSec(accessExpiresSec);
							apiResponse.setRefreshtoken(aliaccount.getRefreshtoken());
							int refreshExpiresSec = (int)((long)aliaccount.getRefreshExpiresSec()*1000 - elapse) / 1000;
							apiResponse.setRefreshExpiresSec(refreshExpiresSec);
							apiResponse.setCode("0");
							apiResponse.setMsg("");
						} else {
							String jsonStr = getAlipayToken (authcode, null);
							apiResponse.json2Obj_wallet(jsonStr);
							if ("0".equals(apiResponse.getCode())) {
								aliaccount.setAuthcode(authcode);
								aliaccount.setAccesstoken(apiResponse.getAccesstoken());
								aliaccount.setAccessExpiresSec(apiResponse.getAccessExpiresSec());
								aliaccount.setRefreshtoken(apiResponse.getRefreshtoken());
								aliaccount.setRefreshExpiresSec(apiResponse.getRefreshExpiresSec());
								aliaccount.setUpdateTime(new Date());
								accountAlipayService.update(aliaccount);
							}
						}
					}

					if (aliaccount.getEmail() == null && aliaccount.getMobile() == null) {
						if (StringUtil.isNotEmpty(aliaccount.getAccesstoken())) {
							new GetAlipayUserInfo(accountAlipayService, apiResponse.getAccesstoken()).start();
						} else {
//							记录访问日志
							FileUtil.addLog(request, "getalipaytoken",channelNo,
									"authcode", authcode,
									"Accesstoken", aliaccount.getAccesstoken());	
						}
					}
				} else {
					apiResponse.setCode("3");
					apiResponse.setMsg("not found record");
					log.debug("GetAlipayTokenServlet : not found record");
				}
				response.getWriter().print(apiResponse.obj2Json());
			} catch (Exception e) {
				e.printStackTrace();
				log.error("GetAlipayTokenServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
//			 记录访问日志
			FileUtil.addLog(request, "getalipaytoken",channelNo,
					"alipayuid", alipayuid,
					"authcode", authcode,
					"refreshtoken", refreshtoken);
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private static final String ALI_OPEN_URL = "https://openapi.alipay.com/gateway.do";
	
	private static final String getAlipayToken (String authcode, String refreshtoken) {
		try {
//			AlipayClient client = new DefaultAlipayClient(ALI_OPEN_URL, PartnerConfigAlipay.APP_ID, PartnerConfigAlipay.RSA_PRIVATE, "json");
//			AlipaySystemOauthTokenRequest req = new AlipaySystemOauthTokenRequest();
//
//			if (StringUtil.isNotEmpty(refreshtoken)) {
//				req.setGrantType("refresh_token");
//				req.setRefreshToken(refreshtoken);
//			} else {
//				req.setGrantType("authorization_code");
//				req.setCode(authcode);
//			}
//			AlipaySystemOauthTokenResponse res = client.execute(req);
//			String jsonStr = res.getBody();
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final HashMap<String, String> map = new HashMap<String, String>();
		map.put("method", "alipay.system.oauth.token");
		map.put("timestamp", sdf.format(new Date()));
//		map.put("timestamp", "2013-08-07 17:11:28");
		map.put("format", "json");
		map.put("app_id", PartnerConfigAlipay.APP_ID);
		map.put("version", "1.0");
		map.put("sign_type", "RSA");
//		map.put("notify_url", value);
//		map.put("return_url", value);
		
		if (StringUtil.isNotEmpty(refreshtoken)) {
			map.put("grant_type", "refresh_token");
			map.put("refresh_token", refreshtoken);
		} else {
			map.put("grant_type", "authorization_code");
			map.put("code", authcode);
		}
		final String digest = StringUtil.buildParams(map, true, false);
		String signature = RSASignatureAlipay.sign(digest);
		map.put("sign", signature);
		final String postStr = StringUtil.buildParams(map, false, true);
		HashMap<String, String> header = new HashMap<String, String>();
		header.put("Accept", "text/xml,text/javascript,text/html");
		header.put("User-Agent", "aop-sdk-java");
		header.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		String jsonStr = WebUtil.readContentFromPost(ALI_OPEN_URL, postStr, header);
			
//		String jsonStr = "{" + 
//"\"alipay_system_oauth_token_response\":{" + 
//"\"alipay_user_id\":\"2088102008772487\"," + 
//"\"access_token\":\"20120823ac6ffaa4d2d84e7384bf983531473993\"," + 
//"\"expires_in\":\"3600\"," + 
//"\"refresh_token\":\"20120823ac6ffdsdf2d84e7384bf983531473993\"," + 
//"\"re_expires_in\":\"3600\"" + 
//"}" + 
//"}";
		return jsonStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	class GetAlipayUserInfo extends Thread {
		private AccountAlipayService accountAlipayService;
		private String accesstoken;
		public GetAlipayUserInfo (AccountAlipayService accountAlipayService, String accesstoken) {
			this.accountAlipayService = accountAlipayService;
			this.accesstoken = accesstoken;
		}
		@Override
		public void run () {
			try {
//				AlipayClient client = new DefaultAlipayClient(ALI_OPEN_URL, PartnerConfigAlipay.APP_ID, PartnerConfigAlipay.RSA_PRIVATE, "json");
//				AlipayUserUserinfoShareRequest req = new AlipayUserUserinfoShareRequest();
//				AlipayUserUserinfoShareResponse res = client.execute(req, accesstoken); 
//				String jsonStr = res.getBody();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("method", "alipay.user.userinfo.share");
			map.put("timestamp", sdf.format(new Date()));
			map.put("format", "json");
			map.put("app_id", PartnerConfigAlipay.APP_ID);
			map.put("version", "1.0");
			map.put("sign_type", "RSA");
			
			map.put("auth_token", accesstoken);

			final String digest = StringUtil.buildParams(map, true, false);
			String signature = RSASignatureAlipay.sign(digest);
			map.put("sign", signature);
			final String postStr = StringUtil.buildParams(map, false, true);
			HashMap<String, String> header = new HashMap<String, String>();
			header.put("Accept", "text/xml,text/javascript,text/html");
			header.put("User-Agent", "aop-sdk-java");
			header.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			String jsonStr = WebUtil.readContentFromPost(ALI_OPEN_URL, postStr, header);
			
			GetAlipayUserInfoAPIResponse apiResponse = new GetAlipayUserInfoAPIResponse();
			apiResponse.json2Obj(jsonStr);
			
			HqlHelper hqlHelper = new HqlHelper(AccountAlipay.class, "aa");
			hqlHelper.addWhereCondition("aa.aliUserId=?", apiResponse.getAliUserId());
			AccountAlipay aliaccount = accountAlipayService.getByCondition(hqlHelper);
			// 登录需要记录到account表的信息
			if (aliaccount != null) {
				aliaccount.setGender(apiResponse.getGender());
				aliaccount.setNickname(apiResponse.getName());
				aliaccount.setCity(apiResponse.getCity());
				aliaccount.setMobile(apiResponse.getMobile());
				if (apiResponse.getEmail() != null && apiResponse.getEmail().indexOf("@") > 0) {
					aliaccount.setEmail(apiResponse.getEmail());
				}
				accountAlipayService.update(aliaccount);
			} else {
				return;
			}
			
			//	同步收货地址
			if (StringUtil.isNotEmpty(aliaccount.getUserId())
//					&& StringUtil.isNotEmpty(apiResponse.getProvince(), apiResponse.getCity(), apiResponse.getArea())
					&& StringUtil.isNotEmpty(apiResponse.getAddress(), apiResponse.getZip())
					&& (StringUtil.isNotEmpty(apiResponse.getName()) || StringUtil.isNotEmpty(apiResponse.getDeliverFullname()))
					&& (StringUtil.isNotEmpty(apiResponse.getMobile()) || StringUtil.isNotEmpty(apiResponse.getDeliverMobile()))) {

				final String userid = aliaccount.getUserId();
				map = new HashMap<String, String>();
				map.put("userid", userid);
				String url = Constants.BASE_URL + "getconsigneeaddress/";
				try {
					String data = WebUtil.readContentFromGet(url, map);
					GetAddressServerResponse serverResponse = new GetAddressServerResponse();
					serverResponse.json2Obj(data);
					
					if (serverResponse.getSize() == 0) {
//						查找省市区
						String address = apiResponse.getAddress();
						CityBean province = null;
						if (StringUtil.isNotEmpty(apiResponse.getProvince())) {
							province = AddressUtil.findBean(apiResponse.getProvince(), null, CityBean.TYPE_PROVINCE);
						} else {
							province = AddressUtil.findBean(address, null, CityBean.TYPE_PROVINCE);
							if (province != null) {
								address = address.substring(province.getName().length());
								if (address.startsWith("省")) address = address.substring(1);
							}
						}
						if (province == null) return;

						CityBean city = null;
						if (StringUtil.isNotEmpty(apiResponse.getCity())) {
							city = AddressUtil.findBean(apiResponse.getCity(), province.getId(), CityBean.TYPE_CITY);
						} else {
							city = AddressUtil.findBean(address, province.getId(), CityBean.TYPE_CITY);
							if (city != null) {
								address = address.substring(city.getName().length());
							}
						}
						if (city == null) return;

						CityBean area = null;
						if (StringUtil.isNotEmpty(apiResponse.getCity())) {
							area = AddressUtil.findBean(apiResponse.getCity(), city.getId(), CityBean.TYPE_AREA);
						} else {
							area = AddressUtil.findBean(address, city.getId(), CityBean.TYPE_AREA);
							if (area != null) {
								address = address.substring(area.getName().length());
							}
						}
						if (area == null) return;

						hqlHelper = new HqlHelper(AccountAlipay.class, "aa");
						hqlHelper.addWhereCondition("aa.aliUserId=?", apiResponse.getAliUserId());
						aliaccount = accountAlipayService.getByCondition(hqlHelper);
						// 登录需要记录到account表的信息
						if (aliaccount != null && aliaccount.getCity() == null) {
							aliaccount.setCity(city.getName());
							accountAlipayService.update(aliaccount);
						}
						
						map = new HashMap<String, String>();
						map.put("userid", userid);
						final String consigneename = StringUtil.isNotEmpty(apiResponse.getDeliverFullname())?apiResponse.getDeliverFullname():apiResponse.getName();
						map.put("consigneename", consigneename);
						map.put("address", address);
						map.put("province", province.getId());
						map.put("city", city.getId());
						map.put("area", area.getId());
						map.put("postcode", apiResponse.getZip());
						final String tel = StringUtil.isNotEmpty(apiResponse.getDeliverMobile())?apiResponse.getDeliverMobile():apiResponse.getMobile();
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
