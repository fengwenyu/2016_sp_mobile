package com.shangpin.biz.service;

import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.UserBuyInfo;
import com.shangpin.biz.service.basic.IBizUserService;

public interface ALBizUserService extends IBizUserService {
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
	 * 
	 * @param email
	 *            用户名称 当邮箱注册时，为必填项
	 * @param phonenum手机号码
	 *            当手机号注册时，为必填项
	 * @param password登录密码
	 * @param gender
	 *            性别 0：女；1：男
	 * @param smscode手机注册六位验证码
	 *            当type为1时，为必填项
	 * @param type
	 *            注册方式 0：邮箱 1： 手机
	 * @param invitecode
	 *            邀请码
	 * @return code 返回注册状态0:成功,1:失败
	 */
	public String register(String email, String phonenum, String password, String gender, String smscode, String type,
			String invitecode);

	/**
	 * 查询用户购买信息
	 * 
	 * @param userId
	 *            用户ID
	 * @author zghw
	 * 
	 */
	public String getUserBuyInfo(String userId);

	/**
	 * 查询用户购买信息
	 * 
	 * @param userId
	 *            用户ID
	 * @author zghw
	 * 
	 */
	public UserBuyInfo getUserBuyInfoObj(String userId);

	/**
	 * 同步数据到account表中
	 * 
	 * @param user
	 *            用户
	 * @param channel渠道
	 * @param product
	 * @param userAgent
	 *            用户代理User-Agent
	 */
	public void synchronizationAccount(User user, String channel, String product, String userAgent);

}
