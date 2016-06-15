package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 微信客服接手返回数据
 * 
 * @author sunweiwei
 * @CreatDate: 2014-04-28
 */
public class WeiXinRedirectServlet extends HttpServlet {

	private static final long serialVersionUID = 2690280627186659434L;

	private String gameUrl = "http://114.215.101.47/digitalanswer/weixin/answer/enterance.php?id=20140420151523";

	private String giftUrl = "http://114.215.101.47/digitalanswer/weixin/answer/UserExpiryCode.php?id=20140420151523";

	private String topUrl = "http://114.215.101.47/digitalanswer/weixin/answer/RankingList.php?id=20140420151523";

	private static final String appid = "wx4e93df954af2f52c";

	private static final String secret = "2a55eede9fbd467e25e6a0eb7b17ce60";

	private static final String grantType = "authorization_code";

	private static final String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";

	public static final String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo";

	private static final String lang = "zh_CN";

	private static final String GAME_CODE = "1";

	private static final String GIFT_CODE = "2";

	private static final String TOP_CODE = "3";

	@Override
	public void init() throws ServletException {
		super.init();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		JSONObject accessTokenObj = getAccessToken(request);
		if (accessTokenObj.has("access_token") && accessTokenObj.has("openid")) {
			String accessToken = accessTokenObj.getString("access_token");
			String openId = accessTokenObj.getString("openid");
			JSONObject userInfoObj = getUserInfo(accessToken, openId);
			String nickName = userInfoObj.getString("nickname");
			redirectUrl(response, openId, nickName, type);
		}

	}

	/**
	 * 跳转url
	 * 
	 * @param response
	 * @param openId
	 * @param nickName
	 * @param type
	 */
	private void redirectUrl(HttpServletResponse response, String openId,
			String nickName, String type) {
		if (StringUtil.isNotEmpty(type)) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		if (GAME_CODE.equals(type)) {
			sb.append(gameUrl);
		} else if (GIFT_CODE.equals(type)) {
			sb.append(giftUrl);
		} else if (TOP_CODE.equals(type)) {
			sb.append(topUrl);
		} else {
			sb.append(gameUrl);
		}
		sb.append("&openid=" + openId).append("&nickname=" + nickName);
		try {
			response.sendRedirect(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到用户的token信息
	 * 
	 * @param request
	 * @return
	 */
	private JSONObject getAccessToken(HttpServletRequest request) {
		String code = request.getParameter("code"); // 微信code
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appid", appid);
		map.put("secret", secret);
		map.put("code", code);
		map.put("grant_type", grantType);
		// 获取活动xml格式字符串
		String data = WebUtil.readContentFromGet(tokenUrl, map);
		JSONObject accessTokenObj = JSONObject.fromObject(data);
		return accessTokenObj;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @return
	 */
	private JSONObject getUserInfo(String accessToken, String openId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("access_token", accessToken);
		map.put("openid", openId);
		map.put("lang", lang);
		String data = WebUtil.readContentFromGet(userInfoUrl, map);
		JSONObject userInfoObj = JSONObject.fromObject(data);
		return userInfoObj;
	}

}
