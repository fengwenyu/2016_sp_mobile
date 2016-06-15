package com.shangpin.biz.service;

import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.UserBuyInfo;
import com.shangpin.biz.service.basic.IBizUserService;
import com.shangpin.core.entity.AccountWeixinBind;

public interface SPBizUserService extends IBizUserService {
    /**
     * 根据用户名或手机号查找用户（尚品、奥莱完全相同）
     * 
     * @param userName
     *            邮箱或手机号
     * @return
     * @author zhanghongwei
     */
    public User findUserByUserName(String userName);

    /**
     * 根据用户Id（尚品、奥莱完全相同）
     * 
     * @param userId
     *            用户Id
     * @return
     * @author sunweiwei
     */
    public User findUserByUserId(String userId);

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
    public User login(String userName, String password);

    /**
     * 微信登录（尚品、奥莱完全相同）
     * 
     * @param userName
     *            用户名
     * @param password
     *            密码
     * @return
     * @author liling
     */
    public User weixinAutoLogin(AccountWeixinBind account);

    /**
     * 
     * @Title: checkUser
     * @Description: 检查是否为新用户
     * @param phone
     * @param type
     *            0:只查找是否存在；1：查找是否存在用户，不存在创建一个新的用户
     * @return QuickUser
     * @throws
     * @Create By qinyingchun
     * @Create Date 2014年11月20日
     * @author zghw
     */
    public QuickUser checkUser(String phone, String type);
    
    /**
     * 获取用户购买信息
     * @param userId
     * @param type
     * @return
     */
    public UserBuyInfo getUserBuyInfoObj(String userId, String type);

	public QuickUser checkUser(String phone, String createNewUser,
			String fashionRegisteSource);
	
	/**
	 * 检查手机号是否注册尚品用户
	 * @param phoneNum
	 * @param channelNo
	 * @return
	 */
	public QuickUser checkPhoneUser(String phoneNum, String channelNo);

    /**
     * 微信 qq 第三方登录
     * @param invitecode 邀请码
     * @param mode 请求来源：qq weixin等
     * @param uid   第三方的用户id openid
     * @param gender   gender
     * @param nickName 第三方的昵称
     * @param trueName 第三方的用户真实名字
     * @return
     */
    public String thirdLogin(String mode, String invitecode, String uid,String gender,String nickName,String trueName);

    /**
     * 获取m站的域名
     * @return
     */
    public String getShangpinDomain();
}
