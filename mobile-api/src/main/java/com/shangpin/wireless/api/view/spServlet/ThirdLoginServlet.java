package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBaseNew;
import com.shangpin.biz.service.ASPBizUserService;
import com.shangpin.biz.utils.SearchParamUtil;
import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.JsonUtil;
import com.shangpin.wireless.api.api2client.domain.LoginAndRegisterAPIResponse;
import com.shangpin.wireless.api.api2server.domain.LoginAndRegisterServerResponse;
import com.shangpin.wireless.api.domain.Account;
import com.shangpin.wireless.api.domain.AccountAlipay;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.service.AccountAlipayService;
import com.shangpin.wireless.api.service.AccountService;
import com.shangpin.wireless.api.util.DBType;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.HqlHelper;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 第三方登录接口，返回本站用户信息
 * 
 * @Author:yumeng
 * @CreatDate: 2012-12-25
 */
public class ThirdLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ThirdLoginServlet.class);
//	private PushconfigShangpinService pushconfigShangpinService;
	private AccountService accountService;
	private AccountAlipayService accountAlipayService;
	private ASPBizUserService userService;//2.9.7增加
	public static final Logger logger = LoggerFactory.getLogger(ThirdLoginServlet.class);
	@Override
	public void init() throws ServletException {
		ServletContext sc = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
//		pushconfigShangpinService = (PushconfigShangpinService) ctx.getBean(PushconfigShangpinService.SERVICE_NAME);
		accountService = (AccountService) ctx.getBean(AccountService.SERVICE_NAME);
		accountAlipayService = (AccountAlipayService) ctx.getBean(AccountAlipayService.SERVICE_NAME);
		userService = (ASPBizUserService) ctx.getBean(ASPBizUserService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String ver = request.getHeader("ver");
		
		String invitecode = "UYTUTSYG";// 邀请码
		final String mode = request.getParameter("mode");// 登录方式：weibo新浪微博；qq腾讯qq；zhifubao支付宝；weixin微信
		final String uid = request.getParameter("uid");
		final String sex = request.getParameter("sex");
		String truename = request.getParameter("truename");
		String nickname = request.getParameter("nickname");
		final String imei = request.getHeader("imei");
		final String productNo = request.getHeader("p");// 产品号
		final String channelNo = request.getHeader("ch");// 渠道号
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//logger.info("request info=========={}", request.getHeader("User-Agent"));
		//logger.info("third login===================" + mode + ",os:" + request.getHeader("os") + ",p:" + productNo);
		Date date = new Date();
		Date dateAccountCreateTime = null;
		if (StringUtil.isNotEmpty(uid, productNo, channelNo, imei)) {
			// 通过第三方平台、本网站平台、手机系统，获取验证码
			if (StringUtils.isNotEmpty(mode)) {
				if ("weibo".equals(mode)) {
					if ("1".equals(productNo)) {
						invitecode = "33836298";
					} else if ("2".equals(productNo)) {
						invitecode = "SCHZSB8T";
					} else if ("101".equals(productNo)) {
						invitecode = "96259508";
					} else if ("102".equals(productNo)) {
						invitecode = "KHADMDB1";
					} else {
						try {
							response.getWriter().print("{\"code\":\"-12\",\"msg\":\"邀请码无效\",\"content\":\"{}\"}");
							return;
						} catch (Exception e) {
							e.printStackTrace();
							log.error("ThirdLoginServlet：" + e);
							try {
								WebUtil.sendApiException(response);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
				} else if ("qq".equals(mode)) {
					if ("1".equals(productNo)) {
						invitecode = "59829785";
					} else if ("2".equals(productNo)) {
						invitecode = "7Q2VVSHG";
					} else if ("101".equals(productNo)) {
						invitecode = "13206936";
					} else if ("102".equals(productNo)) {
						invitecode = "UNVQKM8Y";
					} else {
						try {
							response.getWriter().print("{\"code\":\"-12\",\"msg\":\"邀请码无效\",\"content\":\"{}\"}");
							return;
						} catch (Exception e) {
							e.printStackTrace();
							log.error("ThirdLoginServlet：" + e);
							try {
								WebUtil.sendApiException(response);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
				} else if ("weixin".equals(mode)) {
					if ("1".equals(productNo)) {
						invitecode = "TZDVZQWE";
					} else if ("2".equals(productNo)) {
						invitecode = "TZDVZQWE";
					} else if ("101".equals(productNo)) {
						invitecode = "TZDVZQWE";
					} else if ("102".equals(productNo)) {
						invitecode = "TZDVZQWE";
					} else {
						try {
							response.getWriter().print("{\"code\":\"-12\",\"msg\":\"邀请码无效\",\"content\":\"{}\"}");
							return;
						} catch (Exception e) {
							e.printStackTrace();
							log.error("ThirdLoginServlet：" + e);
							try {
								WebUtil.sendApiException(response);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
				} else if ("zhifubao".equals(mode)) {
					if ("1".equals(productNo)) {
						invitecode = "19652623";
					} else if ("2".equals(productNo)) {
						invitecode = "2MDSABE9";
					} else if ("101".equals(productNo)) {
						invitecode = "30709538";
					} else if ("102".equals(productNo)) {
						invitecode = "R8HRYL3G";
					}
				} else {
					try {
						response.getWriter().print("{\"code\":\"-12\",\"msg\":\"不支持此第三方登录\",\"content\":\"{}\"}");
						return;
					} catch (Exception e) {
						e.printStackTrace();
						log.error("ThirdLoginServlet：" + e);
						try {
							WebUtil.sendApiException(response);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			} else {
				try {
					WebUtil.sendErrorInvalidParams(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			invitecode = "UYTUTSYG";//测试邀请码
			Map<String, String> map = new HashMap<String, String>();
			map.put("invitecode", invitecode);
			map.put("mode", mode);
			map.put("uid", uid);
			map.put("sex", null == sex ? "" : sex);
			nickname=URLDecoder.decode(nickname,"UTF-8");
			truename=URLDecoder.decode(truename,"UTF-8");
			map.put("truename", null == truename ? "" : truename);
			map.put("nickname", null == nickname ? "" : nickname);
			String url = Constants.BASE_URL_SP + "thirdlogin/";
			LoginAndRegisterAPIResponse apiResponse = new LoginAndRegisterAPIResponse();
			LoginAndRegisterServerResponse serverResponse = new LoginAndRegisterServerResponse();
			try {
				String data = WebUtil.readContentFromGet(url, map);//主站数据
				//logger.info("主站data:"+data);
				//logger.info("map:"+JsonUtil.toJson(map));
				if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.7", ver) == 1) {
					//增加添加头像逻辑开始
					final String iconUrl = request.getParameter("iconUrl");
					logger.info("头像："+iconUrl);
					ResultBaseNew resultBaseNew = ResultBaseNew.formatToPojo(data,User.class);
					if (Constants.SUCCESS.equals(resultBaseNew.getCode())){
						User user = (User) resultBaseNew.getContent();
						String userId = user.getUserid();
						String icon = user.getIcon();
						//数据库头像为空,并且传递过来的头像不为空
						if(org.apache.commons.lang3.StringUtils.isBlank(icon)&&org.apache.commons.lang3.StringUtils.isNotBlank(iconUrl)){
							//调用上传头像服务
							String result = userService.modifyUserInfoIcon(userId, iconUrl);
							//logger.info("iconResult头像服务："+result);
							ResultBaseNew iconResult = ResultBaseNew.format(result);
	                        if(iconResult== null || !"0".equals(iconResult.getCode())){
	                        	try {
	            					WebUtil.sendApiException(response);
	            				} catch (Exception e1) {
	            					e1.printStackTrace();
	            				}
	                        	return;
	                        }
							user.setIcon(iconUrl);
							data = JsonUtil.toJson(ResultBaseNew.success(user));
							logger.info("data:"+data);
						}
					}
					//增加添加头像逻辑结束
				}
				logger.info("url:"+url);
				// 设置session
				serverResponse.json2ObjNew(data, nickname);// json2obj	
				BeanUtils.copyProperties(apiResponse, serverResponse);
				if ("0".equals(apiResponse.getCode())) {
					apiResponse.setSessionid(SessionUtil.addUser(apiResponse.getUserid(), imei));
//					HqlHelper hqlHelper = new HqlHelper(PushconfigShangpin.class, "p");
//					hqlHelper.addWhereCondition("p.userId=?", apiResponse.getUserid());
//					// 获取尚品IPhone设备Token数据
//					PushconfigShangpin model = pushconfigShangpinService.getByCondition(hqlHelper, DBType.dataSourceAPI.toString());
//					if (model != null) {
//						apiResponse.setIsopen(String.valueOf(model.getIsOpen()));
//						apiResponse.setMsgtype(String.valueOf(model.getMsgType()));
//					} else {
//						apiResponse.setIsopen("1");
//						apiResponse.setMsgtype("2");
//					}
					String username = apiResponse.getEmail();
					HqlHelper hqlHelper = new HqlHelper(Account.class, "a");
					hqlHelper.addWhereCondition("a.loginName=?", username);
//					hqlHelper.addWhereCondition("a.product=?", productNo + "");
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

						if ("zhifubao".equals(mode)/* && StringUtil.isNotEmpty(authcode)*/) {
							hqlHelper = new HqlHelper(AccountAlipay.class, "aa");
							hqlHelper.addWhereCondition("aa.aliUserId=?", uid);
							AccountAlipay aliaccount = accountAlipayService.getByCondition(hqlHelper);
							// 登录需要记录到account表的信息
							if (aliaccount != null) {
//								aliaccount.setAuthcode(authcode);
//								accountAlipayService.update(aliaccount);
							} else {
								aliaccount = new AccountAlipay();
//								aliaccount.setAuthcode(authcode);
								aliaccount.setAliUserId(uid);
								aliaccount.setUserId(serverResponse.getUserid());
								aliaccount.setProduct(productNo);
								aliaccount.setRegOrigin(serverResponse.getRegOrigin());
								aliaccount.setRegTime(sdf.parse(serverResponse.getRegTime()));
								aliaccount.setUpdateTime(date);
								aliaccount.setCreateTime(date);
								accountAlipayService.save(aliaccount);
							}
							
							apiResponse.setName("");
							apiResponse.setEmail("");
						}
					}
					logger.info("第三方登录返回app:"+JsonUtil.toJson(ResultBaseNew.success(apiResponse)));
					response.getWriter().print(JsonUtil.toJson(ResultBaseNew.success(apiResponse)));//apiResponse.obj2Json()
				}else{
				    JSONObject apiResult=new JSONObject();
				    apiResult.put("code", "1");
				    apiResult.put("msg", "登录失败了，再试一次");
				    apiResult.put("content", new JSONObject());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				log.error("ThirdLoginServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "thirdlogin", channelNo,
					"invitecode", invitecode,
					"mode", mode,
					"uid", uid,
					"sex", sex,
					"truename", truename!=null?URLEncoder.encode(truename, "UTF-8"):"",
					"nickname", nickname!=null?URLEncoder.encode(nickname, "UTF-8"):"",
					"createTime", dateAccountCreateTime == null ? null : sdf.format(dateAccountCreateTime),
					"username", serverResponse.getEmail(),//
					"code", serverResponse.getCode(),//
					"userId", serverResponse.getUserid(),//
					"regOrigin", serverResponse.getRegOrigin(),//
					"gender", serverResponse.getGender(),//
					"regTime", serverResponse.getRegTime(),//
					"loginTime", sdf.format(date)
					);
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
}

