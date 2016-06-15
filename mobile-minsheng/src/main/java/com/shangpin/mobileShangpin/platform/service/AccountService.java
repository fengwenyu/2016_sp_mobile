package com.shangpin.mobileShangpin.platform.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.shangpin.mobileShangpin.base.model.Account;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;
import com.shangpin.mobileShangpin.platform.vo.ConsigneeAddressVO;

/**
 * 用户帐号管理业务逻辑接口，用于用户注册、登录、设置收货人地址等操作。
 * 
 * @author zhouyu
 * @CreatDate 2012-10-29
 */
public interface AccountService {
	public void addAccount(Account acc) throws Exception;
	
	public void modifyAccount(Account acc) throws Exception;
	
	// public Page getAllAccounts() throws Exception;
	/**
	 * 根据登录名和产品号获取用户
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-12-04
	 * @param loginName
	 *            登录名
	 * @Return
	 */
	public Account getAccountByParams(String loginName) throws Exception;

	/**
	 * 登录校验
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-29
	 * @param loginName
	 *            登录名
	 * @param password
	 *            密码
	 * @Return
	 */
	public boolean login(String loginName, String password) throws Exception;

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
	public boolean register(String loginName, String password, String gender, String channelNo) throws Exception;

	/**
	 * 根据用户ID，地址ID，获取收货人地址信息
	 * 
	 * @param userid
	 *            用户ID
	 * @param addrid
	 *            地址ID
	 * 
	 * @return 返回收货人地址信息
	 */
	public ConsigneeAddressVO getConsigneeAddress(String userid, String addrid);

	/**
	 * 根据用户ID，获取收货人地址列表
	 * 
	 * @param userid
	 *            用户ID
	 * 
	 * @return 返回收货人地址列表
	 */
	public List<ConsigneeAddressVO> getConsigneeAddressList(String userid);

	/**
	 * 根据用户ID及收货人地址对象，添加、更新收货人地址
	 * 
	 * @param userid
	 *            用户ID
	 * @param vo
	 *            收货人地址对象
	 * 
	 * @return 返回json对象
	 */
	public JSONObject addOrUpdateConsigneeAddress(String userid, ConsigneeAddressVO vo);

	/**
	 * 根据用户ID、收货人地址ID，删除收货人地址
	 * 
	 * @param userid
	 *            用户ID
	 * 
	 * @return 返回操作是否成功
	 */
	public String removeConsigneeAddress(String userid, String addrid);

	/**
	 * 根据用户ID及发票收货地址对象，添加发票收货地址
	 * 
	 * @param userid
	 *            用户ID
	 * @param vo
	 *            收货人地址对象
	 * 
	 * @return 返回json对象
	 */
	public JSONObject addInvoiceAddress(String userid, ConsigneeAddressVO vo);

	/**
	 * 根据用户ID、手机号码，发送验证码
	 * 
	 * @param userid
	 *            用户ID
	 * @param phonenum
	 *            手机号码
	 * String msgtemplate
	 * @return 返回json对象
	 */
	public JSONObject sendPhoneCode(String userid, String phonenum);
	
	/**
	 * 根据用户ID、手机号码，发送模板信息
	 * 
	 * @param userid
	 *            用户ID
	 * @param phonenum
	 *            手机号码
	 * 
	 * @param msgtemplate
	 *            手机号码
	 * @return 返回json对象
	 */
	public JSONObject sendPhoneCode(String userid, String phonenum,String msgtemplate);

	/**
	 * 根据用户ID、手机号码、验证码，验证并绑定手机
	 * 
	 * @param userid
	 *            用户ID
	 * @param phonenum
	 *            手机号码
	 * @param verifycode
	 *            验证码
	 * 
	 * @return 返回json对象
	 */
	public JSONObject verifyPhoneCode(String userid, String phonenum, String verifycode);

	/**
	 * 根据用户ID，获取用户购物袋、各订单状态（待支付、配货中、发货中）数量
	 * 
	 * @param userid
	 *            用户ID
	 * 
	 * @return 用户购物袋、各订单状态（待支付、配货中、发货中）数量json对象
	 */
	public JSONObject getUserBuyInfo(String userid, String shoptype);

	/**
	 * 用户登录时，如果在5秒，访问超过10次，将此用户禁止10秒钟不能登录
	 * 
	 * @param userName
	 *            用户名
	 * @param ip
	 *            用户IP
	 * @return 是否禁用，true为禁用
	 */
	public boolean ipBlacklist(String userName, String ip) throws Exception;
	
	/**
	 * 修改密码
	 * 
	 * @param oldpassword
	 * @param newpassword
	 *  @param userid
	 * @return 是否成功
	 */
	public JSONObject editPassword(String oldpassword, String newpassword,String userid) throws Exception;
	
	
	/**
	 * 根据userid查询账号信息
	 * 
	 * @param userId
	 *            用户id
	 * 
	 * @return Account帐号信息
	 */
	public Account findByUserId(String userId);
	/**
	 * 根据phoneNumber查询账号信息
	 * 
	 * @param phoneNumber
	 *            电话号码
	 * 
	 * @return Account帐号信息
	 */
	public AccountVO findAccountByPhone(String phoneNumber);

	public JSONObject activateCouponCode(String userid, String mobileNumber, String couponcode);

}
