package com.shangpin.wireless.domain;

import com.shangpin.wireless.util.ProReader;

public class Constants {

	/** push证书，false为生产环境，true为测试环境*/
	public static final boolean PUSH_TEST = Boolean.parseBoolean(ProReader.getInstance().getProperty("push_test"));
	public static final String SP_KEYSTORE = ProReader.getInstance().getProperty("shangpin_keyStore");
	public static final String AL_KEYSTORE = ProReader.getInstance().getProperty("aolai_keyStore");
	public static final String BASE_URL_AL_AL = ProReader.getInstance().getProperty("server_base_url_al_al");// 网站服务器URL
	public static final String BASE_URL_SP_SP = ProReader.getInstance().getProperty("server_base_url_sp_sp");// 网站服务器URL
	public static final String AL_BASE_URL_OLD = ProReader.getInstance().getProperty("server_albase_url_old");// 网站服务器URL
	public static final String SP_BASE_URL_OLD = ProReader.getInstance().getProperty("server_spbase_url_old");// 网站服务器URL
	public static final String BASE_TRADE_URL = ProReader.getInstance().getProperty("server_base_url_trade");// 新订单管理

	public static final String CODE_SUCCESS = "0";// 登录成功，客户端已引用，主站已引用
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
}
