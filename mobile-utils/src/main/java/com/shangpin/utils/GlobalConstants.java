package com.shangpin.utils;


/**
 * 
 * @author sunweiwei
 * @version v1.0
 */
public class GlobalConstants {

    // ------------ httpclient config begin------------------//

    // 每个主机的最大并行链接数,为每个区设置最大的并发连接数,默认每个路由基础上的连接不超过10个
    public static final int DEFAULT_MAX_PER_ROUTE = PropertyUtil.getIntValue("httpclient.default_max_per_route", 10);
    // 客户端总并行链接最大数。默认值总连接数不能超过200
    public static final int MAX_TOTAL = PropertyUtil.getIntValue("httpclient.max_total", 200);
    // http连接超时时间。默认值设置为30秒
    public static final int CONNECT_TIMEOUT = PropertyUtil.getIntValue("httpclient.connect_timeout", 1000000);
    // socket连接超时时间。默认值设置为15秒
    public static final int SOCKET_TIMEOUT = PropertyUtil.getIntValue("httpclient.socket_timeout", 105000);

    // ------------ httpclient config end------------------//

    // ------------ redis config begin------------------//

    public static final int REDIS_MAX_TOTAL = PropertyUtil.getIntValue("redis.max_total", 1024);
    public static final int REDIS_MAX_IDLE = PropertyUtil.getIntValue("redis.max_idle", 200);
    public static final int REDIS_MAX_WAIT_MILLIS = PropertyUtil.getIntValue("redis.max_wait_millis", 1500);
    public static final int REDIS_EXPIRE_TIME = PropertyUtil.getIntValue("redis.expire_time", 60000);
    public static final int REDIS_DEFAULT_TIME_OUT = PropertyUtil.getIntValue("redis.default_time_out", 1500);
    public static final boolean REDIS_TEST_ON_BORROW = PropertyUtil.getBoolValue("redis.test_on_borrow", true);
    public static final boolean REDIS_TEST_ON_RETURN = PropertyUtil.getBoolValue("redis.test_on_return", true);
    public static final String REDIS_HOST = PropertyUtil.getStrValue("redis.host");
    public static final int REDIS_PORT = PropertyUtil.getIntValue("redis.port", 6379);
    public static final String REDIS_PASSWORD = PropertyUtil.getStrValue("redis.password");
    
    //优惠券server
    public static final int COUPON_REDIS_MAX_TOTAL = PropertyUtil.getInt("redis.coupon.max_total", 1024);
    public static final int COUPON_REDIS_MAX_IDLE = PropertyUtil.getInt("redis.coupon.max_idle", 200);
    public static final int COUPON_REDIS_MAX_WAIT_MILLIS = PropertyUtil.getInt("redis.coupon.max_wait_millis", 1500);
    public static final int COUPON_REDIS_EXPIRE_TIME = PropertyUtil.getInt("redis.coupon.expire_time", 60000);
    public static final int COUPON_REDIS_DEFAULT_TIME_OUT = PropertyUtil.getInt("redis.coupon.default_time_out", 1500);
    public static final boolean COUPON_REDIS_TEST_ON_BORROW = PropertyUtil.getBoolean("redis.coupon.test_on_borrow", true);
    public static final boolean COUPON_REDIS_TEST_ON_RETURN = PropertyUtil.getBoolean("redis.coupon.test_on_return", true);
    public static final String COUPON_REDIS_HOST = PropertyUtil.getString("redis.coupon.host");
    public static final int COUPON_REDIS_PORT = PropertyUtil.getInt("redis.coupon.port", 6379);
    public static final String COUPON_REDIS_PASSWORD = PropertyUtil.getString("redis.coupon.password");
    
    //后台服务server
    public static final int SERVICE_REDIS_MAX_TOTAL = PropertyUtil.getInt("redis.service.max_total", 1024);
    public static final int SERVICE_REDIS_MAX_IDLE = PropertyUtil.getInt("redis.service.max_idle", 200);
    public static final int SERVICE_REDIS_MAX_WAIT_MILLIS = PropertyUtil.getInt("redis.service.max_wait_millis", 1500);
    public static final int SERVICE_REDIS_EXPIRE_TIME = PropertyUtil.getInt("redis.service.expire_time", 60000);
    public static final int SERVICE_REDIS_DEFAULT_TIME_OUT = PropertyUtil.getInt("redis.service.default_time_out", 1500);
    public static final boolean SERVICE_REDIS_TEST_ON_BORROW = PropertyUtil.getBoolean("redis.service.test_on_borrow", true);
    public static final boolean SERVICE_REDIS_TEST_ON_RETURN = PropertyUtil.getBoolean("redis.service.test_on_return", true);
    public static final String SERVICE_REDIS_HOST = PropertyUtil.getString("redis.service.host");
    public static final int SERVICE_REDIS_PORT = PropertyUtil.getInt("redis.service.port", 6379);
    public static final String SERVICE_REDIS_PASSWORD = PropertyUtil.getString("redis.service.password");

    // ------------ redis config end------------------//
    
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
 // ------------ push信息 end------------------//
}
