package com.shangpin.biz.utils;

/**
 * 常量
 * 
 * @author Administrator
 *
 */
public class Constants {

    /** 奥莱二级商品列表图片宽度 */
    public static final String GOODS_LIST_PICTURE_WIDTH = "318";
    /** 奥莱二级商品列表图片高度 */
    public static final String GOODS_LIST_PICTURE_HEIGHT = "422";
    /** 奥莱详情页图片宽度 */
    public static final String GOODS_DETAIL_PICTURE_WIDTH = "492";
    /** 奥莱详情页图片高度 */
    public static final String GOODS_DETAIL_PICTURE_HEIGHT = "600";
    /** 奥莱模块 */
    public static final String SHOP_TYPE_AOLAI = "2";
    /** 尚品 */
    public static final String SHOP_TYPE_SHANGPIN = "1";
    /** 是否加载 1表示加载 */
    public static final String HAVE_MORE_YES = "1";
    /** 是否加载 0表示不加载 */
    public static final String HAVE_MORE_NO = "0";
    /**处理成功标识*/
    public static final String SUCCESS = "0";
    /**处理失敗标识*/
    public static final String FAILE = "1";
    /**专题商品列表进入标识*/
    public static final String TYPE_FLAG_TOPIC = "0";
    /**活动商品列表进入标识*/
    public static final String TYPE_FLAG_ACTIVITY = "1";
	/** 商品列表图片宽度 */
	public static final String MERCHANDISE_LIST_PICTURE_WIDTH = "318";
	/** 商品列表图片高度 */
	public static final String MERCHANDISE_LIST_PICTURE_HEIGHT = "422";
	
	
	public static final String ERROR_LOCKSKU_MISSPARAMETER = "0";// 请求参数不对
	public static final String ERROR_LOCKSKU_MISSPARAMETER_PROMPT = "请求参数不能为空";// 请求参数不对
	
	public static final String LOCKSKU_SUCCESS = "1";// 正确
	
	public static final String ERROR_LOCKSKU_NETWORK = "2";
	public static final String ERROR_LOCKSKU_NETWORK_PROMPT = "网络连接错误";// 网络连接错误
	
	public static final String ERROR_LOCKSKU_NOTSKU = "3";
	public static final String ERROR_LOCKSKU_NOTSKU_PROMPT = "未找到SKU对应关系";
	
	public static final String ERROR_LOCKSKU_UNDERSTOCK = "4";
	public static final String ERROR_LOCKSKU_UNDERSTOCK_PROMPT = "库存不足";
	
	public static final String ERROR_LOCKSKU_PUSHFAIL = "5";
	public static final String ERROR_LOCKSKU_PUSHFAIL_PROMPT = "推送失败";
	
	public static final String ORDER_LOCKSKU_ERROR = "3009";
	public static final String ORDER_LOCKSKU_ERROR_PROMPT = "商品已售罄，无法购买，请选购其他商品。";
	public static final String ORDER_LOCKSKU_FAIL = "3010";
	public static final String ORDER_LOCKSKU_FAIL_PROMPT = "商品已售罄，无法购买，请选购其他商品。";
	public static final String STOCKSYNCZERO_SUCCESS = "1";
	
	public static final String BRAND_STORY_URL = PropertyUtil.getString("brand_story_url");
	public static final String SIZE_TRY_URL = PropertyUtil.getString("size_try_url");
	public static final String BRAND_STYLE_URL = PropertyUtil.getString("brand_style_url");
	public static final String BASIC_INFO_URL = PropertyUtil.getString("basic_info_url");
	public static final String BASIC_INFO_GIFTCARD_ENTITY_URL = PropertyUtil.getString("basic_info_giftcard_entity_url");
	public static final String BASIC_INFO_GIFTCARD_ELECTRON_URL = PropertyUtil.getString("basic_info_giftcard_electron_url");
	public static final String DETAIL_TEMPLATE_URL = PropertyUtil.getString("detail_template_url");
    public static final String DETAIL_AFTERSALE_URL = PropertyUtil.getString("detail_afterSale_url");
    
	public static final String FASHION_WAP = PropertyUtil.getString("fashion_wap");
	public static final String COUNTRYPIC_URL = PropertyUtil.getString("countryPic_url");
	/** 海外商品直发标 */
	public static final String IFC_YDL_URL = PropertyUtil.getString("ydl_url");
	public static final String IFC_XJP_URL = PropertyUtil.getString("xjp_url");
	public static final String IFC_KHJ_URL = PropertyUtil.getString("khj_url");
	public static final String MG_URL = PropertyUtil.getString("mg_url");
	public static final String HG_URL = PropertyUtil.getString("hg_url");
	public static final String XG_URL = PropertyUtil.getString("xg_url");
	public static final String OZ_URL = PropertyUtil.getString("oz_url");
    public static final String DOUBLE_ELE_URL = PropertyUtil.getString("double_ele_url");
    /** 活动预热-变价-开启各时间点 */
    public static final String PRICE_ACTIVITY = PropertyUtil.getString("price_activity");
    public static final String PRICE_COMPARE = PropertyUtil.getString("price_compare");
    public static final String ACTIVITY_READY_START = PropertyUtil.getString("activity_ready_start");
    public static final String ACTIVITY_READY_END = PropertyUtil.getString("activity_ready_end");
    public static final String ACTIVITY_OPEN_START = PropertyUtil.getString("activity_open_start");
    public static final String ACTIVITY_OPEN_END = PropertyUtil.getString("activity_open_end");
    public static final String ACTIVITY_SALES_START = PropertyUtil.getString("activity_sales_start");
    public static final String ACTIVITY_SALES_END = PropertyUtil.getString("activity_sales_end");
    /** wap(m站) */
    
    public static final String WAP_URL = PropertyUtil.getString("wap_url");
    
    public static final String ERROR_VERIFY_MSG= "请求参数不合法";// 请求参数不全msg
	public static final String ERROR_VERIFY_CODE="008";
	
	public static final String PAY_OVERSEA_PRICE_LINE="2000";//海外支付方式的价格线/**返回客户端消息跳转的通用规则*/
	public static final String NOTICE_RECONTENT_TYPE_WAP = "1";//wap页面
	public static final String NOTICE_RECONTENT_TYPE_ACTIVITY = "2";//活动
	public static final String NOTICE_RECONTENT_TYPE_DETAIL = "3";//详情
	public static final String NOTICE_RECONTENT_TYPE_TOPIC = "4";//专题	/**消息ICON*/
	public static final String MESSAGE_ICON_RETURN_IOS = "http://pic6.shangpin.com/group1/M00/A4/4C/rBQKaVcdzbiAa0NzAAAHVDKKr1U834.png";//IOS 退货ICON
	public static final String MESSAGE_ICON_RETURN_ANDROID = "http://pic3.shangpin.com/group1/M00/A4/4C/rBQKaVcdzQKAJB4lAAAIJm_jYac789.png";//ANDROID 退货ICON
	public static final String MESSAGE_ICON_PUSH_IOS = "http://pic6.shangpin.com/group1/M00/A4/4C/rBQKaVcdzbiAa0NzAAAHVDKKr1U834.png";//IOS push icon
	public static final String MESSAGE_ICON_PUSH_ANDORID = "http://pic3.shangpin.com/group1/M00/A4/4C/rBQKaVcdzQKAJB4lAAAIJm_jYac789.png";//android push ICON
	public static final String MESSAGE_ICON_ORDER_ISO = "http://pic4.shangpin.com/group1/M00/A4/4C/rBQKaVcdzbyAbLOBAAAKssm2Evo132.png";//ios 订单ICON
	public static final String MESSAGE_ICON_ORDER_ANDROID = "http://pic4.shangpin.com/group1/M00/A4/4C/rBQKaVcdzQaAGzC6AAAMD9uLOsU911.png";//android 订单ICON
	
	
	/**消息状态*/
	public static final String NOTICE_MESSAGE_UN_READ = "0";//未读
	public static final String NOTICE_MESSAGE_READ = "1";//已读
	
	/**消息类型*/
	public static final String NOTICE_TYPE_ORDER= "10_11";//订单消息
	public static final String NOTICE_TYPE_ORDER_FINISH = "10";//订单完成
	public static final String NOTICE_TYPE_ORDER_DELIVER = "11";//订单发货
	public static final String NOTICE_TYPE_ORDER_RETURN = "12_13";//订单退货
	public static final String NOTICE_TYPE_ORDER_RETURN_SUCCESS = "12";//订单退货成功
	public static final String NOTICE_TYPE_ORDER_RETURN_FAIL = "13";//订单退货失败
	/***redis的key */
	public static final String IS_USE_COMBINE_PAY="isUseCombinePay";
	
	/** 网盟相关cookie的键  */
	public static final String THRID_COOKIE_SOURCE="WM_Source";
	public static final String THRID_COOKIE_CAMPAIGN="WM_Campaign";
	public static final String THRID_COOKIE_PARAM="WM_Param";
	public static final String THRID_COOKIE_CHANNEL_TYPE="WM_channelType";
	
	/*** 2016-520活动的键 */
	public static final String FREEBIE_520_SHARE_TOPICID="60512985";//线上活动号
	public static final String FREEBIE_520_SHARE_PRO_SIZE="10";//换购的个数
	public static final String FREEBIE_520_SHARE_M_LIST_URL="order/freebie/share";//列表
	public static final String FREEBIE_520_SHARE_M_DETAIL_URL="order/freebie";//详情
	public static final String FREEBIE_520_SHARE_M_TITLE="送出同款撞衫";
	
	public static final String FREEBIE_520_SHARE_BUTTON_NAME="送出同款撞衫";
	public static final String FREEBIE_520_SHARE_DESC="和我一起撞衫吧，免费送你同款TOPSHOP";
	public static final String FREEBIE_520_SHARE_PIC="http://pic5.shangpin.com/e/u/16/05/17/20160517180822364983-10-10.jpg";
}

