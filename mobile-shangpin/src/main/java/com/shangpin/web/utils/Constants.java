package com.shangpin.web.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static final String SESSION_USER = "mshangpin_user";

    /** userID */
    public static final String SESSION_USERID = "mshangpin_userid";

    public static final String DEFAULT_ENCODE = "UTF-8";
    
    public static final String ROOT_PATH = "/";

    public static final String CHANNEL_PARAM_NAME = "ch";

    /** 尚品wap默认的渠道号，芭莎渠道号为：31 */
    public static final String SP_WAP_DEFAULT_CHANNELNO = "4";

    /** 商城首页热门潮流品牌列表 */
    public static final String INDEX_BRAND_START = "1";
    public static final String INDEX_BRAND_END = "12";
    public static final String INDEX_NEW_BRAND_END = "20";
    /** 商品列表开始索引和显示条数 已和大伟确认一次10条 */
    public static final String PRODUCT_LIST_START = "1";
    public static final String PRODUCT_LIST_END = "10";
 
    
    /** 优惠券列表开始索引和显示条数 */
    public static final String COUPON_LIST_START = "1";
    public static final String COUPON_LIST_SIZE = "20";
    public static final String COUPON_PRODUCT_LIST_SIZE = "20";
    /** 优惠券使用状态 */
    public static final String COUPON_TYPE_UNUSED = "0";// 0未使用
    public static final String COUPON_TYPE_USED = "1";// 1已使用
    public static final String COUPON_TYPE_PAST = "3";// 3已过期
    public static final String COUPON_TYPE_ALL = "-1";// -1全部

    /** 男士女士 */
    public static final String MAN = "1";// 男士
    public static final String WOMAN = "0";// 女士

    /** 商品列表图片宽度 */
    public static final String MERCHANDISE_LIST_PICTURE_WIDTH = "318";
    /** 商品列表图片高度 */
    public static final String MERCHANDISE_LIST_PICTURE_HEIGHT = "422";

    /** 获取图片url传入的图片类型 */
    public static final String PICTURE_PRODUCT = "product";
    public static final String PICTURE_SYSTEM = "system";

    /** 商品详情图片宽度 */
    public static final String MERCHANDISE_DETAIL_PICTURE_WIDTH = "450";
    /** 商品详情图片高度 */
    public static final String MERCHANDISE_DETAIL_PICTURE_HEIGHT = "600";

    /** 购物车商品来源 */
    public static final String SITE_NO_SP = "1"; // 尚品
    public static final String SITE_NO_AL = "2"; // 奥莱

    /** sku来源 */
    public static final String SKU_SP = "1";// 尚品
    public static final String SKU_AL = "1";// 奥莱
    public static final String SKU_MB = "1";// 手机

    /** 数据返回成功失败码 */
    public static final String SUCCESS = "0";
    public static final String FAILE = "1";

    /** 用户未登录码 */

    public static final String UNLOGIN = "2";

    /** 购物车图片高度宽度 */
    public static final String CART_PIC_WIDTH = "120";// 宽度
    public static final String CART_PIC_HEIGHT = "160";// 高度

    /** 购物车商品是否参加促销 */
    public static final String PROMOTION_Y = "0";// 参加
    public static final String PROMOTION_N = "1";// 不参加

    /** 商品购买标识 */
    public static final String BUY_LIST = "1";// 列表购买
    public static final String BUY_TOPIC = "2";// 活动购买

    /** VIP编号 */
    public static final String VIP_NO = "0";// 暂时传0

    /** sku动态属性 */
    public static final String SKU_DYNAMIC = "";// web端移动端传空

    /** 品类默认值 */
    public static final String DEFAULT_CATEGORYNO = "0";// 默认品类号

    /** 是否是新用户 */
    public static final String IS_NEW_USER = "1";// 是
    public static final String ISNOT_NEW_USER = "0";// 不是

    /** 是否创建新用户 */
    public static final String CREATE_NEW_USER = "1";// 查找是否存在用户，不存在创建一个新的用户
    public static final String CREATE_NO_USER = "0";// 只查找是否存在

	/** 支付宝支付 */
	public static final String AL_MAIN_PAY = "20";
	public static final String AL_SUB_PAY = "37";
	/** wap海外支付宝支付 */
	public static final String OVERSEAS_AL_MAIN_PAY = "30";
	public static final String OVERSEAS_AL_SUB_PAY = "121";
	/** 银联支付 */
	public static final String YINLIAN_MAIN_PAY = "19";
	public static final String YINLIAN_SUB_PAY = "49";
	/** 微信支付 */
	public static final String WEIXIN_MAIN_PAY = "27";
	public static final String WEIXIN_SUB_PAY = "58";
	/** 微信支付 */
	public static final String SPDB_MAIN_PAY = "15";
	public static final String SPDB_SUB_PAY = "115";
    public static final String COLLECT_PAGE_INDEX = "1";
    public static final String COLLECT_PAGE_SIZE = "20";

    /** 支付方式 */
    public static final String AL_PAY = "0";// 支付宝支付
    public static final String YL_PAY = "1";// 银联支付
    public static final String LP_PAY = "2";// 礼品卡支付
    public static final String YH_PAY = "3";// 优惠券/折扣码支付
    public static final String HD_PAY = "4";// 货到付款
    public static final Object WX_PAY = "5";
	public static final Object SP_PAY = "6";
    /** 立即购物收货地址保存cookie有效时间 */
    public static final int COOKIE_TIME = 1800;
    /**渠道号在cookie中保存的时间*/
    public static final int COOKIE_CHANNEL = 90*24*3600;
    /**浦发银行渠道号在cookie中保存的时间（涉及支付方式的改变）*/
    public static final int SPDB_COOKIE_CHANNEL = 3600;
    /**用户唯一标识在cookie中保存的时间*/
    public static final int COOKIE_UUID = 90*24*3600;
    public static final String SHANGPIN_URL = PropertyUtil.getString("shangpin_url");

    public static final String BRANDURL = "brand/product/list";

    public static final String SUBJECTURL = "subject/product/list";

    public static final String UA_WEIXIN = "micromessenger";

    public static final String APP_COOKIE_NAME_ORIGIN = "origin";
    
    public static final String APP_COOKIE_NAME_UA = "User-Agent";

    /** APP登录未登录状态拦截URL（后面需要跟回调） */
    public static final String APP_NOT_LOGIN_URL = PropertyUtil.getString("app_not_login_url");
    /** APP登录cookie中存放APP登录uid信息的key名称 */
    public static final String APP_COOKIE_NAME_UID = "userid";
    /** APP登录cookie中存放APP登录token信息的key名称 */
    public static final String APP_COOKIE_NAME_TOKEN = "token";
    /** APP登录cookie中存放APP登录imei信息的key名称 */
    public static final String APP_COOKIE_NAME_IMEI = "imei";

    public static final String APP_NAME_CALLBACK_URL = "callback";

    public static final String WX_APP_ID = PropertyUtil.getString("weixin_app_id");

    public static final String WX_SECRET = PropertyUtil.getString("weixin_secret");

    public static final String GRANT_TYPE = "authorization_code";

    public static final String WX_OAUTH_URL = PropertyUtil.getString("weixin_oauth_url");

    public static final String WX_MODIFY_BIND_NAME = "modify";

    public static final String WX_ID_NAME = "wxId";

    public static final String APP_TOKEN_KEY = PropertyUtil.getString("app_token_key");

    public static final String PAY_AMOUNT = String.valueOf(Integer.MAX_VALUE);
    /** 推荐商品 */
    public static final String INDEX_RECPRODUCT_START = "1";
    public static final String INDEX_RECPRODUCT_END = "12";

    public static final String STRING_1 = "1";

    public static final String YL_PAY_DESCRIPTION = "尚品网-轻奢的选择";

    public static final String SLAE_TODAY_TYPE = "1";

    public static final String SLAE_PAGEINDEX = "1";

    public static final String SLAE_PAGESIZE = "100";

    public static final String SLAE_YESTODAY_TYPE = "2";

    public static final String SLAE_LAST_TYPE = "3";

    public static final String ENTRANCE_TYPE = "1";

    public static final String INDEX_ENTRANCE_START = "1";

    public static final String INDEX_ENTRANCE_END = "10";
    /**提交海外购标识*/
    public static final String REGION_OVERSEAS="2";

    public static final String FASHION_START = "1";

    public static final String FASHION_END = "200";

    public static final String GUESSLIKE_RECPRODUCT_TYPE = "2";

    public static final String SHANGPIN_SHOPTYPE = "1";

    public static final String GUESSLIKE_RECPRODUCT_START = "1";

    public static final String GUESSLIKE_RECPRODUCT_END = "10";

    public static final Object APP_DETAIL_BASIC_TYPE = "1";

    public static final Object APP_DETAIL_SIZE_TYPE = "2";

    public static final Object APP_DETAIL_TEMPLATE_TYPE = "3";
    public static final String APP_DETAIL_GIFTCARD_TYPE = "4";
    public static final String APP_DETAIL_GIFTCARD_E_TYPE = "5";

    public static final String PRODUCT_DETAIL_TEMPLATE_TYPE = "5";

    public static final Object PRODUCT_TEMPLATE_AFTERSALE = "3";
	
	public static final String COUPON_INFO = WeixinPropertyUtil.getString("couponInfo");
	
	public static final String SUBJECT_LIST = WeixinPropertyUtil.getString("subjectList");
	
	public static final String SHARE_TITLE = WeixinPropertyUtil.getString("packet_share_title");
	
	public static final String SHARE_DESC = WeixinPropertyUtil.getString("packet_share_desc");

	public static final String SPDB_GATEWAY = "SPDB";
    public static final Object PRODUCT_OVERSEAS_TYPE = "2";

	public static final String SPDB_CURRENCY = "CNY";

	public static final String SPDB_EXT = "transType:1";

	public static final String SPDB_TIMEOUT = "1";

	public static final String SPDB_CALLBACK_URL = PropertyUtil.getString("spdbpay.callback.url");

	public static final Object SUCCESS_MSG = "SUCCESS";
	public static final String PAY_DESCRIPTION = "尚品网-轻奢的选择";

    
    //尚品微信红包金额
    public static final double SHANGPIN_PACKET_MONEY = 5.0;

    public static final String EXCLUSIVE_RECOMMEND_PAGESIZE = "10";

    public static final String EXCLUSIVE_RECOMMEND_PAGEINDEX = "1";

    public static final String INDEX_FASHION_TYPE = "0";

    public static final String UVID_COOKIE_NAME = "quark_uv";

    public static final String QUERY_CATEGORY_INDEX_PARENT_ID = "0";

    public static final String QUERY_CATEGORY_INDEX_TYPE = "1";

    public static final String CATEGORY_CUSTOM_BRAND_NUM = "16";

    public static final String INDEX_CUSTOM_BRAND_NUM = "8";

    public static final String INDEX_REC_PRODUCT_TYPE = "3";

    /**礼品卡购买记录标识*/
    public static final String RECORD_TYPE_BUY="1";
    /**礼品卡充值标识*/
    public static final String RECORD_TYPE_RECHARGE="2";
    /**礼品卡消费标识*/
    public static final String RECORD_TYPE_CONSUME="3";
    /**当前第一页*/
    public static final String PAGE_NO_1_STR="1";
    /**每页显示的条数*/
    public static final Integer PAGE_SIZE_20=20;
    /**每页显示的条数*/
    public static final String PAGE_SIZE_20_STR="20";
    /**电子卡标识*/
    public static final String GIFTCARD_TYPE_E="1";
    /**实物卡标识*/
    public static final String GIFTCARD_TYPE_ENTITY="2";
    /**fashion run 允许报名人数*/
    public static final String RUN_COUNT = RunPropertyUtil.getString("num");
    /**fashion run 订单状态*/
    public static final String FASHION_RUN_WAIT_PAY ="001";
    public static final String FASHION_RUN_PAID ="002";
    public static final String FASHION_RUN_WAIT_CONFIRM ="003";
    public static final String FASHION_RUN_HAS_CONFIRM ="004";
    public static final String FASHION_RUN_DELIVERED ="005";

	public static final String FASHION_REGISTE_SOURCE = "40";
	public static final String RUN_PRODUCT_LIST_END= "4";
	public static final String OVERS_PRODUCT_LIST_END="20";
	
	//女神新衣注册用户来源
	public static final String GODDES_REGISTE_SOURCE = "41";
	
	//明星领红包注册用户来源
	public static final String STAR_PACKET_SOURCE = "42";
	
	//CEO红包注册用户来源
    public static final String CEO_PACKET_SOURCE = "43";
	
	public static final String RUN_APP_VER = "5.8.0";

	public static final String CHANNEL_END_SIZE = "4";

	public static final String FASHION_RUN_PAY_DESC ="FashionRun报名费";

	public static final String RUN_CHANNEL = "RunChannel";

	public static final String FASHION_FACE_URL = "/fashionrun/face";

	public static final String FASHION_RUN_NAME = "fashionrun";

	public static final String FASHION_RUN_PWD = "girl";

	public static final String RUN_GALLEY_TYPE = "AppSwitchPicture";

	public static final String RUN_BRAND_HOT_TYPE = "AppBestSelling";

	public static final String RUN_INDEX_CHANNEL_END = "3";
	
	public static final String OVERSEAS_GALLEY_TYPE = "AppOverseasChannelSwitchPicture";
	
	public static final Object APP_DETAIL_SHOWPICS_TYPE = "6";

	public static final String INDEX_FASHION_START = "1";

	public static final String INDEX_FASHION_END = "12";

	public static final String EP_APP_VER = "2.9.0";

	public static final String LOGISTIC_APP_VER = "2.9.0";
	public static final String WEIXINWAP_MAIN_PAY = "27";
	public static final String WEIXINWAP_SUB_PAY = "117";
	
	//现金类型
	public  static String RED_PACK_CASH = "1";
	public  static String RED_PACK_COUPON= "2";

	public static final Object CMBC_PAY = "7";

	public static final String CMBCWAP_MAIN_PAY = "15";

	public static final String CMBCWAP_SUB_PAY = "118";

	public static final String WEIXIN_PUB_SEA_MAIN_PAY = "32";

	public static final String WEIXIN_PUB_SEA_SUB_PAY = "111";
	
	/**订单状态*/
	public static final String ORDER_STATUS_CANCEL = "0";//取消
	public static final String ORDER_STATUS_PAY_WAIT = "11";//待支付
	public static final String ORDER_STATUS_PAY_ALREADY = "13";//收款已确认，待发货
	public static final String ORDER_STATUS_DELIVER = "14";//发货中
	public static final String ORDER_STATUS_RECEIVE = "16";//已发货,待收货
	public static final String ORDER_STATUS_OVER = "99";//交易全部完成
	
    /**尚品客信息*/
	public static final String SWITCH_PAY_NAME = "SunnyAdmin@pay.com";
	public static final Object SWITCH_PAY_PWD = "sunny!@#$%";
	public static final String THRID_TOKEN="mshangpin_thridtoken_";
	public static final String THRID_TOKEN_SpuNo="mshangpin_thridtoken_spuNo";//spu的key
	public static final String THRID_TOKEN_TopicId="mshangpin_thridtoken_topicId";//活动id的key
	public static final String THRID_TOKEN_ChannelNo="mshangpin_thridtoken_channelNo";//渠道号的key
	public static final String THRID_TOKEN_ChannelId="mshangpin_thridtoken_channelId";//尚品客id的key	
	
	/** 轮播图的位置*/
	public static final String GALLERY_DAILY_COUPON = "tiantian";// 天天抢券
	//public static final String SWITCH_PAY_PWD = "sunny!@#";
	
	//第三方渠道屏蔽尚品app的下载头
	public static final String THRID_TOPIC_LIST ="TCL,A001";//A001为尚品客来源
	/** 网盟相关cookie的权重  */
	public static final String[] THRID_COOKIE_LEVELS ={
														"{\"source\":\"baidupinzhuan\",\"expires\":\"21\", \"level\": \"100\"}",//百度品专
														"{\"source\":\"baidusem\",\"expires\":\"21\", \"level\": \"100\"}",//百度SEM搜索
														"{\"source\":\"baidudsp\",\"expires\":\"21\", \"level\": \"100\"}",//百度DSP
														"{\"source\":\"gdt\",\"expires\":\"21\", \"level\": \"100\"}",//广点通
														"{\"source\":\"zht\",\"expires\":\"21\", \"level\": \"100\"}",//智慧推
														"{\"source\":\"toutiao\",\"expires\":\"21\", \"level\": \"100\"}",//今日头条
														"{\"source\":\"weixin\",\"expires\":\"21\", \"level\": \"100\"}",//微信
														"{\"source\":\"weibo\",\"expires\":\"21\", \"level\": \"100\"}",//微博
														"{\"source\":\"bd\",\"expires\":\"21\", \"level\": \"100\"}",//BD合作
														"{\"source\":\"lkt\",\"expires\":\"30\", \"level\": \"100\"}",//领克特
														"{\"source\":\"yqf\",\"expires\":\"30\", \"level\": \"100\"}",
														"{\"source\":\"yb\",\"expires\":\"30\", \"level\": \"100\"}",
														"{\"source\":\"fst\",\"expires\":\"30\", \"level\": \"100\"}",
														"{\"source\":\"iqiyi\",\"expires\":\"30\", \"level\": \"100\"}",
														"{\"source\":\"lktwap\",\"expires\":\"30\", \"level\": \"100\"}",//领克特wap
														"{\"source\":\"yqfwap\",\"expires\":\"30\", \"level\": \"100\"}",
														};
	
}
