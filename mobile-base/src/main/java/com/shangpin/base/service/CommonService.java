package com.shangpin.base.service;

import java.util.List;

import com.shangpin.base.vo.Area;
import com.shangpin.base.vo.Brand;
import com.shangpin.base.vo.Category;
import com.shangpin.base.vo.City;
import com.shangpin.base.vo.ConsigneeAddress;
import com.shangpin.base.vo.Province;

/**
 * 调用主站公共接口的Service 通用的都写在这里
 * 
 * @author sunweiwei
 * @version v2.0
 * 
 */
public interface CommonService {

	/**
	 * 获取用户信息
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String findUserInfo(String userId);

	/**
	 * 品类推荐品牌
	 * 
	 * @param categoryId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public String categoryCommendBrandList(String categoryId, String pageIndex, String pageSize);

	/**
	 * 获取图片url
	 * 
	 * @param picNOs
	 *            图片编号
	 * @param picType
	 *            图片类型: product
	 * @return
	 */
	public String queryPicUrl(String picNOs, String picType);

	/**
	 * 
	 * @Title: queryPicUrl
	 * @Description:获取图片url
	 * @param @param picNo
	 * @param @param width
	 * @param @param height
	 * @param @param picType
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年10月28日
	 */
	public String queryPicUrl(String picNo, String width, String height, String picType);

	/**
	 * 登录（尚品、奥莱完全相同）
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @author zhanghongwei
	 */
	public String login(String userName, String password);

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
	 * @return
	 * @author zhanghongwei
	 */
	public String register(String email, String phonenum, String password, String gender, String smscode, String type, String invitecode);
	public String register(String email, String phonenum, String password, String gender, String smscode, String type, String invitecode,String channelNo,String channelId,String param,String channelType);

	/**
	 * 新增发票地址（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * @param consigneeName
	 *            收货人姓名
	 * @param province
	 *            省份id
	 * @param city
	 *            城市id
	 * @param area
	 *            县（区）id
	 * @param address
	 *            街道详细地址
	 * @param postcode
	 *            邮编
	 * @param tel
	 *            手机
	 * @return
	 * @author liujie
	 */
	public String addInvoiceAddress(ConsigneeAddress consiqneeAddressVO);

	public String addInvoiceAddress(String userId, String name, String proviceId, String cityId, String areaId, String townId, String addr, String postCode, String tel);

	/**
	 * 删除发票地址（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * 
	 * @parm addrid 用户收货地址的唯一标识
	 * 
	 * @return
	 * @author liujie
	 */
	public String deleteInvoiceAddress(String userId, String addrId);

	/**
	 * 编辑发票地址（尚品、奥莱完全相同）
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * @param addrid
	 *            用户收货地址的唯一标识
	 * @param consigneeName
	 *            收货人姓名
	 * @param province
	 *            省份id
	 * @param city
	 *            城市id
	 * @param area
	 *            县（区）id
	 * @param address
	 *            街道详细地址
	 * @param postcode
	 *            邮编
	 * @param tel
	 *            手机
	 * @return
	 * @author liujie
	 */
	public String modifyInvoiceAddress(ConsigneeAddress consiqneeAddressVO);

	public String modifyInvoiceAddress(String invoiceId, String userId, String name, String proviceId, String cityId, String areaId, String townId, String addr, String postCode,
			String tel);

	/**
	 * 第三方登陆（尚品、奥莱完全相同）
	 * 
	 * @param mode
	 *            登录方式：weibo 新浪微博；qq 腾讯qq；zhifubao 支付宝；
	 * @param inviteCode
	 *            邀请码
	 * @param uid
	 *            第三方返回的用户id，如果已经入库，则忽略后面的字段
	 * @param sex
	 *            第三方返回的性别(0女1男),为空时不改变原有值
	 * @param nickName
	 *            第三方返回的昵称 ,为空时不改变原有值
	 * @param trueName
	 *            第三方返回的真是姓名, 为空时不改变原有值
	 * @return
	 * @author zhanghongwei
	 */
	public String thirdLogin(String mode, String inviteCode, String uid, String sex, String nickName, String trueName);

	/**
	 * 找回密码（尚品、奥莱共用）
	 * 
	 * @param email
	 *            注册时的邮箱
	 * @return
	 * @author zhanghongwei
	 */
	public String forgotPassword(String email);

	/**
	 * 收藏商品接口（尚品）
	 * 
	 * @param userId
	 *            用户唯一ID
	 * @param shopType
	 *            来自尚品1，奥莱2。参数不传或空时，表示奥莱；参数为1时，返回添加成功、失败，不需要商品列表
	 * @param sku
	 *            货物id
	 * @param count
	 *            购买数量
	 * @param picw
	 *            商品缩略图宽度 可为空
	 * @param pich
	 *            商品缩略图高度 可为空
	 * @param detailPicw
	 *            商品快照宽度 可为空
	 * @param detailPich
	 *            商品快照高度 可为空
	 * @return
	 * @author zhanghongwei
	 */
	public String addProductToCollect(String userId, String shopType, String sku, String count, String picw, String pich, String detailPicw, String detailPich);

	/**
	 * 查询收藏商品接口（尚品）
	 * 
	 * @param userId
	 *            用户唯一ID
	 * @param shopType
	 *            来自尚品1，奥莱2。
	 * @param picw
	 *            商品缩略图宽度 可为空
	 * @param pich
	 *            商品缩略图高度 可为空
	 * @param detailPicw
	 *            商品快照宽度 可为空
	 * @param detailPich
	 *            商品快照高度 可为空
	 * @return
	 * @author zhanghongwei
	 */
	public String findCollectList(String userId, String shopType, String picw, String pich, String detailPicw, String detailPich);

	/**
	 * 获取省级行政区信息（尚品、奥莱完全相同）
	 * 
	 * @return
	 * @author cuibinqiang
	 */
	public String findProvinceList();

	/**
	 * 获取城市行政区信息（尚品、奥莱完全相同）
	 * 
	 * @param Id
	 *            省份ID
	 * @return
	 * @author cuibinqiang
	 */
	public String findCityList(String Id);

	/**
	 * 获取地区行政区信息（尚品、奥莱完全相同）
	 * 
	 * @param Id
	 *            城市ID
	 * @return
	 * @author cuibinqiang
	 */
	public String findAreaList(String Id);

	/**
	 * 获取会场内容数据
	 * 
	 * @param Id
	 *            会场ID：不传的话，默认主会场
	 * @param isChange
	 *            1.强制切换到主会场;0.当前会场
	 * @return
	 * @author cuibinqiang
	 */
	public String findHallContent(String Id, String isChange,String staticType);

	/**
	 * 获取二级分类商品
	 * 
	 * @param categoryId
	 *            商品分类编号
	 * @param start
	 *            起始页
	 * @param end
	 *            结束页
	 * @return
	 * @author cuibinqiang
	 */
	public String findCategoryList(String categoryId, String start, String end);

	/**
	 * 获得省份列表
	 * 
	 * @return
	 * @author zhanghongwei
	 */
	public List<Province> findProvinceListObj();

	/**
	 * 根据省份id获得市列表
	 * 
	 * @param provinceId
	 *            省份ID
	 * @return
	 * @author zhanghongwei
	 */
	public List<City> findCityListObj(String provinceId);

	/**
	 * 根据市ID得到所在的区域列表
	 * 
	 * @param cityId
	 *            城市ID
	 * @return
	 * @author zhanghongwei
	 */
	public List<Area> findAreaListObj(String cityId);

	/**
	 * 
	 * @Title: findTownList
	 * @Description: 获取区下面的街道
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月10日
	 */
	public String findTownList(String areaId);

	/**
	 * 尚品二级商品入口方法
	 * 
	 * @param categoryId
	 * @param gender
	 * @return
	 * @author cuibinqiang
	 */
	public List<Category> getSecondCategoryList(String categoryId, String gender, String postArea);

	/**
	 * 品牌集合入口
	 * 
	 * @param categoryId
	 * @param gender
	 * @return
	 * @author cuibinqiang
	 */
	public List<Brand> getCategoryBrandList(String categoryId, String gender, String postArea);

	/**
	 * 收藏品牌列表
	 * 
	 * @param userId
	 *            用户id
	 * @param pageIndex
	 * @param pageSize
	 * @return String
	 * @author liling
	 */
	public String collectedBrandlistObj(String userId, String pageIndex, String pageSize);

	/**
	 * 收藏商品列表
	 * 
	 * @param userId
	 *            用户id
	 * @param shopType
	 *            1来自尚品，2来自奥莱
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author liling
	 */
	public String collectedProductListObj(String userId, String shopType, String pageIndex, String pageSize, String postArea);

	/**
	 * 收藏品牌
	 * 
	 * @param userId
	 *            用户id
	 * @param brandId
	 *            品牌id
	 * @return
	 * @author liling
	 */
	public String collectBrandObj(String userId, String brandId);

	/**
	 * 取消收藏品牌
	 * 
	 * @param userId
	 *            用户id
	 * @param brandId
	 *            品牌id
	 * @return
	 * @author liling
	 */
	public String cancelCollectBrandObj(String userId, String brandId);

	/**
	 * 页面无内容时获得推荐
	 * 
	 * @param userId
	 *            用户id
	 * @param type
	 *            1:用于购物车;2:猜你喜欢
	 * @param shopType
	 *            1:尚品;2:奥莱
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public String recProduct(String userId, String type, String shopType, String pageIndex, String pageSize);

	public String recProduct(String userId, String type, String shopType, String pageIndex, String pageSize, String productId);

	/**
	 * 为你推荐
	 * 
	 * @param type
	 * @param userId
	 * @param vuId
	 * @param coord
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	String recProduct(String type, String userId, String vuId, String coord, String ip, String pageIndex, String pageSize);

	/**
	 * 通过手机号查找用户是否存在，可直接创造一个新的用户
	 * 
	 * @param phone
	 *            手机号码
	 * @param type
	 *            是否需要创建新用户（0:只查找是否存在；1：查找是否存在用户，不存在创建一个新的用户）
	 * @return
	 * @author wangfeng
	 */
	public String checkUser(String phone, String type);
	/**
	 * 增加支付以便主站定时查询支付状态
	 * 
	 * @param orderId
	 *           主订单号
	 * @param payInfo
	 *           支付请求串
	 * @param payType
	 * 			  支付请求的网关类型          
	 * @return
	 * @author liling
	 */
	public String addPayLog(String orderId,String payInfo,String payType );

	public String checkUser(String phone, String type, String source);
	
	/**
	 * 判断手机号是否注册过尚品用户
	 * @param phoneNum 手机号
	 * @param channelNo 渠道号
	 * @return
	 */
	public String checkPhoneUser(String phoneNum, String channelNo);
	
	 /**
     * 批量查询用户是否注册
     * @param phoneNums 手机号
     * @return
     */
    public String checkUserList(String phoneNums);
    
    /**
     * 检查手机号是否参加过女神活动
     * @param phoneNum
     * @return
     */
    public String checkPhoneActivity(String phoneNum, String activityNo);
    
    /**
     * 查询手机号是否参与活动
     * @param phoneNum
     * @param activityNo
     * @return
     */
    public String isCheckPhoneActivity(String phoneNum, String activityNo);
    
    /**
     * 登录后在个人中心修改密码或修改礼品卡支付密码
     * @param userId
     * @param type
     * @param nowPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    public String fromModifyPassword(String userId, String type,String nowPassword, String newPassword, String confirmPassword);
    
    String querySearchKeyword();

    public String getShangpinDomain();

}
