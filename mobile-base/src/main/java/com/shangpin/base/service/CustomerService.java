package com.shangpin.base.service;

import java.util.Map;

import com.shangpin.base.vo.ConsigneeAddress;

/**
 * 用户管理相关接口的Service
 * 
 * @author sunweiwei
 * 
 */
public interface CustomerService {

	/**
	 * 设定默认收货地址（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * 
	 * @parm addrId 用户收货地址的唯一标识
	 * 
	 * @return
	 * @author liujie
	 */
	public String modifyDefaultConsigneeAddress(String userId, String addrId);

	/**
	 * 设定根据地址id获取收货地址信息（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * 
	 * @parm addrid 用户收货地址的唯一标识
	 * 
	 * @return
	 * @author liujie
	 */
	public String findConsigneeAddressById(String userId, String addrId);

	/**
	 * 获取个人信息（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * @return
	 * @author cuibinqiang
	 */
	public String findUserInfo(String userId);

	/**
	 * 新增收货地址（尚品、奥莱完全相同）
	 * 
	 * @param consigneeAddressVO
	 *            请求参数
	 * @return
	 * @author cuibinqiang
	 */
	public String addConsigneeAddress(ConsigneeAddress consigneeAddressVO);

	/**
	 * 删除收货地址（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * @param addrId
	 *            用户收货地址的唯一标识
	 * @return
	 * @author cuibinqiang
	 */
	public String deleteConsigneeAddress(String userId, String addrId);

	/**
	 * 编辑收货地址（尚品、奥莱完全相同）
	 * 
	 * @param consigneeAddressVO
	 *            请求参数
	 * @return
	 * @author cuibinqiang
	 */
	public String modifyConsigneeAddress(ConsigneeAddress consigneeAddressVO);

	/**
	 * 查询收货地址（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * @return
	 * @author cuibinqiang
	 */
	public String findConsigneeAddress(String userId);

	/**
	 * 查询收货地址（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 * @param isInvoice
	 *            0代表地址列表 1是发票地址列表
	 * @return
	 * @author zghw
	 */
	public String findConsigneeAddress(String userId, String isInvoice);

	/**
	 * 发送手机验证码（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户ID
	 * @param phoneNum
	 *            手机号
	 * @param msgTemplate
	 *            发送验证码的文字模版
	 * @return
	 * @author cuibinqiang
	 */
	public String sendVerifyCode(String userId, String phoneNum,
			String msgTemplate);

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
	 * @author cuibinqiang
	 */
	public String verifyPhoneCode(String userId, String phoneNum,
			String verifyCode);

	/**
	 * 用户手机号与验证码匹配成功后的操作（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户ID
	 * @param type
	 *            操作类型:bindPhone绑定手机号； couponReceive领取优惠券
	 * @param typeValue
	 *            操作类型对应的值：bindPhone类型对应手机号；couponReceive类型对应优惠券充值码
	 * @return
	 * @author cuibinqiang
	 */
	public String verifySuccess(String userId, String type, String typeValue);

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
	public String bindToUser(String userId, String typeInfo);

	/**
	 * 根据用户名或手机号查找用户（尚品、奥莱完全相同）
	 * 
	 * @param userName
	 *            邮箱或手机号
	 * @return
	 * @author zhanghongwei
	 */
	public String findUserByUserName(String userName);

	/**
	 * 根据用户名或手机号查找用户（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户Id
	 * @return
	 * @author sunweiwei
	 */
	public String findUserByUserId(String userId);

	/**
	 * 确认收货（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderNo
	 *            订单ID
	 * @return
	 * @author cuibinqiang
	 */
	public String confirmOrderGoods(String userId, String orderNo);

	/**
	 * 手机修改回密码(尚品)
	 * 
	 * @param userid
	 *            用户id
	 * 
	 * @param password
	 *            新密码
	 * @return
	 */
	public String changePassword(String userid, String password);

	/**
	 * 
	 * @Title: modifyUserInfo
	 * @Description: 修改个人信息（头像 手机 昵称 性别 生日）
	 * @param userId
	 * @param map
	 * @return String
	 * @author fengwenyu
	 * @date 2015年12月16日
	 */
	public String modifyUserInfo(String userId, Map<String, String> map);

	/**
	 * 获取用户信息
	 * 
	 * @return String
	 * @throws
	 * @author fengwenyu
	 * @date 2015年12月16日
	 */
	public String getUserInfo(String userId);

	/**
	 * 修改用户头像
	 * 
	 * @param userId
	 *            用户id
	 * @param icon
	 *            头像url
	 * @return
	 */
	public String modifyUserInfoIcon(String userId, String picno);

	/**
	 * 获取我的尺码
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public String getMyTaglia(String userId, String os);

	/**
	 * 修改我的尺码
	 * 
	 * @param userId
	 *            用户id
	 * @param modifyData
	 *            修改的数据
	 * @return
	 */
	public String modifyMyTaglia(String userId, String modifyData, String os);
}
