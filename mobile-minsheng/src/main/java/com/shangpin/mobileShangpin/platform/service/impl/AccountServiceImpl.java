package com.shangpin.mobileShangpin.platform.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.shangpin.mobileShangpin.base.model.Account;
import com.shangpin.mobileShangpin.businessbean.IPBlacklist;
import com.shangpin.mobileShangpin.businessbean.TopicConfig;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.CookieUtil;
import com.shangpin.mobileShangpin.common.util.DataContainerPool;
import com.shangpin.mobileShangpin.common.util.IPBlacklistCacheUtil;
import com.shangpin.mobileShangpin.common.util.MD5Util;
import com.shangpin.mobileShangpin.common.util.SysContent;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.dao.AccountDAO;
import com.shangpin.mobileShangpin.platform.service.AccountService;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;
import com.shangpin.mobileShangpin.platform.vo.ConsigneeAddressVO;

/**
 * 用户帐号管理业务逻辑接口实现类，用于用户注册、登录、设置收货人地址等操作。
 * 
 * @author zhouyu
 * @CreatDate 2012-10-29
 */
@Service("accountService")
// @Transactional
@SuppressWarnings({ "unchecked", "static-access" })
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDAO accountDAO;

	@Override
	public void addAccount(Account acc) throws Exception {
		accountDAO.saveOrUpdate(acc);
	}

	@Override
	public void modifyAccount(Account acc) throws Exception {
		accountDAO.update(acc);
	}

	// @Override
	// public Page getAllAccounts() throws Exception {
	// int a = 0;
	// if (a == 1) {
	// throw new RuntimeException();
	// }
	// return accountDAO.queryForpage("from Account", null, null);
	// }
	/**
	 * 跳转至登录页面
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-29
	 * @param loginName
	 *            登录名
	 * @param password
	 *            密码
	 * @Return
	 */
	@Override
	public boolean login(String loginName, String password) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", loginName);
		map.put("password", password);
		AccountVO serverResponse = new AccountVO();
		try {
			String data = WebUtil.readContentFromPost(Constants.BASE_URL
					+ "login/", map);
			serverResponse.json2Obj(data);// json2obj
			if (!"0".equals(serverResponse.getCode())) {
				serverResponse.setEmail(loginName);
				ActionContext.getContext().getValueStack().push(serverResponse);
				return false;
			} else {
				sendLoginRegistCount(serverResponse.getUserid(), "1", "1",
						SysContent.getRequest());
				// 查找account表
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletActionContext.getRequest().getSession()
				.setAttribute(WebUtil.SESSION_USER_PARAM, serverResponse);
		return true;
	}

	/**
	 * 注册校验
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-29
	 * @param loginName
	 *            登录名
	 * @param password
	 *            密码
	 * @param gender
	 *            性别
	 * @param channelNo
	 *            渠道号
	 * @Return
	 */
	public boolean register(String loginName, String password, String gender,
			String channelNo) throws Exception {
		AccountVO serverResponse = new AccountVO();
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "0");
		map.put("mailaddress", loginName);
		map.put("password", password);
		map.put("gender", gender);
		// 通过渠道号，从缓存中获取活动的邀请码
		Object obj = DataContainerPool.topicConfigContainer.get(channelNo);
		String invitecode = null;
		if (null != obj) {
			TopicConfig tc = (TopicConfig) obj;
			invitecode = tc.getInvitecode();
		}
		// TODO:TEST
		invitecode = "UYTUTSYG";
		map.put("invitecode", StringUtils.isNotEmpty(invitecode) ? invitecode
				: "UYTUTSYG");// 邀请码UYTUTSYG
		String data = WebUtil.readContentFromPost(Constants.BASE_URL
				+ "register/", map);
		serverResponse.json2Obj(data);// json2obj
		if (!"0".equals(serverResponse.getCode())) {
			serverResponse.setEmail(loginName);
			ActionContext.getContext().getValueStack().push(serverResponse);
			return false;
		} else {
			sendLoginRegistCount(serverResponse.getUserid(), "2", "1",
					SysContent.getRequest());
		}
		ServletActionContext.getRequest().getSession()
				.setAttribute(WebUtil.SESSION_USER_PARAM, serverResponse);
		return true;
	}

	@Override
	public ConsigneeAddressVO getConsigneeAddress(String userid, String addrid) {
		ConsigneeAddressVO consigneeAddressVO = null;
		String url = Constants.BASE_URL + "getConsigneeAddressById/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("addrid", addrid);
		String json = null;
		try {
			// 获取收货人地址json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj
					&& Constants.SUCCESS.equals(jsonObj.get("code"))) {
				consigneeAddressVO = ConsigneeAddressVO
						.json2Bean((JSONObject) jsonObj.get("content"));
			}
		}
		return consigneeAddressVO;
	}

	@Override
	public List<ConsigneeAddressVO> getConsigneeAddressList(String userid) {
		List<ConsigneeAddressVO> list = null;
		String url = Constants.BASE_URL + "getconsigneeaddress/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		String json = null;
		try {
			// 获取收货人地址json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj
					&& Constants.SUCCESS.equals(jsonObj.get("code"))) {
				JSONObject contentObj = (JSONObject) jsonObj.get("content");
				if (!"[ ]".equals(contentObj.get("list").toString())) {
					JSONArray array = (JSONArray) contentObj.get("list");
					// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
					list = array.toList(array, new ConsigneeAddressVO(),
							new JsonConfig());
				}
			}
		}
		return list;
	}

	@Override
	public JSONObject addOrUpdateConsigneeAddress(String userid,
			ConsigneeAddressVO vo) {
		boolean taget = null != vo.getId() && !"".equals(vo.getId().trim());
		String url = Constants.BASE_URL
				+ (taget ? "EditConsigneeAddress/" : "AddConsigneeAddress/");
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		if (taget) {
			map.put("addrid", vo.getId());
		}
		map.put("userid", userid);
		map.put("consigneename", vo.getName());
		map.put("province", vo.getProvince());
		map.put("city", vo.getCity());
		map.put("area", vo.getArea());
		map.put("address", vo.getAddr());
		map.put("postcode", vo.getPostcode());
		map.put("tel", vo.getTel());
		map.put("setd",
				null != vo.getIsd() && "on".equals(vo.getIsd()) ? "true"
						: "false");
		String json = null;
		try {
			// 获取操作结果，json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj) {
				return jsonObj;
			}
		}
		return null;
	}

	@Override
	public String removeConsigneeAddress(String userid, String addrid) {
		String url = Constants.BASE_URL + "DelConsigneeAddress/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("addrid", addrid);
		String json = null;
		try {
			// 获取操作结果，json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj) {
				return jsonObj.get("code").toString();
			}
		}
		return null;
	}

	@Override
	public JSONObject addInvoiceAddress(String userid, ConsigneeAddressVO vo) {
		String url = Constants.BASE_URL + "addinvoiceaddress/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("consigneename", vo.getName());
		map.put("province", vo.getProvince());
		map.put("city", vo.getCity());
		map.put("area", vo.getArea());
		map.put("address", vo.getAddr());
		map.put("postcode", vo.getPostcode());
		map.put("tel", vo.getTel());
		String json = null;
		try {
			// 获取操作结果，json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj) {
				return jsonObj;
			}
		}
		return null;
	}

	@Override
	public JSONObject sendPhoneCode(String userid, String phonenum) {
		JSONObject jsonObj = null;
		String url = Constants.BASE_URL + "sendverifycode/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("phonenum", phonenum);
		String json = null;
		try {
			// 获取操作结果，json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			jsonObj = JSONObject.fromObject(json);
		} else {
			jsonObj = new JSONObject();
			jsonObj.put("code", "-1");
			jsonObj.put("msg", "数据错误，请重试");
		}
		return jsonObj;
	}

	@Override
	public JSONObject sendPhoneCode(String userid, String phonenum,String msgtemplate) {
		JSONObject jsonObj = null;
		String url = Constants.BASE_URL + "sendverifycode/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("phonenum", phonenum);
		map.put("msgtemplate", msgtemplate);
		String json = null;
		try {
			// 获取操作结果，json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			jsonObj = JSONObject.fromObject(json);
		} else {
			jsonObj = new JSONObject();
			jsonObj.put("code", "-1");
			jsonObj.put("msg", "数据错误，请重试");
		}
		return jsonObj;
	}

	@Override
	public JSONObject verifyPhoneCode(String userid, String phonenum,
			String verifycode) {
		JSONObject jsonObj = null;
		String url = Constants.BASE_URL + "verifyphonenum/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("phonenum", phonenum);
		map.put("verifycode", verifycode);
		String json = null;
		try {
			// 获取操作结果，json格式字符串
			json = WebUtil.readContentFromGet(url, map);
			if (null != json && !"".equals(json)) {
				jsonObj = JSONObject.fromObject(json);
				if (null != jsonObj
						&& Constants.SUCCESS.equals(jsonObj.get("code"))) {
					url = Constants.BASE_URL + "codemactchedphone/";
					map.clear();
					map.put("userid", userid);
					map.put("type", "bind:" + phonenum + "coupon:" + 1234);// TODO:modify
																			// coupon
					json = WebUtil.readContentFromGet(url, map);
					if (null != json && !"".equals(json)) {
						return JSONObject.fromObject(json);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj = null;
		}
		jsonObj = new JSONObject();
		jsonObj.put("code", "-1");
		jsonObj.put("msg", "数据错误，请重试");
		return jsonObj;
	}

	@Override
	public JSONObject getUserBuyInfo(String userid, String shoptype) {
		JSONObject jsonObj = null;
		String url = Constants.BASE_URL + "userbuyinfo/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("shoptype", shoptype);
		String json = null;
		try {
			// 获取操作结果，json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			jsonObj = JSONObject.fromObject(json);
		} else {
			jsonObj = new JSONObject();
			jsonObj.put("code", "-1");
			jsonObj.put("msg", "数据错误，请重试");
		}
		return jsonObj;
	}

	/**
	 * 根据登录名和产品号获取用户
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-12-04
	 * @param loginName
	 *            登录名
	 * @Return
	 */
	@Override
	public Account getAccountByParams(String loginName) throws Exception {
		Account account = null;
		List<Account> list = accountDAO.getAccountByLoginName(loginName);
		if (null != list && !list.isEmpty())
			account = list.get(0);
		return account;
	}

	/**
	 * 用户登录时，如果在5秒，访问超过10次，将此用户禁止10秒钟不能登录
	 * 
	 * @param userName
	 *            用户名
	 * @param ip
	 *            用户IP
	 * @return 是否禁用，true为禁用
	 */
	@Override
	public boolean ipBlacklist(String userName, String ip) throws Exception {
		long limit = 5 * 1000l;// 5秒内
		int maxcount = 10;// 访问最多10次
		long forbid = 10 * 60 * 1000l;// 禁用10分钟
		IPBlacklist blacklist = (IPBlacklist) IPBlacklistCacheUtil
				.getValue(userName);
		// 多次访问流程，小于5秒，访问超过10次，禁用10分钟
		if (blacklist != null) {
			Long tmp = new Date().getTime() - blacklist.getStatisticsTime();
			// 访问最多10次，禁用10分钟
			if (blacklist.getStatisticsCount() >= maxcount) {
				if (tmp <= forbid) {
					return true;
				} else {
					blacklist.setStatisticsCount(1);
					blacklist.setStatisticsTime(new Date().getTime());
					IPBlacklistCacheUtil.put(userName, blacklist);
					return false;
				}
			}
			// 小于5秒，访问次数+1
			if (tmp < limit) {
				int count = blacklist.getStatisticsCount();
				blacklist.setStatisticsCount(++count);
				IPBlacklistCacheUtil.put(userName, blacklist);
			} else {
				blacklist.setStatisticsCount(1);
				blacklist.setStatisticsTime(new Date().getTime());
				IPBlacklistCacheUtil.put(userName, blacklist);
			}
		} else {// 初次访问流程
			blacklist = new IPBlacklist();
			blacklist.setUserName(userName);
			blacklist.setIp(ip);
			blacklist.setStatisticsCount(1);
			blacklist.setStatisticsTime(new Date().getTime());
			IPBlacklistCacheUtil.put(userName, blacklist);
		}
		return false;
	}

	/**
	 * 注册、登录、第三方登录统计
	 * 
	 * @param accountVO
	 *            订单对象
	 * @param operationType
	 *            1：登录；2：注册；3：第三方登录
	 * @param userForm
	 *            用户来源，1:尚品；2:奥莱
	 * @param request
	 *            HttpServletRequest
	 */
	private void sendLoginRegistCount(final String uid,
			final String operationType, final String userForm,
			final HttpServletRequest request) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				Map<String, String> map = CookieUtil.getCookieValue(request);
				String url = Constants.SP_COUNT_URL + "mobilereg.aspx";
				String time = new Date().getTime() + "";
				String t = operationType;
				// 将参数MD5加密生成密钥，约定顺序：OrderNo+PayAmount+userFrom+UId+约定的公钥
				String tmp = uid + Constants.SP_BASE_MD5_KEY + t;
				try {
					String key = MD5Util.md5Digest(tmp.toLowerCase());
					// 组装参数
					map.put("time", time);
					map.put("t", t);
					map.put("ut", userForm);
					map.put("UId", uid);
					map.put("passCode", key);
					// 获取结果0失败，1成功
					WebUtil.readContentFromGet(url, map);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	@Override
	public JSONObject editPassword(String oldpassword, String newpassword,
			String userid) throws Exception {
		JSONObject jsonObj = null;
		String url = Constants.BASE_SP_URL + "changePassword/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("oldpass", oldpassword);
		map.put("newpass", newpassword);
		String json = null;
		try {
			// 获取操作结果，json格式字符串
			json = WebUtil.readContentFromPost(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			jsonObj = JSONObject.fromObject(json);
		}
		return jsonObj;
	}

	/**
	 * 根据userId查询账号信息
	 * 
	 * @param userId
	 *            用户id
	 * 
	 * @return Account帐号信息
	 */
	@Override
	public Account findByUserId(String userid) {
		List<Account> accountList = accountDAO
				.find("FROM Account WHERE userid ='" + userid + "'");
		Account account = null;
		if (accountList.size() > 0) {
			account = accountList.get(0);
		}
		return account;
	}
	/**
	 * 根据phoneNumber查询账号信息
	 * 
	 * @param phoneNumber
	 *            电话号码
	 * 
	 * @return Account帐号信息
	 */
	@Override
	public AccountVO findAccountByPhone(String phoneNumber) {
		AccountVO accountVo=null;
		String url = Constants.BASE_SP_URL + "checkUsername/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", phoneNumber);

		String json = null;
		try {
			// 获取操作结果，json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj
					&& Constants.SUCCESS.equals(jsonObj.get("code"))) {
				accountVo = new AccountVO().json2Obj(jsonObj.toString());
			}
		}
		return accountVo;
		
	}

	@Override
	public JSONObject activateCouponCode(String userid, String mobileNumber, String couponcode) {
		JSONObject jsonObj = null;
		// // 组装参数
		Map<String, String> map = new HashMap<String, String>();
		try {
			// 获取操作结果，json格式字符串
			String url = Constants.BASE_URL + "codemactchedphone/";
			map.put("userid", userid);
			map.put("type", "bind:" + mobileNumber + "+coupon:" + couponcode);// TODO:modify
			String json = WebUtil.readContentFromGet(url, map);
			if (null != json && !"".equals(json)) {
				return JSONObject.fromObject(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj = null;
		}
		jsonObj = new JSONObject();
		jsonObj.put("code", "-1");
		jsonObj.put("msg", "数据错误，请重试");
		return jsonObj;
	}
}