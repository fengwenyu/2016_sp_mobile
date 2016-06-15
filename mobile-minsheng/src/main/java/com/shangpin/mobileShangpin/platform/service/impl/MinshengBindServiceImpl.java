package com.shangpin.mobileShangpin.platform.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import com.opensymphony.xwork2.ActionContext;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.SysContent;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.action.AccountAction;
import com.shangpin.mobileShangpin.platform.service.MinshengBindService;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;

@Service("minshengBindService")
public class MinshengBindServiceImpl implements MinshengBindService {

	private final Log log = LogFactory.getLog(AccountAction.class);

	/**
	 * 如果已绑定则自动登陆
	 * 
	 * @param Account
	 *            账户信息
	 * @return boolean 是否自动登录成功
	 */
	@Override
	public boolean autologin(AccountVO accountVo) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", accountVo.getUserid());
		AccountVO serverResponse = new AccountVO();
		String data = null;
		try {
			data = WebUtil.readContentFromPost(Constants.BASE_URL + "getuserinfo/", map);
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		}
		if (null == data && "".equals(data)) {
			serverResponse.setEmail(accountVo.getEmail());
			ActionContext.getContext().getValueStack().push(serverResponse);
			return false;
		} else {
			JSONObject jsonObj = JSONObject.fromObject(data);
			if (Constants.SUCCESS.equals(jsonObj.get("code")) && null != jsonObj && !"{}".equals(jsonObj.get("content").toString())) {
				JSONObject obj = (JSONObject) jsonObj.get("content");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				serverResponse.setUserid(accountVo.getUserid());
				serverResponse.setGender(accountVo.getGender());
				serverResponse.setLevel(obj.getString("level"));
				serverResponse.setRegTime(accountVo.getRegTime());
				serverResponse.setRegOrigin(accountVo.getRegOrigin());
				serverResponse.setName(obj.getString("name"));
				serverResponse.setEmail(obj.getString("email"));
				serverResponse.setMobileNumber((obj.getString("mobile")));
			} else {
				return false;
			}
		}
		ServletActionContext.getRequest().getSession().setAttribute(WebUtil.SESSION_USER_PARAM, serverResponse);
		System.out.println("sessionuser"+WebUtil.getSessionUser());
		System.out.println("minshengbindsession"+ActionContext.getContext().getSession());
		return true;
	}

}
