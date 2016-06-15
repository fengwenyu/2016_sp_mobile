package com.shangpin.mobileAolai.common.util;


public class Constants {
    /**
     * 尚品和奥莱两个产品调用的接口地址域名分别为 尚品xxx.shangpin.com 奥莱xxx.aolai.com
     * 尚品和奥莱两个产品调用的接口地址，相对路径以接口定义为准，沿用/aolai/或/shangpin/
     * 如尚品购物袋xxx.shangpin.com/aolai/selectshoppingcart
     * 如奥莱优惠券xxx.aolai.com/shangpin/selectCoupon
     */
    public static final String PAY_AMOUNT = String.valueOf(Integer.MAX_VALUE); 
    public static final String BASE_URL = ProReader.getInstance().getProperty("server_base_url");// 主站测试URL
    public static final String BASE_URL_AL_SP = ProReader.getInstance().getProperty("server_base_url_al_sp");// 主站测试URL
    public static final String BASE_URL_SP_AL = ProReader.getInstance().getProperty("server_base_url_sp_al");// 主站测试URL
    public static final String OLD_BASE_URL = ProReader.getInstance().getProperty("server_old_base_url");// 主站测试URL
    public static final String BASE_NEWAPI_URL_SP = ProReader.getInstance().getProperty("server_base_newapi_url_sp");
    public static final String BASE_SP_URL = ProReader.getInstance().getProperty("server_base_sp_url");// 主站测试URL
    public static final String SP_BASE_URL = ProReader.getInstance().getProperty("server_sp_base_url");
    
    public static final String PAYSP_URL = ProReader.getInstance().getProperty("pay_sp");
    public static final String AOLAI_URL = ProReader.getInstance().getProperty("aolaiurl");
    public static final String WAL_URL = ProReader.getInstance().getProperty("wal_url");
    public static final String TRADE_URL = ProReader.getInstance().getProperty("trade");
    public static final String MAL_URL = ProReader.getInstance().getProperty("mal_url");
    public static final String SHANGPIN_URL = ProReader.getInstance().getProperty("shangpinurl");
    public static final String NUMBER_URL = ProReader.getInstance().getProperty("number");
    public static final String SECRET_KEY_URL = ProReader.getInstance().getProperty("secret_key");
    public static final String SUCCESS = "0";// 成功标识符
    public static final String SP_WAP_DEFAULT_CHANNELNO = "4";

    public static String SERVER_IP;// 网站服务器IP
    static {
        try {
            java.net.InetAddress addr = java.net.InetAddress.getLocalHost();
            SERVER_IP = addr.getHostAddress().toString();// 获得本机IP
        } catch (Exception e) {
            SERVER_IP = ProReader.getInstance().getProperty("server_ip");// 网站服务器IP
            e.printStackTrace();
        }
    }

    /** 银联环境标识符true代表测试环境，false代表生产环境 */
    public static final boolean UNION_PAYMENT = false;
    /** 首页活动图片宽度 */
    public static final String ACTIVITIES_PICTURE_WIDTH = "290";
    /** 首页活动图片高度 */
    public static final String ACTIVITIES_PICTURE_HEIGHT = "216";
    /** 首页轮播广告图片宽度 */
    public static final String TOPICCAROUSEL_PICTURE_WIDTH = "640";// 600
    /** 首页轮播广告图片高度 */
    public static final String TOPICCAROUSEL_PICTURE_HEIGHT = "320";// 260
    /** 小图片宽度 */
    public static final String SAMLL_PICTURE_WIDTH = "120";
    /** 小图片高度 */
    public static final String SAMLL_PICTURE_HEIGHT = "160";
    /** 商品详情图片宽度 */
    public static final String MERCHANDISE_DETAIL_PICTURE_WIDTH = "492";
    /** 商品详情图片高度 */
    public static final String MERCHANDISE_DETAIL_PICTURE_HEIGHT = "600";
    /** 商品列表图片宽度 */
    public static final String MERCHANDISE_LIST_PICTURE_WIDTH = "318";
    /** 商品列表图片高度 */
    public static final String MERCHANDISE_LIST_PICTURE_HEIGHT = "422";
    /** 渠道号 */
    public static final String CHANNEL_PARAM_NAME = "ch";
    /** 渠道号的Cookie名称 增加此常量以免与尚品渠道cookie名混淆 */
    public static final String COOKIE_CHANNEL_PARAM_NAME = "ch";
    /** 与统计server交互时约定的公钥 */
    public static final String SP_BASE_MD5_KEY = "vr5p32jb0ahagdxksgr59dhqe7hbclxk";
    /** cookie中存放合作信息的key名称 */
    public static final String SP_COOKIE_NAME_COOPERATION = "cooperation";
    /** cookie中存放推广信息的key名称 （一级） */
    public static final String SP_COOKIE_NAME_PROMOTION1ST = "promotion1st";
    /** cookie中存放推广信息的key名称 （二级） */
    public static final String SP_COOKIE_NAME_PROMOTION2ND = "promotion2nd";
    /** cookie中存放推广信息的key名称 （三级） */
    public static final String SP_COOKIE_NAME_PROMOTION3TH = "promotion3th";
    /** cookie值分隔符 */
    public static final char SP_COOKIE_SEPARATOR_CHAR = '&';
    /** 数据统计URL */
    public static final String SP_COUNT_URL = "http://data.aolai.com/"; // 数据统计线上URL
    // public static final String SP_COUNT_URL = "http://data20.aolai.com/";
    // //数据统计测试URL
    public static final String AOLAI_WAP_DEFAULT_CHANNELNO = "3";
    public static final String AOLAI_WEB_DEFAULT_CHANNELNO = "5";

    /** APP登录cookie中存放来源信息的key名称 */
    public static final String APP_COOKIE_NAME_ORIGIN = "origin";
    /** APP登录cookie中存放APP登录uid信息的key名称 */
    public static final String APP_COOKIE_NAME_UID = "userid";
    /** APP登录cookie中存放APP登录token信息的key名称 */
    public static final String APP_COOKIE_NAME_TOKEN = "sessionid";
    /** APP登录cookie中存放APP登录imei信息的key名称 */
    public static final String APP_COOKIE_NAME_IMEI = "imei";
    /** APP登录cookie中存放APP登录imei信息的key名称 */
    public static final String APP_COOKIE_NAME_VER = "ver";
    /** APP登录回调地址信息的key名称 */
    public static final String APP_NAME_CALLBACK_URL = "callback";
    /** APP登录未登录状态拦截URL（后面需要跟回调） */
    public static final String APP_NOT_LOGIN_URL = ProReader.getInstance().getProperty("app_not_login_url");
    /** APP登录未登录状态拦截安卓4.0兼容URL（后面需要跟回调） */
    public static final String APP_ANDROID_NOT_LOGIN_URL = ProReader.getInstance().getProperty("app_android_not_login_url");

    /** APP登录回调地址信息的key名称 */
    public static final String APP_CALLBACK_LOGIN = ProReader.getInstance().getProperty("app_callback_login");

    /** API服务服务器地址 */
    public static final String API_SERVER_BASE_URL = ProReader.getInstance().getProperty("apiUrl");
  
    public static final String ALIPAY_CALLBACK_URL = ProReader.getInstance().getProperty("alipay_callback_url");
	public static final String ALIPAY_NOTIFY_URL =  ProReader.getInstance().getProperty("alipay_notify_url");
	public static final String UNIONPAY_CALLBACK_URL = ProReader.getInstance().getProperty("unionpay_callback_url");
	public static final String UNIONPAY_NOTIFY_URL =  ProReader.getInstance().getProperty("unionpay_notify_url");
}
