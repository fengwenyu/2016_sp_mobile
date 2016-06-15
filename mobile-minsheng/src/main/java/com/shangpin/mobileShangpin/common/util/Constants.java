package com.shangpin.mobileShangpin.common.util;

public class Constants {
	public static final String PAY_AMOUNT = String.valueOf(Integer.MAX_VALUE);	
	public static final String SEARCH_BASE_URL = ProReader.getInstance().getProperty("searchBaseUrl");// 搜索URl
    public static final String BASE_URL = ProReader.getInstance().getProperty("baseUrl");// 主站接口测试URL
    public static final String BASE_SP_URL = ProReader.getInstance().getProperty("baseSpUrl");// 主站接口测试URL
    public static final String BASE_TRADE_URL = ProReader.getInstance().getProperty("baseTradeUrl");// 网站服务器URL
	 
	public static final String SUCCESS = "0";// 成功标识符
	/** 银联环境标识符true代表测试环境，false代表生产环境 */
	public static final boolean UNION_PAYMENT = true;
	/** 民生环境标识符true代表测试环境，false代表生产环境 */
	public static final boolean MINSHENG_PAYMENT = false;
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
	public static final String CHANNEL_PARAM_NAME = "ch";
	/** 尚品wap默认的渠道号，芭莎渠道号为：31 */
	public static final String SP_WAP_DEFAULT_CHANNELNO = "4";
	public static final String SP_WEIXIN_CHANNELNO = "36";
	/** 专题id */
	public static final String SP_TOPIC_ID = "topicid";
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
    public static final String SP_COUNT_URL = ProReader.getInstance().getProperty("dateshangpincom");
}
