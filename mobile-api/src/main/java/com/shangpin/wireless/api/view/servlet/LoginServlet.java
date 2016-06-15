package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shangpin.biz.bo.base.ResultBaseNew;
import com.shangpin.utils.JsonUtil;
import com.shangpin.wireless.api.api2client.domain.LoginAndRegisterAPIResponse;
import com.shangpin.wireless.api.api2server.domain.LoginAndRegisterServerResponse;
import com.shangpin.wireless.api.domain.Account;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.PushconfigAolai;
import com.shangpin.wireless.api.service.AccountService;
import com.shangpin.wireless.api.service.PushconfigAolaiService;
import com.shangpin.wireless.api.util.DBType;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.HqlHelper;
import com.shangpin.wireless.api.util.LoginLimitUtil;
import com.shangpin.wireless.api.util.PropertiesUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 登录接口
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-08-27
 */
public class LoginServlet extends HttpServlet {
	//  奥莱，尚品需要区分开
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(LoginServlet.class);
	private PushconfigAolaiService pushconfigAolaiService;
	private AccountService accountService;

	private static final int COUPON_KEEP_TIME	= 30 * 60 * 1000;	//	30分钟
	private volatile static HashMap<String, JSONObject> CouponCache = new HashMap<String, JSONObject>();
	private volatile static long CouponTime;
	//TD
	public static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	@Override
	public void init() throws ServletException {
		ServletContext sc = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		pushconfigAolaiService = (PushconfigAolaiService) ctx.getBean(PushconfigAolaiService.SERVICE_NAME);
		accountService = (AccountService) ctx.getBean(AccountService.SERVICE_NAME);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String productNo = request.getHeader("p");// 产品号
		String channelNo = request.getHeader("ch");// 渠道号
		String imei = request.getHeader("imei");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Date dateAccountCreateTime = null;
		if (StringUtil.isNotEmpty(username, password, productNo, channelNo, imei)) {

			final SimpleDateFormat sdfconfig = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			long now = System.currentTimeMillis();
//			促销信息
			if (now > CouponTime + COUPON_KEEP_TIME) {
				Properties props = PropertiesUtil.getInstance("/coupon/config.properties");
				CouponCache.clear();
				for (Enumeration e = props.keys(); e.hasMoreElements();) {
					String propkey = (String) e.nextElement();
					final String propvalue = new String(props.getProperty(propkey).getBytes("ISO-8859-1"), "UTF-8");
					JSONObject json = JSONObject.fromObject(propvalue);
					CouponCache.put(propkey, json);
				}
				CouponTime = now;
			}
			// 添加imei登录限制
			boolean addImeiLoginLimit = LoginLimitUtil.addImeiLoginCache(imei, username);
			if (!addImeiLoginLimit) {
				try {
					WebUtil.sendLimitException(response);
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 添加ip登录限制
//			boolean addIpLoginLimit = LoginLimitUtil.addIpLoginCache(request.getRemoteAddr(), username, request);
//			if (!addIpLoginLimit) {
//				try {
//					WebUtil.sendLimitException(response);
//					return;
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
			String url = Constants.BASE_URL_SP_AL + "login/";
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", username);
			map.put("password", password);
			LoginAndRegisterAPIResponse apiResponse = new LoginAndRegisterAPIResponse();
			LoginAndRegisterServerResponse serverResponse = new LoginAndRegisterServerResponse();
			try {
//				System.out.println("username:" + username);
				String data = WebUtil.readContentFromPost(url, map);
				logger.info("后台url:" + url); 
				// System.out.println("LoginServlet end" + sdf1.format(new Date()));
				serverResponse.json2ObjNew(data,"");// json2obj
				BeanUtils.copyProperties(apiResponse, serverResponse);
				logger.info("输出data:" + JsonUtil.toJson(apiResponse));
				if ("0".equals(apiResponse.getCode())) {
					JSONObject couponcache = CouponCache.get("p" + productNo + "login");
					if (couponcache != null) {
						try {
							Date starttime = sdfconfig.parse(couponcache.getString("starttime"));
							Date endtime = sdfconfig.parse(couponcache.getString("endtime"));
							if (date.after(starttime) && date.before(endtime)) {
								new GiveOutCoupon(serverResponse.getUserid(), couponcache.getString("activatecode")).start();
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					
					apiResponse.setSessionid(SessionUtil.addUser(apiResponse.getUserid(), imei));
					HqlHelper hqlHelper = null;
					if ("1".equals(productNo)) {
						hqlHelper = new HqlHelper(PushconfigAolai.class, "p");
						hqlHelper.addWhereCondition("p.userId=?", apiResponse.getUserid());
						PushconfigAolai model = pushconfigAolaiService.getByCondition(hqlHelper, DBType.dataSourceAPI.toString());
						if (model != null) {
							apiResponse.setIsopen(String.valueOf(model.getIsOpen()));
							apiResponse.setMsgtype(String.valueOf(model.getMsgType()));
						} else {
							apiResponse.setIsopen("1");
							apiResponse.setMsgtype("2");
						}
					}
					hqlHelper = new HqlHelper(Account.class, "a");
					// imei、os、osv、p、ch、ver、apn header信息
					hqlHelper.addWhereCondition("a.loginName=?", username);
					// hqlHelper.addWhereCondition("a.product=?", productNo + "");
					synchronized (serverResponse.getUserid().intern()) {
						Account account = accountService.getByCondition(hqlHelper, DBType.dataSourceAPI.toString());
						// 登录需要记录到account表的信息
						if (account != null) {
							account.setLoginTime(date);
							accountService.update(account, DBType.dataSourceAPI.toString());
							dateAccountCreateTime = account.getCreateTime();
						} else {
							account = new Account();
							account.setChannel(Long.parseLong(channelNo));
							account.setProduct(Long.parseLong(productNo));
							account.setGender(serverResponse.getGender());
							account.setLoginName(username);
							account.setCreateTime(date);
							account.setLoginTime(date);
							account.setRegTime(sdf.parse(serverResponse.getRegTime()));
							account.setRegOrigin(serverResponse.getRegOrigin());
							account.setUserId(serverResponse.getUserid());
							account.setPlatform(request.getHeader("os"));
							account.setPhoneType(request.getHeader("mt"));
							account.setPhoneModel(request.getHeader("model"));
							accountService.save(account, DBType.dataSourceAPI.toString());
							dateAccountCreateTime = date;
						}
					}
					response.getWriter().print(JsonUtil.toJson(ResultBaseNew.success(apiResponse)));
				}else{
					response.getWriter().print(apiResponse.obj2Json());// obj2json
				}
				logger.info("返回给app:" + JsonUtil.toJson(ResultBaseNew.success(apiResponse)));//apiResponse.obj2Json()
			} catch (Exception e) {
				e.printStackTrace();
				log.error("LoginServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "login",//
					"username", username,//
					"code", serverResponse.getCode(),//
					"userId", serverResponse.getUserid(),//
					"regOrigin", serverResponse.getRegOrigin(),//
					"gender", serverResponse.getGender(),//
					"regTime", serverResponse.getRegTime(),//
					"createTime", dateAccountCreateTime == null ? null : sdf.format(dateAccountCreateTime),//
					"loginTime", sdf.format(date));
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	class GiveOutCoupon extends Thread {
		private String userid;
		private String activateCode;
		public GiveOutCoupon (String userid, String activateCode) {
			this.userid = userid;
			this.activateCode = activateCode;
		}
		@Override
		public void run () {
			try {
				String url = Constants.BASE_URL_AL_AL + "codemactchedphone/";
				// 组装参数
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("type", "coupon:" + activateCode);

				String data = WebUtil.readContentFromGet(url, map);
				log.debug("login provied Coupon : userid =" + userid);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("LoginServlet run ():"+e);
			}
		}
	}
}
