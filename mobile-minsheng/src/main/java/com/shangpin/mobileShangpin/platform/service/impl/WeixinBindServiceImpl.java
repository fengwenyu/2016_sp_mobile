package com.shangpin.mobileShangpin.platform.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.shangpin.mobileShangpin.base.model.Account;
import com.shangpin.mobileShangpin.base.model.AccountWeixinBind;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.action.AccountAction;
import com.shangpin.mobileShangpin.platform.dao.WeixinBindDAO;
import com.shangpin.mobileShangpin.platform.service.WeixinBindService;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;

@Service("weixinBindService")
public class WeixinBindServiceImpl implements WeixinBindService {
	@Autowired
	private WeixinBindDAO weixinBindDAO;
	private final Log log = LogFactory.getLog(AccountAction.class);

	/**
	 * 根据微信id查询绑定信息
	 * 
	 * @param weixinid
	 *            微信id
	 * @return AccountWeixinBind 绑定信息
	 */
	@Override
	public AccountWeixinBind findByWeixinId(String weixinid) throws Exception {
		List<AccountWeixinBind> accountWeixinBindList = null;
		try {
			accountWeixinBindList = weixinBindDAO.findWXBind(weixinid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AccountWeixinBind accountWeixinBind = null;
		if (accountWeixinBindList.size() > 0) {
			accountWeixinBind = accountWeixinBindList.get(0);
		}
		if (accountWeixinBindList.size() > 1) {
			log.warn("weixin bind data error! weixinID：" + weixinid);
		}
		return accountWeixinBind;
	}

	/**
	 * 添加微信绑定
	 * 
	 * @param accountWeixinBind
	 *            绑定信息
	 * @return boolean 添加成功返回true
	 */
	@Override
	public void addAccountWeixinBind(AccountWeixinBind accountWeixinBind) throws Exception {
		weixinBindDAO.saveOrUpdate(accountWeixinBind);
	}

	/**
	 * 如果已绑定则自动登陆
	 * 
	 * @param Account
	 *            账户信息
	 * @return boolean 是否自动登录成功
	 */
	@Override
	public boolean autologin(Account account) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", account.getUserId());
		AccountVO serverResponse = new AccountVO();
		String data = null;
		try {
			data = WebUtil.readContentFromPost(Constants.BASE_URL + "getuserinfo/", map);
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		}
		if (null == data && "".equals(data)) {
			serverResponse.setEmail(account.getLoginName());
			ActionContext.getContext().getValueStack().push(serverResponse);
			return false;
		} else {
			JSONObject jsonObj = JSONObject.fromObject(data);
			if (Constants.SUCCESS.equals(jsonObj.get("code")) && null != jsonObj && !"{}".equals(jsonObj.get("content").toString())) {
				JSONObject obj = (JSONObject) jsonObj.get("content");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				serverResponse.setUserid(account.getUserId());
				serverResponse.setGender(account.getGender());
				serverResponse.setLevel(obj.getString("level"));
				serverResponse.setRegTime(sdf.format((account.getRegTime())));
				serverResponse.setRegOrigin(account.getRegOrigin());
				serverResponse.setName(obj.getString("name"));
				serverResponse.setEmail(obj.getString("email"));
			} else {
				return false;
			}
		}
		ServletActionContext.getRequest().getSession().setAttribute(WebUtil.SESSION_USER_PARAM, serverResponse);
		return true;
	}

	/**
	 * 查询微信id是否绑定
	 * 
	 * @param weixinid
	 *            微信id
	 * @return true为已经绑定
	 */
	@Override
	public boolean isBind(String weixinid) {
		long count = 0;
		try {
			count = weixinBindDAO.findWXBindBywxid(weixinid);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (count > 0) {
			return true;
		}
		return false;
	}
}
