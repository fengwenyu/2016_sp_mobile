package com.shangpin.biz.service.basic;

import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.SourceEnum;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;

/**
 * 用户基础接口
 * 
 * @author zghw
 *
 */
public interface IBizUserService {
	/**
	 * 登录（尚品、奥莱完全相同）
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @author zghw
	 */
	public String fromLogin(String userName, String password);

	/**
	 * 登录（尚品、奥莱完全相同）
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<User> beLogin(String userName, String password);

	/**
	 * 注册
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
	 * @return json串
	 * @author zghw
	 */
	public String fromRegister(String email, String phonenum, String password, String gender, String smscode, String type, String invitecode);
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
	 * @param channelNo 参数与网盟的source对应
	 * @param channelId 参数与网盟的campaign对应
	 * @param param 网盟自定义参数，如无参数可为空
	 * @param channelType 尚品客为0，网盟为1；当为空时，后台默认为0
	 * @return
	 */
	public String fromRegister(String email, String phonenum, String password, String gender, String smscode, String type, String invitecode,String channelNo,String channelId,String param,String channelType);

	/**
	 * 注册
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
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<User> beRegister(String email, String phonenum, String password, String gender, String smscode, String type, String invitecode);

	/**
	 * 找回密码（尚品、奥莱共用）
	 * 
	 * @param email
	 *            注册时的邮箱
	 * @return
	 * @author zghw
	 */
	public String fromForgotPassword(String email);

	/**
	 * 找回密码（尚品、奥莱共用）
	 * 
	 * @param email
	 *            注册时的邮箱
	 * @return
	 * @author zghw
	 */
	public ResultBase beForgotPassword(String email);

	/**
	 * 验证手机号（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户ID
	 * @param phoneNum
	 *            手机号
	 * @param verifyCode
	 *            验证码
	 * @return
	 * @author zghw
	 */
	public String fromVerifyPhoneCode(String userId, String phoneNum, String verifyCode);

	/**
	 * 验证手机号（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户ID
	 * @param phoneNum
	 *            手机号
	 * @param verifyCode
	 *            验证码
	 * @return
	 * @author zghw
	 */
	public ResultBase beVerifyPhoneCode(String userId, String phoneNum, String verifyCode);

	/**
	 * 发送手机验证码（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户ID
	 * @param phoneNum
	 *            手机号
	 * @param msgTemplate
	 *            发送验证码的文字模版
	 * @param type
	 *            1:注册，2:其他
	 * @return
	 * @author zghw
	 */
	public String fromSendVerifyCode(String userId, String phoneNum, String msgTemplate);

	/**
	 * 发送手机验证码（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户ID
	 * @param phoneNum
	 *            手机号
	 * @param msgTemplate
	 *            发送验证码的文字模版
	 * @param type
	 *            1:注册，2:其他
	 * @return
	 * @author zghw
	 */
	public ResultBase beSendVerifyCode(String userId, String phoneNum, String msgTemplate);

	/**
	 * 根据用户名或手机号查找用户（尚品、奥莱完全相同）
	 * 
	 * @param userName
	 *            邮箱或手机号
	 * @return
	 * @author zhanghongwei
	 */
	public String fromUserByUserName(String userName);

	/**
	 * 根据用户名或手机号查找用户（尚品、奥莱完全相同）
	 * 
	 * @param userName
	 *            邮箱或手机号
	 * @return
	 * @author zhanghongwei
	 */
	public ResultObjOne<User> beUserByUserName(String userName);

	/**
	 * 根据用户ID查找用户
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 * @author zhanghongwei
	 */
	public String fromFindUserInfo(String userId);

	/**
	 * 根据用户ID查找用户
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 * @author zhanghongwei
	 */
	public ResultObjOne<User> beFindUserInfo(String userId);

	/**
	 * 手机修改回密码
	 * 
	 * @param userid
	 *            用户id
	 * 
	 * @param password
	 *            新密码
	 * @return
	 * @author zghw
	 */
	public String fromChangePassword(String userid, String password);

	/**
	 * 手机修改回密码
	 * 
	 * @param userid
	 *            用户id
	 * 
	 * @param password
	 *            新密码
	 * @return
	 * @author zghw
	 */
	public ResultBase beChangePassword(String userid, String password);

	/**
	 * 通过手机号查找用户是否存在，可直接创造一个新的用户
	 * 
	 * @param phone
	 *            手机号码
	 * @param type
	 *            是否需要创建新用户（0:只查找是否存在；1：查找是否存在用户，不存在创建一个新的用户）
	 * @return
	 * @author zghw
	 */
	public String fromCheckUser(String phone, String type);

	/**
	 * 通过手机号查找用户是否存在，可直接创造一个新的用户
	 * 
	 * @param phone
	 *            手机号码
	 * @param type
	 *            是否需要创建新用户（0:只查找是否存在；1：查找是否存在用户，不存在创建一个新的用户）
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<QuickUser> beCheckUser(String phone, String type);

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
	public String fromBindToUser(String userId, String typeInfo);

	public ResultBase beBindToUser(String userId, String typeInfo);

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
	public void saveSendPhoneInfo(String phone, String imei, SourceEnum source);

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
	public boolean isOpenCheck(String phone, String imei, SourceEnum source);
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
	public Integer getSendCount(String phone, String imei, SourceEnum source);
}
