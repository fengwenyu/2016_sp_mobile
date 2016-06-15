package com.shangpin.biz.service.abstraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.AoLaiService;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.CustomerService;
import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.SourceEnum;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.UserBuyInfo;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;
import com.shangpin.utils.StringUtil;

public abstract class AbstractBizUserService {

	public static Logger logger = LoggerFactory.getLogger(AbstractBizUserService.class);

	@Autowired
	public CommonService commonService;
	@Autowired
	CustomerService customerService;
	@Autowired
	AoLaiService aoLaiService;

	public String fromLogin(String userName, String password) {
		String json = commonService.login(userName, password);
		return json;
	}

	public String fromFindUserByUserId(String userId) {
		String json = customerService.findUserByUserId(userId);
		return json;
	}

	public ResultObjOne<User> beFindUserByUserId(String userId) {
		String json = fromFindUserByUserId(userId);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<User> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<User>>() {
			});
			return result;
		}
		return null;
	}

	public ResultObjOne<User> beLogin(String userName, String password) {
		String json = fromLogin(userName, password);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<User> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<User>>() {
			});
			return result;
		}
		return null;
	}

	public String fromRegister(String email, String phonenum, String password, String gender, String smscode, String type, String invitecode) {
		String json = commonService.register(email, phonenum, password, gender, smscode, type, invitecode);
		return json;
	}
	public String fromRegister(String email, String phonenum, String password, String gender, String smscode, String type, String invitecode,String channelNo,String channelId,String param,String channelType) {
		String json = commonService.register(email, phonenum, password, gender, smscode, type, invitecode, channelNo, channelId,param,channelType);
		return json;
	}

	public ResultObjOne<User> beRegister(String email, String phonenum, String password, String gender, String smscode, String type, String invitecode) {
		String json = fromRegister(email, phonenum, password, gender, smscode, type, invitecode);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<User> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<User>>() {
			});
			return result;
		}
		return null;
	}

	public String fromForgotPassword(String email) {
		String json = commonService.forgotPassword(email);
		return json;
	}

	public ResultBase beForgotPassword(String email) {
		String json = fromForgotPassword(email);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}

	public String fromVerifyPhoneCode(String userId, String phoneNum, String verifyCode) {
		String json = customerService.verifyPhoneCode(userId, phoneNum, verifyCode);
		return json;
	}

	public ResultBase beVerifyPhoneCode(String userId, String phoneNum, String verifyCode) {
		String json = fromVerifyPhoneCode(userId, phoneNum, verifyCode);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}

	public String fromSendVerifyCode(String userId, String phoneNum, String msgTemplate) {
		String json = customerService.sendVerifyCode(userId, phoneNum, msgTemplate);
		return json;
	}

	public ResultBase beSendVerifyCode(String userId, String phoneNum, String msgTemplate) {
		String json = fromSendVerifyCode(userId, phoneNum, msgTemplate);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}

	public String fromUserByUserName(String userName) {
		String json = customerService.findUserByUserName(userName);
		return json;
	}

	/**
	 * 根据用户Id查找用户
	 * 
	 * @param userId
	 * @return
	 */
	public String fromUserByUserId(String userId) {
		String json = customerService.findUserByUserId(userId);
		return json;
	}

	public ResultObjOne<User> beUserByUserName(String userName) {
		String json = fromUserByUserName(userName);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<User> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<User>>() {
			});
			return result;
		}
		return null;
	}

	public String fromFindUserInfo(String userId) {
		String json = commonService.findUserInfo(userId);
		return json;
	}

	public ResultObjOne<User> beFindUserInfo(String userId) {
		String json = fromFindUserInfo(userId);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<User> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<User>>() {
			});
			return result;
		}
		return null;
	}

	public String fromChangePassword(String userid, String password) {
		String json = customerService.changePassword(userid, password);
		return json;
	}

	public ResultBase beChangePassword(String userid, String password) {
		String json = fromChangePassword(userid, password);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}

	public String fromCheckUser(String phone, String type) {
		String json = commonService.checkUser(phone, type);
		return json;
	}

	public ResultObjOne<QuickUser> beCheckUser(String phone, String type) {
		String json = fromCheckUser(phone, type);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<QuickUser> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<QuickUser>>() {
			});
			return result;
		}
		return null;
	}

	public ResultObjOne<QuickUser> beCheckUser(String phone, String type, String source) {
		String json = fromCheckUser(phone, type, source);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<QuickUser> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<QuickUser>>() {
			});
			return result;
		}
		return null;
	}

	private String fromCheckUser(String phone, String type, String source) {
		String json = commonService.checkUser(phone, type, source);
		return json;
	}

	/**
	 * 用户绑定相关的信息
	 * 
	 * @param userId
	 * @param typeInfo
	 *            绑定的信息 绑定手机例如：typeInfo=bind:13699120345
	 *            绑定优惠券例如：typeInfo=coupon:123456
	 * @return
	 * @author zghw
	 */
	public String fromBindToUser(String userId, String typeInfo) {
		String json = customerService.bindToUser(userId, typeInfo);
		return json;
	}

	public ResultBase beBindToUser(String userId, String typeInfo) {
		String json = fromBindToUser(userId, typeInfo);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}

	/**
	 * 登录（尚品、奥莱完全相同）
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @author sunweiwei
	 */
	public User login(String userName, String password) {
		try {
			ResultObjOne<User> obj = beLogin(userName, password);
			if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
				return obj.getObj();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 登录（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户Id
	 * @return
	 * @author sunweiwei
	 */
	public User findUserByUserId(String userId) {
		try {
			ResultObjOne<User> obj = beFindUserByUserId(userId);
			if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
				return obj.getObj();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getUserBuyInfo(String userId, String type) {
		String json = aoLaiService.findUserBuyInfo(userId, type);
		if (!StringUtils.isEmpty(json)) {
			return json;
		}
		return null;
	}

	public UserBuyInfo getUserBuyInfoObj(String userId, String type) {
		String json = getUserBuyInfo(userId, type);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<UserBuyInfo> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<UserBuyInfo>>() {
			});
			return obj.getObj();
		}
		return null;
	}

	public QuickUser checkPhoneUser(String phoneNum, String channelNo) {
		String json = commonService.checkPhoneUser(phoneNum, channelNo);
		try {
			if (StringUtils.isEmpty(json)) {
				return null;
			}
			ResultObjOne<QuickUser> resultObjOne = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<QuickUser>>() {
			});
			if (null != resultObjOne && Constants.SUCCESS.equals(resultObjOne.getCode())) {
				return resultObjOne.getObj();
			}
		} catch (Exception e) {
			logger.error("check phone user occur error,return data:{}", json);
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 保存发送手机信息到缓存中
	 * 
	 * @param phone
	 *            手机号码
	 * @param imei
	 *            手机imei值
	 * @param source
	 *            发送手机时间来源
	 * @author zghw
	 */
	public void saveSendPhoneInfo(String phone, String imei, SourceEnum source) {
		// 手机号码+imei+来源作为key
		String key = getSendPhoneInfoKey(phone, imei, source);
		// 递增添加到缓存中
		int expire = 0;
		switch (source) {
		case Ql:
			expire = 1800;
			break;
		}
		ApiBizData.incrKey(key, expire);
	}

	/**
	 * 缓存中的key
	 * 
	 * @author zghw
	 */
	private String getSendPhoneInfoKey(String phone, String imei, SourceEnum source) {
		String key = phone.concat("-").concat(imei).concat("-").concat(source.name());
		return key;
	}

	/**
	 * 是否打开验证
	 * 
	 * @param phone
	 *            手机号码
	 * @param imei
	 *            手机imei值
	 * @param source
	 *            发送手机时间来源
	 * @author zghw
	 */
	public boolean isOpenCheck(String phone, String imei, SourceEnum source) {
		// 手机号码+imei+来源作为key
		Integer count = getSendCount(phone, imei, source);
		boolean isOpenCheck = false;
		switch (source) {
		case Ql:
			if (count > 1) {
				isOpenCheck = true;
			}
			break;
		}
		return isOpenCheck;
	}
	/**
	 * 手机发送短信的次数
	 * 
	 * @param phone
	 *            手机号码
	 * @param imei
	 *            手机imei值
	 * @param source
	 *            发送手机时间来源
	 * @author zghw
	 */
	public Integer getSendCount(String phone, String imei, SourceEnum source) {
		String key = getSendPhoneInfoKey(phone, imei, source);
		String value = ApiBizData.getCache(key);
		Integer count = 0;
		if (StringUtil.isNotEmpty(value)) {
			count = Integer.valueOf(value);
		}
		return count;
	}
	 /**
     * 登录后在个人中心修改密码或修改礼品卡支付密码
     * @param userId
     * @param type
     * @param nowPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
	public String modifyPassword(String userId, String type,String nowPassword, String newPassword, String confirmPassword) {
		String data = commonService.fromModifyPassword(userId, type, nowPassword, newPassword, confirmPassword);
		return data;
	}

	/**
	 * 获取m站的域名
	 * @return
     */
	public String getShangpinDomain() {
		return commonService.getShangpinDomain();
	}
}
