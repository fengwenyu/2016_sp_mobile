package com.shangpin.biz.service.impl;


import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.AoLaiService;
import com.shangpin.base.service.CommonService;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.UserBuyInfo;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ALBizUserService;
import com.shangpin.biz.service.abstraction.AbstractBizUserService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.DateTimeUtil;
import com.shangpin.biz.utils.ParseLogUtil;
import com.shangpin.core.entity.Account;
import com.shangpin.core.service.IAccountService;
import com.shangpin.utils.JsonUtil;

@Service
public class ALBizUserServiceImpl extends AbstractBizUserService implements ALBizUserService {

    public static Logger logger = LoggerFactory.getLogger(ALBizUserServiceImpl.class);

    @Autowired
    CommonService commonService;
    @Autowired
    IAccountService accountService;
    @Autowired
    private AoLaiService aoLaiService;
    
	@Override
	public String register(String email, String phonenum, String password,
			String gender, String smscode, String type, String invitecode) {
		String json = commonService.register(email, phonenum, password, gender, smscode, type, invitecode);
		if(!StringUtils.isEmpty(json)){
			 return json;
		}
		return null;
	}

    @Override
    public void synchronizationAccount(User user,String channel,String product,String ua) {
            Account account=new Account();
            account.setChannel(Long.valueOf(channel));
            account.setProduct(Long.valueOf(product));
            account.setGender(user.getGender());
            account.setLoginName(user.getEmail());
            account.setCreateTime(new Date());
            account.setLoginTime(new Date());
            account.setRegTime(DateTimeUtil.parse(user.getRegTime()));
            account.setRegOrigin(user.getRegOrigin());
            account.setUserId(user.getUserid());
            Map<String, String> uaMap = ParseLogUtil.parseUA(ua);
            if (uaMap != null) {
              account.setPlatform(uaMap.get("platform"));
              account.setPhoneModel(uaMap.get("phoneModel"));
            } 
            accountService.save(account);
        }

    @Override
    public String getUserBuyInfo(String userId) {
        String json = aoLaiService.findUserBuyInfo(userId, Constants.SHOP_TYPE_AOLAI);
        if(!StringUtils.isEmpty(json)){
            return json;
       }
       return null;
    }
    @Override
    public UserBuyInfo getUserBuyInfoObj(String userId) {
        String json = getUserBuyInfo(userId);
        if(!StringUtils.isEmpty(json)){
            ResultObjOne<UserBuyInfo> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<UserBuyInfo>>() {
            });
            return obj.getObj();
        }
        return null;
    }
    

}
