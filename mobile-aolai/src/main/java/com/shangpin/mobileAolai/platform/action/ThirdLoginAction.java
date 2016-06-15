package com.shangpin.mobileAolai.platform.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import weibo4sina.Oauth;
import weibo4sina.Users;
import weibo4sina.http.AccessToken;
import weibo4sina.model.User;

import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.CookieUtil;
import com.shangpin.mobileAolai.common.util.SysContent;
import com.shangpin.mobileAolai.common.util.WebUtil;

/**
 * 第三方登录Action
 * 
 * @Author zhouyu
 * @CreatDate 2012-11-20
 */
@Controller
@ParentPackage("mobileAolai")
@Scope("prototype")
@Actions({ @Action(value = ("/thirdloginaction"), results = {//
		//@Result(name = "registerUI", location = "/WEB-INF/pages/account/register.jsp"),//
		//@Result(name = "loginUI", type = "redirect", location = "accountaction!loginui"),//
		@Result(name = "index", type = "redirect", location = "aolaiindex!index") }) })
public class ThirdLoginAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ch;
	@SuppressWarnings("unused")
	private String platform;

	/**
	 * 新浪微博登录
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-20
	 * @param
	 * @Return
	 */
	public String sina() {
		Oauth oauth = new Oauth();
		String code = ServletActionContext.getRequest().getParameter("code");
		if (code == null || "".equals(code)) {
			return "loginUI";
		} else {
			try {
				AccessToken accessToken = oauth.getAccessTokenByCode(code);
				Users um = new Users();
				um.client.setToken(accessToken.getAccessToken());
				User user = um.showUserById(accessToken.getUid());
				String gender = user.getGender();
				if ("m".equals(gender)) {
					gender = "1";
				} else {
					gender = "0";
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("mode", "weibo");// 登录方式：normal正常；weibo新浪微博；qq腾讯qq；zhifubao支付宝；
				map.put("invitecode", "26501690");// 邀请码
				map.put("uid", accessToken.getUid());
				map.put("sex", gender);
				map.put("nikckname", user.getScreenName());
				map.put("truename", user.getName());
				String url = Constants.BASE_URL + "thirdlogin/";
				@SuppressWarnings("unused")
				String data = WebUtil.readContentFromPost(url, map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "index";
	}

	public String getCh() {
		ch = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(ch)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
			}else{
				ch=StringUtils.isEmpty(ch)?Constants.AOLAI_WAP_DEFAULT_CHANNELNO:ch;
			}
			
		}
		return ch;
	}

	public void setCh(String ch) {
		ch = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(ch)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
			}else{
				ch=StringUtils.isEmpty(ch)?Constants.AOLAI_WAP_DEFAULT_CHANNELNO:ch;
			}
			
		}
		this.ch = ch;
	}

	public String getPlatform() {
		Object platform = SysContent.getSession().getAttribute("platform");
		if(platform != null) {
			return (String) platform;
		}
		return "";
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
}
