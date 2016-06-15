package com.aolai.web.utils;

public class Constants {

	public static final String SESSION_USER = "maolai_user";

	public static final String SESSION_USERID = "maolai_uid";

	public static final String DEFAULT_ENCODE = "UTF-8";

	public static final String CHANNEL_PARAM_NAME = "ch";

	public static final String PAY_AMOUNT = String.valueOf(Integer.MAX_VALUE);
	/** 女士 */
	public static final String GENDER_WOMEN = "0";
	/** 男士 */
	public static final String GENDER_MEN = "1";
	/** 预售日历 */
	public static final String GENDER_PRE = "2";
	/** 当前页码 */
	public static final int PAGE_INDEX = 1;
	/** 每页条数 */
	public static final int PAGE_SIZE = 20;

	/** 尚品wap默认的渠道号，芭莎渠道号为：31 */
	public static final String SP_WAP_DEFAULT_CHANNELNO = "4";
	/** 首页活动图片宽度 */
	public static final String ACTIVITIES_PICTURE_WIDTH = "290";
	/** 首页活动图片高度 */
	public static final String ACTIVITIES_PICTURE_HEIGHT = "216";

	/** 购物车列表图片宽度 */
	public static final String CART_LIST_WIDTH = "120";
	/** 购物车列表图片宽度 */
	public static final String CART_LIST_HEIGHT = "160";
	/** 购物车类型:1：尚品 2：奥莱 */
	public static final String SHOP_TYPE_AOLAI = "2";
	/** 购物车活动商品是否参加促销：0为促销，1为不参加促销 */
	public static final String IS_PROMOTION_NO = "1";
	public static final String IS_PROMOTION_YES = "0";
	/** 处理成功标识 */
	public static final String SUCCESS = "0";
	/** 处理失败标识 */
	public static final String FAILURE = "1";
	// 支付相关
	/** 支付宝主支付方式 */
	public static final String ALIPAY_MAIN_WAY = "20";
	/** 支付宝子支付方式 */
	public static final String ALIPAY_SUB_WAY = "37";
	/** 银联主支付方式 */
	public static final String UNIONPAY_MAIN_WAY = "19";
	/** 银联子支付方式 */
	public static final String UNIONPAY_SUB_WAY = "49";
	// 读取配置文件支付参数的值
	/** 支付宝同步回调地址 */
	public static final String ALIPAY_CALLBACK_URL = PropertyUtil.getStrValue("alipay.callback.url");
	/** 支付宝异步回调地址 */
	public static final String ALIPAY_NOTIFY_URL = PropertyUtil.getStrValue("alipay.notify.url");
	/** 支付宝返回商户地址 */
	public static final String ALIPAY_MERCHANT_URL = PropertyUtil.getStrValue("alipay.merchant.url");
	/** 银联同步回调地址 */
	public static final String UNIONPAY_CALLBACK_URL = PropertyUtil.getStrValue("unionpay.callback.url");
	/** 银联异步回调地址 */
	public static final String UNIONPAY_NOTIFY_URL = PropertyUtil.getStrValue("unionpay.notify.url");

	/** 奥莱默认渠道号 */
	public static final String AOLAI_WAP_DEFAULT_CHANNELNO = "3";
	/** 渠道号的Cookie名称 增加此常量以免与尚品渠道cookie名混淆 */
	public static final String COOKIE_CHANNEL_PARAM_NAME = "ch";
	/** 浏览器的用户代理 */
	public static final String USER_AGENT = "User-Agent";
	/** 注册时是email标识 */
	public static final String TYPE_EMAIL = "0";
	/** 产品号10000 */
	public static final String PRODUCT_10000 = "10000";
	/** 当前第一页 */
	public static final String PAGE_NO_1_STR = "1";
	/** 每页显示10条 */
	public static final String PAGE_SIZE_10_STR = "10";
	/** 每页显示10条 */
	public static final int PAGE_SIZE_10 = 10;
	/** 待支付 */
	public static final String STATUS_WAIT_PAY = "2";
	/** 待收货 */
	public static final String STATUS_WAIT_TAKE = "3";
	/** 全部订单 */
	public static final String STATUS_ALL = "1";
	/** 未使用 */
	public static final String TYPE_UNUSED = "0";
	/** 已使用 */
	public static final String TYPE_USED = "1";
	/** 已过期 */
	public static final String TYPE_EXPIRED = "3";
	/** 全部 */
	public static final String TYPE_ALL = "-1";
	/** 发送优惠券拼接字段coupon: */
	public static final String COUPON = "coupon:";

	/** APP登录未登录状态拦截URL（后面需要跟回调） */
	public static final String APP_NOT_LOGIN_URL = PropertyUtil.getString("app.notLogin.url");

	/** APP登录cookie中存放APP登录uid信息的key名称 */
	public static final String APP_COOKIE_NAME_UID = "userid";
	/** APP登录cookie中存放APP登录token信息的key名称 */
	public static final String APP_COOKIE_NAME_TOKEN = "token";
	/** APP登录cookie中存放APP登录imei信息的key名称 */
	public static final String APP_COOKIE_NAME_IMEI = "imei";

	public static final String APP_NAME_CALLBACK_URL = "callback";

	public static final String APP_TOKEN_KEY = PropertyUtil.getString("app_token_key");

	public static final String APP_COOKIE_NAME_ORIGIN = "origin";

	public static final Object AOLAI_URL = PropertyUtil.getString("aolai.url");
	/** 是否是新用户 */
    public static final String IS_NEW_USER = "1";// 是
    public static final String ISNOT_NEW_USER = "0";// 不是
    public static final Object SUCCESS_MSG = "SUCCESS";
	/** 支付宝支付 */
	public static final String AL_MAIN_PAY = "20";
	public static final String AL_SUB_PAY = "37";
	/** wap海外支付宝支付 */
	public static final String OVERSEAS_AL_MAIN_PAY = "30";
	public static final String OVERSEAS_AL_SUB_PAY = "108";
	/** 银联支付 */
	public static final String YINLIAN_MAIN_PAY = "19";
	public static final String YINLIAN_SUB_PAY = "49";

	public static final String COMMON_CALLBACK_URL = PropertyUtil.getString("common.callback.url");
}
