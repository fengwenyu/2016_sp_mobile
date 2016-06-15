package com.shangpin.biz.service.impl;

import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.CustomerService;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBaseNew;
import com.shangpin.biz.service.ASPBizUserService;
import com.shangpin.biz.service.abstraction.AbstractBizUserService;
import com.shangpin.biz.utils.DateTimeUtil;
import com.shangpin.core.entity.Account;
import com.shangpin.core.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Service
public class ASPBizUserServiceImpl extends AbstractBizUserService implements ASPBizUserService {

	public static final Logger logger = LoggerFactory.getLogger(ASPBizUserServiceImpl.class);
	@Autowired
	IAccountService accountService;
	
    @Autowired
    CommonService commonService;
@Autowired
    private CustomerService customerService;
	@Override
	public void synchronizationAccount(User user, String channelNo, String productNo, HttpServletRequest request) {
		Account account = accountService.findByUserId(user.getUserid());
		Date date = new Date();
		if (account != null) {
			account.setLoginTime(date);
			accountService.modify(account);
		} else {
			account = new Account();
			account.setChannel(Long.valueOf(channelNo));
			account.setProduct(Long.valueOf(productNo));
			account.setGender(user.getGender());
			account.setLoginName(user.getName());
			account.setCreateTime(date); 
			account.setLoginTime(date);
			account.setRegTime(DateTimeUtil.getDateFomate(user.getRegTime()));
			account.setRegOrigin(user.getRegOrigin());
			account.setUserId(user.getUserid());
			account.setPlatform(request.getHeader("os"));
			account.setPhoneType(request.getHeader("mt"));
			account.setPhoneModel(request.getHeader("model"));
			accountService.save(account);
		}
	}

    @Override
    public String checkUserList(String phoneNums) {
      return commonService.checkUserList(phoneNums);
    }

@Override
	public String aspVerifyPhoneCode(String userId, String phoneNum,
			String verifyCode) {
		
		return this.fromVerifyPhoneCode(userId, phoneNum, verifyCode);
	}

    @Override
    public String modifyUserInfo(String userId, Map<String, String> map) {
        return customerService.modifyUserInfo(userId, map);
    }

    @Override
    public String getUserInfo(String userId) {
        return customerService.getUserInfo(userId);
    }

    @Override
    public User getUserInfoPojo(String userId) {
        String date = this.getUserInfo(userId);
        if(date == null){
            return null;
        }
        ResultBaseNew resultBaseNew = ResultBaseNew.formatToPojo(date,User.class);
        User user = null;
        if (resultBaseNew != null && "0".equals(resultBaseNew.getCode())) {
            user = (User) resultBaseNew.getContent();
        }
        return user;
    }

	@Override
	public String modifyUserInfoIcon(String userId, String picno) {
		return customerService.modifyUserInfoIcon(userId, picno);
	}
@Override
	public String getMyTaglia(String userId, String os) {		
		return customerService.getMyTaglia(userId, os);
	}
	
	@Override
	public String modifyMyTaglia(String userId, String modifyData, String os) {		
		return customerService.modifyMyTaglia(userId,modifyData,os);
	}

}