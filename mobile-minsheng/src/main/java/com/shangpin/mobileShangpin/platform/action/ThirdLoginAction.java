package com.shangpin.mobileShangpin.platform.action;

import java.util.HashMap;
import java.util.Map;

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
import weibo4sina.model.WeiboException;

import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.WebUtil;

/**
 * 第三方登录Action
 * 
 * @Author zhouyu
 * @CreatDate 2012-11-20
 */
@Controller
@ParentPackage("mobileShangpin")
@Scope("prototype")
@Actions({ @Action(value = ("/thirdloginaction"), results = {//
		//@Result(name = "registerUI", location = "/WEB-INF/pages/account/register.jsp"),//
		//@Result(name = "loginUI", type = "redirect", location = "accountaction!loginui"),//
		@Result(name = "index", type = "redirect", location = "shangpinindex!index") }) })
public class ThirdLoginAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 新浪微博登录
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-20
	 * @param
	 * @Return
	 */
	//TODO add by zhouyu 完善新浪微博
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
				String data = WebUtil.readContentFromPost(url, map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "index";
	}
}
