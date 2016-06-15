package com.shangpin.biz.service.impl;

import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.biz.service.abstraction.AbstractBizUserService;
import com.shangpin.core.entity.AccountWeixinBind;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;

@Service
public class SPBizUserServiceImpl extends AbstractBizUserService implements SPBizUserService {

	public static final Logger logger = LoggerFactory.getLogger(SPBizUserServiceImpl.class);

	@Override
	public User findUserByUserName(String userName) {
		try {
			ResultObjOne<User> obj = beUserByUserName(userName);
			if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
				return obj.getObj();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User weixinAutoLogin(AccountWeixinBind account) {
		try {
			User user = null;
			ResultObjOne<User> obj = beFindUserInfo(account.getUserId());
			if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
				user = obj.getObj();
			}
			if (user != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				user.setUserid(account.getUserId());
				user.setGender(account.getGender());
				user.setRegTime(sdf.format((account.getRegTime())));
				user.setRegOrigin(account.getRegOrigin());
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public QuickUser checkUser(String phone, String type) {
		try {
			ResultObjOne<QuickUser> obj = beCheckUser(phone, type);
			if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
				return obj.getObj();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public QuickUser checkUser(String phone, String type,String fashionRegisteSource) {
		try {
			ResultObjOne<QuickUser> obj = beCheckUser(phone, type,fashionRegisteSource);
			if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
				return obj.getObj();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String thirdLogin( String mode,String invitecode, String uid, String gender,String nickName,String trueName) {
		invitecode = "UYTUTSYG";//测试邀请码
		return commonService.thirdLogin(mode, invitecode, uid, gender, nickName, trueName);
	}

}
