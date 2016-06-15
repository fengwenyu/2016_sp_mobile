package com.shangpin.wireless.api.domain;

import com.shangpin.wireless.api.util.ProReader;

public class Constants {
	
	public static final String VERSION ="4.0.0";
	/** 支付环境标识，false生产环境，true为测试环境 */
	public static final boolean PAYMENT_TEST = Boolean.parseBoolean(ProReader.getInstance().getProperty("payment_test"));//
	/** 微信环境标识符true代表测试环境，false代表生产环境 */
	public static final boolean WEIXIN_PAYMENT = Boolean.parseBoolean(ProReader.getInstance().getProperty("weixin_payment"));
	public static final String PAY_AMOUNT = String.valueOf(Integer.MAX_VALUE); // 这个需要按照银行返回的金额
	public static final String BASE_URL = ProReader.getInstance().getProperty("server_base_url_al_al");// 网站服务器URL
	public static final String BASE_URL_SP = ProReader.getInstance().getProperty("server_base_url_sp_al");// 网站服务器URL
	public static final String SP_BASE_URL = ProReader.getInstance().getProperty("server_base_url_sp_sp");// 网站服务器URL
	
	public static final String BASE_SEARCH_URL = ProReader.getInstance().getProperty("server_base_search_url");// 搜索服务器URL
	
	public static final String BASE_M_SHANGPIN_URL = ProReader.getInstance().getProperty("server_m_shangpin_url");// 尚品M站测试
	
	public static final String BASE_M_AOLAI_URL = ProReader.getInstance().getProperty("server_m_aolai_url");// 奥莱M站测试
	//	使用新的变量名称
	public static final String BASE_TRADE_URL = ProReader.getInstance().getProperty("server_base_url_trade");// 新订单管理
	public static final String BASE_URL_AL_AL = ProReader.getInstance().getProperty("server_base_url_al_al");// 网站服务器URL
	public static final String BASE_URL_AL_SP = ProReader.getInstance().getProperty("server_base_url_al_sp");// 网站服务器URL
	public static final String BASE_URL_SP_AL = ProReader.getInstance().getProperty("server_base_url_sp_al");// 网站服务器URL
	public static final String BASE_URL_SP_SP = ProReader.getInstance().getProperty("server_base_url_sp_sp");// 网站服务器URL
	public static final String BASE_URL_OLD = ProReader.getInstance().getProperty("server_base_url_old");// 网站服务器URL
	public static final String SP_BASE_URL_OLD = ProReader.getInstance().getProperty("server_spbase_url_old");// 网站服务器URL
	public static final String SP_SEARCHBASE_URL = ProReader.getInstance().getProperty("server_spsearch_url");//// 主站新搜索接口测试URL
	public static final String BASE_URL_NEWSP = ProReader.getInstance().getProperty("server_base_url_newsp");// 网站服务器URL
	
	
	public static final String BASE_API = ProReader.getInstance().getProperty("server_url_api");//api地址
	public static final String WEIXIN_ACTION_BASE_URL = ProReader.getInstance().getProperty("server_weixin_action_base_url");// 网站服务器URL
	//微信用户维权css推送url
	public static final String CSS_WXFEEDBACK_URL =  ProReader.getInstance().getProperty("css_wxfeedback_url");
	
	public static final String AOLAI_INDEX_ADVERT_URL =  ProReader.getInstance().getProperty("aolai_index_advert_url");
	
	// bestPayClient.java 的变量
	public static final String BESTPAY_MEPFADDRESS_URL = ProReader.getInstance().getProperty("bestMepfAddress");
	public static final String BESTPAY_PARTNERID_VARIATE = ProReader.getInstance().getProperty("bestPartnerId");
	
	// 微信变量----存在于WeiXinGetToken.java 的地址
	public static final String WECHAT_URL = ProReader.getInstance().getProperty("weChatUrl");
	
	// 微信变量----存在于WeixinUtil.java 的变量
	public static final String WECHAT_APPID_VARIATE = ProReader.getInstance().getProperty("weChatAppId");
	public static final String WECHAT_APPSECRET_VARIATE = ProReader.getInstance().getProperty("weChatAppSecret");
	public static final String WECHAT_PAYSIGNKEY_VARIATE = ProReader.getInstance().getProperty("weChatPaySignKey");
	public static final String WECHAT_PARTNERID_VARIATE = ProReader.getInstance().getProperty("weChatPartnerId");
	public static final String WECHAT_PARTNERKEY_VARIATE = ProReader.getInstance().getProperty("weChatPartnerKey");
	public static final String PAY_NOTIFY_URL = ProReader.getInstance().getProperty("pay_notify_url");
	
	// 银联支付变量----存在于UnionUtil.java 的变量/地址
	public static final String UNIONU_MERCHANT_VARIATE = ProReader.getInstance().getProperty("onUnionUMerchant");
	public static final String UNIONU_SECRET_VARIATE = ProReader.getInstance().getProperty("onUnionUSecret");
	public static final String UNIONU_GATEWAY_URL = ProReader.getInstance().getProperty("onUnionUGatewayUrl");
	public static final String UNIONU_NOTIFY_URL = ProReader.getInstance().getProperty("onUnionUNotifyUrl");
	// 银联支付变量 ----存在于UnionPayModel.java 的变量
	public static final String UNIONPM_MERCHANT_VARIATE = ProReader.getInstance().getProperty("onUnionPMMerchant");
	public static final String UNIONPM_SPID_VARIATE = ProReader.getInstance().getProperty("onUnionPMSPId");
	public static final String UNIONPM_SYSPROVIDER_VARIATE = ProReader.getInstance().getProperty("onUnionPMSysProvider");
	
	// 支付宝地址----存在于AlipayModel.java 的地址
	public static final String ALIPAY_URL = ProReader.getInstance().getProperty("alipayUrl");
	
	// 拉卡拉支付地址及变量----存在于LakalaPayModel.java 的地址及变量
	public static final String LAKALAPAY_URL = ProReader.getInstance().getProperty("lakalaUrl");
	public static final String LAKALAPAY_MERCHANT_VARIATE = ProReader.getInstance().getProperty("lakalaMerchant");
	public static final String LAKALAPAY_MINCODE_VARIATE = ProReader.getInstance().getProperty("lakalaMinCode");
	public static final String LAKALAPAY_MERKEY_VARIATE = ProReader.getInstance().getProperty("lakalaMerKey");
	
	// 客户端需缓存的静态资源
	public static final String STATICCACHE_JS = ProReader.getInstance().getProperty("staticCacheJS");
	public static final String STATICCACHE_CSS = ProReader.getInstance().getProperty("staticCacheCSS");
	public static final String STATICCACHE_PIC = ProReader.getInstance().getProperty("staticCachePIC");
	
	// 客户端提示文案
	public static final String PROMPT = ProReader.getInstance().getProperty("prompt");
	
	public static String SERVER_IP;// 网站服务器IP
	static {
		try {
			String serverip = null;
        	java.util.Enumeration <java.net.NetworkInterface> allNetInterfaces = null;
            allNetInterfaces = java.net.NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                java.net.NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                }

                for (java.net.InterfaceAddress address : netInterface.getInterfaceAddresses()) {
                	 if (address.getAddress() instanceof java.net.Inet4Address) {
                		 java.net.Inet4Address inet4Address = (java.net.Inet4Address) address.getAddress();
                		 if (inet4Address.isReachable(1000)) {
                    		 serverip = inet4Address.getHostAddress();
                		 }
                	 }
                }
            }
			if (serverip == null || "127.0.0.1".equals(serverip)) {
				SERVER_IP = ProReader.getInstance().getProperty("server_ip");// 网站服务器IP
			} else {
				SERVER_IP = serverip;
			}
		} catch (Exception e) {
			SERVER_IP = ProReader.getInstance().getProperty("server_ip");// 网站服务器IP
			e.printStackTrace();
		}
	}

	/** 尚品iPhone产品号 */
	public static final String SP_IPHONE_PRODUCTNO = "2";
	/** 尚品奥莱iPhone产品号 */
	public static final String AOLAI_IPHONE_PRODUCTNO = "1";
	/** 首页专题活动图片高度（高分辨率） */
	public static final String SP_TOPIC_PICH_HIGH = "584";
	/** 首页专题活动图片宽度（高分辨率） */
	public static final String SP_TOPIC_PICW_HIGH = "640";
	/** 首页专题活动图片高度（低分辨率） */
	public static final String SP_TOPIC_PICH_LOW = "292";
	/** 首页专题活动图片宽度（低分辨率） */
	public static final String SP_TOPIC_PICW_LOW = "320";
	/** 商品详情页图片高度（高分辨率） */
	public static final String SP_PRODUCT_DETAIL_PICH_HIGH = "678";
	/** 商品详情页图片宽度（高分辨率） */
	public static final String SP_PRODUCT_DETAIL_PICW_HIGH = "488";
	/** 商品列表页图片高度（低分辨率） */
	public static final String SP_PRODUCT_DETAIL_PICH_LOW = "339";
	/** 商品列表页图片宽度（低分辨率） */
	public static final String SP_PRODUCT_DETAIL_PICW_LOW = "244";
	/** 商品列表页图片高度 */
	public static final String SP_PRODUCTS_PICH = "440";
	/** 商品列表页图片宽度 */
	public static final String SP_PRODUCTS_PICW = "320";
	// ***********************************************************
	public static final String SUCCESS = "0";// 登录成功，客户端已引用，主站已引用
	// ==================一般性错误码==========================
	public static final String NO_BIND_PHONE="2";
	public static final String SMS_CODE_ERROR="2";
	public static final String SMS_CODE_PROMPT="您输入的短信验证码有误！";
	public static final String ERROR_PHONE_HAS_BIND="3";
	public static final String PHONE_HAS_BIND_PROMPT = "该手机号已被其它账号绑定，请确认您输入的是否有误！";//
	public static final String PHONE_INVALID_PROMPT = "手机号码不正确！";//
	public static final String ERROR = "1";
	public static final String ERROR_NO_USERID = "-1";// 没有uerid参数，msg找不到用户
	public static final String ERROR_NO_SESSIONID = "-2";// 没有sessionid参数，msg会话已过期，请重新登录
	public static final String ERROR_NO_SESSIONID_PROMPT = "会话已过期，请重新登录！";//
	public static final String ERROR_SYSTEM = "-3";// 程序异常
	public static final String ERROR_SYSTEM_PROMPT = "系统开小差了，请稍等片刻~";//
	public static final String ERROR_LIMIT = "-8";// 程序异常
	public static final String ERROR_LIMIT_PROMPT = "您的操作过于频繁，请稍后重试！";//
	public static final String ERROR_FEE_FORMAT = "-4";// 订单金额格式非法
	public static final String ERROR_NO_PAYMODE = "-5";// 不支持的支付方式
	public static final String ERROR_SUBMITORDER = "-6";// 提交订单返回购物车，客户端已引用
	/** 订单错误编码3000---3999 */
	public static final String ERROR_ORDERSUBMIT="3001";//提交订单失败
	public static final String ERROR_GETPAY="3002";//获取支付数据失败
	public static final String ERROR_ADDRESS="3003";//地址不全
	public static final String INVALID_ADDRESS="3004";//无效地址
	public static final String LOW_STOCKS="3005";//库存不足
	public static final String ERROR_PRICE="3006";//价格错误
	
	public static final String ORDER_LOCKSKU_ERROR = "3009";
	public static final String ORDER_LOCKSKU_ERROR_PROMPT = "订单锁库存失败";
	public static final String ORDER_LOCKSKU_FAIL = "3010";
	public static final String ORDER_LOCKSKU_FAIL_PROMPT = "订单锁库存失败，订单已自动取消";
	public static final String ERROR_CANPAY="3007";//提交订单失败
	public static final String ERROR_INVALIDPARAMS = "-7";// 请求参数不对
	public static final String ERROR_INVALIDPARAMS_PROMPT = "请求参数不能为空";// 请求参数不对
	
	 
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
	
    // 海外支付宝主支付方式
    public static final String PAYTYPE_ID = "30";
    // 海外支付宝app子支付方式
    public static final String PAYTYPE_CHILD_ID = "107";
    // 接口返回成功标识
    public static final String SUCESS_CODE = "0";
    // 接口返回失败标识
    public static final String FAILURE_CODE = "1";
    
	// ==================push信息,缓存中key值==========================
	/** 尚品女性相关的广播（手动录入） */
	public static final String PUSH_BROADCAST_ANDROID_SHANGPIN_0 = "PUSH_BROADCAST_ANDROID_SHANGPIN_0";
	/** 尚品男性相关的广播（手动录入） */
	public static final String PUSH_BROADCAST_ANDROID_SHANGPIN_1 = "PUSH_BROADCAST_ANDROID_SHANGPIN_1";
	/** 尚品全部广播（手动录入） */
	public static final String PUSH_BROADCAST_ANDROID_SHANGPIN_2 = "PUSH_BROADCAST_ANDROID_SHANGPIN_2";
	/** 尚品奥莱女性相关的广播（手动录入） */
	public static final String PUSH_BROADCAST_ANDROID_AOLAI_0 = "PUSH_BROADCAST_ANDROID_AOLAI_0";
	/** 尚品奥莱男性相关的广播（手动录入） */
	public static final String PUSH_BROADCAST_ANDROID_AOLAI_1 = "PUSH_BROADCAST_ANDROID_AOLAI_2";
	/** 尚品奥莱全部广播（手动录入） */
	public static final String PUSH_BROADCAST_ANDROID_AOLAI_2 = "PUSH_BROADCAST_ANDROID_AOLAI_3";
	/** 尚品女性相关的广播（系统自动发送） */
	public static final String PUSH_BROADCAST_ANDROID_SHANGPIN_AUTO_0 = "PUSH_BROADCAST_ANDROID_SHANGPIN_AUTO_0";
	/** 尚品男性相关的广播（系统自动发送） */
	public static final String PUSH_BROADCAST_ANDROID_SHANGPIN_AUTO_1 = "PUSH_BROADCAST_ANDROID_SHANGPIN_AUTO_1";
	/** 尚品全部广播（系统自动发送） */
	public static final String PUSH_BROADCAST_ANDROID_SHANGPIN_AUTO_2 = "PUSH_BROADCAST_ANDROID_SHANGPIN_AUTO_2";
	/** 尚品奥莱女性相关的广播（系统自动发送） */
	public static final String PUSH_BROADCAST_ANDROID_AOLAI_AUTO_0 = "PUSH_BROADCAST_ANDROID_AOLAI_AUTO_0";
	/** 尚品奥莱男性相关的广播（系统自动发送） */
	public static final String PUSH_BROADCAST_ANDROID_AOLAI_AUTO_1 = "PUSH_BROADCAST_ANDROID_AOLAI_AUTO_2";
	/** 尚品奥莱全部广播（系统自动发送） */
	public static final String PUSH_BROADCAST_ANDROID_AOLAI_AUTO_2 = "PUSH_BROADCAST_ANDROID_AOLAI_AUTO_3";
	// ==================品类首页,缓存中key值==========================
	public static final String A01B01_NEW = "A01B01_NEW";
	public static final String A01B01_RECOMMEND = "A01B01_RECOMMEND";
	public static final String A01B02_NEW = "A01B02_NEW";
	public static final String A01B02_RECOMMEND = "A01B02_RECOMMEND";
	public static final String A01B03_NEW = "A01B03_NEW";
	public static final String A01B03_RECOMMEND = "A01B03_RECOMMEND";
	public static final String A01B04_NEW = "A01B04_NEW";
	public static final String A01B04_RECOMMEND = "A01B04_RECOMMEND";
	public static final String A02B01_NEW = "A02B01_NEW";
	public static final String A02B01_RECOMMEND = "A02B01_RECOMMEND";
	public static final String A02B02_NEW = "A02B02_NEW";
	public static final String A02B02_RECOMMEND = "A02B02_RECOMMEND";
	public static final String A02B03_NEW = "A02B03_NEW";
	public static final String A02B03_RECOMMEND = "A02B03_RECOMMEND";
	public static final String A02B04_NEW = "A02B04_NEW";
	public static final String A02B04_RECOMMEND = "A02B04_RECOMMEND";
	// ==================具体业务错误码==========================
	// private static final String ERROR_EMAIL = "f5cb9a37";// 请输入正确的邮箱地址
	// private static final String ERROR_EMAIL_PHONE = "83392ea9";// 请输入正确的邮箱或手机号
	// private static final String ERROR_PASSWORD = "82f5a5d4";// 密码不正确
	// private static final String ERROR_ORIGPASSWORD = "7b2343a9";// 原密码输入错误
	// private static final String ERROR_ORDER_NO = "ed610eb6";// 订单号不存在
	// private static final String ERROR_ORDER_CANCEL = "2e16196e";// 该订单已取消
	// private static final String ERROR_ORDER_CONFIRM = "92ed20fe";// 该订单已确认
	// private static final String ERROR_ORDER_CONFIRMM_ONEYRECEIPT = "a98e3ede";// 该订单收款已确认
	// private static final String ERROR_ORDER_CONSIGNING = "a51a43eb";// 该订单配货中
	// private static final String ERROR_ORDER_PARTCONSIGNING = "369bc595";// 该订单已部分发货
	// private static final String ERROR_ORDER_ALLCONSIGNING = "fff8efd4";// 该订单已全部发货
	// private static final String ERROR_ORDER_CONFIRMCOD = "29bebf18";// 该订单COD收款已确认
	// private static final String ERROR_ORDER_TERMINATEDEAL = "411d9630";// 该订单交易异常终止
	// private static final String ERROR_ORDER_PARTDEAL = "90e5d5c9";// 该订单交易部分完成
	// private static final String ERROR_ORDER_ALLDEAL = "5986ff88";// 该订单交易全部完成
	// private static final String ERROR_ORDER_STATUS = "fe74ccb2";// 订单状态不正确
	// private static final String ERROR_PHONE_FORMAT = "4fdb48a1";// 手机号码不正确
	// private static final String ERROR_REQUIRED = "7da6cc6a";// 必填项不能为空
	// private static final String ERROR_CAPTCHA = "56fd3d21";// 验证码输入有误，请重新输入
	// private static final String ERROR_CAPTCHA_INVALID = "34da170a";// 验证码已失效，请输入新验证码或重新获取
	// private static final String ERROR_CAPTCHA_REQUIRED = "0c3d5471";// 请输入验证码
	// private static final String ERROR_CAPTCHA_FAULT = "d3777f6a";// 验证码错误,请重新输入
	// private static final String ERROR_CAPTCHA_INVALID1 = "6d6d6e32";// 验证码已失效,请输入最新验证码或重新获取
	// private static final String ERROR_PASSWORD_CONFIRM = "55933d39";// 两次密码输入不一致,请重新输入
	// private static final String ERROR_ACCOUNT_PASSWORD = "0b0a9873";// 账号或密码错误,请重新输入
	// private static final String ERROR_ACCOUNT_LOCK = "1a396c8a";// 账号已锁定
	// private static final String ERROR_REGISTER_EMAIL_EXISTING = "de0718a6";// 该邮箱已被注册，请更换其他邮箱
	// private static final String ERROR_PHONE_EXISTING = "4ab2ba31";// 手机号码已经存在
	// private static final String ERROR_REGISTER_REQUIRED = "f7ac9e05";// 请填写注册信息
	// private static final String ERROR_REGISTER_EMAIL_FORMAT = "e7d7f715";// 请输入常用的电子邮箱地址
	// private static final String ERROR_REGISTER_PASSWORD_LENGTH = "a0e4572c";// 密码长度由6-16位字符组成
	// private static final String ERROR_REGISTER_USERNAME_MAXLENGTH = "263ca896";// 姓名不得超过10个汉字或20个英文字符
	// private static final String ERROR_REGISTER_USERNAME_MINLENGTH = "c5f0cf64";// 姓名不得少于2个字符
	// private static final String ERROR_INVITATION_CODE_INVALID = "59482d7d";// 邀请码无效
	// private static final String ERROR_GENDER_REQUIRED = "47b35d30";// 请选择您的性别
	// private static final String ERROR_PASSWORD_REQUIRED = "ef97e9e7";// 密码不能为空
	// private static final String ERROR_USERNAME_TRUE = "57964eb5";// 请填写您的真实姓名"},
	// private static final String ERROR_IN_CARD_LENGTH = "d2dd6980";// 请输入您的IN卡号码前9位"},
	// private static final String ERROR_IN_CARD_FAULT = "3fef002e";// 您的IN卡号码前9位有误"},
	// private static final String str = "ecb862d3";// 请输入您的信用卡号码前6位和后4位"},
	// private static final String str = "602194ed";// 请填写正确的信用卡号码前6位"},
	// private static final String str = "a95175e5";// 请填写正确的信用卡号码后4位"},
	// private static final String str = "50447bc0";// 收货人姓名不能为空"},
	// private static final String str = "37a06a53";// 请输入您的手机号码或者固定电话号码"},
	// private static final String str = "065dfe91";// 地区信息不完整"},
	// private static final String str = "121d1bf7";// 收货人地址不能为空"},
	// private static final String str = "1982bfaa";// 邮件格式不正确"},
	// private static final String str = "c8c2a57b";// 请填写手机号码"},
	// private static final String str = "d1fb23a6";// 用户创建失败"},
	// private static final String str = "edbbe46c";// 支付宝ID不能为空"},
	// private static final String str = "f432ea05";// 登录失败"},
	// private static final String str = "99f787a0";// 身份验证失败"},
	// private static final String str = "af301fd3";// 收货人ID不能为空"},
	// private static final String str = "492df162";// 您的地址薄已满，请删除或修改已有地址"},
	// private static final String str = "0732a0fb";// 收货地址ID不能为空"},
	// private static final String str = "fc3c53bb";// 邮箱已经被其他用户绑定"},
	// private static final String str = "adcd6465";// 手机已经被其他用户绑定"},
	// private static final String str = "e2e04063";// 请输入注册邮箱"},
	// private static final String str = "b9d21ad3";// 邮箱地址不存在"},
	// private static final String str = "b3adf8b4";// 密码修改失败"},
	// private static final String str = "b75ec0e3";// 链接地址已过期"},
	// private static final String str = "a09d7804";// 链接地址无效"},
	// private static final String str = "96feb981";// 没有绑定邮箱或手机"},
	// private static final String str = "6f263481";// 升级失败"},
	// private static final String str = "d09f9f95";// 电话号码不正确"},
	// private static final String str = "a905893a";// 请输入正确收货地址！"},
	// private static final String str = "6d58e46e";// 发送失败"},
	// private static final String str = "a1793643";// 真实姓名不符合要求"},
	// private static final String str = "6e15a651";// 收货人姓名不符合要求"},
	// private static final String str = "ac6c777c";// 收货地址不符合要求"},
	// private static final String str = "357d50b6";// 邮编不正确"},
	// private static final String str = "54c2b76f";// 库存不足"},
	// private static final String str = "6137612f";// 你的购物袋已有10件商品，请结算后再购买"},
	// private static final String str = "d3df3950";// 商品所属活动已结束"},
	// private static final String str = "6194cca8";// 无可结算商品"},
	// private static final String str = "d82a8abd";// 订单提交失败请稍候再试"},
	// private static final String str = "9912c033";// 订单信息不正确"},
	// private static final String str = "4de22ccb";// 请确认收货地址"},
	// private static final String str = "83ca2b90";// 请确认发票地址"},
	// private static final String str = "25db6ae2";// 请选择发票类型"},
	// private static final String str = "7c77479e";// 请选择发票内容"},
	// private static final String str = "f2fad375";// 请选择收货时间"},
	// private static final String str = "9b64b9ed";// 请选择优惠券"},
	// private static final String str = "44ac97b8";// 抱歉，商品已下架"},
	// private static final String str = "7e3c4649";// 错误的商品信息"},
	// private static final String str = "85135908";// 库存不足请重新选择！
	// private static final String str = "ad611ec6";// 该订单不能取消"}
	// private static final String str = "b2c267e0";//商品不属于奥莱
	// private static final String str ="a00b3c5a";//库存数量小于购买数量
	// private static final String str ="2bc1c5c3";//商品所属专题已过期
	// ***********************************************************
	public static final String STOCKSYNCZERO_SUCCESS = "1";

}
