package com.shangpin.biz.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.basic.IBizUserService;

public interface ASPBizUserService extends IBizUserService {

	QuickUser checkPhoneUser(String phone, String channelNo);

	/**
	 * 根据用户Id（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户Id
	 * @return
	 * @author zghw
	 */
	public User findUserByUserId(String userId);

	/**
	 * 同步数据到account表中
	 * 
	 * @param user
	 *            用户
	 * @param channel渠道
	 * @param product
	 * @param request
	 * 
	 */
	public void synchronizationAccount(User user, String channel, String product, HttpServletRequest request);
	
	   
     /**
      * 批量查询用户是否注册
      * @param phoneNums 手机号
      * @return
      */
     public String checkUserList(String phoneNums);
/**
      * 上传用户头像
      * @return String
      * @author fengwenyu 
      * @date 2015年12月16日
      */
     public String modifyUserInfo(String userId,Map<String, String> map);
     
     /**
      * 获取用户信息
      * @return String
      * @throws 
      * @author fengwenyu 
      * @date 2015年12月16日
      */
     public String getUserInfo(String userId);
     
     /**
      * 获取用户信息
      * @return User
      * @throws 
      * @author fengwenyu 
      * @date 2015年12月16日
      */
     public User getUserInfoPojo(String userId);
     /**
      * 登录后在个人中心修改密码或修改礼品卡支付密码
      * @param userId
      * @param type
      * @param nowPassword
      * @param newPassword
      * @param confirmPassword
      * @return
      */
     public String modifyPassword(String userId,String type, String nowPassword,String newPassword,String confirmPassword);
     public String aspVerifyPhoneCode(String userId, String phoneNum,String verifyCode);
     
     /**
      * 修改用户头像
      * @param userId 用户id
      * @param icon 头像url 
      * @return
      */
     public String modifyUserInfoIcon(String userId, String picno);
     
     /**
      * 获取我的尺码
      * @param userId 用户id
      * @return
      */
     public String getMyTaglia(String userId, String os);
     
     /**
      * 修改我的尺码
      * @param userId 用户信息
      * @param modifyData 修改数据
      * @return
      */
     public String modifyMyTaglia(String userId, String modifyData, String os);

     String getShangpinDomain();
}
